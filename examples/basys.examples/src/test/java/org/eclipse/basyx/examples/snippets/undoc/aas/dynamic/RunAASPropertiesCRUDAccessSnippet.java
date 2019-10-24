package org.eclipse.basyx.examples.snippets.undoc.aas.dynamic;

import static org.junit.Assert.assertTrue;

import org.eclipse.basyx.components.servlet.submodel.VABLambdaServlet;
import org.eclipse.basyx.examples.contexts.BaSyxExamplesContext_1MemoryAASServer_1SQLDirectory;
import org.eclipse.basyx.examples.deployment.BaSyxDeployment;
import org.eclipse.basyx.examples.support.directory.ExamplesPreconfiguredDirectory;
import org.eclipse.basyx.submodel.metamodel.map.SubModel;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.property.SingleProperty;
import org.eclipse.basyx.vab.manager.VABConnectionManager;
import org.eclipse.basyx.vab.modelprovider.VABElementProxy;
import org.eclipse.basyx.vab.protocol.http.connector.HTTPConnectorProvider;
import org.junit.ClassRule;
import org.junit.Test;



/**
 * Illustrate the use of AAS operations
 * 
 * @author kuhn
 *
 */
public class RunAASPropertiesCRUDAccessSnippet {

	
	/**
	 * VAB connection manager backend
	 */
	protected VABConnectionManager connManager = new VABConnectionManager(
			new ExamplesPreconfiguredDirectory()
				// Add example specific mappings
			    .addMapping("urn:de.FHG:devices.es.iese:statusSM:1.0:3:x-509#003",  "http://localhost:8080/basys.examples/Testsuite/components/BaSys/1.0/devicestatusVAB/"),
			new HTTPConnectorProvider());

	
	/**
	 * Instantiate and start context elements for this example. BaSyxDeployment contexts instantiate all
	 * components on the IP address of the host. Therefore, all components use the same IP address. 
	 */
	@ClassRule
	public static BaSyxDeployment context = new BaSyxDeployment(
				// Simulated servlets
				// - BaSys topology with one AAS Server and one SQL directory
				new BaSyxExamplesContext_1MemoryAASServer_1SQLDirectory().
					// Deploy example specific servlets to Tomcat server in this context
					addServletMapping("/Testsuite/components/BaSys/1.0/devicestatusVAB/*", new VABLambdaServlet())
			);

	
	
	
	/**
	 * Test basic queries
	 */
	@Test
	public void snippet() throws Exception {

		// Server connections
		// - Connect to device (VAB object)
		VABElementProxy connSubModel1 = this.connManager.connectToVABElement("urn:de.FHG:devices.es.iese:statusSM:1.0:3:x-509#003");

		
		// Create properties on AAS
		// - Add example properties
		SubModel submodel = new SubModel();
		submodel.setIdShort("urn:de.FHG:devices.es.iese:statusSM:1.0:3:x-509#003");
		SingleProperty prop1 = new SingleProperty(7);
		prop1.setIdShort("prop1");
		submodel.addSubModelElement(prop1);

		SingleProperty prop2 = new SingleProperty("myStr");
		prop2.setIdShort("prop2");
		submodel.addSubModelElement(prop2);
		// - Transfer sub model to server
		connSubModel1.setModelPropertyValue("/", submodel);
		
		// Read property values
		int prop1Val = (int) connSubModel1.getModelPropertyValue("dataElements/prop1/value");
		String prop2Val = (String) connSubModel1.getModelPropertyValue("dataElements/prop2/value");
		// - Check property values
		assertTrue(prop1Val == 7);
		assertTrue(prop2Val.equals("myStr"));
		
		// Update property values
		connSubModel1.setModelPropertyValue("dataElements/prop1/value", 8);
		connSubModel1.setModelPropertyValue("dataElements/prop2/value", "stillMine");
		
		// Read property values again
		prop1Val = (int) connSubModel1.getModelPropertyValue("dataElements/prop1/value");
		prop2Val = (String) connSubModel1.getModelPropertyValue("dataElements/prop2/value");
		// - Check property values
		assertTrue(prop1Val == 8);
		assertTrue(prop2Val.equals("stillMine"));

		// Delete property values
		connSubModel1.deleteValue("dataElements/prop1");
		connSubModel1.deleteValue("dataElements/prop2");
		
		// Read property values again
		assertTrue(connSubModel1.getModelPropertyValue("dataElements/prop1") == null);
		assertTrue(connSubModel1.getModelPropertyValue("dataElements/prop2") == null);
	}
}

