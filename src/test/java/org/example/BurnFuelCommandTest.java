package org.example;

import com.sun.jdi.InvalidTypeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BurnFuelCommandTest {

    @Test
    void testExecute() {
        IFuel fuel = mock(IFuel.class);
        when(fuel.fuelVolume()).thenReturn(100.0);
        when(fuel.fuelExpense()).thenReturn(10.0);

        BurnFuelCommand command = new BurnFuelCommand(fuel);

        command.execute();

        verify(fuel, times(1)).saveFuelVolume(90.0);
    }

    @Test
    void testExecute_invalidType() {
        IFuel fuel = mock(IFuel.class);
        when(fuel.fuelVolume()).thenReturn("100.0");
        when(fuel.fuelExpense()).thenReturn("10.0");

        BurnFuelCommand command = new BurnFuelCommand(fuel);

        assertThrows(InvalidTypeException.class, () -> {
            command.execute();
        });
    }
}

