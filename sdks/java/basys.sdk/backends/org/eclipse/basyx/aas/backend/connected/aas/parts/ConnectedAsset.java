package org.eclipse.basyx.aas.backend.connected.aas.parts;

import java.util.HashSet;

import org.eclipse.basyx.aas.api.metamodel.aas.identifier.IIdentifier;
import org.eclipse.basyx.aas.api.metamodel.aas.parts.IAsset;
import org.eclipse.basyx.aas.api.metamodel.aas.qualifier.IAdministrativeInformation;
import org.eclipse.basyx.aas.api.metamodel.aas.reference.IReference;
import org.eclipse.basyx.aas.backend.connected.ConnectedElement;
import org.eclipse.basyx.aas.backend.connected.facades.ConnectedHasDataSpecificationFacade;
import org.eclipse.basyx.aas.backend.connected.facades.ConnectedHasKindFacade;
import org.eclipse.basyx.aas.backend.connected.facades.ConnectedIdentifiableFacade;
import org.eclipse.basyx.aas.metamodel.hashmap.aas.parts.Asset;
import org.eclipse.basyx.vab.core.proxy.VABElementProxy;
/**
 * "Connected" implementation of IAsset
 * @author rajashek
 *
 */
public class ConnectedAsset extends ConnectedElement implements IAsset {
	
	public ConnectedAsset(String path, VABElementProxy proxy) {
		super(path, proxy);
		
	}
	
	@Override
	public HashSet<IReference> getDataSpecificationReferences() {
		return new ConnectedHasDataSpecificationFacade(getPath(),getProxy()).getDataSpecificationReferences();
	}
	
	@Override
	public void setDataSpecificationReferences(HashSet<IReference> ref) {
		new ConnectedHasDataSpecificationFacade(getPath(),getProxy()).setDataSpecificationReferences(ref);
		
	}
	
	@Override
	public String getHasKindReference() {
		return  new ConnectedHasKindFacade(getPath(),getProxy()).getHasKindReference();
	}

	@Override
	public void setHasKindReference(String kind) {
		 new ConnectedHasKindFacade(getPath(),getProxy()).setHasKindReference(kind);
		
	}
	
	@Override
	public IAdministrativeInformation getAdministration() {
		return new ConnectedIdentifiableFacade(getPath(),getProxy()).getAdministration();
	}

	@Override
	public IIdentifier getIdentification() {
		return new ConnectedIdentifiableFacade(getPath(),getProxy()).getIdentification();
	}

	@Override
	public void setAdministration(String version, String revision) {
		 new ConnectedIdentifiableFacade(getPath(),getProxy()).setAdministration(version, revision);;
		
	}

	@Override
	public void setIdentification(String idType, String id) {
		 new ConnectedIdentifiableFacade(getPath(),getProxy()).setIdentification(idType, id);
		
	}

	@Override
	public IReference getAssetIdentificationModel() {
		return (IReference)getProxy().readElementValue(constructPath(Asset.ASSETIDENTIFICATIONMODEL));
	}

	@Override
	public void setAssetIdentificationModel(IReference submodel) {
		getProxy().updateElementValue(constructPath(Asset.ASSETIDENTIFICATIONMODEL), submodel);
		
	}

}