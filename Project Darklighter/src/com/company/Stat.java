package com.company;

/**
 * Made a class for Stat since there were a few properties I wanted to flesh out
 * I wanted to make it easier to access what a stat means for certain actions
 *  - Scripture > what Faith level do you need for certain Scripture
 *  - Intelligence > what Intelligence level do you need for certain actions
 *  - etc....
 */
public class Stat implements java.io.Serializable {

    private Dice d1 = new Dice();

    private String name;
    private String description;
    private int stat_level;

    public Stat(String name, String description, int stat_level) {
        this.name = name;
        this.description = description;
        this.stat_level = stat_level;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStat_level() {
        if (stat_level < 0) {
            return 0;
        }
        return stat_level;
    }

    public void increaseStatLevel(int amount) {
        this.stat_level += amount;
        if (stat_level > 100) {
            stat_level = 100;
        }
    }

    public void decreaseStatLevel(int amount) {
        this.stat_level -= amount;
    }

    public void setStat_level(int level) { this.stat_level = level; }

    // Perform a roll using the stat level
    public int statRoll() {
        return d1.manualDiceRoll(stat_level);
    }
}
