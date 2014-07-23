package com.sa7i7alboukhari.mediaplayer;

import java.io.IOException;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;

import com.sa7i7alboukhari.R;

/**
 * AlMoufasserAlSaghir
 * @author HICHEM LAROUSSI - RAMI TRABELSI
 * Copyright (c) 2014 Zad Group. All rights reserved.
 */

public class SABMediaPlayer {

	public static MediaPlayer m ;
	private Context context;
	private MediaPlayer player;
	private IMediaPlayerNotifier notifier;
	
	public SABMediaPlayer(Context context) {
		this.context = context;
	}
	
	public SABMediaPlayer(Context context, IMediaPlayerNotifier notifier) {
		this.context = context;
		this.notifier = notifier;
		
	}
	
	public boolean isPlaying(){
		
		if (player != null) return player.isPlaying();
		return false ;
	}
	
	public void stop(){
		if (player != null){
			player.stop();
			player.release();
			player = null ;
		}
		
	}
	
	public void playWithCompletion(String audio){
		try{
			stop();

			AssetFileDescriptor afd = context.getAssets().openFd(audio);

			player = new MediaPlayer();
			player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());

			player.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {

					notifier.onCompletion();

				}
			});

			player.prepare();
			player.start();

		}catch(IOException ex) {
			ex.printStackTrace();
		}
		catch(IllegalStateException ex) {
			ex.printStackTrace();
		}
	}
	
	public void playFromSdcardWithCompletion(String audio){
		try{
			stop();

			player = new MediaPlayer();
			player.setDataSource(audio);

			player.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {

					notifier.onCompletion();

				}
			});

			player.prepare();
			player.start();

		}catch(IOException ex) {
			ex.printStackTrace();
		}
		catch(IllegalStateException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public void play(String audio) {
		try{
			stop();

			AssetFileDescriptor afd = context.getAssets().openFd(audio);

			player = new MediaPlayer();
			player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			player.prepare();
			player.start();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		catch(IllegalStateException ex) {
			ex.printStackTrace();
		}
	}
	
	public void playFromSdcard(String audio) {
		try{
			stop();

			player = new MediaPlayer();
			player.setDataSource(audio);
			player.prepare();
			player.start();

		}catch(IOException ex) {
			ex.printStackTrace();
		}
		catch(IllegalStateException ex) {
			ex.printStackTrace();
		}
	}
	
	AsyncTask<String, Void, Boolean> playerAsync;
	ProgressDialog pd;
	
	public void playFromUrlWithCompletion(String streamAudio){
		
		playerAsync = new AsyncTask<String, Void, Boolean>() {

			protected void onPreExecute() {

				stop();
				
				pd = new ProgressDialog(context);
				pd.setCancelable(false);
				pd.setMessage(context.getString(R.string.preparing_audio));
				pd.show();

			};

			@Override
			protected Boolean doInBackground(String... params) {

				String stream = params[0];
				/**
				 * If there is more than 1 link to stream, we should initialize the MediaPlayer
				 */
				if(stream != null)
				{
					if(stream.length() != 0){
						return initMediaPlayer(stream);
					}
				}
				
				return false;

			}

			@Override
			protected void onPostExecute(Boolean result) {
				pd.dismiss();
				
				if(result){
					/**
					 * Starting MediaPlayer...
					 */
					player.start();
					
				}
				else{
					/**
					 * Killing MediaPlayer...
					 */
					if(!isCancelled())
					{
						stop();		
					}

				}

				cancel(true);
			}

		}.execute(streamAudio);
	}
	
	private boolean initMediaPlayer(String streamAudio){
		try{
			stop();

			player = MediaPlayer.create(context, Uri.parse(streamAudio));

			player.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {

					notifier.onCompletion();
					stop();

				}
			});
			player.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {
				
				@Override
				public void onBufferingUpdate(MediaPlayer arg0, int arg1) {
					// TODO Auto-generated method stub
				}
			});
			player.setOnInfoListener(new OnInfoListener() {
				
				@Override
				public boolean onInfo(MediaPlayer arg0, int arg1, int arg2) {
					// TODO Auto-generated method stub
					return false;
				}
			});
			player.setOnPreparedListener(new OnPreparedListener() {
				
				@Override
				public void onPrepared(MediaPlayer arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			player.setOnErrorListener(new OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
					stop();
					return false;
				}
			});

		} catch(IllegalStateException ex) {
			ex.printStackTrace();
			return false;
		}
		
		return true;
	}
}
