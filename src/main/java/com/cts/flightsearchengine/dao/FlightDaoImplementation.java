package com.cts.flightsearchengine.dao;

import com.cts.flightsearchengine.model.*;
import com.cts.flightsearchengine.repository.FlightJpaRepository;
import com.cts.flightsearchengine.service.FlightService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class FlightDaoImplementation implements FlightDao{

	//private static List<Flight> flightDetails = new ArrayList<Flight>();
	
	
	
	@Autowired
	private FlightJpaRepository flightJpaRepository;
	
	public void saveFlightDetailsFromFile() throws IOException, ParseException{
		File file = new File("C:\\Users\\mpros\\Documents\\workspace-spring-tool-suite-4-4.7.0.RELEASE\\FlightSearchEngineProject\\src\\main\\resources\\flight_schedule.txt");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = bufferedReader.readLine();
		while(line != null) {
			String[] str = line.split("\t");
			System.out.println(str.length);
			String[] originWithCode = str[0].split(" ");
			String origin = originWithCode[0];
			String originCode = originWithCode[1];
			String[] destinationWithCode = str[1].split(" ");
			String destination = destinationWithCode[0];
			String destinationCode = destinationWithCode[1];
			String flightNumber = str[2];
			String departure = str[3]+":00";
//			DateFormat dateFormat = new SimpleDateFormat("hh:mm z");
//			Date departureTime = dateFormat.parse(departure);
			Time departureTime = java.sql.Time.valueOf(departure);
			String arrival = str[4]+":00";
			Time arrivalTime = java.sql.Time.valueOf(arrival);
			
			long diff = arrivalTime.getTime() - departureTime.getTime();
			int hoursDiff = Math.abs(arrivalTime.getHours()-departureTime.getHours());
			int minutesDiff = Math.abs(arrivalTime.getMinutes()-departureTime.getMinutes());
			String duration = hoursDiff+"hrs "+minutesDiff+"min";
			String flightType = str[5];
			String via = str[6];
			String daysOfOperation = str[7];
			
	//		long lowerLimit = 3000L;
	//		long upperLimit = 10000L;
			long price = (long) (Math.random() * (10000 - 3000) + 3000); 
			Flight flight = new Flight(origin,destination,flightNumber,departureTime,arrivalTime,flightType,via,daysOfOperation,price);
			originCode = originCode.replace("(","");
			originCode = originCode.replace(")", "");
			destinationCode = destinationCode.replace(")", "");
			destinationCode = destinationCode.replace("(", "");
			flight.setOriginCode(originCode);
			flight.setDestinationCode(destinationCode);
			flight.setDuration(duration);
			flightJpaRepository.save(flight);
			line = bufferedReader.readLine();
		}
	}
	
	public List<Flight> getFlights(String source,String destination,int noOfStops,long lowerLimit,long upperLimit, int duration, Date journeyDate){
		List<Flight> flightList = new ArrayList<Flight>(); 
		flightList = flightJpaRepository.findAll();
		List<Flight> result = new ArrayList<Flight>();
		for(int i=0;i<flightList.size();i++) {
			SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
			String day = simpleDateformat.format(journeyDate);
			day = day.toLowerCase();
			if(((flightList.get(i).getOrigin()).equals(source) || (flightList.get(i).getOriginCode()).equals(source)) && ((flightList.get(i).getDestination()).equals(destination) || (flightList.get(i).getDestinationCode()).equals(destination)) && flightList.get(i).getPrice()>=lowerLimit && flightList.get(i).getPrice()<=upperLimit) {
				String[] via = (flightList.get(i).getVia()).split("/");
				String[] str = (flightList.get(i).getDuration()).split(" ");
				String[] hours = str[0].split("h");
				String[] minutes = str[1].split("m");
				int time = Integer.parseInt(hours[0]);
				String[] dayArr = flightList.get(i).getDaysOfOperation().split(" ");
				String[] dayOfWeekIncluding= {};
				String[] dayOfWeekExcept= {};
				if(dayArr.length==2 && dayArr[0].equals("Every")) {
					dayOfWeekIncluding = (dayArr[1].toLowerCase()).split("/");
				}
				else if(dayArr.length==2 && dayArr[0].equals("Except")) {
					dayOfWeekExcept = (dayArr[1].toLowerCase()).split("/");
				}
				if(((flightList.get(i).getFlightType()).equals("Connecting") || (flightList.get(i).getFlightType()).equals("Via")) && via.length==noOfStops && time<duration) {
					if((flightList.get(i).getDaysOfOperation()).equals("Daily")) {
						result.add(flightList.get(i));
					}
					else if(dayArr[0].equals("Every")){
						for(int j=0;j<dayOfWeekIncluding.length;j++) {
							if(dayOfWeekIncluding[j].equals(day)) {
								result.add(flightList.get(i));
							}
						}
					}
					else if(dayArr[0].equals("Except")){
						int flag=1;
						for(int j=0;j<dayOfWeekExcept.length;j++) {
							if(dayOfWeekExcept[j].equals(day)) {
								flag=0;
							}
						}
						if(flag==1) {
							result.add(flightList.get(i));
						}
					}
				}
				else if((flightList.get(i).getFlightType()).equals("Direct") && noOfStops==0) {
					if((flightList.get(i).getDaysOfOperation()).equals("Daily")) {
						result.add(flightList.get(i));
					}
					else if(dayArr[0].equals("Every")){
						for(int j=0;j<dayOfWeekIncluding.length;j++) {
							if(dayOfWeekIncluding[j].equals(day)) {
								result.add(flightList.get(i));
							}
						}
					}
					else if(dayArr[0].equals("Except")){
						int flag=1;
						for(int j=0;j<dayOfWeekExcept.length;j++) {
							if(dayOfWeekExcept[j].equals(day)) {
								flag=0;
							}
						}
						if(flag==1) {
							result.add(flightList.get(i));
						}
					}
				}
//				else if(((flightList.get(i).getFlightType()).equals("Connecting") || (flightList.get(i).getFlightType()).equals("Via")) && via.length==noOfStops && time<duration && dayOfWeekIncluding.equals(day)) {
//					result.add(flightList.get(i));
//				}
//				else if((flightList.get(i).getFlightType()).equals("Direct") && noOfStops==0 && dayOfWeekIncluding.equals(day)) {
//					result.add(flightList.get(i));
//				}
//				else if(((flightList.get(i).getFlightType()).equals("Connecting") || (flightList.get(i).getFlightType()).equals("Via")) && via.length==noOfStops && time<duration) {
//					
//				}
//				else if((flightList.get(i).getFlightType()).equals("Direct") && noOfStops==0 && !dayOfWeekExcept.equals(day) && dayOfWeekExcept!="") {
//					result.add(flightList.get(i));
//				}
			}
		}
		return result;
	}

	
	
	public List<Flight> getAllFlightDetails() {
		return flightJpaRepository.findAll();
	}

	@Override
	public List<Flight> getFlightsWithNumberOfStops(int noOfStops) {
		List<Flight> flightList = new ArrayList<Flight>();
		flightList = flightJpaRepository.findAll();
		List<Flight> result = new ArrayList<Flight>();
		for(int i=0;i<flightList.size();i++) {
			if((flightList.get(i).getFlightType()).equals("Connecting") || (flightList.get(i).getFlightType()).equals("Via")) {
				String[] via = (flightList.get(i).getVia()).split("/");
				if(via.length == noOfStops) {
					result.add(flightList.get(i));
				}
			}
			else if((flightList.get(i).getFlightType()).equals("Direct") && noOfStops==0) {
				result.add(flightList.get(i));
			}
		}
		return result;
	}
	
	
	
}
