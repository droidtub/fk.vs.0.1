package com.droidtub.fakecall.view;

import com.droidtub.fakecall.R;
import com.droidtub.fakecall.controller.MainController;
import com.droidtub.fakecall.controller.MainViewPagerAdapter;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

public class MainView {
	private MainController mController;
	private ActionBarActivity mActivity;
	private Toolbar mToolbar;
	private MainViewPagerAdapter adapter;
	private ViewPager pager;
	private SlidingTabLayout tabs;
	private CharSequence titles[] = {"PHONE", "MESSAGE"};
	int numOfTabs = 2;
	
	public MainView(MainController controller, ActionBarActivity activity){
		mController = controller;
		mActivity = activity;
	}
	
	public void createMainView(){
		mActivity.setContentView(R.layout.activity_main);
		
		mToolbar = (Toolbar)mActivity.findViewById(R.id.toolbar);
		mActivity.setSupportActionBar(mToolbar);
		//mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mActivity.getSupportActionBar().setElevation(0);
		
		adapter = new MainViewPagerAdapter(mActivity.getSupportFragmentManager(), titles, numOfTabs);
		
		pager = (ViewPager)mActivity.findViewById(R.id.pager);
		pager.setAdapter(adapter);
		
		tabs = (SlidingTabLayout)mActivity.findViewById(R.id.tabs);
		tabs.setDistributeEvenly(true);
		
		tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return mActivity.getResources().getColor(R.color.white);
            	
            }
        });
		
		tabs.setViewPager(pager);
		
	}
}
