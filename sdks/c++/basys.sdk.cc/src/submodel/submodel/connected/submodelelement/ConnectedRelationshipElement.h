/*
 * ConnectedRelationshipElement.h
 *
 *      Author: wendel
 */

#ifndef AAS_BACKEND_SUBMODELELEMENT_CONNECTEDRELATIONSHIPELEMENT_H_
#define AAS_BACKEND_SUBMODELELEMENT_CONNECTEDRELATIONSHIPELEMENT_H_

#include "basyx/types.h"

#include "vab/core/proxy/IVABElementProxy.h"

#include "submodel/api/submodelelement/ISubmodelElement.h"
#include "submodel/api/submodelelement/IRelationshipElement.h"
#include "submodel/connected/submodelelement/ConnectedSubmodelElement.h"

#include <string>

namespace basyx {
namespace submodel {

class ConnectedRelationshipElement : public ConnectedSubmodelElement, submodelelement::IRelationshipElement
{
public:
  ConnectedRelationshipElement(std::shared_ptr<vab::core::proxy::IVABElementProxy> proxy);
  ~ConnectedRelationshipElement() = default;
  
  // Inherited via IRelationshipElement
  virtual void setFirst(const std::shared_ptr<IReference> & first) override;
  virtual std::shared_ptr<IReference> getFirst() const override;
  virtual void setSecond(const std::shared_ptr<IReference> & second) override;
  virtual std::shared_ptr<IReference> getSecond() const override;
};


}
}

#endif
