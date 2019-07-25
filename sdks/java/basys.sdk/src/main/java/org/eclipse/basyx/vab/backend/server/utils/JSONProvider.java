package org.eclipse.basyx.vab.backend.server.utils;

import java.io.PrintWriter;
import java.util.List;

import org.eclipse.basyx.aas.api.exception.LostHTTPRequestParameterException;
import org.eclipse.basyx.aas.api.exception.ServerException;
import org.eclipse.basyx.aas.backend.http.tools.GSONTools;
import org.eclipse.basyx.aas.backend.http.tools.factory.DefaultTypeFactory;
import org.eclipse.basyx.aas.backend.http.tools.factory.GSONToolsFactory;
import org.eclipse.basyx.vab.core.IModelProvider;



/**
 * Provider class that supports JSON serialized communication <br/>
 * Generic Caller is required since messages can be technology specific.
 * 
 * 
 * @author pschorn, schnicke, kuhn
 *
 */
public class JSONProvider<ModelProvider extends IModelProvider> {

	
	/**
	 * Reference to IModelProvider backend
	 */
	protected ModelProvider providerBackend = null;
	
	
	/**
	 * Reference to serializer / deserializer
	 */
	protected GSONTools serializer = null;
	

	
	/**
	 * Constructor
	 */
	public JSONProvider(ModelProvider modelProviderBackend) {
		// Store reference to backend
		providerBackend = modelProviderBackend;
		
		// Create GSON serializer
		serializer = new GSONTools(new DefaultTypeFactory());
	}


	/**
	 * Constructor
	 */
	public JSONProvider(ModelProvider modelProviderBackend, GSONToolsFactory factory) {
		// Store reference to backend
		providerBackend = modelProviderBackend;
		
		// Create GSON serializer
		serializer = new GSONTools(factory);
	}
	
	
	/**
	 * Get serializer reference
	 */
	public GSONTools getSerializerReference() {
		return serializer;
	}
	

	/**
	 * Get backend reference
	 */
	public ModelProvider getBackendReference() {
		return providerBackend;
	}
	
	
	/**
	 * Wrap object with meta-protocol and return serialized string 
	 */
	private String serialize(boolean success, Object entity,  Class<?> entityType, List<IMessage> messages) {
		
		// Wrap the entity in the meta-protocol
		IResult result = new Result(success, entity, entityType, messages);
		
		// Serialize the whole thing
		return serialize(result);
	}
	
	
	/**
	 * Acknowledges successful operation without entity body
	 * @param success
	 * @return
	 */
	private String serialize(boolean success) {
		
		// Create Ack
		IResult result = new Result(success);
		
		// Serialize the whole thing
		return serialize(result);
	}
	
	
	/**
	 * Marks success as false and delivers exception cause messages 
	 * @param e
	 * @return
	 */
	private String serialize(Exception e) {
		// Create Ack
		IResult result = new Result(e);
		
		// Serialize the whole thing
		return serialize(result);
	}
	
	
	/**
	 * Serialize IResult (HashMap)
	 * @param string
	 * @return
	 */
	private String serialize(IResult string) {
		// Serialize the whole thing
		return serializer.serialize(string);
	}
	

	/**
	 * Send JSON encoded response
	 */
	private void sendJSONResponse(PrintWriter outputStream, String jsonValue) {
		// Write result to output stream
		outputStream.write(jsonValue); 
		outputStream.flush();
	}
	

