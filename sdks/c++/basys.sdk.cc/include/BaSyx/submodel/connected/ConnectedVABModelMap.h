/*
 * ConnectedVABModelMap.h
 *
 *      Author: wendel
 */

#ifndef CONNECTEDVABMODELMAP_H_
#define CONNECTEDVABMODELMAP_H_

#include <BaSyx/submodel/connected/ConnectedElement.h>

namespace basyx {
namespace submodel {
namespace backend {

class ConnectedVABModelMap : public ConnectedElement
{
public:
  ConnectedVABModelMap(std::shared_ptr<vab::core::proxy::IVABElementProxy> proxy);
  ~ConnectedVABModelMap() = default;
};

}
}
}

#endif
