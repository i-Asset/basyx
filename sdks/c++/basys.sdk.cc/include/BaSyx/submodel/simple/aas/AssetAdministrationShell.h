#ifndef BASYX_SUBMODEL_SIMPLE_AAS_ASSETADMINISTRATIONSHELL_H
#define BASYX_SUBMODEL_SIMPLE_AAS_ASSETADMINISTRATIONSHELL_H

#include <BaSyx/submodel/api_v2/aas/IAssetAdministrationShell.h>
#include <BaSyx/submodel/api_v2/parts/IConceptDescription.h>

#include <BaSyx/submodel/simple/aas/Asset.h>
#include <BaSyx/submodel/simple/reference/Reference.h>


#include <BaSyx/submodel/simple/common/ElementContainer.h>

namespace basyx {
namespace submodel {
namespace simple { 

class AssetAdministrationShell : public api::IAssetAdministrationShell
{
private:
	Identifiable identifiable;
	HasDataSpecification dataSpecification;

	Reference derivedFrom;
	Asset asset;
	ElementContainer<api::IConceptDescription> conceptDictionary;
	ElementContainer<api::ISubModel> submodels;
public:
	virtual ~AssetAdministrationShell() = default;
public:
	AssetAdministrationShell(const std::string & idShort, const Identifier & identifier, const Asset & asset);

	ElementContainer<api::IConceptDescription> & getConceptDictionary();

	// Inherited via IAssetAdministrationShell
	virtual api::IAsset & getAsset() override;
	virtual Reference * getDerivedFrom() override;
	virtual void setDerivedFrom(const api::IReference & reference) override;
	virtual SubmodelContainer_t & getSubmodels() override;

	// Inherited via IReferable
	virtual const std::string & getIdShort() const override;
	virtual const std::string * const getCategory() const override;
	virtual void setCategory(const std::string & category) override;
	virtual simple::LangStringSet & getDescription() override;
	virtual const simple::LangStringSet & getDescription() const override;
	virtual const IReferable * const getParent() const override;

	// Inherited via IIdentifiable
	virtual const AdministrativeInformation & getAdministrativeInformation() const override;
	virtual AdministrativeInformation & getAdministrativeInformation() override;

	virtual Identifier getIdentification() const override;

	virtual bool hasAdministrativeInformation() const;

	// Inherited via IHasDataSpecification
	virtual void addDataSpecification(const Reference & reference) override;
	virtual const std::vector<Reference> getDataSpecificationReference() const override;
};

}
}
}


#endif /* BASYX_SUBMODEL_SIMPLE_AAS_ASSETADMINISTRATIONSHELL_H */
