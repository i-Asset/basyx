package org.eclipse.basyx.testsuite.support.backend.common.stubs.java.directory;

import org.eclipse.basyx.aas.impl.services.PreconfiguredDirectory;




/**
 * Implement the test suite directory service with pre-configured directory entries
 * 
 * @author kuhn
 *
 */
public class OPCUATestsuiteDirectory extends PreconfiguredDirectory {

	
	/**
	 * Constructor - load all directory entries
	 */
	public OPCUATestsuiteDirectory() {
		// Define mappings
		// - Asset administration shells
		addMapping("Stub1AAS", "http://localhost:8080/basys.sdk/Testsuite/Stub1AAS/BaSys/1.0/provider");
		// - Sub models of Stub1AAS
		addMapping("statusSM.Stub1AAS",        "http://localhost:8080/basys.sdk/Testsuite/Stub1Submodel/BaSys/1.0/provider");
		addMapping("statusSM",                 "http://localhost:8080/basys.sdk/Testsuite/Stub1Submodel/BaSys/1.0/provider");
		addMapping("technicalDataSM.Stub1AAS", "http://localhost:8080/basys.sdk/Testsuite/Stub1Submodel/BaSys/1.0/provider");
		
		/**
		 * any opcua specific addresses?
		 */
		
	}
}