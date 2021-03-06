/*
 * ConnectedReferenceElement.cpp
 *
 *      Author: wendel
 */

#include <BaSyx/submodel/connected/submodelelement/property/ConnectedReferenceElement.h>

namespace basyx {
namespace submodel {

ConnectedReferenceElement::ConnectedReferenceElement(std::shared_ptr<vab::core::proxy::IVABElementProxy> proxy) :
  ConnectedDataElement(proxy)
{}

void ConnectedReferenceElement::setValue(const IReference & ref)
{
  //todo
  //this->setProxyValue(submodelelement::property::PropertyPaths::VALUE, ref);
}

std::shared_ptr<IReference> ConnectedReferenceElement::getValue() const
{
  //todo 
  return nullptr;// this->getProxyValue(submodelelement::property::PropertyPaths::VALUE);
}

}
}

