package org.example;

import com.sun.jdi.InvalidTypeException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class CheckFuelCommand implements ICommand{

    private final IFuel fuel;

    @SneakyThrows
    @Override
    public void execute() {
        Object volume = fuel.fuelVolume();
        Object expense = fuel.fuelExpense();

        if (volume instanceof Number && expense instanceof Number) {
            double result = ((Number) volume).doubleValue() - ((Number) expense).doubleValue();
            if (result < 0) {
                throw new CommandException("Fuel has run out");
            }
        } else {
            throw new InvalidTypeException("Invalid type for fuelVolume() or fuelExpense()");
        }
    }
}
