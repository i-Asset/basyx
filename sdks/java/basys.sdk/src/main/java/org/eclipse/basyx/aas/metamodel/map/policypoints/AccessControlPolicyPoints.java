package org.eclipse.basyx.aas.metamodel.map.policypoints;

import java.util.Map;

import org.eclipse.basyx.aas.metamodel.api.policypoints.IAccessControlPolicyPoints;
import org.eclipse.basyx.vab.model.VABModelMap;

/**
 * Security class
 * 
 * @author elsheikh
 *
 */
public class AccessControlPolicyPoints extends VABModelMap<Object> implements IAccessControlPolicyPoints {
	public static final String POLICYADMINISTRATIONPOINT = "policyAdministrationPoint";
	public static final String POLICYDECISIONPOINT = "policyDecisionPoint";
	public static final String POLICYENFORECEMENTPOINT = "policyEnforcementPoint";
	public static final String POLICYINFORMATIONPOINTS = "policyInformationPoints";

	/**
	 * Constructor
	 */
	public AccessControlPolicyPoints() {
		// Default values
		put(POLICYADMINISTRATIONPOINT, null);
		put(POLICYDECISIONPOINT, null);
		put(POLICYENFORECEMENTPOINT, null);

		put(POLICYINFORMATIONPOINTS, null);
	}

	/**
	 * Creates a DataSpecificationIEC61360 object from a map
	 * 
	 * @param obj
	 *            a DataSpecificationIEC61360 object as raw map
	 * @return a DataSpecificationIEC61360 object, that behaves like a facade for
	 *         the given map
	 */
	public static AccessControlPolicyPoints createAsFacade(Map<String, Object> map) {
		if (map == null) {
			return null;
		}

		AccessControlPolicyPoints ret = new AccessControlPolicyPoints();
		ret.setMap(map);
		return ret;
	}

	public void setPolicyAdministrationPoint(Object obj) {
		put(AccessControlPolicyPoints.POLICYADMINISTRATIONPOINT, obj);
	}

	@Override
	public Object getPolicyAdministrationPoint() {
		return get(AccessControlPolicyPoints.POLICYADMINISTRATIONPOINT);
	}

	public void setPolicyDecisionPoint(Object obj) {
		put(AccessControlPolicyPoints.POLICYDECISIONPOINT, obj);
	}

	@Override
	public Object getPolicyDecisionPoint() {
		return get(AccessControlPolicyPoints.POLICYDECISIONPOINT);
	}

	public void setPolicyEnforcementPoint(Object obj) {
		put(AccessControlPolicyPoints.POLICYENFORECEMENTPOINT, obj);
	}

	@Override
	public Object getPolicyEnforcementPoint() {
		return get(AccessControlPolicyPoints.POLICYENFORECEMENTPOINT);
	}

	public void setPolicyInformationPoints(Object obj) {
		put(AccessControlPolicyPoints.POLICYINFORMATIONPOINTS, obj);
	}

	@Override
	public Object getPolicyInformationPoints() {
		return get(AccessControlPolicyPoints.POLICYINFORMATIONPOINTS);
	}

}
