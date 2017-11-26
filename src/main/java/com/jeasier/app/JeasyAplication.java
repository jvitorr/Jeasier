package com.jeasier.app;

import java.io.IOException;
import java.net.URISyntaxException;

import com.google.common.reflect.ClassPath;
import com.jeasier.generator.controller.EntityControllerGenerator;
import com.jeasier.generator.filter.EntityFilterGenerator;
import com.jeasier.generator.model.EntityPageWrapperGenerator;
import com.jeasier.generator.repository.EntityHelperGenerator;
import com.jeasier.generator.repository.EntityImplGenerator;
import com.jeasier.generator.repository.EntityRepositoryGenerator;
import com.jeasier.generator.service.EntityServiceGenerator;
import com.jeasier.generator.template.GeneratorLayout;
import com.jeasier.generator.view.ThymeleafCreatorGenerator;
import com.jeasier.generator.view.ThymeleafListGenerator;
import com.jeasier.model.TemplateFormat;
import com.jeasier.util.EasyJavaProperties;
import com.jeasier.util.EasyJavaUtil;
import com.jeasier.util.FieldUtil;

/**
 * 
 * @author João Vitor Feitosa
 * @since 0.0.1
 */

public class JeasyAplication {

	private EntityFilterGenerator filter;
	private EntityRepositoryGenerator repository;
	private EntityHelperGenerator helper;
	private EntityImplGenerator impl;
	private EntityServiceGenerator service;
	private EntityControllerGenerator controller;
	private EntityPageWrapperGenerator pageWrapper;
	private ThymeleafCreatorGenerator thymeleafCreator;
	private ThymeleafListGenerator thymeleafList;
	private EasyJavaProperties properties;

	public JeasyAplication() throws IOException {
		properties = new EasyJavaProperties();
	}

	// template exist

	public void generateCrud(Class<?> gClass, String layoutDecorator) throws URISyntaxException, IOException {

		validations(gClass);
		properties.getProp().setProperty("layoutDecorator", layoutDecorator);
		properties.getProp().setProperty("entity", gClass.getSimpleName());

		filter = new EntityFilterGenerator(properties);
		repository = new EntityRepositoryGenerator(properties);
		helper = new EntityHelperGenerator(properties);
		impl = new EntityImplGenerator(properties);
		service = new EntityServiceGenerator(properties);
		controller = new EntityControllerGenerator(properties);
		pageWrapper = new EntityPageWrapperGenerator(properties);
		thymeleafCreator = new ThymeleafCreatorGenerator(properties, null);
		thymeleafList = new ThymeleafListGenerator(properties, null);

		filter.generateClass(gClass);
		repository.generateClass(gClass);
		helper.generateClass(gClass);
		impl.generateClass(gClass);
		service.generateClass(gClass);
		controller.generateClass(gClass);
		pageWrapper.generateClass(gClass);
		thymeleafCreator.generateClass(gClass);
		thymeleafList.generateClass(gClass);

	}

	public void generateCrud(String gPackage, String layoutDecorator) throws IOException, URISyntaxException {
		final ClassLoader loader = Thread.currentThread().getContextClassLoader();

		for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
			if (info.getName().startsWith(gPackage)) {

				final Class<?> gClass = info.load();
				System.out.println(gClass);
				try {
					validations(gClass);
					generateCrud(gClass, layoutDecorator);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}

	}

	// template does not exist

	public void generateCrud(String gPackage, TemplateFormat template) throws IOException, URISyntaxException {
		final ClassLoader loader = Thread.currentThread().getContextClassLoader();

		if (ClassPath.from(loader).getTopLevelClasses().asList().get(0) != null) {
			if (ClassPath.from(loader).getTopLevelClasses().asList().get(0).getName().startsWith(gPackage)) {
				validations(ClassPath.from(loader).getTopLevelClasses().asList().get(0).load());

			}

		}
		for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
			if (info.getName().startsWith(gPackage)) {

				final Class<?> gClass = info.load();
				System.out.println(gClass);
				try {

					generateCrud(gClass, template);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}

		GeneratorLayout generatorLayout = new GeneratorLayout(properties);
		generatorLayout.generate(template);

	}

	public void generateCrud(Class<?> gClass, TemplateFormat template) throws URISyntaxException, IOException {

		validations(gClass);
		properties.getProp().setProperty("layoutDecorator", "layout/MainlyLayout");
		properties.getProp().setProperty("entity", gClass.getSimpleName());

		filter = new EntityFilterGenerator(properties);
		repository = new EntityRepositoryGenerator(properties);
		helper = new EntityHelperGenerator(properties);
		impl = new EntityImplGenerator(properties);
		service = new EntityServiceGenerator(properties);
		controller = new EntityControllerGenerator(properties);
		pageWrapper = new EntityPageWrapperGenerator(properties);
		thymeleafCreator = new ThymeleafCreatorGenerator(properties, template);
		thymeleafList = new ThymeleafListGenerator(properties, template);

		filter.generateClass(gClass);
		repository.generateClass(gClass);
		helper.generateClass(gClass);
		impl.generateClass(gClass);
		service.generateClass(gClass);
		controller.generateClass(gClass);
		pageWrapper.generateClass(gClass);
		thymeleafCreator.generateClass(gClass);
		thymeleafList.generateClass(gClass);

	}

	private void validations(Class<?> gClass) throws URISyntaxException {

		if (FieldUtil.getPrimaryKey(gClass) == null) {
			throw new IllegalArgumentException(gClass.getName() + " does not have primary key");

		}

		autoConfiguration(gClass);

	}

	private void autoConfiguration(Class<?> gClass) throws URISyntaxException {
		if (Boolean.parseBoolean(properties.getProp().getProperty("autoConfiguration"))) {
			properties.getProp().setProperty("resourcePath", EasyJavaUtil.getPathResources(gClass));
			properties.getProp().setProperty("staticPath", EasyJavaUtil.getPathResources(gClass) + "static");
			properties.getProp().setProperty("templatePath", EasyJavaUtil.getPathResources(gClass) + "templates");
			properties.getProp().setProperty("referencePackage", EasyJavaUtil.getPackageDefault(gClass));

			System.out.println(properties.getProp().get("resourcePath"));
			System.out.println(properties.getProp().get("staticPath"));
			System.out.println(properties.getProp().get("templatePath"));
			System.out.println(properties.getProp().get("referencePackage"));

		}

	}
}
