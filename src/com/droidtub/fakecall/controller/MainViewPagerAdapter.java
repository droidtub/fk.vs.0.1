package com.droidtub.fakecall.controller;


import com.droidtub.fakecall.view.FragmentCall;
import com.droidtub.fakecall.view.FragmentMessage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter{
	CharSequence mTitles[];
	int mNumOfTabs;
	

	public MainViewPagerAdapter(FragmentManager fm, CharSequence titles[], int num){
		super(fm);
		this.mTitles = titles;
		this.mNumOfTabs = num;
	}


	@Override
	public Fragment getItem(int position) {
		if(position == 0) // if the position is 0 we are returning the First tab
        {
            FragmentCall tab1 = new FragmentCall();
            return tab1;
        }
        else             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            FragmentMessage tab2 = new FragmentMessage();
            return tab2;
        }
	}

	@Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mNumOfTabs;
	}
}
