package com.forged.co_living.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.forged.android.co_living.R;

public class ProfilePicImageView extends ImageView {

	public ProfilePicImageView(Context context) {
		super(context);
		init();
	}


	public ProfilePicImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}


	public ProfilePicImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.test_profile_pic);
		Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

		BitmapShader shader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
		Paint paint = new Paint();
		paint.setShader(shader);
		
		Canvas c = new Canvas(circleBitmap);
		c.drawCircle(bitmap.getWidth()/2, bitmap.getHeight()/2, bitmap.getHeight()/2, paint);
		this.setImageBitmap(circleBitmap);
	}
}
