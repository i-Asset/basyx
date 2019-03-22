package org.eclipse.basyx.aas.api.metamodel.aas.qualifier.qualifiable;

import java.util.Set;

import org.eclipse.basyx.aas.api.metamodel.aas.reference.IReference;
/**
 * Interface for Formula
 * @author rajashek
 *
*/
public interface IFormula {
	
	public void setDependsOn(Set<IReference> dependsOn);
	
	public  Set<IReference> getDependsOn();

}
