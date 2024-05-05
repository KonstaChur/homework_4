package org.example;

import com.sun.jdi.InvalidTypeException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Slf4j
public class BurnFuelCommand implements ICommand{

    private final IFuel fuel;
    @SneakyThrows
    @Override
    public void execute() {
        Object volume = fuel.fuelVolume();
        Object expense = fuel.fuelExpense();

        if (volume instanceof Number && expense instanceof Number) {
            fuel.saveFuelVolume(((Number) volume).doubleValue() - ((Number) expense).doubleValue());
        } else {
            throw new InvalidTypeException("Invalid type for fuelVolume() or fuelExpense()");
        }
    }
}
