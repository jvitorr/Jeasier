package com.jeasier.util;

import java.lang.reflect.Field;

import com.jeasier.model.Modifier;

public class FieldUtil {

	public static void main(String[] args) {
		// Class<ProdutoTest> classe = ProdutoTest.class;
		// for (Field field : classe.getDeclaredFields()) {
		//
		// for (java.lang.annotation.Annotation a : field.getAnnotations()) {
		// System.out.println(a.annotationType().getSimpleName());
		// }
		//
		// }
	}

	public static Field getPrimaryKey(Class<?> gclass) {

		for (Field field : gclass.getDeclaredFields()) {

			for (java.lang.annotation.Annotation a : field.getAnnotations()) {

				if (a.annotationType().getSimpleName().equals("Id")) {
					return field;
				}
			}

		}

		return null;
	}

	public static String generateField(Field field) {
		StringBuilder result = new StringBuilder();
		result.append(Modifier.PRIVATE.description);
		result.append(" ");
		result.append(getClass(field));
		result.append(" ");
		result.append(getFieldName(field));
		result.append(";\n");
		return result.toString();
	}

	public static String generateGetter(Field field) {
		StringBuilder result = new StringBuilder();

		if (getClass(field).equals("boolean")) {
			result.append(Modifier.PUBLIC.description);
			result.append(" ");
			result.append(getClass(field));
			result.append(" is");
			result.append(getFieldNameUpperFirst(field));
			result.append("() {\n");
			result.append("  return this." + getFieldName(field) + ";\n}\n");
		} else {
			result.append(Modifier.PUBLIC.description);
			result.append(" ");
			result.append(getClass(field));
			result.append(" get");
			result.append(getFieldNameUpperFirst(field));
			result.append("() {\n");
			result.append("  return this." + getFieldName(field) + ";\n}\n");

		}

		return result.toString();
	}

	public static String generateSetter(Field field) {
		StringBuilder result = new StringBuilder();

		result.append(Modifier.PUBLIC.description);
		result.append(" void");
		result.append(" set");
		result.append(getFieldNameUpperFirst(field));
		result.append("(" + getClass(field) + " " + getFieldName(field) + ") {\n");
		result.append("  this." + getFieldName(field) + " = " + getFieldName(field) + ";\n}\n");

		return result.toString();
	}

	public static String getImport(Field field) {
		return field.getType().getPackage().getName();

	}

	public static String getFieldName(Field field) {
		return field.getName();

	}

	public static String getFieldNameUpperFirst(Field field) {

		return getFieldName(field).substring(0, 1).toUpperCase() + getFieldName(field).substring(1);

	}

	public static String getFieldFromClass(String className) {

		return className.substring(0, 1).toLowerCase() + className.substring(1);

	}

	public static String getClass(Field field) {
		return field.getType().getSimpleName();

	}

	public static boolean fieldIsRecognizedJavaClass(Field field) {
		if (FieldUtil.getClass(field).equals("String") || FieldUtil.getClass(field).equals("Integer")
				|| FieldUtil.getClass(field).equals("Double") || FieldUtil.getClass(field).equals("Long")
				|| FieldUtil.getClass(field).equals("Float") || FieldUtil.getClass(field).equals("BigDecimal")
				|| FieldUtil.getClass(field).equals("double") || FieldUtil.getClass(field).equals("float")
				|| FieldUtil.getClass(field).equals("long") || FieldUtil.getClass(field).equals("short")
				|| FieldUtil.getClass(field).equals("char") || FieldUtil.getClass(field).equals("byte")
				|| FieldUtil.getClass(field).equals("Byte") || FieldUtil.getClass(field).equals("Short")
				|| FieldUtil.getClass(field).equals("Long") || FieldUtil.getClass(field).equals("Float")
				|| FieldUtil.getClass(field).equals("Double") || FieldUtil.getClass(field).equals("Character")
				|| FieldUtil.getClass(field).equals("Date")) {
			return true;
		}

		return false;
	}

	public static boolean fieldIsEnumeration(Field field) {
		if (field.getType().isEnum()) {
			return true;
		}

		return false;
	}

	public static boolean fieldHasRecognizedRelationship(Field field) {
		for (java.lang.annotation.Annotation a : field.getAnnotations()) {

			if (a.annotationType().getSimpleName().equals("ManyToOne")
					|| a.annotationType().getSimpleName().equals("OneToOne")) {
				return true;

			}
		}

		return false;
	}

	public static void replaceAll(StringBuilder builder, String from, String to) {
		int index = builder.indexOf(from);
		while (index != -1) {
			builder.replace(index, index + from.length(), to);
			index += to.length(); //
			index = builder.indexOf(from, index);
		}
	}

}
