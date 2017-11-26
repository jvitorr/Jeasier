package com.jeasier.generator.template;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;

import com.jeasier.model.TemplateFormat;
import com.jeasier.util.EasyJavaProperties;
import com.jeasier.util.IOUtil;
import com.jeasier.util.OSValidator;


/**
 * 
 * @author João Vitor Feitosa
 * @since 0.0.1
 */

public class GeneratorLayout {

	private EasyJavaProperties prop;

	public GeneratorLayout(EasyJavaProperties properties) {
		this.prop = properties;
	}

	public void generate(TemplateFormat template) throws URISyntaxException, IOException {

		System.out.println(Class.class.getResource(template.getPathVendors()).getFile());
		System.out.println(Class.class.getResource(template.getPathLayoutFragments()).getFile());
		// System.out.println(EasyJavaAplication.class.getResource(template.getPathMainlyLayout()).getFile());
		System.out.println(Class.class.getResource(template.getPathResourcesLayout()).getFile());

		String pathResourcesVendors = prop.getProp().getProperty("staticPath") + OSValidator.getOsSeparator()
				+ "vendors";
		String pathResourcesLayout = prop.getProp().getProperty("staticPath") + OSValidator.getOsSeparator() + "layout";

		String pathTemplates = prop.getProp().getProperty("templatePath") + OSValidator.getOsSeparator() + "layout";

		IOUtil.criarPastasCasoNaoExista(pathResourcesVendors);
		IOUtil.criarPastasCasoNaoExista(pathResourcesLayout);
		IOUtil.criarPastasCasoNaoExista(pathTemplates);

		FileUtils.copyDirectory(
				new File(Class.class.getResource(template.getPathLayoutFragments()).getFile()),
				new File(pathTemplates));

		// IOUtil.gravarArquivo(
		// IOUtil.lerArquivo(EasyJavaAplication.class.getResource(template.getPathMainlyLayout()).getFile()),
		// pathTemplates, template.getPathMainlyLayout());

		FileUtils.copyDirectory(new File(Class.class.getResource(template.getPathVendors()).getFile()),
				new File(pathResourcesVendors));
		FileUtils.copyDirectory(
				new File(Class.class.getResource(template.getPathResourcesLayout()).getFile()),
				new File(pathResourcesLayout));
	}

	// public void generateClass(Class<?> gClass, EasyJavaProperties creator)
	// throws FileNotFoundException, URISyntaxException {
	//
	// String pathToSave = EasyJavaUtil.getPathFile(gClass)
	// + EasyJavaUtil.getPathFromPackage(creator.getPackageImpl());
	// String fileName = creator.getNameImpl() + ".java";
	//
	// System.out.println("Path " + pathToSave);
	// System.out.println("Nome arquivo " + fileName);
	// IOUtil.criarPastasCasoNaoExista(pathToSave);
	// IOUtil.gravarArquivo(generateContent(gClass, creator), pathToSave,
	// fileName);
	// }

}
