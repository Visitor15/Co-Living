package com.forged.co_living.database;

public class DBUtils {

	public static final String DATABASE_NAME = "colivingdb";
	
	public static final int DATABASE_VERSION = 1;
	
	public static final String PROFILE_TABLE_NAME = "coliving_profile_table";
	
	public static enum Columns {
		_id,
		PROFILE_NAME,
		IS_UTD,
		PROFILE_BLOB
	}
	
	public static final String[] ALL_COLUMNS_FOR_PROFILE_TABLE = { Columns._id.name(), Columns.PROFILE_NAME.name(), Columns.IS_UTD.name(), Columns.PROFILE_BLOB.name() };

	public static final String CREATE_PROFILE_TABLE = "CREATE TABLE " + PROFILE_TABLE_NAME + 
			" (" + Columns._id + " INTERGER PRIMARY KEY," +
			Columns.PROFILE_NAME + " TEXT," +
			Columns.IS_UTD + " BOOLEAN," +
			Columns.PROFILE_BLOB + " BLOB" + ");";
	
}
