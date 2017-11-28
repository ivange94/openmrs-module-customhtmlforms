package org.openmrs.module.customhtmlforms.extension.html;

import org.openmrs.module.customhtmlforms.CustomHtmlFormsConfig;
import org.openmrs.module.web.extension.LinkExt;

public class GutterListExt extends LinkExt {
	
	@Override
	public String getLabel() {
		return "New Patient Registration";
	}
	
	@Override
	public String getUrl() {
		return "owa/patientregistration/index.html#/";
	}
	
	@Override
	public String getRequiredPrivilege() {
		return CustomHtmlFormsConfig.MODULE_PRIVILEGE;
	}
}
