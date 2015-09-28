package com.droidtub.fakecall.controller;

import com.droidtub.fakecall.view.FcScreen;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FcService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		Intent i = new Intent(getBaseContext(), FcScreen.class);
		i.putExtras(intent);
		startActivity(i);
		
		FcReceiver.setAlarm(this);
		return super.onStartCommand(intent, flags, startId);
	}

}
