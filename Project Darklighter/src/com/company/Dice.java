package com.company;
import java.util.Random;

public class Dice implements java.io.Serializable {
    Random rand = new Random();

    public Dice() {
        // Default constructor
    }

    // Return num between 1 - 6
    public int diceRoll() { return rand.nextInt(6) + 1; }

    // Manual dice roll, return num between 1 - (max)
    public int manualDiceRoll(int max) {
        if (max < 0) { return 0; }
        if (max == 1) { return 1; }
        return rand.nextInt(max) + 1;
    }

    // Return random num between min-max
    public int manualDiceRollBetween(int min, int max) { return (int)(Math.random() * (max - min) + min); }

    // Return random num between 0.00 - 1.00 * 3
    public double decimalDiceRoll() {
        return rand.nextDouble() * 3.00;
    }

    // Return double 0.00 - 1.00
    public double chance_roll() { return rand.nextDouble(); }

    // Used for generating the tiles in each room
    // Returns either a 1 or 0
    public int tile_spawn() {
        double tile_chance = chance_roll();
        if (tile_chance < 0.05) {
            return 1;
        }
        return 0;
    }

    public String random_enemy_name(String[] arr) {
        return arr[manualDiceRoll(arr.length)-1];
    }

    // Encounters for 1's at Room tiles
    public String encounter() {
        if (chance_roll() < 0.3) {
            return "You encounter enemy!";
        }
        return "You encounter some treasure..";
    }
}
