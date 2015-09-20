package com.droidtub.fakecall.controller;

import java.util.ArrayList;

import com.droidtub.fakecall.model.CallItemData;
import com.droidtub.fakecall.model.ContactItem;
import com.droidtub.fakecall.view.CallAddItemDialog;
import com.droidtub.fakecall.view.CallListUi;
import com.droidtub.fakecall.view.MainActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CallFragmentController {
	
	private ActionBarActivity mActivity;
	private CallListUi mCallListUi;
	private Fragment mFragment;
	private CallListAdapter mCallListAdapter;
	private ArrayList<ContactItem> mCallList;
	
	
	public void setUi(CallListUi callListUi){
		mCallListUi = callListUi;
	}

	public void setActivity(ActionBarActivity activity){
		mActivity = activity;
	}
	
	public void setFragment(Fragment fragment){
		mFragment = fragment;
	}
	
	public FragmentManager getFragmentManager(){
		return mFragment.getFragmentManager();
	}
	
	public Context getContext(){
		return mActivity;
	}
	
	public CallListAdapter getAdapter(){
		return mCallListAdapter;
	}
	
	public ArrayList<ContactItem> getCallList(){
		return mCallList;
	}
	
	public View onCreate(ViewGroup container, Bundle savedInstanceState){
		mCallList = new ArrayList<ContactItem>();	
		mCallListUi.setActivityAndController(mActivity, this);
		View v = mCallListUi.createView(container);
		return v;
	}
	
	public void onResume(){
		loadContactList();
		mCallListAdapter = new CallListAdapter(mCallList, mActivity, this);
		mCallListUi.onResume();
	}
	
	public void loadContactList(){
		mCallList = FcSQLiteOpenHelper.getInstance(mActivity).getAllContactList();
	}
}