package org.eclipse.basyx.aas.impl.metamodel.facades;

import java.util.Map;

import org.eclipse.basyx.aas.api.metamodel.aas.policypoints.IAccessControlPolicyPoints;
import org.eclipse.basyx.aas.impl.metamodel.hashmap.aas.policypoints.AccessControlPolicyPoints;
/**
 * Facade providing access to a map containing the AccessControlPolicyPoints structure
 * @author rajashek
 *
 */

public class AccessControlPolicyPointsFacade implements IAccessControlPolicyPoints{

	private Map<String, Object> map;
	
	public AccessControlPolicyPointsFacade(Map<String, Object> map) {
		super();
		this.map = map;
	}

	@Override
	public void setPolicyAdministrationPoint(Object obj) {
		map.put(AccessControlPolicyPoints.POLICYADMINISTRATIONPOINT, obj);
		
	}

	@Override
	public Object getPolicyAdministrationPoint() {
		return map.get(AccessControlPolicyPoints.POLICYADMINISTRATIONPOINT);
	}

	@Override
	public void setPolicyDecisionPoint(Object obj) {
		map.put(AccessControlPolicyPoints.POLICYDECISIONPOINT, obj);
		
	}

	@Override
	public Object getPolicyDecisionPoint() {
		return map.get(AccessControlPolicyPoints.POLICYDECISIONPOINT);
	}

	@Override
	public void setPolicyEnforcementPoint(Object obj) {
		map.put(AccessControlPolicyPoints.POLICYENFORECEMENTPOINT, obj);
		
	}

	@Override
	public Object getPolicyEnforcementPoint() {
		return map.get(AccessControlPolicyPoints.POLICYENFORECEMENTPOINT);
	}

	@Override
	public void setPolicyInformationPoints(Object obj) {
		map.put(AccessControlPolicyPoints.POLICYINFORMATIONPOINTS, obj);
		
	}

	@Override
	public Object getPolicyInformationPoints() {
		return map.get(AccessControlPolicyPoints.POLICYINFORMATIONPOINTS);
	}

}