package ru.geekbrains.dungeon.game;

import lombok.Data;

@Data
public class Armor {
    public enum Type {
        SPEAR, SWORD, MACE, AXE, NONE
    }

    Type type;
    int armorHh;


    public Armor(Type type) {
        this.type = type;
        if (type == Type.SPEAR) {
            this.armorHh = 3;
        }
        if (type == Type.SWORD) {
            this.armorHh = 5;
        }
    }

    public boolean isEffectively(Weapon weapon) {
        return weapon.getType().name() == this.type.name();
    }

    public void changeArmorType(Type to) {
        type = to;
    }
}