	/**
	 * Send Error
	 * @param e
	 * @param path
	 * @param resp
	 */
	private void sendException(PrintWriter resp, Exception e) {
		e.printStackTrace();
		
		// Serialize Exception
		String jsonString = serialize(e);

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
	private Object extractParameter(String path, String serializedJSONValue, PrintWriter outputStream) {
		// Return value
		Object result = null;

		// Deserialize json body
		result = serializer.deserialize(serializedJSONValue);
			
		return result;
	}
	

	/**
	 * Process a BaSys get operation, return JSON serialized result
	 */
	public void processBaSysGet(String path, PrintWriter outputStream) {

		try {
			// Get requested value from provider backend
			Object value = providerBackend.getModelPropertyValue(path);

			// if (value.getClass().isArray()) {
			// List<Object> tmp = new ArrayList<>();
			// for (Object o : (Object[]) value) {
			// tmp.add(o);
			// }
			// value = tmp;
			// }

			// Serialize as json string
			String jsonString = serialize(true, value, (value == null ? null : value.getClass()), null); // any
																											// messages?

			// Send response
			sendJSONResponse(outputStream, jsonString);
			
		} catch (Exception e) {
			sendException(outputStream, e);		// FIXME: There is no exception thrown for GET requests on the client!!
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
		
		// Try to set value of BaSys VAB element
		try {
			
			// Deserialize json body. If parameter is not ex
			Object parameter = extractParameter(path, serializedJSONValue, outputStream);

			// Set the value of the element
			providerBackend.setModelPropertyValue(path, parameter);

			// Create serialized acknowledgement 
			String jsonString = serialize(true);
			
			// Send response
			sendJSONResponse(outputStream, jsonString);

		} catch (Exception e) {
			sendException(outputStream, e);
		}
	}

	
	/**
	 * Process a BaSys invoke operation
	 */
	@SuppressWarnings("unchecked")
	public void processBaSysInvoke(String path, String serializedJSONValue, PrintWriter outputStream) {

		try {
			
			// Deserialize json body. 
			Object parameter = extractParameter(path, serializedJSONValue, outputStream);
			
			// If only a single parameter has been sent, pack it into an array so it can be casted safely ------- FIXME Parameters should actually be a List of Hashmaps (see VWiD json)

			if (parameter instanceof List<?>) {
				List<Object> list = (List<Object>) parameter;
				Object[] parameterArray = new Object[list.size()];
				for (int i = 0; i < list.size(); i++) {
					parameterArray[i] = list.get(i);
				}
				parameter = parameterArray;
			}

			if (!(parameter instanceof Object[])) {
				Object[] parameterArray = new Object[1];
				Object tmp = parameter;
				parameterArray[0] = tmp;
				parameter = parameterArray;
			}
			
			Object result = providerBackend.invokeOperation(path, (Object[]) parameter);

			// Serialize result as json string
			String jsonString = serialize(true, result, (result == null ? null : result.getClass()), null); // any
																											// messages?
			
			// Send response
			sendJSONResponse(outputStream, jsonString);

		} catch (Exception e) {
			sendException(outputStream, e);
		}
	}

	
	/**
	 * Implement "Delete" operation. Deletes any resource under the given path.
	 *
	 * @param path
	 * @param serializedJSONValue If this parameter is not null (basystype),we remove an element from a collection by index / remove from map by key. We assume that the parameter only contains 1 element
	 * @param outputStream
	 */
	public void processBaSysDelete(String path, String serializedJSONValue, PrintWriter outputStream) {
		
		try {

			// Deserialize json body. If parameter is not ex
			Object parameter = extractParameter(path, serializedJSONValue, outputStream);
			
			// Process delete request with or without argument
			if (parameter == null) {
				this.providerBackend.deleteValue(path);
			} else {
				this.providerBackend.deleteValue(path, parameter);
			}

			// Create serialized acknowledgement 
			String jsonString = serialize(true);
			
			// Send response
			sendJSONResponse(outputStream, jsonString);

		} catch (Exception e) {
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
			// Deserialize json body. 
			Object parameter = extractParameter(path, serializedJSONValue, outputStream);
						

			providerBackend.createValue(path, parameter);

			// Create serialized acknowledgement 
			String jsonString = serialize(true);
			
			// Send response
			sendJSONResponse(outputStream, jsonString);

		} catch (Exception e) {
			sendException(outputStream, e);
		}
	}
}