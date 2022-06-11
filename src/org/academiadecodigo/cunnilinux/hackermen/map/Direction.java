package org.academiadecodigo.cunnilinux.hackermen.map;

import org.academiadecodigo.cunnilinux.hackermen.utils.RandomGenerator;

public enum Direction {

    LEFT,
    RIGHT;

    public static Direction randomDirectionType() {

        Direction[] directions = Direction.values();
        return directions[RandomGenerator.getRandom(directions.length - 1)];

    }

}
