package org.eclipse.basyx.vab.backend.server;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

import org.eclipse.basyx.aas.api.exception.LostHTTPRequestParameterException;
import org.eclipse.basyx.aas.api.exception.ServerException;
import org.eclipse.basyx.aas.backend.http.tools.GSONTools;
import org.eclipse.basyx.vab.core.IModelProvider;
import org.eclipse.basyx.vab.core.tools.VABPathTools;

/**
 * Provider class that supports JSON serialized communication
 * 
 * 
 * @author pschorn, schnicke, kuhn
 *
 */
public class JSONProvider<T extends IModelProvider> {

	/**
	 * Reference to IModelProvider backend
	 */
	protected T providerBackend = null;

	/**
	 * Constructor
	 */
	public JSONProvider(T modelProviderBackend) {
		// Store reference to backend
		providerBackend = modelProviderBackend;
	}

	/**
	 * Get backend reference
	 */
	public T getBackendReference() {
		return providerBackend;
	}

	/**
	 * Send JSON encoded response
	 */
	private void sendJSONResponse(PrintWriter outputStream, Object jsonValue) {
		// Output result
		outputStream.write(jsonValue.toString()); // FIXME throws nullpointer exception if jsonValue is null
		outputStream.flush();
	}

	/**
	 * Send Error
	 * 
	 * @param e
	 * @param path
	 * @param resp
	 */
	private void sendException(PrintWriter resp, Exception e) {
		System.out.println("Sending " + e.toString() + " ...");
		e.printStackTrace();
		
		
		//JSONObject error = JSONTools.Instance.serialize(e);
		Map<String, Object> gsonobj = GSONTools.Instance.serialize(e);
		String jsonString   = GSONTools.Instance.getJsonString(gsonobj);

		// Send error response
		sendJSONResponse(resp, jsonString);
	}

	/**
	 * Extracts parameter from JSON and handles de-serialization errors
	 * 
	 * @param path
	 * @param serializedJSONValue
	 * @param outputStream
	 * @return
	 * @throws LostHTTPRequestParameterException 
	 * @throws ServerException
	 */
	@SuppressWarnings("unchecked")
	private Object extractParameter(String path, String serializedJSONValue, PrintWriter outputStream) throws LostHTTPRequestParameterException  {

		System.out.println("Extracting Parameter: " + serializedJSONValue);

		// Return value
		Object result = null;

		// Deserialize json body
		try {
			if (serializedJSONValue.equals("")) {
				LostHTTPRequestParameterException ex = new LostHTTPRequestParameterException(path);
				sendException(outputStream, ex);
				throw ex;
			}
			
			Object gsonObj =GSONTools.Instance.getObjFromJsonStr(serializedJSONValue.toString());
			
			result = GSONTools.Instance.deserialize((Map<String, Object>) gsonObj);
			

		} catch (Exception e) {
			if (!(e instanceof LostHTTPRequestParameterException)) {
			e.printStackTrace();
			sendException(outputStream, new IllegalArgumentException("Invalid paramater: " + serializedJSONValue));
			} else {
				throw e;
			}
		}
		return result;
	}

	/**
	 * Process a BaSys get operation, return JSON serialized result
	 */
	public void processBaSysGet(String path, PrintWriter outputStream) {

		System.out.println("-------------------------- DO GET " + path + "---------------------------------------------------------");
		try {
			Object value = providerBackend.getModelPropertyValue(path);

			// Initialize JSON object
			Map<String, Object> gsonobj = GSONTools.Instance.serialize(value);
			String jsonString = GSONTools.Instance.getJsonString(gsonobj);
			// Send response
			sendJSONResponse(outputStream, jsonString);
		} catch (Exception e) {
			sendException(outputStream, e);
		}
	}

	/**
	 * Process a BaSys set operation
	 * 
	 * @param path
	 * @param serializedJSONValue
	 * @param outputStream
	 */
	public void processBaSysSet(String path, String serializedJSONValue, PrintWriter outputStream) {
		// Deserialize json body. If parameter is null, an exception has been sent
		Object parameter;
		try {
			parameter = extractParameter(path, serializedJSONValue, outputStream);
		} catch (LostHTTPRequestParameterException e1) {
			return;
		}

		System.out.println("-------------------------- DO PUT " + path + " => " + parameter + " ---------------------------------------------------------");

		// Try to set value of BaSys VAB element
		try {
			// Set the value of the element
			providerBackend.setModelPropertyValue(path, parameter);

			// Send positive JSON response
			Map<String, Object> gsonobj = GSONTools.Instance.serialize(true);
			String jsonString   = GSONTools.Instance.getJsonString(gsonobj);
			sendJSONResponse(outputStream, jsonString);

		} catch (Exception e) {
			sendException(outputStream, e);
		}
	}

