package ru.geekbrains.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ProjectileController {
    private static final int MAX_PROJECTILES = 100;
    private Projectile[] items;
    boolean doubleShotFlag;
    boolean shotFlag;

    float lastDT;
    float lastX;
    float lastY;
    float lastVX;
    float lastVY;


    public Projectile[] getItems() {
        return items;
    }

    public ProjectileController(TextureAtlas atlas) {
        this.items = new Projectile[MAX_PROJECTILES];
        TextureRegion region = atlas.findRegion("projectile");
        for (int i = 0; i < items.length; i++) {
            items[i] = new Projectile(region);
        }
    }

    public void activateDoubleShot() {
        if (doubleShotFlag) {
            doubleShotFlag = false;
        } else {
            doubleShotFlag = true;
        }
        shotFlag = false;
    }

    public void activate(float x, float y, float vx, float vy) {

        for (Projectile p : items) {
            if (!p.isActive()) {
                p.activate(x, y, vx, vy);
                lastX = x;
                lastY = y;
                lastVX = vx;
                lastVY = vy;
                shotFlag = true;
                return;
            }
        }
    }

    public void update(float dt) {
        if (doubleShotFlag && shotFlag) {
            lastDT++;
        }
        if ((lastDT == 5) && doubleShotFlag) {
            activate(lastX, lastY, lastVX, lastVY);
            shotFlag = false;
            lastDT = 0;
        }

        for (Projectile p : items) {
            if (p.isActive()) {
                p.update(dt);
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Projectile p : items) {
            if (p.isActive()) {
                p.render(batch);
            }
        }
    }
}
