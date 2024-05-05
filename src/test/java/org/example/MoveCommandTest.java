package org.example;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
public class MoveCommandTest {


    @Test
    public void testExecute() {

        IMove move = mock(IMove.class);
        when(move.getPosition()).thenReturn(new Vector(1, 2));
        when(move.getVelocity()).thenReturn(new Vector(2, 2));

        Vector newPosition = new Vector(3, 4);

        MoveCommand moveCommand = new MoveCommand(move);

        moveCommand.execute();

        verify(move, times(1)).setPosition(newPosition);

    }

    @Test
    public void testThrowErrorWhenGetPosition() {

        IMove move = mock(IMove.class);
        when(move.getPosition()).thenReturn(null);
        when(move.getVelocity()).thenReturn(new Vector(2, 2));

        MoveCommand moveCommand = new MoveCommand(move);

        assertThrows(NullPointerException.class, () -> {
            moveCommand.execute();
        });
    }

    @Test
    public void testThrowErrorWhenGetVelocity() {
        IMove move = mock(IMove.class);
        when(move.getPosition()).thenReturn(new Vector(1, 2));
        when(move.getVelocity()).thenReturn(null);

        MoveCommand moveCommand = new MoveCommand(move);

        assertThrows(NullPointerException.class, () -> {
            moveCommand.execute();
        });
    }

    @Test
    public void testThrowErrorWhenSetPosition() {
        IMove move = mock(IMove.class);
        when(move.getPosition()).thenReturn(new Vector(1, 2));
        when(move.getVelocity()).thenReturn(new Vector(2, 2));

        doThrow(new NullPointerException()).when(move).setPosition(any(Vector.class));

        MoveCommand moveCommand = new MoveCommand(move);

        assertThrows(NullPointerException.class, () -> {
            moveCommand.execute();
        });
    }
}