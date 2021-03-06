/*
 * ConnectedMapProperty.h
 *
 *      Author: wendel
 */

#ifndef AAS_BACKEND_SUBMODELELEMENT_PROPERTY_CONNECTEDMAPPROPERTY_H_
#define AAS_BACKEND_SUBMODELELEMENT_PROPERTY_CONNECTEDMAPPROPERTY_H_

#include <BaSyx/submodel/connected/submodelelement/property/ConnectedProperty.h>
#include <BaSyx/submodel/api/submodelelement/property/IMapProperty.h>

namespace basyx { 
namespace submodel {

class ConnectedMapProperty : public submodelelement::property::IMapProperty, public ConnectedProperty
{
public:
  ConnectedMapProperty(std::shared_ptr<vab::core::proxy::IVABElementProxy> proxy);
	~ConnectedMapProperty() = default;

  // Inherited via IMapProperty
  virtual basyx::object getValue(const std::string & key) const override;
  virtual void put(const std::string & key, const basyx::object & value) const override;
  virtual void set(const basyx::object::object_map_t & map) const override;
  virtual basyx::object::object_list_t getKeys() const override;
  virtual int getEntryCount() const override;
  virtual void remove(const std::string & key) const override;

private:
  basyx::object::object_map_t getMap() const;
};
 
}
}

#endif
