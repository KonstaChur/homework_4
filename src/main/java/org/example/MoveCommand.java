package org.example;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MoveCommand implements ICommand {

    private final IMove m;

    public void execute() {
        m.setPosition(new Vector(m.getPosition().getX() + m.getVelocity().getX(),
                m.getPosition().getY() + m.getVelocity().getY()));
    }
}
