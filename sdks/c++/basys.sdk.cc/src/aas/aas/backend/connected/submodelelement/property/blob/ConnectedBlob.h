/*
 * ConnectedBlob.h
 *
 *      Author: wendel
 */

#ifndef AAS_BACKEND_SUBMODELELEMENT_PROPERTY_CONNECTEDBLOB_H_
#define AAS_BACKEND_SUBMODELELEMENT_PROPERTY_CONNECTEDBLOB_H_

#include "aas/submodelelement/property/blob/IBlob.h"
#include "aas/backend/connected/submodelelement/ConnectedDataElement.h"
#include "aas/BlobType.h"
#include "aas/MimeType.h"

namespace basyx {
namespace aas {
namespace backend {
namespace connected {

class ConnectedBlob : public submodelelement::IBlob, ConnectedDataElement
{
public:
  ConnectedBlob(std::shared_ptr<vab::core::proxy::IVABElementProxy> proxy);
  ~ConnectedBlob() = default;

  // Inherited via IBlob
  virtual void setValue(const basyx::any & value) override;

  virtual basyx::any getValue() const override;

  virtual void setMimeType(const std::string & mimeType) override;

  virtual std::string getMimeType() const override;

};

}
}
}
}

#endif
