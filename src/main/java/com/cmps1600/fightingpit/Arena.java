package com.cmps1600.fightingpit;
import java.util.Random;

public class Arena {

    // Builds one of three character types from a roll. Each type gets its own
    // stat spread derived from the RNG so the fighters differ every seed.
    private static Character spawn(Random rng, String name) {
        int type = rng.nextInt(3);          // 0 = Warrior, 1 = Mage, 2 = Rogue
        int health = 60 + rng.nextInt(41);  // 60..100
        int stat   = 10 + rng.nextInt(21);  // 10..30
        int base   = 5  + rng.nextInt(11);  // 5..15

        if (type == 0) {
            return new Warrior(name, health, stat, base);
        } else if (type == 1) {
            return new Mage(name, health, stat, base);
        } else {
            return new Rogue(name, health, stat, base);
        }
    }

    public static void main(String[] args) {
        Random rng = new Random();

        Character first  = spawn(rng, "Fighter A");
        Character second = spawn(rng, "Fighter B");

        // Whoever the RNG favours opens the fight.
        Character current;
        Character other;
        if (rng.nextBoolean()) {
            current = first;
            other   = second;
        } else {
            current = second;
            other   = first;
        }

        int turn = 1;
        while (first.isAlive() && second.isAlive()) {
            // Every third turn the active fighter casts instead of striking.
            if (turn % 3 == 0) {
                current.cast();
            } else {
                Attacker weapon = (Attacker) current;
                int dmg = weapon.dealDamage();
                other.takeDamage(dmg);
            }

            // swap active fighter
            Character temp = current;
            current = other;
            other   = temp;
            turn++;
        }

        Character winner = first.isAlive() ? first : second;
        System.out.println("The fight is over after " + (turn - 1) + " turns.");
        System.out.println("Winner: " + winner.getName());
    }
}
