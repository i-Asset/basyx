/*
 * IDataSpecification.h
 *
 *      Author: wendel
 */

#ifndef BASYX_METAMODEL_IDATASPECIFICATION_H_
#define BASYX_METAMODEL_IDATASPECIFICATION_H_

#include "aas/reference/IReference.h"

#include "BaSyx/types.h"

#include <string>
namespace basyx {
namespace aas {
namespace backend {
namespace connected {
namespace dataspecification {

namespace PathSpecification
{
long serialVersionUID = 1L;
std::string PREFERREDNAME = "preferredName";
std::string SHORTNAME = "shortName";
std::string UNIT = "unit";
std::string UNITID = "unitId";
std::string SOURCEOFDEFINITION = "sourceOfDefinition";
std::string SYMBOL = "symbol";
std::string DATATYPE = "dataType";
std::string DEFINITION = "definition";
std::string VALUEFORMAT = "valueFormat";
std::string VALUELIST = "valueList";
std::string CODE = "code";
}

class IDataSpecification
{
public:
  virtual ~IDataSpecification() = default;

  virtual std::string getPreferredName() const = 0;
  virtual std::string getShortName() const = 0;
  virtual std::string getUnit() const = 0;
  //virtual IReference getUnitId() const = 0;
  virtual std::string getSourceOfDefinition() const = 0;
  virtual std::string getSymbol() const = 0;
  virtual std::string getDataType() const = 0;
  virtual std::string getDefinition() const = 0;
  virtual std::string getValueFormat() const = 0;
  virtual std::string getValueList() const = 0;
  virtual std::string getCode() const = 0;

  virtual void setPreferredName(const std::string & preferredName) = 0;
  virtual void setShortName(const std::string & shortName) = 0;
  virtual void setUnit(const std::string & uni) = 0;
  virtual void setUnitId(const IReference & unitId) = 0;
  virtual void setSourceOfDefinition(const std::string & sourceOfDefinition) = 0;
  virtual void setSymbol(const std::string & symbol) = 0;
  virtual void setDataType(const std::string & dataType) = 0;
  virtual void setDefinition(const std::string & definition) = 0;
  virtual void setValueFormat(const std::string & valueFormat) = 0;
  virtual void setValueList(const basyx::object & obj) = 0;
  virtual void setCode(const basyx::object & obj) = 0;
};

#endif
}
}
}
}
}
