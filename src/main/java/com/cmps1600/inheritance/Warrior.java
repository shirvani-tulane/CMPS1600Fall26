package com.cmps1600.inheritance;

public class Warrior extends Character {
    int fortitude;
    public Warrior(String name, int health){
        super(name, health);
        System.out.println("warrior constructor");
    }

    @Override
    public int getHealth(){
        return super.getHealth() + fortitude;
    }

    @Override
    public String toString(){
        return super.toString() + fortitude;
    }
}
