package org.eclipse.basyx.tools.propertyfile.opdef;

import java.util.Collection;
import java.util.LinkedList;



/**
 * Operation definition tools
 * 
 * This class implements operations that support developers in parsing operation definitions from property files
 * 
 * @author kuhn
 *
 */
public class OperationDefinition {
	
	
	/**
	 * Get operation name of operation definition 
	 * 
	 * @param opDef The operation definition string
	 * @return Operation name
	 */
	public static String getOperation(String opDef) {
		// Get operation name
		return opDef.substring(0, opDef.indexOf("("));
	}
	
	
	
	/**
	 * Get parameter list of an operation definition
	 * 
	 * A parameter list contains of a name and of a type for each parameter+
	 * 
	 * @param opDef The operation definition string
	 * @return Collection of Parameter definitions
	 */
	public static Collection<Parameter> getParameter(String opDef) {
		// Return type
		LinkedList<Parameter> result = new LinkedList<>();

		System.out.println("**"+opDef);
		
		// Extract parameter sequence
		String   callParameterStr  = opDef.substring(opDef.indexOf("(")+1, opDef.length()-1).trim();
		String[] callParameterList = callParameterStr.split(",");
		
		// Iterate all parameter. If no parameter is given, the loop will execute once with an empty String (length = 0)
		for (String parameterDef: callParameterList) {
			System.out.println("[["+parameterDef+"]]"+opDef);
			
			// Only process strings with a length > 0
			if (parameterDef.length() == 0) continue;
			
			// Add parameter
			result.add(new Parameter(parameterDef.substring(0, parameterDef.indexOf(":")).trim(), parameterDef.substring(parameterDef.indexOf(":")+1).trim().toLowerCase()));			
		}
		
		// Return result
		return result;
	}
	
	

	/**
	 * Create a SQL string from an input SQL string with place holders in format $x with x being an integer number.
	 * 
	 * @param baseString SQL string with place holders
	 * @param parameter Parameter values that place holders are substituted for
	 * 
	 * @return SQL string with parameter instead of place holders
	 */
	public static String getSQLString(String baseString, Collection<String> parameter) {
		// Resulting SQL String
		String result = baseString;
		
		// Replace place holders with parameter
		// - Counter variable
		int counter = 1;
		// - Replace all place holders
		for (String par: parameter) {
			result = result.replace("$"+counter, par);
			counter++;
		}
		
		// Return SQL string with resolved parameter
		return result;
	}
}

