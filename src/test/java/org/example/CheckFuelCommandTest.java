package org.example;

import com.sun.jdi.InvalidTypeException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CheckFuelCommandTest {

    @Test
    public void testSufficientFuel() {
        IFuel fuel = mock(IFuel.class);
        when(fuel.fuelVolume()).thenReturn(100.0);
        when(fuel.fuelExpense()).thenReturn(10.0);

        CheckFuelCommand command = new CheckFuelCommand(fuel);

        command.execute();
    }

    @Test
    public void testInsufficientFuel() {
        IFuel fuel = mock(IFuel.class);
        when(fuel.fuelVolume()).thenReturn(5.0);
        when(fuel.fuelExpense()).thenReturn(10.0);

        CheckFuelCommand command = new CheckFuelCommand(fuel);

        assertThrows(CommandException.class, () -> {
            command.execute();
        });
    }

    @Test
    public void testInvalidFuelType() {
        IFuel fuel = mock(IFuel.class);
        when(fuel.fuelVolume()).thenReturn("invalid");
        when(fuel.fuelExpense()).thenReturn(10.0);

        CheckFuelCommand command = new CheckFuelCommand(fuel);

        assertThrows(InvalidTypeException.class, () -> {
            command.execute();
        });
    }
}

