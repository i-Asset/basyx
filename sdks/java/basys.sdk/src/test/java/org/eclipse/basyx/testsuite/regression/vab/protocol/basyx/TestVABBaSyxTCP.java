package org.eclipse.basyx.testsuite.regression.vab.protocol.basyx;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.eclipse.basyx.testsuite.regression.vab.modelprovider.SimpleVABElement;
import org.eclipse.basyx.testsuite.regression.vab.modelprovider.TestProvider;
import org.eclipse.basyx.vab.manager.VABConnectionManager;
import org.eclipse.basyx.vab.modelprovider.map.VABMapProvider;
import org.eclipse.basyx.vab.protocol.basyx.connector.BaSyxConnector;
import org.eclipse.basyx.vab.protocol.basyx.connector.BaSyxConnectorProvider;
import org.eclipse.basyx.vab.protocol.basyx.server.VABBaSyxTCPInterface;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test VAB using the BaSyx protocol. This is an integration test
 * 
 * @author schnicke, pschorn
 *
 */
public class TestVABBaSyxTCP extends TestProvider {
	protected VABConnectionManager connManager = new VABConnectionManager(new TestsuiteDirectory_BaSyxNative(),
			new BaSyxConnectorProvider());

	@Rule
	public VABTCPServerResource res = new VABTCPServerResource(new VABMapProvider(new SimpleVABElement()));

	@Override
	protected VABConnectionManager getConnectionManager() {
		return connManager;
	}

	/**
	 * Tests if a call to {@link BaSyxConnector#closeConnection} of the
	 * BaSyxConnector also terminates the thread on the server side
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testSuccessfulShutdown() throws InterruptedException {
		BaSyxConnector connector = new BaSyxConnector("localhost", 6998);

		// Wait for thread start
		Thread.sleep(100);

		// Ensure that connector thread is running
		Set<Thread> threads = Thread.getAllStackTraces().keySet();
		assertTrue(threads.stream().anyMatch(t -> t.getName().contains(VABBaSyxTCPInterface.class.getName()) && t.isAlive()));

		// Close socket --> Should also terminate thread
		connector.closeConnection();

		// Wait for thread shutdown
		Thread.sleep(100);

		// Ensure that thread is closed
		threads = Thread.getAllStackTraces().keySet();
		assertFalse(threads.stream().anyMatch(t -> t.getName().contains(VABBaSyxTCPInterface.class.getName())));
	}
}
