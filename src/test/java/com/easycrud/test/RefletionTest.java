package com.easycrud.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;

import org.junit.Test;

import com.easycrud.model.ProdutoTest;
import com.google.common.reflect.ClassPath;
import com.jeasier.util.FieldUtil;

public class RefletionTest {

	@Test
	public void getPath() throws URISyntaxException, IOException {
		// EasyJavaAplication app = new EasyJavaAplication();
		// app.generateCrud(ProdutoTest.class);

		final ClassLoader loader = Thread.currentThread().getContextClassLoader();

		for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
		  if (info.getName().startsWith("com.easyjava.generator")) {
		    final Class<?> clazz = info.load();
		    System.out.println(clazz);
		  }
		}

	}

	// @Test
	// public void fields() {
	// Class<Produto> prod = Produto.class;
	// for (Field field : prod.getDeclaredFields()) {
	// System.out.println(field);
	// }
	// imports(Produto.class);
	// }

	private String imports(Class<?> gclass) {

		StringBuilder imports = new StringBuilder();

		for (Field field : gclass.getDeclaredFields()) {
			System.out.println(field);
			imports.append(FieldUtil.getImport(field));
		}

		return imports.toString();

	}

	@Test
	public void loadPackageDefault() {
		Class<ProdutoTest> produto = ProdutoTest.class;
		String packageSplit[] = produto.getPackage().getName().split("\\.");
		String packageDefault = "";
		for (int i = 0; i < packageSplit.length; i++) {
			if (!packageSplit[i].equals(packageSplit[packageSplit.length - 1])) {
				packageDefault += packageSplit[i] + ".";
			}

		}
		System.out.println(packageDefault);
	}

	private void criarPastasCasoNaoExista(String path) {
		File theDir = new File(path);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("creating directory: " + theDir.getName());
			boolean result = false;

			try {
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				System.out.println("DIR created");
			}
		}
	}

}
