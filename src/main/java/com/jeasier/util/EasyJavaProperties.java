package com.jeasier.util;

import java.io.IOException;

/**
 * 
 * @author João Vitor Feitosa
 * @since 0.0.1
 */

public class EasyJavaProperties {
	private XProperties prop;

	public EasyJavaProperties() throws IOException {
		prop = new XProperties();

		prop.load(Class.class.getResourceAsStream("/easyjava.properties"));

	}

	public XProperties getProp() {
		return prop;
	}

}
