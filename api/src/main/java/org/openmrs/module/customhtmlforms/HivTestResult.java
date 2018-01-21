package org.openmrs.module.customhtmlforms;

import org.openmrs.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "customhtmlforms.HivTestResult")
@Table(name = "customhtmlforms_hiv_test_result")
public class HivTestResult extends BaseOpenmrsData {
	
	@Id
	@GeneratedValue
	@Column(name = "customhtmlforms_hiv_test_result_id")
	private Integer id;
	
	@Column(name = "encounter_date")
	private Date encounterDate;
	
	@ManyToOne
	@JoinColumn(name = "encounter_location")
	private Location encounterLocation;
	
	@ManyToOne
	@JoinColumn(name = "encounter_provider")
	private Provider encounterProvider;
	
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "encounter")
	private Encounter encounter;
	
	@ManyToOne
	@JoinColumn(name = "patient")
	private Patient patient;
	
	@Column(name = "result1")
	private Integer result1;
	
	@Column(name = "result2")
	private Integer result2;
	
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
}
