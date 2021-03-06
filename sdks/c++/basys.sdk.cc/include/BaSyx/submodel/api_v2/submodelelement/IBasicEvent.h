#ifndef BASYX_API_V2_SDK_IBASICEVENT_H
#define BASYX_API_V2_SDK_IBASICEVENT_H

#include <BaSyx/submodel/api_v2/submodelelement/IBasicEvent.h>

namespace basyx {
namespace submodel {
namespace api {

/**
 * Mandatory members: observed
 */
class IBasicEvent : public api::IIBasicEvent
{
public:
  ~IBasicEvent() = 0;

  virtual IReferable & getObserved() const = 0;
};

}
}
}
#endif //BASYX_API_V2_SDK_IBASICEVENT_H
