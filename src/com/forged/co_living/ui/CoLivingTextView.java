package com.forged.co_living.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CoLivingTextView extends TextView {

	public CoLivingTextView(Context context) {
		super(context);
		init();
	}
	
	public CoLivingTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public CoLivingTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		this.setTypeface(Typeface.createFromAsset(this.getContext().getAssets(), "fonts/Roboto-Thin.ttf"));
//		this.setText("Nick Champagne");
	}
}
