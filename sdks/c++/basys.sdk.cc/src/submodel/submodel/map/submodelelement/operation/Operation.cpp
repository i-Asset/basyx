/*
 * Operation.cpp
 *
 *      Author: wendel
 */

#include <BaSyx/submodel/map/submodelelement/operation/Operation.h>
#include <BaSyx/shared/object/obj_function.h>

namespace basyx {
namespace submodel {

Operation::Operation()
  : vab::ElementMap{}
  , ModelType{Path::ModelType}
  , SubmodelElement{}
{
  this->map.insertKey(Path::Input, basyx::object::make_list<basyx::object>());
  this->map.insertKey(Path::Invokable, basyx::object::make_null());
  this->map.insertKey(Path::Output, basyx::object::make_null());
}

Operation::Operation(const IOperation & other)
  : vab::ElementMap{}
  , ModelType{Path::ModelType}
  , SubmodelElement{other}
{
  this->setParameterTypes(other.getParameterTypes());
  this->setReturnTypes(other.getReturnType());
  this->setInvocable(other.getInvocable());
}

Operation::Operation(const basyx::object & obj) :
  vab::ElementMap{obj}
  , SubmodelElement{}
{}

basyx::specificCollection_t<IOperationVariable> Operation::getParameterTypes() const
{
	return basyx::specificCollection_t<IOperationVariable>();
}

std::shared_ptr<IOperationVariable> Operation::getReturnType() const
{
	return std::shared_ptr<IOperationVariable>();
}

basyx::object Operation::getInvocable() const
{
	return this->map.getProperty(Path::Invokable);
}

void Operation::setParameterTypes(const basyx::specificCollection_t<IOperationVariable>& parameterTypes)
{
}

void Operation::setReturnTypes(const std::shared_ptr<IOperationVariable>& returnTypes)
{
}

void Operation::setInvocable(basyx::object invocable)
{
	this->map.insertKey(Path::Invokable, invocable, true);
}

basyx::object Operation::invoke(basyx::object & parameters) const
{
	return basyx::object();
}



}
}
