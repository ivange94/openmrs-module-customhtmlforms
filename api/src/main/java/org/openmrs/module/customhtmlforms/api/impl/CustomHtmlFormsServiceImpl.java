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
import org.openmrs.module.customhtmlforms.db.CustomHtmlFormsDao;

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
		encounter.setCreator(Context.getAuthenticatedUser());
		encounter.setDateCreated(new Date());
		encounter.setForm(config.getSmearResultForm());
		final Encounter saved = Context.getEncounterService().saveEncounter(encounter);
		smearResult.setEncounter(saved);
		
		final Obs obsForSampleID = new Obs();
		obsForSampleID.setConcept(config.getSmearResultSampleConcept());
		obsForSampleID.setPerson(smearResult.getPatient());
		obsForSampleID.setEncounter(saved);
		obsForSampleID.setCreator(Context.getAuthenticatedUser());
		obsForSampleID.setDateCreated(new Date());
		obsForSampleID.setObsDatetime(new Date());
		obsForSampleID.setValueText(smearResult.getSampleId());
		Context.getObsService().saveObs(obsForSampleID, "");
		
		final Obs obsForSampleType = new Obs();
		obsForSampleType.setConcept(config.getSmearResultSampleType());
		obsForSampleType.setPerson(smearResult.getPatient());
		obsForSampleType.setEncounter(saved);
		obsForSampleType.setCreator(Context.getAuthenticatedUser());
		obsForSampleType.setDateCreated(new Date());
		obsForSampleType.setObsDatetime(new Date());
		
		final Concept sampleTypeAnswerConcept = Context.getConceptService().getConcept(smearResult.getSampleType());
		obsForSampleType.setValueCoded(sampleTypeAnswerConcept);
		Context.getObsService().saveObs(obsForSampleType, "");
		
		final Obs obsForAppearance = new Obs();
		obsForAppearance.setConcept(config.getSmearResultAppearance());
		obsForAppearance.setPerson(smearResult.getPatient());
		obsForAppearance.setEncounter(saved);
		obsForAppearance.setCreator(Context.getAuthenticatedUser());
		obsForAppearance.setDateCreated(new Date());
		obsForAppearance.setObsDatetime(new Date());
		
		final Concept answerForAppearance = Context.getConceptService().getConcept(smearResult.getAppearance());
		obsForAppearance.setValueCoded(answerForAppearance);
		Context.getObsService().saveObs(obsForAppearance, "");
		
		final Obs obsForResult = new Obs();
		obsForResult.setConcept(config.getSmearResultResult());
		obsForResult.setPerson(smearResult.getPatient());
		obsForResult.setEncounter(saved);
		obsForResult.setCreator(Context.getAuthenticatedUser());
		obsForResult.setDateCreated(new Date());
		obsForResult.setObsDatetime(new Date());
		
		final Concept answerForResult = Context.getConceptService().getConcept(smearResult.getResult());
		obsForResult.setValueCoded(answerForResult);
		Context.getObsService().saveObs(obsForResult, "");
		
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
		encounter.setCreator(Context.getAuthenticatedUser());
		encounter.setDateCreated(new Date());
		encounter.setForm(config.getHivTestResultForm());
		final Encounter saved = Context.getEncounterService().saveEncounter(encounter);
		hivTestResult.setEncounter(saved);
		
		final Obs result1 = new Obs();
		result1.setConcept(config.getHivTestResultFormResultOneConcept());
		result1.setPerson(hivTestResult.getPatient());
		result1.setEncounter(saved);
		result1.setCreator(Context.getAuthenticatedUser());
		result1.setDateCreated(new Date());
		result1.setObsDatetime(new Date());
		
		final Integer answer1ConceptId = hivTestResult.getResult1();
		Concept answerConcept = Context.getConceptService().getConcept(answer1ConceptId);
		result1.setValueCoded(answerConcept);
		Context.getObsService().saveObs(result1, "");
		
		final Obs result2 = new Obs();
		result2.setConcept(config.getHivTestResultFormResultTwoConcept());
		result2.setPerson(hivTestResult.getPatient());
		result2.setEncounter(saved);
		result2.setCreator(Context.getAuthenticatedUser());
		result2.setDateCreated(new Date());
		result2.setObsDatetime(new Date());
		
		final Integer answer2ConceptId = hivTestResult.getResult2();
		final Concept answer2Concept = Context.getConceptService().getConcept(answer2ConceptId);
		result2.setValueCoded(answer2Concept);
		Context.getObsService().saveObs(result2, "");
		hivTestResult.setEncounter(saved);
		
		final Obs date1 = new Obs();
		date1.setConcept(config.getDateConceptForForms());
		date1.setPerson(hivTestResult.getPatient());
		date1.setEncounter(saved);
		date1.setCreator(Context.getAuthenticatedUser());
		date1.setDateCreated(new Date());
		date1.setObsDatetime(new Date());
		date1.setValueDate(hivTestResult.getDate1());
		Context.getObsService().saveObs(date1, "");
		
		final Obs date2 = new Obs();
		date2.setConcept(config.getDateConceptForForms());
		date2.setPerson(hivTestResult.getPatient());
		date2.setEncounter(saved);
		date2.setCreator(Context.getAuthenticatedUser());
		date2.setDateCreated(new Date());
		date2.setObsDatetime(new Date());
		date2.setValueDate(hivTestResult.getDate1());
		Context.getObsService().saveObs(date2, "");
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
		encounter.setCreator(Context.getAuthenticatedUser());
		encounter.setDateCreated(new Date());
		encounter.setForm(config.getHivTbInformationForm());
		final Encounter saved = Context.getEncounterService().saveEncounter(encounter);
		tbHivInformation.setEncounter(saved);
		
		final Obs serologyResult = new Obs();
		serologyResult.setConcept(config.getHivTestResultFormResultOneConcept());
		serologyResult.setPerson(tbHivInformation.getPatient());
		serologyResult.setEncounter(saved);
		serologyResult.setCreator(Context.getAuthenticatedUser());
		serologyResult.setDateCreated(new Date());
		serologyResult.setObsDatetime(new Date());
		
		final Concept serologyResultAns = Context.getConceptService().getConcept(tbHivInformation.getHivSerologyResult());
		serologyResult.setValueCoded(serologyResultAns);
		Context.getObsService().saveObs(serologyResult, "");
		
		final Obs serologyResultDate = new Obs();
		serologyResultDate.setConcept(config.getDateConceptForForms());
		serologyResultDate.setPerson(tbHivInformation.getPatient());
		serologyResultDate.setEncounter(saved);
		serologyResultDate.setCreator(Context.getAuthenticatedUser());
		serologyResultDate.setDateCreated(new Date());
		serologyResultDate.setObsDatetime(new Date());
		serologyResultDate.setValueDate(tbHivInformation.getSerologyResultDate());
		Context.getObsService().saveObs(serologyResultDate, "");
		
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
		cultureResult.setEncounter(saved);
		
		final Obs obs = new Obs();
		obs.setConcept(config.getCultureResultFormResultConcept());
		obs.setPerson(cultureResult.getPatient());
		obs.setEncounter(saved);
		obs.setCreator(Context.getAuthenticatedUser());
		obs.setDateCreated(new Date());
		obs.setObsDatetime(new Date());
		
		final Integer answerConceptId = cultureResult.getResult();
		Concept answerConcept = Context.getConceptService().getConcept(answerConceptId);
		obs.setValueCoded(answerConcept);
		Context.getObsService().saveObs(obs, "");
		return saveCultureResult(cultureResult);
	}
}
