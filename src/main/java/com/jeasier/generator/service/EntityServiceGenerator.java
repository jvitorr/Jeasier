package com.jeasier.generator.service;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import com.jeasier.app.EasyJavaAplication;
import com.jeasier.util.EasyJavaProperties;
import com.jeasier.util.EasyJavaUtil;
import com.jeasier.util.FieldUtil;
import com.jeasier.util.IOUtil;

public class EntityServiceGenerator {

	private EasyJavaProperties prop;

	public EntityServiceGenerator(EasyJavaProperties properties) {
		this.prop = properties;
	}

	public static final String TEMPLATE = "/templates/service/Service.txt";

	public String generateContent(Class<?> gClass) throws URISyntaxException {
		prop.getProp().setProperty("entity", gClass.getSimpleName());
		StringBuilder template = new StringBuilder(
				IOUtil.lerArquivo(EasyJavaAplication.class.getResource(TEMPLATE).getFile()));
		// Service
		FieldUtil.replaceAll(template, "${packageService}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("packageService")));
		FieldUtil.replaceAll(template, "${Service}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("service")));

		// imports
		FieldUtil.replaceAll(template, "${packageFilter}",
				prop.getProp().getProperty("packageFilter") + "." + prop.getProp().getProperty("filter"));
		FieldUtil.replaceAll(template, "${packageEntity}",
				prop.getProp().getProperty("packageEntity") + "." + prop.getProp().getProperty("entity"));
		FieldUtil.replaceAll(template, "${packageRepository}",
				prop.getProp().getProperty("packageRepository") + "." + prop.getProp().getProperty("repository"));

		// classes
		FieldUtil.replaceAll(template, "${repository}", prop.getProp().getProperty("repository"));
		FieldUtil.replaceAll(template, "${entity}", prop.getProp().getProperty("entity"));
		FieldUtil.replaceAll(template, "${service}", prop.getProp().getProperty("service"));
		FieldUtil.replaceAll(template, "${filter}", prop.getProp().getProperty("filter"));

		// fields
		FieldUtil.replaceAll(template, "${entityField}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("entity")));
		FieldUtil.replaceAll(template, "${filterField}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("filter")));
		FieldUtil.replaceAll(template, "${repositoryField}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("repository")));

		// messages
		FieldUtil.replaceAll(template, "${errorDeleteMessage}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("errorDeleteMessage")));

		return template.toString();
	}

	public void generateClass(Class<?> gClass) throws FileNotFoundException, URISyntaxException {

		String pathToSave = EasyJavaUtil.getPathFile(gClass)
				+ EasyJavaUtil.getPathFromPackage(prop.getProp().getProperty("packageService"));
		String fileName = prop.getProp().getProperty("service") + ".java";

		System.out.println("Path " + pathToSave);
		System.out.println("Nome arquivo " + fileName);
		IOUtil.criarPastasCasoNaoExista(pathToSave);
		IOUtil.gravarArquivo(generateContent(gClass), pathToSave, fileName);
	}

}
