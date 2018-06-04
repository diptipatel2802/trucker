package io.egen.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
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
	
	List<Vehicle> expected;
	
	@Before
	public void setup() {
		expected = new ArrayList<Vehicle>();
		
		Vehicle vehicle = new Vehicle();
		vehicle.setMake("HONDA"); 
		vehicle.setModel("ACCORD"); 
		vehicle.setYear(2015);  
		vehicle.setRedlineRpm(5000); 
		vehicle.setMaxFuelVolume(15); 
		vehicle.setLastServiceDate(new Date().toString()); 
		
		expected.add(vehicle);
		
		Mockito.when(repository.findAll()).thenReturn(expected);
		Mockito.when(repository.findById(vehicle.getVin())).thenReturn(Optional.of(vehicle));
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
		Assert.assertEquals(expected, results);
        
    }
	
	@Test
	public void create() {}
	
	@Test
	public void findOne() {
		// TODO Auto-generated method stub
		Vehicle vehicle = vehicleService.findOne(expected.get(0).getVin()).get();
		Assert.assertEquals(expected.get(0), vehicle);
	}
	@Test
	public void findVehiclesWithHigh() {}
	@Test
	public void findVehicleReadings() {}
	
	@Test
	public void findVehicleAlerts(){}
	
}

