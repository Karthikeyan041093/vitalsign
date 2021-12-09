package com.example.i2i.VitalSignManagement.dto;

public class VitalSignDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int vitalSignId;
	private int pulseRate;
	private int bp;
	private int temperature;
	private int respirationRate;
	private int bloodSugarlevel;
	private double weight;
	private int height;
	private int spo2;
	private double ptINR;
	private int patientID;
	private String patientName;
	private int userid;
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
