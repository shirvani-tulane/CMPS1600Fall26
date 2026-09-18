package com.cmps1600.fightingpit;

public abstract class Character {
    private String name;
    protected int health;

    public Character(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() { return name; }
    public int getHealth()  { return health; }

    public void takeDamage(int amount) {
        health = health - amount;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public abstract void cast();

    public String toString() {
        return name + " (hp " + health + ")";
    }
}
