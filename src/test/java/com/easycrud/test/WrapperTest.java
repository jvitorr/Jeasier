package com.easycrud.test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import com.jeasier.app.JeasierAplication;

public class WrapperTest {
	public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
		System.out.println(JeasierAplication.class.getResource("/templates/model/PageWrapper.txt").getFile());
		//System.out.println(Produto.class.getClassLoader().getResource("src/main/resources/"));

	}

}
