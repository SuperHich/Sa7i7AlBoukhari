package com.sa7i7alboukhari;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.sa7i7alboukhari.adapters.AhadithAdapter;
import com.sa7i7alboukhari.adapters.IHadtihListener;
import com.sa7i7alboukhari.entity.Hadith;

public class AhadithFragment extends Fragment implements IHadtihListener{
	
        public static final String ARG_AHADITH = "ahadith_type";
        
        private ListView listView;
        private AhadithAdapter adapter;
        private ArrayList<Hadith> ahadith = new ArrayList<Hadith>();
        private int ahadith_typeId = 0;

        public AhadithFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_ahadith, container, false);
            ahadith_typeId = getArguments().getInt(ARG_AHADITH);

            initAhadith();
            
            adapter = new AhadithAdapter(getActivity(), R.layout.hadith_list_item, ahadith, this);
            
            listView = (ListView) rootView.findViewById(R.id.listView);
            listView.setAdapter(adapter);
            listView.setCacheColorHint(Color.TRANSPARENT);
//            getActivity().setTitle(planet);
            return rootView;
        }

        private void initAhadith(){
        	ahadith.clear();
        	
        	switch (ahadith_typeId) {
        	case 0:
        		ahadith.addAll(((MainActivity)getActivity()).sabDB.getFavoriteHadiths());				
        		break;
        	case 1:
        		ahadith.addAll(((MainActivity)getActivity()).sabDB.getAllHadithsWithPage(0));				
        		break;
        	default:
        		break;
        	}
        }
        
		@Override
		public void onHadithShowMore(int position) {
			Hadith hadith = ahadith.get(position);
			ahadith.get(position).setShown(!hadith.isShown());
			
			adapter.notifyDataSetChanged();
		}

		@Override
		public void onHadithListen(int position) {
			// TODO Auto-generated method stub
			
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
			// TODO Auto-generated method stub
			
		}

}
