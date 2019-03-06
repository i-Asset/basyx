package org.eclipse.basyx.vab.provider.lambda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.basyx.vab.provider.hashmap.VABMapHandler;

/**
 * VABMapHandler that can additionally handle maps with hidden
 * get/set/delete/invoke properties.
 * 
 * @author schnicke, espen
 *
 */
public class VABLambdaMapHandler extends VABMapHandler {
	public static final String VALUE_SET_SUFFIX = "set";
	public static final String VALUE_GET_SUFFIX = "get";
	public static final String VALUE_INSERT_SUFFIX = "insert";
	public static final String VALUE_REMOVE_SUFFIX = "remove";

	@Override
	public Object postprocessObject(Object object) {
		return resolveAll(object);
	}

	@Override
	public Object getElementProperty(Object element, String propertyName) throws Exception {
		return super.getElementProperty(resolveSingle(element), propertyName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setModelPropertyValue(Object element, String propertyName, Object newValue) throws Exception {
		Object child = getElementProperty(element, propertyName);
		if (hasHiddenSetter(child)) {
			((Consumer<Object>) ((Map<String, Object>) child).get(VALUE_SET_SUFFIX)).accept(newValue);
		} else if (hasHiddenInserter(element) && (resolveSingle(element) instanceof Map<?, ?>)) {
			((BiConsumer<String, Object>) ((Map<String, Object>) element).get(VALUE_INSERT_SUFFIX)).accept(propertyName,
					newValue);
		} else {
			super.setModelPropertyValue(element, propertyName, newValue);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createValue(Object element, Object newValue) throws Exception {
		if (hasHiddenInserter(element)) {
			((Consumer<Object>) ((Map<String, Object>) element).get(VALUE_INSERT_SUFFIX)).accept(newValue);
		} else {
			super.createValue(element, newValue);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteValue(Object element, String propertyName) throws Exception {
		if (hasHiddenRemover(element)) {
			Consumer<Object> c = (Consumer<Object>) ((Map<Object, Object>) element).get(VALUE_REMOVE_SUFFIX);
			c.accept(propertyName);
		} else {
			super.deleteValue(element, propertyName);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteValue(Object element, Object property) throws Exception {
		if (hasHiddenRemover(element)) {
			Consumer<Object> c = (Consumer<Object>) ((Map<Object, Object>) element).get(VALUE_REMOVE_SUFFIX);
			c.accept(property);
		} else {
			super.deleteValue(element, property);
		}
	}

	@SuppressWarnings("unchecked")
	private Object resolveSingle(Object o) {
		while (hasHiddenGetter(o)) {
			Map<?, ?> map = (Map<?, ?>) o;
			o = ((Supplier<Object>) map.get(VALUE_GET_SUFFIX)).get();
		}
		return o;
	}

	/**
	 * Checks if a value is a raw value or points to a gettable property and
	 * resolves the underlying structure
	 */
	@SuppressWarnings("unchecked")
	private Object resolveAll(Object o) {
		o = resolveSingle(o);
		if (o instanceof Map<?, ?>) {
			return resolveMap((Map<String, Object>) o);
		} else if (o instanceof Collection<?>) {
			return resolveCollection((Collection<Object>) o);
		} else {
			return o;
		}
	}

	private Object resolveMap(Map<String, Object> map) {
		Map<String, Object> ret = new HashMap<>();
		for (String s : map.keySet()) {
			ret.put(s, resolveAll(map.get(s)));
		}
		return ret;
	}

	private Object resolveCollection(Collection<Object> collection) {
		Collection<Object> ret = new ArrayList<>(collection.size());
		for (Object o : collection) {
			ret.add(resolveAll(o));
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	private boolean hasHiddenInserter(Object elem) {
		if (elem instanceof Map<?, ?>) {
			Map<String, Object> map = (Map<String, Object>) elem;
			Object o = map.get(VALUE_INSERT_SUFFIX);
			return o instanceof BiConsumer<?, ?> || o instanceof Consumer<?>;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private boolean hasHiddenRemover(Object elem) {
		if (elem instanceof Map<?, ?>) {
			Map<String, Object> map = (Map<String, Object>) elem;
			Object o = map.get(VALUE_REMOVE_SUFFIX);
			return o instanceof Consumer<?>;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private boolean hasHiddenSetter(Object elem) {
		if (elem instanceof Map<?, ?>) {
			Map<String, Object> map = (Map<String, Object>) elem;
			Object o = map.get(VALUE_SET_SUFFIX);
			return o instanceof Consumer<?>;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private boolean hasHiddenGetter(Object elem) {
		if (elem instanceof Map<?, ?>) {
			Map<String, Object> map = (Map<String, Object>) elem;
			Object o = map.get(VALUE_GET_SUFFIX);
			return o instanceof Supplier<?>;
		}
		return false;
	}
}