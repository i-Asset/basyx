package org.eclipse.basyx.extensions.aas.directory.tagged.restapi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.basyx.aas.registration.restapi.DirectoryModelProvider;
import org.eclipse.basyx.extensions.aas.directory.tagged.api.TaggedAASDescriptor;
import org.eclipse.basyx.extensions.aas.directory.tagged.map.MapTaggedDirectory;
import org.eclipse.basyx.vab.exception.provider.ProviderException;
import org.eclipse.basyx.vab.modelprovider.VABPathTools;

public class TaggedDirectoryProvider extends DirectoryModelProvider {
	private MapTaggedDirectory directory;
	public static final String PREFIX = "api/v1/directory";
	public static final String API_ACCESS = "?tags=";

	public TaggedDirectoryProvider() {
		this(new MapTaggedDirectory(new HashMap<>(), new HashMap<>()));
	}

	public TaggedDirectoryProvider(MapTaggedDirectory directory) {
		super(directory);
		this.directory = directory;
	}

	@Override
	public Object getModelPropertyValue(String path) throws ProviderException {
		path = VABPathTools.stripSlashes(path);
		if (path.startsWith(PREFIX)) {
			return directory.lookupTags(extractTags(path));
		} else {
			return super.getModelPropertyValue(path);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void createValue(String path, Object newEntity) throws ProviderException {
		path = VABPathTools.stripSlashes(path);
		if (path.startsWith(PREFIX)) {
			directory.register(TaggedAASDescriptor.createAsFacade((Map<String, Object>) newEntity));
		} else {
			super.createValue(path, newEntity);
		}
	}

	private Set<String> extractTags(String path) {
		path = VABPathTools.stripSlashes(path);
		path = path.replace(PREFIX, "");

		// Paths now does only contain ?tags=a,b,c
		path = path.replace(API_ACCESS, "");
		return Arrays.stream(path.split(",")).collect(Collectors.toSet());
	}

}
