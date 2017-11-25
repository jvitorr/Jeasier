package com.easycrud.test;

import org.junit.Test;

import com.jeasier.util.GeneratorUtils;

public class GeneratorsTest {

	@Test
	public void testModelAndView() {
		GeneratorUtils gn = new GeneratorUtils();
		System.out.println(gn.generateModelAndViewAddObject("mv", "list"));
		System.out.println(gn.generateAutowiredFields("Teste", "teste"));
		System.out.println(gn.generateImport("pacote", "Classe"));
	
	}

}
