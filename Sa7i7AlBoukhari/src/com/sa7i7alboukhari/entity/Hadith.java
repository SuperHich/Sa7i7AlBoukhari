package com.sa7i7alboukhari.entity;

public class Hadith extends SABEntity {
	
	private int titleId;
	private String text;
	private String file;
	private int bookId;
	
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID " + getId() + "\n");
		sb.append("BookID " + getBookId() + "\n");
		sb.append("TitleID " + getTitleId() + "\n");
		sb.append("Text " + getText() + "\n");
		sb.append("file " + getFile());
		return sb.toString();
	}
	
}
