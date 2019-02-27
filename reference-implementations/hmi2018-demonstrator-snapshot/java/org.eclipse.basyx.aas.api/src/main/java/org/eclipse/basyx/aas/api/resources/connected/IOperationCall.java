package org.eclipse.basyx.aas.api.resources.connected;

public interface IOperationCall {
	public void success(Object result);
	public void exception(Object e);
	public void timeout(Object e);
}
