package com.droidtub.fakecall.view;

import com.droidtub.fakecall.R;
import com.droidtub.fakecall.controller.CallFragmentController;
import com.droidtub.fakecall.controller.FcSQLiteOpenHelper;
import com.droidtub.fakecall.model.ContactItem;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
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
	private Dialog deleteDialog;
	
	public void setActivityAndController(ActionBarActivity activity, CallFragmentController controller){
		mController = controller;
		mActivity = activity;
	}

	
	public View createView(ViewGroup container){
		rootView = (ViewGroup)mActivity.getLayoutInflater().inflate(R.layout.fragment_call, container, false);
		callListView = (ListView)rootView.findViewById(R.id.call_list);
		noItemText = (TextView)rootView.findViewById(R.id.calllist_no_text);
		
		addBtn = (ImageButton)rootView.findViewById(R.id.call_fab);
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showChooseDialog(mController.getFragmentManager());
			}
		});
		
		callListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				showDeleteDialog(position);
				return false;
			}
		});
		return rootView;
	}

	public void onResume(){
		checkForEmptyList();
		callListView.setAdapter(mController.getAdapter());
		mController.getAdapter().notifyDataSetChanged();
	}

	protected void showDeleteDialog(final int position){
		deleteDialog = new Dialog(mActivity);
		deleteDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		deleteDialog.setContentView(R.layout.delete_dialog);
		
		TextView btnCancel = (TextView)deleteDialog.findViewById(R.id.btn_cancel);
		TextView btnOk = (TextView)deleteDialog.findViewById(R.id.btn_ok);
		TextView txtTitle = (TextView)deleteDialog.findViewById(R.id.delete_dialog_title);
		
		
		btnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				deleteDialog.dismiss();
			}
		});
		
		btnOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				ContactItem item = mController.getCallList().get(position);
				FcSQLiteOpenHelper.getInstance(mActivity).deleteContactItem(item);
				mController.refreshItem();
				checkForEmptyList();
				deleteDialog.dismiss();
			}
		});
		
		deleteDialog.show();
	}
	
	protected void showChooseDialog(FragmentManager fragmentManager) {
		mChooserDialog = new ChooserDialog();
		mChooserDialog.show(fragmentManager, null);
		
	}


	private void checkForEmptyList() {
		if(mController.getCallList().size() == 0){
			callListView.setVisibility(View.GONE);
			noItemText.setVisibility(View.VISIBLE);
		}else{
			noItemText.setVisibility(View.GONE);
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
				showAddManualDialog(getFragmentManager());
				dismiss();
				break;

			case R.id.call_chooser_contact:
				Intent i = new Intent(mActivity, ContactsActivity.class);
				startActivity(i);
				dismiss();
				break;
			case R.id.call_chooser_call_log:
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
	
	class AddProfileDialog extends DialogFragment implements View.OnClickListener{
		
		private EditText mHourValue;
		private EditText mMinValue;
		private EditText mSecValue;
		private TextView mSetBtn;
		private TextView mCancelBtn;
		private EditText mName;
		private EditText mNumber;
		private ContactItem mContactItem;
		
		@Override
		public Dialog onCreateDialog(Bundle bundle){
			
			AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
			LayoutInflater inflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mContactItem = new ContactItem();
			
			View view = inflater.inflate(R.layout.add_profile_dialog, null);
			mName = (EditText)view.findViewById(R.id.add_call_name);
			mNumber = (EditText)view.findViewById(R.id.add_call_number);
			
			mHourValue = (EditText)view.findViewById(R.id.call_hour_value);
			mMinValue = (EditText)view.findViewById(R.id.call_minute_value);
			mSecValue = (EditText)view.findViewById(R.id.call_second_value);
			mSetBtn = (TextView)view.findViewById(R.id.set_btn);
			mSetBtn.setOnClickListener(this);
			mCancelBtn = (TextView)view.findViewById(R.id.cancel_btn);
			mCancelBtn.setOnClickListener(this);
			builder.setView(view);
			Dialog addDialogManual = builder.create();
			return addDialogManual;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.set_btn:
				setModelFromLayout();
				FcSQLiteOpenHelper.getInstance(mActivity).insertFakeCallProfile(mContactItem);
				mController.refreshItem();
				checkForEmptyList();
				dismiss();
				break;
			case R.id.cancel_btn:
				dismiss();
				break;
			default:
				break;
			}
			
		}
		
		public void setModelFromLayout(){
			mContactItem.setName(mName.getText().toString());
			mContactItem.setNumber(mNumber.getText().toString());
			mContactItem.setHour(mHourValue.getText().toString());
			mContactItem.setMinute(mMinValue.getText().toString());
			mContactItem.setSecond(mSecValue.getText().toString());
			mContactItem.setSelected(true);
		}
	}
}