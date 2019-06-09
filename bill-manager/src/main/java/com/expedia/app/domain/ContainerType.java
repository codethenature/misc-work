package com.expedia.app.domain;

public enum ContainerType {
	BOTTLE("bottle"), PACK("pack"), BOX("box"), NA("na");
	
	String name;
	
	ContainerType(String val) {
		this.name=val;
	}
	
	public String getValue( ) {
		return name;
	}
}
