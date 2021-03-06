package org.eclipse.basyx.testsuite.regression.vab.modelprovider.map;

import org.eclipse.basyx.testsuite.regression.vab.modelprovider.SimpleVABElement;
import org.eclipse.basyx.testsuite.regression.vab.modelprovider.TestProvider;
import org.eclipse.basyx.testsuite.regression.vab.protocol.http.TestsuiteDirectory;
import org.eclipse.basyx.vab.manager.VABConnectionManager;
import org.eclipse.basyx.vab.modelprovider.api.IModelProvider;
import org.eclipse.basyx.vab.modelprovider.map.VABMapProvider;
import org.eclipse.basyx.vab.protocol.api.ConnectorProvider;

/**
 * Tests the functionality of the VABMapProvider according to the test cases in
 * the snippet package
 * 
 * @author schnicke
 *
 */
public class TestMapProvider extends TestProvider {
	private VABConnectionManager connManager;

	@Override
	protected VABConnectionManager getConnectionManager() {
		if (connManager == null) {
			connManager = new VABConnectionManager(new TestsuiteDirectory(), new ConnectorProvider() {
				@Override
				protected IModelProvider createProvider(String addr) {

					// Creates a new VABHashMapProvider which manages a data model
					// as defined in SimpleVABElement
					return new VABMapProvider(new SimpleVABElement());
				}
			});
		}
		return connManager;
	}
}
