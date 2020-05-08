#ifndef BASYX_SUBMODEL_MAP_V2_SUBMODELELEMENT_PROPERTY_REFERENCEELEMENT_H
#define BASYX_SUBMODEL_MAP_V2_SUBMODELELEMENT_PROPERTY_REFERENCEELEMENT_H

#include <BaSyx/submodel/api_v2/submodelelement/property/IReferenceElement.h>
#include <BaSyx/submodel/map_v2/submodelelement/SubmodelElement.h>
#include <BaSyx/submodel/map_v2/reference/Reference.h>

namespace basyx {
namespace submodel {
namespace map {


class ReferenceElement : 
	public api::IReferenceElement,
	public SubmodelElement
{
private:
	Reference value;
public:
	ReferenceElement(const std::string & idShort, Kind kind = Kind::Instance);
	virtual ~ReferenceElement() = default;

	virtual const api::IReference * const getValue() const = 0;
	virtual void setValue(const api::IReference & value) = 0;
};

}
}
}

#endif /* BASYX_SUBMODEL_MAP_V2_SUBMODELELEMENT_PROPERTY_REFERENCEELEMENT_H */