package com.sa7i7alboukhari;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sa7i7alboukhari.utils.MySuperScaler;
import com.sa7i7alboukhari.utils.SABFonts;

public class AboutFragment extends ListFragment {

	private TextView title , whatsapp, youtube, website, twitter, instagram, facebook;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.about, container, false);
		if(!(MySuperScaler.scaled))
			MySuperScaler.scaleViewAndChildren(view, MySuperScaler.scale);
		
		
		
	//	title = (TextView) view.findViewById(R.id.title);
		
		whatsapp = (TextView) view.findViewById(R.id.whatsapp);
		youtube = (TextView) view.findViewById(R.id.youtube);
		website = (TextView) view.findViewById(R.id.site);
		twitter = (TextView) view.findViewById(R.id.twitter);
		instagram = (TextView) view.findViewById(R.id.instagram);
		facebook = (TextView) view.findViewById(R.id.facebook);
	
		int size = (int) MySuperScaler.screen_width / 23 ;
		int size_web = (int) MySuperScaler.screen_width / 26 ;
	//	title.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		
		whatsapp.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		facebook.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		website.setTextSize(TypedValue.COMPLEX_UNIT_PX, size_web);
		twitter.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		instagram.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		youtube.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		
		whatsapp.setTypeface(SABFonts.getMOHANDFont());
		youtube.setTypeface(SABFonts.getMOHANDFont());
		website.setTypeface(SABFonts.getMOHANDFont());
		twitter.setTypeface(SABFonts.getMOHANDFont());
		instagram.setTypeface(SABFonts.getMOHANDFont());
		facebook.setTypeface(SABFonts.getMOHANDFont());
	
	
		whatsapp.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					TextView view = (TextView) v;
					view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
					v.invalidate();
					break;
				}
				case MotionEvent.ACTION_UP: {

				/// Action What's up
				
				}
				case MotionEvent.ACTION_CANCEL: {
					TextView view = (TextView) v;
					view.getBackground().clearColorFilter();
					view.invalidate();
					break;
				}
				}
				return true;
			}
		});
		
		youtube.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					TextView view = (TextView) v;
					view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
					v.invalidate();
					break;
				}
				case MotionEvent.ACTION_UP: {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/Alssunnah1428")));
				}
				case MotionEvent.ACTION_CANCEL: {
					TextView view = (TextView) v;
					view.getBackground().clearColorFilter();
					view.invalidate();
					break;
				}
				}
				return true;
			}
		});
		
		website.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					TextView view = (TextView) v;
					view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
					v.invalidate();
					break;
				}
				case MotionEvent.ACTION_UP: {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("www.alssunnah.com")));
				}
				case MotionEvent.ACTION_CANCEL: {
					TextView view = (TextView) v;
					view.getBackground().clearColorFilter();
					view.invalidate();
					break;
				}
				}
				return true;
			}
		});
		
		twitter.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					TextView view = (TextView) v;
					view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
					v.invalidate();
					break;
				}
				case MotionEvent.ACTION_UP: {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/alssunnah")));
				}
				case MotionEvent.ACTION_CANCEL: {
					TextView view = (TextView) v;
					view.getBackground().clearColorFilter();
					view.invalidate();
					break;
				}
				}
				return true;
			}
		});
		
		instagram.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					TextView view = (TextView) v;
					view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
					v.invalidate();
					break;
				}
				case MotionEvent.ACTION_UP: {

				/// ACtion instagram
				
				}
				case MotionEvent.ACTION_CANCEL: {
					TextView view = (TextView) v;
					view.getBackground().clearColorFilter();
					view.invalidate();
					break;
				}
				}
				return true;
			}
		});
		
		facebook.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					TextView view = (TextView) v;
					view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
					v.invalidate();
					break;
				}
				case MotionEvent.ACTION_UP: {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Alssunah")));
				}
				case MotionEvent.ACTION_CANCEL: {
					TextView view = (TextView) v;
					view.getBackground().clearColorFilter();
					view.invalidate();
					break;
				}
				}
				return true;
			}
		});
		
		
	return view ;
	
	}
	
}
