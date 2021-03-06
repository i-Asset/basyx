#ifndef BASYX_SUBMODEL_ENUM_ModelingKind_H
#define BASYX_SUBMODEL_ENUM_ModelingKind_H

#include <string>

namespace basyx {
namespace submodel {

enum class ModelingKind {
    Template,
    Instance,
};

class ModelingKind_
{
public:
    static ModelingKind from_string(const std::string & name);
    static const char * to_string(ModelingKind value);
};


}
}

#endif
