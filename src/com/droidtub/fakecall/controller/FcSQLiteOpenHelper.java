package com.droidtub.fakecall.controller;

import java.util.ArrayList;

import com.droidtub.fakecall.model.ContactItem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
	public static final String FC_HOUR = "Hour";
	public static final String FC_MIN = "Min";
	public static final String FC_SEC = "Sec";
	
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
				" INTEGER PRIMARY KEY, " + FC_NAME + " TEXT, " + FC_NUMBER + 
				" TEXT, " + FC_HOUR + " INTEGER, " + FC_MIN + " INTEGER, " + FC_SEC + " INTEGER)";
		db.execSQL(sql);
		
		sql = "CREATE TABLE " + TB_FAKE_MSG + "(" + FM_ID +
				" INTEGER PRIMARY KEY, " + FM_NAME + " TEXT, " + FM_NUMBER +
				" TEXT, " + FM_TIME + " INTEGER)";
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
		cv.put(FC_HOUR, time);
		db.insertOrThrow(TB_FAKE_CALL, null, cv);
	}
	
	public void insertFakeCallProfile(ContactItem mContactItem){
		db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(FC_NAME, mContactItem.getName());
		cv.put(FC_NUMBER, mContactItem.getNumber());
		cv.put(FC_HOUR, mContactItem.getHour());
		cv.put(FC_MIN, mContactItem.getMinute());
		cv.put(FC_SEC, mContactItem.getSecond());
		db.insertOrThrow(TB_FAKE_CALL, null, cv);
	}
	
	public void insertFakeMsgProfile(String name, String number, int time){
		db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(FM_NAME, name);
		cv.put(FM_NUMBER, number);
		db.insertOrThrow(TB_FAKE_MSG, null, cv);
	}

	public ArrayList<ContactItem> getAllContactList(){
		
		db = this.getReadableDatabase();
		ArrayList<ContactItem> list = new ArrayList<ContactItem>();
		Cursor cursor = null;
		String orderBy = FC_ID + " DESC";
		cursor = db.query(TB_FAKE_CALL, null, null, null, null, null, orderBy);
		if(cursor == null)
			return null;
		cursor.moveToFirst();
		int count = cursor.getCount();
		for(int i = 0; i < count; i++){
			ContactItem item = new ContactItem(cursor.getString(cursor.getColumnIndex(FC_NAME)), 
					cursor.getString(cursor.getColumnIndex(FC_NUMBER)), 
					cursor.getInt(cursor.getColumnIndex(FC_HOUR)),
					cursor.getInt(cursor.getColumnIndex(FC_MIN)),
					cursor.getInt(cursor.getColumnIndex(FC_SEC)));
			list.add(item);
			cursor.moveToNext();
		}
		cursor.close();
		return list;
	}
	
	public void deleteContactItem(ContactItem item){
		db = this.getWritableDatabase();
		String name = item.getName();
		String number = item.getNumber();
		int hour = item.getHour();
		int min = item.getMinute();
		int sec = item.getSecond();
		db.delete(TB_FAKE_CALL, FC_NAME + " =? AND" + FC_NUMBER + " =? AND" + FC_HOUR + " =? AND" + FC_MIN + " =? AND" + FC_SEC + " =?", new String(){ name,number, hour, min, sec})
	}
	
}