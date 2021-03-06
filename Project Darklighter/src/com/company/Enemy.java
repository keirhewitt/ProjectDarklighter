package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Enemy extends Character, having the unique field of 'xp' for reward
 * ...when you kill them
 *
 *  *** GLITCH: Enemy reduced to 1/1 health/MaxHealth and never goes below
 *   - Happened to Player too, reduced to 4/4 health/MaxHealth
 */
public class Enemy extends Character implements InventoryManagement, java.io.Serializable {

    private Dice d1 = new Dice();
    private int xp;
    private ArrayList<Ability> possible_attacks=new ArrayList<>();

    public Enemy(String name, String type, int xp, int level) {
        super(name, type);
        this.xp = xp;
        setInventory(10);
        super.setLevel(level);
        populate_inventory();
        populate_inventory();
    }

    public Enemy(String name, String type, Character player, int room) {
        super(name, type);
        super.getAttack().setStat_level(d1.manualDiceRollBetween((room*10)-7, room*10));
        super.getDefence().setStat_level(d1.manualDiceRollBetween((room*10)-7, room*10));
        super.getStrength().setStat_level(d1.manualDiceRollBetween((room*10)-7, room*10));
        super.getDexterity().setStat_level(d1.manualDiceRollBetween((room*10)-7, room*10));
        super.getFaith().setStat_level(d1.manualDiceRollBetween((room*10)-7, room*10));
        super.getInitiative().setStat_level(d1.manualDiceRollBetween((room*10)-7, room*10));
        super.getIntelligence().setStat_level(d1.manualDiceRollBetween((room*10)-7, room*10));
        super.getMaxHealth().setStat_level(d1.manualDiceRollBetween((room*10)-7, room*10));
        super.getHealth().setStat_level(getMaxHealth().getStat_level());
        super.setLevel(calculate_level());
        this.xp = calculate_xp_reward();
        setInventory(10);
        populate_inventory();
        populate_inventory();
    }

    public int getXpReward() { return xp; }

    public void setXpReward(int xp_r) { this.xp = xp_r; }

    public int calculate_xp_reward() {
        int xp = 0;
        for (int i = 1; i <= getLevel().getStat_level(); i++) {
            xp += d1.manualDiceRollBetween(5,10);
        }

        return xp;
    }

    /**
     * This will roll for the enemy's chance to perform each action in combat
     * Probability changes with current health
     * @return - int - choice
     */
    public int roll_combat_choice() {
        int choice = 0;
        double chance = 0.00;

        // Always attack if health > 60% max
        if (getHealth().getStat_level() > (int)getMaxHealth().getStat_level() * 0.6) {
            choice = 1;

        /** If health is between 41% and 60%
        Probability to heal is now enabled, very small chance to flee */
        } else if (getHealth().getStat_level()
                > (int)getMaxHealth().getStat_level() * 0.4
                && getHealth().getStat_level()
                <= (int)getMaxHealth().getStat_level() * 0.6) {
            if (d1.chance_roll() < 0.20) {
                choice = 2;         // Heal
            } else if (d1.chance_roll() < 0.23) {
                choice = 3;         // Flee
            } else {
                choice = 1;
            }
        /** If health is between 20% and 40%
         Probability of heal and flee increased */
        } else if (getHealth().getStat_level()
                >= (int)getMaxHealth().getStat_level() * 0.2
                && getHealth().getStat_level()
                <= (int)getMaxHealth().getStat_level() * 0.4) {
            if (d1.chance_roll() < 0.35) {
                choice = 2;         // Heal
            } else if (d1.chance_roll() < 0.40) {
                choice = 3;         // Flee
            } else {
                choice = 1;
            }
        /** Always heal if health less than 20% [or flee] */
        } else {
            if (d1.chance_roll() < 0.45) {
                choice = 2;         // Heal
            } else if (d1.chance_roll() < 52) {
                choice = 3;         // Flee
            } else {
                choice = 1;
            }
        }
        return choice;
    }

    /**
     * Compiles all Ability's that the enemy can do and returns them in an ArrayList
     * This way it is easy to access them when choosing what Ability to use
     * @return ArrayList
     */
    public ArrayList<Ability> list_of_possible_attacks() {
        possible_attacks.clear();

        HashMap<Integer, Ability> ability_map = compile_active_abilities_and_return();
        for (Map.Entry<Integer, Ability> set: ability_map.entrySet()) {
            possible_attacks.add(set.getValue());
        }
        return possible_attacks;
    }

