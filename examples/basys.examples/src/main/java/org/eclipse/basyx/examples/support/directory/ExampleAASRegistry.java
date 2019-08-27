package org.eclipse.basyx.examples.support.directory;

import org.eclipse.basyx.aas.api.modelurn.ModelUrn;
import org.eclipse.basyx.aas.metamodel.hashmap.aas.descriptor.AASDescriptor;
import org.eclipse.basyx.aas.metamodel.hashmap.aas.descriptor.SubmodelDescriptor;
import org.eclipse.basyx.aas.metamodel.hashmap.aas.identifier.IdentifierType;
import org.eclipse.basyx.testsuite.support.vab.stub.AASRegistryStub;

public class ExampleAASRegistry extends AASRegistryStub {

	@Override
	public ExampleAASRegistry addAASMapping(String rawUrn, String endpoint) {
		AASDescriptor aasDescriptor = new AASDescriptor(rawUrn, IdentifierType.URI, endpoint);
		ModelUrn aasUrn = new ModelUrn(rawUrn);
		register(aasUrn, aasDescriptor);

		return this;
	}

	public ExampleAASRegistry addOnlySubmodelMapping(String submodelid, String endpoint) {
		AASDescriptor aasDes = new AASDescriptor();
		SubmodelDescriptor smDes = new SubmodelDescriptor(submodelid, IdentifierType.URI, endpoint);
		aasDes.addSubmodelDescriptor(smDes);
		ModelUrn aasUrn = new ModelUrn(submodelid);
		register(aasUrn, aasDes);
		return this;
	}

	public ExampleAASRegistry addSubmodelMapping(String rawurn, String submodelid, String endpoint) {
		AASDescriptor aasDescriptor = new AASDescriptor(rawurn, IdentifierType.URI, endpoint);
		SubmodelDescriptor smDes = new SubmodelDescriptor(submodelid, IdentifierType.URI, endpoint);
		aasDescriptor.addSubmodelDescriptor(smDes);
		ModelUrn aasUrn = new ModelUrn(rawurn);
		register(aasUrn, aasDescriptor);
		return this;
	}


}
