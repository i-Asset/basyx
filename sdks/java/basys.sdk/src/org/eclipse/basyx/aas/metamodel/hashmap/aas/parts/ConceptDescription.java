package org.eclipse.basyx.aas.metamodel.hashmap.aas.parts;

import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.basyx.aas.api.metamodel.aas.parts.IConceptDescription;
import org.eclipse.basyx.aas.api.metamodel.aas.reference.IReference;
import org.eclipse.basyx.aas.metamodel.facades.ConceptDescriptionFacade;
import org.eclipse.basyx.aas.metamodel.facades.HasDataSpecificationFacade;
import org.eclipse.basyx.aas.metamodel.facades.IdentifiableFacade;
import org.eclipse.basyx.aas.metamodel.hashmap.aas.identifier.Identifier;
import org.eclipse.basyx.aas.metamodel.hashmap.aas.qualifier.AdministrativeInformation;
import org.eclipse.basyx.aas.metamodel.hashmap.aas.qualifier.HasDataSpecification;
import org.eclipse.basyx.aas.metamodel.hashmap.aas.qualifier.Identifiable;

/**
 * ConceptDescription class as described in DAAS document
 * 
 * @author schnicke
 *
 */
public class ConceptDescription extends HashMap<String, Object> implements IConceptDescription {
	private static final long serialVersionUID = 1L;
	
	public static final String ISCASEOF="isCaseOf";

	public ConceptDescription() {
		// Add qualifiers
		putAll(new HasDataSpecification());
		putAll(new Identifiable());

		// Add attributes
		put(ISCASEOF, new HashSet<String>());
	}

	@Override
	public HashSet<IReference> getDataSpecificationReferences() {
		return new HasDataSpecificationFacade(this).getDataSpecificationReferences();
	}

	@Override
	public void setDataSpecificationReferences(HashSet<IReference> ref) {
		new HasDataSpecificationFacade(this).setDataSpecificationReferences(ref);
		
	}
	
	@Override
	public AdministrativeInformation getAdministration() {
	return new IdentifiableFacade(this).getAdministration();
	}

	@Override
	public Identifier getIdentification() {
		return new IdentifiableFacade(this).getIdentification();
	}

	@Override
	public void setAdministration(String version, String revision) {
		new IdentifiableFacade(this).setAdministration(version, revision);
		
	}

	@Override
	public void setIdentification(String idType, String id) {
		new IdentifiableFacade(this).setIdentification(idType, id);
		
	}

	@Override
	public HashSet<String> getisCaseOf() {
		return new ConceptDescriptionFacade(this).getisCaseOf();
	}

	@Override
	public void setIscaseOf(HashSet<String> ref) {
		new ConceptDescriptionFacade(this).setIscaseOf(ref);
		
	}

}
