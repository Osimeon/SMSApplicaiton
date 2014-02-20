package org.smslib;

/**
 * Interface of the callback class used by SMSLib. SMSLib will call this method
 * whenever a gateway retrieves a message from the Queue and before it tries to
 * send it out.
 * 
 * @see Service#setQueueSendingNotification(IQueueSendingNotification)
 */
public interface IQueueSendingNotification{
	/**
	 * This method will be called by SMSLib whenever a gateway retrieves a
	 * message from the Queue and before it tries to send it out.
	 * 
	 * @param gateway
	 *            The gateway from which the message is about to be sent from.
	 * @param msg
	 *            The outbound message ready to be sent.
	 */
	void process(final AGateway gateway, final OutboundMessage msg);
}
