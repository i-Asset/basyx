/*
 * test_cxxhandler.cpp
 *
 *  Created on: 12.03.2019
 *      Author: psota
 */

/////////////////////////////////////////////////////////////////
// Includes
// GTest
#include "gtest/gtest.h"

#include <backends/provider/vab/VABHashmapProvider.h>

#include <util/any.h>

#include <memory>
#include <unordered_map>
#include <vector>

class TestBaSyxHashmapProviderMap : public ::testing::Test {
public:
    basyx::provider::HashmapProvider hashMapProvider;

	const std::string mapPath{"property1/propertyMap"};

    virtual void SetUp()
    {
        basyx::objectMap_t outerMap, innerMap, propertyMap;
        propertyMap.emplace("test", 123);
		innerMap.emplace("propertyMap", std::move(propertyMap));
        outerMap.emplace("property1", std::move(innerMap));

        hashMapProvider = basyx::provider::HashmapProvider { std::move(outerMap) };
    }

    virtual void TearDown()
    {
    }
};


TEST_F(TestBaSyxHashmapProviderMap, testGet)
{
	basyx::any & anyMap = hashMapProvider.getModelPropertyValue(mapPath);
	ASSERT_TRUE(anyMap.InstanceOf<basyx::objectMap_t>());

	auto & objectMap = anyMap.Get<basyx::objectMap_t&>();
	ASSERT_EQ(objectMap.size(), 1);
};

TEST_F(TestBaSyxHashmapProviderMap, testUpdateComplete)
{
	// Replace entries in map
	basyx::objectMap_t replacement;
	replacement.emplace("a", 1);
	replacement.emplace("b", 2);

	hashMapProvider.setModelPropertyValue(mapPath, std::move(replacement));

	// Read values back
	auto & map = hashMapProvider.getModelPropertyValue(mapPath).Get<basyx::objectMap_t&>();
	
	// Check test case results
	ASSERT_EQ(map.size(), 2);
	ASSERT_EQ(map["a"], 1);
	ASSERT_EQ(map["b"], 2);
};


TEST_F(TestBaSyxHashmapProviderMap, testUpdateElement)
{
	// Create new entry in map
	hashMapProvider.createValue(mapPath + "/a", 2);

	// Read values back
	auto & map = hashMapProvider.getModelPropertyValue(mapPath).Get<basyx::objectMap_t&>();

	// Check test case results
	ASSERT_EQ(map.size(), 2);
	ASSERT_EQ(map["a"], 2);
	ASSERT_EQ(map["test"], 123);
}

TEST_F(TestBaSyxHashmapProviderMap, testRemoveElement)
{
	// Remove entry from map
	hashMapProvider.deleteValue(mapPath + "/test");

	// Read values back
	auto & map = hashMapProvider.getModelPropertyValue(mapPath).Get<basyx::objectMap_t&>();

	// Check test case results
	ASSERT_EQ(map.size(), 0);
}