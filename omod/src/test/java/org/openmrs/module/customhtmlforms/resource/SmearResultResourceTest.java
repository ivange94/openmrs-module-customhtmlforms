package org.openmrs.module.customhtmlforms.resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.openmrs.api.context.Context;
import org.openmrs.module.customhtmlforms.SmearResult;
import org.openmrs.module.customhtmlforms.api.CustomHtmlFormsService;
import org.openmrs.module.customhtmlforms.web.resource.SmearResultResource;
import org.openmrs.module.webservices.rest.web.RestUtil;
import org.openmrs.module.webservices.rest.web.representation.CustomRepresentation;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Context.class, RestUtil.class })
public class SmearResultResourceTest {
	
	private static final String SMEAR_RESULT_UUID = "0072401d-9d96-464f-b753-b726f0e33395";
	
	@Mock
	CustomHtmlFormsService customHtmlFormsService;
	
	@Mock
	SmearResultResource smearResultResource = new SmearResultResource();
	
	SmearResult smearResult = new SmearResult();
	
	@Before
	public void setUp() {
		smearResult.setUuid(SMEAR_RESULT_UUID);
		
		PowerMockito.mockStatic(RestUtil.class);
		PowerMockito.mockStatic(Context.class);
		when(Context.getService(CustomHtmlFormsService.class)).thenReturn(customHtmlFormsService);
		when(customHtmlFormsService.getSmearResultByUuid(SMEAR_RESULT_UUID)).thenReturn(smearResult);
	}
	
	@Test
	public void getRepresentationDescription_shouldReturnDefaultRepresentationGivenInstanceOfDefaultRepresentation()
	        throws Exception {
		DefaultRepresentation defaultRepresentation = new DefaultRepresentation();
		
		DelegatingResourceDescription resourceDescription = smearResultResource
		        .getRepresentationDescription(defaultRepresentation);
		assertNotNull(resourceDescription);
		assertThat(resourceDescription.getProperties().keySet(), contains("uuid", "display"));
	}
	
	@Test
	public void getRepresentationDescription_shouldReturnFullRepresentationGivenInstanceOfFullRepresentation()
	        throws Exception {
		FullRepresentation fullRepresentation = new FullRepresentation();
		
		DelegatingResourceDescription resourceDescription = smearResultResource
		        .getRepresentationDescription(fullRepresentation);
		assertNotNull(resourceDescription);
		assertThat(resourceDescription.getProperties().keySet(),
		    contains("uuid", "display", "encounterDate", "encounterLocation", "encounterProvider", "patient"));
	}
	
	@Test
	public void getRepresentationDescription_shouldReturnNullForRepresentationOtherThenDefaultOrFull() throws Exception {
		CustomRepresentation customRepresentation = new CustomRepresentation("some");
		
		assertThat(smearResultResource.getRepresentationDescription(customRepresentation), is(nullValue()));
	}
	
	@Test
	public void getByUniqueId_shouldGetSmearResultWithGivenUuid() throws Exception {
		SmearResult returned = smearResultResource.getByUniqueId(SMEAR_RESULT_UUID);
		assertNotNull(returned);
		assertThat(returned, is(smearResult));
	}
	
	@Test(expected = ResourceDoesNotSupportOperationException.class)
	@Ignore
	public void newDelegate_shouldThrowResourceDoesNotSupportOperationException() throws Exception {
		smearResultResource.newDelegate();
	}
	
	@Test(expected = ResourceDoesNotSupportOperationException.class)
	@Ignore
	public void save_shouldThrowResourceDoesNotSupportOperationException() throws Exception {
		smearResultResource.save(smearResult);
	}
	
	@Test(expected = ResourceDoesNotSupportOperationException.class)
	public void purge_shouldThrowResourceDoesNotSupportOperationException() throws Exception {
		smearResultResource.purge(smearResult, null);
	}
}
