package org.eclipse.basyx.examples.mockup.devicemanager;

import java.util.HashMap;

import org.eclipse.basyx.aas.metamodel.map.AssetAdministrationShell;
import org.eclipse.basyx.aas.metamodel.map.descriptor.AASDescriptor;
import org.eclipse.basyx.aas.metamodel.map.descriptor.ModelUrn;
import org.eclipse.basyx.aas.registration.proxy.AASRegistryProxy;
import org.eclipse.basyx.components.devicemanager.TCPControllableDeviceManagerComponent;
import org.eclipse.basyx.examples.support.directory.ExamplesPreconfiguredDirectory;
import org.eclipse.basyx.models.controlcomponent.ControlComponentChangeListener;
import org.eclipse.basyx.submodel.metamodel.map.SubModel;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.eclipse.basyx.submodel.restapi.SubmodelElementProvider;
import org.eclipse.basyx.vab.manager.VABConnectionManager;
import org.eclipse.basyx.vab.modelprovider.VABElementProxy;
import org.eclipse.basyx.vab.modelprovider.map.VABMapProvider;
import org.eclipse.basyx.vab.protocol.basyx.server.BaSyxTCPServer;
import org.eclipse.basyx.vab.protocol.http.connector.HTTPConnectorProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Example manufacturing device manager code
 * 
 * This example code extends the BaSyxTCPManufacturingDeviceManager by adding a control component sub model to control the managed device.
 *  
 * @author kuhn
 *
 */
public class BaSyxTCPControlManufacturingDeviceManager extends TCPControllableDeviceManagerComponent implements ControlComponentChangeListener {
	
	/**
	 * Initiates a logger using the current class
	 */
	private static final Logger logger = LoggerFactory.getLogger(BaSyxTCPControlManufacturingDeviceManager.class);
	
	/**
	 * AAS server connection
	 */
	protected VABElementProxy aasServerConnection = null;

	
	

	/**
	 * Constructor
	 */
	public BaSyxTCPControlManufacturingDeviceManager(int deviceTCPPort, int ctrlComponentServerPort) {
		// Invoke base constructor
		super(deviceTCPPort, ctrlComponentServerPort);
		
		
		// Set registry that will be used by this service
		setRegistry(new AASRegistryProxy("http://localhost:8080/basys.examples/Components/Directory/SQL"));
		
		
		// Set service connection manager and create AAS server connection
		setConnectionManager(new VABConnectionManager(new ExamplesPreconfiguredDirectory(), new HTTPConnectorProvider()));
		// - Create AAS server connection
		aasServerConnection = getConnectionManager().connectToVABElement("AASServer");
		
		
		// Set AAS server VAB object ID, AAS server URL, and AAS server path prefix
		setAASServerObjectID("AASServer");
		setAASServerURL("http://localhost:8080/basys.examples/Components/BaSys/1.0/aasServer");
	}

	
	/**
	 * Initialize the device, and register it with the backend
	 */
	@Override 
	public void start() {
		// Base implementation
		super.start();
		
		// Create the device AAS and sub model structure
		createDeviceAASAndSubModels();
		
		// Register AAS and sub model descriptors in directory (push AAS descriptor to server)
		getRegistry().register(getAASDescriptor());
	}

	
	
	/**
	 * Create the device AAS and sub model structure
	 */
	protected void createDeviceAASAndSubModels() {
		// Register URNs of managed VAB objects
		addShortcut("AAS",        new ModelUrn("urn:de.FHG:devices.es.iese:aas:1.0:3:x-509#001"));
		addShortcut("Status",     new ModelUrn("urn:de.FHG:devices.es.iese:statusSM:1.0:3:x-509#001"));
		
		// Create device AAS
		AssetAdministrationShell aas = new AssetAdministrationShell();
		// - Populate AAS
		aas.setIdShort("DeviceIDShort");
	
		// The device also brings a sub model structure with an own ID that is being pushed on the server
		// - Create generic sub model and add properties
		SubModel statusSM = new SubModel();
		// - Set submodel ID
		statusSM.setIdShort("Status");
		//   - Property status: indicate device status
		Property statusProp = new Property("offline");
		statusProp.setIdShort("status");
		statusSM.addSubModelElement(statusProp);
		//   - Property statistics: export invocation statistics for every service
		//     - invocations: indicate total service invocations. Properties are not persisted in this example,
		//                    therefore we start counting always at 0.
		Property invocationsProp = new Property(0);
		invocationsProp.setIdShort("invocations");
		statusSM.addSubModelElement(invocationsProp);
		// - Add the submodel to the AAS
		aas.addSubModel(statusSM);
		
		// Push the AAS and submodels to the server
		// - Transfer device AAS to server
		aasServerConnection.createValue("/aas", aas);
		// - Transfer device sub model to server
		aasServerConnection.createValue("/aas/submodels", statusSM);
		
		// Register URNs of control component VAB object
		addShortcut("ControlComponent", new ModelUrn("urn:de.FHG:devices.es.iese:controlComponentSM:1.0:3:x-509#001"));
		
		// Register control component as local sub model
		// - This sub model will stay with the device
		server = new BaSyxTCPServer<>(new VABMapProvider(simpleControlComponent), controlComponentServerPort);
		// - Start local BaSyx/TCP server
		server.start();
	}
	
	@Override
	public void stop() {
		super.stop();
		// End server
		server.stop();
	}
	
	
	/**
	 * Get AAS descriptor for managed device
	 */
	@Override 
	protected AASDescriptor getAASDescriptor() {
		// Create AAS and sub model descriptors
		AASDescriptor aasDescriptor = new AASDescriptor(lookupURN("AAS"), getAASEndpoint(lookupURN("AAS")));
		addSubModelDescriptorURI(aasDescriptor, lookupURN("Status"), "Status");
		
		// Add descriptor for control component sub model
		addSubModelDescriptorURI(aasDescriptor, lookupURN("ControlComponent"),
				"basyx://127.0.0.1:" + controlComponentServerPort);
		
		// Return AAS, sub model descriptors, and added control component sub model descriptor
		return aasDescriptor;
	}

	
	
	/**
	 * Received a string from network
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void onReceive(byte[] rxData) {
		// Do not process null values
		if (rxData == null) return;
		
		// Convert received data to string
		String rxStr = new String(rxData); 
		// - Trim string to remove possibly trailing and leading white spaces
		rxStr = rxStr.trim();
		
		logger.debug("- - --------------- RXMSG:"+rxStr);
		
		// Check what was being received. This check is performed based on a prefix that he device has to provide);
		// - Update of device status
		if (hasPrefix(rxStr, "status:"))
			aasServerConnection.setModelPropertyValue("/aas/submodels/Status/" + SubmodelElementProvider.PROPERTIES + "/status/value", removePrefix(rxStr, "status"));
		// - Device indicates service invocation
		if (hasPrefix(rxStr, "invocation:")) {
			// Start of process
			if (hasPrefix(rxStr, "invocation:start")) {
				// Read and increment invocation counter
				HashMap<String, Object> property = (HashMap<String, Object>) aasServerConnection.getModelPropertyValue("/aas/submodels/Status/" + SubmodelElementProvider.PROPERTIES + "/invocations");
				int invocations = (int) property.get("value");
				aasServerConnection.setModelPropertyValue("/aas/submodels/Status/" + SubmodelElementProvider.PROPERTIES + "/invocations/value", ++invocations);
			}
			
			// End of process
			if (hasPrefix(rxStr, "invocation:end")) {
				// Do nothing for now
			}
		}
		
		// Let base implementation process the message
		super.onReceive(rxData);
	}
}

