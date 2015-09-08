package com.droidtub.fakecall.controller;

import java.util.ArrayList;

import com.droidtub.fakecall.model.ContactItem;
import com.droidtub.fakecall.view.ContactsUi;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;

public class ContactsController {
	private Activity mActivity;
	private ContactsUi mUi;
	private ContactsAdapter mAdapter;
	private ArrayList<ContactItem> contactsList;

	public ContactsController(Activity activity){
		mActivity = activity;
		contactsList = new ArrayList<ContactItem>();
		new LoadContact().execute();	
		mAdapter = new ContactsAdapter(mActivity, contactsList, this);		
		mActivity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				mAdapter.notifyDataSetChanged();			
			}
		});
		
		mUi = new ContactsUi(this, mActivity);
	}
	
	public void onCreate(Bundle savedInstanceState){	
		mUi.createUi();
	}
	
	public ContactsAdapter getAdapter(){
		return mAdapter;
	}
	
	private class LoadContact extends AsyncTask<Integer, Void, Void>{

		@Override
		protected Void doInBackground(Integer... params) {
			Cursor cursor = mActivity.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
			ContactItem mContactItem = new ContactItem();
			while(cursor.moveToNext()){
				String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
				String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				number = number.replaceAll("\\s", "");
				mContactItem = new ContactItem(name, number);
				contactsList.add(mContactItem);
				
			}
			cursor.close();
			
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Void... values){
			super.onProgressUpdate(values);
			mAdapter.notifyDataSetChanged();
		}
		
		@Override
		protected void onPostExecute(Void result){
			mAdapter.notifyDataSetChanged();
		}
		
		
	}
}
