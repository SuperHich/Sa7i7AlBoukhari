package com.sa7i7alboukhari.entity;

import java.util.ArrayList;

public class Book extends SABEntity{
	
	private String name;
	private ArrayList<Chapter> chapters = new ArrayList<Chapter>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Chapter> getChapters() {
		return chapters;
	}
	public void setChapters(ArrayList<Chapter> chapters) {
		this.chapters = chapters;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID " + getId() + "\n");
		sb.append("Name " + getName() + "\n");
		sb.append("chapters size " + (chapters != null ? chapters.size() : "0"));
		return sb.toString();
	}

}
