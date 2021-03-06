package org.eclipse.basyx.vab.modelprovider.generic;

import org.eclipse.basyx.vab.modelprovider.api.IModelProvider;

/**
 * Interface for a handler, that can handle an object in the VAB. Can process
 * the object and supports all primitives defined by {@link IModelProvider}.
 * 
 * @author espen
 *
 */
public interface IVABElementHandler {
	/**
	 * Handles internal objects after they have been processed
	 */
	public default Object postprocessObject(Object element) {
		return element;
	}

	public Object getElementProperty(Object element, String propertyName);

	public void setModelPropertyValue(Object element, String propertyName, Object newValue);

	public void createValue(Object element, Object newValue);

	public void deleteValue(Object element, String propertyName);

	public void deleteValue(Object element, Object property);
}
