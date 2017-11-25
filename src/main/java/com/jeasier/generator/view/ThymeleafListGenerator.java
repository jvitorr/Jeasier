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

public class ThymeleafListGenerator {

	private EasyJavaProperties prop;

	private TemplateFormat templateFormat;

	public ThymeleafListGenerator(EasyJavaProperties properties, TemplateFormat templateFormat) {
		this.prop = properties;
		this.templateFormat = templateFormat;

	}

	private StringBuilder columns;
	private StringBuilder rows;

	public static final String TEMPLATE = "/templates/thymeleaf/thymeleafList.txt";

	public static final String TEMPLATE_CREATE_FIELD = "/templates/thymeleaf/ThymeleafCreateField.txt";

	public static final String TEMPLATE_CREATE_FIELD_BOOLEAN = "/templates/thymeleaf/ThymeleafCreateFieldBoolean.txt";

	public static final String TEMPLATE_CREATE_FIELD_ENUMERATION = "/templates/thymeleaf/ThymeleafCreateFieldEnum.txt";

	public static final String TEMPLATE_CREATE_FIELD_CLASS = "/templates/thymeleaf/ThymeleafCreateFieldClass.txt";

	public String generateContent(Class<?> gClass) throws URISyntaxException {
		prop.getProp().setProperty("entity", gClass.getSimpleName());
		loadColumnsAndRow(gClass.getDeclaredFields());
		StringBuilder template;
		if (this.templateFormat == null) {
			template = new StringBuilder(IOUtil.lerArquivo(EasyJavaAplication.class.getResource(TEMPLATE).getFile()));
		} else {
			template = new StringBuilder(IOUtil
					.lerArquivo(EasyJavaAplication.class.getResource(this.templateFormat.getListPage()).getFile()));

		}
		FieldUtil.replaceAll(template, "${decorator}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("layoutDecorator")));
		FieldUtil.replaceAll(template, "${filterField}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("filter")));
		FieldUtil.replaceAll(template, "${entityField}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("entity")));
		FieldUtil.replaceAll(template, "${new}", prop.getProp().getProperty("new"));
		FieldUtil.replaceAll(template, "${list}", prop.getProp().getProperty("list"));
		FieldUtil.replaceAll(template, "${atributePrimaryKey}",
				FieldUtil.getFieldName(FieldUtil.getPrimaryKey(gClass)));
		FieldUtil.replaceAll(template, "${entity}", prop.getProp().getProperty("entity"));
		FieldUtil.replaceAll(template, "${fields}", generateFields(gClass));
		FieldUtil.replaceAll(template, "${columns}", columns.toString());
		FieldUtil.replaceAll(template, "${rows}", rows.toString());
		FieldUtil.replaceAll(template, "${emptyListMessage}", prop.getProp().getProperty("emptyListMessage"));
		FieldUtil.replaceAll(template, "${searchButtonTitle}", prop.getProp().getProperty("searchButtonTitle"));
		FieldUtil.replaceAll(template, "${listMessage}", prop.getProp().getProperty("listMessage"));
		FieldUtil.replaceAll(template, "${createMessage}", prop.getProp().getProperty("createMessage"));

		return template.toString();
	}

	public void generateClass(Class<?> gClass) throws FileNotFoundException, URISyntaxException {

		String pathToSave = EasyJavaUtil.getPathResources(gClass) + "templates" + OSValidator.getOsSeparator()
				+ FieldUtil.getFieldFromClass(prop.getProp().getProperty("entity")) + OSValidator.getOsSeparator();

		String fileName = prop.getProp().getProperty("list") + ".html";

		System.out.println("Path " + pathToSave);
		System.out.println("Nome arquivo " + fileName);
		IOUtil.criarPastasCasoNaoExista(pathToSave);
		IOUtil.gravarArquivo(generateContent(gClass), pathToSave, fileName);
	}

	public void loadColumnsAndRow(Field[] fields) {
		columns = new StringBuilder();
		rows = new StringBuilder();
		for (Field field : fields) {
			columns.append("<td>" + FieldUtil.getFieldName(field) + "</td>\n");
			rows.append(generateRow(field));
		}

		columns.append("<td>#</td>\n");
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

	public String generateRow(Field field) {

		if (FieldUtil.fieldHasRecognizedRelationship(field)) {
			return "<td th:text=\"${obj." + FieldUtil.getFieldName(field) + "."
					+ FieldUtil.getFieldNameUpperFirst(FieldUtil.getPrimaryKey(field.getType())) + "}\" ></td>";

		} else {
			return "<td th:text=\"${obj." + FieldUtil.getFieldName(field) + "}\" ></td>";
		}

	}

	private String generateThymeleafField(Field field, Class<?> gClass) {

		StringBuilder template = new StringBuilder();

		if (FieldUtil.fieldIsEnumeration(field)) {

			if (this.templateFormat == null) {
				template = new StringBuilder(IOUtil
						.lerArquivo(EasyJavaAplication.class.getResource(TEMPLATE_CREATE_FIELD_ENUMERATION).getFile()));
			} else {
				template = new StringBuilder(IOUtil.lerArquivo(
						EasyJavaAplication.class.getResource(this.templateFormat.getCreateFieldEnum()).getFile()));

			}

			FieldUtil.replaceAll(template, "${entityField}", FieldUtil.getFieldName(field));
			FieldUtil.replaceAll(template, "${upperEntityField}", FieldUtil.getFieldNameUpperFirst(field));

		} else if (FieldUtil.fieldIsRecognizedJavaClass(field)) {

			if (this.templateFormat == null) {
				template = new StringBuilder(
						IOUtil.lerArquivo(EasyJavaAplication.class.getResource(TEMPLATE_CREATE_FIELD).getFile()));
			} else {
				template = new StringBuilder(IOUtil.lerArquivo(
						EasyJavaAplication.class.getResource(this.templateFormat.getCreateField()).getFile()));

			}

			FieldUtil.replaceAll(template, "${entityField}", FieldUtil.getFieldName(field));
			FieldUtil.replaceAll(template, "${upperEntityField}", FieldUtil.getFieldNameUpperFirst(field));
			template.append("\n");

		} else if (FieldUtil.getClass(field).equals("boolean") || FieldUtil.getClass(field).equals("Boolean")) {

			if (this.templateFormat == null) {
				template = new StringBuilder(IOUtil
						.lerArquivo(EasyJavaAplication.class.getResource(TEMPLATE_CREATE_FIELD_BOOLEAN).getFile()));
			} else {
				template = new StringBuilder(IOUtil.lerArquivo(
						EasyJavaAplication.class.getResource(this.templateFormat.getCreateFieldBoolean()).getFile()));

			}

			FieldUtil.replaceAll(template, "${entityField}", FieldUtil.getFieldName(field));
			FieldUtil.replaceAll(template, "${upperEntityField}", FieldUtil.getFieldNameUpperFirst(field));
			template.append("\n");
		} else if (FieldUtil.fieldHasRecognizedRelationship(field)) {

			if (this.templateFormat == null) {
				template = new StringBuilder(
						IOUtil.lerArquivo(EasyJavaAplication.class.getResource(TEMPLATE_CREATE_FIELD_CLASS).getFile()));
			} else {
				template = new StringBuilder(IOUtil.lerArquivo(
						EasyJavaAplication.class.getResource(this.templateFormat.getCreateFieldClass()).getFile()));

			}

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
