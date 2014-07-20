package com.sa7i7alboukhari;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sa7i7alboukhari.AhadithSearchDialog.EditNameDialogListener;
import com.sa7i7alboukhari.adapters.IMenuListener;
import com.sa7i7alboukhari.adapters.MenuCustomAdapter;
import com.sa7i7alboukhari.externals.SABDataBase;
import com.sa7i7alboukhari.utils.MySuperScaler;


@SuppressLint("Recycle")
public class MainActivity extends MySuperScaler implements IMenuListener, OnTouchListener, EditNameDialogListener{


	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private Button btn_menu, btn_search;

	public SABDataBase sabDB;
	
	private ActionBarDrawerToggle mDrawerToggle;
	RelativeLayout mainView ;

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
		
		
		if (savedInstanceState == null) {
			selectItem(1);
		}

		btn_menu = (Button) findViewById(R.id.menu);
		btn_menu.setOnTouchListener(this);
//		btn_menu.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				if(!mDrawerLayout.isDrawerOpen(Gravity.RIGHT))
//					mDrawerLayout.openDrawer(Gravity.RIGHT);
//				else
//					mDrawerLayout.closeDrawer(Gravity.RIGHT);		
//			}
//		});
		
		btn_search = (Button) findViewById(R.id.search);
		btn_search.setOnTouchListener(this);

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if(sabDB == null){
			sabDB = new SABDataBase(this);
		}
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

         FragmentManager fragmentManager = getSupportFragmentManager();
         FragmentTransaction ft = fragmentManager.beginTransaction();
         
         ft.replace(R.id.content_frame, fragment);
         scaled = false ;
         ft.commit();

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

	@Override
	public void onMenuItemClicked(int position) {
		selectItem(position);
	}
	
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			Button view = (Button) v;
			view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
			v.invalidate();
			break;
		}
		case MotionEvent.ACTION_UP: {
			
			switch (v.getId()) {
			case R.id.menu:
				if(!mDrawerLayout.isDrawerOpen(Gravity.RIGHT))
					mDrawerLayout.openDrawer(Gravity.RIGHT);
				else
					mDrawerLayout.closeDrawer(Gravity.RIGHT);		
				break;
			case R.id.search:
				//show search dialog;
				showSearchDialog();
				break;
			default:
				break;
			}
			
		}
		case MotionEvent.ACTION_CANCEL: {
			Button view = (Button) v;
			view.getBackground().clearColorFilter();
			view.invalidate();
			break;
		}
		}
		return true;
	}

	 private void showSearchDialog() {
	        FragmentManager fm = getSupportFragmentManager();
	        AhadithSearchDialog searchDialog = new AhadithSearchDialog();
	        searchDialog.show(fm, "fragment_search_keyword");
	    }
	
	@Override
	public void onFinishEditDialog(String inputText) {
		
		 Toast.makeText(this, getString(R.string.to_search) + " " + inputText, Toast.LENGTH_SHORT).show();

		// update the main content by replacing fragments
		Fragment fragment = new AhadithFragment();
		Bundle args = new Bundle();
		args.putInt(AhadithFragment.ARG_AHADITH, AhadithFragment.ARG_AHADITH_KEYWORD_ID);
		args.putString(AhadithFragment.ARG_AHADITH_KEYWORD_TEXT, inputText);
		fragment.setArguments(args);

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();

		ft.replace(R.id.content_frame, fragment);
		scaled = false ;
		ft.commit();

	}

}