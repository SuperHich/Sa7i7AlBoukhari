package com.sa7i7alboukhari.utils;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.sa7i7alboukhari.R;
import com.sa7i7alboukhari.entity.Hadith;

public class AhadithAdapter extends ArrayAdapter<Hadith> {

	Context mContext;
	int layoutResourceId;
	ArrayList<Hadith> data = null;
	public AhadithAdapter(Context mContext, int layoutResourceId, ArrayList<Hadith> data) {

		super(mContext, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.mContext = mContext;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View listItem = convertView;

		// inflate the listview_item_row.xml parent
		LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
		listItem = inflater.inflate(layoutResourceId, parent, false);

		// get the elements in the layout
		TextView textview = (TextView) listItem.findViewById(R.id.text); 
		Button btn_showMore = (Button) listItem.findViewById(R.id.btn_showMore);
		Button btn_listen = (Button) listItem.findViewById(R.id.btn_listen);
		Button btn_download = (Button) listItem.findViewById(R.id.btn_download);
		Button btn_favorite = (Button) listItem.findViewById(R.id.btn_favorite);
		Button btn_comment = (Button) listItem.findViewById(R.id.btn_comment);
		/*
		 * Set the data for the list item. You can also set tags here if you
		 * want.
		 */
		Hadith hadith = data.get(position);

		textview.setText(hadith.getText());

		return listItem;
	}

}
