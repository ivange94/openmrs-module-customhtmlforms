package org.openmrs.module.customhtmlforms.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.customhtmlforms.DstTestResult;
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

@Resource(name = RestConstants.VERSION_1 + "/dsttestresult", supportedClass = DstTestResult.class, supportedOpenmrsVersions = { "1.*.*" })
public class DstTestResultResource extends DataDelegatingCrudResource<DstTestResult> {
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		if (representation instanceof DefaultRepresentation) {
			final DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("display");
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
	public DstTestResult getByUniqueId(String uuid) {
		return null;
	}
	
	@Override
	public DstTestResult newDelegate() throws ResourceDoesNotSupportOperationException {
		throw new ResourceDoesNotSupportOperationException("Not yet supported");
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		final DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("name");
		return description;
	}
	
	@Override
	public DstTestResult save(DstTestResult dstTestResult) {
		throw new ResourceDoesNotSupportOperationException("Not yet supported");
	}
	
	@PropertyGetter("display")
	public String getDisplayString(DstTestResult smearResult) {
		return smearResult.getId() + "";
	}
	
	@Override
	protected void delete(DstTestResult dstTestResult, String s, RequestContext requestContext) throws ResponseException {
		throw new ResourceDoesNotSupportOperationException("Not yet supported");
	}
	
	@Override
	public void purge(DstTestResult dstTestResult, RequestContext requestContext) throws ResponseException {
		throw new ResourceDoesNotSupportOperationException("Not yet supported");
	}
	
	@Override
	public String getResourceVersion() {
		return "1.11.6";
	}
	
	@Override
	public NeedsPaging<DstTestResult> doGetAll(RequestContext context) {
		return new NeedsPaging<DstTestResult>(Context.getService(CustomHtmlFormsService.class).getAllDstTestResults(),
		        context);
	}
}
