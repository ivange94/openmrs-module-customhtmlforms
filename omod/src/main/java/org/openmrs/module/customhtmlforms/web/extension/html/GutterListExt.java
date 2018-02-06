package org.openmrs.module.customhtmlforms.web.extension.html;

import org.openmrs.module.web.extension.LinkExt;

public class GutterListExt extends LinkExt {
	
	@Override
	public String getLabel() {
		return "New Patient Registration";
	}
	
	@Override
	public String getUrl() {
		return "owa/patientregistration/index.html#";
	}
	
	@Override
	public String getRequiredPrivilege() {
		return "Add Patients";
	}
}
