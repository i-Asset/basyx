package org.eclipse.basyx.submodel.metamodel.map.submodelelement.operation;

import java.util.Set;

import org.eclipse.basyx.submodel.metamodel.api.qualifier.qualifiable.IConstraint;
import org.eclipse.basyx.submodel.metamodel.api.reference.IReference;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.ISubmodelElement;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.operation.IOperationVariable;
import org.eclipse.basyx.submodel.metamodel.facade.qualifier.HasDataSpecificationFacade;
import org.eclipse.basyx.submodel.metamodel.facade.qualifier.HasSemanticsFacade;
import org.eclipse.basyx.submodel.metamodel.facade.qualifier.ReferableFacade;
import org.eclipse.basyx.submodel.metamodel.facade.qualifier.haskind.HasKindFacade;
import org.eclipse.basyx.submodel.metamodel.facade.qualifier.qualifiable.QualifiableFacade;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.SubmodelElement;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.property.SingleProperty;

/**
 * OperationVariable as described by DAAS document An operation variable is a
 * submodel element that is used as input or output variable of an operation.
 * 
 * @author schnicke
 *
 */
public class OperationVariable extends SubmodelElement implements IOperationVariable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param value
	 *            Describes the needed argument for an operation via a submodel
	 *            element of kind=Type
	 */
	public OperationVariable(SubmodelElement value) {
		put(SingleProperty.VALUE, value);
	}

	public OperationVariable() {

	}

	public void setValue(ISubmodelElement value) {
		put(SingleProperty.VALUE, value);
	}

	@Override
	public ISubmodelElement getValue() {
		return (ISubmodelElement) get(SingleProperty.VALUE);
	}

	@Override
	public Set<IReference> getDataSpecificationReferences() {
		return new HasDataSpecificationFacade(this).getDataSpecificationReferences();
	}

	@Override
	public void setDataSpecificationReferences(Set<IReference> ref) {
		new HasDataSpecificationFacade(this).setDataSpecificationReferences(ref);
	}

	@Override
	public String getIdShort() {
		return new ReferableFacade(this).getIdShort();
	}

	@Override
	public String getCategory() {
		return new ReferableFacade(this).getCategory();
	}

	@Override
	public String getDescription() {
		return new ReferableFacade(this).getDescription();
	}

	@Override
	public IReference getParent() {
		return new ReferableFacade(this).getParent();
	}

	@Override
	public void setIdShort(String idShort) {
		new ReferableFacade(this).setIdShort(idShort);
	}

	@Override
	public void setCategory(String category) {
		new ReferableFacade(this).setCategory(category);
	}

	@Override
	public void setDescription(String description) {
		new ReferableFacade(this).setDescription(description);
	}

	@Override
	public void setParent(IReference obj) {
		new ReferableFacade(this).setParent(obj);
	}

	@Override
	public void setQualifier(Set<IConstraint> qualifiers) {
		new QualifiableFacade(this).setQualifier(qualifiers);
	}

	@Override
	public Set<IConstraint> getQualifier() {
		return new QualifiableFacade(this).getQualifier();
	}

	@Override
	public IReference getSemanticId() {
		return new HasSemanticsFacade(this).getSemanticId();
	}

	@Override
	public void setSemanticID(IReference ref) {
		new HasSemanticsFacade(this).setSemanticID(ref);
	}

	@Override
	public String getHasKindReference() {
		return new HasKindFacade(this).getHasKindReference();
	}

	@Override
	public void setHasKindReference(String kind) {
		new HasKindFacade(this).setHasKindReference(kind);
	}

}