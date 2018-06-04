package io.egen.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.egen.entity.Alert;
import io.egen.entity.Vehicle;

@Repository
public interface AlertRepository extends CrudRepository<Alert, String>{
	List<Alert> findByPriorityAndTimeStampGreaterThanOrderByTimeStampDesc(String priority,Date timeStamp);
	List<Alert> findByVehicleOrderByTimeStampDesc(Vehicle vin);
}
