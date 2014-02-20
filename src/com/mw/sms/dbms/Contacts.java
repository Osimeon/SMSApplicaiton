package com.mw.sms.dbms;

public class Contacts {
	String contactName;
	String phoneNumber;
	int ID;
	
	/**
	 * Empty Constructor
	 */
	public Contacts(){
		
	}
	
	/**
	 * Parametized Constructor	
	 * @param _name
	 * @param _phone
	 */
	public Contacts(String _name, String _phone){
		this.contactName = _name;
		this.phoneNumber = _phone;
	}
	
	/**
	 * @return Contact Name
	 */
	public String getContactName() {
		return contactName;
	}
	
	/**
	 * @return Phone Number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * @param contactName
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	/**
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}
