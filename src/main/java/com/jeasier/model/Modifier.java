package com.jeasier.model;

public enum Modifier {

	PRIVATE("private"), PUBLIC("public");

	public String description;

	private Modifier(String description) {
		this.description = description;
	}

}
