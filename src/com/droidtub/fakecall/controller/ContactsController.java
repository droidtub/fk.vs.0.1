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
		mAdapter = new ContactsAdapter(mActivity, contactsList, this);
		new LoadContact().execute();
		mAdapter.notifyDataSetChanged();
		mUi = new ContactsUi(this, mActivity);
	}
	
	public void onCreate(Bundle savedInstanceState){	
		mUi.createUi();
	}
	
	public ContactsAdapter getAdapter(){	
		return mAdapter;
	}
	
	private class LoadContact extends AsyncTask<Integer, Void, ArrayList<ContactItem>>{

	    ArrayList<ContactItem> temp = new ArrayList<ContactItem>();
	    
        @Override
		protected ArrayList<ContactItem> doInBackground(Integer... params) {
			temp = getContactList();
			return temp;
		}
		
		@Override
		protected void onProgressUpdate(Void... values){
			super.onProgressUpdate(values);
		}
		
		@Override
		protected void onPostExecute(final ArrayList<ContactItem> result){
		    contactsList.clear();
		    contactsList.addAll(result);
            mAdapter.notifyDataSetChanged();			
		}
		
		
	}
	
	public ArrayList<ContactItem> getContactList(){
	    ArrayList<ContactItem> result = new ArrayList<>();
		Cursor cursor = mActivity.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");
		ContactItem mContactItem = new ContactItem();
		while(cursor.moveToNext()){
			String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			number = number.replaceAll("\\s", "");
			mContactItem = new ContactItem(name, number);
			result.add(mContactItem);

		}
		cursor.close();
		return result;
	}
}