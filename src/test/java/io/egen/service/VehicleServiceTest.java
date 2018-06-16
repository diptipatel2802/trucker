package io.egen.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import io.egen.entity.Alert;
import io.egen.entity.Reading;
import io.egen.entity.Vehicle;
import io.egen.exception.ResourceNotFoundException;
import io.egen.repository.AlertRepository;
import io.egen.repository.ReadingsRepository;
import io.egen.repository.VehicleRepository;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
public class VehicleServiceTest {
	
	@Autowired
	private VehicleService vehicleService;
	
	@MockBean
	VehicleRepository repository;
	
	@MockBean
	ReadingsRepository readingRepo;
	
	@MockBean
	AlertRepository alertRepo;
	
	List<Vehicle> expectedVehicleList;
	List<Alert> expectedAlertList;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		expectedVehicleList=new ArrayList<Vehicle>();
		expectedAlertList = new ArrayList<Alert>();
		Vehicle vehicle = new Vehicle();
		vehicle.setMake("HONDA"); 
		vehicle.setModel("ACCORD"); 
		vehicle.setYear(2015);  
		vehicle.setRedlineRpm(5000); 
		vehicle.setMaxFuelVolume(15); 
		vehicle.setLastServiceDate(new Date().toString()); 
		expectedVehicleList.add(vehicle);
		
		Reading reading = new Reading();
		reading.setLongitude((float) 41.803194);
		reading.setLatitude((float) -88.14);
		reading.setFuelVolume((float) 1.5);
		reading.setCheckEngineLightOn(true);
		reading.setCruiseControlOn(false);
		reading.setEngineCoolantLow(true);
		reading.setEngineHp(200);
		reading.setEngineRpm(2800);
		reading.setFrontLeft(34);
		reading.setFrontRight(32);
		reading.setRearLeft(28);
		reading.setRearRight(20);
		reading.setTimestamp(new Date());
		reading.setVehicle(vehicle);
		
		Alert alert = new Alert();
		alert.setPriority("HIGH");
		alert.setReason("Tire pressure high");
		alert.setTimeStamp(new Date());
		alert.setVehicle(vehicle);
		alert.setReading(reading);
		
		expectedAlertList.add(alert);
		
		Mockito.when(repository.findAll()).thenReturn(expectedVehicleList);
		Mockito.when(repository.findById(vehicle.getVin())).thenReturn(Optional.of(vehicle));
		
		Mockito.when(alertRepo.findByPriorityAndTimeStampGreaterThanOrderByTimeStampDesc("HIGH", new Date())).thenReturn(expectedAlertList);
	}
	
	@After
	public void cleanup() {
		
	}
	
	@TestConfiguration
	static class VehicleServiceTestConfig{
		@Bean
		public VehicleService getService() {
			return new VehicleService();
		}
		
		
		
	}
	@Test
	public void findAll() {
		List<Vehicle> results = vehicleService.findAll();
		Assert.assertEquals(expectedVehicleList, results);
        
    }
	
	@Test
	public void create() {}
	
	@Test
	public void findOne() {
		// TODO Auto-generated method stub
		Vehicle vehicle = vehicleService.findOne(expectedVehicleList.get(0).getVin());
		Assert.assertEquals(expectedVehicleList.get(0), vehicle);
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void findOneNotFound() {
		vehicleService.findOne("kvdshhfuad");
	}
	
	@Test
	public void findVehiclesWithHigh() {
		
	}
	
	@Test
	public void findVehicleReadings() {}
	
	@Test
	public void findVehicleAlerts(){}
	
}

