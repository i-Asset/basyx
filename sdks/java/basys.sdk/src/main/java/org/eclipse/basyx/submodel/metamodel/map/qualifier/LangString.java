package org.eclipse.basyx.submodel.metamodel.map.qualifier;

import java.util.Map;

import org.eclipse.basyx.vab.model.VABModelMap;

/**
 * This class holds a description in a single language
 * 
 * @author haque
 *
 */
public class LangString extends VABModelMap<Object> {
	private static final String LANGUAGE = "language";
	private static final String DESCRIPTION = "description";
	
	private LangString() {
	}
	
	/**
	 * Constructor that accepts a language and a description
	 * @param language
	 * @param description
	 */
	public LangString(String language, String description) {
		put(LANGUAGE, language);
		put(DESCRIPTION, description);
	}
	
	/**
	 * Creates a LangString object from a map
	 * 
	 * @param obj a LangString object as raw map
	 * @return a LangString object, that behaves like a facade for
	 *         the given map
	 */
	public static LangString createAsFacade(Map<String, Object> map) {
		if (map == null) {
			return null;
		}

		LangString ret = new LangString();
		ret.setMap(map);
		return ret;
	}
	
	/**
	 * Get Language of the langString
	 * @return Language
	 */
	public String getLanguage() {
		return (String) get(LANGUAGE);
	}
	
	/**
	 * Get Description of the langString
	 * @return Description
	 */
	public String getDescription() {
		return (String) get(DESCRIPTION);
	} 
}
