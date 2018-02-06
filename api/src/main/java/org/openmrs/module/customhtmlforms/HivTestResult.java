package org.openmrs.module.customhtmlforms;

import org.openmrs.*;

import java.util.Date;

public class HivTestResult extends BaseOpenmrsData {
	
	private Integer id;
	
	private Date encounterDate;
	
	private Location encounterLocation;
	
	private Provider encounterProvider;
	
	private Encounter encounter;
	
	private Patient patient;
	
	private Integer result1;
	
	private Integer result2;
	
	private Date date1;
	
	private Date date2;
	
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
	
	public Integer getResult1() {
		return result1;
	}
	
	public void setResult1(Integer result1) {
		this.result1 = result1;
	}
	
	public Integer getResult2() {
		return result2;
	}
	
	public void setResult2(Integer result2) {
		this.result2 = result2;
	}
	
	public Date getDate1() {
		return date1;
	}
	
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	
	public Date getDate2() {
		return date2;
	}
	
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
}
