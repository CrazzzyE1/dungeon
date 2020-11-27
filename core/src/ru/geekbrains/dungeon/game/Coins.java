package ru.geekbrains.dungeon.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import ru.geekbrains.dungeon.helpers.Assets;

import java.util.ArrayList;
import java.util.List;

public class Coins extends Unit{
    GameController gc;
    TextureRegion texture;
    int cellX;
    int cellY;
    int quantity;
    boolean active;
    StringBuilder stringHelper;
    float innerTimer;
    List<Coins> allCoins;

    public Coins(GameController gc) {
        super(gc, 5, 2, 5);
        this.texture = Assets.getInstance().getAtlas().findRegion("projectile");
        this.hp = -1;
        this.innerTimer = MathUtils.random(1000.0f);
    }

    public void activate(int cellX, int cellY, int quantity) {
        this.cellX = cellX;
        this.cellY = cellY;
        this.targetX = cellX;
        this.targetY = cellY;
        this.hpMax = 10;
        this.hp = hpMax;
        this.quantity = quantity;
    }

    public void update(float dt) {
        super.update(dt);
    }

    public void render(SpriteBatch batch, BitmapFont font18) {

        float px = cellX * GameMap.CELL_SIZE;
        float py = cellY * GameMap.CELL_SIZE;

        batch.draw(texture, px, py);
        batch.setColor(0.0f, 0.0f, 0.0f, 1f);

        float barX = px, barY = py + MathUtils.sin(innerTimer * 5.0f) * 2;
//        batch.draw(textureHp, barX + 1, barY + 51, 58, 10);
        batch.setColor(0.7f, 0.0f, 0.0f, 1f);
//        batch.draw(texturep, barX + 2, barY + 52, 56, 8);
        batch.setColor(0.0f, 1.0f, 0.0f, 1f);
//        batch.draw(textureHp, barX + 2, barY + 52, (float) hp / hpMax * 56, 8);
        batch.setColor(1.0f, 1.0f, 1.0f, 1f);
        stringHelper.setLength(0);
        stringHelper.append("GOLD: " + quantity + "\n");



        font18.setColor(1.0f, 1.0f, 1.0f, 1f);
        font18.draw(batch, stringHelper, barX, barY + 85, 60, 1, false);

        font18.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);

    }

}
