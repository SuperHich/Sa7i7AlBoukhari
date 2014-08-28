package com.sa7i7alboukhari;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sa7i7alboukhari.utils.MySuperScaler;
import com.sa7i7alboukhari.utils.SABFonts;

public class AboutAppFragment extends ListFragment {

	private TextView title , whatsapp, email, website, twitter, call;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.about_links, container, false);
		if(!(MySuperScaler.scaled))
			MySuperScaler.scaleViewAndChildren(view, MySuperScaler.scale);
		
		
		
	//	title = (TextView) view.findViewById(R.id.title);
		
		whatsapp = (TextView) view.findViewById(R.id.whatsapp);
		email = (TextView) view.findViewById(R.id.email);
		website = (TextView) view.findViewById(R.id.site);
		twitter = (TextView) view.findViewById(R.id.twitter);
		call = (TextView) view.findViewById(R.id.call);
	
		int size = (int) MySuperScaler.screen_width / 23 ;
		int size_web = (int) MySuperScaler.screen_width / 26 ;
	//	title.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		
		whatsapp.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		email.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		website.setTextSize(TypedValue.COMPLEX_UNIT_PX, size_web);
		twitter.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		call.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		
		whatsapp.setTypeface(SABFonts.getMOHANDFont());
		email.setTypeface(SABFonts.getMOHANDFont());
		website.setTypeface(SABFonts.getMOHANDFont());
		twitter.setTypeface(SABFonts.getMOHANDFont());
		call.setTypeface(SABFonts.getMOHANDFont());
	
	
	return view ;
	
	}
	
}
