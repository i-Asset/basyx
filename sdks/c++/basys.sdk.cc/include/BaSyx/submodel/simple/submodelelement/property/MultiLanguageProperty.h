#ifndef BASYX_SUBMODEL_SIMPLE_SUBMODELELEMENT_PROPERTY_MULTILANGUAGEPROPERTY_H
#define BASYX_SUBMODEL_SIMPLE_SUBMODELELEMENT_PROPERTY_MULTILANGUAGEPROPERTY_H

#include <BaSyx/submodel/api_v2/submodelelement/property/IMultiLanguageProperty.h>
#include <BaSyx/submodel/simple/submodelelement/SubmodelElement.h>
#include <BaSyx/submodel/simple/reference/Reference.h>
#include <BaSyx/submodel/simple/common/LangStringSet.h>

namespace basyx {
namespace submodel {
namespace simple {


class MultiLanguageProperty : 
	public api::IMultiLanguageProperty,
	public SubmodelElement
{
private:
	LangStringSet value;
	Reference valueId;
public:
	MultiLanguageProperty(const std::string & idShort, ModelingKind kind = ModelingKind::Instance);
	virtual ~MultiLanguageProperty() = default;

	virtual api::ILangStringSet & getValue() override;

	virtual const api::IReference * const getValueId() const override;
	virtual void setValueId(const api::IReference & valueId) override;
};


}
}
}

#endif /* BASYX_SUBMODEL_SIMPLE_SUBMODELELEMENT_PROPERTY_MULTILANGUAGEPROPERTY_H */
