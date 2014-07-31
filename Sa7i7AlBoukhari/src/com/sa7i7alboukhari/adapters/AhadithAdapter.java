package com.sa7i7alboukhari.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sa7i7alboukhari.R;
import com.sa7i7alboukhari.entity.Hadith;
import com.sa7i7alboukhari.externals.SABDataBase;
import com.sa7i7alboukhari.utils.MySuperScaler;

public class AhadithAdapter extends ArrayAdapter<Hadith> {

	Context mContext;
	IHadtihListener listener;
	int layoutResourceId;
	ArrayList<Hadith> data = null;
	LayoutInflater inflater;
	private boolean isEnabled = true;

	public AhadithAdapter(Context mContext, int layoutResourceId, ArrayList<Hadith> data, IHadtihListener listener) {

		super(mContext, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.mContext = mContext;
		this.data = data;
		this.listener = listener;
		inflater = ((Activity) mContext).getLayoutInflater();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if(convertView==null)
		{

			holder = new ViewHolder();
			convertView = inflater.inflate(layoutResourceId, parent, false);

			if(!MySuperScaler.scaled)
				MySuperScaler.scaleViewAndChildren(convertView, MySuperScaler.scale);

			// get the elements in the layout
			holder.textview = (TextView) convertView.findViewById(R.id.text); 
			holder.btn_showMore = (Button) convertView.findViewById(R.id.btn_showMore);
			holder.btn_listen = (Button) convertView.findViewById(R.id.btn_listen);
			holder.btn_download = (Button) convertView.findViewById(R.id.btn_download);
			holder.btn_favorite = (Button) convertView.findViewById(R.id.btn_favorite);
			holder.btn_comment = (Button) convertView.findViewById(R.id.btn_comment);
			holder.btn_share = (Button) convertView.findViewById(R.id.btn_share);

			holder.bottom_layout = (RelativeLayout) convertView.findViewById(R.id.bottom_layout);
			holder.btn_pause = (Button) convertView.findViewById(R.id.btn_pause);
			
			holder.btn_showMore.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if(!isEnabled())
						return;
					int position = (Integer)(v.getTag());					
					listener.onHadithShowMore(position);
				}
			});

			holder.btn_listen.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if(!isEnabled())
						return;
					int position = (Integer)(v.getTag());					
					listener.onHadithListen(position);
				}
			});
			
			holder.btn_pause.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if(!isEnabled())
						return;
					int position = (Integer)(v.getTag());					
					listener.onHadithPause(position);
				}
			});

			holder.btn_download.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if(!isEnabled())
						return;
					int position = (Integer)(v.getTag());					
					listener.onHadithDownload(position);
				}
			});

			holder.btn_favorite.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if(!isEnabled())
						return;
					int position = (Integer)(v.getTag());					
					listener.onHadithFavorite(position);
				}
			});

			holder.btn_comment.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if(!isEnabled())
						return;
					int position = (Integer)(v.getTag());					
					listener.onHadithComment(position);
				}
			});

			holder.btn_share.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if(!isEnabled())
						return;
					int position = (Integer)(v.getTag());					
					listener.onHadithShare(position);
				}
			});

			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder)convertView.getTag();
		}

		int size = (int) MySuperScaler.screen_width / 23 ;
		holder.textview.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);

		holder.btn_showMore.setTag(position);
		holder.btn_listen.setTag(position);
		holder.btn_pause.setTag(position);
		holder.btn_download.setTag(position);
		holder.btn_favorite.setTag(position);
		holder.btn_comment.setTag(position);
		holder.btn_share.setTag(position);

		/*
		 * Set the data for the list item. You can also set tags here if you
		 * want.
		 */
		Hadith hadith = data.get(position);

		if(hadith.isShown()){
			holder.textview.setMaxLines(Integer.MAX_VALUE);
			holder.textview.setText(Html.fromHtml(SABDataBase.formatHadith(hadith.getText()).concat(".")));
			holder.btn_showMore.setBackgroundResource(R.drawable.pointstop_selector);
		}
		else{
			holder.textview.setMaxLines(2);
			holder.textview.setText(Html.fromHtml(SABDataBase.formatHadith(hadith.getText()).concat(" ... ")));
			holder.btn_showMore.setBackgroundResource(R.drawable.more_selector);
		}

		if(!hadith.isDownload())
			holder.btn_download.setBackgroundResource(R.drawable.download_hadith_selector);
		else
			holder.btn_download.setBackgroundResource(R.drawable.download_hadith_selected);

		if(!hadith.isFavorite())
			holder.btn_favorite.setBackgroundResource(R.drawable.favourite_hadith_selector);
		else
			holder.btn_favorite.setBackgroundResource(R.drawable.favourite_hadith_selected);

		if(hadith.isBottomLayoutShown())
			holder.bottom_layout.setVisibility(View.VISIBLE);
		else
			holder.bottom_layout.setVisibility(View.GONE);

		return convertView;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	class ViewHolder
	{
		TextView textview; 
		Button btn_showMore;
		Button btn_listen;
		Button btn_pause;
		Button btn_download;
		Button btn_favorite;
		Button btn_comment;
		Button btn_share;
		RelativeLayout bottom_layout;
	}

}
