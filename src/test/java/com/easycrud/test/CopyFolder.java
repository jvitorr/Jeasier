package com.easycrud.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class CopyFolder {

	@Test
	public void copyFolder() throws IOException {
		FileUtils.copyDirectory(new File("C:\\Users\\ea9566\\Desktop\\Pasta A"),
				new File("C:\\Users\\ea9566\\Desktop\\Pasta B"));
	}

}
