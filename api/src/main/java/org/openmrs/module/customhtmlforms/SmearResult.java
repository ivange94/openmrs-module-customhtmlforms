package org.openmrs.module.customhtmlforms;

import org.openmrs.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "customhtmlforms.SmearResult")
@Table(name = "customhtmlforms_smear_result")
public class SmearResult extends BaseOpenmrsData {
	
	@Id
	@GeneratedValue
	@Column(name = "customhtmlforms_smear_result_id")
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
}
