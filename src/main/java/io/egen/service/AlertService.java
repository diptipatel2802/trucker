package io.egen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.entity.Alert;
import io.egen.repository.AlertRepository;

@Service
public class AlertService {
	@Autowired
	AlertRepository alertRepo;
	public Alert create(Alert alert) {
		return alertRepo.save(alert);
	}
	
}
