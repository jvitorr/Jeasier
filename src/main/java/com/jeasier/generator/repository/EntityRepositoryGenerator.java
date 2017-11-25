package com.jeasier.generator.repository;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import com.jeasier.app.EasyJavaAplication;
import com.jeasier.util.EasyJavaProperties;
import com.jeasier.util.EasyJavaUtil;
import com.jeasier.util.FieldUtil;
import com.jeasier.util.IOUtil;

public class EntityRepositoryGenerator {

	private EasyJavaProperties prop;

	public EntityRepositoryGenerator(EasyJavaProperties properties) {
		this.prop = properties;
	}

	public static final String TEMPLATE = "/templates/repository/Repository.txt";

	public String generateContent(Class<?> gClass) throws URISyntaxException {
		StringBuilder template = new StringBuilder(
				IOUtil.lerArquivo(EasyJavaAplication.class.getResource(TEMPLATE).getFile()));
		// repository
		FieldUtil.replaceAll(template, "${packageRepository}",
				FieldUtil.getFieldFromClass(prop.getProp().getProperty("packageRepository")));
		FieldUtil.replaceAll(template, "${repository}", prop.getProp().getProperty("repository"));

		// imports
		FieldUtil.replaceAll(template, "${packageEntity}",
				prop.getProp().getProperty("packageEntity") + "." + prop.getProp().getProperty("entity"));
		FieldUtil.replaceAll(template, "${packageHelper}",
				prop.getProp().getProperty("packageHelper") + "." + prop.getProp().getProperty("helper"));

		// classes
		FieldUtil.replaceAll(template, "${entity}", prop.getProp().getProperty("entity"));
		FieldUtil.replaceAll(template, "${typePrimaryKey}", FieldUtil.getPrimaryKey(gClass).getType().getSimpleName());
		FieldUtil.replaceAll(template, "${helper}", prop.getProp().getProperty("helper"));

		return template.toString();
	}

	public void generateClass(Class<?> gClass) throws FileNotFoundException, URISyntaxException {

		String pathToSave = EasyJavaUtil.getPathFile(gClass)
				+ EasyJavaUtil.getPathFromPackage(prop.getProp().getProperty("packageRepository"));
		String fileName = prop.getProp().getProperty("repository") + ".java";

		System.out.println("Path " + pathToSave);
		System.out.println("Nome arquivo " + fileName);
		IOUtil.criarPastasCasoNaoExista(pathToSave);
		IOUtil.gravarArquivo(generateContent(gClass), pathToSave, fileName);
	}

}
