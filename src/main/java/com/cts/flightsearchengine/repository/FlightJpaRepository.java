package com.cts.flightsearchengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cts.flightsearchengine.model.Flight;

@Repository
public interface FlightJpaRepository extends JpaRepository<Flight,Long>{
	
}
