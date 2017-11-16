package org.openmrs.module.customhtmlforms.controller;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceControllerTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.junit.Assert.assertNotNull;

public class SmearResultControllerTest extends MainResourceControllerTest {
	
	private static final String TEST_DATASET = "SmearResultControllerTestDataset.xml";
	
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
	
	@Test
	public void getSmearResult_shouldDefaultRepresentationOfASmearResult() throws Exception {
		MockHttpServletRequest req = request(RequestMethod.GET, getURI() + "/" + getUuid());
		SimpleObject result = deserialize(handle(req));
		
		assertNotNull(result);
	}
	
}
