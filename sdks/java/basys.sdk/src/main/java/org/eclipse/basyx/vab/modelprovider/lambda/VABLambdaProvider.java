package org.eclipse.basyx.vab.modelprovider.lambda;

import java.util.Map;

import org.eclipse.basyx.vab.modelprovider.generic.VABModelProvider;

/**
 * Provider that optionally allows properties to be modifiable by hidden
 * set/get/insert/remove property. <br />
 * Supports nested lambda-expressions. <br />
 * E.g.:<br />
 * GET $path is internally delegated to a call of $path/get which is a
 * {@link java.util.function.Consumer}. <br />
 * SET $path is delegated to $path/set which is a
 * {@link java.util.function.Supplier}.
 * 
 * @author schnicke, espen
 *
 */
public class VABLambdaProvider extends VABModelProvider {
	public VABLambdaProvider(Map<String, Object> elements) {
		super(elements, new VABLambdaHandler());
	}
}
