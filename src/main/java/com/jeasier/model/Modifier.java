package com.jeasier.model;

/**
 * 
 * @author João Vitor Feitosa
 * @since 0.0.1
 */
public enum Modifier {

	PRIVATE("private"), PUBLIC("public");

	public String description;

	private Modifier(String description) {
		this.description = description;
	}

}
