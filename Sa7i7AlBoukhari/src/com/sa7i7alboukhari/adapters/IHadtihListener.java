package com.sa7i7alboukhari.adapters;

public interface IHadtihListener {
	
	void onHadithShowMore(int position);
	void onHadithListen(int position);
	void onHadithDownload(int position);
	void onHadithFavorite(int position);
	void onHadithComment(int position);
	void onHadithShare(int position);

}
