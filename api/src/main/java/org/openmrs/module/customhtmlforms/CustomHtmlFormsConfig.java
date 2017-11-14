/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.customhtmlforms;

import org.openmrs.EncounterRole;
import org.openmrs.EncounterType;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Component;

/**
 * Contains module's config.
 */
@Component("customhtmlforms.CustomHtmlFormsConfig")
public class CustomHtmlFormsConfig {
	
	public final static String MODULE_PRIVILEGE = "CustomHtmlForms Privilege";
	
	private final static String ENCOUNTER_TYPE_UUID = "b6eb5073-a470-478a-8b74-bf788485f6af";
	
	private final static String ENCOUNTER_ROLE_UUID = "890a3f0e-84d5-43f1-8a2f-4da6e1260b07";
	
	public EncounterType getEncounterTypeForForms() {
		return Context.getEncounterService().getEncounterTypeByUuid(ENCOUNTER_TYPE_UUID);
	}
	
	public EncounterRole getEncounterRoleForForms() {
		return Context.getEncounterService().getEncounterRoleByUuid(ENCOUNTER_ROLE_UUID);
	}
}
