package com.cts.flightsearchengine.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flightsearchengine.dao.FlightDao;
import com.cts.flightsearchengine.model.Flight;
import com.cts.flightsearchengine.repository.FlightJpaRepository;
import com.cts.flightsearchengine.service.FlightService;

@Controller
@RequestMapping("/flights")
public class FlightController {
	
	@Autowired
	private FlightService flightServiceImplementation;
	
	@RequestMapping("/all")
	public String getAllFlights(Model model){
		List<Flight> flights = flightServiceImplementation.getAllFlightDetails();
		model.addAttribute("flights", flights);
		return "flights";
	}
	
	@RequestMapping("/getFlights")
	public String getFlights(String origin,String destination,int noOfStops,long lowerLimit,long upperLimit,int duration,@DateTimeFormat(pattern="yyyy-MM-dd") Date journeyDate,Model model){
		List<Flight> flights = flightServiceImplementation.getFlights(origin, destination, noOfStops, lowerLimit, upperLimit, duration, journeyDate);
		model.addAttribute("flights", flights);
		return "flights";
	}
	
	@RequestMapping("/getFlights/getFlightsWithNumberOfStops")
	public String getFlightsWithNumberOfStops(int noOfStops,Model model) {
		List<Flight> flights = flightServiceImplementation.getFlightsWithNumberOfStops(noOfStops);
		model.addAttribute("flights", flights);
		return "flights";
	}
	
	@PostMapping(value="/save")
	public void saveFlightDetailsFromFile() throws IOException, ParseException {
		flightServiceImplementation.saveFlightDetailsFromFile();
	}
	
	
	
//	@PostMapping(value="/saveFlightsFromFile")
//	public List<Flight> saveAllFlightsFromFile() throws IOException, ParseException{
//		return flightDao.saveAllFlightDetailsFromFile();
//	}
	
	
}
