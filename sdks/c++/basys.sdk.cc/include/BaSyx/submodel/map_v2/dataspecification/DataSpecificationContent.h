#ifndef BASYX_MAP_V2_SDK_DATASPECIFICATIONCONTENT_H
#define BASYX_MAP_V2_SDK_DATASPECIFICATIONCONTENT_H

#include <BaSyx/submodel/api_v2/dataspecification/IDataSpecificationContent.h>

#include <BaSyx/vab/ElementMap.h>

namespace basyx {
namespace submodel {
namespace map {

class DataSpecificationContent
    : public api::IDataSpecificationContent
    , public virtual vab::ElementMap
{};

}
}
}
#endif //BASYX_MAP_V2_SDK_DATASPECIFICATIONCONTENT_H
