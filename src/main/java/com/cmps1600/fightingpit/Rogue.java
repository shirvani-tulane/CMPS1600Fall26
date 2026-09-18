package com.cmps1600.fightingpit;

public class Rogue extends Character implements Attacker {
    private int agility;
    private final int baseDamage;

    public Rogue(String name, int health, int agility, int baseDamage) {
        super(name, health);
        this.agility = agility;
        this.baseDamage = baseDamage;
    }

    public int getAgility() { return agility; }

    public void cast() {
        // Rogue tumbles into the shadows, gains 10% agility.
        agility += .1 * agility;
    }

    public int dealDamage() {
        return baseDamage + agility;
    }

    public String toString() {
        return super.toString() + " [agility " + agility + "]";
    }
}
