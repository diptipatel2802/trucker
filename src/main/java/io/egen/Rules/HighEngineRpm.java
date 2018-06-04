package io.egen.Rules;


import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import io.egen.entity.Alert;
import io.egen.entity.Reading;

@Rule(name = "HighEngineRpm", description = "will set priority to High if engineRpm > redlineRpm", priority = 3)
public class HighEngineRpm {
	@Condition
    public boolean condition(@Fact("readings") Reading reading) {
        return reading.getEngineRpm() > reading.getVehicle().getRedlineRpm();
    }
    
    @Action
    public void then(@Fact("alert") Alert alert) {
        alert.setPriority("HIGH");
        alert.setReason("HighEngineRpm");
    }
}
