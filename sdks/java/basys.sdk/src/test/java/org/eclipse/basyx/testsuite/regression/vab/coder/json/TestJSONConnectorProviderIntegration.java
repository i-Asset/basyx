package org.eclipse.basyx.testsuite.regression.vab.coder.json;

import org.eclipse.basyx.testsuite.regression.vab.modelprovider.SimpleVABElement;
import org.eclipse.basyx.testsuite.regression.vab.modelprovider.TestProvider;
import org.eclipse.basyx.testsuite.regression.vab.protocol.http.TestsuiteDirectory;
import org.eclipse.basyx.vab.coder.json.connector.JSONConnector;
import org.eclipse.basyx.vab.coder.json.provider.JSONProvider;
import org.eclipse.basyx.vab.manager.VABConnectionManager;
import org.eclipse.basyx.vab.modelprovider.api.IModelProvider;
import org.eclipse.basyx.vab.modelprovider.map.VABMapProvider;
import org.eclipse.basyx.vab.protocol.api.ConnectorProvider;

/**
 * Test JSONConnector against JSONProvider
 * 
 * @author pschorn
 *
 */
public class TestJSONConnectorProviderIntegration extends TestProvider {

	protected VABConnectionManager connManager = new VABConnectionManager(new TestsuiteDirectory(),
			new ConnectorProvider() {

				@Override
				protected IModelProvider createProvider(String addr) {

					// BACKEND
					// Creates a new VABMapProvider which manages a data model as defined in
					// SimpleVABElement.
					VABMapProvider modelprovider = new VABMapProvider(new SimpleVABElement());

					// We stack the JSONProvider on top of the model to handle serialization and exceptions
					JSONProvider<VABMapProvider> provider = new JSONProvider<VABMapProvider>(modelprovider);

					// FRONTEND
					// We stack the JSONConnector on top of the JSONProvider to handle de-serialization and response
					// verification
					JSONConnector connector = new JSONConnector(
							new IBasyxConnectorFacade<VABMapProvider>(provider));

					return connector;
				}
			});

	@Override
	protected VABConnectionManager getConnectionManager() {
		return connManager;
	}
}