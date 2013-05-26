package com.forged.co_living.profile;

import java.util.ArrayList;

import com.forged.co_living.utilities.Utility;

public interface IBaseProfile {

	public void setId(final long id);
	
	public long getId();
	
	public void setName(final String name);
	
	public String getName();
	
	public void setEmail(final String email);
	
	public String getEmail();
	
	public void setTotalDue(final double amountDue);
	
	public double getTotalDue();
	
	public void setUtilityList(final ArrayList<Utility> utilityList);
	
	public ArrayList<Utility> getUtilityList();
	
	public void setIsUpToDate(final boolean isUpdateToDate);
	
	public boolean isUpToDate();
	
	public byte[] toByteArray();
	
	public IBaseProfile fromByteArray(final byte[] byteArray);
}
