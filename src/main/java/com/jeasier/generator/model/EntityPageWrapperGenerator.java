package com.jeasier.generator.model;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import com.jeasier.app.JeasyAplication;
import com.jeasier.util.EasyJavaProperties;
import com.jeasier.util.EasyJavaUtil;
import com.jeasier.util.FieldUtil;
import com.jeasier.util.IOUtil;


/**
 * 
 * @author João Vitor Feitosa
 * @since 0.0.1
 */

public class EntityPageWrapperGenerator {

	private EasyJavaProperties prop;

	public EntityPageWrapperGenerator(EasyJavaProperties properties) {
		this.prop = properties;
	}

	public static final String TEMPLATE = "/templates/model/PageWrapper.txt";

	public String generateContent(Class<?> gClass) throws URISyntaxException {
		prop.getProp().setProperty("entity", gClass.getSimpleName());
		StringBuilder template = new StringBuilder(
				IOUtil.lerArquivo(JeasyAplication.class.getResource(TEMPLATE).getFile()));

		// PageWrapper
		FieldUtil.replaceAll(template, "${packagePageWrapper}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("packagePageWrapper")));

		FieldUtil.replaceAll(template, "${pageWrapper}", prop.getProp().getProperty("pageWrapper"));

		return template.toString();
	}

	public void generateClass(Class<?> gClass) throws FileNotFoundException, URISyntaxException {

		String pathToSave = EasyJavaUtil.getPathFile(gClass)
				+ EasyJavaUtil.getPathFromPackage(prop.getProp().getProperty("packagePageWrapper"));
		String fileName = prop.getProp().getProperty("pageWrapper") + ".java";

		System.out.println("Path " + pathToSave);
		System.out.println("Nome arquivo " + fileName);
		IOUtil.criarPastasCasoNaoExista(pathToSave);
		IOUtil.gravarArquivo(generateContent(gClass), pathToSave, fileName);
	}

}
