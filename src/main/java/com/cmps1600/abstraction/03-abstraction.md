# Abstraction — Live Coding Activity

We're picking up the `Character` family from the last two sessions. Build the
steps in order. Every step hands you a situation — try it, watch what happens,
and answer the question before moving on.

## Where we left off

`Character.java`
```java
public class Character {
    private String name;
    protected int health;

    public Character(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() { return name; }
    public int getHealth()  { return health; }

    public String toString() {
        return name + " (hp " + health + ")";
    }
}
```

`Warrior.java`
```java
public class Warrior extends Character {
    private int fortitude;

    public Warrior(String name, int health, int fortitude) {
        super(name, health);
        this.fortitude = fortitude;
    }

    public int getHealth() {
        return health + fortitude;
    }

    public String toString() {
        return super.toString() + " [fortitude " + fortitude + "]";
    }
}
```

`Mage.java`
```java
public class Mage extends Character {
    private int mana;

    public Mage(String name, int health, int mana) {
        super(name, health);
        this.mana = mana;
    }

    public int getMana() { return mana; }

    public String toString() {
        return super.toString() + " [mana " + mana + "]";
    }
}
```

`Main.java`
```java
public class Main {
    public static void main(String[] args) {
        Mage m = new Mage("Aria", 80, 40);
        System.out.println(m);
    }
}
```

## 1. Forbid the meaningless character
Every character in the game is really a warrior, a mage, or something else specific
— a plain `Character` with no class is not a thing you'd ever want to spawn. Yet
right now nothing stops `new Character("Nobody", 0)`. You still want `Character` as
the shared parent every type builds on; you just don't want anyone creating a bare
one. Mark the `Character` class `abstract`, then try `new Character("Nobody", 0)`
in `main` and recompile.
> What happened? Did marking the class `abstract` break the subclasses too, or only the attempt to build a `Character` directly?

## 2. A spell every character can cast
Every character can `cast`, but each does something different when it does:
- a warrior steels itself, gaining health,
- a mage draws on the weave, gaining mana,
- a `Character` on its own can't say which.

On `Character`, add a `cast()` method that returns `void` but has no body.
> The compiler won't accept a body-less method as it stands. What one keyword is it insisting you add?

## 3. Compile the whole project
Now build everything.
> Something breaks, and it isn't `Character`. Find it. The parent only *declared* that method and never even used it — so why did this break? Fix it by giving each subclass its own `cast()`: warrior raises its own health, mage raises its own mana.

## 4. Getting a character anyway
As we know, we can only use a class if we instantiate it — and you just made
`Character` impossible to instantiate. So in `main`, try `new Character(...)` again
and confirm it's still refused.

Then here's how to still get a `Character` variable:
```java
Character c = new Mage("Aria", 80, 40);
c.cast();
```
Run it — it works: the variable's declared type is `Character`, but the object
is a `Mage`.
> Don't chase *why* this is allowed — that's **polymorphism**, next session's whole topic. For now just keep it in your back pocket: this is how you get a variable of an abstract type.

## 5. A capability that isn't a character
Write a new abstract class `Attacker` with a single abstract method `dealDamage()`
that returns an `int` and has no body.

Anything that is an `Attacker` is promising it can deal some amount of damage — a
character could, but so could a trapped treasure chest, a spell rune, almost
anything. Nothing implements it yet; just get the class written.

## 6. Make the characters deal damage
Not every character deals damage — only the ones that take on the `Attacker`
capability do — so a damage floor doesn't belong on `Character`. Give each
attacker its *own* `baseDamage`: add a `private final int baseDamage` to `Warrior`
and to `Mage`, set through each constructor.

Now make `Warrior` and `Mage` `Attacker`s, each with its own `dealDamage()`: a
warrior deals its `baseDamage`; a mage spends its mana, dealing `baseDamage` plus
half its mana. Do it by having each character **extend** `Attacker`.
> You can't — what does the compiler say, and what does that tell you about how many classes a class is allowed to extend? Change `Attacker` from a class into something a class can take on freely, and adjust how the characters pick it up.

## 7. What's the difference, really?
Try to give `Attacker` an instance field (`private double x;`) and a constructor.
Then try adding those same two things to `Character`.
> Which type accepted them, and which refused? Based on what you just saw, how would you describe the difference between an interface and an abstract class — and when would you reach for each?

## On your own
- Add a `Rogue` that extends `Character` and implements `Attacker`. Give it an
  `agility` field, and have its `dealDamage()` add that agility on top of
  `baseDamage`. It still has to provide a `cast()` — decide what a rogue's spell
  does to it.
- Add a class with nothing to do with characters (e.g. `TreasureChest`) that
  implements `Attacker` too.
