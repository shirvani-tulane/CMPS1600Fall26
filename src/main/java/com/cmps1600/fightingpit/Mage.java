package com.cmps1600.fightingpit;

public class Mage extends Character implements Attacker {
    private int mana, maxMana;
    private final int baseDamage;
    public Mage(String name, int health, int mana, int baseDamage) {
        super(name, health);
        this.mana = mana;
        this.maxMana = mana;
        this.baseDamage = baseDamage;
    }

    public int getMana() { return mana; }

    public void cast() {
        // Mage draws on the weave, gaining mana.
        mana = maxMana;
    }

    public int dealDamage() {
        // Mage spends mana, dealing baseDamage plus half its mana.
        int spent = mana / 2;
        int damage = baseDamage + spent;
        mana = mana - spent;
        return damage;
    }

    public String toString() {
        return super.toString() + " [mana " + mana + "]";
    }
}
