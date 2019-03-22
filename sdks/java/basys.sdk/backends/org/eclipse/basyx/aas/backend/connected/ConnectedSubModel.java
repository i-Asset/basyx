package org.eclipse.basyx.aas.backend.connected;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.basyx.aas.api.resources.IOperation;
import org.eclipse.basyx.aas.api.resources.IProperty;
import org.eclipse.basyx.aas.api.resources.ISubModel;
import org.eclipse.basyx.aas.backend.connected.property.ConnectedPropertyFactory;
import org.eclipse.basyx.vab.core.proxy.VABElementProxy;

/**
 * Connected implementation of the SubModel <br />
 * Allows access to a remote SubModel
 * 
 * @author schnicke
 *
 */
public class ConnectedSubModel extends ConnectedElement implements ISubModel {

	ConnectedPropertyFactory factory = new ConnectedPropertyFactory();

	/**
	 * Constructor creating a ConnectedSM pointing to the SM represented by proxy
	 * and path
	 * 
	 * @param path
	 * @param proxy
	 * @param manager
	 */
	public ConnectedSubModel(String path, VABElementProxy proxy) {
		super(path, proxy);
	}

	/**
	 * Copy constructor, allowing to create a ConnectedSM pointing to the same SM as
	 * <i>shell</i>
	 * 
	 * @param shell
	 */
	public ConnectedSubModel(ConnectedSubModel sm) {
		super(sm.getPath(), sm.getProxy());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, IProperty> getProperties() {
		Map<String, Object> props = (Map<String, Object>) getProxy().readElementValue(constructPath("properties"));

		Map<String, IProperty> ret = new HashMap<>();
		for (String s : props.keySet()) {
			ret.put(s, factory.createProperty(constructPath("properties/" + s), getProxy()));
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, IOperation> getOperations() {
		Map<String, Object> ops = (Map<String, Object>) getProxy().readElementValue(constructPath("operations"));

		Map<String, IOperation> ret = new HashMap<>();
		for (String s : ops.keySet()) {
			ret.put(s, new ConnectedOperation(constructPath("operations/" + s), getProxy()));
		}
		return ret;
	}
}
