package com.jeasier.templates;

import com.jeasier.model.TemplateFormat;

/**
 * 
 * @author João Vitor Feitosa
 * @since 0.0.1
 */

public class Gentelella implements TemplateFormat {

	public String getPathVendors() {

		return "/templates/layouts/gentelella/vendors";
	}

	public String getPathResourcesLayout() {
		return "/templates/layouts/gentelella/staticFiles";
	}

	public String getPathLayoutFragments() {

		return "/templates/layouts/gentelella/layout";
	}

	public String getPathMainlyLayout() {
		// TODO Auto-generated method stub
		return "LayoutPadrao";

	}

	public String getCreatePage() {
		// TODO Auto-generated method stub
		return "/templates/layouts/gentelella/thymeleaf/ThymeleafCreate.txt";
	}

	public String getListPage() {
		// TODO Auto-generated method stub
		return "/templates/layouts/gentelella/thymeleaf/ThymeleafList.txt";
	}

	public String getCreateField() {
		// TODO Auto-generated method stub
		return "/templates/layouts/gentelella/thymeleaf/ThymeleafCreateField.txt";
	}

	public String getCreateFieldBoolean() {
		// TODO Auto-generated method stub
		return "/templates/layouts/gentelella/thymeleaf/ThymeleafCreateFieldBoolean.txt";
	}

	public String getCreateFieldClass() {
		// TODO Auto-generated method stub
		return "/templates/layouts/gentelella/thymeleaf/ThymeleafCreateFieldClass.txt";
	}

	public String getCreateFieldEnum() {
		// TODO Auto-generated method stub
		return "/templates/layouts/gentelella/thymeleaf/ThymeleafCreateFieldEnum.txt";
	}

	public String getCreateFilterField() {
		// TODO Auto-generated method stub
		return "/templates/layouts/gentelella/thymeleaf/ThymeleafFilterField.txt";
	}

	@Override
	public String toString() {
		return "Gentelella [getPathVendors()=" + getPathVendors() + ", getPathResourcesLayout()="
				+ getPathResourcesLayout() + ", getPathLayoutFragments()=" + getPathLayoutFragments()
				+ ", getPathMainlyLayout()=" + getPathMainlyLayout() + ", getCreatePage()=" + getCreatePage()
				+ ", getListPage()=" + getListPage() + ", getCreateField()=" + getCreateField()
				+ ", getCreateFieldBoolean()=" + getCreateFieldBoolean() + ", getCreateFieldClass()="
				+ getCreateFieldClass() + ", getCreateFieldEnum()=" + getCreateFieldEnum() + ", getCreateFilterField()="
				+ getCreateFilterField() + "]";
	}
	
	

}
