package com.droidtub.fakecall.view;

import com.droidtub.fakecall.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;

public class CallAddItemDialog extends DialogFragment{

	@Override
	public Dialog onCreateDialog(Bundle bundle){
		AlertDialog.Builder builder = new Builder(getActivity());
		LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		//View view = inflater.inflate(R.layout.call_add_item_dialog, null);
		
		return null;
	}
}
