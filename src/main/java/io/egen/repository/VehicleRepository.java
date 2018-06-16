package io.egen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.egen.entity.Vehicle;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, String>{
	
}
