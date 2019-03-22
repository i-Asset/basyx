package org.eclipse.basyx.components.cfgprovider;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.basyx.components.provider.BaseConfiguredProvider;



/**
 * Asset administration shell sub model provider that exports a properties file
 * 
 * @author kuhn
 *
 */
public class RawCFGSubModelProvider extends BaseConfiguredProvider {

		
	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public RawCFGSubModelProvider(Map<Object, Object> cfgValues) {
		// Call base constructor
		super(cfgValues);

		
		// Create sub model
		submodelData = createSubModel(cfgValues);

		// Load predefined elements from sub model
		elements.putAll(submodelData);


		// Load properties
		for (Object key: cfgValues.keySet()) {
			// Do not put meta data keys into map
			if (((String) key).endsWith("$ftype")) continue;

			// Get path to element
			String[] path = splitPath((String) key);
			
			// Create path
			Map<String, Object> scope = elements;
			for (int i=0; i<path.length-1; i++) {
				if (!scope.containsKey(path[i])) scope.put(path[i], new HashMap<String, Object>()); 
				scope = (Map<String, Object>) scope.get(path[i]);
			}
			
			// Get and optionally convert value
			Object value = cfgValues.get(key);
			// - Cast value if requested by user
			if (cfgValues.get(key+"$ftype") != null) switch((String) cfgValues.get(key+"$ftype")) {
				case "int":
					value = Integer.parseInt((String) value);
					break;
				case "boolean":
					value = Boolean.parseBoolean((String) value);
					break;
				case "float":
					value = Float.parseFloat((String) value);
					break;
				
				default: System.out.println("Unknown type:"+cfgValues.get(key+"$ftype"));
			}
			
			System.out.println("Putting:"+key+" = "+cfgValues.get(key)+" as "+value.getClass().getName());
			
			//if (cfgValues.get(key).equals("8"))
				//scope.put(path[path.length-1], Integer.parseInt((String) cfgValues.get(key)));
			//else
				scope.put(path[path.length-1], value);
		}

		
		// Print configuration values
		System.out.println("CFG exported");
	}
}

