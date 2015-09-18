package com.droidtub.fakecall.controller;

import java.util.ArrayList;

import com.droidtub.fakecall.R;
import com.droidtub.fakecall.model.CallItemData;
import com.droidtub.fakecall.model.ContactItem;
import com.droidtub.fakecall.view.ContactsActivity;
import com.droidtub.fakecall.view.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CallListAdapter extends BaseAdapter{

	private ArrayList<ContactItem> mCallList;
	private Activity mActivity;
	private CallFragmentController mController;
	
	public CallListAdapter(ArrayList<ContactItem> callList, Activity activity, CallFragmentController controller) {
		mCallList = callList;
		mActivity = activity;
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
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			LayoutInflater inflater = mActivity.getLayoutInflater();
			convertView = inflater.inflate(R.layout.call_list_row, null);
			
			holder = new ViewHolder();
			holder.mName = (TextView)convertView.findViewById(R.id.fc_item_name);
			holder.mNumber = (TextView)convertView.findViewById(R.id.fc_item_number);
			holder.mIcon = (ImageView)convertView.findViewById(R.id.fc_item_icon);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		ContactItem item = mCallList.get(position);
		holder.mName.setText(item.getName());
		holder.mNumber.setText(item.getNumber());
		return null;
	}

	private class ViewHolder{
		ImageView mIcon;
		TextView mName;
		TextView mNumber;
	}
}