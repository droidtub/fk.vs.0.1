package com.droidtub.fakecall.controller;

import com.droidtub.fakecall.view.MainView;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MainController {
	private ActionBarActivity mActivity;
	private MainView mMainView;
	
	public MainController(ActionBarActivity activity){
		mActivity = activity;
		mMainView = new MainView(this, mActivity);
	}
	
	public void onCreate(Bundle savedInstanceState){
		mMainView.createMainView();
	}
}
