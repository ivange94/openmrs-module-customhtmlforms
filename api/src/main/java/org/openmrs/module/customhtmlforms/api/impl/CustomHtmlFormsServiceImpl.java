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

import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.customhtmlforms.*;
import org.openmrs.module.customhtmlforms.api.CustomHtmlFormsService;
import org.openmrs.module.customhtmlforms.api.dao.CustomHtmlFormsDao;

import java.util.Date;
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
	
	@Override
	public DstTestResult getDstTestResultByUuid(String uuid) throws APIException {
		return dao.getDstTestResultByUuid(uuid);
	}
	
	@Override
	public List<DstTestResult> getAllDstTestResults() throws APIException {
		return dao.getAllDstTestResults();
	}
	
	@Override
	public DstTestResult saveDstTestResult(DstTestResult dstTestResult) throws APIException {
		return dao.saveDstTestResult(dstTestResult);
	}
	
	@Override
	public DstTestResult addDstTestResult(DstTestResult dstTestResult) throws APIException {
		final Encounter encounter = new Encounter();
		encounter.setPatient(dstTestResult.getPatient());
		encounter.setEncounterDatetime(dstTestResult.getEncounterDate());
		encounter.setProvider(config.getEncounterRoleForForms(), dstTestResult.getEncounterProvider());
		encounter.setEncounterType(config.getEncounterTypeForForms());
		encounter.setLocation(dstTestResult.getEncounterLocation());
		dstTestResult.setEncounter(encounter);
		return saveDstTestResult(dstTestResult);
	}
	
	@Override
	public TbHivInformation getTbHivInformationByUuid(String uuid) throws APIException {
		return dao.getTbHivInformationByUuid(uuid);
	}
	
	@Override
	public List<TbHivInformation> getAllTbHivInformation() throws APIException {
		return dao.getAllTbInformation();
	}
	
	@Override
	public TbHivInformation saveTbHivInformation(TbHivInformation tbHivInformation) throws APIException {
		return dao.saveTbHivInformation(tbHivInformation);
	}
	
	@Override
	public TbHivInformation addTbHivInformation(TbHivInformation tbHivInformation) throws APIException {
		final Encounter encounter = new Encounter();
		encounter.setPatient(tbHivInformation.getPatient());
		encounter.setEncounterDatetime(tbHivInformation.getEncounterDate());
		encounter.setProvider(config.getEncounterRoleForForms(), tbHivInformation.getEncounterProvider());
		encounter.setEncounterType(config.getEncounterTypeForForms());
		encounter.setLocation(tbHivInformation.getEncounterLocation());
		tbHivInformation.setEncounter(encounter);
		return saveTbHivInformation(tbHivInformation);
	}
	
	@Override
	public CultureResult getCultureResultByUuid(String uuid) throws APIException {
		return dao.getCultureResultByUuid(uuid);
	}
	
	@Override
	public List<CultureResult> getAllCultureResults() throws APIException {
		return dao.getAllCultureResults();
	}
	
	@Override
	public CultureResult saveCultureResult(CultureResult cultureResult) throws APIException {
		return dao.saveCultureResult(cultureResult);
	}
	
	@Override
	public CultureResult addCultureResult(CultureResult cultureResult) throws APIException {
		final Encounter encounter = new Encounter();
		encounter.setPatient(cultureResult.getPatient());
		encounter.setEncounterDatetime(cultureResult.getEncounterDate());
		encounter.setProvider(config.getEncounterRoleForForms(), cultureResult.getEncounterProvider());
		encounter.setEncounterType(config.getEncounterTypeForForms());
		encounter.setLocation(cultureResult.getEncounterLocation());
		encounter.setCreator(Context.getAuthenticatedUser());
		encounter.setDateCreated(new Date());
		encounter.setForm(config.getCultureResultForm());
		final Encounter saved = Context.getEncounterService().saveEncounter(encounter);
		
		final Obs obs = new Obs();
		obs.setConcept(config.getCultureResultFormResultConcept());
		obs.setPerson(cultureResult.getPatient().getPerson());
		obs.setEncounter(saved);
		obs.setCreator(Context.getAuthenticatedUser());
		obs.setDateCreated(new Date());
		obs.setObsDatetime(new Date());
		
		final Integer answerConceptId = cultureResult.getResult();
		Concept answerConcept = Context.getConceptService().getConcept(answerConceptId);
		obs.setValueCoded(answerConcept);
		Context.getObsService().saveObs(obs, "");
		cultureResult.setEncounter(saved);
		return saveCultureResult(cultureResult);
	}
}
