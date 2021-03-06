package org.eclipse.basyx.submodel.metamodel.api.qualifier;

import java.util.Collection;

import org.eclipse.basyx.submodel.metamodel.api.dataspecification.IEmbeddedDataSpecification;
import org.eclipse.basyx.submodel.metamodel.api.reference.IReference;

/**
 * Element that can be extended by using data specification templates. A data
 * specification template defines the additional attributes an element may or
 * shall have. The data specifications used are explicitly specified with their
 * global id.
 *
 * @author rajashek, schnicke
 *
 */

public interface IHasDataSpecification {

	/**
	 * Global reference to the data specification template used by the element.
	 */
	public Collection<IReference> getDataSpecificationReferences();

	public Collection<IEmbeddedDataSpecification> getEmbeddedDataSpecifications();
}
