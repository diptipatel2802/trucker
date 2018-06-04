package io.egen.repository;

import java.util.Optional;

import io.egen.entity.Vehicle;

public class RepositoryImpl implements VehicleRepository{

	public <S extends Vehicle> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends Vehicle> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Vehicle> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterable<Vehicle> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<Vehicle> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Vehicle entity) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAll(Iterable<? extends Vehicle> entities) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

}
