package com.forged.co_living;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.forged.android.co_living.R;
import com.forged.co_living.client.ConnectionManager;
import com.forged.co_living.ui.GenericResultSetAdapter;
import com.forged.co_living.ui.ProfilePicImageView;

public class MainActivity extends BaseActivity {

	public static final String TAG = "MainActivity";

	private static MainActivity mInstance;

	private RelativeLayout mHeaderContainer;

	private LinearLayout mMainContainer;

	private ProfilePicImageView profilePic;

	private RelativeLayout btnApartment;

	private RelativeLayout btnUtilities;

	private RelativeLayout btnHistory;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.main);

		mMainContainer = (LinearLayout) findViewById(R.id.ll_main_container);

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
		mInstance = this;
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

	public static MainActivity getInstanceActivity() {
		return mInstance;
	}

	public void showResultSet(final ArrayList<String> valList) {
		this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				mMainContainer.removeView(mMainContainer.findViewById(R.id.body));
				
				View v = getLayoutInflater().inflate(R.layout.generic_list_view, null);
				
				for(int i = 0; i < valList.size(); i++) {
					Log.d(TAG, "NCC - ITEM: " + valList.get(i));
				}
				
				((ListView) v).setAdapter(new GenericResultSetAdapter(mInstance, valList));
				
				mMainContainer.addView(v);

			};
		});
	}

	private void initButtons() {
		((TextView) btnApartment.findViewById(R.id.tv_btn_title)).setText("Apartment");
		((TextView) btnUtilities.findViewById(R.id.tv_btn_title)).setText("Utilities");
		((TextView) btnHistory.findViewById(R.id.tv_btn_title)).setText("History");
	}
}
