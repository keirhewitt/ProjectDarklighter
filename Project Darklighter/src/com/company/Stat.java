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
    private boolean temporary_increase = false;  // If stat has been temporarily altered
    private boolean temporary_decrease = false;  // If stat has been temporarily altered

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

    /**
     * Increases a stat level to a max of 100
     * @param amount
     */
    public void increaseStatLevel(int amount) {
        this.stat_level += amount;
        if (stat_level > 100) {
            stat_level = 100;
        }
    }

    /**
     * Decreases stat level to a min of 1
     * @param amount
     */
    public void decreaseStatLevel(int amount) {
        this.stat_level -= amount;
        if (stat_level < 1 && !name.toLowerCase().equals("health")) {       // Health can go below 1
            stat_level = 1;
        } else if (stat_level < 1){                                         // If it's health, it goes to 0
            stat_level = 0;
        }
    }

    /**
     * This does not call increaseStatLevel() since I want Stats to be able to breach the Max when using a stat increase
     * mechanic
     * @param amount
     */
    public void temporary_stat_increase(int amount) {
        this.stat_level += amount;
        apply_temp_stat_increase();
    }

    /**
     * This DOES use decreaseStatLevel since I don't want stats to go beneath 1 for stat decrease mechanics
     * @param amount
     */
    public void temporary_stat_decrease(int amount) {
        decreaseStatLevel(amount);
        apply_temp_stat_decrease();
    }

    public void apply_temp_stat_decrease() { this.temporary_decrease = true; }
    public void remove_temp_stat_decrease() { this.temporary_decrease = false; }
    public boolean get_temp_stat_decrease() { return this.temporary_decrease;}

    public void apply_temp_stat_increase() { this.temporary_increase = true; }
    public void remove_temp_stat_increase() { this.temporary_increase = false;}
    public boolean get_temp_stat_increase() { return temporary_increase; }

    public void setStat_level(int level) { this.stat_level = level; }

    // Perform a roll using the stat level
    public int statRoll() {
        return d1.manualDiceRoll(stat_level);
    }
}
