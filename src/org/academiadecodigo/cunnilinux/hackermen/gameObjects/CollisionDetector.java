package org.academiadecodigo.cunnilinux.hackermen.gameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class CollisionDetector {

    private static Hero hero;
    private static Enemies[] enemies;

    public static boolean detectCollisionHeroEnemy() {

        boolean checkIntersection = intersects(hero.getHero(), enemy.getEnemy());
        enemy.grow();

        return checkIntersection;

    }

    public static boolean detectCollisionBulletEnemy(Bullet bullet) {

        boolean checkIntersection = intersects(bullet.getBullet(), enemy.getEnemy());
        bullet.grow();
        enemy.grow();

        return checkIntersection;

    }

    public static boolean intersects(Picture rect1, Picture rect2) {

        int rect1Width = rect1.getWidth();
        int rect1Height = rect1.getHeight();
        int rect2Width = rect2.getWidth();
        int rect2Height = rect2.getHeight();

        if (rect2Width <= 0 || rect2Height <= 0 || rect1Width <= 0 || rect1Height <= 0) {
            return false;
        }

        int rect1X = rect1.getX();
        int rect1Y = rect1.getY();
        int rect2X = rect2.getX();
        int rect2Y = rect2.getY();
        rect2Width += rect2X;
        rect2Height += rect2Y;
        rect1Width += rect1X;
        rect1Height += rect1Y;

        return ((rect2Width < rect2X || rect2Width > rect1X) &&
                (rect2Height < rect2Y || rect2Height > rect1Y) &&
                (rect1Width < rect1X || rect1Width > rect2X) &&
                (rect1Height < rect1Y || rect1Height > rect2Y));

    }

    public static void setHero(Hero hero) {

        CollisionDetector.hero = hero;

    }

    public static void setEnemy(Enemy enemy) {

        CollisionDetector.enemy = enemy;

    }

}
