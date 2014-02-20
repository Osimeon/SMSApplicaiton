package com.mw.sms.dbms;

public class Messages {
	int contact;
	String message;
	String received;
	String status;
	String msgType;
	int messageId;
	
	/**
	 * Empty Constructor
	 */
	public Messages(){
		
	}
	
	/**
	 * Parametized Constructor
	 * @param _contact
	 * @param _message
	 * @param _received
	 * @param _status
	 */
	public Messages(int _contact, String _message, String _received, String _status, String _type){
		this.contact = _contact;
		this.message = _message;
		this.received = _received;
		this.status = _status;
		this.msgType = _type;
	}
	
	/**
	 * @return Contact Number
	 */
	public int getContact() {
		return contact;
	}
	
	/**
	 * @return Message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @return Date Received
	 */
	public String getReceived() {
		return received;
	}
	
	/**
	 * @return Message Status (New, Old)
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * @return Message Type (Inbox, Outbox, Draft)
	 */
	public String getMsgType() {
		return msgType;
	}
	
	/** 
	 * @param contact
	 */
	public void setContact(int contact) {
		this.contact = contact;
	}
	
	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @param received
	 */
	public void setReceived(String received) {
		this.received = received;
	}
	
	/**
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/*
	 * @param msgType
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId; 
	}
}
