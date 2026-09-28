# Polymorphism Activity

Last session you wrote `Character c = new Mage("Aria", 80, 40);` — an abstract type
on the left, a concrete `Mage` on the right — and we told you to pocket the *why*.
Today is the why. Build the steps in order, run after each one, and answer the
question before moving on.

## Where we left off

`Character.java`
```java
public abstract class Character {
    private String name;
    protected int health;

    public Character(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() { return name; }
    public int getHealth()  { return health; }

    public abstract void cast();

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

    public int getFortitude() { return fortitude; }

    public int getHealth() {
        return health + fortitude;
    }

    public void cast() { health += 20; }

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

    public void cast() { mana += 20; }

    public String toString() {
        return super.toString() + " [mana " + mana + "]";
    }
}
```

## 1. A function for any character
Here's a method meant to run on any character at all — it only touches things every
`Character` has:
```java
static void report(Character c) {
    System.out.println(c.getName() + ", hp " + c.getHealth());
}
```
In `main`, try to call it by handing it a brand-new `Character`.
> `new Character(...)` is refused — the class is abstract, so a bare `Character`
> can't exist. What *can* you pass to `report` to call it at all?

## 2. Prove it's really a warrior
Handing a `Warrior` (or a `Mage`) to a parameter typed `Character` needed no cast at
all — widening a value to a parent type like that is called **upcasting**. Now have
`report` also print the fortitude by adding `c.getFortitude();`.
> The warrior you passed in plainly has a fortitude, yet `c.getFortitude()` won't
> compile. What type is the compiler checking `c` against to decide which calls are
> legal — and is that the same as the object you actually handed in?

## 3. Cast it back to a warrior
Reach the fortitude by telling the compiler the real type: change the line to
`((Warrior) c).getFortitude()`. Narrowing a value back down to a specific type like
this is called **downcasting**, and unlike upcasting it's not automatic. Call
`report` on a warrior — fine.

## 4. Try the same on a mage
Now call `report` on a mage.
> Does it compile? Does it run? Why wasn't the compiler able to catch this? (The
> error it throws names both types.)

## 5. Asking "what kind are you?"
Say `report` should print something different per type — fortitude for a warrior,
mana for a mage. The obvious way is to check the type first, then downcast:
```java
static void report(Character c) {
    if (c instanceof Warrior) {
        System.out.println(c.getName() + " fortitude " + ((Warrior) c).getFortitude());
    } else if (c instanceof Mage) {
        System.out.println(c.getName() + " mana " + ((Mage) c).getMana());
    }
}
```
> If a rogue joins the party, what do you have to add to `report` to handle it? And
> if five more character types join after that, how much of `report` are you writing
> and maintaining by hand?

Let each character answer for itself instead:
- add an abstract `String describe()` to `Character`, so every subclass is forced to
  provide one;
- give `Warrior` and `Mage` each their own `describe()`;
- replace the whole `instanceof` chain in `report` with one line:
  `System.out.println(c.describe());`.

Now a type that forgets `describe()` won't compile at all — no silent gap to hunt
for later.

## 6. Two labels, one object
Point two `Character` variables at the same warrior, change it through the first,
then read it back through the second:
```java
Character borinOriginal = new Warrior("Borin", 100, 15);
Character borinCopy = borinOriginal;
borinOriginal.cast();
System.out.println(borinOriginal.getHealth());
System.out.println(borinCopy.getHealth());
```
> You named the second one `borinCopy` and never touched it, yet its health moved
> with the first. How many warrior objects actually exist here — one or two — and
> what did `borinCopy = borinOriginal` copy: the warrior itself, or just a way to
> reach it?

---

The two things you've been prying apart, side by side:

| | The reference (`c`, `borinCopy`, an array slot) | The object (what `new` built) |
|---|---|---|
| What it is | a typed name pointing at an object | the actual instance in memory |
| Lives on the | **stack** | **heap** |
| Its type is fixed | when you declare it, at **compile time** | when `new` runs, at **run time** |
| Governs | which methods you're *allowed* to call | which overridden version actually *runs* |
| `=` copies | the pointer — a second name for the same object | nothing; only `new` makes a new object |

---

## 7. The whole party at once
How do you gather a warrior and a mage into one party list and loop over it just
once, printing each character and its health as you go?
> Every element sits in a slot typed `Character`, yet the warrior and the mage print
> differently and report health differently from that single loop. Who chose which
> version ran on each pass — the compiler when it read the loop once, or something
> deciding per element as it runs?

## On your own
- Put `(Mage) party.get(0)` in `main` where slot `0` is a warrior. Before running,
  predict: does it fail while compiling or while running, and which two type names
  show up in the message?
- Loop over the party calling `cast()` on every member, then print each one's
  `getHealth()`. Predict which characters changed, and by how much, before you run —
  and notice you never once asked what type each was.
- Java has a shorter `instanceof` that casts in the same breath:
  `if (c instanceof Warrior w) { ... w.getFortitude() ... }` (note the `w`). Rewrite
  one check that way. Does it remove the need to edit `report` for every new type —
  the problem from step 5 — or just shorten each branch?
