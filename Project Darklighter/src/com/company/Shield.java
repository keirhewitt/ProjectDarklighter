package com.company;

/**
 * Shield takes on all the properties of Armour
 * Can only be equipped in off-hand
 */
public class Shield extends Armour {
    public Shield(String name, String description, double weight, int value, int defence_rating, int max_armour_durability, double damage_absorption_modifier) {
        super(name, description, weight, value, defence_rating, max_armour_durability, damage_absorption_modifier, false, false, new ItemType[]{});
        super.setType("Shield");
    }


}
