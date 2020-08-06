package com.cts.flightsearchengine.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.flightsearchengine.model.Flight;

@Service
public interface FlightService {
	public void saveFlightDetailsFromFile() throws IOException, ParseException;
	public List<Flight> getFlights(String source,String destination, int noOfStops, long lowerLimit, long upperLimit, int duration, Date journeyDate);
  	public List<Flight> getAllFlightDetails();
	public List<Flight> getFlightsWithNumberOfStops(int noOfStops);
}
