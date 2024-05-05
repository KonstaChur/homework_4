package org.example;


public class Main {
    public static void main(String[] args) {

        IRotate r = new IRotate() {
            private int direction = 0;
            private int angularVelocity = 90;

            @Override
            public int getDirection() {
                return direction;
            }

            @Override
            public int getAngularVelocity() {
                return angularVelocity;
            }

            @Override
            public void setDirection(int newDirection) {
                this.direction = newDirection;
            }
        };

        // Создаем команду вращения
        ICommand rotateCommand = new RotateCommand(r); // Передаем объект вращения в конструктор команды

        // Выполняем команду вращения
        rotateCommand.execute();

        // Выводим новое направление
        System.out.println("Новое направление: " + r.getDirection());







        IFuel fuel = fuelOb();
        ICommand checkFuelCommand = new CheckFuelCommand(fuel);
        checkFuelCommand.execute();

        BurnFuelCommand command = new BurnFuelCommand(fuel);
        command.execute();


        IMove moveObject = moveObject();
        ICommand moveCommand = new MoveCommand(moveObject);
        moveCommand.execute();

        System.out.println("New position: " + moveObject.getPosition());
        System.out.println("fuelChek = "+ checkFuelCommand);
        System.out.println("com = "+fuel.fuelVolume());
    }



    private static IFuel fuelOb() {
        return new IFuel() {
            @Override
            public Object fuelVolume() {
                return 50;
            }

            @Override
            public Object fuelExpense() {
                return 10;
            }

            @Override
            public void saveFuelVolume(Object newVolume) {

            }
        };
    }

    private static IMove moveObject() {
        return new IMove() {
            private Vector position = new Vector(1, 0);

            @Override
            public Vector getPosition() {
                return position;
            }

            @Override
            public Vector getVelocity() {
                return new Vector(2, 3);
            }

            @Override
            public Vector setPosition(Vector newValue) {
                position = newValue;
                return position;
            }

            @Override
            public Vector setVelocity(Vector newValue) {
                return null;
            }
        };
    }
}
