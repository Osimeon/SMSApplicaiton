package org.smslib;

import java.util.ArrayList;
import java.util.Collection;

public class Group{
	private String groupName;

	private Collection<String> groupNumbers;

	public Group(String myGroupName){
		this.groupName = myGroupName;
		this.groupNumbers = new ArrayList<String>();
	}
	
	/**
	 * @return Group Name
	 */
	public String getName(){
		return this.groupName;
	}

	/**
	 * Returns the numbers associated with the group.
	 * @return The numbers associated with the group.
	 */
	public Collection<String> getNumbers(){
		return new ArrayList<String>(this.groupNumbers);
	}

	/**
	 * Adds a number to the group.
	 * @param number The number to add to the group.
	 */
	public void addNumber(String number){
		this.groupNumbers.add(number);
	}

	/**
	 * Removes a number from the group.
	 * @param number The number to be removed from the group.
	 * @return True if the removal was a success. False if the number was not found.
	 */
	public boolean removeNumber(String number){
		return this.groupNumbers.remove(number);
	}

	/**
	 * Removes all numbers from the group (clears the group).
	 */
	public void clear(){
		this.groupNumbers.clear();
	}
}
