package com.jeasier.util;

import java.io.IOException;

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
