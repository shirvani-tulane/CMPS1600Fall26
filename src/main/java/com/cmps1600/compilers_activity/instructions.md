# Compilers Activity

In Python, you run your program and it fails partway through when it trips over a
mistake. Java works differently: before it will run *anything*, your code is
**compiled** — checked from top to bottom — and it won't build until the mistakes
it can see are gone. VS Code runs that checker for you constantly in the background,
so you don't even have to hit Run to see problems: they pile up live in the
**Problems** panel (open it with View → Problems, or Ctrl/Cmd+Shift+M). This
activity is about learning to read what shows up there.

Below is a `Character` class and a `Main` that uses it, riddled with mistakes. Open
both files and watch the Problems panel fill up. You'll fix them **one at a time,
top to bottom, watching the list shrink** — and you'll notice the panel isn't
static: fixing one mistake sometimes makes a *new* one appear that was hidden
before. Each error below is labelled with the file and line it points at, and shows
the exact text VS Code displays — but *not* what it means. Your job each time: first
say what's wrong, then fix it.

## Start here — the broken code

`Character.java`
```java
 1  public class Character {
 2      private String name;
 3      private int health
 4
 5      public void setName(String name) {
 6          this.name = name;
 7      }
 8      public String getName() {
 9          String label = "Name: " + name;
10      }
11      public void setHealth(int health) {
12          this.health = health;
13      }
14      public int getHealth() {
15          return health;
16      }
17  }
```

`Main.java`
```java
 1  public class Main {
 2      public static void Main(String[] args) {
 3          Character hero = Character();
 4          hero.setName("Aria")
 5          hero.setHealth("full");
 6          System.out.println(hero.health);
 7
 8          int level = getHealth();
 9
10          status = "Level: " + level;
11          System.out.println(Status);
12
13          int bonus;
14          System.out.println(bonus);
15      }
16  }
```

Go only by what the Problems panel reports — don't try to fix everything you can
spot by eye. Fix the top item, save, and watch the list update before moving on.

---

## Round 1 — the panel on open
As soon as both files load, the checker reports a batch of problems. Work them top
to bottom. (One line — `Main.java` line 2 — shows a yellow *warning*, not a red
error. Leave it for now; we come back to it at the very end.)

**`Character.java:3`**
```
Syntax error, insert ";" to complete FieldDeclaration
```
> What is the checker saying it needs at the end of this field, and why can't it finish reading the class without it? Fix it.

**`Main.java:3`**
```
The method Character() is undefined for the type Main
```
> The checker thinks you're *calling a method* named `Character` — and there's no such method. But you didn't mean to call anything; you meant to build a brand-new `Character` object. What one keyword turns "call something named `Character`" into "make a new `Character`"?

**`Main.java:4`**
```
Syntax error, insert ";" to complete Statement
```
> The same kind of complaint as the first one, this time on a statement in `main`. Fix it the same way.

**`Main.java:5`**
```
The method setHealth(int) in the type Character is not applicable for the arguments (String)
```
> Go read `setHealth` in `Character` — what kind of value does it accept? Now look at what line 5 hands it. The message spells out the mismatch exactly. What would a valid call look like?

**`Main.java:6`**
```
The field Character.health is not visible
```
> You deliberately marked that field a certain way inside `Character`, and now `main` can't see it. Why not — and what did you add to `Character` specifically so outside code *could* get the value? Use that instead.

**`Main.java:8`**
```
The method getHealth() is undefined for the type Main
```
> `getHealth` clearly exists — but as something a *`Character`* can do, and on line 8 it's being called on its own, floating in `main`. Whose health is being asked for? What does this call need in front of it to make sense?

**`Main.java:10`**
```
status cannot be resolved to a variable
```
> The checker has never heard of `status`. In Python a name springs into being the moment you assign it; Java won't let a variable exist until you've said what *kind* of value it holds. What's missing from the front of this line?

**`Main.java:11`**
```
Status cannot be resolved to a variable
```
> You just declared a variable on line 10. Put that declared name and this name side by side — what's different, and why does Java treat them as two entirely unrelated names?

## Round 2 — problems that were hiding
Fix everything in Round 1 and save. Two new red errors appear that you hadn't seen
— one in each file. Each was hidden behind that file's syntax error: while a
semicolon was missing, the checker couldn't parse far enough to notice these. Now
that each file parses, they surface.

**`Character.java:10`**
```
This method must return a result of type String
```
> Why is this only showing up *now*, when `getName()` hasn't changed? Read the method header — it promises to hand something back — then follow the body to the closing brace. Does anything actually get handed back? Fix it.

**`Main.java:13`**
```
The local variable bonus may not have been initialized
```
> You declared `bonus` on line 13, so it exists — but the checker traced the code to line 14 and found `bonus` never got a value. Java reserved the slot and refuses to *read* it until you put something in. What's the smallest fix?

Save. The Problems panel is empty of red now.

## One last thing — the yellow one
Look back at the item we skipped in Round 1. It's still there, but notice its colour
and icon: **yellow**, not red.

**`Main.java:2`**
```
This method has a constructor name
```
This is a **warning**, not an error. That distinction matters. An *error* means the
code is broken and won't compile. A *warning* means the code is perfectly valid Java
— it will compile and run — but the checker has spotted something that looks like it
might not be what you intended, and is giving you a heads-up before it bites you
later.

> Here's why it's suspicious: a method whose name exactly matches its class (`Main`
> inside class `Main`) looks like an attempt at a special kind of method you haven't
> learned yet — so the checker flags it in case that wasn't your plan. It *was* an
> accident: you meant the program's starting method, which Java requires be spelled
> a very specific way. Compare `public static void Main` with the standard entry
> point, fix the capitalization, and watch the warning disappear.

Once the yellow is gone too, run the program — it should print its output.

---

You just watched the checker work: it parses first (which is why the missing-return
and the uninitialized-variable stayed hidden until each file's semicolon was fixed),
then checks names and types, then traces how values flow — all **before** the
program runs. You also met the two things it can tell you: **errors**, which stop the
build, and **warnings**, which don't stop anything but flag code that may not mean
what you think. Reading both fluently is a core Java skill; you'll run this exact
loop for the rest of the course.

## On your own
- Break it again on purpose: rename `getHealth` to `getHP` in `Character` but leave
  `Main` calling `hero.getHealth()`. Predict the exact message before you save, then
  check.
- In `Character`, try writing `private int health = "100";`. Which Round 1 error
  does this match, and why?
- Delete the word `int` from `int bonus = 5;` so it reads `bonus = 5;`. Which
  Round 1 error does this reproduce, and what does it tell you Java requires that
  Python doesn't?
