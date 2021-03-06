/*
 * IKey.h
 *
 *      Author: wendel
 */ 

#ifndef BASYX_METAMODEL_IKey_H_
#define BASYX_METAMODEL_IKey_H_


#include <string>

namespace basyx {
namespace submodel {

class IKey
{
public:
  struct Path
  {
    static constexpr char Type[] = "type";
    static constexpr char Local[] = "local";
    static constexpr char Value[] = "value";
    static constexpr char IdType[] = "idType";
  };
public:
	virtual ~IKey() = 0;

	virtual std::string getType() const = 0;
	virtual bool isLocal() const = 0;
	virtual std::string getValue() const = 0;
	virtual std::string getidType() const = 0;
};

inline IKey::~IKey() = default;

}
}

#endif
