package io.egen.Rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import io.egen.entity.Alert;
import io.egen.entity.Reading;

@Rule(name = "EngineCheck", description = "will set priority to low if engineCoolantLow = true || checkEngineLightOn = true", priority = 1)
public class EngineCheck {
	@Condition
    public boolean condition(@Fact("readings") Reading reading) {
		return (reading.isEngineCoolantLow() || reading.isCheckEngineLightOn());
    }
    
    @Action
    public void then(@Fact("alert") Alert alert) {
        alert.setPriority("LOW");
        alert.setReason("Enginee coolant is low or Engine light is on");
    }
}