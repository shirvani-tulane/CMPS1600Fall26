package com.cmps1600.compilers_activity;

public class Character {

    private String name;
    private int health;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        String label = "Name: " + name;
        return label;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
