package org.eclipse.basyx.submodel.metamodel.connected.submodelelement.dataelement.property;

import java.util.Collection;

import org.eclipse.basyx.submodel.metamodel.api.submodelelement.dataelement.property.ICollectionProperty;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.dataelement.property.PropertyType;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.eclipse.basyx.vab.exception.TypeMismatchException;
import org.eclipse.basyx.vab.exception.provider.ProviderException;
import org.eclipse.basyx.vab.modelprovider.VABElementProxy;

/**
 * Connects to a PropertySingleValued as specified by DAAS containing a
 * collection
 * 
 * @author schnicke
 *
 */
public class ConnectedCollectionProperty extends ConnectedProperty implements ICollectionProperty {

	public ConnectedCollectionProperty(VABElementProxy proxy) {
		super(PropertyType.Collection, proxy);
	}

	@Override
	public void set(Collection<Object> collection) throws ProviderException {
		try {
			getProxy().setModelPropertyValue(Property.VALUE, collection);
		} catch (ProviderException e) {
			throw e;
		} catch (Exception e) {
			throw new ProviderException(e);
		}
	}

	@Override
	public void add(Object newValue) throws ProviderException, TypeMismatchException {
		try {
			getProxy().createValue(Property.VALUE, newValue);
		} catch (ProviderException e) {
			throw e;
		} catch (Exception e) {
			throw new ProviderException(e);
		}
	}

	@Override
	public void remove(Object objectRef) throws ProviderException {
		try {
			getProxy().deleteValue(Property.VALUE, objectRef);
		} catch (ProviderException e) {
			throw e;
		} catch (Exception e) {
			throw new ProviderException(e);
		}
	}

	@Override
	public Collection<Object> getElements() throws ProviderException {
		return getCollection();
	}

	@Override
	public int getElementCount() throws ProviderException {
		return getCollection().size();
	}

	private Collection<Object> getCollection() {
		return retrieveObject();
	}
}
