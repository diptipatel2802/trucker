package io.egen.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.entity.Reading;
import io.egen.entity.Vehicle;
import io.egen.exception.ResourceNotFoundException;
import io.egen.repository.VehicleRepository;
import io.egen.service.ReadingsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/readings")
@CrossOrigin(origins = "http://mocker.egen.io")
@Api(value="trucker")
public class ReadingsController {
	
	@Autowired
	ReadingsService readingsService;
	
	@Autowired
	VehicleRepository vehicleRepo;
	
	@CrossOrigin(origins = "http://mocker.egen.io")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
	                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value="post readings")
	public Reading create(@RequestBody Reading readings) {
		
		//System.out.println("********************"+readings.getFrontLeft());
		Optional<Vehicle> vehicle = vehicleRepo.findById(readings.getVin());
		if(!vehicle.isPresent()) {
			throw new ResourceNotFoundException();
		}
		else {
			readings.setVehicle(vehicle.get());
			return readingsService.create(readings);
		}
		
	}
}
