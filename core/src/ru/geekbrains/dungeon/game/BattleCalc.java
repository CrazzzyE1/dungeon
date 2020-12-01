package ru.geekbrains.dungeon.game;

import com.badlogic.gdx.math.MathUtils;
import ru.geekbrains.dungeon.game.units.Unit;

public class BattleCalc {
    public static int attack(Unit attacker, Unit target) {
        int out = 0;
        if (attacker.getArmor().type != Armor.Type.NONE && target.getArmor().isEffectively(attacker.getWeapon())) {
            target.getArmor().armorHh -= attacker.getWeapon().damage;
            if (target.getArmor().armorHh < 1) {
                target.getArmor().armorHh = 0;
                target.getArmor().changeArmorType(Armor.Type.NONE);
            }
        } else {
            out = attacker.getWeapon().getDamage();
            out -= target.getStats().getDefence();
            if (out < 0) {
                out = 0;
            }
        }

        return out;
    }

    public static int checkCounterAttack(Unit attacker, Unit target) {
        if (MathUtils.random() < 0.5f) {
            int amount = attack(target, attacker);
            return amount;
        }
        return 0;
    }
}
