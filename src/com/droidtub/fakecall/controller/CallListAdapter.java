package com.droidtub.fakecall.controller;

import java.util.ArrayList;

import com.droidtub.fakecall.model.CallItemData;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CallListAdapter extends BaseAdapter {
	
	private ArrayList<CallItemData> mCallList;
	private Context mContext;
	private CallFragmentController mController;

	public CallListAdapter(ArrayList<CallItemData> callList, Context context, CallFragmentController controller){
		mCallList = callList;
		mContext = context;
		mController = controller;
	}
	
	static class CallListItemViewHolder{
		protected TextView itemName;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCallList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mCallList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
