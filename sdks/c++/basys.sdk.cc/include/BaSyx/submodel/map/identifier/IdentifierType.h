/*
 * IdentifierType.h
 *
 *      Author: wendel
 */

#ifndef AAS_IMPL_METAMODEL_IDENTIFIERTYPE_H_
#define AAS_IMPL_METAMODEL_IDENTIFIERTYPE_H_

namespace basyx {
namespace submodel {

struct IdentifierType 
{
	  static constexpr char IRDI[] = "IRDI";
	  static constexpr char URI[] = "URI";
	  static constexpr char Custom[] = "Custom";
};

}
}

#endif
