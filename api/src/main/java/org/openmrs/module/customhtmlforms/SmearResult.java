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
	
	@Column(name = "sample_id")
	private String sampleId;
	
	@Column(name = "sample_type")
	private Integer sampleType;
	
	@Column(name = "appearance")
	private Integer appearance;
	
	@Column(name = "result")
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
	
	public String getSampleId() {
		return sampleId;
	}
	
	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}
	
	public Integer getSampleType() {
		return sampleType;
	}
	
	public void setSampleType(Integer sampleType) {
		this.sampleType = sampleType;
	}
	
	public Integer getAppearance() {
		return appearance;
	}
	
	public void setAppearance(Integer appearance) {
		this.appearance = appearance;
	}
	
	public Integer getResult() {
		return result;
	}
	
	public void setResult(Integer result) {
		this.result = result;
	}
}
