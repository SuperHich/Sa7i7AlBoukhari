package com.sa7i7alboukhari;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.sa7i7alboukhari.adapters.AbwabAdapter;
import com.sa7i7alboukhari.entity.Chapter;
import com.sa7i7alboukhari.externals.SABDataBase;
import com.sa7i7alboukhari.utils.MySuperScaler;


public class AbwabFragment extends ListFragment{

	public static final String ARG_AHADITH = "ahadith_type";
	
	private AbwabAdapter adapter;
	private ArrayList<Chapter> abwab = new ArrayList<Chapter>();
	
	private SABDataBase sabDB;

	public AbwabFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		sabDB = ((MainActivity)getActivity()).sabDB;
	}
	
	@Override
	public void onDetach() {
		super.onDetach();

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_ahadith, container, false);

		if(!(MySuperScaler.scaled))
			MySuperScaler.scaleViewAndChildren(rootView, MySuperScaler.scale);


		adapter = new AbwabAdapter(getActivity(), R.layout.bab_list_item, abwab);

		return rootView;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		getListView().setAdapter(adapter);
		getListView().setCacheColorHint(Color.TRANSPARENT);
		
		abwab.clear();
		abwab.addAll(sabDB.getAllBabs());
		adapter.notifyDataSetChanged();
		
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				((MainActivity) getActivity()).onBabItemClicked(abwab.get(position));
			}
		});
	}

}