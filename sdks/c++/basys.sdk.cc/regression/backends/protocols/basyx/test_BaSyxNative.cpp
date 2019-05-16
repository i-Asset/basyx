/*
 * test_BaSyxNative.cpp
 * Tests the whole loop of BaSyxNativeConnector -> BaSyxNativeProvider
 *  Created on: 15.08.2018
 *      Author: schnicke
 */

#include <atomic>
#include <memory>

#include <abstraction/Thread.h>

#include <gtest/gtest.h>

#include "backends/protocols/connector/basyx/BaSyxNativeConnector.h"
#include "backends/protocols/provider/basyx/BaSyxTCPServer.h"

#include "regression/backends/protocols/provider/basyx/frame/MockupModelProvider.h"



std::atomic_flag running = ATOMIC_FLAG_INIT;

void serverLoop(std::unique_ptr<basyx::provider::TCPServer<MockupModelProvider>> & tcpServer)
{
	while (running.test_and_set(std::memory_order_acquire))
	{
		tcpServer->update();
	}
};

/**
 * Creates the test environment needed in the following tests:
 * - Provides BaSyxTCPServer
 * - Provides BaSyxNativeConnector
 */
class BaSyxNativeTest: public ::testing::Test {
protected:
	const int port = 6112;

	std::unique_ptr<basyx::connector::NativeConnector> connector;
	std::unique_ptr<basyx::provider::TCPServer<MockupModelProvider>> tcpServer;
	std::unique_ptr<MockupModelProvider> mockup;
	basyx::thread serverThread{};

	virtual void SetUp() {
		running.test_and_set(std::memory_order_acquire);

		mockup = util::make_unique<MockupModelProvider>();
		tcpServer = util::make_unique<basyx::provider::TCPServer<MockupModelProvider>>(mockup.get(), port);

		serverThread = basyx::thread{serverLoop, std::ref(tcpServer)};

		connector = util::make_unique<basyx::connector::NativeConnector>("127.0.0.1", port);
	}

	virtual void TearDown() {
		running.clear(std::memory_order_release);

		tcpServer->Close();

		serverThread.join();
	}

};

TEST_F(BaSyxNativeTest, getTest) {
	std::string path = "TestPath";
	basyx::any val = connector->basysGet(path);

	// Check if correct call occured
	ASSERT_EQ(mockup->called, MockupModelProvider::CalledFunction::GET);
	ASSERT_EQ(mockup->path, path);
	
	// Check return value
	ASSERT_TRUE(val.InstanceOf<int>());
	ASSERT_EQ(val.Get<int>(), 2);
}

TEST_F(BaSyxNativeTest, setTest) {
	std::string path = "TestPath";
	basyx::any val = 10;
	connector->basysSet(path, val);
	
	while (mockup->called != MockupModelProvider::CalledFunction::SET)
		;

	// Check if correct call occured
	ASSERT_EQ(mockup->called, MockupModelProvider::CalledFunction::SET);
	ASSERT_EQ(mockup->path, path);
}

TEST_F(BaSyxNativeTest, createTest) {
	std::string path = "TestPath";
	basyx::any val = 10;
	connector->basysCreate(path, val);
	
	// Check if correct call occured
	ASSERT_EQ(mockup->called, MockupModelProvider::CalledFunction::CREATE);
	ASSERT_EQ(mockup->path, path);
	ASSERT_TRUE(mockup->val.InstanceOf<int>());
	ASSERT_EQ(mockup->val.Get<int>(), 10);
}

TEST_F(BaSyxNativeTest, deleteSimpleTest) {
	std::string path = "TestPath";
	connector->basysDelete(path);
	
	// Check if correct call occured
	ASSERT_EQ(mockup->called, MockupModelProvider::CalledFunction::DELETE_SIMPLE);
	ASSERT_EQ(mockup->path, path);
}

TEST_F(BaSyxNativeTest, deleteComplexTest) {
	std::string path = "TestPath";
	basyx::any val = 10;
	connector->basysDelete(path, val);
	
	// Check if correct call occured
	ASSERT_EQ(mockup->called, MockupModelProvider::CalledFunction::DELETE_COMPLEX);
	ASSERT_EQ(mockup->path, path);
	ASSERT_TRUE(mockup->val.InstanceOf<int>());
	ASSERT_EQ(mockup->val.Get<int>(), 10);
}

// TODO: invokeTest
//TEST_F(BaSyxNativeTest, invokeTest) {
//	std::string path = "TestPath";
//	basyx::any val = 10;
//	basyx::any retVal = connector->basysInvoke(path, val);
//
//	// Check if correct call occured
//	ASSERT_EQ(mockup->called, MockupModelProvider::CalledFunction::INVOKE);
//	ASSERT_EQ(mockup->path, path);
//	ASSERT_EQ(mockup->val->getType(), BASYS_INT);
//	ASSERT_EQ(static_cast<BRef<BValue>>(mockup->val)->getInt(), 10);
//	
//	// Check return value
//	ASSERT_EQ(retVal->getType(), BASYS_INT);
//	ASSERT_EQ(retVal->getInt(), 3);
//}