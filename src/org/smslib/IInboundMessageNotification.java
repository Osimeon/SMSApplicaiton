package org.smslib;

import org.smslib.Message.MessageTypes;

/**
 * Interface of the callback class used by SMSLib. SMSLib will call this method
 * when it detects an inbound message.
 * 
 * @see Service#setInboundMessageNotification(IInboundMessageNotification)
 */
public interface IInboundMessageNotification{
	/**
	 * This method will be called by SMSLib upon receiving an sms message.
	 * 
	 * @param gateway
	 *            The gateway which received the message.
	 * @param msgType
	 *            The message type.
	 * @param msg
	 *            The message received.
	 */
	void process(final AGateway gateway, final MessageTypes msgType, final InboundMessage msg);
}
