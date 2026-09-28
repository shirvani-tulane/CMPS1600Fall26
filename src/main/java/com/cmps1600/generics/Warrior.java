package com.cmps1600.generics;

public class Warrior extends Character {
    private int fortitude;
    public Warrior(String name, int health, int fortitude) { super(name, health); this.fortitude = fortitude; }
    public int getHealth() { return health + fortitude; }
    public void cast() { health += 10; }
    public void rally() { System.out.println(getName() + " rallies the party!"); }
}
