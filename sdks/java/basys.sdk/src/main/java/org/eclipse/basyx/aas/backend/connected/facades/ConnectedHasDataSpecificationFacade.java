package org.eclipse.basyx.aas.backend.connected.facades;

import java.util.HashSet;

import org.eclipse.basyx.aas.api.metamodel.aas.qualifier.IHasDataSpecification;
import org.eclipse.basyx.aas.api.metamodel.aas.reference.IReference;
import org.eclipse.basyx.aas.backend.connected.ConnectedElement;
import org.eclipse.basyx.aas.backend.connected.aas.qualifier.ConnectedHasDataSpecification;
import org.eclipse.basyx.vab.core.proxy.VABElementProxy;
/**
 * Facade providing access to a map containing the ConnectedHasDataSpecification structure
 * @author rajashek
 *
 */
public class ConnectedHasDataSpecificationFacade  extends ConnectedElement implements IHasDataSpecification {

	public ConnectedHasDataSpecificationFacade(VABElementProxy proxy) {
		super(proxy);
	}

	@Override
	public HashSet<IReference> getDataSpecificationReferences() {
		return new ConnectedHasDataSpecification(getProxy()).getDataSpecificationReferences();
	}

	@Override
	public void setDataSpecificationReferences(HashSet<IReference> ref) {
		new ConnectedHasDataSpecification(getProxy()).setDataSpecificationReferences(ref);
	}
		
	}

