package org.eclipse.basyx.testsuite.regression.aas.metamodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.basyx.aas.metamodel.api.IAssetAdministrationShell;
import org.eclipse.basyx.aas.metamodel.map.AssetAdministrationShell;
import org.eclipse.basyx.submodel.metamodel.api.ISubModel;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IIdentifier;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.api.qualifier.IAdministrativeInformation;
import org.eclipse.basyx.submodel.metamodel.api.reference.IKey;
import org.eclipse.basyx.submodel.metamodel.api.reference.IReference;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyElements;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyType;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.dataelement.IProperty;
import org.eclipse.basyx.submodel.metamodel.map.SubModel;
import org.eclipse.basyx.submodel.metamodel.map.identifier.Identifier;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.AdministrativeInformation;
import org.eclipse.basyx.submodel.metamodel.map.reference.Key;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.junit.Test;


/**
 * Test suite for AAS testing. <br />
 * Can be extended to test arbitrary AAS implementations, as long as they
 * implement {@link IAssetAdministrationShell}
 * 
 * 
 * @author schnicke
 *
 */
public abstract class AssetAdministrationShellSuite {
	protected static final Reference EXPECTED_ASSETREF = new Reference(new Key(KeyElements.ASSET, false, "AssetRef", KeyType.CUSTOM));
	protected static final Reference EXPECTED_DERIVEDFROMREF = new Reference(new Key(KeyElements.ASSETADMINISTRATIONSHELL, false, "AASRef", KeyType.CUSTOM));
	protected static final AdministrativeInformation EXPECTED_ADMINISTRATIVEINFORMATION = new AdministrativeInformation("1", "2");

	// String constants used in this test case
	protected static final IIdentifier SMID = new Identifier(IdentifierType.CUSTOM, "smId");
	protected static final IIdentifier AASID = new Identifier(IdentifierType.CUSTOM, "aasId");
	protected static final String SMENDPOINT = "http://endpoint";
	protected static final String SMIDSHORT = "smName";
	protected static final String AASIDSHORT = "aasName";
	protected static final String PROPID = "propId";
	protected static final int PROPVAL = 11;

	/**
	 * Abstract method returning the IAssetAdministrationShell implementation to
	 * test
	 * 
	 * @return
	 */
	protected abstract IAssetAdministrationShell retrieveShell();

	/**
	 * Sets up a baseline AAS that can be used for concrete AAS implementation
	 * initialization
	 * 
	 * @return
	 */
	protected static AssetAdministrationShell retrieveBaselineShell() {
		/*
		 * ! Caution: If the AAS is constructed in any way that is not using the
		 * setters, additional tests for setters are needed. Currently, this is tested
		 * implicitly
		 */
		// Create an AAS containing a reference to the created SubModel
		AssetAdministrationShell aas = new AssetAdministrationShell();
		aas.setIdShort(AASIDSHORT);
		aas.setIdentification(AASID);
		aas.addSubModel(retrieveBaselineSM());
		aas.setAssetReference(EXPECTED_ASSETREF);
		aas.setDerivedFrom(EXPECTED_DERIVEDFROMREF);
		aas.setAdministration(EXPECTED_ADMINISTRATIVEINFORMATION);

		return aas;
	}

	/**
	 * Sets up a baseline SM that can be used for concrete SM implementation
	 * initialization
	 * 
	 * @return
	 */
	protected static SubModel retrieveBaselineSM() {
		/*
		 * ! Caution: If the Submodel is constructed in any way that is not using the
		 * setters, additional tests for setters are needed. Currently, this is tested
		 * implicitly
		 */

		// Create a SubModel containing no operations and one property
		Property p = new Property(PROPVAL);
		p.setIdShort(PROPID);

		SubModel sm = new SubModel();
		sm.addSubModelElement(p);
		sm.setIdShort(SMIDSHORT);
		sm.setIdentification(SMID.getIdType(), SMID.getId());

		return sm;
	}

	/**
	 * Tests retrieving the reference to the Asset described by the AAS
	 */
	@Test
	public void testAssetRef() {
		assertEquals(EXPECTED_ASSETREF, retrieveShell().getAssetReference());
	}

	/**
	 * Tests the getId() function
	 */
	@Test
	public void testGetId() {
		assertEquals(AASIDSHORT, retrieveShell().getIdShort());
	}

	/**
	 * Tests retrieving the contained SubModels
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSubmodel() throws Exception {
		IAssetAdministrationShell shell = retrieveShell();

		// Check if the number of SubModels is as expected
		assertEquals(1, shell.getSubModels().size());

		// Check if the contained SubModel id is as expected
		assertTrue(shell.getSubModels().containsKey(SMIDSHORT));

		// Check if the submodel has been retrieved correctly
		ISubModel sm = shell.getSubModels().get(SMIDSHORT);
		IProperty prop = sm.getProperties().get(PROPID);
		assertEquals(PROPVAL, prop.get());
	}

	/**
	 * Tests retrieving the reference to the AAS the current AAS is derived from
	 */
	@Test
	public void testGetDerivedFrom() {
		IAssetAdministrationShell shell = retrieveShell();
		assertEquals(EXPECTED_DERIVEDFROMREF, shell.getDerivedFrom());
	}

	@Test
	public void testGetAdministrativeInformation() {
		IAssetAdministrationShell shell = retrieveShell();
		IAdministrativeInformation info = shell.getAdministration();
		assertEquals(EXPECTED_ADMINISTRATIVEINFORMATION.getRevision(), info.getRevision());
		assertEquals(EXPECTED_ADMINISTRATIVEINFORMATION.getVersion(), info.getVersion());
	}
	
	@Test
	public void testAddSubmodel() {
		// Create a submodel
		String smId = "newSubmodelId";
		String testId = "smIdTest";
		SubModel subModel = new SubModel(Collections.singletonList(new Property("testProperty")));
		subModel.setIdentification(IdentifierType.CUSTOM, testId);
		subModel.setIdShort(smId);
		
		//Retrieve the aas
		IAssetAdministrationShell shell = retrieveShell();
		shell.addSubModel(subModel);
		
		// Create the expected reference for assertion
		List<IKey> expected1Keys = new ArrayList<>();
		expected1Keys.add(new Key(KeyElements.ASSETADMINISTRATIONSHELL, true, AASID.getId(), AASID.getIdType()));
		expected1Keys.add(new Key(KeyElements.SUBMODEL, true, "smId", IdentifierType.CUSTOM));
		Reference expected1 = new Reference(expected1Keys);

		List<IKey> expected2Keys = new ArrayList<>();
		expected2Keys.add(new Key(KeyElements.ASSETADMINISTRATIONSHELL, true, AASID.getId(), AASID.getIdType()));

		expected2Keys.add(new Key(KeyElements.SUBMODEL, true, testId, IdentifierType.CUSTOM));
		Reference expected2 = new Reference(expected2Keys);

		Collection<IReference> smReferences = shell.getSubmodelReferences();
		assertTrue(smReferences.contains(expected1));
		assertTrue(smReferences.contains(expected2));
		assertEquals(2, smReferences.size());
	} 
}
