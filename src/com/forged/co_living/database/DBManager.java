package com.forged.co_living.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBManager extends SQLiteOpenHelper {
	
	public static final String TAG = "DBManager";
	
	private Context mContext;

	private DBManager mInstance;
	
	public DBManager getInstance(final Context c) {
		if(mInstance == null) {
			return new DBManager(c);
		}
		else {
			return mInstance;
		}
	}
	
	private DBManager(final Context context) {
		super(context, DBUtils.DATABASE_NAME, null, DBUtils.DATABASE_VERSION);
		mContext = context;
		mInstance = this;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DBUtils.CREATE_PROFILE_TABLE);

		if(db.isReadOnly()) {
			final SQLiteDatabase wdb = this.getWritableDatabase();
			//Whoever may need a writeable database gets it here.
			wdb.close();
		} else {
			//Initialize with the read only database.
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public Cursor query(final String tableName, final String selection, final String[] selectionArgs, final String orderBy) {
		Cursor c = null;
		try {
			SQLiteDatabase db = getReadableDatabase();
			Log.d("TAG", "NCC : Query");

			if(tableName.equalsIgnoreCase(DBUtils.PROFILE_TABLE_NAME)) {

				Log.d("TAG", "NCC - QUERIED FOR REMINDERS");

				c = db.query(DBUtils.PROFILE_TABLE_NAME, DBUtils.ALL_COLUMNS_FOR_PROFILE_TABLE,
						selection, selectionArgs, null, null, orderBy);

				Log.d("TAG", "NCC - GOT CURSOR FOR MASTER_TABLE_NAME - SIZE IS: " + c.getCount());

				return c;
			}
//			else if(tableName.equalsIgnoreCase(EnvironmentVariables.DATABASE.CATEGORIES_TABLE_NAME)) {
//				c = db.query(EnvironmentVariables.DATABASE.CATEGORIES_TABLE_NAME, EnvironmentVariables.DATABASE.ALL_COLUMNS_FOR_CATEGORY_OBJ,
//						selection, selectionArgs, null, null, orderBy);
//
//				Log.d("TAG", "NCC - QUERIED FOR CATEGORIES");
//
//				return c;
//			}
		} catch (final SQLiteException e) {
			Log.e(TAG, "There was an error querying database for table: " + tableName);
		}

		return c;
	}

}
