package com.example.i2i.VitalSignManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.example.i2i.VitalSignManagement.dto.Auditable;


@Entity
@Table(name = "VITAL_SIGN_DETAILS")
@Audited
public class VitalSign extends Auditable<String> implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vital_sign_id", unique = true, nullable = false)
	private int vitalSignId;

	@Column(name = "pulseRate")
	private int pulseRate;

	@Column(name = "bp")
	private int bp;

	@Column(name = "temperature")
	private int temperature;

	@Column(name = "respiration_rate")
	private int respirationRate;

	@Column(name = "blood_sugar_level")
	private int bloodSugarlevel;

	@Column(name = "weight")
	private double weight;

	@Column(name = "height")
	private int height;

	@Column(name = "spo2")
	private int spo2;

	@Column(name = "pt_inr")
	private double ptINR;

	@Column(name = "patient_id")
	private int patientID;

	@Column(name = "patient_name")
	private String patientName;
	
	@Column(name = "user_id")
	private int userid;
	
	@Column(name = "user_name")
	private String userName;

	public int getVitalSignId() {
		return vitalSignId;
	}

	public void setVitalSignId(int vitalSignId) {
		this.vitalSignId = vitalSignId;
	}

	public int getPulseRate() {
		return pulseRate;
	}

	public void setPulseRate(int pulseRate) {
		this.pulseRate = pulseRate;
	}

	public int getBp() {
		return bp;
	}

	public void setBp(int bp) {
		this.bp = bp;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getRespirationRate() {
		return respirationRate;
	}

	public void setRespirationRate(int respirationRate) {
		this.respirationRate = respirationRate;
	}

	public int getBloodSugarlevel() {
		return bloodSugarlevel;
	}

	public void setBloodSugarlevel(int bloodSugarlevel) {
		this.bloodSugarlevel = bloodSugarlevel;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSpo2() {
		return spo2;
	}

	public void setSpo2(int spo2) {
		this.spo2 = spo2;
	}

	public double getPtINR() {
		return ptINR;
	}

	public void setPtINR(double ptINR) {
		this.ptINR = ptINR;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
