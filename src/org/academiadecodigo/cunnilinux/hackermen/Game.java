package org.academiadecodigo.cunnilinux.hackermen;

import org.academiadecodigo.cunnilinux.hackermen.gameObjects.*;
import org.academiadecodigo.cunnilinux.hackermen.gameObjects.factory.EnemyFactory;
import org.academiadecodigo.cunnilinux.hackermen.map.Canvas;
import org.academiadecodigo.cunnilinux.hackermen.map.Direction;
import org.academiadecodigo.cunnilinux.hackermen.utils.GameOverMenu;
import org.academiadecodigo.cunnilinux.hackermen.utils.MainMenu;
import org.academiadecodigo.cunnilinux.hackermen.utils.Music;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {

    private int gameLevel;
    private final int delay;
    private Picture background;

    private Hero hero;
    private Enemy[] enemies;
    private Enemy boss;
    private final int spawnedEnemies = 2;
    private int enemyDeadCounter;

    private Health health;

    private CollisionDetector collisionDetector;

    private boolean gameOver;
    private boolean win;

    private Music musicGame;


    public Game(int delay) {

        this.delay = delay;
        gameOver = false;
        win = false;

        gameLevel = 1;

    }

    public void init() {

        setupMenu();

    }

    public void start() throws InterruptedException {

        setupLevel();

        while (!gameOver) {

            try {

                Thread.sleep(delay);

            } catch (InterruptedException exception) {

                throw new RuntimeException(exception);

            }

            if (collisionDetector.checkHeroEnemies()) {

                enemyDeadCounter--;
                health.setCounter(health.getHeroHealth() - 1);

            }

            if (collisionDetector.checkEnemies()) {

                enemyDeadCounter--;

            }

            if (health.getHeroHealth() == 0) {

                gameOver = true;
                win = false;

            }

            // Next level
            if(enemyDeadCounter == 0) {

                win = true;
                break;

            }

            moveAll();

        }

        if (gameOver) {

            gameOver();

        }

        gameLevel++;
        start();

    }

    public void moveAll() {

        try {

            hero.getBullet().move();

        } catch (NullPointerException ignored) {
        }

        for (Enemy enemy : enemies) {

            enemy.move();

        }

    }

    public void setupLevel() {

        if (gameLevel < 1 || gameLevel > 2) {

            gameOver();

        } else {

            setupStage(gameLevel);
            setupCollisionDetector(gameLevel);
            renderStage(gameLevel);

        }
    }

    public void setupStage(int gameLevel) {

        switch (gameLevel) {

            case 1:
                background = new Picture(Canvas.PADDING, Canvas.PADDING, AssetPaths.BACKGROUND_LEVEL1);
                musicGame = new Music(AssetPaths.DURING_GAME_MUSIC);
                musicGame.startMusic(-1);
                health = new Health();
                hero = new Hero(Canvas.CANVAS_WIDTH / 2);

                enemies = new Enemy[spawnedEnemies];
                for (int i = 0; i < spawnedEnemies; i++) {

                    enemies[i] = EnemyFactory.spawnEnemy(i);

                }
                enemyDeadCounter = spawnedEnemies;
                break;
            case 2:
                background = new Picture(Canvas.PADDING, Canvas.PADDING, AssetPaths.BACKGROUND_LEVEL2);
                hero = new Hero(0);
                boss = new Enemy(Direction.LEFT);
                enemyDeadCounter = 1;
                break;

        }

        musicGame = new Music(AssetPaths.DURING_GAME_MUSIC);
        musicGame.startMusic(-1);

    }

    public void setupCollisionDetector(int gameLevel) {

        switch (gameLevel) {
            case 1:
                collisionDetector = new CollisionDetector(hero, enemies);
                break;
            case 2:
                collisionDetector = new CollisionDetector(hero, boss);

        }

        hero.setCollisionDetector(collisionDetector);

    }

    public void renderStage(int gameLevel) {

        background.draw();
        hero.show();

        switch (gameLevel) {
            case 1:
                health.show();
                for (int i = 0; i < spawnedEnemies; i++) {

                    enemies[i].show();

                }
                break;
            case 2:
                boss.show();
        }

    }

    public void gameOver() {

        hero.setDead(true);
        musicGame.stop();
        setBackground();

    }

    private void setupMenu() {

        MainMenu menu = new MainMenu();
        menu.menuLoop();

    }

    private void setBackground() {

        GameOverMenu menu = new GameOverMenu();
        menu.menuLoop();

    }

}
