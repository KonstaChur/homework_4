package org.example;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class ChangeVelocityCommand implements ICommand {

    private final IMove move;
    private final IRotate rotate;

    @Override
    public void execute() {

        double x = move.getPosition().getX()+move.getVelocity().getX()* Math.cos(rotate.getDirection()*Math.PI / 180)-move.getVelocity().getY()*Math.sin(rotate.getDirection()*Math.PI / 180);
        double y = move.getPosition().getY()+move.getVelocity().getX()*Math.sin(rotate.getDirection()*Math.PI / 180)+move.getVelocity().getY()*Math.cos(rotate.getDirection()*Math.PI / 180);

        double vx = x - move.getPosition().getX();
        double vy = y - move.getPosition().getY();
        move.setVelocity(new Vector(vx, vy));
        move.setPosition(new Vector(x, y));


    }

}

