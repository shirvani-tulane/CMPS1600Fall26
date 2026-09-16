package com.cmps1600.abstraction;

public class Warrior extends Character {
    private int fortitude;

    public Warrior(String name, int health, int fortitude) {
        super(name, health);
        this.fortitude = fortitude;
    }

    public int getHealth() {
        return health + fortitude;
    }

    public String toString() {
        return super.toString() + " [fortitude " + fortitude + "]";
    }
}
