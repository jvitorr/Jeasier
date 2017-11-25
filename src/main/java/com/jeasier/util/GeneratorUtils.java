package com.jeasier.util;

public class GeneratorUtils {

	public String generateModelAndViewAddObject(String mvVariable, String attributeName, String attributeValue) {
		StringBuilder sb = new StringBuilder();
		sb.append(mvVariable).append(".addObject(").append("\"" + attributeName + "\",").append(attributeValue)
				.append(");");
		return sb.toString();
	}

	public String generateModelAndViewAddObject(String mvVariable, String attributeValue) {
		StringBuilder sb = new StringBuilder();
		sb.append(mvVariable).append(".addObject(").append(attributeValue).append(");");
		return sb.toString();
	}

	public String generateImport(String gpackage, String gClass) {
		StringBuilder sb = new StringBuilder();
		sb.append("import ").append(gpackage).append(".").append(gClass).append(";");
		return sb.toString();
	}

	public String generateAutowiredFields(String type, String gClass) {
		StringBuilder sb = new StringBuilder();
		sb.append("@Autowired ").append("private ").append(type).append(" ").append(gClass).append(";");
		return sb.toString();
	}

}
