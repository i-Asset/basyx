/*
 * any_serializer.h
 *
 *  Created on: 21.03.2019
 *      Author: psota
 */

#ifndef BASYX_JSON_DESERIALIZER_H
#define BASYX_JSON_DESERIALIZER_H

#include <nlohmann/json.hpp>

#include <BaSyx/shared/object/object_header.h>
#include <BaSyx/shared/types.h>

#include "typeid.h"

namespace basyx {
namespace serialization {
namespace json {
    using json_t = nlohmann::json;

    // This class handles the actual deserialization of the BaSyx JSON format
    // Assumes that every JSON is well formed and adheres to the VAB serialization standard
    class deserialize_helper {
    public:
        static basyx::object deserialize(const json_t& json)
        {
            // Get the basystype
            // auto basysType = json[basyx::serialization::typeSpecifier].get<std::string>();

            if (json.is_primitive()) {
                return deserialize_helper::fundamental(json);
            } else // deserialize objectMap
            if (json.is_object()) 
			{
				if (json.find(basyx::serialization::typeSpecifier) != json.end())
				{	
					// deserialize typed basyx list
					return collection(json);
				}
				else // assume is map
				{
					return basyx::object{ std::forward<basyx::object::object_map_t>(deserialize_helper::objectMap(json)) };
				}
            } 

			return basyx::object::make_null();
        }

    private:
        // Deserialize a fundamental type (and std::string) from JSON and return a basyx::object object holding the deserialized value
        static basyx::object fundamental(const json_t& json)
        {
			if (json.is_number_float())
				return basyx::object{ json.get<double>() };
			else if (json.is_number_integer())
				return basyx::object{ json.get<int>() };
			else if (json.is_boolean())
				return basyx::object{ json.get<bool>() };
            else if (json.is_string())
                return basyx::object { json.get<std::string>() };

            // Get the typeId and value node
            //auto typeId = json[basyx::serialization::typeIdSpecifier].get<std::string>();
            //auto valueJson = json[basyx::serialization::valueSpecifier];

            //if (typeId == basyx::serialization::basysType<int>::string) {
            //	return basyx::object{ valueJson.get<int>() };
            //}
            //else if (typeId == basyx::serialization::basysType<bool>::string) {
            //	return basyx::object{ valueJson.get<bool>() };
            //}
            //else if (typeId == basyx::serialization::basysType<float>::string) {
            //	return basyx::object{ valueJson.get<float>() };
            //}
            //else if (typeId == basyx::serialization::basysType<double>::string) {
            //	return basyx::object{ valueJson.get<double>() };
            //}
            //else if (typeId == basyx::serialization::basysType<char>::string) {
            //	return basyx::object{ valueJson.get<char>() };
            //}
            //else if (typeId == basyx::serialization::basysType<std::string>::string) {
            //	return basyx::object{ valueJson.get<std::string>() };
            //}

            return basyx::object::make_null();
        }

		static basyx::type::valueType check_array_type(const json_t & json)
		{
			basyx::type::valueType last_type = basyx::type::valueType::Null;
			basyx::type::valueType current_type = basyx::type::valueType::Null;

			for (const auto& val : json) {
				if (val.is_boolean()) {
					current_type = basyx::type::valueType::Bool;
				}
				else if (val.is_number_integer()) {
					current_type = basyx::type::valueType::Int;
				}
				else if (val.is_number_float()) {
					current_type = basyx::type::valueType::Float;
				}
				else if (val.is_string()) {
					current_type = basyx::type::valueType::String;
				};

				if ( last_type != basyx::type::valueType::Null && current_type != last_type)
					return basyx::type::valueType::Object;

				last_type = current_type;
			}

			return last_type;
		};

		static basyx::object collection(const json_t & json)
		{
			if (json[basyx::serialization::typeSpecifier] == "list")
			{
				return deserialize_helper::list(json);
			}
			else if (json[basyx::serialization::typeSpecifier] == "set")
			{
				return deserialize_helper::set(json);
			};

			return basyx::object::make_null();
		};

		static basyx::object list(const json_t & json)
		{
			auto json_array = json[basyx::serialization::valueSpecifier];

			if (json_array.empty())
				return basyx::object::make_list<int>();

			if (json_array.is_array())
			{
				auto value_type = check_array_type(json_array);

				switch (value_type)
				{
				case basyx::type::valueType::Bool:
					return deserialize_helper::list_t<bool>(json_array);
				case basyx::type::valueType::Int:
					return deserialize_helper::list_t<int>(json_array);
				case basyx::type::valueType::String:
					return deserialize_helper::list_t<std::string>(json_array);
				case basyx::type::valueType::Float:
					return deserialize_helper::list_t<double>(json_array);
				case basyx::type::valueType::Object:
					return deserialize_helper::object_list(json_array);
				};
			}

			return basyx::object::make_null();
		};

		static basyx::object set(const json_t & json)
		{
			auto json_array = json[basyx::serialization::valueSpecifier];

			if (json_array.empty())
				return basyx::object::make_set<int>();

			if (json_array.is_array())
			{
				auto value_type = check_array_type(json_array);

				switch (value_type)
				{
				case basyx::type::valueType::Bool:
					return deserialize_helper::set_t<bool>(json_array);
				case basyx::type::valueType::Int:
					return deserialize_helper::set_t<int>(json_array);
				case basyx::type::valueType::String:
					return deserialize_helper::set_t<std::string>(json_array);
				case basyx::type::valueType::Float:
					return deserialize_helper::set_t<double>(json_array);
				};
			}

			return basyx::object::make_null();
		};

        static basyx::object object_list(const json_t& json)
        {
            basyx::object::object_list_t objectList;

            for (const auto& val : json) {
                objectList.push_back(deserialize(val));
            }

            return objectList;
		}

		template<typename T>
		static basyx::object list_t(const json_t & json)
		{
			basyx::object list = basyx::object::make_list<T>();

			for (const auto& val : json) {
				list.insert(deserialize(val));
			}

			return list;
		};

		template<typename T>
		static basyx::object set_t(const json_t & json)
		{
			basyx::object set = basyx::object::make_set<T>();

			for (const auto& val : json) {
				set.insert(deserialize(val));
			}

			return set;
		};

        // Deserializes an objectMap from the given JSON
        static basyx::object::object_map_t objectMap(const json_t& json)
        {
            //auto size = json[basyx::serialization::sizeSpecifier].get<std::size_t>();
            auto size = json.size();

            basyx::object::object_map_t objectMap { size };

            for (const auto& element : json.items()) {
                objectMap.emplace(element.key(), deserialize(element.value()));
            };

            return objectMap;
        }
    };
};
};
};

#endif
