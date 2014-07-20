package com.sa7i7alboukhari;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.sa7i7alboukhari.adapters.IMenuListener;
import com.sa7i7alboukhari.adapters.MenuCustomAdapter;
import com.sa7i7alboukhari.externals.SABDataBase;
import com.sa7i7alboukhari.utils.MySuperScaler;



@SuppressLint({ "Recycle", "HandlerLeak" })
public class MainActivity extends MySuperScaler implements IMenuListener{


	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

	public SABDataBase sabDB;

	private ActionBarDrawerToggle mDrawerToggle;
	RelativeLayout mainView ;
	
	public static final int MESSAGE_START = 1;
	
	boolean test ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sabDB = new SABDataBase(this);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.right_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		MenuCustomAdapter adapter = new MenuCustomAdapter(this, getResources().obtainTypedArray(R.array.menu_drawables));


		mDrawerList.setAdapter(adapter);
		mDrawerList.setDivider(null);

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mainView = (RelativeLayout) findViewById(R.id.content_frame);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
			public void onDrawerClosed(View view) {
				supportInvalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				supportInvalidateOptionsMenu();
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				super.onDrawerSlide(drawerView, slideOffset);
				mainView.setTranslationX(- slideOffset * drawerView.getWidth());
				mDrawerLayout.bringChildToFront(drawerView);
				mDrawerLayout.requestLayout();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

//		if (savedInstanceState == null) {
//			selectItem(1);
//		}

		Button btn = (Button) findViewById(R.id.menu);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(!mDrawerLayout.isDrawerOpen(Gravity.RIGHT))
					mDrawerLayout.openDrawer(Gravity.RIGHT);
				else
					mDrawerLayout.closeDrawer(Gravity.RIGHT);		
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();

		if(sabDB == null){
			sabDB = new SABDataBase(this);
		}
		
	}
	
	
	@Override
	protected void onStart() {
		super.onStart();

		Message msg = Message.obtain();
		msg.what = MESSAGE_START;
	    mHandler.sendMessageDelayed(msg, 10);
	}
	
	
	@Override
	protected void onStop() {
		super.onStop();

		if(sabDB != null){
			sabDB.close();
			sabDB = null;
		}
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			selectItem(position);

		}
	}


	private void selectItem(int position) {
		// update the main content by replacing fragments
		Fragment fragment = new AhadithFragment();
		Bundle args = new Bundle();
		args.putInt(AhadithFragment.ARG_AHADITH, position);
		fragment.setArguments(args);

		switchTab(fragment);

		//		FragmentManager fragmentManager = getSupportFragmentManager();
		//		FragmentTransaction ft = fragmentManager.beginTransaction();
		//
		//		FragmentManager fm = getSupportFragmentManager();
		//		Fragment frag = fm.findFragmentById(R.id.content_frame);
		//
		//		if (frag == null) {
		//			ft.add(R.id.content_frame, fragment);
		//			scaled = false ;
		//		} else {
		//
		//			ft.replace(R.id.content_frame, fragment);
		//			scaled = false ;
		//
		//		}
		//		ft.commit();

		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		//         setTitle(mPlanetTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);

		//        sabDB.setFavoriteHadith(4, true);
		//        sabDB.getAllBabs();
		//        sabDB.getAllHadithsWithPage(0);
		//        sabDB.getAllHadithsWithBabId(6);
		//        sabDB.getFavoriteHadiths();
		//        sabDB.searchHadithWithText("Ø¹ÙŽÙ†Ù’ Ø¹Ù�ÙƒÙ’Ø±Ù�Ù…ÙŽØ©ÙŽ Ø¨Ù’Ù†Ù� Ø®ÙŽØ§Ù„Ù�Ø¯Ù�");
		//        boolean isFav = sabDB.isHadithFavorite(4);
		//        Log.i("", "isFav " + isFav);
	}


	private void switchTab(Fragment tab) {
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.content_frame);

		final FragmentTransaction ft = fm.beginTransaction();
		if (fragment == null) {
			ft.add(R.id.content_frame, tab);

		} else {
			ft.replace(R.id.content_frame, tab);
			scaled = false ;
		}

		ft.commit();
	}

	@Override
	public void onMenuItemClicked(int position) {
		selectItem(position);
	}
	
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case MESSAGE_START :
				selectItem(1);
				break;
			
			}
			super.handleMessage(msg);
		
	}};
}