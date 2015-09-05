package com.droidtub.fakecall.view;

import com.droidtub.fakecall.R;
import com.droidtub.fakecall.controller.CallFragmentController;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class CallListUi {

	private CallFragmentController mController;
	private ActionBarActivity mActivity;
	private ViewGroup rootView;
	private ListView callListView;
	private TextView noItemText;
	private ImageButton addBtn;
	private CallAddItemDialog mCallAddItemDialog;
	private ChooserDialog mChooserDialog;
	private View noItemLayout = null;
	
	public void setActivityAndController(ActionBarActivity activity, CallFragmentController controller){
		mController = controller;
		mActivity = activity;
	}

	
	public View createView(ViewGroup container){
		rootView = (ViewGroup)mActivity.getLayoutInflater().inflate(R.layout.fragment_call, container, false);
		callListView = (ListView)rootView.findViewById(R.id.call_list);
		noItemText = (TextView)rootView.findViewById(R.id.calllist_no_text);
		//callListView.setOnItemClickListener(this);
		checkForEmptyList();
		callListView.setAdapter(mController.getAdapter());
		
		addBtn = (ImageButton)rootView.findViewById(R.id.call_fab);
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showChooseDialog(mController.getFragmentManager());
			}
		});
		return rootView;
	}


	protected void showChooseDialog(FragmentManager fragmentManager) {
		mChooserDialog = new ChooserDialog();
		mChooserDialog.show(fragmentManager, null);
		
	}


	private void checkForEmptyList() {
		if(mController.getCallList().size() == 0){
			noItemText.setVisibility(View.VISIBLE);
		}else{
			callListView.setVisibility(View.VISIBLE);
		}
	}

	class ChooserDialog extends DialogFragment implements View.OnClickListener{

		private TextView mManual;
		private TextView mFromContact;
		private TextView mFromCallLog;
		@Override
		public Dialog onCreateDialog(Bundle bundle){
			AlertDialog.Builder builder = new Builder(mActivity);
			LayoutInflater inflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			View view = inflater.inflate(R.layout.call_chooser_dialog, null);
			
			mManual = (TextView)view.findViewById(R.id.call_chooser_manual);
			mFromContact = (TextView)view.findViewById(R.id.call_chooser_contact);
			mFromCallLog = (TextView)view.findViewById(R.id.call_chooser_call_log);
			mManual.setOnClickListener(this);
			mFromContact.setOnClickListener(this);
			mFromCallLog.setOnClickListener(this);
			
			builder.setView(view);
			AlertDialog dialog = builder.create();
			return dialog;
		}
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.call_chooser_manual:
				showAddManualDialog(mController.getFragmentManager());
				dismiss();
				break;

			default:
				break;
			}
			
		}
		
	}

	public void showAddManualDialog(FragmentManager fragmentManager) {
		AddProfileDialog mAddProfileDialog = new AddProfileDialog();
		mAddProfileDialog.show(fragmentManager, null);
	}
	
	class AddProfileDialog extends DialogFragment{
		
		@Override
		public Dialog onCreateDialog(Bundle bundle){
			AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
			LayoutInflater inflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			View view = inflater.inflate(R.layout.add_profile_dialog, null);
			
			builder.setView(view);
			Dialog addDialogManual = builder.create();
			return addDialogManual;
		}
	}
}