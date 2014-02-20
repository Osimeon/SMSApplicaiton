package org.smslib;

/**
 * Interface of the callback class used by SMSLib. SMSLib will call this method when finds an orphaned inbound message. 
 * @see Service#setOrphanedMessageNotification(IOrphanedMessageNotification)
 */
public interface IOrphanedMessageNotification{
	/**
	 * This method will be called by SMSLib upon detection of an orphaned message part. 
	 * @param gateway - The gateway from which the orphaned message part was received.
	 * @param msg - The actual orphaned message. This is the actual message part
	 *              and not the complete message!
	 * @return True if the application agrees to delete the message part. False
	 *         if the application wishes to leave it as is.
	 */
	boolean process(final AGateway gateway, final InboundMessage msg);
}
