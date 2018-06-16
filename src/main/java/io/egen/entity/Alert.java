package io.egen.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Alert {
	
	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name="vin")
	private Vehicle vehicle;
	
	//private String vin;
	
	@OneToOne
	@JoinColumn(name="readingId")
	private Reading reading;
	//private String reading;
	
	private String priority;
	private Date timeStamp;
	
	private String reason;
	public Alert() {
		this.id=UUID.randomUUID().toString();
	}
	
	public Alert(Vehicle vehicle, Reading reading, String priority, Date timeStamp, String reason) {
		super();
		//this.vin = vin;
		this.vehicle=vehicle;
		this.reading = reading;
		this.priority = priority;
		this.timeStamp = timeStamp;
		this.reason = reason;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/*public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getReading() {
		return reading;
	}
	public void setReading(String reading) {
		this.reading = reading;
	}*/
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Reading getReading() {
		return reading;
	}

	public void setReading(Reading reading) {
		this.reading = reading;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
		
}
