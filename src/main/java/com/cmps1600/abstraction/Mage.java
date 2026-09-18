package com.cmps1600.abstraction;

public class Mage extends Character implements Attacker {
    private int mana;
    private int baseDamage;
    public Mage(String name, int health, int mana, int baseDamage) {
        super(name, health);
        this.mana = mana;
        this.baseDamage = baseDamage;
    }

    @Override
    public void cast(){
        mana += 10;
    }

    @Override
    public int dealDamage(){
        return baseDamage + mana/2;
    }

    public int getMana() { return mana; }

    public String toString() {
        return super.toString() + " [mana " + mana + "]";
    }
}
