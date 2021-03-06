/*
 * Referable.cpp
 *
 *      Author: wendel
 */

#include <BaSyx/submodel/map/qualifier/Referable.h>
#include <BaSyx/submodel/map/reference/Reference.h>

namespace basyx {
namespace submodel {

Referable::Referable()
  : vab::ElementMap{}
{
	this->map.insertKey(Path::IdShort, "");
	this->map.insertKey(Path::Category, "");
	this->map.insertKey(Path::Description, basyx::object::make_map());
	this->map.insertKey(Path::Parent, Reference{}.getMap());
}

basyx::submodel::Referable::Referable(basyx::object & obj)
	: vab::ElementMap{ obj }
{
	this->map.insertKey(Path::IdShort, "");
	this->map.insertKey(Path::Category, "");
	this->map.insertKey(Path::Description, Description{}.getMap());
	this->map.insertKey(Path::Parent, Reference{}.getMap());
}

Referable::Referable(const std::string & idShort, const std::string & category, const Description & description) :
  vab::ElementMap{}
{
	this->map.insertKey(Path::IdShort, idShort, true);
	this->map.insertKey(Path::Category, category, true);
	this->map.insertKey(Path::Description, description.getMap(), true);
  this->map.insertKey(Path::Parent, Reference{}.getMap(), true);
}

Referable::Referable(const IReferable & other) :
  vab::ElementMap{}
{
  this->setIdShort(other.getIdShort());
  this->setCategory(other.getCategory());
  this->setDescription(other.getDescription());
  this->setParent(*other.getParent());
}

std::string Referable::getIdShort() const
{
	return this->map.getProperty(Path::IdShort).GetStringContent();
}

std::string Referable::getCategory() const
{
	return this->map.getProperty(Path::Category).GetStringContent();
}

Description Referable::getDescription() const
{
	return Description{ this->map.getProperty(Path::Description) };
}

std::shared_ptr<IReference> Referable::getParent() const
{
	return std::make_shared<Reference>(this->map.getProperty(Path::Parent));
}

void Referable::setIdShort(const std::string & shortID)
{
	this->map.insertKey(Path::IdShort, shortID, true);
}

void Referable::setCategory(const std::string & category)
{
	this->map.insertKey(Path::Category, category, true);
}

void Referable::setDescription(const Description & description)
{
	this->map.insertKey(Path::Description, description.getMap(), true);
}

void Referable::setParent(const IReference & parentReference)
{
  this->insertMapElement(Path::Parent, Reference{parentReference});
}

}
}
