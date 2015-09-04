package com.droidtub.fakecall.controller;

import java.util.ArrayList;

import com.droidtub.fakecall.model.CallItemData;
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
	private Fragment mFragment;
	private CallListUi mCallListUi;
	private CallListAdapter mCallListAdapter;
	private ArrayList<CallItemData> mCallList;
	
	public void setUi(CallListUi callListUi){
		mCallListUi = callListUi;
	}

	public void setActivity(ActionBarActivity activity){
		mActivity = activity;
	}
	
	public void setFragment(Fragment fragment){
		mFragment = fragment;
	}
	
	public Context getContext(){
		return mActivity;
	}
	
	public View onCreate(ViewGroup container, Bundle savedInstanceState){
		Context context = getContext().getApplicationContext();
		mCallList = new ArrayList<CallItemData>();
		mCallListUi.setActivityAndController(mActivity, this);
		View v = mCallListUi.createView(container);
		return v;
	}
	
	public CallListAdapter getAdapter(){
		mCallListAdapter = new CallListAdapter(mCallList, mActivity,this);
		return mCallListAdapter;
	}
	
	public ArrayList<CallItemData> getCallList(){
		return mCallList;
	}
	
	public FragmentManager getFragmentManager(){
		return mFragment.getFragmentManager();
	}
	
}
