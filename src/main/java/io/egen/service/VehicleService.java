package io.egen.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.entity.Alert;
import io.egen.entity.Reading;
import io.egen.entity.Vehicle;
import io.egen.exception.ResourceNotFoundException;
import io.egen.repository.AlertRepository;
import io.egen.repository.ReadingsRepository;
import io.egen.repository.VehicleRepository;

@Service
public class VehicleService {
	
	@Autowired
	VehicleRepository repository;
	
	@Autowired
	ReadingsRepository readingRepo;
	
	@Autowired
	AlertRepository alertRepo;
	
	public List<Vehicle> findAll() {
        return (List<Vehicle>) repository.findAll();
    }
	public Vehicle create(Vehicle vehicle) {
		// TODO Auto-generated method stub
		repository.save(vehicle);
		return vehicle;
	}
	public Optional<Vehicle> findOne(String vin) {
		// TODO Auto-generated method stub
		return repository.findById(vin);
	}
	
	public List<Vehicle> findVehiclesWithHigh() {
		// TODO Auto-generated method stub
		
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.HOUR, -2);
		List<Alert> highPriorityAlerts = alertRepo.findByPriorityAndTimeStampGreaterThanOrderByTimeStampDesc("HIGH", cal.getTime());
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		for (Alert alert : highPriorityAlerts) {
			vehicles.add(alert.getVehicle());
		}
		return vehicles;
	}
	
	public List<Reading> findVehicleReadings(String vin) {
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.MINUTE, -30);
		Optional<Vehicle> vehicle = repository.findById(vin);
		if(vehicle.isPresent()) {
			List<Reading> readings = readingRepo.findByVehicleAndTimestampGreaterThanOrderByTimestampDesc(vehicle.get(), cal.getTime());
			return readings;
		} else {
			throw new ResourceNotFoundException();
		}
	}
	
	public List<Alert> findVehicleAlerts(String vin){
		Optional<Vehicle> vehicle = repository.findById(vin);
		if(vehicle.isPresent()) {
			List<Alert> alertsList = alertRepo.findByVehicleOrderByTimeStampDesc(vehicle.get());
			return alertsList;
		} else {
			throw new ResourceNotFoundException();
		}
	}
	
}
