package io.egen.Rules;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.egen.entity.Alert;
import io.egen.entity.Reading;
import io.egen.service.AlertService;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

@Aspect
@Component
public class AlertsExecutor {
	
	@Autowired
	private AlertService alertService;
	
	@After(value = "execution(* io.egen.service.ReadingsService.create(..)) and args(readings)")
	public void afterReadingsCreate(JoinPoint joinPoint, Reading readings) {
		Alert alert = new Alert();
		alert.setReading(readings);
		alert.setVehicle(readings.getVehicle());;
		
		RulesEngine rulesEngine = new DefaultRulesEngine();
        Rules rules = new Rules();
        rules.register(new HighEngineRpm());
        rules.register(new FuelVolume());
        rules.register(new TirePressure());
        rules.register(new EngineCheck());
        		
        Facts facts = new Facts();
        facts.put("readings", readings);
        facts.put("alert", alert);
        rulesEngine.fire(rules, facts);
        
        if(alert.getPriority() != null && alert.getPriority().trim() != "") {
        	alert.setTimeStamp(new Date());
        	alertService.create(alert);
        }
	}
}
