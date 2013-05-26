package com.forged.co_living.utilities;


/*
 * Class to objectify a utility service
 */
public abstract class Utility implements IUtility {
	
	private String UtilityName;
	
	private long dueDate;
	
	private double totalAmountDue;

	public Utility() {
	
	}
}


