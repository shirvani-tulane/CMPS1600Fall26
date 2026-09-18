package com.cmps1600.fightingpit;

public class Warrior extends Character implements Attacker {
    private int fortitude;
    private final int baseDamage;

    public Warrior(String name, int health, int fortitude, int baseDamage) {
        super(name, health);
        this.fortitude = fortitude;
        this.baseDamage = baseDamage;
    }

    public int getHealth() {
        return health + fortitude;
    }

    public void cast() {
        // Warrior steels itself, gaining health.
        health += fortitude;
    }

    public int dealDamage() {
        return baseDamage;
    }

    public String toString() {
        return super.toString() + " [fortitude " + fortitude + "]";
    }
}
