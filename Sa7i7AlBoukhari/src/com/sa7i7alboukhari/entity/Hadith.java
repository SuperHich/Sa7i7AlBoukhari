package com.sa7i7alboukhari.entity;

public class Hadith extends SABEntity {
	
	private int titleId;
	private String text;
	private String file;
	private int bookId;
	private String link;
	private int pageId;
	private boolean isDownload = false;
	private boolean isFavorite = false;
	private boolean isShown = false;
	
	public int getTitleId() {
		return titleId;
	}
	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public boolean isDownload() {
		return isDownload;
	}
	public void setDownload(boolean isDownload) {
		this.isDownload = isDownload;
	}
	public boolean isFavorite() {
		return isFavorite;
	}
	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID " + getId() + "\n");
		sb.append("BookID " + getBookId() + "\n");
		sb.append("TitleID " + getTitleId() + "\n");
		sb.append("Text " + getText() + "\n");
		sb.append("file " + getFile() + "\n");
		sb.append("Link " + getLink() + "\n");
		sb.append("Page " + getPageId() + "\n");
		sb.append("isDownload " + isDownload() + "\n");
		sb.append("isFavorite " + isFavorite());
		return sb.toString();
	}
	public boolean isShown() {
		return isShown;
	}
	public void setShown(boolean isShown) {
		this.isShown = isShown;
	}
	
}
