package org.eclipse.basyx.regression.registry;

import org.eclipse.basyx.aas.registration.api.IAASRegistryService;
import org.eclipse.basyx.aas.registration.restapi.DirectoryModelProvider;
import org.eclipse.basyx.components.sqlregistry.SQLRegistry;
import org.eclipse.basyx.testsuite.regression.aas.registration.proxy.TestRegistryProvider;
import org.eclipse.basyx.vab.modelprovider.VABElementProxy;
import org.eclipse.basyx.vab.modelprovider.api.IModelProvider;

/**
 * Test class for a local registry provider based on SQL tables
 * 
 * @author espen
 *
 */
public class TestSQLRegistryProvider extends TestRegistryProvider {
	/**
	 * Sets up the SQL directory servlet for the sql tests
	 */
	@Override
	protected IModelProvider getProxyProvider() {
		// Sets up the sql registry based on the given config file
		IAASRegistryService sqlRegistry = new SQLRegistry("localRegistry.properties");
		// Wraps the registry in an IModelProvider
		DirectoryModelProvider provider = new DirectoryModelProvider(sqlRegistry);
		// Uses a proxy to directly address the registry via its api prefix
		IModelProvider apiProxy = new VABElementProxy("/api/v1/registry", provider);
		return apiProxy;
	}
}