package com.droidtub.fakecall.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FcSQLiteOpenHelper extends SQLiteOpenHelper {
	
	public static final String TAG = "FcSQLiteOpenHelper";
	private static final String DB_NAME = "FakeCallList.db";
	private static final int ver = 1;
	private SQLiteDatabase db;
	
	public static final String TB_FAKE_CALL = "tb_fakecall";
	public static final String FC_ID = "_id";
	public static final String FC_NAME = "Name";
	public static final String FC_NUMBER = "Number";
	public static final String FC_TIME = "Time";
	
	public static final String TB_FAKE_MSG = "tb_fakemsg";
	public static final String FM_ID = "_id";
	public static final String FM_NAME = "Name";
	public static final String FM_NUMBER = "Number";
	public static final String FM_TIME = "Time";
	
	private static FcSQLiteOpenHelper instance;
	
	private FcSQLiteOpenHelper(Context context){
		super(context, DB_NAME, null, ver);
	}

	public static FcSQLiteOpenHelper getInstance(Context context){
		if(instance == null){
			instance = new FcSQLiteOpenHelper(context);
		}
		return instance;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TB_FAKE_CALL + "(" + FC_ID + 
				" INTEGER PRIMARY KEY AUTO INCREMENT, " + FC_NAME + " TEXT" + FC_NUMBER + 
				" TEXT" + FC_TIME + " INTEGER)";
		db.execSQL(sql);
		
		sql = "CREATE TABLE " + TB_FAKE_MSG + "(" + FM_ID +
				" INTEGER PRIMARY KEY AUTO INCREMENT, " + FM_NAME + " TEXT" + FM_NUMBER +
				" TEXT" + FM_TIME + " INTEGER)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXIST " + TB_FAKE_CALL);
		db.execSQL("DROP TABLE IF EXIST " + TB_FAKE_MSG);
		onCreate(db);
	}
	
	public void insertFakeCallProfile(String name, String number, int time){
		db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(FC_NAME, name);
		cv.put(FC_NUMBER, number);
		cv.put(FC_TIME, time);
		db.insertOrThrow(TB_FAKE_CALL, null, cv);
	}
	
	public void insertFakeMsgProfile(String name, String number, int time){
		db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(FM_NAME, name);
		cv.put(FM_NUMBER, number);
		db.insertOrThrow(TB_FAKE_MSG, null, cv);
	}
	
}
