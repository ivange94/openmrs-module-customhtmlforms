package org.openmrs.module.customhtmlforms.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.customhtmlforms.HivTestResult;
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
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + "/hivtestresult", supportedClass = HivTestResult.class, supportedOpenmrsVersions = { "1.*.*" })
public class HivTestResultResource extends DataDelegatingCrudResource<HivTestResult> {
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		if (representation instanceof DefaultRepresentation) {
			final DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("display");
			description.addProperty("encounter");
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
			description.addSelfLink();
			description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
			return description;
		}
		return null;
	}
	
	@Override
	public HivTestResult getByUniqueId(String uuid) {
		return Context.getService(CustomHtmlFormsService.class).getHivTestResultByUuid(uuid);
	}
	
	@Override
	public HivTestResult newDelegate() {
		return new HivTestResult();
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		final DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("encounterDate");
		description.addProperty("encounterProvider");
		description.addProperty("encounterLocation");
		description.addProperty("patient");
		description.addProperty("result1");
		description.addProperty("result2");
		description.addProperty("date1");
		description.addProperty("date2");
		return description;
	}
	
	@Override
	public HivTestResult save(HivTestResult hivTestResult) {
		return Context.getService(CustomHtmlFormsService.class).addHivTestResult(hivTestResult);
	}
	
	@PropertyGetter("display")
	public String getDisplayString(HivTestResult hivTestResult) {
		return hivTestResult.getId() + "";
	}
	
	@Override
	protected void delete(HivTestResult hivTestResult, String s, RequestContext requestContext) throws ResponseException {
		throw new ResourceDoesNotSupportOperationException("Not yet supported");
	}
	
	@Override
	public void purge(HivTestResult hivTestResult, RequestContext requestContext) throws ResponseException {
		throw new ResourceDoesNotSupportOperationException("Not yet supported");
	}
	
	@Override
	public String getResourceVersion() {
		return "1.0.0";
	}
	
	@Override
	public NeedsPaging<HivTestResult> doGetAll(RequestContext context) {
		return new NeedsPaging<HivTestResult>(Context.getService(CustomHtmlFormsService.class).getAllHivTestResults(),
		        context);
	}
}
