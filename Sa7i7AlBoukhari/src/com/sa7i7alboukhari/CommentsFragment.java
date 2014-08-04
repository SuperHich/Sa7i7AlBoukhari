package com.sa7i7alboukhari;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.sa7i7alboukhari.adapters.CommentsAdapter;
import com.sa7i7alboukhari.adapters.IFragmentNotifier;
import com.sa7i7alboukhari.entity.Comment;
import com.sa7i7alboukhari.entity.Hadith;
import com.sa7i7alboukhari.externals.SABDataBase;
import com.sa7i7alboukhari.externals.SABManager;
import com.sa7i7alboukhari.utils.MySuperScaler;


@SuppressLint("ValidFragment")
public class CommentsFragment extends ListFragment implements IFragmentNotifier{

	private CommentsAdapter adapter;
	private ArrayList<Comment> comments = new ArrayList<Comment>();
	
	private TextView txv_text;
	private Button btn_showMore, btn_add_comment;
	
	private SABDataBase sabDB;
	private Hadith hadith;

	public CommentsFragment() {
		// Empty constructor required for fragment subclasses
	}
	
	public CommentsFragment(Hadith hadith) {
		this.hadith = hadith;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		SABManager.getInstance(getActivity()).setFragmentNotifier2(this);
		sabDB = ((MainActivity)getActivity()).sabDB;
	}
	
	@Override
	public void onDetach() {
		super.onDetach();

		SABManager.getInstance(getActivity()).setFragmentNotifier2(null);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_comments, container, false);

		if(!(MySuperScaler.scaled))
			MySuperScaler.scaleViewAndChildren(rootView, MySuperScaler.scale);

		txv_text = (TextView) rootView.findViewById(R.id.txv_text);
		btn_showMore = (Button) rootView.findViewById(R.id.btn_showMore);
		btn_add_comment = (Button) rootView.findViewById(R.id.btn_add_comment);
		
		int size = (int) MySuperScaler.screen_width / 23 ;
		txv_text.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		
		
		
		btn_showMore.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hadith.setShown(!hadith.isShown());
				toggleShown(hadith.isShown());
			}
		});
		
		btn_add_comment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).gotoAddEditCommentFragment(MainActivity.ADD_COMMENT_FRAGMENT, null, hadith.getId());
			}
		});

		toggleShown(hadith.isShown());
		
		adapter = new CommentsAdapter(getActivity(), R.layout.comment_list_item, comments);

		return rootView;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		getListView().setAdapter(adapter);
		getListView().setCacheColorHint(Color.TRANSPARENT);
		
		refreshList();
		
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				((MainActivity) getActivity()).gotoAddEditCommentFragment(MainActivity.EDIT_COMMENT_FRAGMENT, comments.get(position), hadith.getId());
			}
		});
	}
	
	private void refreshList(){
		comments.clear();
		comments.addAll(sabDB.getCommentsWithHadithID(hadith.getId()));
		adapter.notifyDataSetChanged();
	}

	private void toggleShown(boolean isShown){
		if(isShown){
			txv_text.setMaxLines(Integer.MAX_VALUE);
			txv_text.setText(Html.fromHtml(SABDataBase.formatHadith(hadith.getText()).concat(".")));
			btn_showMore.setBackgroundResource(R.drawable.showless_selector);
		}
		else{
			txv_text.setMaxLines(2);
			txv_text.setText(Html.fromHtml(SABDataBase.formatHadith(hadith.getText()).concat("...")));
			btn_showMore.setBackgroundResource(R.drawable.showmore_selector);
		}
	}

	@Override
	public void requestRefrech() {
		refreshList();
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		
		getListView().setEnabled(enabled);
		getListView().setClickable(enabled);
		
		btn_showMore.setEnabled(enabled);
		btn_add_comment.setEnabled(enabled);
		
	}
}
