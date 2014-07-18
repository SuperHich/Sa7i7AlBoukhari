package com.sa7i7alboukhari.entity;

import java.util.ArrayList;

public class Chapter extends SABEntity{
	
	private String name;
	private int bookId;
	private ArrayList<Hadith> ahadith = new ArrayList<Hadith>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public ArrayList<Hadith> getAhadith() {
		return ahadith;
	}
	public void setAhadith(ArrayList<Hadith> ahadith) {
		this.ahadith = ahadith;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID " + getId() + "\n");
		sb.append("BookID " + getBookId() + "\n");
		sb.append("Name " + getName() + "\n");
		sb.append("Ahadith size " + (ahadith != null ? ahadith.size() : "0"));
		return sb.toString();
	}
	
	

}
