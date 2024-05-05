package org.example;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
public class RotateCommandTest {

    @Test
    public void testExecute() {
        IRotate rotate = mock(IRotate.class);
        when(rotate.getDirection()).thenReturn(0);
        when(rotate.getAngularVelocity()).thenReturn(45);

        RotateCommand command = new RotateCommand(rotate);

        command.execute();
        verify(rotate).setDirection(45);

    }
}

