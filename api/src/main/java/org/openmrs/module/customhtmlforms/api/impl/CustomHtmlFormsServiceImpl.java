/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.customhtmlforms.api.impl;

import org.openmrs.Encounter;
import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.customhtmlforms.CustomHtmlFormsConfig;
import org.openmrs.module.customhtmlforms.HivTestResult;
import org.openmrs.module.customhtmlforms.Item;
import org.openmrs.module.customhtmlforms.SmearResult;
import org.openmrs.module.customhtmlforms.api.CustomHtmlFormsService;
import org.openmrs.module.customhtmlforms.api.dao.CustomHtmlFormsDao;

import java.util.List;

public class CustomHtmlFormsServiceImpl extends BaseOpenmrsService implements CustomHtmlFormsService {
	
	CustomHtmlFormsDao dao;
	
	UserService userService;
	
	private CustomHtmlFormsConfig config = new CustomHtmlFormsConfig();
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setDao(CustomHtmlFormsDao dao) {
		this.dao = dao;
	}
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public Item getItemByUuid(String uuid) throws APIException {
		return dao.getItemByUuid(uuid);
	}
	
	@Override
	public Item saveItem(Item item) throws APIException {
		if (item.getOwner() == null) {
			item.setOwner(userService.getUser(1));
		}
		
		return dao.saveItem(item);
	}
	
	@Override
	public SmearResult getSmearResultByUuid(String uuid) throws APIException {
		return dao.getSmearResultByUuid(uuid);
	}
	
	@Override
	public SmearResult saveSmearResult(SmearResult smearResult) throws APIException {
		return dao.saveSmearResult(smearResult);
	}
	
	@Override
	public SmearResult addSmearResult(SmearResult smearResult) {
		
		final Encounter encounter = new Encounter();
		encounter.setPatient(smearResult.getPatient());
		encounter.setEncounterDatetime(smearResult.getEncounterDate());
		encounter.setProvider(config.getEncounterRoleForForms(), smearResult.getEncounterProvider());
		encounter.setEncounterType(config.getEncounterTypeForForms());
		encounter.setLocation(smearResult.getEncounterLocation());
		smearResult.setEncounter(encounter);
		return saveSmearResult(smearResult);
	}
	
	@Override
	public HivTestResult getHivTestResultByUuid(String uuid) throws APIException {
		return dao.getHivTestResultByUuid(uuid);
	}
	
	@Override
	public HivTestResult saveHivTestResult(HivTestResult hivTestResult) throws APIException {
		return dao.saveHivTestResult(hivTestResult);
	}
	
	@Override
	public HivTestResult addHivTestResult(HivTestResult hivTestResult) throws APIException {
		final Encounter encounter = new Encounter();
		encounter.setPatient(hivTestResult.getPatient());
		encounter.setEncounterDatetime(hivTestResult.getEncounterDate());
		encounter.setProvider(config.getEncounterRoleForForms(), hivTestResult.getEncounterProvider());
		encounter.setEncounterType(config.getEncounterTypeForForms());
		encounter.setLocation(hivTestResult.getEncounterLocation());
		hivTestResult.setEncounter(encounter);
		return saveHivTestResult(hivTestResult);
	}
	
	@Override
	public List<HivTestResult> getAllHivTestResults() {
		return dao.getAllHivTestResults();
	}
}
