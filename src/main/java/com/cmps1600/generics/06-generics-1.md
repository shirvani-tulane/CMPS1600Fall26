# Generics Activity

Last time, you put a mix of characters into a single `Character[]` and looped over
them — the polymorphism payoff. This time we'll build a *party*: a container that
holds a group of characters. Along the way you'll hit the limit of that
`Character[]`, and generics will be what gets you past it. Build the steps in order
and run after each one. Every step hands you a problem — work out the fix before
moving on.

## Where we left off

Our character tree, trimmed to what we need here:

`Character.java`
```java
public abstract class Character {
    private String name;
    protected int health;
    public Character(String name, int health) { this.name = name; this.health = health; }
    public String getName() { return name; }
    public int getHealth()  { return health; }
    public abstract void cast();
}
```

`Warrior.java`
```java
public class Warrior extends Character {
    private int fortitude;
    public Warrior(String name, int health, int fortitude) { super(name, health); this.fortitude = fortitude; }
    public int getHealth() { return health + fortitude; }
    public void cast() { health += 10; }
    public void rally() { System.out.println(getName() + " rallies the party!"); }
}
```

`Mage.java`
```java
public class Mage extends Character {
    private int mana;
    public Mage(String name, int health, int mana) { super(name, health); this.mana = mana; }
    public void cast() { mana += 20; }
    public int getMana() { return mana; }
}
```

## 1. "Just use a Character[]"
Every warrior and mage *is* a `Character`, so a single container should be able to
hold a whole party. Write one `Party` class backed by a `Character[]`, with
`add(Character c)` and `Character get(int i)`. Add a `Warrior` to it, then pull
member 0 back out into a `Warrior` variable and call `rally()`.
> The loop and storage live in one class — good. But pulling member 0 back into a `Warrior` won't compile. What does the error say `get` hands back? The party held your warrior perfectly, so why has Java forgotten it was a `Warrior`? (You *could* cast — but read on before you reach for it.)

## 2. What the cast costs you
Force it with a cast: `Warrior w = (Warrior) party.get(0);`. Now add a `Mage` to the
*same* `Party`, and try `Warrior w2 = (Warrior) party.get(1);` followed by
`w2.rally()`.
> It compiled. When does it go wrong — as you type, or only when it runs? A `Character[]` will hold anything that's a `Character`, so it can't stop you from yanking a `Mage` out *as if* it were a `Warrior`. You've got one class, but it forgot the specific type it was holding, and casting just moves the danger to runtime. You want one `Party` class that *remembers*.

## 3. Let the party carry a type
Change the header to `public class Party<T>`. That `<T>` **introduces** a type
placeholder — a stand-in for "whatever kind of character *this* party holds,"
decided later by whoever makes one. For storage, hold the members in an
`ArrayList<T>` (`import java.util.ArrayList;`), have `add` take a `T`, and have `get`
return a `T`.
> You've now *defined* a type parameter. Read the class: `T` isn't any real type — `Warrior`, `Mage`, `Character` appear nowhere in the body. So who decides what `T` is, and at what moment?

## 4. Supply the type
In `main`, write `Party<Warrior> warriors = new Party<Warrior>();`. Add a `Warrior`,
pull it back out into a `Warrior` variable — no cast — and call `rally()`.
> In step 3 you *defined* the placeholder `T` on the class; here in `<Warrior>` you're doing the other half — you're **supplying** the actual type. What did writing `<Warrior>` do to what `add` accepts and what `get` returns? Why is the cast from step 2 gone?

## 5. It now refuses the wrong character
Take that same `Party<Warrior>` and try to `add(new Mage(...))`.
> In step 2 this was allowed and only blew up at runtime; now what happens, and *when*? Supplying the type turned a lurking runtime bug into one the compiler catches immediately. That's the trade you were chasing.

## 6. Ask the party for a roster
Add a `printRoster()` method to `Party<T>` that loops the members and prints each
one's `getName()` and `getHealth()`. (Both live on `Character`.)
> It won't compile — `T` has no `getName()`. But everything you put in *is* a `Character`... so why won't Java take your word for it? As far as the class is concerned, what is the complete list of things `T` might be — could someone write `new Party<String>()` right now?

## 7. Constrain what T can be
Promise Java that `T` is always at least a `Character`: change the header to
`public class Party<T extends Character>`. Recompile `printRoster()`.
> It works now. What did `extends Character` let you *do* inside the class that you couldn't a moment ago? And as a bonus, try declaring a `Party<String>` — what does the compiler say, and why is being stopped here a *good* thing? (Heads-up: this `extends` puts a *bound* on a type parameter — a different job from the `extends` that builds a subclass.)

## 8. Pairing two kinds at once
Sometimes you want to bind two characters together — a tank and the healer keeping
them alive — and remember *each one's* exact type. One placeholder won't do; you
need to choose two independently. Write a class
`public class Pairing<A extends Character, B extends Character>` with an `A first`
and a `B second`, a constructor taking both, and getters `getFirst()`/`getSecond()`.
Then make a `Pairing<Warrior, Mage>`, call `rally()` on the first and `getMana()` on
the second — both without a cast.
> Now make a *different* pairing from the same class — say `Pairing<Rogue, Warrior>`. You wrote `Pairing` once; how many distinct two-type combinations can it produce? And because both parameters are bounded, what were you able to call on each side without casting?

---

You started with a `Character[]` and watched it forget the specific type it held —
forcing casts that fail only at runtime. Then you **defined** a type parameter on
the class and **supplied** a concrete type at each use — one reusable `Party` that
still checks types at compile time and hands members back as their real type. You
then **bounded** a parameter so the class could use what its members can do, and used
**multiple** bounded parameters to vary two types at once.

## On your own
- Java lets you drop the repeated type on the right: try
  `Party<Warrior> warriors = new Party<>();`. Does it still compile? What must the
  `<>` be figuring out on its own?
- Make a `Party` with *no* `<...>` at all — `Party raw = new Party();` — and add
  both a `Warrior` and a `Mage`. Does it compile? Do you get errors, or *warnings*?
  Tie this back to what supplying a type bought you.
- Add a `Rogue` (with an `agility` field and a `sneak()` method) and confirm a
  `Party<Rogue>` hands back rogues you can `sneak()` on with no cast. What did you
  *not* have to change in `Party` to make this work, and why is that the whole point?
