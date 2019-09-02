package org.eclipse.basyx.aas.backend.connected.aas.submodelelement.property;

import org.eclipse.basyx.aas.api.resources.IProperty;
import org.eclipse.basyx.aas.api.resources.PropertyType;
import org.eclipse.basyx.aas.backend.connected.aas.submodelelement.ConnectedDataElement;
import org.eclipse.basyx.aas.metamodel.hashmap.aas.submodelelement.property.Property;
import org.eclipse.basyx.aas.metamodel.hashmap.aas.submodelelement.property.valuetypedef.PropertyValueTypeDefHelper;
import org.eclipse.basyx.vab.core.proxy.VABElementProxy;
/**
 * "Connected" implementation of IProperty
 * @author rajashek
 *
 */
public class ConnectedProperty extends ConnectedDataElement implements IProperty {
	private PropertyType type;

	public ConnectedProperty(PropertyType type, VABElementProxy proxy) {
		super(proxy);		
		this.type = type;
	}

	@Override
	public PropertyType getPropertyType() {
		return type;
	}

	@Override
	public void setValue(Object value) {
		getProxy().setModelPropertyValue(Property.VALUE, value);
		getProxy().setModelPropertyValue(Property.VALUETYPE, PropertyValueTypeDefHelper.fromObject(value));

		
	}

	@Override
	public Object getValue() {
		return	getProxy().getModelPropertyValue(Property.VALUE);
	}

	@Override
	public void setValueId(Object obj) {
		getProxy().setModelPropertyValue(Property.VALUEID, obj);
		
	}

	@Override
	public Object getValueId() {
		return getProxy().getModelPropertyValue(Property.VALUEID);
	}





}
