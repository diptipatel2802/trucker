package io.egen.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.entity.Alert;
import io.egen.entity.Reading;
import io.egen.entity.Vehicle;
import io.egen.service.VehicleService;

@RestController
@RequestMapping(value="/vehicles")
public class VehicleController {

   @Autowired	
   VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> findAll() {
        List<Vehicle> list = vehicleService.findAll();
        return list;
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "/highpriority")
    public List<Vehicle> findHighPriority() {
        List<Vehicle> list = vehicleService.findVehiclesWithHigh();
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{vin}",
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Vehicle findOne(@PathVariable("vin") String vin) {
        return vehicleService.findOne(vin);
    }


    @CrossOrigin(origins = "http://mocker.egen.io")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ArrayList<Vehicle> create(@RequestBody ArrayList<Vehicle> vehicleList) {
    	ArrayList<Vehicle> retVehicleList = new ArrayList<Vehicle>();
    	for (Vehicle vehicle : vehicleList) {
    		retVehicleList.add(vehicleService.create(vehicle));
		}
        return retVehicleList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/locations",
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getVehicleLocations(@PathVariable("id") String vin) {
        List<Reading> readings = vehicleService.findVehicleReadings(vin);
        
        List<Map<String, Float>> locations = new ArrayList<Map<String, Float>>();
        for (Reading reading : readings) {
        	Map<String, Float> location = new HashMap<String, Float>();
        	location.put("latitude", reading.getLatitude());
        	location.put("longitude", reading.getLongitude());
        	locations.add(location);
        }
        return locations;
    }
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/alert",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Object getVehicleAlert(@PathVariable("id") String vin) {
		List<Alert> alertList = vehicleService.findVehicleAlerts(vin);
		
		List<Map<String, String>> alertsForVehicleList = new ArrayList<Map<String, String>>();
		for (Alert alert : alertList) {
			Map<String, String> alertsMap = new HashMap<String, String>();
			alertsMap.put("Priority", alert.getPriority());
			alertsMap.put("Reason", alert.getReason());
			alertsForVehicleList.add(alertsMap);
		}
		return alertsForVehicleList;
		}
/*
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable("id") String empId) {
        vehicleService.delete(empId);
    }
*/
}
