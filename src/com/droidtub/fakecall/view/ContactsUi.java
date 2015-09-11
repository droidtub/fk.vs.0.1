package com.droidtub.fakecall.view;

import java.util.ArrayList;

import com.droidtub.fakecall.R;
import com.droidtub.fakecall.controller.ContactsAdapter;
import com.droidtub.fakecall.controller.ContactsController;
import com.droidtub.fakecall.model.ContactItem;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


public class ContactsUi implements OnClickListener{
	private ContactsController mController;
	private Activity mActivity;
	private ContactsAdapter mAdapter;
	private ArrayList<ContactItem> contactsList;
	private ImageButton backBtn;
	//private TextView addBtn;
	private ListView lvContact;

	public ContactsUi(ContactsController controller, Activity activity){
		mController = controller;
		mActivity = activity;
	}
	
	public void createUi(){
		mActivity.setContentView(R.layout.contacts_list_layout);
		contactsList = new ArrayList<ContactItem>();
		lvContact = (ListView)mActivity.findViewById(R.id.contacts_list);
		backBtn = (ImageButton)mActivity.findViewById(R.id.contacts_activity_back_btn);
		//addBtn = (TextView)mActivity.findViewById(R.id.contact_add_btn);
		lvContact.setAdapter(mController.getAdapter());
		mController.getAdapter().notifyDataSetChanged();
		backBtn.setOnClickListener(this);
		//addBtn.setOnClickListener(this);
		lvContact.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ContactItem item = (ContactItem)parent.getItemAtPosition(position);
				//CheckBox cb = (CheckBox)view.findViewById(R.id.contact_checkbox);
				//item.setSelected(!cb.isChecked());
				//cb.setChecked(item.isSelected());
				AddProfileDialog mDialog = AddProfileDialog.newInstance(mActivity, item.getName(), item.getNumber());
				mDialog.show(mActivity.getFragmentManager(), null);
			}
		});
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.contacts_activity_back_btn:
			mActivity.finish();
			break;
		
			
		default:
			break;
		}
		
	}
	
	
}
