package org.eclipse.basyx.aas.api.metamodel.aas.policypoints;

/**
 * Interface for AccessControlPolicyPoints
 * 
 * @author rajashek
 *
 */
public interface IAccessControlPolicyPoints {
	public Object getPolicyAdministrationPoint();

	public Object getPolicyDecisionPoint();

	public Object getPolicyEnforcementPoint();

	public Object getPolicyInformationPoints();
}
