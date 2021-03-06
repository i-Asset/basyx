package org.eclipse.basyx.vab.directory.memory;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.basyx.vab.directory.api.IVABDirectoryService;
import org.eclipse.basyx.vab.exception.provider.ResourceNotFoundException;



/**
 * Implement a in memory directory
 * 
 * @author kuhn
 *
 */
public class InMemoryDirectory implements IVABDirectoryService {

	
	/**
	 * Map that stores key/value mappings
	 */
	protected Map<String, Object> keyToValue = new HashMap<>();
	
	
		
	
	/**
	 * Default constructor
	 */
	public InMemoryDirectory() {
		// Do nothing
	}
	
	
	/**
	 * Constructor that accepts initial entries
	 */
	public InMemoryDirectory(Map<String, String> addedValues) {
		keyToValue.putAll(addedValues);
	}
	
	
	
	/**
	 * Add a mapping to directory
	 */
	@Override
	public IVABDirectoryService addMapping(String key, String value) {
		keyToValue.put(key, value);
		
		// Return 'this' instance
		return this;
	}

	/**
	 * Add several mappings to directory
	 */
	public void addMappings(Map<String, String> mappings) {
		keyToValue.putAll(mappings);
	}
	
	/**
	 * Remove a mapping from directory
	 */
	@Override
	public void removeMapping(String key) {
		keyToValue.remove(key);
	}
	
	/**
	 * Lookup method
	 */
	@Override
	public String lookup(String id) {
		if(keyToValue.containsKey(id)) {
			return (String) keyToValue.get(id);
		} else {
			throw new ResourceNotFoundException("No entry exists for key " + id);
		}
	}
}
