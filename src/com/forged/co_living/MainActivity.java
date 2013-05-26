package com.forged.co_living;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.forged.android.co_living.R;
import com.forged.co_living.client.ConnectionManager;
import com.forged.co_living.ui.ProfilePicImageView;

public class MainActivity extends BaseActivity {

	public static final String TAG = "MainActivity";
	
	private RelativeLayout mHeaderContainer;
	
	private ProfilePicImageView profilePic;
	
	private RelativeLayout btnApartment;
	
	private RelativeLayout btnUtilities;
	
	private RelativeLayout btnHistory;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.main);
		
		mHeaderContainer = (RelativeLayout) findViewById(R.id.rl_header_container);
		
		btnApartment = (RelativeLayout) findViewById(R.id.btn_apartment);
		btnUtilities = (RelativeLayout) findViewById(R.id.btn_utilities);
		btnHistory = (RelativeLayout) findViewById(R.id.btn_history);
		
		initButtons();
		
		profilePic = (ProfilePicImageView) mHeaderContainer.findViewById(R.id.iv_profile_pic);
		
		profilePic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ConnectionManager.getInstance().sendExchange();
			}
			
		});
		
		
	}

	@Override
	public void onActivityStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onActivityRestart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onActivityResume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onActivityPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onActivityStop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onActivityDestroy() {
		// TODO Auto-generated method stub
		
	}

	private void initButtons() {
		((TextView) btnApartment.findViewById(R.id.tv_btn_title)).setText("Apartment");
		((TextView) btnUtilities.findViewById(R.id.tv_btn_title)).setText("Utilities");
		((TextView) btnHistory.findViewById(R.id.tv_btn_title)).setText("History");
	}
}
