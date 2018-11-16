package org.eclipse.basyx.vab.provider.lambda;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.basyx.vab.provider.hashmap.VABHashmapProvider;

/**
 * Provider that optionally allows properties to be modifiable by hidden
 * set/get/insert/remove property<br />
 * <br />
 * E.g.:<br />
 * GET $path is internally delegated to a call of $path/get which is a
 * {@link java.util.function.Consumer}. <br />
 * SET $path is delegated to $path/set which is a
 * {@link java.util.function.Supplier}
 * 
 * @author schnicke
 *
 */
public class VABLambdaProvider extends VABHashmapProvider {
	public static final String VALUE_SET_SUFFIX = "set";
	public static final String VALUE_GET_SUFFIX = "get";
	public static final String VALUE_INSERT_SUFFIX = "insert";
	public static final String VALUE_REMOVE_SUFFIX = "remove";

	public VABLambdaProvider(Map<String, Object> map) {
		super(map);
	}

	@Override
	public Object getModelPropertyValue(String path) {
		return resolve(super.getModelPropertyValue(path));
	}

	/**
	 * Checks if a value is a raw value or points to a gettable property and
	 * resolves the underlying value
	 * 
	 * @param o
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Object resolve(Object o) {
		if (o instanceof Map<?, ?>) {
			Map<String, Object> elemMap = (Map<String, Object>) o;
			if (hasHiddenGetter(elemMap)) {
				return ((Supplier<Object>) elemMap.get(VALUE_GET_SUFFIX)).get();
			} else {
				return resolveMap((Map<String, Object>) o);
			}
		} else {
			return o;
		}
	}

	private Object resolveMap(Map<String, Object> map) {
		Map<String, Object> ret = new HashMap<>();
		for (String s : map.keySet()) {
			ret.put(s, resolve(map.get(s)));
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.basyx.vab.provider.hashmap.VABHashmapProvider#createValue(java.
	 * lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void createValue(String path, Object newValue) throws Exception {
		// Check if it is a map to insert into
		if (path.contains("/")) {
			String parentPath = path.substring(0, path.lastIndexOf("/"));
			Object parent = super.getModelPropertyValue(parentPath);
			if (hasHiddenInserter(parent)) {
				String childPath = path.substring(path.lastIndexOf("/") + 1);
				((BiConsumer<String, Object>) ((Map<String, Object>) parent).get(VALUE_INSERT_SUFFIX)).accept(childPath,
						newValue);
				return;
			}
		}

		Object elem = super.getModelPropertyValue(path);
		if (hasHiddenInserter(elem)) {
			((Consumer<Object>) ((Map<String, Object>) elem).get(VALUE_INSERT_SUFFIX)).accept(newValue);
		} else {
			super.createValue(path, newValue);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setModelPropertyValue(String path, Object newValue) throws Exception {
		Object elem = super.getModelPropertyValue(path);

		if (hasHiddenSetter(elem)) {
			((Consumer<Object>) ((Map<String, Object>) elem).get(VALUE_SET_SUFFIX)).accept(newValue);
		} else {
			super.setModelPropertyValue(path, newValue);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteValue(String path, Object obj) throws Exception {
		Object elem = super.getModelPropertyValue(path);
		if (hasHiddenRemover(elem)) {
			Consumer<Object> c = (Consumer<Object>) ((Map<Object, Object>) elem).get(VALUE_REMOVE_SUFFIX);
			c.accept(obj);
		} else {
			super.deleteValue(path, obj);
		}
	}

	@SuppressWarnings("unchecked")
	private boolean hasHiddenInserter(Object elem) {
		if (elem instanceof Map<?, ?> && ((Map<Object, Object>) elem).containsKey(VALUE_INSERT_SUFFIX)) {
			Object o = ((Map<Object, Object>) elem).get(VALUE_INSERT_SUFFIX);
			return o instanceof BiConsumer<?, ?> || o instanceof Consumer<?>;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private boolean hasHiddenRemover(Object elem) {
		return elem instanceof Map<?, ?> && ((Map<Object, Object>) elem).containsKey(VALUE_REMOVE_SUFFIX)
				&& ((Map<Object, Object>) elem).get(VALUE_REMOVE_SUFFIX) instanceof Consumer<?>;
	}

	@SuppressWarnings("unchecked")
	private boolean hasHiddenSetter(Object elem) {
		return elem instanceof Map<?, ?> && ((Map<Object, Object>) elem).containsKey(VALUE_SET_SUFFIX)
				&& ((Map<Object, Object>) elem).get(VALUE_SET_SUFFIX) instanceof Consumer<?>;
	}

	private boolean hasHiddenGetter(Map<String, Object> elemMap) {
		return elemMap.containsKey(VALUE_GET_SUFFIX) && elemMap.get(VALUE_GET_SUFFIX) instanceof Supplier<?>;
	}
}