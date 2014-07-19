package com.sa7i7alboukhari;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sa7i7alboukhari.entity.Hadith;
import com.sa7i7alboukhari.utils.AhadithAdapter;

public class AhadithFragment extends Fragment{
	
        public static final String ARG_AHADITH = "ahadith_type";
        
        ArrayList<Hadith> ahadith = new ArrayList<Hadith>();

        public AhadithFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_ahadith, container, false);
            int i = getArguments().getInt(ARG_AHADITH);

            switch (i) {
			case 0:
				ahadith.addAll(((MainActivity)getActivity()).sabDB.getFavoriteHadiths());				
				break;
			case 1:
				ahadith.addAll(((MainActivity)getActivity()).sabDB.getAllHadithsWithPage(0));				
				break;
			default:
				break;
			}
            
            
            AhadithAdapter adapter = new AhadithAdapter(getActivity(), R.layout.hadith_list_item, ahadith);
            
            ((ListView) rootView.findViewById(R.id.listView)).setAdapter(adapter);
//            getActivity().setTitle(planet);
            return rootView;
        }

}
