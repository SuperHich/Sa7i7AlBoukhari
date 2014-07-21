package com.sa7i7alboukhari;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.sa7i7alboukhari.adapters.AhadithAdapter;
import com.sa7i7alboukhari.adapters.IHadtihListener;
import com.sa7i7alboukhari.entity.Hadith;
import com.sa7i7alboukhari.mediaplayer.IMediaPlayerNotifier;
import com.sa7i7alboukhari.mediaplayer.SABMediaPlayer;
import com.sa7i7alboukhari.utils.MySuperScaler;


public class AhadithFragment extends Fragment implements IHadtihListener, IMediaPlayerNotifier{

	public static final String ARG_AHADITH = "ahadith_type";
	public static final String ARG_AHADITH_KEYWORD_TEXT = "ahadith_keyword";
	public static final int ARG_AHADITH_KEYWORD_ID = 10;

	private ListView listView;
	private AhadithAdapter adapter;
	private ArrayList<Hadith> ahadith = new ArrayList<Hadith>();
	private int ahadith_typeId = 0;
	private String ahadith_keyword;
	private SABMediaPlayer sabPlayer;

	public AhadithFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		sabPlayer = new SABMediaPlayer(getActivity(), this);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_ahadith, container, false);
		ahadith_typeId = getArguments().getInt(ARG_AHADITH);
		ahadith_keyword = getArguments().getString(ARG_AHADITH_KEYWORD_TEXT);

		if(!(MySuperScaler.scaled))
			MySuperScaler.scaleViewAndChildren(rootView, MySuperScaler.scale);


		adapter = new AhadithAdapter(getActivity(), R.layout.hadith_list_item, ahadith, this);

		listView = (ListView) rootView.findViewById(R.id.listView);
		listView.setAdapter(adapter);
		listView.setCacheColorHint(Color.TRANSPARENT);
		//            getActivity().setTitle(planet);
		return rootView;
	}

	private void initAhadith(){

		try {
			ahadith.clear();
			
			switch (ahadith_typeId) {
			case 0:
				ahadith.addAll(((MainActivity)getActivity()).sabDB.getFavoriteHadiths());				
				break;
			case 1:
				ahadith.addAll(((MainActivity)getActivity()).sabDB.getAllHadithsWithPage(0));				
				break;
			case ARG_AHADITH_KEYWORD_ID:
				ahadith.addAll(((MainActivity)getActivity()).sabDB.searchHadithWithText(ahadith_keyword));				
				break;
			default:
				break;
			}

			adapter.notifyDataSetChanged();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		initAhadith();
	}

	@Override
	public void onHadithShowMore(int position) {
		Hadith hadith = ahadith.get(position);
		ahadith.get(position).setShown(!hadith.isShown());

		adapter.notifyDataSetChanged();
	}

	@Override
	public void onHadithListen(int position) {
		Hadith hadith = ahadith.get(position);
		if(hadith.isDownload())
		{
			sabPlayer.playFromSdcardWithCompletion(hadith.getFile());
		}else
		{
			sabPlayer.playFromUrlWithCompletion(hadith.getLink());
		}
		
	}

	@Override
	public void onHadithDownload(int position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHadithFavorite(int position) {

		Hadith hadith = ahadith.get(position);
		boolean newFavStatus = !hadith.isFavorite();
		if(((MainActivity)getActivity()).sabDB.setFavoriteHadith(hadith.getId(), newFavStatus))
		{
			if (ahadith_typeId == 0) {
				initAhadith();
			}else
				ahadith.get(position).setFavorite(newFavStatus);

			adapter.notifyDataSetChanged();

			if(newFavStatus)
				Toast.makeText(getActivity(), R.string.added_to_fav, Toast.LENGTH_LONG).show();
			else
				Toast.makeText(getActivity(), R.string.removed_from_fav, Toast.LENGTH_LONG).show();

			//				return;
		}

		//			Toast.makeText(getActivity(), "Error occured", Toast.LENGTH_LONG).show();

	}

	@Override
	public void onHadithComment(int position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHadithShare(int position) {
		Hadith hadith = ahadith.get(position);
		shareHadith(hadith.getText());
	}


	private void shareHadith(String text){


		Log.e("TEXT", text);
		
		text = text.replace(System.getProperty("line.separator"), " ");
		
		String shareBody = text;
		Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
		sharingIntent.setType("text/plain");
		sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name));
		sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
		startActivity(Intent.createChooser(sharingIntent, getString(R.string.share)));

	}

	@Override
	public void onCompletion() {
		// TODO Auto-generated method stub
		
	}

}
