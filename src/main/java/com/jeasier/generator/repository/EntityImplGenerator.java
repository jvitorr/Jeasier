package com.jeasier.generator.repository;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;

import com.jeasier.util.EasyJavaProperties;
import com.jeasier.util.EasyJavaUtil;
import com.jeasier.util.FieldUtil;
import com.jeasier.util.IOUtil;

/**
 * 
 * @author João Vitor Feitosa
 * @since 0.0.1
 */

public class EntityImplGenerator {

	private EasyJavaProperties prop;

	public EntityImplGenerator(EasyJavaProperties properties) {
		this.prop = properties;
	}

	public static final String TEMPLATE = "/templates/repository/Impl.txt";

	public static final String TEMPLATE_FILTER_STRING = "/templates/repository/FieldFilterString.txt";

	public static final String TEMPLATE_FILTER_ANY_CLASS = "/templates/repository/FieldFilterClass.txt";

	public static final String TEMPLATE_FILTER_BOOLEAN = "/templates/repository/FieldFilterBoolean.txt";

	public String generateContent(Class<?> gClass) throws URISyntaxException {
		prop.getProp().setProperty("entity", gClass.getSimpleName());

		StringBuilder template = new StringBuilder(IOUtil.lerArquivo(Class.class.getResource(TEMPLATE).getFile()));

		// impl
		FieldUtil.replaceAll(template, "${packageImpl}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("packageImpl")));
		FieldUtil.replaceAll(template, "${impl}", prop.getProp().getProperty("impl"));

		// imports
		FieldUtil.replaceAll(template, "${packageFilter}",
				prop.getProp().getProperty("packageFilter") + "." + prop.getProp().getProperty("filter"));
		FieldUtil.replaceAll(template, "${packageEntity}",
				prop.getProp().getProperty("packageEntity") + "." + prop.getProp().getProperty("entity"));
		FieldUtil.replaceAll(template, "${packageHelper}",
				prop.getProp().getProperty("packageHelper") + "." + prop.getProp().getProperty("helper"));

		// classes
		FieldUtil.replaceAll(template, "${entity}", prop.getProp().getProperty("entity"));
		FieldUtil.replaceAll(template, "${helper}", prop.getProp().getProperty("helper"));
		FieldUtil.replaceAll(template, "${filter}", prop.getProp().getProperty("filter"));
		FieldUtil.replaceAll(template, "${filterField}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("filter")));

		// others
		FieldUtil.replaceAll(template, "${filters}", generateFilters(gClass.getDeclaredFields()));

		return template.toString();
	}

	public void generateClass(Class<?> gClass) throws FileNotFoundException, URISyntaxException {

		String pathToSave = EasyJavaUtil.getPathFile(gClass)
				+ EasyJavaUtil.getPathFromPackage(prop.getProp().getProperty("packageImpl"));
		String fileName = prop.getProp().getProperty("impl") + ".java";

		System.out.println("Path " + pathToSave);
		System.out.println("Nome arquivo " + fileName);
		IOUtil.criarPastasCasoNaoExista(pathToSave);
		IOUtil.gravarArquivo(generateContent(gClass), pathToSave, fileName);
	}

	private String generateFilters(Field[] fields) {
		StringBuilder sb = new StringBuilder();

		for (Field field : fields) {

			if (FieldUtil.isValidField(field)) {

				sb.append(generateFilter(field));
			}
		}

		return sb.toString();
	}

	private String generateFilter(Field field) {

		StringBuilder template;

		if (FieldUtil.getClass(field).equals("String")) {
			template = new StringBuilder(IOUtil.lerArquivo(Class.class.getResource(TEMPLATE_FILTER_STRING).getFile()));

			FieldUtil.replaceAll(template, "${upperAtribute}", FieldUtil.getFieldNameUpperFirst(field));
			FieldUtil.replaceAll(template, "${atribute}", FieldUtil.getFieldName(field));
			FieldUtil.replaceAll(template, "${filterField}",
					FieldUtil.getFieldFromClass(prop.getProp().getProperty("filter")));

			template.append("\n");

		} else if (FieldUtil.getClass(field).equals("boolean")) {
			template = new StringBuilder(IOUtil.lerArquivo(Class.class.getResource(TEMPLATE_FILTER_BOOLEAN).getFile()));
			FieldUtil.replaceAll(template, "${upperAtribute}", FieldUtil.getFieldNameUpperFirst(field));
			FieldUtil.replaceAll(template, "${atribute}", FieldUtil.getFieldName(field));
			FieldUtil.replaceAll(template, "${filterField}",
					FieldUtil.getFieldFromClass(prop.getProp().getProperty("filter")));
			template.append("\n");
		} else {
			template = new StringBuilder(
					IOUtil.lerArquivo(Class.class.getResource(TEMPLATE_FILTER_ANY_CLASS).getFile()));
			FieldUtil.replaceAll(template, "${upperAtribute}", FieldUtil.getFieldNameUpperFirst(field));
			FieldUtil.replaceAll(template, "${atribute}", FieldUtil.getFieldName(field));
			FieldUtil.replaceAll(template, "${filterField}",
					FieldUtil.getFieldFromClass(prop.getProp().getProperty("filter")));
			template.append("\n");
		}

		return template.toString();

	}

}
