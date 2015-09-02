package com.droidtub.fakecall.view;

import com.droidtub.fakecall.controller.CallFragmentController;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentCall extends Fragment{

	private CallFragmentController mCallFragmentController;
	private CallListUi mCallListUi;
	private ActionBarActivity mActivity;
	
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach((ActionBarActivity)activity);
		mActivity = (ActionBarActivity) activity;	
		mCallFragmentController = new CallFragmentController();
		createUi();
		mCallFragmentController.setUi(mCallListUi);
		mCallFragmentController.setActivity(mActivity);
	}
	
	private void createUi() {
		try {
			mCallListUi = CallListUi.class.newInstance();
		} catch (java.lang.InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return mCallFragmentController.onCreate(container, savedInstanceState);
	}
}
