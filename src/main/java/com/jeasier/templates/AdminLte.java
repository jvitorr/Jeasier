package com.jeasier.templates;

import com.jeasier.model.TemplateFormat;

/**
 * 
 * @author João Vitor Feitosa
 * @since 0.0.1
 */

public class AdminLte implements TemplateFormat {

	public String getPathVendors() {

		return "/templates/layouts/adminlte/vendors";
	}

	public String getPathResourcesLayout() {
		return "/templates/layouts/adminlte/staticFiles";
	}

	public String getPathLayoutFragments() {

		return "/templates/layouts/adminlte/layout";
	}

	public String getPathMainlyLayout() {
		// TODO Auto-generated method stub
		return "LayoutPadrao";

	}

	public String getCreatePage() {
		// TODO Auto-generated method stub
		return "/templates/layouts/adminlte/thymeleaf/ThymeleafCreate.txt";
	}

	public String getListPage() {
		// TODO Auto-generated method stub
		return "/templates/layouts/adminlte/thymeleaf/ThymeleafList.txt";
	}

	public String getCreateField() {
		// TODO Auto-generated method stub
		return "/templates/layouts/adminlte/thymeleaf/ThymeleafCreateField.txt";
	}

	public String getCreateFieldBoolean() {
		// TODO Auto-generated method stub
		return "/templates/layouts/adminlte/thymeleaf/ThymeleafCreateFieldBoolean.txt";
	}

	public String getCreateFieldClass() {
		// TODO Auto-generated method stub
		return "/templates/layouts/adminlte/thymeleaf/ThymeleafCreateFieldClass.txt";
	}

	public String getCreateFieldEnum() {
		// TODO Auto-generated method stub
		return "/templates/layouts/adminlte/thymeleaf/ThymeleafCreateFieldEnum.txt";
	}

	public String getCreateFilterField() {
		// TODO Auto-generated method stub
		return "/templates/layouts/adminlte/thymeleaf/ThymeleafFilterField.txt";
	}

}
