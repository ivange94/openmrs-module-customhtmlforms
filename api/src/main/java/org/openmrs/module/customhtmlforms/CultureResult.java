package org.openmrs.module.customhtmlforms;

import org.openmrs.*;

import javax.persistence.*;
import java.util.Date;

public class CultureResult extends BaseOpenmrsData {
	
	private Integer id;
	
	private Date encounterDate;
	
	private Location encounterLocation;
	
	private Provider encounterProvider;
	
	private Encounter encounter;
	
	private Patient patient;
	
	private Integer result;
	
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
	
	public Integer getResult() {
		return result;
	}
	
	public void setResult(Integer result) {
		this.result = result;
	}
}
