package ru.geekbrains.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private ProjectileController projectileController;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 directionProjectile;
    private TextureRegion texture;
    private int speed;
    private int speedProjectile;


    public Hero(TextureAtlas atlas, ProjectileController projectileController) {
        this.position = new Vector2(100, 100);
        this.velocity = new Vector2(0, 0);
        this.directionProjectile = new Vector2(0, 0);
        this.texture = atlas.findRegion("tank");
        this.projectileController = projectileController;
        this.speed = 100;
        this.speedProjectile = 300;
    }

    public void move(String direction, float dt) {
        switch (direction) {
            case ("RIGHT"):
                velocity.x = speed;
                velocity.y = 0;
                directionProjectile.x = speedProjectile;
                directionProjectile.y = 0;
                break;
            case ("LEFT"):
                velocity.x = -speed;
                velocity.y = 0;
                directionProjectile.x = -speedProjectile;
                directionProjectile.y = 0;
                break;
            case ("UP"):
                velocity.x = 0;
                velocity.y = speed;
                directionProjectile.x = 0;
                directionProjectile.y = speedProjectile;
                break;
            case ("DOWN"):
                velocity.x = 0;
                velocity.y = -speed;
                directionProjectile.x = 0;
                directionProjectile.y = -speedProjectile;
                break;
        }
        position.mulAdd(velocity, dt);
        checkPosition();

    }

    public void checkPosition() {
        int maxX = Math.min(1260, GameMap.CELLS_X * GameMap.CELLS_WEIGHT - 20);
        int maxY = Math.min(700, GameMap.CELLS_Y * GameMap.CELLS_HEIGHT - 20);
        ;

        if (position.x < 20) position.x = 20;
        if (position.y < 20) position.y = 20;
        if (position.x > maxX) position.x = maxX;
        if (position.y > maxY) position.y = maxY;
    }

    public void update(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            projectileController.activate(position.x, position.y, directionProjectile.x, directionProjectile.y);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            projectileController.activateDoubleShot();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            move("UP", dt);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            move("DOWN", dt);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            move("LEFT", dt);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            move("RIGHT", dt);
        }

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 20, position.y - 20);
    }
}
