package com.sa7i7alboukhari;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.sa7i7alboukhari.adapters.IMenuListener;
import com.sa7i7alboukhari.adapters.MenuCustomAdapter;
import com.sa7i7alboukhari.externals.SABDataBase;


public class MainActivity extends FragmentActivity implements IMenuListener{


	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

	public SABDataBase sabDB;

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


		if (savedInstanceState == null) {
			selectItem(1);
		}

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
         fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

         // update selected item and title, then close the drawer
         mDrawerList.setItemChecked(position, true);
//         setTitle(mPlanetTitles[position]);
         mDrawerLayout.closeDrawer(mDrawerList);
        
//        sabDB.setFavoriteHadith(4, true);
//        sabDB.getAllBabs();
//        sabDB.getAllHadithsWithPage(0);
//        sabDB.getAllHadithsWithBabId(6);
//        sabDB.getFavoriteHadiths();
//        sabDB.searchHadithWithText("عَنْ عِكْرِمَةَ بْنِ خَالِدٍ");
//        boolean isFav = sabDB.isHadithFavorite(4);
//        Log.i("", "isFav " + isFav);
	}

	@Override
	public void onMenuItemClicked(int position) {
		selectItem(position);
	}

}