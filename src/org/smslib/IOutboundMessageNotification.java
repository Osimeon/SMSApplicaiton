package org.smslib;

/**
 * Interface of the callback class used by SMSLib. SMSLib will call this method
 * when it sends (or fails to send) an outbound message from its queue.
 * 
 * @see Service#setOutboundMessageNotification(IOutboundMessageNotification)
 */
public interface IOutboundMessageNotification{
	/**
	 * This method will be called by SMSLib upon sending or when it failed to
	 * send a message. Please note that this method is only called when you send
	 * messages via SMSLib queue manager.
	 * 
	 * @param gateway
	 *            The gateway from which the message was sent.
	 * @param msg
	 *            The actual outbound message. This messages has its fields
	 *            updated according to whether SMSLib has manage to send it or
	 *            failed to send it.
	 */
	void process(final AGateway gateway, final OutboundMessage msg);
}
