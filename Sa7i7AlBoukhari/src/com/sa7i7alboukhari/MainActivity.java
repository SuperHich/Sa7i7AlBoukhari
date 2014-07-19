package com.sa7i7alboukhari;

import java.util.Locale;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.sa7i7alboukhari.utils.MySuperScaler;


public class MainActivity extends MySuperScaler {


	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

	private String[] mPlanetTitles;

	private Drawable d1, d2 , d3 , d4 , d5, d6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mPlanetTitles = getResources().getStringArray(R.array.planets_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.right_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		d1 = getResources().getDrawable(R.drawable.favourites_list);
		d2 = getResources().getDrawable(R.drawable.show_ahadith);
		d3 = getResources().getDrawable(R.drawable.show_abweb);
		d4 = getResources().getDrawable(R.drawable.show_fahras);
		d5 = getResources().getDrawable(R.drawable.about_chabaka);
		d6 = getResources().getDrawable(R.drawable.about);


		Drawable[] lListIcone={d1 , d2 ,d3 ,d4, d5, d6};

		CustomAdapter adapter = new CustomAdapter(lListIcone);


		mDrawerList.setAdapter(adapter);
		mDrawerList.setDivider(null);

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


		if (savedInstanceState == null) {
			selectItem(0);
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

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


			selectItem(position);


		}
	}

	private void selectItem(int position) {
		// update the main content by replacing fragments
		Fragment fragment = new PlanetFragment();
		Bundle args = new Bundle();
		args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
		fragment.setArguments(args);

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(mPlanetTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}


	public static class PlanetFragment extends Fragment {
		public static final String ARG_PLANET_NUMBER = "planet_number";

		public PlanetFragment() {
			// Empty constructor required for fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
			int i = getArguments().getInt(ARG_PLANET_NUMBER);
			String planet = getResources().getStringArray(R.array.planets_array)[i];

			int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
					"drawable", getActivity().getPackageName());
			((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
			getActivity().setTitle(planet);
			return rootView;
		}
	}

	class CustomAdapter extends BaseAdapter implements OnTouchListener
	{
		Drawable images[]; 
		LayoutInflater inflater;
		public CustomAdapter(Drawable[] lListIcone)
		{
			images=lListIcone;
			inflater= LayoutInflater.from(MainActivity.this);

		}
		@Override
		public int getCount() {
			return images.length;
		}
		@Override
		public Object getItem(int arg0) {
			return arg0;
		}
		@Override
		public long getItemId(int position) {
			return 0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if(convertView==null)
			{
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.rowlv_module, null);
				holder.iv= (ImageView) convertView.findViewById(R.id.trim1);
				holder.iv.setOnTouchListener(this);
				convertView.setTag(holder);
			}
			else {
				holder = (ViewHolder)convertView.getTag();
			}
			holder.iv.setBackgroundDrawable(images[position]);
			return convertView;
		}

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN: {
				ImageView view = (ImageView) v;
				view.getBackground().setColorFilter(0x77ffffff, PorterDuff.Mode.SRC_ATOP);
				v.invalidate();
				break;
			}
			case MotionEvent.ACTION_UP: {


			}
			case MotionEvent.ACTION_CANCEL: {
				ImageView view = (ImageView) v;
				view.getBackground().clearColorFilter();
				view.invalidate();
				break;
			}
			}
			return true;
		}

	}
	class ViewHolder
	{
		ImageView iv;
	}


}