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

import org.openmrs.Concept;
import org.openmrs.EncounterRole;
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Component;

/**
 * Contains module's config.
 */
@Component("customhtmlforms.CustomHtmlFormsConfig")
public class CustomHtmlFormsConfig {
	
	public static final String MODULE_PRIVILEGE = "CustomHtmlForms Privilege";
	
	private static final String ENCOUNTER_TYPE_UUID = "b6eb5073-a470-478a-8b74-bf788485f6af";
	
	private static final String ENCOUNTER_ROLE_UUID = "890a3f0e-84d5-43f1-8a2f-4da6e1260b07";
	
	private static final String CULTURE_RESULT_FORM_ID_GLOBAL_PROPERTY = "customhtmlforms.cultureResultFormId";
	
	private static final String CULTURE_RESULT_FORM_RESULT_CONCEPT_ID_GLOBAL_PROPERTY = "customhtmlforms.cultureResultFormResultConceptID";
	
	public EncounterType getEncounterTypeForForms() {
		return Context.getEncounterService().getEncounterTypeByUuid(ENCOUNTER_TYPE_UUID);
	}
	
	public EncounterRole getEncounterRoleForForms() {
		return Context.getEncounterService().getEncounterRoleByUuid(ENCOUNTER_ROLE_UUID);
	}
	
	public Form getCultureResultForm() {
		final String formId = Context.getAdministrationService().getGlobalProperty(CULTURE_RESULT_FORM_ID_GLOBAL_PROPERTY);
		final Form cultureResultForm = Context.getFormService().getForm(formId);
		return cultureResultForm;
	}
	
	public Concept getCultureResultFormResultConcept() {
		final String conceptId = Context.getAdministrationService().getGlobalProperty(
		    CULTURE_RESULT_FORM_RESULT_CONCEPT_ID_GLOBAL_PROPERTY);
		final Concept concept = Context.getConceptService().getConcept(conceptId);
		return concept;
	}
}