    /**
     * Random Enemy attack based on their Stats and Weapon/Equipment
     * @return
     */
    public Ability roll_combat_ability() {
        possible_attacks=list_of_possible_attacks();
        Ability enemy_ability_choice = null;

        for (Ability a: possible_attacks) {

            enemy_ability_choice = possible_attacks.get(d1.manualDiceRoll(possible_attacks.size())-1);

        }

        return enemy_ability_choice;
    }

    /**
     * Rolls for the chance to spawn each item
     * Item is added to each enemy's inventory
     *
     * Roll happens twice (is called twice - i.e. this method does not contain 2 rolls each)
     */
    public void populate_inventory() {
        double gold_chance = d1.chance_roll();
        double weapon_chance = d1.chance_roll();
        double healing_chance = d1.chance_roll();
        double valuable_item_chance = d1.chance_roll();
        double armour_chance = d1.chance_roll();
        double food_chance = d1.chance_roll();

        if (valuable_item_chance < 0.06) {
            Item loot_valuable_item = getInventory().loot_random_valuable_item();
            if (loot_valuable_item != null) {
                add_to_inventory(loot_valuable_item);
            }
        }
        if (armour_chance < 0.12) {
            Armour loot_armour = return_levelled_armour_item();
            add_to_inventory(loot_armour);
            if (loot_armour.headArmour()) {
                equipHeadArmour(loot_armour);
            } else {
                equipChestArmour(loot_armour);
            }
        }
        if (weapon_chance < 0.30) {
            Weapon loot_weapon = return_levelled_weapon_item();
            add_to_inventory(loot_weapon);
            setEquipped_weapon(loot_weapon);

        }
        if (healing_chance < 0.75) {
            Item loot_heal_item = getInventory().loot_random_healing_item();
            if (loot_heal_item != null) {
                add_to_inventory(loot_heal_item);
            }
        }
        if (gold_chance < 0.85) {
            Currency currency_loot = new Currency(d1.manualDiceRoll(40));
            add_to_inventory(currency_loot);
        }
        if (food_chance < 0.90) {
            // Add later
        }


    }

    // If enemy has a healing item in their inventory, then heal random amount
    // Otherwise, return false
    public boolean check_healing() {
        Item i = null;
        Iterator<Item> it = getInventory().return_inventory().iterator();
        while (it.hasNext()) {
            i = it.next();
            if (i.getHealingAmount() > 0) {
                gainHealth(i.getHealingAmount());
                getInventory().return_inventory().remove(i);
                System.out.println(this.getName() + " heals for " + i.getHealingAmount() + "hp!");
                return true;
            }
        }
        return false;
    }

    // Return random heal amount between 1 - 20% of max health
    public int random_heal_amount() {
        return d1.manualDiceRoll((int)getMaxHealth().getStat_level() * 20);
    }


    public String toString() {
        return  "              | " + getName().toUpperCase() + '\n' +
                "````````````````````````" + '\n' +
                "Attack        | " + getAttack().getStat_level() + " |" + '\n' +
                "Strength      | " + getStrength().getStat_level() + " |" + '\n' +
                "Defence       | " + getDefence().getStat_level() + " |" + '\n' +
                "Dexterity     | " + getDexterity().getStat_level() + " |" + '\n' +
                "Intelligence  | " + getIntelligence().getStat_level() + " |" + '\n' +
                "Faith         | " + getFaith().getStat_level() + " |" + '\n' +
                "Initiative    | " + getInitiative().getStat_level() + " |" + '\n' +
                "Max Health    | " + getMaxHealth().getStat_level() + " |" + '\n' +
                "------------------------" + '\n' +
                "Level:        | " + getLevel().getStat_level() + " |" + '\n' +
                "XP:           | " + getXpReward() + " |";
    }

    /**
     * Add item to Inventory as long as there is a free slot
     * @param item - item to add to inventory
     * @return boolean - true if added, false if no free spaces
     */
    @Override
    public boolean add_to_inventory(Item item) {
        if (getInventory().getEmpty_slots() >= 1) {
            getInventory().addToInventory(item);
            return true;
        }
        return false;
    }

    /**
     * Removes given item from inventory, if it exists in arraylist
     * @param item - item to add
     * @return - true if removed, false if not
     */
    @Override
    public boolean remove_from_inventory(Item item) {
        Item current_item = null;
        Iterator<Item> it = getInventory().return_inventory().iterator();
        while (it.hasNext()) {
            current_item = it.next();
            if (current_item.equals(item)) {
                getInventory().remove_item(item);
                return true;
            }
        }
        return false;
    }
}
