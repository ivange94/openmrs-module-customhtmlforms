package org.openmrs.module.customhtmlforms;

import org.openmrs.*;

import java.util.Date;

public class TbHivInformation extends BaseOpenmrsData {
	
	private Integer id;
	
	private Date encounterDate;
	
	private Location encounterLocation;
	
	private Provider encounterProvider;
	
	private Encounter encounter;
	
	private Patient patient;
	
	private Integer hivSerologyResult;
	
	private Date serologyResultDate;
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getEncounterDate() {
		return encounterDate;
	}
	
	public void setEncounterDate(Date encounterDate) {
		this.encounterDate = encounterDate;
	}
	
	public Location getEncounterLocation() {
		return encounterLocation;
	}
	
	public void setEncounterLocation(Location encounterLocation) {
		this.encounterLocation = encounterLocation;
	}
	
	public Provider getEncounterProvider() {
		return encounterProvider;
	}
	
	public void setEncounterProvider(Provider encounterProvider) {
		this.encounterProvider = encounterProvider;
	}
	
	public Encounter getEncounter() {
		return encounter;
	}
	
	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Integer getHivSerologyResult() {
		return hivSerologyResult;
	}
	
	public void setHivSerologyResult(Integer hivSerologyResult) {
		this.hivSerologyResult = hivSerologyResult;
	}
	
	public Date getSerologyResultDate() {
		return serologyResultDate;
	}
	
	public void setSerologyResultDate(Date serologyResultDate) {
		this.serologyResultDate = serologyResultDate;
	}
}
