package com.forged.co_living;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity implements IActivity {

	public static final String TAG = "BaseActivity";
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		onActivityCreated(savedInstanceState);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		onActivityStart();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		onActivityRestart();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		onActivityResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		onActivityPause();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		onActivityStop();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		onActivityDestroy();
	}
	
	/*
	 * These are required to be overriden in classes extending this one. They
	 * will be called during the activity lifecycle.
	 */
	public abstract void onActivityCreated(final Bundle savedInstanceState);
	
	public abstract void onActivityStart();
	
	public abstract void onActivityRestart();
	
	public abstract void onActivityResume();
	
	public abstract void onActivityPause();
	
	public abstract void onActivityStop();
	
	public abstract void onActivityDestroy();
	
}
