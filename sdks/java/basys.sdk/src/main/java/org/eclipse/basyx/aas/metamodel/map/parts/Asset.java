package org.eclipse.basyx.aas.metamodel.map.parts;

import java.util.Collection;
import java.util.Map;

import org.eclipse.basyx.aas.metamodel.api.parts.asset.AssetKind;
import org.eclipse.basyx.aas.metamodel.api.parts.asset.IAsset;
import org.eclipse.basyx.submodel.metamodel.api.dataspecification.IEmbeddedDataSpecification;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IIdentifier;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.api.qualifier.IAdministrativeInformation;
import org.eclipse.basyx.submodel.metamodel.api.reference.IReference;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyElements;
import org.eclipse.basyx.submodel.metamodel.map.modeltype.ModelType;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.AdministrativeInformation;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.HasDataSpecification;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.Identifiable;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.LangStrings;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.Referable;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;
import org.eclipse.basyx.vab.model.VABModelMap;

/**
 * Asset class as described in DAAS document<br/>
 * An Asset describes meta data of an asset that is represented by an AAS. <br/>
 * The asset may either represent an asset type or an asset instance.<br/>
 * The asset has a globally unique identifier plus � if needed � additional
 * domain specific (proprietary) identifiers.
 * 
 * @author kuhn, elsheikh, schnicke
 *
 */

public class Asset extends VABModelMap<Object> implements IAsset {
	public static String ASSETIDENTIFICATIONMODEL = "assetIdentificationModel";
	public static String MODELTYPE = "Asset";
	public static final String KIND = "kind";
	public static final String BILLOFMATERIAL = "billOfMaterial";

	/**
	 * Constructor
	 */
	public Asset() {
		// Add model type
		putAll(new ModelType(MODELTYPE));

		// Add qualifiers
		putAll(new HasDataSpecification());
		putAll(new Identifiable());

		// Default values
		put(ASSETIDENTIFICATIONMODEL, null);
		put(KIND, null);
	}

	/**
	 * 
	 * @param submodel
	 *            A reference to a Submodel that defines the handling of additional
	 *            domain specific (proprietary) Identifiers for the asset like e.g.
	 *            serial number etc.
	 */
	public Asset(Reference submodel) {
		this();
		put(ASSETIDENTIFICATIONMODEL, submodel);
	}

	/**
	 * Creates a Asset object from a map
	 * 
	 * @param obj
	 *            a Asset object as raw map
	 * @return a Asset object, that behaves like a facade for the given map
	 */
	public static Asset createAsFacade(Map<String, Object> map) {
		if (map == null) {
			return null;
		}
		Asset ret = new Asset();
		ret.setMap(map);
		return ret;
	}

	@Override
	public Collection<IReference> getDataSpecificationReferences() {
		return HasDataSpecification.createAsFacade(this).getDataSpecificationReferences();
	}

	public void setDataSpecificationReferences(Collection<IReference> ref) {
		HasDataSpecification.createAsFacade(this).setDataSpecificationReferences(ref);
	}

	@Override
	public Collection<IEmbeddedDataSpecification> getEmbeddedDataSpecifications() {
		return HasDataSpecification.createAsFacade(this).getEmbeddedDataSpecifications();
	}

	public void setEmbeddedDataSpecifications(Collection<IEmbeddedDataSpecification> embeddedDataSpecifications) {
		HasDataSpecification.createAsFacade(this).setEmbeddedDataSpecifications(embeddedDataSpecifications);
	}

	@Override
	public AssetKind getAssetKind() {
		return AssetKind.fromString((String) get(Asset.KIND));
	}

	public void setAssetKind(AssetKind kind) {
		put(Asset.KIND, kind.toString());
	}

	@Override
	public IAdministrativeInformation getAdministration() {
		return Identifiable.createAsFacade(this, getKeyElement()).getAdministration();
	}

	@Override
	public IIdentifier getIdentification() {
		return Identifiable.createAsFacade(this, getKeyElement()).getIdentification();
	}

	public void setAdministration(AdministrativeInformation information) {
		Identifiable.createAsFacade(this, getKeyElement()).setAdministration(information);
	}

	public void setIdentification(IdentifierType idType, String id) {
		Identifiable.createAsFacade(this, getKeyElement()).setIdentification(idType, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public IReference getAssetIdentificationModel() {
		return Reference.createAsFacade((Map<String, Object>) get(Asset.ASSETIDENTIFICATIONMODEL));
	}

	public void setAssetIdentificationModel(IReference submodel) {
		put(Asset.ASSETIDENTIFICATIONMODEL, submodel);
	}
	@Override
	public String getIdShort() {
		return Referable.createAsFacade(this, getKeyElement()).getIdShort();
	}

	@Override
	public String getCategory() {
		return Referable.createAsFacade(this, getKeyElement()).getCategory();
	}

	@Override
	public LangStrings getDescription() {
		return Referable.createAsFacade(this, getKeyElement()).getDescription();
	}

	@Override
	public IReference getParent() {
		return Referable.createAsFacade(this, getKeyElement()).getParent();
	}

	public void setIdShort(String idShort) {
		Referable.createAsFacade(this, getKeyElement()).setIdShort(idShort);
	}

	public void setCategory(String category) {
		Referable.createAsFacade(this, getKeyElement()).setCategory(category);
	}

	public void setDescription(LangStrings description) {
		Referable.createAsFacade(this, getKeyElement()).setDescription(description);
	}

	public void setParent(IReference obj) {
		Referable.createAsFacade(this, getKeyElement()).setParent(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IReference getBillOfMaterial() {
		return Reference.createAsFacade((Map<String, Object>) get(Asset.BILLOFMATERIAL));
	}

	public void setBillOfMaterial(Reference reference) {
		put(Asset.BILLOFMATERIAL, reference);
	}
	
	private KeyElements getKeyElement() {
		return KeyElements.ASSET;
	}

	@Override
	public IReference getReference() {
		return Identifiable.createAsFacade(this, getKeyElement()).getReference();
	}

}
