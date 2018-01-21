package org.openmrs.module.customhtmlforms.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.api.context.Context;
import org.openmrs.module.customhtmlforms.*;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class CustomHtmlFormsServiceComponentTest extends BaseModuleContextSensitiveTest {
	
	private static final String EXISTING_SMEAR_RESULT_UUID = "1d5476e9-ee2c-427e-95a7-f87294a352ad";
	
	private static final Integer EXISTING_SMEAR_RESULT_ID = 1;
	
	private static final String EXISTING_HIV_TEST_RESULT_UUID = "925825cf-6165-4137-ba35-1773a7c25bb4";
	
	private static final Integer EXISTING_HIV_TEST_RESULT_ID = 1;
	
	private static final String EXISTING_DST_TEST_RESULT_UUID = "925825cf-6165-4137-ba35-1773a7c25b2e";
	
	private static final Integer EXISTING_DST_TEST_RESULT_ID = 1;
	
	private static final String EXISTING_TB_HIV_INFORMATION_UUID = "8b51a784-f3bd-458e-a1f2-56dfa5c3c8c8";
	
	private static final Integer EXISTING_TB_HIV_INFORMATION_ID = 1;
	
	private static final String EXISTING_CULTURE_RESULT_UUID = "a23f02a6-953e-423e-ac3a-48c1c2174cdc";
	
	private static final Integer EXISTING_CULTURE_RESULT_ID = 1;
	
	private static final Integer PROVIDER_ID = 1;
	
	private static final Integer LOCATION_ID = 1;
	
	private static final String PATIENT_UUID = "d2c1adbf-d9fa-11e5-90c3-08002719a237";
	
	@Autowired
	private CustomHtmlFormsService customHtmlFormsService;
	
	Provider encounterProvider;
	
	Location encounterLocation;
	
	Date encounterDate;
	
	Patient patient;
	
	@Before
	public void setUp() throws Exception {
		executeDataSet("CustomHtmlFormsServiceComponentTestDataset.xml");
		encounterProvider = Context.getProviderService().getProvider(PROVIDER_ID);
		encounterLocation = Context.getLocationService().getLocation(LOCATION_ID);
		encounterDate = new Date();
		patient = Context.getPatientService().getPatientByUuid(PATIENT_UUID);
	}
	
	@Test
	public void getSmearResultByUuid_shouldGetSmearResultWithUuid() throws Exception {
		
		SmearResult saved = customHtmlFormsService.getSmearResultByUuid(EXISTING_SMEAR_RESULT_UUID);
		assertNotNull(saved);
		assertThat(saved.getId(), is(EXISTING_SMEAR_RESULT_ID));
	}
	
	@Test
	public void addSmearResult_shouldSaveSmearResultWithEncounterCreated() {
		
		SmearResult smearResult = new SmearResult();
		smearResult.setPatient(patient);
		smearResult.setEncounterDate(encounterDate);
		smearResult.setEncounterProvider(encounterProvider);
		smearResult.setEncounterLocation(encounterLocation);
		
		SmearResult saved = customHtmlFormsService.addSmearResult(smearResult);
		assertNotNull(saved);
		assertNotNull(saved.getEncounter());
		assertNotNull(saved.getEncounter().getLocation());
		assertThat(saved.getEncounter().getLocation().getId(), is(LOCATION_ID));
	}
	
	@Test
	public void getHivTestResultByUuid_shouldReturnObjectWithGivenUuid() {
		HivTestResult hivTestResult = customHtmlFormsService.getHivTestResultByUuid(EXISTING_HIV_TEST_RESULT_UUID);
		
		assertNotNull(hivTestResult);
		assertThat(hivTestResult.getId(), is(EXISTING_HIV_TEST_RESULT_ID));
	}
	
	@Test
	public void addHivTestResult_shouldSaveHivTestResultWithEncounterCreated() {
		HivTestResult hivTestResult = new HivTestResult();
		hivTestResult.setPatient(patient);
		hivTestResult.setEncounterDate(encounterDate);
		hivTestResult.setEncounterProvider(encounterProvider);
		hivTestResult.setEncounterLocation(encounterLocation);
		
		HivTestResult saved = customHtmlFormsService.addHivTestResult(hivTestResult);
		assertNotNull(saved);
		assertNotNull(saved.getEncounter());
		assertNotNull(saved.getEncounter().getLocation());
		assertThat(saved.getEncounter().getLocation().getId(), is(LOCATION_ID));
	}
	
	@Test
	public void getDstTestResultByUuid_shouldReturnObjectWithGivenUuid() {
		DstTestResult dstTestResult = customHtmlFormsService.getDstTestResultByUuid(EXISTING_DST_TEST_RESULT_UUID);
		
		assertNotNull(dstTestResult);
		assertThat(dstTestResult.getId(), is(EXISTING_DST_TEST_RESULT_ID));
	}
	
	@Test
	public void addDstTestResult_shouldSaveHivTestResultWithEncounterCreated() {
		DstTestResult dstTestResult = new DstTestResult();
		dstTestResult.setPatient(patient);
		dstTestResult.setEncounterDate(encounterDate);
		dstTestResult.setEncounterProvider(encounterProvider);
		dstTestResult.setEncounterLocation(encounterLocation);
		
		DstTestResult saved = customHtmlFormsService.addDstTestResult(dstTestResult);
		assertNotNull(saved);
		assertNotNull(saved.getEncounter());
		assertNotNull(saved.getEncounter().getLocation());
		assertThat(saved.getEncounter().getLocation().getId(), is(LOCATION_ID));
	}
	
	@Test
	public void getTbHivInformationByUuid_shouldReturnObjectWithGivenUuid() {
		TbHivInformation tbHivInformation = customHtmlFormsService
		        .getTbHivInformationByUuid(EXISTING_TB_HIV_INFORMATION_UUID);
		
		assertNotNull(tbHivInformation);
		assertThat(tbHivInformation.getId(), is(EXISTING_TB_HIV_INFORMATION_ID));
	}
	
	@Test
	public void getCultureResultByUuid_shouldReturnObjectWithGivenUuid() {
		CultureResult cultureResult = customHtmlFormsService.getCultureResultByUuid(EXISTING_CULTURE_RESULT_UUID);
		
		assertNotNull(cultureResult);
		assertThat(cultureResult.getId(), is(EXISTING_CULTURE_RESULT_ID));
	}
	
	@Test
	public void addCultureResult_shouldSaveCultureWithEncounterCreated() {
		CultureResult cultureResult = new CultureResult();
		cultureResult.setPatient(patient);
		cultureResult.setEncounterDate(encounterDate);
		cultureResult.setEncounterProvider(encounterProvider);
		cultureResult.setEncounterLocation(encounterLocation);
		
		CultureResult saved = customHtmlFormsService.addCultureResult(cultureResult);
		assertNotNull(saved);
		assertNotNull(saved.getEncounter());
		assertNotNull(saved.getEncounter().getLocation());
		assertThat(saved.getEncounter().getLocation().getId(), is(LOCATION_ID));
	}
}
