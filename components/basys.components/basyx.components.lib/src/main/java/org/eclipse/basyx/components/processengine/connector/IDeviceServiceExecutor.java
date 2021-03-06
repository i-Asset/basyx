package org.eclipse.basyx.components.processengine.connector;

import java.util.List;

/**
 * Interface for the device service executor
 * 
 * @author zhangzai
 *
 */
public interface IDeviceServiceExecutor {

	/**
	 * 
	 * @param servicename     - name of the operation in the aas
	 * @param serviceProvider - raw urn of the device aas
	 * @param submodelid      - id of the sub-model for asscess
	 * @param params          - parameters needed by the operation in list
	 * @return - return number if operation is executed succesfully
	 * @throws Exception
	 */
	public Object executeService( String servicename, String serviceProvider,String submodelid,  List<Object> params) throws Exception;
}
