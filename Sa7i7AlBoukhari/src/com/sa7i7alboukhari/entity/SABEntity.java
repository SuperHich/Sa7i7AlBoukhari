package com.sa7i7alboukhari.entity;

public class SABEntity implements ISABEntity{

	private int id;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

}
