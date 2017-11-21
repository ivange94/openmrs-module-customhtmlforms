package org.openmrs.module.customhtmlforms.controller;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.api.context.Context;
import org.openmrs.module.customhtmlforms.SmearResult;
import org.openmrs.module.customhtmlforms.api.CustomHtmlFormsService;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.test.Util;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceControllerTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SmearResultControllerTest extends MainResourceControllerTest {
	
	private static final String TEST_DATASET = "SmearResultControllerTestDataset.xml";

	private static final String PATIENT_UUID = "d2c1adbf-d9fa-11e5-90c3-08002719a237";

	private static final Integer PROVIDER_ID = 1;

	private static final Integer LOCATION_ID = 1;
	
	@Before
	public void setup() throws Exception {
		executeDataSet(TEST_DATASET);
	}
	
	@Override
	public String getURI() {
		return "smearresult";
	}
	
	@Override
	public String getUuid() {
		return "1d5476e9-ee2c-427e-95a7-f87294a352ad";
	}
	
	@Override
	public long getAllCount() {
		return 1;
	}
	
	@Override
	@Test(expected = ResourceDoesNotSupportOperationException.class)
	public void shouldGetAll() throws Exception {
		super.shouldGetAll();
	}
	
	@Test
	public void getSmearResult_shouldDefaultRepresentationOfASmearResult() throws Exception {
		MockHttpServletRequest req = request(RequestMethod.GET, getURI() + "/" + getUuid());

		SimpleObject result = deserialize(handle(req));
		Util.log("SmearResult retrieved (default)", result);

		String uuid = (String)PropertyUtils.getProperty(result, "uuid");
		String display = (String)PropertyUtils.getProperty(result, "display");

		assertThat(result, is(notNullValue()));
		assertThat(uuid, is(getUuid()));
		assertThat(display, is("1"));
	}

	@Test
	public void getSmearResult_shouldReturnFullRepresentationIfRequested() throws Exception {
		Provider encounterProvider = Context.getProviderService().getProvider(PROVIDER_ID);
		Location encounterLocation = Context.getLocationService().getLocation(LOCATION_ID);
		Date encounterDate = new Date();
		Patient patient = Context.getPatientService().getPatientByUuid(PATIENT_UUID);


		assertThat(encounterProvider, is(notNullValue()));
		assertThat(encounterProvider.getId(), is(PROVIDER_ID));
		assertThat(encounterLocation, is(notNullValue()));
		assertThat(encounterLocation.getId(), is(LOCATION_ID));
		assertThat(patient, is(notNullValue()));
		assertThat(patient.getUuid(), is(PATIENT_UUID));

		SmearResult smearResult = new SmearResult();
		smearResult.setEncounterProvider(encounterProvider);
		smearResult.setEncounterLocation(encounterLocation);
		smearResult.setEncounterDate(encounterDate);
		smearResult.setPatient(patient);

		SmearResult saved = Context.getService(CustomHtmlFormsService.class).addSmearResult(smearResult);
		assertThat(saved, is(notNullValue()));
		assertThat(saved.getUuid(), is(notNullValue()));

		String uuid = saved.getUuid();


		MockHttpServletRequest req = request(RequestMethod.GET, getURI() + "/" + uuid);
		req.addParameter("v", "full");

		SimpleObject result = deserialize(handle(req));
		Util.log("SmearResult retrieved (default)", result);


	}
	
}
