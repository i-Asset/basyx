/*
 * ISubModel.h
 *
 *      Author: kuhn, wendel
 */

#ifndef API_ISUBMODEL_H_
#define API_ISUBMODEL_H_


 /* *********************************************************************************
  * Includes
  * *********************************************************************************/

  // StdC++ includes
#include <map>
#include <string>

// BaSyx includes
#include "submodel/api/submodelelement/property/IProperty.h"
#include "submodel/api/qualifier/IHasSemantics.h"
#include "submodel/api/qualifier/IIdentifiable.h"
#include "submodel/api/qualifier/IHasDataSpecification.h"
#include "submodel/api/qualifier/haskind/IHasKind.h"
#include "submodel/api/submodelelement/operation/IOperation.h"
#include "submodel/map/IVABElementContainer.h"
#include "basyx/types.h"


namespace basyx {
namespace aas {

namespace SubmodelPaths {
  static constexpr char  SUBMODELELEMENT[] = "submodelElement";
  static constexpr char  PROPERTIES[] = "dataElements";
  static constexpr char  OPERATIONS[] = "operations";
}


/* *********************************************************************************
 * Sub model interface class
 * *********************************************************************************/
class ISubModel : public qualifier::IHasSemantics, qualifier::IIdentifiable, qualifier::IHasDataSpecification, qualifier::haskind::IHasKind, impl::IVABElementContainer
{

public:
  virtual ~ISubModel() = default;
  virtual void setProperties(const basyx::object::object_map_t & properties) = 0;
  virtual void setOperations(const basyx::object::object_map_t & operations) = 0;

};

}
}



#endif /* API_ISUBMODEL_H_ */