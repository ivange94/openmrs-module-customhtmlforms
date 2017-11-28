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
		
		String uuid = (String) PropertyUtils.getProperty(result, "uuid");
		String display = (String) PropertyUtils.getProperty(result, "display");
		
		assertThat(result, is(notNullValue()));
		assertThat(uuid, is(getUuid()));
		assertThat(display, is("1"));
	}
	
	@Test
	public void getSmearResult_shouldReturnFullRepresentationIfRequested() throws Exception {
		
		MockHttpServletRequest req = request(RequestMethod.GET, getURI() + "/" + getUuid());
		req.addParameter("v", "full");
		
		SimpleObject result = deserialize(handle(req));
		Util.log("SmearResult retrieved (default)", result);
		
		String uuid = (String) PropertyUtils.getProperty(result, "uuid");
		String display = (String) PropertyUtils.getProperty(result, "display");
		
		assertThat(result, is(notNullValue()));
		assertThat(uuid, is(getUuid()));
		assertThat(display, is("1"));
	}
	
}
