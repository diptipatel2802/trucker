package io.egen.entity;

import java.util.UUID;

import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Tires {
	
	@Id
	private String id;
	@JsonProperty("frontLeft")
	private int frontLeft;
	@JsonProperty("frontRight")
	private int frontRight;
	@JsonProperty("rearLeft")
	private int rearLeft;
	@JsonProperty("rearRight")
	private int rearRight;
	
	public Tires() {
		this.id=UUID.randomUUID().toString();
	}
	public Tires(int frontLeft, int frontRight, int rearLeft, int rearRight) {
		super();
		this.frontLeft = frontLeft;
		this.frontRight = frontRight;
		this.rearLeft = rearLeft;
		this.rearRight = rearRight;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
}
