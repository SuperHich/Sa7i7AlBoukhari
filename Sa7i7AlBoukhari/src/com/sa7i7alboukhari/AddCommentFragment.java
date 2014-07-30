package com.sa7i7alboukhari;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sa7i7alboukhari.adapters.IFragmentNotifier;
import com.sa7i7alboukhari.entity.Comment;
import com.sa7i7alboukhari.externals.SABDataBase;
import com.sa7i7alboukhari.externals.SABManager;
import com.sa7i7alboukhari.utils.MySuperScaler;

@SuppressLint("ValidFragment")
public class AddCommentFragment extends Fragment {

    private EditText edt_name, edt_email, edt_comment;
    private TextView txv_name, txv_email, txv_comment ;
    private Button btn_remove, btn_add;
    private int hadithId;
    private Comment selectedComment;
	private SABDataBase sabDB;
	private IFragmentNotifier fragNotifier;

    public AddCommentFragment() {
        // Empty constructor required for DialogFragment
    }
    
    public AddCommentFragment(int hadithId) {
    	
    	this.hadithId = hadithId;
    }
    
    public AddCommentFragment(Comment comment) {
    	
    	this.selectedComment = comment;
    	this.hadithId = comment.getHadithId();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_comment, container, false);
        
        if(!(MySuperScaler.scaled))
			MySuperScaler.scaleViewAndChildren(rootView, MySuperScaler.scale);
        
        
        txv_name = (TextView) rootView.findViewById(R.id.txv_name);
        txv_email = (TextView) rootView.findViewById(R.id.txv_email);
        txv_comment = (TextView) rootView.findViewById(R.id.txv_comment);
        edt_name = (EditText) rootView.findViewById(R.id.edt_name);
        edt_email = (EditText) rootView.findViewById(R.id.edt_email);
        edt_comment = (EditText) rootView.findViewById(R.id.edt_comment);
        btn_remove = (Button) rootView.findViewById(R.id.btn_remove);
        btn_add = (Button) rootView.findViewById(R.id.btn_add);
        
        int size = (int) MySuperScaler.screen_width / 25 ;
        edt_name.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        edt_email.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        edt_comment.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        txv_name.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        txv_email.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        txv_comment.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);

        btn_remove.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().onBackPressed();
			}
		});
        
        btn_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(selectedComment == null){
					selectedComment = new Comment();
				}
				
				String name = edt_name.getText().toString();
				String email = edt_email.getText().toString();
				String text = edt_comment.getText().toString();
				
				if(name.equals("") || email.equals("") || text.equals("")){
					Toast.makeText(getActivity(), R.string.please_fill, Toast.LENGTH_LONG).show();
					return;
				}
				
				selectedComment.setHadithId(hadithId);
				selectedComment.setTitle(name);
				selectedComment.setText(text);
				
				sabDB.addComment(hadithId, selectedComment);
				
				fragNotifier.requestRefrech();
				
				getActivity().onBackPressed();
			}
		});
        
        return rootView;
    }
    
    @Override
    public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	
    	sabDB = ((MainActivity)getActivity()).sabDB;
    	
    	fragNotifier = SABManager.getInstance(getActivity()).getFragmentNotifier2();
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	super.onViewCreated(view, savedInstanceState);
    	
    	
    	if(selectedComment != null){
    		edt_name.setText(selectedComment.getTitle());
    		edt_comment.setText(selectedComment.getText());
    	}
    	
    }

}
