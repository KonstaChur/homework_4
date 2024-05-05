package org.example;

public interface IMove {

    Vector getPosition();
    Vector getVelocity();
    Vector setPosition(Vector newValue);

    Vector setVelocity(Vector newValue);
}
