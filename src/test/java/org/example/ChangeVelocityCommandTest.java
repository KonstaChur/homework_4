package org.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ChangeVelocityCommandTest {

    private Vector position = new Vector(0, 0);
    private Vector velocity = new Vector(10, 0);

    @Test
    public void testExecute() {

        IMove move = move();
        IRotate rotate = mock(IRotate.class);

        when(rotate.getDirection()).thenReturn(0);

        ChangeVelocityCommand command = new ChangeVelocityCommand(move, rotate);

        //проверка движения по кругу с поворотом на 45гр

        for (int i = 0; i < 8; i++) {
            when(rotate.getDirection()).thenReturn(45);
            command.execute();
        }
        assertEquals(0.0, move.getPosition().getX(), 0.00001);
        assertEquals(0.0, move.getPosition().getY(), 0.00001);

    }

    private IMove move(){
        return new IMove() {

            @Override
            public Vector getPosition() {
                return position;
            }

            @Override
            public Vector getVelocity() {
                return velocity;
            }

            @Override
            public Vector setPosition(Vector newValue) {
                position = newValue;
                return newValue;
            }

            @Override
            public Vector setVelocity(Vector newValue) {
                velocity = newValue;
                return velocity;
            }
        };
    }
}
