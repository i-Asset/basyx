#ifdef HUH

#include <gtest/gtest.h>

#include "vab/core/IModelProvider.h"

#include <memory>
#include <unordered_map>
#include <vector>

using namespace basyx;

class vabHashMap : public vab::core::IModelProvider
{
public:
	basyx::object::hash_map_t<basyx::object> map;
	vabHashMap() {};
	vabHashMap(basyx::object::object_map_t && map) : map(std::move(map)) {};
public:        
	virtual basyx::object getModelPropertyValue(const std::string& path) override
	{

	};

	virtual void setModelPropertyValue(const std::string& path, const basyx::object& newValue) {};
	virtual void createValue(const std::string& path, const basyx::object & addedValue) {};
	virtual void deleteValue(const std::string& path, const basyx::object & deletedValue) {};
	virtual void deleteValue(const std::string& path) {};
};


class TestBaSyxObjHashmapProvider : public ::testing::Test {
public:
	vabHashMap hashMapProvider;

    virtual void SetUp()
    {
        basyx::object::list_t<int> collection;
        collection.emplace_back(1);
        collection.emplace_back(2);

        basyx::object::object_map_t outerMap, innerMap, propertyMap;

		propertyMap.emplace("Test", 321);
		propertyMap.emplace("test", 123);

		innerMap.emplace("property1.1", 7);
		innerMap.emplace("property1.2", std::move(collection));
		innerMap.emplace("propertyMap", std::move(propertyMap));
			   		 	  
		outerMap.emplace("property1", std::move(innerMap));

        hashMapProvider = vabHashMap{ std::move(outerMap) };
    }

    virtual void TearDown()
    {
    }
};

TEST_F(TestBaSyxObjHashmapProvider, GetPropertyValue)
{
	// Get property value
	basyx::object  property1 = hashMapProvider.getModelPropertyValue("property1");

	basyx::object  value1 = hashMapProvider.getModelPropertyValue("property1/property1.1");
	basyx::object  value2 = hashMapProvider.getModelPropertyValue("/property1/property1.1");
	basyx::object  value3 = hashMapProvider.getModelPropertyValue("property1/property1.1/");
	basyx::object  value4 = hashMapProvider.getModelPropertyValue("/property1/property1.1/");

	basyx::object  mapTest1 = hashMapProvider.getModelPropertyValue("property1/propertyMap/Test");
	basyx::object  mapTest2 = hashMapProvider.getModelPropertyValue("property1/propertyMap/test");

	// Check test case results
	ASSERT_TRUE(property1.InstanceOf<basyx::object::object_map_t>());
	ASSERT_EQ(property1.Get<basyx::object::object_map_t&>().size(), 3);

	ASSERT_TRUE(value1.InstanceOf<int>());
	ASSERT_TRUE(value2.InstanceOf<int>());
	ASSERT_TRUE(value3.InstanceOf<int>());
	ASSERT_TRUE(value4.InstanceOf<int>());
	ASSERT_TRUE(mapTest1.InstanceOf<int>());
	ASSERT_TRUE(mapTest2.InstanceOf<int>());

	ASSERT_EQ(value1.Get<int>(), 7);
	ASSERT_EQ(value2.Get<int>(), 7);
	ASSERT_EQ(value3.Get<int>(), 7);
	ASSERT_EQ(value4.Get<int>(), 7);
	ASSERT_EQ(mapTest1.Get<int>(), 321);
	ASSERT_EQ(mapTest2.Get<int>(), 123);
}

int testFunc(int a, int b)
{
	return a + b;
}

//TEST_F(TestBaSyxObjHashmapProvider, TestInvoke)
//{
//	hashMapProvider.createValue("function", basyx::make_function(testFunc));
//
//	basyx::object::list_t<int> args{ 1,2 };
//
//	auto val = hashMapProvider.invokeOperationImpl("function", args);
//
//	ASSERT_ANY_EQ(val, 3);
//}

TEST_F(TestBaSyxObjHashmapProvider, SetPropertyValue)
{
	// Set and reread property value
	hashMapProvider.setModelPropertyValue("property1/property1.1", 12);
	basyx::object  property1_1 = hashMapProvider.getModelPropertyValue("property1/property1.1");

	// Check test case results
	ASSERT_TRUE(property1_1.InstanceOf<int>());
	ASSERT_EQ(property1_1.Get<int>(), 12);

	// Change value back
	hashMapProvider.setModelPropertyValue("property1/property1.1", 7);
	basyx::object  property1_1b = hashMapProvider.getModelPropertyValue("property1/property1.1");

	// Check test case results
	ASSERT_TRUE(property1_1b.InstanceOf<int>());
	ASSERT_EQ(property1_1b.Get<int>(), 7);
}

TEST_F(TestBaSyxObjHashmapProvider, CreateDelete)
{
    basyx::object  property1 = hashMapProvider.getModelPropertyValue("property1");
    basyx::object  property1_1 = hashMapProvider.getModelPropertyValue("property1/property1.1");

    ASSERT_TRUE(property1.InstanceOf<basyx::object::object_map_t>());
    ASSERT_TRUE(property1_1.InstanceOf<int>());
    ASSERT_EQ(property1_1.Get<int>(), 7);

    // - Create property directly in VAB element
    hashMapProvider.createValue("property2", 21);
    // - Create property in contained hashmap
    hashMapProvider.createValue("property1/property1.4", 22);
    // - Create property in collection in contained hashmap
    hashMapProvider.createValue("property1/property1.2", 23);

    // Read values back
    basyx::object  property2 = hashMapProvider.getModelPropertyValue("property2");
    // - Check test case results
    ASSERT_TRUE(property2.InstanceOf<int>());
    ASSERT_EQ(property2.Get<int>(), 21);

    // Read values back
    basyx::object  property1_4 = hashMapProvider.getModelPropertyValue("property1/property1.4");
    // - Check test case results
    ASSERT_TRUE(property1_4.InstanceOf<int>());
    ASSERT_EQ(property1_4.Get<int>(), 22);

    // Read values back
    basyx::object  property1_2 = hashMapProvider.getModelPropertyValue("property1/property1.2");
    // - Check test case results
    ASSERT_TRUE(property1_2.InstanceOf<basyx::object::list_t<int>>());
    ASSERT_EQ(property1_2.Get<basyx::object::list_t<int>&>().size(), 3);

    // Delete properties
    hashMapProvider.deleteValue("property2");
    hashMapProvider.deleteValue("property1/property1.4");
//   	hashMapProvider.deleteValue("property1/property1.2", 23);

    // Read values back
    //basyx::object  property2_del = hashMapProvider.getModelPropertyValue("property2");	// - Check test case results
    //assertEquals(null, value6);

    // Read values back
    //basyx::object  property1_4del = hashMapProvider.getModelPropertyValue("property1/property1.4");	// - Check test case results
    // - Check test case results
    //assertEquals(null, value7);

    // Read values back
    basyx::object  property1_2b = hashMapProvider.getModelPropertyValue("property1/property1.2");
    // - Check test case results
    ASSERT_TRUE(property1_2b.InstanceOf<basyx::object::list_t<int>>());
	ASSERT_EQ(property1_2b.Get<basyx::object::list_t<int>&>().size(), 3);
}

#endif