package org.eclipse.basyx.examples.support.servlets;

import java.util.HashMap;

import org.eclipse.basyx.vab.backend.server.http.VABHTTPInterface;
import org.eclipse.basyx.vab.provider.hashmap.VABHashmapProvider;
import org.eclipse.basyx.vab.provider.lambda.VABLambdaProvider;



/**
 * Empty VAB provider servlet. It realizes a VAB lambda provider on its endpoint.
 * 
 * @author kuhn
 *
 */
public class EmptyVABLambdaElementServlet extends VABHTTPInterface<VABHashmapProvider> {

	
	/**
	 * Version information to identify the version of serialized instances
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Constructor
	 */
	public EmptyVABLambdaElementServlet() {
		// Invoke base constructor, instantiate a VAB object
		// - This object initially is empty, it is therefore described by an empty map
		super(new VABLambdaProvider(new HashMap<>()));
	}
}