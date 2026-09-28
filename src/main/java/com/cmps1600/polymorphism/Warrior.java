package com.cmps1600.polymorphism;

public class Warrior extends Character {
    private int fortitude;

    public Warrior(String name, int health, int fortitude) {
        super(name, health);
        this.fortitude = fortitude;
    }

    public int getFortitude() { return fortitude; }

    public int getHealth() {
        return health + fortitude;
    }

    public void cast() { health += 20; }

    public String toString() {
        return super.toString() + " [fortitude " + fortitude + "]";
    }
}
