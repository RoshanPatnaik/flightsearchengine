package com.cts.flightsearchengine.model;

import java.util.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Flight {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String origin;
	private String originCode;
	private String destination;
	private String destinationCode;
	private String flightNumber;
	private Time departureTime;
	private Time arrivalTime;
	private String flightType;
	private String via;
	private String daysOfOperation;
	private String duration;

	private long price;
	
	
	
	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Flight(String origin, String destination, String flightNumber, Time departureTime, Time arrivalTime,
			String flightType, String via, String daysOfOperation, long price) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.flightNumber = flightNumber;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.flightType = flightType;
		this.via = via;
		this.daysOfOperation = daysOfOperation;
		this.price = price;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public Date getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}
	public Time getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getFlightType() {
		return flightType;
	}
	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getDaysOfOperation() {
		return daysOfOperation;
	}
	public void setDaysOfOperation(String daysOfOperation) {
		this.daysOfOperation = daysOfOperation;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}

	public String getOriginCode() {
		return originCode;
	}

	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}

	public String getDestinationCode() {
		return destinationCode;
	}

	public void setDestinationCode(String destinationCode) {
		this.destinationCode = destinationCode;
	}
	
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}


	@Override
	public String toString() {
		return "Flight [id=" + id + ", origin=" + origin + ", originCode=" + originCode + ", destination=" + destination
				+ ", destinationCode=" + destinationCode + ", flightNumber=" + flightNumber + ", departureTime="
				+ departureTime + ", arrivalTime=" + arrivalTime + ", flightType=" + flightType + ", via=" + via
				+ ", daysOfOperation=" + daysOfOperation + ", duration=" + duration + ", price=" + price + "]";
	}

	
	
	
}

