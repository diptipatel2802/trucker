package io.egen.Rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import io.egen.entity.Alert;
import io.egen.entity.Reading;

@Rule(name = "FuelVolume", description = "will set priority to medium if fuel vol is less than 10% of max fuel vol", priority = 2)
public class FuelVolume {
	@Condition
    public boolean condition(@Fact("readings") Reading reading) {
		int maxFuelVol = reading.getVehicle().getMaxFuelVolume();
        return reading.getFuelVolume() < (0.10 * maxFuelVol);
    }
    
    @Action
    public void then(@Fact("alert") Alert alert) {
        alert.setPriority("MEDIUM");
        alert.setReason("Fuel Volume is less than 10% of max fuel volume");
    }
}
