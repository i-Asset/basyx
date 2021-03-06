/**
 * 
 */
package org.eclipse.basyx.testsuite.regression.aas.restapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Map;

import org.eclipse.basyx.aas.metamodel.connected.ConnectedAssetAdministrationShell;
import org.eclipse.basyx.aas.metamodel.map.AssetAdministrationShell;
import org.eclipse.basyx.aas.metamodel.map.descriptor.ModelUrn;
import org.eclipse.basyx.aas.restapi.AASModelProvider;
import org.eclipse.basyx.aas.restapi.VABMultiSubmodelProvider;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.api.reference.IReference;
import org.eclipse.basyx.submodel.metamodel.map.SubModel;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.eclipse.basyx.submodel.restapi.SubModelProvider;
import org.eclipse.basyx.submodel.restapi.SubmodelElementProvider;
import org.eclipse.basyx.testsuite.regression.submodel.restapi.SimpleAASSubmodel;
import org.eclipse.basyx.testsuite.regression.vab.manager.VABConnectionManagerStub;
import org.eclipse.basyx.vab.exception.provider.ProviderException;
import org.eclipse.basyx.vab.modelprovider.VABElementProxy;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the capability to multiplex of a VABMultiSubmodelProvider
 * 
 * @author schnicke, kuhn
 *
 */
public class MultiSubmodelProviderTest {
	private VABElementProxy proxy;

	// Used short ids
	private static final String AASIDSHORT = "StubAAS";

	// Used URNs
	private static final ModelUrn AASURN = new ModelUrn("urn:fhg:es.iese:aas:1:1:myAAS#001");

	@Before
	public void build() {
		VABConnectionManagerStub stub = new VABConnectionManagerStub();
		String urn = "urn:fhg:es.iese:aas:1:1:submodel";
		VABMultiSubmodelProvider provider = new VABMultiSubmodelProvider();
		// set dummy aas
		AssetAdministrationShell aas = new AssetAdministrationShell();
		aas.setIdShort(AASIDSHORT);
		aas.setIdentification(AASURN);
		provider.setAssetAdministrationShell(new AASModelProvider(aas));
		provider.addSubmodel("SimpleAASSubmodel", new SubModelProvider(new SimpleAASSubmodel()));
		stub.addProvider(urn, "", provider);
		proxy = stub.connectToVABElement(urn);
	}

	/**
	 * Run JUnit test case
	 */
	@Test
	public void invokeExceptionTest() {
		// Invoke operationEx1
		try {
			proxy.invokeOperation("/aas/submodels/SimpleAASSubmodel/operations/exception1/invokable");
			fail();
		} catch (ProviderException e) {}

		// Invoke operationEx2
		try {
			proxy.invokeOperation("/aas/submodels/SimpleAASSubmodel/operations/exception2/invokable", "prop1");
			fail();
		} catch (ProviderException e) {}
	}

	@Test
	public void invokeTest() {
		// Invoke operation
		assertEquals(7, proxy.invokeOperation("/aas/submodels/SimpleAASSubmodel/operations/complex", 10, 3));
		assertEquals(true, proxy.invokeOperation("/aas/submodels/SimpleAASSubmodel/operations/simple"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getTest() {
		AssetAdministrationShell aas = AssetAdministrationShell.createAsFacade((Map<String, Object>) proxy.getModelPropertyValue("/aas"));
		assertEquals(AASIDSHORT, aas.getIdShort());

		getTestRunner("SimpleAASSubmodel");
	}

	@Test
	public void createDeleteSubmodelTest() {
		SubModel sm = new SimpleAASSubmodel("TestSM");
		sm.setIdentification(IdentifierType.CUSTOM, "TestId");
		proxy.createValue("/aas/submodels", sm);

		getTestRunner("TestSM");

		// Ensure that the Submodel References where updated
		ConnectedAssetAdministrationShell shell = new ConnectedAssetAdministrationShell(proxy.getDeepProxy("/aas"), null);
		Collection<IReference> refs = shell.getSubmodelReferences();
		assertEquals(2, refs.size());
		assertEquals(sm.getReference(), refs.iterator().next());

		proxy.deleteValue("/aas/submodels/TestSM");

		// Ensure that the Submodel Reference was removed again
		assertEquals(1, shell.getSubmodelReferences().size());
		assertFalse(shell.getSubmodelReferences().contains(sm.getReference()));

		try {
			proxy.getModelPropertyValue("/aas/submodels/TestSM");
			fail();
		} catch (ProviderException e) {
			// Expected
		}
	}

	@SuppressWarnings("unchecked")
	void getTestRunner(String smId) {
		// Get property value
		Map<String, Object> value = (Map<String, Object>) proxy
				.getModelPropertyValue("/aas/submodels/" + smId + "/" + SubmodelElementProvider.PROPERTIES + "/integerProperty/value");
		assertEquals(123, value.get(Property.VALUE));

		// Get property value with /submodel suffix
		value = (Map<String, Object>) proxy.getModelPropertyValue("/aas/submodels/" + smId + "/submodel/" + SubmodelElementProvider.PROPERTIES + "/integerProperty/value");
		assertEquals(123, value.get(Property.VALUE));
	}
}