	/**
	 * Process a BaSys invoke or create operation
	 * 
	 * @param path
	 * @param serializedJSONValue
	 * @param outputStream
	 */
	public void processBaSysPost(String path, String serializedJSONValue, PrintWriter outputStream) {

		// Invoke provider backend
		try {
			// Check if request is for property creation or operation invoke
			if (VABPathTools.isOperationPath(path)) {

				// Invoke BaSys VAB 'invoke' primitive
				processBaSysInvoke(path, serializedJSONValue, outputStream);

			} else {
				// Invoke the BaSys 'create' primitive
				processBaSysCreate(path, serializedJSONValue, outputStream);
			}
		} catch (Exception e) {
			sendException(outputStream, e);
		}

	}

	/**
	 * Process a BaSys invoke operation
	 */
	public void processBaSysInvoke(String path, String serializedJSONValue, PrintWriter outputStream) {

		// Deserialize json body. If parameter is null, an exception has been sent
		Object parameter;
		try {
			parameter = extractParameter(path, serializedJSONValue, outputStream);
		} catch (LostHTTPRequestParameterException e1) {
			return;
		}

		Object returnValue = null;
		
		// If only a single parameter has been sent, pack it into an array so it can be casted safely
		if (!(parameter instanceof Object[])) {
			Object[] parameterArray = new Object[1];
			Object tmp = parameter;
			parameterArray[0] = tmp;
			parameter = parameterArray;
		}

		try {
			System.out.println("Invoking Service: " + path + " with arguments " + Arrays.toString((Object[]) parameter)); // casts parameter to collection

			Object result = providerBackend.invokeOperation(path, (Object[]) parameter);
			System.out.println("Return Value: " + result);

			Map<String, Object> gsonobj = GSONTools.Instance.serialize(result);
			returnValue = GSONTools.Instance.getJsonString(gsonobj);
			
			System.out.println(returnValue);

		} catch (Exception e) {
			sendException(outputStream, e);
		}

		// Send response
		sendJSONResponse(outputStream, returnValue);
	}

	/**
	 * Process a patch request. Deletes and element from a map or collection.
	 * 
	 * @param path
	 * @param serializedJSONValue
	 * @param action
	 * @param outputStream
	 */
	public void processBaSysPatch(String path, String serializedJSONValue, PrintWriter outputStream) {

		try {
			// Deserialize json body. If parameter is null, an exception has been sent
			Object parameter = extractParameter(path, serializedJSONValue, outputStream);
			/**
			 * Remove an element from a collection by index / remove from map by key. We
			 * know parameter must only contain 1 element
			 */
			providerBackend.deleteValue(path, parameter);

			// Send positive JSON response
			// TODO provide message meta information here
			Map<String, Object> gsonobj = GSONTools.Instance.serialize(true);
			String jsonString   = GSONTools.Instance.getJsonString(gsonobj);
			sendJSONResponse(outputStream, jsonString);

		} catch (Exception e) {
			sendException(outputStream, e);
		}
	}

	/**
	 * Implement "Delete" operation. Deletes any resource under the given path.
	 * 
	 * @param path
	 * @param hasArgument 
	 * @param serializedJSONValue
	 * @param outputStream
	 */
	public void processBaSysDelete(String path, String serializedValue, PrintWriter outputStream) {
		// Deserialize json body. If parameter is null, an exception has been sent
		Object parameter;
		try {
			parameter = extractParameter(path, serializedValue, outputStream);
		} catch (LostHTTPRequestParameterException e1) {
			return;
		}
		

		System.out.println("Delete1:" + parameter);
		// Invoke provider backend
		try {
			// Process delete request with or without argument
			if (parameter == null) {
				this.providerBackend.deleteValue(path);
			} else {
				System.out.println("Delete:" + parameter);
				this.providerBackend.deleteValue(path, parameter);
			}

			// Send positive JSON response
			// TODO provide message meta information here
			Map<String, Object> gsonobj = GSONTools.Instance.serialize(true);
			String jsonString = GSONTools.Instance.getJsonString(gsonobj);
			sendJSONResponse(outputStream, jsonString);

		} catch (Exception e) {
			e.printStackTrace();

			sendException(outputStream, e);
		}
	}

	/**
	 * Creates a resource under the given path
	 * 
	 * @param path
	 * @param parameter
	 * @param outputStream
	 */
	public void processBaSysCreate(String path, String serializedJSONValue, PrintWriter outputStream) {

		try {
			// Deserialize json body. If parameter is null, an exception has been sent
			Object parameter = extractParameter(path, serializedJSONValue, outputStream);

			providerBackend.createValue(path, parameter);

			// Send positive JSON response
			Map<String, Object> gsonobj = GSONTools.Instance.serialize(true);
			String jsonString = GSONTools.Instance.getJsonString(gsonobj);
			// TODO provide message meta information here
			sendJSONResponse(outputStream, jsonString);

		} catch (Exception e) {
			sendException(outputStream, e);
		}
	}

}
