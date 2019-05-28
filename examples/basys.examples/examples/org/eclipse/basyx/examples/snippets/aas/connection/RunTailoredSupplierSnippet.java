package org.eclipse.basyx.examples.snippets.aas.connection;

import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.Map;
import java.util.function.Supplier;

import org.eclipse.basyx.aas.backend.connector.http.HTTPConnectorProvider;
import org.eclipse.basyx.examples.contexts.BaSyxExamplesContext_1MemoryAASServer_1SQLDirectory;
import org.eclipse.basyx.examples.deployment.BaSyxDeployment;
import org.eclipse.basyx.examples.support.directory.ExamplesPreconfiguredDirectory;
import org.eclipse.basyx.examples.support.servlets.EmptyVABLambdaElementServlet;
import org.eclipse.basyx.vab.core.VABConnectionManager;
import org.eclipse.basyx.vab.core.proxy.VABElementProxy;
import org.eclipse.basyx.vab.provider.lambda.VABLambdaProviderHelper;
import org.junit.ClassRule;
import org.junit.Test;



/**
 * Example for a tailored BaSyx supplier
 * 
 * - BaSyx will serialize this class (and all contained references) and transmit it to the AAS server
 * 
 * @author kuhn
 *
 */
class TailoredBaSyxSupplier implements Supplier<Object>, Serializable {

	/**
	 * Version number of serialized instances
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Return value
	 */
	@Override
	public Object get() {
		// Delegate call to tailored BaSyx supplier base class
		return getInternal();
	}

	
	/**
	 * Example function of tailored BaSyx supplier base class
	 */
	protected String getInternal() {
		return "BaSyxSupplier!";
	}
}




/**
 * Illustrate the dynamic deployment of AAS operations with a tailored consumer
 * 
 * @author kuhn
 *
 */
public class RunTailoredSupplierSnippet {

	
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
					addServletMapping("/Testsuite/components/BaSys/1.0/devicestatusVAB/*", new EmptyVABLambdaElementServlet())
			);

		
	
	/**
	 * Test basic queries
	 */
	@Test
	public void snippet() throws Exception {

		// Server connections
		// - Connect to device (VAB object)
		VABElementProxy connSubModel1 = this.connManager.connectToVABElement("urn:de.FHG:devices.es.iese:statusSM:1.0:3:x-509#003");

		
		// Create dynamic get/set operation as lambda expression
		Map<String, Object> dynamicPropertyVal = VABLambdaProviderHelper.createSimple(new TailoredBaSyxSupplier(), null);
		// - Update property properties/dynamicExample with dynamic get/set operation
		connSubModel1.updateElementValue("dynamicExampleProperty", dynamicPropertyVal);

		// Read dynamicExample property
		Object devMode1c = connSubModel1.readElementValue("dynamicExampleProperty");

		// - Check value
		assertTrue(devMode1c.equals("BaSyxSupplier!"));
	}
}

