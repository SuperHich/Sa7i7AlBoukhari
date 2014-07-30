package com.sa7i7alboukhari.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sa7i7alboukhari.R;
import com.sa7i7alboukhari.entity.Comment;
import com.sa7i7alboukhari.utils.MySuperScaler;

public class CommentsAdapter extends ArrayAdapter<Comment> {

	Context mContext;
	int layoutResourceId;
	ArrayList<Comment> data = null;
	LayoutInflater inflater;
	
	public CommentsAdapter(Context mContext, int layoutResourceId, ArrayList<Comment> data) {

		super(mContext, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.mContext = mContext;
		this.data = data;
		inflater = ((Activity) mContext).getLayoutInflater();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if(convertView==null)
		{
			holder = new ViewHolder();
			convertView = inflater.inflate(layoutResourceId, parent, false);
			
			MySuperScaler.scaleViewAndChildren(convertView, MySuperScaler.scale);
			
			// get the elements in the layout
			holder.txv_title = (TextView) convertView.findViewById(R.id.txv_title_comment); 
			holder.txv_text = (TextView) convertView.findViewById(R.id.txv_text_comment);
			
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder)convertView.getTag();
		}

		/*
		 * Set the data for the list item. You can also set tags here if you
		 * want.
		 */
		Comment comment = data.get(position);

		holder.txv_title.setText(comment.getTitle());
		holder.txv_text.setText(comment.getText());

		return convertView;
	}

	class ViewHolder
	{
		TextView txv_title;
		TextView txv_text;
	}

}
