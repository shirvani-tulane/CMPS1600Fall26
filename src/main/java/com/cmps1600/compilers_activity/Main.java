package com.cmps1600.compilers_activity;

public class Main {

    public static void main(String[] args) {
        Character hero = new Character();
        hero.setName("Aria");
        hero.setHealth(100);
        System.out.println(hero.getHealth());

        int level = hero.getHealth();

        String status = "Level: " + level;
        System.out.println(status);

        int bonus = 5;
        System.out.println(bonus);
    }
}
