package org.eclipse.basyx.aas.registration.proxy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.basyx.aas.metamodel.map.descriptor.AASDescriptor;
import org.eclipse.basyx.aas.metamodel.map.descriptor.SubmodelDescriptor;
import org.eclipse.basyx.aas.registration.api.IAASRegistryService;
import org.eclipse.basyx.aas.registration.restapi.DirectoryModelProvider;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IIdentifier;
import org.eclipse.basyx.vab.coder.json.connector.JSONConnector;
import org.eclipse.basyx.vab.directory.proxy.VABDirectoryProxy;
import org.eclipse.basyx.vab.exception.provider.ProviderException;
import org.eclipse.basyx.vab.modelprovider.VABElementProxy;
import org.eclipse.basyx.vab.modelprovider.VABPathTools;
import org.eclipse.basyx.vab.modelprovider.api.IModelProvider;
import org.eclipse.basyx.vab.protocol.http.connector.HTTPConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Local proxy class that hides HTTP calls to BaSys registry
 * 
 * @author kuhn, schnicke
 *
 */
public class AASRegistryProxy extends VABDirectoryProxy implements IAASRegistryService {
	
	private static Logger logger = LoggerFactory.getLogger(AASRegistryProxy.class);

	/**
	 * Constructor for an AAS registry proxy based on a HTTP connection
	 * 
	 * @param registryUrl
	 *            The endpoint of the registry with a HTTP-REST interface
	 */
	public AASRegistryProxy(String registryUrl) {
		this(new JSONConnector(new HTTPConnector(registryUrl)));
	}

	/**
	 * Constructor for an AAS registry proxy based on its model provider
	 * 
	 * @param provider
	 *            A model provider for the actual registry
	 */
	public AASRegistryProxy(IModelProvider provider) throws ProviderException {
		super(createProxy(provider));
	}

	private static VABElementProxy createProxy(IModelProvider provider) {
		return new VABElementProxy(DirectoryModelProvider.PREFIX, provider);
	}

	/**
	 * Register AAS descriptor in registry, delete old registration
	 */
	@Override
	public void register(AASDescriptor deviceAASDescriptor) throws ProviderException {
		// Add a mapping from the AAS id to the serialized descriptor
		try {
			String encodedId = VABPathTools.encodePathElement(deviceAASDescriptor.getIdentifier().getId());

			// Typically, VAB SET should not create new entries. Nevertheless, the registry
			// API is defined to do it.
			provider.setModelPropertyValue(encodedId, deviceAASDescriptor);
		} catch (Exception e) {
			if (e instanceof ProviderException) {
				throw (ProviderException) e;
			} else {
				throw new ProviderException(e);
			}
		}
	}

	/**
	 * Delete AAS descriptor from registry
	 */
	@Override
	public void delete(IIdentifier aasIdentifier) throws ProviderException {
		try {
			this.removeMapping(URLEncoder.encode(aasIdentifier.getId(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error("Could not encode URL. This should not happen");
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Lookup device AAS
	 */
	@Override @SuppressWarnings("unchecked")
	public AASDescriptor lookupAAS(IIdentifier aasIdentifier) throws ProviderException {
		try {
			Object result = provider.getModelPropertyValue(URLEncoder.encode(aasIdentifier.getId(), "UTF-8"));
			return new AASDescriptor((Map<String, Object>) result);
		} catch (Exception e) {
			if (e instanceof ProviderException) {
				throw (ProviderException) e;
			} else {
				throw new ProviderException(e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AASDescriptor> lookupAll() throws ProviderException {
		try {
			Object result = provider.getModelPropertyValue("");
			Collection<?> descriptors = (Collection<?>) result;
			return descriptors.stream().map(x -> new AASDescriptor((Map<String, Object>) x)).collect(Collectors.toList());
		} catch (Exception e) {
			if (e instanceof ProviderException) {
				throw (ProviderException) e;
			} else {
				throw new ProviderException(e);
			}
		}
	}

	@Override
	public void register(IIdentifier aas, SubmodelDescriptor smDescriptor) throws ProviderException {
		try {
			// Typically, VAB SET should not create new entries. Nevertheless, the registry
			// API is defined to do it.
			provider.setModelPropertyValue(VABPathTools.concatenatePaths(buildSubmodelPath(aas), URLEncoder.encode(smDescriptor.getIdentifier().getId(), "UTF-8")), smDescriptor);
		} catch (Exception e) {
			if (e instanceof ProviderException) {
				throw (ProviderException) e;
			} else {
				throw new ProviderException(e);
			}
		}
	}

	@Override
	public void delete(IIdentifier aasId, IIdentifier smId) throws ProviderException {
		try {
			provider.deleteValue(VABPathTools.concatenatePaths(buildSubmodelPath(aasId), URLEncoder.encode(smId.getId(), "UTF-8")));
		} catch (Exception e) {
			if (e instanceof ProviderException) {
				throw (ProviderException) e;
			} else {
				throw new ProviderException(e);
			}
		}
	}

	private String buildSubmodelPath(IIdentifier aas) throws ProviderException {
		// Encode id to handle usage of reserved symbols, e.g. /
		String encodedAASId = VABPathTools.encodePathElement(aas.getId());
		return VABPathTools.concatenatePaths(encodedAASId, DirectoryModelProvider.SUBMODELS);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubmodelDescriptor> lookupSubmodels(IIdentifier aasId) throws ProviderException {
		try {
			Object result = provider.getModelPropertyValue(VABPathTools.concatenatePaths(buildSubmodelPath(aasId)));
			Collection<?> descriptors = (Collection<?>) result;
			return descriptors.stream().map(x -> new SubmodelDescriptor((Map<String, Object>) x)).collect(Collectors.toList());
		} catch (Exception e) {
			if (e instanceof ProviderException) {
				throw (ProviderException) e;
			} else {
				throw new ProviderException(e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public SubmodelDescriptor lookupSubmodel(IIdentifier aasId, IIdentifier smId) throws ProviderException {
		try {
			Object result = provider.getModelPropertyValue(VABPathTools.concatenatePaths(buildSubmodelPath(aasId), URLEncoder.encode(smId.getId(), "UTF-8")));
			return new SubmodelDescriptor((Map<String, Object>) result);
		} catch (Exception e) {
			if (e instanceof ProviderException) {
				throw (ProviderException) e;
			} else {
				throw new ProviderException(e);
			}
		}
	}
}

