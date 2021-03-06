package org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement;

import java.util.Collection;
import java.util.Map;

import org.eclipse.basyx.submodel.metamodel.api.reference.IReference;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyElements;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.dataelement.IMultiLanguageProperty;
import org.eclipse.basyx.submodel.metamodel.map.modeltype.ModelType;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.LangStrings;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;

/**
 * A MultiLanguageProperty element as defined in DAAS document
 * 
 * @author conradi
 *
 */
public class MultiLanguageProperty extends DataElement implements IMultiLanguageProperty {

	public static final String MODELTYPE = "MultiLanguageProperty";
	public static final String VALUE = "value";
	public static final String VALUEID = "valueId";
	
	public MultiLanguageProperty() {
		// Add model type
		putAll(new ModelType(MODELTYPE));
	}
	
	public MultiLanguageProperty(Reference reference, LangStrings langStrings) {
		this();
		put(VALUE, langStrings);
		put(VALUEID, reference);
	}
	
	/**
	 * Creates a MultiLanguageProperty object from a map
	 * 
	 * @param obj a MultiLanguageProperty object as raw map
	 * @return a MultiLanguageProperty object, that behaves like a facade for the given map
	 */
	public static MultiLanguageProperty createAsFacade(Map<String, Object> obj) {
		MultiLanguageProperty facade = new MultiLanguageProperty();
		facade.setMap(obj);
		return facade;
	}
	
	/**
	 * Returns true if the given submodel element map is recognized as a MultiLanguageProperty
	 */
	public static boolean isMultiLanguageProperty(Map<String, Object> map) {
		String modelType = ModelType.createAsFacade(map).getName();
		// Either model type is set or the element type specific attributes are contained (fallback)
		return MODELTYPE.equals(modelType)
				|| (modelType == null && (map.containsKey(VALUE) && map.containsKey(VALUE) && map.containsKey(VALUEID)
						&& !map.containsKey(Property.VALUETYPE)));
	}

	@Override
	@SuppressWarnings("unchecked")
	public IReference getValueId() {
		return Reference.createAsFacade((Map<String, Object>) get(VALUEID));
	}

	@Override
	@SuppressWarnings("unchecked")
	public LangStrings getValue() {
		return LangStrings.createAsFacade((Collection<Map<String, Object>>) get(VALUE));
	}
	
	@Override
	protected KeyElements getKeyElement() {
		return KeyElements.MULTILANGUAGEPROPERTY;
	}
}