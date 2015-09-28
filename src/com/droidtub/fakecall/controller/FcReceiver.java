package com.droidtub.fakecall.controller;

import java.util.ArrayList;
import java.util.Calendar;

import com.droidtub.fakecall.model.ContactItem;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FcReceiver extends BroadcastReceiver {

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String TIME_HOUR = "timeHour";
	public static final String TIME_MINUTE = "timeMinute";
	public static final String TIME_SECOND = "timeSecond";
	public static final String SCREEN_TYPE = "screenType";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		setAlarm(context);
	}

	public static void setAlarm(Context context){
		ArrayList<ContactItem> items = FcSQLiteOpenHelper.getInstance(context).getAllContactList();
		
		for(ContactItem item:items){
			PendingIntent pIntent = createPendingIntent(context, item);
			
			Calendar calendar = Calendar.getInstance();
			
			final int nowHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			final int nowMinute = Calendar.getInstance().get(Calendar.MINUTE);
			final int nowSecond = Calendar.getInstance().get(Calendar.SECOND);
			
			calendar.set(Calendar.HOUR_OF_DAY, nowHour + Integer.parseInt(item.getHour()));
			calendar.set(Calendar.MINUTE, nowMinute + Integer.parseInt(item.getMinute()));
			calendar.set(Calendar.SECOND, nowSecond + Integer.parseInt(item.getSecond()));
			
			setAlarm(context, calendar, pIntent);
		
		}
	}
	
	private static void setAlarm(Context context, Calendar calendar, PendingIntent pIntent){
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
			alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
		} else {
			alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
		}
	}
	
	public static PendingIntent createPendingIntent(Context context, ContactItem item){
		Intent i = new Intent(context, FcService.class);
		i.putExtra(ID, item.getId());
		i.putExtra(NAME, item.getName());
		i.putExtra(TIME_HOUR, item.getHour());
		i.putExtra(TIME_MINUTE, item.getMinute());
		i.putExtra(TIME_SECOND, item.getSecond());
		
		return PendingIntent.getService(context, (int)item.getId(), i, PendingIntent.FLAG_UPDATE_CURRENT);
	}
}
