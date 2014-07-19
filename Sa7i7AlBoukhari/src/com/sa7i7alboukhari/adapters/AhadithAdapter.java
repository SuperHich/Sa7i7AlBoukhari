package com.sa7i7alboukhari.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.sa7i7alboukhari.R;
import com.sa7i7alboukhari.entity.Hadith;

public class AhadithAdapter extends ArrayAdapter<Hadith> implements OnTouchListener {

	Context mContext;
	IHadtihListener listener;
	int layoutResourceId;
	ArrayList<Hadith> data = null;
	LayoutInflater inflater;
	
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
			
			// get the elements in the layout
			holder.textview = (TextView) convertView.findViewById(R.id.text); 
			holder.btn_showMore = (Button) convertView.findViewById(R.id.btn_showMore);
			holder.btn_listen = (Button) convertView.findViewById(R.id.btn_listen);
			holder.btn_download = (Button) convertView.findViewById(R.id.btn_download);
			holder.btn_favorite = (Button) convertView.findViewById(R.id.btn_favorite);
			holder.btn_comment = (Button) convertView.findViewById(R.id.btn_comment);
			holder.btn_share = (Button) convertView.findViewById(R.id.btn_share);
			
			holder.btn_showMore.setOnTouchListener(this);
			holder.btn_listen.setOnTouchListener(this);
			holder.btn_download.setOnTouchListener(this);
			holder.btn_favorite.setOnTouchListener(this);
			holder.btn_comment.setOnTouchListener(this);
			holder.btn_share.setOnTouchListener(this);
			
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder)convertView.getTag();
		}

		holder.btn_showMore.setTag(position);
		holder.btn_listen.setTag(position);
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
			holder.textview.setText(Html.fromHtml(hadith.getText().concat(".")));
			holder.btn_showMore.setBackgroundResource(R.drawable.point_stop);
		}
		else{
			holder.textview.setMaxLines(2);
			holder.textview.setText(Html.fromHtml(hadith.getText().concat(" ... ")));
			holder.btn_showMore.setBackgroundResource(R.drawable.more);
		}

		return convertView;
	}

	class ViewHolder
	{
		TextView textview; 
		Button btn_showMore;
		Button btn_listen;
		Button btn_download;
		Button btn_favorite;
		Button btn_comment;
		Button btn_share;
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
			
			int position = (Integer)(v.getTag());
			
			switch (v.getId()) {
			case R.id.btn_showMore:
				listener.onHadithShowMore(position);
				break;
			case R.id.btn_listen:
				listener.onHadithListen(position);
				break;
			case R.id.btn_download:
				listener.onHadithDownload(position);
				break;
			case R.id.btn_favorite:
				listener.onHadithFavorite(position);
				break;
			case R.id.btn_comment:
				listener.onHadithComment(position);
				break;
			case R.id.btn_share:
				listener.onHadithShare(position);
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
}
