package com.mw.sms.dbms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBMS {	
	Connection c = null;
	Statement stmt = null;
	
	/**
	 * Default Constructor
	 */
	public DBMS(){
		
	}
	
	/**
	 * Open Connection To Database And Return
	 * @return Connection
	 */
	public Connection getConnection(){
		try{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:smsdb.db");
		}
		catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return c;
	}
	
	
	/**
	 * Contacts Table SQL
	 * @return String
	 */
	public String contactsTable(){
		String sql = "CREATE TABLE CONTACTS " +
					 "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
					 " NAME TEXT NOT NULL, " +
					 " PHONENUMBER TEXT NOT NULL)";
		return sql;
	}
	
	/**
	 * Messages Table SQL
	 * @return
	 */
	public String messagesTable(){
		String sql = "CREATE TABLE MESSAGES " +
					  "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
					  " MESSAGE TEXT NOT NULL, " +
					  " CONTACT TEXT NOT NULL, " +
					  " MSGDATE TEXT NOT NULL, " +
					  " STATUS TEXT NOT NULL, " +
					  " MESSAGETYPE TEXT NOT NULL)";
		return sql;
	}
	
	/**
	 * Query Execution For Insert, Delete Operations
	 * @param statement
	 * @return boolean
	 */
	public boolean executeQuery(String statement){
		try {
			stmt = getConnection().createStatement();
			stmt.executeUpdate(statement);
			stmt.close();
			c.close();
		} 
		catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return true;
	}
	
	/**
	 * Query Execution For Reads, Update Operations
	 * @param c (Connection Object)
	 * @return Statement Object
	 */
	public Statement performQuery(Connection c){
		Statement stmt = null;
		try{
			stmt = c.createStatement();
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return stmt;
	}
	
	/**
	 * ResultSet Object For Select Operations
	 * @param stmt
	 * @param sql
	 * @return
	 */
	public ResultSet getResultSet(Statement stmt, String sql){
		ResultSet mResult = null;
		try{
			mResult = stmt.executeQuery(sql);
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return mResult;
	}
	
	/**
	 * Initialize DBMS
	 */
	public void init(){
		this.executeQuery(this.contactsTable());
		this.executeQuery(this.messagesTable());
	}
	
	/**
	 * @param contact (Object)
	 * @return boolean
	 */
	public boolean insertContact(Contacts contact){
		String name = contact.getContactName();
		String phone = contact.getPhoneNumber();
		try{
			String sql = "INSERT INTO CONTACTS(NAME, PHONENUMBER) " +
						 "VALUES ('" + name + "'," + "'" + phone + "'" +")";
			this.executeQuery(sql);
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return true;
	}

	/**
	 * @param message
	 * @return boolean
	 */
	public boolean insertMessage(Messages message){
		int contact = message.getContact();
		String textMessage = message.getMessage();
		String received = message.getReceived();
		String status = message.getStatus();
		String type = message.getMsgType();
		
		try{
			String sql = "INSERT INTO MESSAGES(MESSAGE, CONTACT, MSGDATE, STATUS, MESSAGETYPE) " +
						 "VALUES ('" + textMessage + "'" + "," + "'" + contact + "'" + "," + "'" + received +"'" + "," + "'" + status + "'" + "," + "'" + type + "'" +")";
			this.executeQuery(sql);
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return true;
	}
	
	/**
	 * @return Contacts
	 */
	public List<Contacts> getContacts(){
		List<Contacts> mContacts = new ArrayList<Contacts>();
		try{
			String sql = "SELECT * FROM CONTACTS";
			ResultSet rs = this.getResultSet(this.performQuery(this.getConnection()), sql);
			while (rs.next()){	
				Contacts contact = new Contacts();
				contact.setID(rs.getInt(1));
				contact.setContactName(rs.getString(2));
				contact.setPhoneNumber(rs.getString(3));
				mContacts.add(contact);
			}
			rs.close();
		}	
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return mContacts;
	}
	
	/**
	 * Return A Single Contact Entry (Object Type)
	 * @param contactId
	 * @return Contact Object
	 */
	public Contacts getContact(int contactId){
		Contacts mContact = new Contacts();
		try{
			String sql = "SELECT * FROM CONTACTS WHERE ID = " + contactId;
			ResultSet rs = this.getResultSet(this.performQuery(this.getConnection()), sql);
			while(rs.next()){				
				mContact.setID(rs.getInt(1));
				mContact.setContactName(rs.getString(2));
				mContact.setPhoneNumber(rs.getString(3));
			}
			rs.close();
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return mContact;
	}
	
	public Messages getMessage(int messageId){
		Messages mMessage = new Messages();
		try{
			String sql = "SELECT * FROM MESSAGES WHERE ID = " + messageId;
			ResultSet rs = this.getResultSet(this.performQuery(this.getConnection()), sql);
			while(rs.next()){
				mMessage.setMessageId(rs.getInt(1));
				mMessage.setMessage(rs.getString(2));
				mMessage.setContact(rs.getInt(3));				
				mMessage.setReceived(rs.getString(4));
				mMessage.setStatus(rs.getString(5));
				mMessage.setMsgType(rs.getString(6));
			}
			rs.close();
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return mMessage;
	}
	
	/**
	 * @return Messages
	 */
	public List<Messages> getMessages(String msgType){
		List<Messages> mMessages = new ArrayList<Messages>();
		try{
			String sql = "SELECT * FROM MESSAGES WHERE MESSAGETYPE = '" + msgType +"'";
			ResultSet rs = this.getResultSet(this.performQuery(this.getConnection()), sql);
			while (rs.next()){
				Messages message = new Messages();
				message.setMessageId(rs.getInt(1));
				message.setMessage(rs.getString(2));
				message.setContact(rs.getInt(3));				
				message.setReceived(rs.getString(4));
				message.setStatus(rs.getString(5));
				message.setMsgType(rs.getString(6));
				mMessages.add(message);
			}
			rs.close();
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return mMessages;
	}
	
	/**
	 * Update A Contact Object
	 * @param contact
	 * @return Number Of Rows Affected
	 */
	public boolean updateContact(Contacts contact){
		try{
			int ID = contact.getID();
			String name = contact.getContactName();
			String phone = contact.getPhoneNumber();
			String sql = "UPDATE CONTACTS SET NAME = '" + name + "'" + "," +" PHONENUMBER = " + "'" + phone + "'" + " WHERE ID = " + ID;
			this.executeQuery(sql);
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return true;
	}
	
	public boolean deleteContact(Contacts contact){
		try{
			String sql = "DELETE FROM CONTACTS WHERE ID = " + contact.getID();
			this.executeQuery(sql);
		}
		catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return true;
	}
	
	public static void main(String [] args){
		/*DBMS db = new DBMS();
		Contacts con1 = db.getContact(2);
		System.out.println("Contact Name: " + con1.getContactName() + "\n");
		System.out.println("Phone Number: " + con1.getPhoneNumber() + "\n");*/
	}
}
