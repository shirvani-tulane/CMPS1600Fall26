package com.cmps1600.polymorphism;

public class Mage extends Character {
    private int mana;

    public Mage(String name, int health, int mana) {
        super(name, health);
        this.mana = mana;
    }

    public int getMana() { return mana; }

    public void cast() { mana += 20; }

    public String toString() {
        return super.toString() + " [mana " + mana + "]";
    }
}
