package com.cmps1600.inheritance;

public class Character {
    private String name;
    private int health;

    public Character() { }

    public Character(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public Character(String name) {
        this(name, 100);
    }

    public String getName()  { return name; }
    public int getHealth()   { return health; }
}