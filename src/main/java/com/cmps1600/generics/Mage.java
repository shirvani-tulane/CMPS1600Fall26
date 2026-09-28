package com.cmps1600.generics;

public class Mage extends Character {
    private int mana;
    public Mage(String name, int health, int mana) { super(name, health); this.mana = mana; }
    public void cast() { mana += 20; }
    public int getMana() { return mana; }
}
