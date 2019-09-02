package org.eclipse.basyx.aas.backend.connected.aas.submodelelement.property.file;

import org.eclipse.basyx.aas.api.metamodel.aas.submodelelement.property.file.IFile;
import org.eclipse.basyx.aas.backend.connected.aas.submodelelement.ConnectedDataElement;
import org.eclipse.basyx.aas.metamodel.hashmap.aas.submodelelement.property.MimeType;
import org.eclipse.basyx.aas.metamodel.hashmap.aas.submodelelement.property.Property;
import org.eclipse.basyx.aas.metamodel.hashmap.aas.submodelelement.property.blob.Blob;
import org.eclipse.basyx.aas.metamodel.hashmap.aas.submodelelement.property.file.PathType;
import org.eclipse.basyx.vab.core.proxy.VABElementProxy;
/**
 * "Connected" implementation of IFile
 * @author rajashek
 *
 */
public class ConnectedFile extends ConnectedDataElement implements IFile {
	public ConnectedFile(VABElementProxy proxy) {
		super(proxy);		
	}
	
	@Override
	public void setValue(PathType value) {
		getProxy().setModelPropertyValue(Property.VALUE, value);
		
	}

	@Override
	public PathType getValue() {
		return (PathType)getProxy().getModelPropertyValue(Property.VALUE);
	}

	@Override
	public void setMimeType(MimeType mimeType) {
		getProxy().setModelPropertyValue(Blob.MIMETYPE, mimeType);
		
	}

	@Override
	public MimeType getMimeType() {
		return (MimeType)getProxy().getModelPropertyValue(Blob.MIMETYPE);
	}

}
