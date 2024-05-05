package org.example;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RotateCommand implements ICommand{

    private IRotate r;
    @Override
    public void execute() {
        int newDirection = r.getDirection() + r.getAngularVelocity();
        r.setDirection(newDirection);

    }
}
