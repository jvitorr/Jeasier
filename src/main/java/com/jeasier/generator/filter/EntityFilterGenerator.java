package com.jeasier.generator.filter;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;

import com.jeasier.app.EasyJavaAplication;
import com.jeasier.util.EasyJavaProperties;
import com.jeasier.util.EasyJavaUtil;
import com.jeasier.util.FieldUtil;
import com.jeasier.util.GeneratorUtils;
import com.jeasier.util.IOUtil;

public class EntityFilterGenerator {

	private EasyJavaProperties prop;

	public EntityFilterGenerator(EasyJavaProperties properties) {
		this.prop = properties;
	}

	public static final String TEMPLATE = "/templates/filter/Filter.txt";

	public String generateContent(Class<?> gClass) throws URISyntaxException {
		StringBuilder template = new StringBuilder(
				IOUtil.lerArquivo(EasyJavaAplication.class.getResource(TEMPLATE).getFile()));
		// filter
		FieldUtil.replaceAll(template, "${packageFilter}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("packageFilter")));

		FieldUtil.replaceAll(template, "${filter}", prop.getProp().getProperty("filter"));

		// imports
		FieldUtil.replaceAll(template, "${imports}", imports(gClass));

		// fields
		FieldUtil.replaceAll(template, "${fields}", generateFields(gClass));

		return template.toString();
	}

	public void generateClass(Class<?> gClass) throws FileNotFoundException, URISyntaxException {

		String pathToSave = EasyJavaUtil.getPathFile(gClass)
				+ EasyJavaUtil.getPathFromPackage(prop.getProp().getProperty("packageFilter"));
		String fileName = prop.getProp().getProperty("filter") + ".java";

		System.out.println("Path " + pathToSave);
		System.out.println("Nome arquivo " + fileName);
		IOUtil.criarPastasCasoNaoExista(pathToSave);
		IOUtil.gravarArquivo(generateContent(gClass), pathToSave, fileName);
	}

	private String generateFields(Class<?> gclass) {
		StringBuilder fieldsResult = new StringBuilder();
		StringBuilder getterSetters = new StringBuilder();
		for (Field field : gclass.getDeclaredFields()) {
			fieldsResult.append(FieldUtil.generateField(field));
		}
		fieldsResult.append("\n\n");

		for (Field field : gclass.getDeclaredFields()) {
			getterSetters.append(FieldUtil.generateGetter(field));
			getterSetters.append(FieldUtil.generateSetter(field));
		}

		return fieldsResult.append(getterSetters.toString()).toString();

	}

	private String imports(Class<?> gclass) {

		StringBuilder imports = new StringBuilder();

		for (Field field : gclass.getDeclaredFields()) {
			if (field.getType().getPackage() != null) {

				String pathImport = FieldUtil.getImport(field);
				if (!pathImport.equals("java.lang")) {
					imports.append("import " + pathImport + EasyJavaUtil.packageSeparator() + FieldUtil.getClass(field)
							+ ";\n");
				}
			}

		}

		return imports.toString();

	}

}
