package io.egen.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.egen.entity.Reading;
import io.egen.entity.Vehicle;

@Repository
public interface ReadingsRepository extends CrudRepository<Reading, String> {
	//List<Reading> findByVin(String vin);
	List<Reading> findByVehicleAndTimestampGreaterThanOrderByTimestampDesc(Vehicle vehicle, Date timestamp);
	

}
