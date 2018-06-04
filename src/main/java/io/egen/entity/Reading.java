package io.egen.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
public class Reading {
	
	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name="vin")
	private Vehicle vehicle;
	
	@OneToOne
	@JoinColumn(name="id")
	private Alert alert;
	
	private String vinId;	
	private float latitude;
	private float longitude;
	private Date timestamp;	
	private float fuelVolume;
	private int speed;
	private int engineHp;
	private boolean checkEngineLightOn;
	private boolean engineCoolantLow;
	private boolean cruiseControlOn;
	private int engineRpm;


	@JsonProperty("tires")
	@JsonDeserialize
	@Transient
	private Tires tires;
	
	private int frontLeft;
	private int frontRight;
	private int rearLeft;
	private int rearRight;
	
//	private Date timestamp;
	
	public Reading() {
		// TODO Auto-generated constructor stub
		this.id=UUID.randomUUID().toString();
	}
	
	/*public Reading(Vehicle vehicle, int latitude, int longitute, String timestamp, float fuelVolume, int speed, int engineHp,
			boolean checkEngineLightOn, boolean engineCoolantLow, boolean cruiseControlOn, int engineRpm) {
		super();
		this.id=UUID.randomUUID().toString();
		this.vin = vehicle;
		this.latitude = latitude;
		this.longitute = longitute;
		this.timestamp = timestamp;
		this.fuelVolume = fuelVolume;
		this.speed = speed;
		this.engineHp = engineHp;
		this.checkEngineLightOn = checkEngineLightOn;
		this.engineCoolantLow = engineCoolantLow;
		this.cruiseControlOn = cruiseControlOn;
		this.engineRpm = engineRpm;
	}
	*/

	public Reading(String vinId, float latitude, float longitute, Date timestamp, float fuelVolume, int speed, int engineHp,
			boolean checkEngineLightOn, boolean engineCoolantLow, boolean cruiseControlOn, int engineRpm,int frontLeft,int frontRight, int rearLeft, int rearRight) {
		super();
		this.vinId=vinId;
		
		this.latitude = latitude;
		this.longitude = longitute;
		this.timestamp = timestamp;
		this.fuelVolume = fuelVolume;
		this.speed = speed;
		this.engineHp = engineHp;
		this.checkEngineLightOn = checkEngineLightOn;
		this.engineCoolantLow = engineCoolantLow;
		this.cruiseControlOn = cruiseControlOn;
		this.engineRpm = engineRpm;
		this.frontLeft = frontLeft;
		this.frontRight = frontRight;
		this.rearLeft = rearLeft;
		this.rearRight = rearRight;
	}
	
	public Reading(String vinId, float latitude, float longitute, Date timestamp, float fuelVolume, int speed, int engineHp,
			boolean checkEngineLightOn, boolean engineCoolantLow, boolean cruiseControlOn, int engineRpm, Tires tires) {
		super();
		this.vinId=vinId;
		
		this.latitude = latitude;
		this.longitude = longitute;
		this.timestamp = timestamp;
		this.fuelVolume = fuelVolume;
		this.speed = speed;
		this.engineHp = engineHp;
		this.checkEngineLightOn = checkEngineLightOn;
		this.engineCoolantLow = engineCoolantLow;
		this.cruiseControlOn = cruiseControlOn;
		this.engineRpm = engineRpm;
		this.tires = tires;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	


	public String getVin() {
		return vinId;
	}

	public void setVin(String vin) {
		this.vinId = vin;
	}

	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public float getFuelVolume() {
		return fuelVolume;
	}
	public void setFuelVolume(float fuelVolume) {
		this.fuelVolume = fuelVolume;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getEngineHp() {
		return engineHp;
	}
	public void setEngineHp(int engineHp) {
		this.engineHp = engineHp;
	}
	public boolean isCheckEngineLightOn() {
		return checkEngineLightOn;
	}
	public void setCheckEngineLightOn(boolean checkEngineLightOn) {
		this.checkEngineLightOn = checkEngineLightOn;
	}
	public boolean isEngineCoolantLow() {
		return engineCoolantLow;
	}
	public void setEngineCoolantLow(boolean engineCoolantLow) {
		this.engineCoolantLow = engineCoolantLow;
	}
	public boolean isCruiseControlOn() {
		return cruiseControlOn;
	}
	public void setCruiseControlOn(boolean cruiseControlOn) {
		this.cruiseControlOn = cruiseControlOn;
	}
	public int getEngineRpm() {
		return engineRpm;
	}
	public void setEngineRpm(int engineRpm) {
		this.engineRpm = engineRpm;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public String getVinId() {
		return vinId;
	}

	public void setVinId(String vinId) {
		this.vinId = vinId;
	}

	
	public int getFrontLeft() {
		return frontLeft;
	}

	public void setFrontLeft(int frontLeft) {
		this.frontLeft = frontLeft;
	}

	public int getFrontRight() {
		return frontRight;
	}

	public void setFrontRight(int frontRight) {
		this.frontRight = frontRight;
	}

	public int getRearLeft() {
		return rearLeft;
	}

	public void setRearLeft(int rearLeft) {
		this.rearLeft = rearLeft;
	}

	public int getRearRight() {
		return rearRight;
	}

	public void setRearRight(int rearRight) {
		this.rearRight = rearRight;
	}

	public Tires getTires() {
		return tires;
	}

	public void setTires(Tires tires) {
		this.tires = tires;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
