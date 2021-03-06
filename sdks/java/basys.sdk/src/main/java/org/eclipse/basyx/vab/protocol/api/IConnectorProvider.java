/**
 * 
 */
package org.eclipse.basyx.vab.protocol.api;

import org.eclipse.basyx.vab.modelprovider.api.IModelProvider;

/**
 * Base interface for providers that return IModelProviders that connects to
 * specific addresses
 * 
 * @author schnicke
 *
 */
public interface IConnectorProvider {

	/**
	 * Gets an IModelProvider connecting the specific address.
	 *
	 * @param addr 
	 * 		The address for which a provider is returned. Must be an address limited to one included endpoint.
	 * 		For example, it does NOT support basyx://localhost:6998//http://localhost/a/b/c, but http://localhost/a/b/c
	 * @return
	 * 		A provider that directly points to the element referenced by the given address. 
	 * 		E.g. the returned model provider for http://localhost/a/b/c directly points to the element c. Therefore, 
	 * 		getConnector("http://localhost/a/b/c").getModelPropertyValue(""); returns the value of the element c.  
	 */
	public IModelProvider getConnector(String addr);
}
