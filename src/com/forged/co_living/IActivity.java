package com.forged.co_living;

import android.os.Bundle;


public interface IActivity {

	public void onActivityCreated(final Bundle savedInstanceState);
	
	public void onActivityStart();
	
	public void onActivityRestart();
	
	public void onActivityResume();
	
	public void onActivityPause();
	
	public void onActivityStop();
	
	public void onActivityDestroy();
	
}
