#include <BaSyx/submodel/map_v2/submodelelement/property/MultiLanguageProperty.h>

using namespace basyx::submodel;
using namespace basyx::submodel::api;
using namespace basyx::submodel::map;

MultiLanguageProperty::MultiLanguageProperty(const std::string & idShort, ModelingKind kind)
	: SubmodelElement(idShort, kind)
{
	this->map.insertKey("value", value.getMap());
	this->map.insertKey("valueId", valueId.getMap());
  this->map.insertKey("kind", ModelingKind_::to_string(kind));
};

api::ILangStringSet & MultiLanguageProperty::getValue()
{
	return this->value;
};

const api::IReference * const MultiLanguageProperty::getValueId() const
{
	return &this->valueId;
};

void MultiLanguageProperty::setValueId(const api::IReference & valueId)
{
	this->valueId = Reference(valueId.getKeys());
};