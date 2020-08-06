package com.cts.flightsearchengine.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.flightsearchengine.dao.FlightDao;
import com.cts.flightsearchengine.model.Flight;

@Service
public class FlightServiceImplementation implements FlightService{
	
	@Autowired
	private FlightDao flightDaoImplementation;

	@Override
	public void saveFlightDetailsFromFile() throws IOException, ParseException {
		flightDaoImplementation.saveFlightDetailsFromFile();
		
	}

	@Override
	public List<Flight> getFlights(String source, String destination, int noOfStops, long lowerLimit, long upperLimit, int duration, Date journeyDate) {
		return flightDaoImplementation.getFlights(source, destination, noOfStops, lowerLimit, upperLimit, duration,journeyDate);
	}

	@Override
	public List<Flight> getAllFlightDetails() {
		return flightDaoImplementation.getAllFlightDetails();
	}

	@Override
	public List<Flight> getFlightsWithNumberOfStops(int noOfStops) {
		return flightDaoImplementation.getFlightsWithNumberOfStops(noOfStops);
	}
	
}
