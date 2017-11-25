package com.easycrud.test;

import java.io.IOException;

import org.junit.Test;

import com.jeasier.util.XProperties;

public class PropertiesTest {
	
	@Test
	public void propertiesTest() throws IOException{
		XProperties prop = new XProperties();
		
		prop.load(Class.class.getResourceAsStream("/easyjava.properties"));
		
		System.out.println(prop.getProperty("impl"));
	
	}

}
