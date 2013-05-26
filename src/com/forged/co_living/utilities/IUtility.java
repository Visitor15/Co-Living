package com.forged.co_living.utilities;

import java.util.Date;

public interface IUtility {
	public void setUtiliyName(final String name);
	
	public String getUtilityName();
	
	public void setDueDate(final long date);
	
	public long getDueDateAsLong();
	
	public String getPrettyDueDate();
	
	public Date getDueDateAsDate();
	
	public byte[] toByteArray();
	
	public IUtility fromByteArray(final byte[] byteArray);
	
	public void setIsPaid(final boolean isPaid);
	
	public boolean isPaid();
}
