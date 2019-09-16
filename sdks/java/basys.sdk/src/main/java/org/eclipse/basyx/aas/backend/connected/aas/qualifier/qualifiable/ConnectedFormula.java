package org.eclipse.basyx.aas.backend.connected.aas.qualifier.qualifiable;

import java.util.Set;

import org.eclipse.basyx.aas.api.metamodel.aas.qualifier.qualifiable.IFormula;
import org.eclipse.basyx.aas.api.metamodel.aas.reference.IReference;
import org.eclipse.basyx.aas.impl.metamodel.hashmap.aas.qualifier.qualifiable.Formula;
import org.eclipse.basyx.vab.core.proxy.VABElementProxy;

/**
 * "Connected" implementation of IFormula
 * 
 * @author rajashek
 *
 */
public class ConnectedFormula extends ConnectedConstraint implements IFormula {

	public ConnectedFormula(VABElementProxy proxy) {
		super(proxy);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<IReference> getDependsOn() {
		return (Set<IReference>) getProxy().getModelPropertyValue(Formula.DEPENDSON);
	}

}
