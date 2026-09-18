# Debugging the Arena

*Finding the truth with breakpoints and watches — VS Code*

`Arena.java` runs a whole fight and prints only two lines: how many turns it lasted and the winner's name. Everything else that happens — who the fighters are, their stats, how hard each blow lands, how their health falls — happens silently inside the program. **You cannot answer the questions below by reading the output or even by reading the code carefully.** The numbers are decided at run time. Your job is to pause the program and look.

## Setup

- `cd` into the `arena-src` folder and open it in VS Code (install the **Extension Pack for Java** if prompted).
- Open `Arena.java` and press **F5** once to confirm it runs. You should see the two output lines.
- Set a **breakpoint** by clicking in the gutter to the left of a line number (a red dot appears). Press **F5** to run to it.
- While paused, use the **VARIABLES** panel to inspect objects, the **WATCH** panel to evaluate an expression like `other.getHealth()`, and the step buttons: **Step Over (F10)**, **Step Into (F11)**, **Continue (F5)**.
- A **conditional breakpoint** (right-click the red dot → Edit Breakpoint) that stops only when e.g. `turn == 8` will save you a lot of clicking.

---

## Part A — Who showed up?

Put a breakpoint just after both fighters are created (the line that decides who opens is a good spot) and inspect the two objects.

**Q1.** What **type** (Warrior / Mage / Rogue) is Fighter A, and what type is Fighter B?
> *Debug tip: In the VARIABLES panel the object's actual class is shown next to it, e.g. `Warrior@…`*

<br>

**Q2.** Write each fighter's starting stats: their stored `health` field, and their special stat (`fortitude`, `mana`, or `agility`), and their `baseDamage`.
> *Debug tip: Expand each object in VARIABLES to see its private fields. `baseDamage` is final but still visible there.*

<br>

**Q3.** For the Warrior, add `theWarrior.getHealth()` to the WATCH panel and compare it to the `health` field you just read. They differ — by how much, and why?
> *Debug tip: Look at how each class defines getHealth(). One of them overrides it.*

<br>

**Q4.** Which fighter takes the **first turn**, and what decided it?
> *Debug tip: Step past the `rng.nextBoolean()` line and watch which branch runs.*

<br>

## Part B — Blow by blow

Move your breakpoint inside the loop. A conditional breakpoint on the attack line is ideal.

**Q5.** How much damage is dealt on **turn 2**, and what is the target's `health` field immediately after that hit?
> *Debug tip: Break when `turn == 2`; watch `dmg` and `other.getHealth()`.*

<br>

**Q6.** Turns 3 and 6 are **cast** turns, not attacks. Describe what each cast changes on the fighter that casts (which field moves, and by how much).
> *Debug tip: Step Into (F11) the `cast()` call and watch the fields before/after.*

<br>

**Q7.** The Rogue's per-hit damage is **not constant** across the fight. Compare the damage it deals on turn 2 with the damage it deals on turn 8. What changed in between to make it hit harder?
> *Debug tip: One of the cast turns belongs to the Rogue. Connect that to dealDamage().*

<br>

**Q8.** On the **final turn**, what is the losing fighter's `health` field the instant the loop exits? (It is not zero.)
> *Debug tip: Break on the winner-decision line, or watch the loop condition go false.*

<br>

## Part C — The verdict

**Q9.** How many **turns** does the fight last? (You may read this from the output — but confirm it against your step count.)

<br>

**Q10.** The program prints the winner's **name** but never its type or final health. What **type** is the winner, and what is its final health?
> *Debug tip: Inspect the `winner` variable on the last line.*

<br>

**Q11.** Bonus: had the **other** fighter opened the fight instead, do you think the result would change? Form a hypothesis, then change nothing but the opener and re-run to check.