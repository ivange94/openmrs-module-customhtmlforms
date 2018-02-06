package org.openmrs.module.customhtmlforms.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.customhtmlforms.DstTestResult;
import org.openmrs.module.customhtmlforms.SmearResult;
import org.openmrs.module.customhtmlforms.TbHivInformation;
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

@Resource(name = RestConstants.VERSION_1 + "/tbhivinformation", supportedClass = TbHivInformation.class, supportedOpenmrsVersions = { "1.*.*" })
public class TbHivInformationResource extends DataDelegatingCrudResource<TbHivInformation> {
	
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
	public TbHivInformation getByUniqueId(String uuid) {
		return Context.getService(CustomHtmlFormsService.class).getTbHivInformationByUuid(uuid);
	}
	
	@Override
	public TbHivInformation newDelegate() throws ResourceDoesNotSupportOperationException {
		return new TbHivInformation();
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		final DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("encounterDate");
		description.addProperty("encounterProvider");
		description.addProperty("encounterLocation");
		description.addProperty("patient");
		description.addProperty("hivSerologyResult");
		description.addProperty("serologyResultDate");
		return description;
	}
	
	@Override
	public TbHivInformation save(TbHivInformation tbHivInformation) {
		return Context.getService(CustomHtmlFormsService.class).addTbHivInformation(tbHivInformation);
	}
	
	@PropertyGetter("display")
	public String getDisplayString(TbHivInformation tbHivInformation) {
		return tbHivInformation.getId() + "";
	}
	
	@Override
	protected void delete(TbHivInformation tbHivInformation, String s, RequestContext requestContext)
	        throws ResponseException {
		throw new ResourceDoesNotSupportOperationException("Not yet supported");
	}
	
	@Override
	public void purge(TbHivInformation tbHivInformation, RequestContext requestContext) throws ResponseException {
		throw new ResourceDoesNotSupportOperationException("Not yet supported");
	}
	
	@Override
	public String getResourceVersion() {
		return "1.9.11";
	}
	
	@Override
	public NeedsPaging<TbHivInformation> doGetAll(RequestContext context) {
		return new NeedsPaging<TbHivInformation>(Context.getService(CustomHtmlFormsService.class).getAllTbHivInformation(),
		        context);
	}
}
