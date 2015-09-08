package com.droidtub.fakecall.controller;

import java.util.ArrayList;

import com.droidtub.fakecall.R;
import com.droidtub.fakecall.model.ContactItem;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract.PhoneticNameStyle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ContactsAdapter extends BaseAdapter {

	Activity mActivity;
	int layoutId;
	ArrayList<ContactItem> mPhoneList;
	private ContactsController mController;
	
	/*public ContactsAdapter(Activity context, int resource, ArrayList<ContactItem> objects) {
		super(context, resource, objects);
		this.mActivity = context;
		layoutId = resource;
		mPhoneList = objects;
	}*/
	
	public ContactsAdapter(Activity context, ArrayList<ContactItem> objects, ContactsController controller){
		mActivity = context;
		mPhoneList = objects;
		mController = controller;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolder holder = null;
		if(convertView == null){
			LayoutInflater inflater = mActivity.getLayoutInflater();
			convertView = inflater.inflate(R.layout.contact_item, null);
			
			holder = new ViewHolder();
			holder.mName = (TextView)convertView.findViewById(R.id.contact_name);
			holder.mNumber = (TextView)convertView.findViewById(R.id.contact_number);
			holder.mCheckbox = (CheckBox)convertView.findViewById(R.id.contact_checkbox);
			convertView.setTag(holder);
			holder.mCheckbox.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					CheckBox cb = (CheckBox)v;
					ContactItem item = (ContactItem)cb.getTag();
					item.setSelected(cb.isChecked());
				}
			});
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		ContactItem item = mPhoneList.get(position);
		if(item.getName().equals("") || item.getClass() == null)
			holder.mName.setText(item.getNumber());
		else holder.mName.setText(item.getName());
		holder.mNumber.setText(item.getNumber());
		holder.mCheckbox.setChecked(item.isSelected());
		holder.mCheckbox.setTag(item);
		return convertView;
	}
	
	private class ViewHolder{
		TextView mName;
		TextView mNumber;
		CheckBox mCheckbox;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mPhoneList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mPhoneList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
}
