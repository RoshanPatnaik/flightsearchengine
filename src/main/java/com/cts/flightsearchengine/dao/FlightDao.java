package com.cts.flightsearchengine.dao;

import com.cts.flightsearchengine.model.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface FlightDao{
	
	public void saveFlightDetailsFromFile() throws IOException, ParseException;
	public List<Flight> getFlights(String source,String destination,int noOfStops, long lowerLimit, long upperLimit, int duration, Date journeyDate);
	public List<Flight> getAllFlightDetails();
	public List<Flight> getFlightsWithNumberOfStops(int noOfStops);
	
	
	
	
}
