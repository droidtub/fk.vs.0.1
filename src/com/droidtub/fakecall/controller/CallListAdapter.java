package com.droidtub.fakecall.controller;

import java.util.ArrayList;
import java.util.Random;

import com.droidtub.fakecall.R;
import com.droidtub.fakecall.model.CallItemData;
import com.droidtub.fakecall.model.ContactItem;
import com.droidtub.fakecall.view.ContactsActivity;
import com.droidtub.fakecall.view.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
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
	
	public void swapItems(ArrayList<ContactItem> items){
		this.mCallList = items;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			LayoutInflater inflater = mActivity.getLayoutInflater();
			convertView = inflater.inflate(R.layout.call_list_row, null);
			
			holder = new ViewHolder();
			holder.fl = (FrameLayout)convertView.findViewById(R.id.icon_first_char);
			holder.mName = (TextView)convertView.findViewById(R.id.fc_item_name);
			holder.mNumber = (TextView)convertView.findViewById(R.id.fc_item_number);
			holder.mIcon = (ImageView)convertView.findViewById(R.id.iv_first_char);
			holder.mFirstChar = (TextView)convertView.findViewById(R.id.tv_first_char);
			holder.arrayColor = mActivity.getResources().getStringArray(R.array.rainbow);
			holder.randomColor = holder.arrayColor[new Random().nextInt(holder.arrayColor.length)];
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		ContactItem item = mCallList.get(position);
		LayerDrawable bgDrawable = (LayerDrawable)holder.fl.getBackground();
		GradientDrawable shape = (GradientDrawable)bgDrawable.findDrawableByLayerId(R.id.circle_shape);
		shape.setColor(Color.parseColor(holder.randomColor));
		holder.mName.setText(item.getName());
		holder.mNumber.setText(item.getNumber());
		
		if (isASCIILetter(item.getName().charAt(0))) {
			holder.mFirstChar.setVisibility(View.VISIBLE);
			holder.mIcon.setVisibility(View.GONE);
			holder.mFirstChar.setText(item.getName().subSequence(0, 1).toString().toUpperCase());
		} else {
			holder.mFirstChar.setVisibility(View.GONE);
			holder.mIcon.setVisibility(View.VISIBLE);
		}
		return convertView;
	}

	private class ViewHolder{
		String[] arrayColor;
		String randomColor;
		FrameLayout fl;
		ImageView mIcon;
		TextView mFirstChar;
		TextView mName;
		TextView mNumber;
	}
	
	public boolean isASCIILetter(char c) {
		return (c > 64 && c < 91) || (c > 96 && c < 123);
	}
}