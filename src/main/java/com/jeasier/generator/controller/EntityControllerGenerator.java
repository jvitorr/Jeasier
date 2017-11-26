package com.jeasier.generator.controller;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;

import com.jeasier.app.JeasyAplication;
import com.jeasier.util.EasyJavaProperties;
import com.jeasier.util.EasyJavaUtil;
import com.jeasier.util.FieldUtil;
import com.jeasier.util.GeneratorUtils;
import com.jeasier.util.IOUtil;

public class EntityControllerGenerator {

	private EasyJavaProperties prop;

	private GeneratorUtils generator = new GeneratorUtils();

	public EntityControllerGenerator(EasyJavaProperties properties) {
		this.prop = properties;
	}

	public static final String TEMPLATE = "/templates/controller/Controller.txt";

	private StringBuilder enumImports = new StringBuilder();
	private StringBuilder enumMv = new StringBuilder();
	private StringBuilder importClassesModels = new StringBuilder();
	private StringBuilder classesMv = new StringBuilder();
	private StringBuilder repositoryClasses = new StringBuilder();

	public String generateContent(Class<?> gClass) throws URISyntaxException {
		prop.getProp().setProperty("entity", gClass.getSimpleName());

		StringBuilder template = new StringBuilder(
				IOUtil.lerArquivo(JeasyAplication.class.getResource(TEMPLATE).getFile()));
		validateEnumerations(gClass.getDeclaredFields());
		validateClassesModel(gClass.getDeclaredFields());

		// controller
		FieldUtil.replaceAll(template, "${packageController}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("packageController")));
		FieldUtil.replaceAll(template, "${controller}", gClass.getSimpleName() + "Controller");

		// imports
		FieldUtil.replaceAll(template, "${packageFilter}",
				prop.getProp().getProperty("packageFilter") + "." + prop.getProp().getProperty("filter"));
		FieldUtil.replaceAll(template, "${packageEntity}",
				prop.getProp().getProperty("packageEntity") + "." + prop.getProp().getProperty("entity"));
		FieldUtil.replaceAll(template, "${packageRepository}",
				prop.getProp().getProperty("packageRepository") + "." + prop.getProp().getProperty("repository"));
		FieldUtil.replaceAll(template, "${packageService}",
				prop.getProp().getProperty("packageService") + "." + prop.getProp().getProperty("service"));
		FieldUtil.replaceAll(template, "${packageEnuns}", enumImports.toString());
		FieldUtil.replaceAll(template, "${packageClasses}", importClassesModels.toString());
		FieldUtil.replaceAll(template, "${packagePageWrapper}",
				prop.getProp().getProperty("packagePageWrapper") + "." + prop.getProp().getProperty("pageWrapper"));

		// classes
		FieldUtil.replaceAll(template, "${service}", prop.getProp().getProperty("service"));
		FieldUtil.replaceAll(template, "${repository}", prop.getProp().getProperty("repository"));
		FieldUtil.replaceAll(template, "${entity}", prop.getProp().getProperty("entity"));
		FieldUtil.replaceAll(template, "${pageWrapper}", prop.getProp().getProperty("pageWrapper"));
		FieldUtil.replaceAll(template, "${filter}", prop.getProp().getProperty("filter"));

		// fields
		FieldUtil.replaceAll(template, "${entityField}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("entity")));
		FieldUtil.replaceAll(template, "${serviceField}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("service")));
		FieldUtil.replaceAll(template, "${repositoryField}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("repository")));
		FieldUtil.replaceAll(template, "${filterField}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("filter")));

		// path
		FieldUtil.replaceAll(template, "${new}", prop.getProp().getProperty("new"));
		FieldUtil.replaceAll(template, "${list}", prop.getProp().getProperty("list"));

		// others
		FieldUtil.replaceAll(template, "${typePrimaryKey}", FieldUtil.getPrimaryKey(gClass).getType().getSimpleName());
		FieldUtil.replaceAll(template, "${atributePrimaryKey}",
				FieldUtil.getFieldNameUpperFirst(FieldUtil.getPrimaryKey(gClass)));
		FieldUtil.replaceAll(template, "${imprementationEnumerations}", enumMv.toString());
		FieldUtil.replaceAll(template, "${implementationModelClasses}", classesMv.toString());
		FieldUtil.replaceAll(template, "${instancesFieldsRepository}", repositoryClasses.toString());

		// messages
		FieldUtil.replaceAll(template, "${sucessDelete}", prop.getProp().getProperty("sucessDelete"));
		FieldUtil.replaceAll(template, "${errorDelete}", prop.getProp().getProperty("errorDelete"));
		FieldUtil.replaceAll(template, "${sucessCreate}", prop.getProp().getProperty("sucessCreate"));
		FieldUtil.replaceAll(template, "${titleCreate}", prop.getProp().getProperty("titleCreate"));
		FieldUtil.replaceAll(template, "${titleCreateButton}", prop.getProp().getProperty("titleCreateButton"));
		FieldUtil.replaceAll(template, "${titleCreateEdit}", prop.getProp().getProperty("titleCreateEdit"));
		FieldUtil.replaceAll(template, "${titleCreateEditButton}", prop.getProp().getProperty("titleCreateEditButton"));

		return template.toString();
	}

	public void generateClass(Class<?> gClass)
			throws FileNotFoundException, URISyntaxException {

		String pathToSave = EasyJavaUtil.getPathFile(gClass)
				+ EasyJavaUtil.getPathFromPackage(prop.getProp().getProperty("packageController"));
		String fileName = prop.getProp().getProperty("controller") + ".java";

		System.out.println("Path " + pathToSave);
		System.out.println("Nome arquivo " + fileName);
		IOUtil.criarPastasCasoNaoExista(pathToSave);
		IOUtil.gravarArquivo(generateContent(gClass), pathToSave, fileName);
	}

	public void validateEnumerations(Field[] fields) {

		for (Field field : fields) {

			if (FieldUtil.fieldIsEnumeration(field)) {
				enumMv.append(generator.generateModelAndViewAddObject("mv", FieldUtil.getFieldName(field) + "List",
						FieldUtil.getClass(field) + ".values()"));
				enumImports.append(
						generator.generateImport(field.getType().getPackage().getName(), FieldUtil.getClass(field)));
			}
		}

	}

	public void validateClassesModel(Field[] fields) {

		for (Field field : fields) {

			if (FieldUtil.fieldHasRecognizedRelationship(field)) {

				// importClasses
				classesMv.append(generator.generateModelAndViewAddObject("mv", (FieldUtil.getFieldName(field) + "List"),
						FieldUtil.getFieldName(field) + prop.getProp().getProperty("repositorySufix") + ".findAll()"));

				// importRepositoryToClasses
				importClassesModels.append(generator.generateImport(prop.getProp().getProperty("packageRepository"),
						FieldUtil.getClass(field) + prop.getProp().getProperty("repositorySufix")));

				// repositoryClassesModel
				repositoryClasses.append(generator.generateAutowiredFields(
						FieldUtil.getClass(field) + prop.getProp().getProperty("repositorySufix"),
						FieldUtil.getFieldName(field) + prop.getProp().getProperty("repositorySufix")));

			}

		}

	}

}
