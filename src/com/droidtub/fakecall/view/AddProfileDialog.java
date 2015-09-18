package com.droidtub.fakecall.view;

import com.droidtub.fakecall.R;
import com.droidtub.fakecall.controller.FcSQLiteOpenHelper;
import com.droidtub.fakecall.model.ContactItem;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddProfileDialog extends DialogFragment implements View.OnClickListener{
	
		private EditText mHourValue;
		private EditText mMinValue;
		private EditText mSecValue;
		private TextView mSetBtn;
		private TextView mCancelBtn;
		private EditText mName;
		private EditText mNumber;
		static private Activity mActivity;
		private ContactItem mContactItem;
		
		public static AddProfileDialog newInstance(Activity context, String name, String number){
			AddProfileDialog dialog = new AddProfileDialog();
			mActivity = context;
			Bundle args = new Bundle();		
			args.putString("number", number);
			args.putString("name", name);
			dialog.setArguments(args);
			return dialog;		
		}
		
		@Override
		public Dialog onCreateDialog(Bundle bundle){
			
			String number = getArguments().getString("number");
			String name = getArguments().getString("name");
			AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
			LayoutInflater inflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mContactItem = new ContactItem();
			
			View view = inflater.inflate(R.layout.add_profile_dialog, null);
			mName = (EditText)view.findViewById(R.id.add_call_name);
			mName.setText(name);
			mNumber = (EditText)view.findViewById(R.id.add_call_number);
			mNumber.setText(number);
			
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
			mContactItem.setName(mNumber.getText().toString());
			mContactItem.setHour(Integer.parseInt(mHourValue.getText().toString()));
			mContactItem.setMinute(Integer.parseInt(mMinValue.getText().toString()));
			mContactItem.setSecond(Integer.parseInt(mSecValue.getText().toString()));
			mContactItem.setSelected(true);
		}
	
}