package com.jeasier.generator.view;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;

import com.jeasier.app.EasyJavaAplication;
import com.jeasier.model.TemplateFormat;
import com.jeasier.util.EasyJavaProperties;
import com.jeasier.util.EasyJavaUtil;
import com.jeasier.util.FieldUtil;
import com.jeasier.util.IOUtil;
import com.jeasier.util.OSValidator;

/**
 * 
 * @author João Vitor Feitosa
 * @since 0.0.1
 */

public class ThymeleafCreatorGenerator {

	private EasyJavaProperties prop;

	private TemplateFormat templateFormat;

	public ThymeleafCreatorGenerator(EasyJavaProperties properties, TemplateFormat templateFormat) {
		this.prop = properties;
		this.templateFormat = templateFormat;
	}

	// defaults
	public static final String TEMPLATE = "/templates/thymeleaf/ThymeleafCreate.txt";

	public static final String TEMPLATE_CREATE_FIELD = "/templates/thymeleaf/ThymeleafCreateField.txt";

	public static final String TEMPLATE_CREATE_FIELD_BOOLEAN = "/templates/thymeleaf/ThymeleafCreateFieldBoolean.txt";

	public static final String TEMPLATE_CREATE_FIELD_ENUMERATION = "/templates/thymeleaf/ThymeleafCreateFieldEnum.txt";

	public static final String TEMPLATE_CREATE_FIELD_CLASS = "/templates/thymeleaf/ThymeleafCreateFieldClass.txt";

