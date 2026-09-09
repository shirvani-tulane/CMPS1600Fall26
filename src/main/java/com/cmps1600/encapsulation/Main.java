package com.cmps1600.encapsulation;

public class Main {
    public static void main(String[] args) {
        Character c = new Character("Aria", 300);
       // c.health = -9999;
       System.out.println(c.getName() + c.getHealth());
       Character c2 = new Character("Greg");
       System.out.println(c2.getName() + c2.getHealth());
    }
}
