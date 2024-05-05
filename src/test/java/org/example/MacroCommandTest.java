package org.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@Slf4j
class MacroCommandTest {

    @Test
    @DisplayName("Реализовать команду движения по прямой с расходом топлива, используя команды с предыдущих шагов")
    public void testExecuteMove() {

        Vector newPosition = new Vector(3, 4);

        IMove move = mock(IMove.class);
        when(move.getPosition()).thenReturn(new Vector(1, 2));
        when(move.getVelocity()).thenReturn(new Vector(2, 2));
        MoveCommand moveCommand = new MoveCommand(move);


        IFuel fuel = mock(IFuel.class);
        when(fuel.fuelVolume()).thenReturn(100.0);
        when(fuel.fuelExpense()).thenReturn(10.0);

        CheckFuelCommand checkFuelCommand = new CheckFuelCommand(fuel);

        BurnFuelCommand burnFuelCommand = new BurnFuelCommand(fuel);

        ArrayList<ICommand> macroMoveComm = new ArrayList<>();
        macroMoveComm.add(moveCommand);
        macroMoveComm.add(checkFuelCommand);
        macroMoveComm.add(burnFuelCommand);

        MacroCommand macroCommand = new MacroCommand(macroMoveComm);
        macroCommand.execute();

        verify(move, times(1)).setPosition(newPosition);
        verify(fuel, times(1)).saveFuelVolume(90.0);
    }


    @Test
    @DisplayName("вся последовательность команд приостанавливает свое выполнение, а макрокоманда выбрасывает CommandException")
    public void testMacroCommandException() {
        IMove move = mock(IMove.class);
        when(move.getPosition()).thenReturn(new Vector(1, 2));
        when(move.getVelocity()).thenReturn(new Vector(2, 2));
        MoveCommand moveCommand = new MoveCommand(move);

        IFuel fuel = mock(IFuel.class);
        when(fuel.fuelVolume()).thenReturn(1.0);
        when(fuel.fuelExpense()).thenReturn(10.0);

        CheckFuelCommand checkFuelCommand = new CheckFuelCommand(fuel);
        BurnFuelCommand burnFuelCommand = new BurnFuelCommand(fuel);

        ArrayList<ICommand> macroMoveComm = new ArrayList<>();
        macroMoveComm.add(moveCommand);
        macroMoveComm.add(checkFuelCommand);
        macroMoveComm.add(burnFuelCommand);

        MacroCommand macroCommand = new MacroCommand(macroMoveComm);

        assertThrows(CommandException.class, () -> {
            macroCommand.execute();
        });

        assertEquals(1.0, (double) fuel.fuelVolume(), 0.00001);
        assertEquals(new Vector(1,2), move.getPosition());

    }

    @Test
    @DisplayName("Реализовать команду поворота, которая еще и меняет вектор мгновенной скорости, если есть")
    public void testExecuteRotate() {

        Vector position = new Vector(0, 0);
        Vector velocity = new Vector(0, 0);
        IMove move = move(position, velocity);

        int direction = 0;
        int angularVelocity = 90;
        IRotate rotate = rotate(direction, angularVelocity);

        RotateCommand rotateCommand = new RotateCommand(rotate);
        ChangeVelocityCommand changeVelocityCommand = new ChangeVelocityCommand(move, rotate);

        ArrayList<ICommand> macroRotate = new ArrayList<>();

        macroRotate.add(rotateCommand);
        macroRotate.add(changeVelocityCommand);

        MacroCommand macroCommand = new MacroCommand(macroRotate);
        macroCommand.execute();

        //поворачиваем на 90 и проверяем Direction и Position

        assertEquals(90.0, rotate.getDirection(), 0.00001);

        assertEquals(0.0, move.getPosition().getX(), 0.00001);
        assertEquals(0.0, move.getPosition().getY(), 0.00001);

        macroCommand.execute();


    }

    private IMove move(Vector pos, Vector vel) {
        return new IMove() {

            private Vector position = pos;
            private Vector velocity = vel;

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

    private IRotate rotate(int dir, int angular) {
        return new IRotate() {
            private int direction = dir;

            @Override
            public int getDirection() {
                return direction;
            }

            @Override
            public int getAngularVelocity() {
                return angular;
            }

            @Override
            public void setDirection(int newDirection) {
                this.direction = newDirection;
            }
        };
    }


}