package org.openmrs.module.customhtmlforms.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.customhtmlforms.CultureResult;
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

@Resource(name = RestConstants.VERSION_1 + "/cultureresult", supportedClass = CultureResult.class, supportedOpenmrsVersions = { "2.*.*" })
public class CultureResultResource extends DataDelegatingCrudResource<CultureResult> {
	
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
	public CultureResult getByUniqueId(String uuid) {
		return Context.getService(CustomHtmlFormsService.class).getCultureResultByUuid(uuid);
	}
	
	@Override
	public CultureResult newDelegate() {
		return new CultureResult();
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		final DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("encounterDate");
		description.addProperty("encounterProvider");
		description.addProperty("encounterLocation");
		description.addProperty("patient");
		description.addProperty("result");
		return description;
	}
	
	@Override
	public CultureResult save(CultureResult cultureResult) {
		return Context.getService(CustomHtmlFormsService.class).addCultureResult(cultureResult);
	}
	
	@PropertyGetter("display")
	public String getDisplayString(CultureResult cultureResult) {
		return cultureResult.getId() + "";
	}
	
	@Override
	protected void delete(CultureResult smearResult, String s, RequestContext requestContext) throws ResponseException {
		throw new ResourceDoesNotSupportOperationException("Not yet supported");
	}
	
	@Override
	public void purge(CultureResult smearResult, RequestContext requestContext) throws ResponseException {
		throw new ResourceDoesNotSupportOperationException("Not yet supported");
	}
	
	@Override
	public String getResourceVersion() {
		return "2.0.0";
	}
	
	@Override
	public NeedsPaging<CultureResult> doGetAll(RequestContext context) {
		return new NeedsPaging<CultureResult>(Context.getService(CustomHtmlFormsService.class).getAllCultureResults(),
		        context);
	}
}
