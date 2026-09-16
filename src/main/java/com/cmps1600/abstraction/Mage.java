package com.cmps1600.abstraction;

public class Mage extends Character {
    private int mana;

    public Mage(String name, int health, int mana) {
        super(name, health);
        this.mana = mana;
    }

    public int getMana() { return mana; }

    public String toString() {
        return super.toString() + " [mana " + mana + "]";
    }
}
