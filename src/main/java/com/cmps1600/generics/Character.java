package com.cmps1600.generics;

public abstract class Character {
    private String name;
    protected int health;
    public Character(String name, int health) { this.name = name; this.health = health; }
    public String getName() { return name; }
    public int getHealth()  { return health; }
    public abstract void cast();
}
