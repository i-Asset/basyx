package org.eclipse.basyx.models.manufacturing.process.product;

import org.eclipse.basyx.aas.metamodel.hashmap.aas.AssetAdministrationShell;




/**
 * A product Asset Administration Shell example
 * 
 * - Every product has a quality sub model that tracks the product quality
 * 
 * @author kuhn
 *
 */
public class ProductAAS extends AssetAdministrationShell {

	
	/**
	 * Version number of serialized instances
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Associated product quality sub model
	 */
	protected ProductQualitySubmodel qualitySubModelInstance = null;
	
	
	
	
	/**
	 * Constructor
	 */
	public ProductAAS(String id) {
		// Store unique product id
		this.setId(id);
		
		// Create sub model instances
		qualitySubModelInstance = new ProductQualitySubmodel(""+id+"-Quality"); 
	}
	
	
	/**
	 * Get quality sub model
	 */
	public ProductQualitySubmodel getQualitySubModel() {
		return qualitySubModelInstance;
	}
}
