package io.egen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.entity.Reading;
import io.egen.repository.ReadingsRepository;

@Service
public class ReadingsService {
	@Autowired
	ReadingsRepository readingsRepo;
	public Reading create(Reading reading) {
		reading.setFrontLeft(reading.getTires().getFrontLeft());
		reading.setFrontRight(reading.getTires().getFrontRight());
		reading.setRearLeft(reading.getTires().getRearLeft());
		reading.setRearRight(reading.getTires().getRearRight());
		return readingsRepo.save(reading);
	}
}
