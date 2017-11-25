package com.jeasier.util;

import java.net.URISyntaxException;

/**
 * 
 * @author João Vitor Feitosa
 * @since 0.0.1
 */

public class EasyJavaUtil {

	public static String getPackageDefault(Class<?> gClass) throws URISyntaxException {

		String packageSplit[] = gClass.getPackage().getName().split("\\.");
		String packageDefault = "";
		for (int i = 0; i < packageSplit.length; i++) {
			if (!packageSplit[i].equals(packageSplit[packageSplit.length - 1])) {
				if (!packageSplit[i].equals(packageSplit[packageSplit.length - 2])) {
					packageDefault += packageSplit[i] + ".";
				} else {
					packageDefault += packageSplit[i];
				}

			}

		}
		return packageDefault;
	}

	public static String getPathFile(Class<?> gClass) throws URISyntaxException {

		String packageWithSeparator = "src" + OSValidator.getOsSeparator() + "main" + OSValidator.getOsSeparator()
				+ "java" + OSValidator.getOsSeparator() + "";

		return gClass.getResource(gClass.getSimpleName() + ".class").getFile().split("target")[0]
				+ packageWithSeparator;
	}
	
	public static String getPathResources(Class<?> gClass) throws URISyntaxException {

		String packageWithSeparator = "src" + OSValidator.getOsSeparator() + "main" + OSValidator.getOsSeparator()
				+ "resources" + OSValidator.getOsSeparator() + "";

		return gClass.getResource(gClass.getSimpleName() + ".class").getFile().split("target")[0]
				+ packageWithSeparator;
	}

	public static String getPathFromPackage(String gPackage) {

		String packageSplit[] = gPackage.split("\\.");
		String result = "";
		for (int i = 0; i < packageSplit.length; i++) {

			result += packageSplit[i] + OSValidator.getOsSeparator();

		}
		return result;
	}

	public static String packageSeparator() {
		return ".";
	}

}
