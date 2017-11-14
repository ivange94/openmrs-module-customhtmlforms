package org.openmrs.module.customhtmlforms.api;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.api.context.Context;
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
	
	private static final Integer PROVIDER_ID = 1;
	
	private static final Integer LOCATION_ID = 1;
	
	private static final Integer PERSON_ID = 70011;
	
	private static final String PATIENT_UUID = "d2c1adbf-d9fa-11e5-90c3-08002719a237";
	
	@Autowired
	private CustomHtmlFormsService customHtmlFormsService;
	
	@Before
	public void setUp() throws Exception {
		executeDataSet("CustomHtmlFormsServiceComponentTestDataset.xml");
	}
	
	@Test
	public void getSmearResultByUuid_shouldGetSmearResultWithUuid() throws Exception {
		
		SmearResult saved = customHtmlFormsService.getSmearResultByUuid(EXISTING_SMEAR_RESULT_UUID);
		assertNotNull(saved);
		assertThat(saved.getId(), is(EXISTING_SMEAR_RESULT_ID));
	}
	
	@Test
	public void saveSmearResult_shouldSaveGivenSmearResult() {
		SmearResult smearResult = new SmearResult();
		SmearResult saved = customHtmlFormsService.saveSmearResult(smearResult);
		assertNotNull(saved);
		assertNotNull(saved.getId());
	}
	
	@Test
	public void addSmearResult_shouldSaveSmearResultWithEncounterCreated() {
		Provider encounterProvider = Context.getProviderService().getProvider(PROVIDER_ID);
		Location encounterLocation = Context.getLocationService().getLocation(LOCATION_ID);
		Date encounterDate = new Date();
		Patient patient = Context.getPatientService().getPatientByUuid(PATIENT_UUID);
		
		assertNotNull(encounterProvider);
		assertNotNull(encounterLocation);
		assertNotNull(patient);
		assertNotNull(patient.getPerson());
		assertThat(patient.getPerson().getId(), is(PERSON_ID));
		
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
}