	public String generateContent(Class<?> gClass) throws URISyntaxException {
		prop.getProp().setProperty("entity", gClass.getSimpleName());

		StringBuilder template;

		if (this.templateFormat == null) {
			System.out.println("=========");
			System.out.println(TEMPLATE);
			System.out.println("=========");
			template = new StringBuilder(IOUtil.lerArquivo(EasyJavaAplication.class.getResource(TEMPLATE).getFile()));
		} else {
			
			System.out.println("=========");
			System.out.println(this.templateFormat.getCreatePage());
			System.out.println("=========");
			
			
			template = new StringBuilder(IOUtil
					.lerArquivo(EasyJavaAplication.class.getResource(this.templateFormat.getCreatePage()).getFile()));

		}
		
		FieldUtil.replaceAll(template, "${decorator}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("layoutDecorator")));
		FieldUtil.replaceAll(template, "${entityField}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("entity")));

		FieldUtil.replaceAll(template, "${new}", prop.getProp().getProperty("new"));
		FieldUtil.replaceAll(template, "${list}", prop.getProp().getProperty("list"));
		FieldUtil.replaceAll(template, "${listMessage}", prop.getProp().getProperty("listMessage"));
		FieldUtil.replaceAll(template, "${entity}", prop.getProp().getProperty("entity"));
		FieldUtil.replaceAll(template, "${fields}", generateFields(gClass));
		FieldUtil.replaceAll(template, "${atributePrimaryKey}",
				FieldUtil.getFieldName(FieldUtil.getPrimaryKey(gClass)));

		return template.toString();
	}

	public String generateContent(Class<?> gClass, TemplateFormat templateFormat) throws URISyntaxException {
		prop.getProp().setProperty("entity", gClass.getSimpleName());

		StringBuilder template = new StringBuilder(
				IOUtil.lerArquivo(EasyJavaAplication.class.getResource(templateFormat.getCreatePage()).getFile()));

		FieldUtil.replaceAll(template, "${entityField}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("entity")));

		FieldUtil.replaceAll(template, "${new}", prop.getProp().getProperty("new"));
		FieldUtil.replaceAll(template, "${list}", prop.getProp().getProperty("list"));
		FieldUtil.replaceAll(template, "${listMessage}", prop.getProp().getProperty("listMessage"));
		FieldUtil.replaceAll(template, "${entity}", prop.getProp().getProperty("entity"));
		FieldUtil.replaceAll(template, "${fields}", generateFields(gClass, templateFormat));
		FieldUtil.replaceAll(template, "${atributePrimaryKey}",
				FieldUtil.getFieldName(FieldUtil.getPrimaryKey(gClass)));

		return template.toString();
	}

	public void generateClass(Class<?> gClass) throws FileNotFoundException, URISyntaxException {

		String pathToSave = EasyJavaUtil.getPathResources(gClass) + "templates" + OSValidator.getOsSeparator()
				+ FieldUtil.getFieldFromClass(prop.getProp().getProperty("entity")) + OSValidator.getOsSeparator();

		String fileName = prop.getProp().getProperty("new") + ".html";

		System.out.println("Path " + pathToSave);
		System.out.println("Nome arquivo " + fileName);
		IOUtil.criarPastasCasoNaoExista(pathToSave);
		IOUtil.gravarArquivo(generateContent(gClass), pathToSave, fileName);
	}

	private String generateFields(Class<?> gClass) {
		StringBuilder sb = new StringBuilder();

		for (Field field : gClass.getDeclaredFields()) {
			if (!FieldUtil.getFieldName(FieldUtil.getPrimaryKey(gClass)).equals(FieldUtil.getFieldName(field))) {
				sb.append(generateThymeleafField(field, gClass));
			}

		}

		return sb.toString();
	}

	private String generateFields(Class<?> gClass, TemplateFormat format) {
		StringBuilder sb = new StringBuilder();

		for (Field field : gClass.getDeclaredFields()) {
			if (!FieldUtil.getFieldName(FieldUtil.getPrimaryKey(gClass)).equals(FieldUtil.getFieldName(field))) {
				sb.append(generateThymeleafField(field, gClass, format));
			}

		}

		return sb.toString();
	}

	private String generateThymeleafField(Field field, Class<?> gClass) {

		StringBuilder template = new StringBuilder();

		if (FieldUtil.fieldIsEnumeration(field)) {
			template = new StringBuilder(IOUtil
					.lerArquivo(EasyJavaAplication.class.getResource(TEMPLATE_CREATE_FIELD_ENUMERATION).getFile()));
			FieldUtil.replaceAll(template, "${entityField}", FieldUtil.getFieldName(field));
			FieldUtil.replaceAll(template, "${upperEntityField}", FieldUtil.getFieldNameUpperFirst(field));

		} else if (FieldUtil.fieldIsRecognizedJavaClass(field)) {
			template = new StringBuilder(
					IOUtil.lerArquivo(EasyJavaAplication.class.getResource(TEMPLATE_CREATE_FIELD).getFile()));
			FieldUtil.replaceAll(template, "${entityField}", FieldUtil.getFieldName(field));
			FieldUtil.replaceAll(template, "${upperEntityField}", FieldUtil.getFieldNameUpperFirst(field));
			template.append("\n");
		} else if (FieldUtil.getClass(field).equals("boolean") || FieldUtil.getClass(field).equals("Boolean")) {
			template = new StringBuilder(
					IOUtil.lerArquivo(EasyJavaAplication.class.getResource(TEMPLATE_CREATE_FIELD_BOOLEAN).getFile()));
			FieldUtil.replaceAll(template, "${entityField}", FieldUtil.getFieldName(field));
			FieldUtil.replaceAll(template, "${upperEntityField}", FieldUtil.getFieldNameUpperFirst(field));
			template.append("\n");
		} else if (FieldUtil.fieldHasRecognizedRelationship(field)) {
			template = new StringBuilder(
					IOUtil.lerArquivo(EasyJavaAplication.class.getResource(TEMPLATE_CREATE_FIELD_CLASS).getFile()));

			FieldUtil.replaceAll(template, "${entityField}", FieldUtil.getFieldName(field));
			FieldUtil.replaceAll(template, "${upperEntityField}", FieldUtil.getFieldNameUpperFirst(field));
			FieldUtil.replaceAll(template, "${repositoryList}", FieldUtil.getFieldName(field) + "List");
			FieldUtil.replaceAll(template, "${atributePrimaryKey}",
					FieldUtil.getFieldName(FieldUtil.getPrimaryKey(gClass)));

			template.append("\n");
		}

		return template.toString();

	}

	private String generateThymeleafField(Field field, Class<?> gClass, TemplateFormat templateFormat) {

		StringBuilder template = new StringBuilder();

		if (FieldUtil.fieldIsEnumeration(field)) {
			template = new StringBuilder(IOUtil
					.lerArquivo(EasyJavaAplication.class.getResource(templateFormat.getCreateFieldEnum()).getFile()));
			FieldUtil.replaceAll(template, "${entityField}", FieldUtil.getFieldName(field));
			FieldUtil.replaceAll(template, "${upperEntityField}", FieldUtil.getFieldNameUpperFirst(field));

		} else if (FieldUtil.fieldIsRecognizedJavaClass(field)) {
			template = new StringBuilder(
					IOUtil.lerArquivo(EasyJavaAplication.class.getResource(templateFormat.getCreateField()).getFile()));
			FieldUtil.replaceAll(template, "${entityField}", FieldUtil.getFieldName(field));
			FieldUtil.replaceAll(template, "${upperEntityField}", FieldUtil.getFieldNameUpperFirst(field));
			template.append("\n");
		} else if (FieldUtil.getClass(field).equals("boolean") || FieldUtil.getClass(field).equals("Boolean")) {
			template = new StringBuilder(IOUtil.lerArquivo(
					EasyJavaAplication.class.getResource(templateFormat.getCreateFieldBoolean()).getFile()));
			FieldUtil.replaceAll(template, "${entityField}", FieldUtil.getFieldName(field));
			FieldUtil.replaceAll(template, "${upperEntityField}", FieldUtil.getFieldNameUpperFirst(field));
			template.append("\n");
		} else if (FieldUtil.fieldHasRecognizedRelationship(field)) {
			template = new StringBuilder(IOUtil
					.lerArquivo(EasyJavaAplication.class.getResource(templateFormat.getCreateFieldClass()).getFile()));

			FieldUtil.replaceAll(template, "${entityField}", FieldUtil.getFieldName(field));
			FieldUtil.replaceAll(template, "${upperEntityField}", FieldUtil.getFieldNameUpperFirst(field));
			FieldUtil.replaceAll(template, "${repositoryList}", FieldUtil.getFieldName(field) + "List");
			FieldUtil.replaceAll(template, "${atributePrimaryKey}",
					FieldUtil.getFieldName(FieldUtil.getPrimaryKey(gClass)));

			template.append("\n");
		}

		return template.toString();

	}

}
