/*
 * IView.h
 *
 *      Author: wendel
 */ 

#ifndef BASYX_METAMODEL_IView_H_
#define BASYX_METAMODEL_IView_H_


#include "submodel/api/qualifier/IHasSemantics.h"
#include "submodel/api/qualifier/IHasDataSpecification.h"
#include "submodel/api/qualifier/IReferable.h"
#include "submodel/api/reference/IReference.h"

#include <vector>

namespace basyx {
namespace aas {

class IView : virtual qualifier::IHasSemantics, qualifier::IHasDataSpecification, qualifier::IReferable
{
public:
	

	virtual ~IView() = default;

	virtual void setContainedElement(std::vector<reference::IReference> references) = 0;
	virtual std::vector<reference::IReference> getContainedElement() const = 0;
};

}
}

#endif
