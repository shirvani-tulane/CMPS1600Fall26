# Inheritance Activity

We're picking up the `Character` from last time and building a specific kind of
character on top of it. Build the steps in order and run after each one. Every step
hands you a problem — work out the fix before moving on.

## Where we left off

`Character.java`
```java
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
```

## 1. Make a `Warrior` that is a `Character`
Create a new `Warrior` class that inherits from `Character`. Leave its body empty
for now, and make sure it compiles.

## 2. What did it inherit?
Here's a `main` that pokes at a `Warrior` every way it can. Before you compile,
predict which lines work and which don't — then check.
```java
Warrior w = new Warrior();
Warrior x = new Warrior("Borin", 100);
System.out.println(w.name);
System.out.println(w.health);
System.out.println(w.getName());
System.out.println(w.getHealth());
```
> Why do `getName()` and `getHealth()` run fine when neither is written anywhere in `Warrior.java`? And why does `new Warrior("Borin", 100)` fail when the parent clearly has that exact constructor?

## 3. Build it with real values
You just saw that `new Warrior("Borin", 100)` doesn't work. Give `Warrior` a
constructor that takes a name and a health and sets up the `Character` part with
them. You already saw you can't touch `name` or `health` directly — so how do you
get the parent to set them?
> Whatever you use has to be the very first line of the constructor. Why must it come first? Hold onto your answer.

## 4. Watch the order they run in
Add a `System.out.println(...)` as the first line of every constructor — each of
`Character`'s and `Warrior`'s — printing which one it is (e.g.
`"Character(name, health)"`, `"Warrior"`). In the child, first try putting the
print *above* the call to the parent. Then create a single
`new Warrior("Borin", 100)` and watch what prints, and in what order.
> The child's print won't go above the parent call — the compiler stops you. Given that, could the child's body ever run before the parent's? And notice which of `Character`'s constructors printed, and which stayed silent. (You can delete these prints once you've seen it.)

## 5. Give the warrior its own edge
A warrior's training lets it shrug off blows that would fell anyone else. Add a
`fortitude` field to `Warrior` and take it in the constructor. Then override
`getHealth()` so a warrior reports its health boosted by its fortitude. Computing
that means getting at the stored health.
> Reach for `health` and you'll hit a wall — the parent keeps it private, even from its own children. Public is too far the other way. What sits between them? (One more trap: calling `getHealth()` from inside your new `getHealth()` won't do what you'd hope.)

## 6. Print a character
In `main`, print a character directly with `System.out.println(w);`. You never
wrote a print format, yet you get something like `Warrior@1b6d358c`. `Character`
doesn't extend anything you wrote — so where is that text coming from? Track it
down, then give `Character` its own version that returns something readable.
> What must your method's name, parameters, and return type match for Java to treat it as a *replacement* rather than a brand-new method?

## 7. Don't reprint what the parent prints
Give `Warrior` its own readable printout too — but reuse the one you just wrote in
`Character` instead of retyping the name-and-health part, then add the fortitude
onto it. How do you call the parent's version from inside the child's?
> Calling it plainly just runs the child's own version again, forever. You need a way to aim one level up.

## On your own
Add a `Mage` that also inherits from `Character`:
- give it a `mana` field, set through its constructor, and a getter for it;
- give it a readable printout that reuses `Character`'s and adds the mana.
