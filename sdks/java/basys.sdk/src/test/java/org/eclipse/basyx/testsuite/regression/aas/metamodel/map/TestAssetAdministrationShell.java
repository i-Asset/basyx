package org.eclipse.basyx.testsuite.regression.aas.metamodel.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.basyx.aas.metamodel.api.IAssetAdministrationShell;
import org.eclipse.basyx.aas.metamodel.api.parts.IConceptDictionary;
import org.eclipse.basyx.aas.metamodel.map.AssetAdministrationShell;
import org.eclipse.basyx.aas.metamodel.map.parts.ConceptDictionary;
import org.eclipse.basyx.aas.metamodel.map.security.Security;
import org.eclipse.basyx.submodel.metamodel.api.dataspecification.IEmbeddedDataSpecification;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.api.reference.IKey;
import org.eclipse.basyx.submodel.metamodel.api.reference.IReference;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyElements;
import org.eclipse.basyx.submodel.metamodel.map.SubModel;
import org.eclipse.basyx.submodel.metamodel.map.dataspecification.EmbeddedDataSpecification;
import org.eclipse.basyx.submodel.metamodel.map.parts.ConceptDescription;
import org.eclipse.basyx.submodel.metamodel.map.reference.Key;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.eclipse.basyx.testsuite.regression.aas.metamodel.AssetAdministrationShellSuite;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the map implementation of {@link IAssetAdministrationShell} based on
 * the AAS test suite. <br />
 * Additionally to the test suite, the setters of the map implementation are
 * tested
 * 
 * @author schnicke
 *
 */
public class TestAssetAdministrationShell extends AssetAdministrationShellSuite {
	private static final Reference REFERENCE = new Reference(new Key(KeyElements.ASSET, true, "testValue", IdentifierType.IRI));
	
	private AssetAdministrationShell shell;

	/**
	 * Ensures that each test case is working on a fresh copy of the AAS
	 */
	@Before
	public void buildShell() {
		shell = retrieveBaselineShell();
	}

	@Override
	protected AssetAdministrationShell retrieveShell() {
		return shell;
	}

	@Override
	public void testGetSubmodel() throws Exception {
		// Overwritten because getting submodels on local AAS is not supported
	}
	
	@Test
	public void testSetEndpoint() {
		String endpoint = "testEndpoint.com";
		String endpointType = "http";
		shell.setEndpoint(endpoint, endpointType);
		List<HashMap<String, String>> endPoints = shell.getEndpoints();
		HashMap<String, String> map = endPoints.iterator().next();
		assertTrue(map.containsValue(endpoint));
		assertTrue(map.containsValue(endpointType));
	}
	
	@Test
	public void testSetDataSpecificationReferences() {
		Collection<IReference> refs = Collections.singleton(REFERENCE);
		shell.setDataSpecificationReferences(refs);
		assertEquals(refs, shell.getDataSpecificationReferences());
	}
	
	@Test
	public void testSetEmbeddedDataSpecifications() {
		EmbeddedDataSpecification embeddedDataSpecification = new EmbeddedDataSpecification();
		Collection<IEmbeddedDataSpecification> specifications = Collections.singleton(embeddedDataSpecification);
		shell.setEmbeddedDataSpecifications(specifications);
		assertEquals(specifications, shell.getEmbeddedDataSpecifications());
	}
	
	@Test
	public void testSecurity() {
		Security security = new Security();
		shell.setSecurity(security);
		assertEquals(security, shell.getSecurity());
	} 
	
	@Test
	public void testSetParent() {
		shell.setParent(REFERENCE);
		assertEquals(REFERENCE, shell.getParent());
	}
	
	@Test
	public void testAddConceptDescription() {
		IdentifierType idType = IdentifierType.IRI;
		String id = "testId";
		ConceptDescription description = new ConceptDescription();
		description.setIdentification(idType, id);
		description.setCategory("testCategory");
		shell.addConceptDescription(description);
		Collection<IConceptDictionary> dictionaries = new HashSet<IConceptDictionary>();
		ConceptDictionary dictionary = new ConceptDictionary();
		dictionary.addConceptDescription(description);
		dictionaries.add(dictionary);
		assertEquals(dictionaries, shell.getConceptDictionary());
	}
	
	@Test
	public void testSetSubmodels() {
		// Create submodels
		SubModel subModel1 = new SubModel(Collections.singletonList(new Property("testProperty1")));
		subModel1.setIdShort("newSubmodelId1");
		subModel1.setIdentification(IdentifierType.CUSTOM, "smId1");
		SubModel subModel2 = new SubModel(Collections.singletonList(new Property("testProperty2")));
		subModel2.setIdShort("newSubmodelId2");
		subModel2.setIdentification(IdentifierType.CUSTOM, "smId2");
		
		// create a collection of descriptors and add the above descriptors
		Collection<SubModel> submodels = new ArrayList<SubModel>();
		submodels.add(subModel1);
		submodels.add(subModel2);

		shell.setSubModels(submodels);

		// expect references to be set according to the descriptors
		Collection<IReference> smReferences = shell.getSubmodelReferences();
		List<IKey> expected1Keys = new ArrayList<>();
		expected1Keys.add(new Key(KeyElements.ASSETADMINISTRATIONSHELL, true, AASID.getId(), AASID.getIdType()));
		expected1Keys.add(new Key(KeyElements.SUBMODEL, true, "smId1", IdentifierType.CUSTOM));
		Reference expected1 = new Reference(expected1Keys);

		List<IKey> expected2Keys = new ArrayList<>();
		expected2Keys.add(new Key(KeyElements.ASSETADMINISTRATIONSHELL, true, AASID.getId(), AASID.getIdType()));
		expected2Keys.add(new Key(KeyElements.SUBMODEL, true, "smId1", IdentifierType.CUSTOM));
		Reference expected2 = new Reference(expected2Keys);
		assertTrue(smReferences.contains(expected1));
		assertTrue(smReferences.contains(expected2));
		assertEquals(2, smReferences.size());
	} 
}
