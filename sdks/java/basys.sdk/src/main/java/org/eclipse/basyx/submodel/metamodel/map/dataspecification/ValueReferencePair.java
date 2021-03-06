package org.eclipse.basyx.submodel.metamodel.map.dataspecification;

import java.util.Map;

import org.eclipse.basyx.submodel.metamodel.api.dataspecification.IValueReferencePair;
import org.eclipse.basyx.submodel.metamodel.api.qualifier.IIdentifiable;
import org.eclipse.basyx.submodel.metamodel.api.reference.IReference;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyElements;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyType;
import org.eclipse.basyx.submodel.metamodel.map.reference.Key;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;
import org.eclipse.basyx.vab.model.VABModelMap;

/**
 * The map implementation for a value reference pair within a value list of the DataSpecificationIEC61360.
 * Each value has a global unique id defining its semantic.
 * 
 * @author espen
 *
 */
public class ValueReferencePair extends VABModelMap<Object> implements IValueReferencePair {
	public static final String VALUE = "value";
	public static final String VALUEID = "valueId";

	/**
	 * Constructor
	 */
	public ValueReferencePair() {
		setValue(null);
		setValueId(null);
	}

	/**
	 * Constructs a reference based on an {@link IIdentifiable} and additional
	 * information (see {@link Key#Key(KeyElements, boolean, String, KeyType)}).
	 * 
	 * @param identifiable
	 * @param keyElement
	 * @param local
	 */
	public ValueReferencePair(String value, IReference valueId) {
		setValue(value);
		setValueId(valueId);
	}

	/**
	 * Creates a ValueReferencePair object from a map
	 * 
	 * @param obj
	 *            a ValueReferencePair object as raw map
	 * @return a ValueReferencePair object, that behaves like a facade for the given map
	 */
	public static ValueReferencePair createAsFacade(Map<String, Object> map) {
		if (map == null) {
			return null;
		}

		ValueReferencePair ret = new ValueReferencePair();
		ret.setMap(map);
		return ret;
	}

	@Override
	@SuppressWarnings("unchecked")
	public IReference getValueId() {
		return Reference.createAsFacade((Map<String, Object>) get(VALUEID));
	}

	@Override
	public String getValue() {
		return (String) get(VALUE);
	}

	public void setValueId(IReference valueId) {
		put(VALUEID, valueId);
	}

	public void setValue(String value) {
		put(VALUE, value);
	}
}
