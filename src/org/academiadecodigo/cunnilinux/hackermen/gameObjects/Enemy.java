package org.academiadecodigo.cunnilinux.hackermen.gameObjects;

import org.academiadecodigo.cunnilinux.hackermen.AssetPaths;
import org.academiadecodigo.cunnilinux.hackermen.map.Canvas;
import org.academiadecodigo.cunnilinux.hackermen.map.Direction;
import org.academiadecodigo.cunnilinux.hackermen.utils.RandomGenerator;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Enemy {

    private final Picture enemy;
    private final Direction direction;
    private final double speed;

    public Enemy() {

        direction = Direction.randomDirectionType();
        enemy = new Picture((direction == Direction.RIGHT) ? 0 : Canvas.CANVAS_WIDTH - 230,
                Canvas.FLOOR_LEVEL, AssetPaths.MONSTER_TIER1);
        speed = RandomGenerator.getRandom(0.5, 2.0);

    }

    public void move() {

        switch (direction) {
            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
            default:
                break;
        }

    }

    private void moveRight() {

        enemy.translate(Canvas.CELL_SIZE * speed, 0.0);

    }

    private void moveLeft() {

        enemy.translate(-Canvas.CELL_SIZE * speed, 0.0);

    }

    public int getX() {

        return enemy.getX();

    }

    public int getRightX() {

        return enemy.getX() + enemy.getWidth();

    }

    public void show() {

        enemy.draw();

    }

    public void hide() {

        enemy.delete();

    }

}
