package org.smslib;

import org.smslib.AGateway.GatewayStatuses;

/**
 * Interface of the callback class used by SMSLib. SMSLib will call this method
 * whenever a gateway changes status. 
 * @see Service#setGatewayStatusNotification(IGatewayStatusNotification)
 */
public interface IGatewayStatusNotification{
	/**
	 * This method will be called by SMSLib whenever a gateway changes its
	 * status.
	 * 
	 * @param gateway
	 *            The Gateway involved in the status change.
	 * @param oldStatus
	 *            The old status.
	 * @param newStatus
	 *            The new status.
	 */
	void process(final AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus);
}
