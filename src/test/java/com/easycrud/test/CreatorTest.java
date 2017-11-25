package com.easycrud.test;

import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.CharMatcher;

public class CreatorTest {

	@Test
	public void testCreator() throws URISyntaxException {
		// System.out.println(java.nio.file.FileSystems.getDefault().getSeparator());
		// EasyJavaProperties creator = new EasyJavaProperties();
		// creator.loadInformations(ProdutoTest.class);
		// System.out.println(creator);
		StringBuilder input = new StringBuilder("${teste} ${teste}");
		replaceAll(input, "${teste}", "replaceOk");
		System.out.println(input.toString());
	}

	public static void replaceAll(StringBuilder builder, String from, String to) {
		int index = builder.indexOf(from);
		while (index != -1) {
			builder.replace(index, index + from.length(), to);
			index += to.length(); // Move to the end of the replacement
			index = builder.indexOf(from, index);
		}
	}
}
