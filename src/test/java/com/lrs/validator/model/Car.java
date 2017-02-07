package com.lrs.validator.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Car {
	@NotNull
	private String manufacturer;
	@NotNull
	@Size(min = 2, max = 14)
	private String licensePlate;
	@Min(2)
	private int seatCount;

	public Car() {
		super();
	}

	public Car(String manufacturer, String licensePlate, int seatCount) {
		super();
		this.manufacturer = manufacturer;
		this.licensePlate = licensePlate;
		this.seatCount = seatCount;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
