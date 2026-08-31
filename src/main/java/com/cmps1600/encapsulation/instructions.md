# Encapsulation Activity

Follow along as we build one class step by step. Each step sets up the next, so
build them in order and run your code after every step. Every step hands you a
problem — figure out the fix yourself before moving on.

## Start here

`Character.java`
```java
public class Character {
    String name;
    int health;
}
```

`Main.java`
```java
public class Main {
    public static void main(String[] args) {
        Character c = new Character();
        c.name = "Aria";
        c.health = 100;
        System.out.println(c.name + ": " + c.health);
    }
}
```

Run it. It works — for now.

## 1. Lock down the data
In `main`, set `c.health` to something absurd like `-99999` and run it. Nothing
stops you — any code anywhere can overwrite a character's fields, dropping health
below zero or above whatever cap the game is supposed to enforce. How do you stop
outside code from reaching in and changing them directly? Make that change to
`Character`.
> Once you do, `main` won't compile. Good — read exactly what it can no longer do.

## 2. Read it back
Now `main` can't even print `name` or `health`. How can you let outside code
*read* those values without opening the fields back up for editing? Add it, then
use it to print the character and run.
> You never set anything after locking the fields down — so what actually prints? Note what a `String` and an `int` start out as.

## 3. Set it up
`main` still has no way to give a character its `name` and `health`, and
reopening the fields isn't the answer. How can you hand a character its starting
values at the moment it's created?
> Get its name and return type right, or what you write won't do the job. And once it compiles, watch the `new Character()` call `main` has used since the start — something about it just changed.

## 4. Create a character with it
`main` won't compile until you create the character the new way. Update it and run.

## 5. Name your parameters honestly
Give your constructor's parameters the same names as the fields — `name` and
`health` — and run it. The health comes back `0` even though you passed a real
number. Which `health` is each line inside the constructor actually referring to,
and how do you tell Java you mean the field, not the parameter?
> Nothing broke by accident — the names are doing exactly what Java's scope rules say they should.

## 6. A second way to spawn a character
Most characters spawn at full health. Add another way to create a `Character`
from just a name, starting the health at `100`, and make one character of each
kind in `main`.
> Same name, built two ways — something about the two has to differ, or Java won't allow it.

## 7. Stop repeating yourself
Your two constructors now share setup code. How can the shorter one reuse the
longer one's work instead of copying those lines?
> Whatever you reach for is fussy about *where* in the constructor it's allowed to appear.

---

You now have a class that protects its data, exposes it through getters, and can
be built two different ways without repeating itself.

## On your own
If you finish early, keep extending the class:
- Add a `private int level`, make sure every new character receives one, and give
  outside code a way to read it.
- Add a third way to spawn a character without duplicating any setup.
