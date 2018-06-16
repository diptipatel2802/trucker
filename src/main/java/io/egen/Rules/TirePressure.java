package io.egen.Rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import io.egen.entity.Alert;
import io.egen.entity.Reading;

@Rule(name = "TirePressure", description = "will set priority to low if tire pressure < 32 || tire Prssure>36", priority = 1)
public class TirePressure {
	@Condition
    public boolean condition(@Fact("readings") Reading reading) {
		return (reading.getFrontLeft()<32||reading.getFrontLeft()>36 || 
				reading.getFrontRight()<32||reading.getFrontRight()>36 ||
				reading.getRearLeft()<32||reading.getRearLeft()>36 ||
				reading.getRearRight()<32||reading.getRearRight()>36);
		
    }
    
    @Action
    public void then(@Fact("alert") Alert alert) {
        alert.setPriority("LOW");
        alert.setReason("Tire pressure is less than 32 or greater than 36 ");
    }
}