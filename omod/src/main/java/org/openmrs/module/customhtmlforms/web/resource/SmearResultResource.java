package org.openmrs.module.customhtmlforms.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.customhtmlforms.SmearResult;
import org.openmrs.module.customhtmlforms.api.CustomHtmlFormsService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + "/smearresult", supportedClass = SmearResult.class, supportedOpenmrsVersions = { "1.*.*" })
public class SmearResultResource extends DataDelegatingCrudResource<SmearResult> {
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		if (representation instanceof DefaultRepresentation) {
			final DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("display");
			description.addProperty("encounter", Representation.REF);
			description.addSelfLink();
			description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
			return description;
		} else if (representation instanceof FullRepresentation) {
			final DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("display");
			description.addProperty("encounterDate");
			description.addProperty("encounterLocation", Representation.REF);
			description.addProperty("encounterProvider", Representation.REF);
			description.addProperty("patient", Representation.REF);
			description.addProperty("encounter", Representation.REF);
			description.addSelfLink();
			description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
			return description;
		}
		return null;
	}
	
	@Override
	public SmearResult getByUniqueId(String uuid) {
		return Context.getService(CustomHtmlFormsService.class).getSmearResultByUuid(uuid);
	}
	
	@Override
	public SmearResult newDelegate() {
		return new SmearResult();
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		final DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("encounterDate");
		description.addProperty("encounterProvider");
		description.addProperty("encounterLocation");
		description.addProperty("patient");
		description.addProperty("sampleId");
		description.addProperty("sampleType");
		description.addProperty("appearance");
		description.addProperty("result");
		return description;
	}
	
	@Override
	public SmearResult save(SmearResult smearResult) {
		return Context.getService(CustomHtmlFormsService.class).addSmearResult(smearResult);
	}
	
	@PropertyGetter("display")
	public String getDisplayString(SmearResult smearResult) {
		return smearResult.getId() + "";
	}
	
	@Override
	protected void delete(SmearResult smearResult, String s, RequestContext requestContext) throws ResponseException {
		throw new ResourceDoesNotSupportOperationException("Not yet supported");
	}
	
	@Override
	public void purge(SmearResult smearResult, RequestContext requestContext) throws ResponseException {
		throw new ResourceDoesNotSupportOperationException("Not yet supported");
	}
	
	@Override
	public String getResourceVersion() {
		return "1.9.11";
	}
}
