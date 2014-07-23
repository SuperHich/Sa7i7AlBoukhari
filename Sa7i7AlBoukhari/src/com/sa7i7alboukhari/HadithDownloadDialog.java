package com.sa7i7alboukhari;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sa7i7alboukhari.entity.Hadith;
import com.sa7i7alboukhari.externals.IDownloadComplete;
import com.sa7i7alboukhari.externals.IDownloadNotifier;
import com.sa7i7alboukhari.externals.SABDownloadManager;
import com.sa7i7alboukhari.externals.SABManager;

public class HadithDownloadDialog extends DialogFragment implements IDownloadNotifier, OnClickListener {

    public interface EditNameDialogListener {
        void onFinishEditDialog(String inputText);
    }

    private TextView mTxvSoundName;
    private SeekBar mProgressBar;
    private Button mBtnCancel;
    private String soundFile = "";
    private SABDownloadManager sabDownloadManager;
    private IDownloadComplete notifier;

    public HadithDownloadDialog() {
        // Empty constructor required for DialogFragment
    }
    
    public HadithDownloadDialog(Hadith hadith) {
    	
//    	this.soundFile = hadith.getLink();
    	this.soundFile = "http://tondeapel.net/wp-content/uploads/2012/09/Iphone_Ringtone.mp3";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_download_dialog, container);
        mTxvSoundName = (TextView) view.findViewById(R.id.txv_soundName);
        mProgressBar = (SeekBar) view.findViewById(R.id.progressBar);
        mBtnCancel = (Button) view.findViewById(R.id.btn_cancel);
        getDialog().setTitle(R.string.app_name);
        setCancelable(false);

        mTxvSoundName.setText(getString(R.string.download_running) + " : " + SABDownloadManager.getFileNameFromUrl(soundFile));
        mBtnCancel.setOnClickListener(this);
        
        ShapeDrawable thumb = new ShapeDrawable(new RectShape());
	    thumb.getPaint().setColor(Color.rgb(0, 0, 0));
	    thumb.setIntrinsicHeight(-80);
	    thumb.setIntrinsicWidth(30);
	    mProgressBar.setThumb(thumb);
	    mProgressBar.setMax(100);
	    mProgressBar.setEnabled(false);
        
        return view;
    }
    
    @Override
    public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	
    	notifier = SABManager.getInstance(activity).getFragmentNotifier();
    	
    	sabDownloadManager = new SABDownloadManager(activity, this);
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	super.onViewCreated(view, savedInstanceState);
    	
    	if(!sabDownloadManager.initializeDownload(soundFile))
    	{
    		Toast.makeText(getActivity(), R.string.already_exist, Toast.LENGTH_LONG).show();
    	}
    		
    }
    
	@Override
	public void onProgressDownload(int progress) {
		mProgressBar.setProgress(progress);		
	}

	@Override
	public void onDownloadComplete(String path) {
		
		notifier.onDownloadComplete(path);
		Toast.makeText(getActivity(), R.string.download_success, Toast.LENGTH_LONG).show();
		dismiss();
	}

	@Override
	public void onErrorDownload() {
		sabDownloadManager.cancelDownload();
		Toast.makeText(getActivity(), R.string.download_error, Toast.LENGTH_LONG).show();
		dismiss();
	}

	@Override
	public void onClick(View arg0) {
		sabDownloadManager.cancelDownload();
		dismiss();

	}
}
