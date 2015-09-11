package com.droidtub.fakecall.view;

import com.droidtub.fakecall.controller.ContactsController;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ContactsActivity extends Activity implements OnClickListener{

	private ContactsController mController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ContactsController mController = new ContactsController(this);
		mController.onCreate(savedInstanceState);
		
	}
	@Override
	public void onClick(View v) {
		
		
	}

	@Override
	public void finish(){
		super.finish();
	}
}
