package org.openmrs.module.customhtmlforms.resource;

import org.junit.Before;
import org.openmrs.module.customhtmlforms.SmearResult;
import org.openmrs.module.customhtmlforms.api.CustomHtmlFormsService;
import org.openmrs.module.customhtmlforms.web.resource.SmearResultResource;
import org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResourceTest;
import org.springframework.beans.factory.annotation.Autowired;

public class SmearResultResourceComponentTest extends BaseDelegatingResourceTest<SmearResultResource, SmearResult> {
	
	private static final String TEST_DATASET = "SmearResultResourceComponentTestDataset.xml";
	
	@Autowired
	CustomHtmlFormsService customHtmlFormsService;
	
	@Before
	public void setUp() throws Exception {
		executeDataSet(TEST_DATASET);
	}
	
	@Override
	public SmearResult newObject() {
		return customHtmlFormsService.getSmearResultByUuid(getUuidProperty());
	}
	
	@Override
	public String getDisplayProperty() {
		return "";
	}
	
	@Override
	public String getUuidProperty() {
		return "1d5476e9-ee2c-427e-95a7-f87294a352ad";
	}
	
	@Override
	public void validateDefaultRepresentation() throws Exception {
		super.validateDefaultRepresentation();
		assertPropPresent("uuid");
		assertPropPresent("display");
	}
	
	@Override
	public void validateFullRepresentation() throws Exception {
		super.validateFullRepresentation();
		assertPropPresent("uuid");
		assertPropPresent("display");
		assertPropPresent("encounterDate");
		assertPropPresent("encounterLocation");
		assertPropPresent("encounterProvider");
		assertPropPresent("patient");
	}
}
