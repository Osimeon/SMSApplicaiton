package org.smslib;

/**
 * Interface of the callback class used by SMSLib. SMSLib will call this method
 * when it detects an inbound voice call. 
 * @see Service#setCallNotification(ICallNotification)
 */
public interface ICallNotification{
	/**
	 * This method will be called by SMSLib upon a voice call reception. 
	 * @param gateway - The gateway which received the voice call.
	 * @param callerId - The caller-id of the call.
	 */
	void process(final AGateway gateway, final String callerId);
}
