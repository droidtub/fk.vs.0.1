package com.droidtub.fakecall.view;

import com.droidtub.fakecall.R;
import com.droidtub.fakecall.controller.CallFragmentController;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

public class CallListUi {

	private CallFragmentController mController;
	private ActionBarActivity mActivity;
	private ViewGroup rootView;
	private ListView callListView;
	private ImageButton addBtn;
	
	private CallAddItemDialog mCallAddItemDialog;
	
	public void setActivityAndController(ActionBarActivity activity, CallFragmentController controller){
		mController = controller;
		mActivity = activity;
	}

	
	public View createView(ViewGroup container){
		rootView = (ViewGroup)mActivity.getLayoutInflater().inflate(R.layout.fragment_call, container, false);
		callListView = (ListView)rootView.findViewById(R.id.call_list);
		//callListView.setOnItemClickListener(this);
		callListView.setAdapter(mController.getAdapter());
		
		addBtn = (ImageButton)rootView.findViewById(R.id.call_fab);
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showCallAddItemDialog(mActivity.getFragmentManager());
				
			}
		});
		return rootView;
	}
	
	protected void showCallAddItemDialog(FragmentManager fm){
		mCallAddItemDialog = new CallAddItemDialog();
		mCallAddItemDialog.show();
	}
}
