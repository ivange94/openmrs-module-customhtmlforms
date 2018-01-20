package org.openmrs.module.customhtmlforms.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.api.context.Context;
import org.openmrs.module.customhtmlforms.HivTestResult;
import org.openmrs.module.customhtmlforms.SmearResult;
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
	
	private static final Integer PROVIDER_ID = 1;
	
	private static final Integer LOCATION_ID = 1;
	
	private static final Integer PERSON_ID = 70011;
	
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
}
