package com.cmps1600.encapsulation;

public class Character {
    private String name;
    private int health;

    public Character(String name, int health){
        this.name=name;
        this.health=health;
    }

    public Character(String name){
        //this.name = name;
        //this.health = 100;
        this(name, 100);
    }
    public String getName(){
        return name;
    }

    public int getHealth(){
        return health;
    }
}
