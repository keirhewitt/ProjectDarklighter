package com.company;

import java.util.*;

/**
 * Inventory contains an arraylist which holds all of the items
 * This will have a max number and also a weight limit
 * Players originally may only hold 16 items and/or up to 30kg
 */
public class Inventory implements java.io.Serializable {

    private Dice d1 = new Dice();
    private DB_ db_ = new DB_();
    private ArrayList<Item> inventory_items = new ArrayList<Item>();
    private HashMap<Integer, Item> temp_map = new HashMap<Integer, Item>();
    private HashMap<Integer, Item> heal_map = new HashMap<Integer, Item>();
    private HashMap<Integer, Weapon> weapon_map = new HashMap<Integer, Weapon>();
    private HashMap<Integer, Ingredient> ingredient_map = new HashMap<>();
    private int slots;
    private int empty_slots;
    private double weight_limit = 30.0;

    // Format for tables
    public String leftAlignFormat = "| %-4d | %-29s | %-11s | %-5d | %-9d|%n";
    public String leftAlignHealing = "| %-4d | %-25s | %-7d | %-5d | %-9d|%n";
    public String leftAlignIngredient = "| %-4d | %-29s | %-5d | %-9d|%n";

    private ArrayList<Item> enemy_loot_chance = new ArrayList<Item>();

    public Inventory(int slots) {
        this.slots = slots;
        this.empty_slots = slots;
    }

    /**
     * If the Item to be replaced is in the Inventory and has a quantity of 1,
     * Or if the Item to replaced has a quantity of > 1 but there are still slots available
     *  replace it with the given 'to_be_added' Item
     * @param to_be_added
     * @param to_be_replaced
     * @return boolean
     */
    public boolean replace_with(Item to_be_added, Item to_be_replaced) {
        if (can_hold_item_replace(to_be_added, to_be_replaced)) {
            if (inventory_items.contains(to_be_replaced)
                    && inventory_items.get(inventory_items.indexOf(to_be_replaced)).getQuantity() == 1) {

                remove_item(to_be_replaced);
                addToInventory(to_be_added);

                return true;

            } else if (inventory_items.contains(to_be_replaced)
                    && inventory_items.get(inventory_items.indexOf(to_be_replaced)).getQuantity() > 1
                    && this.slots >= 1) {

                // Remove one from the quantity of the items 'to be replaced' and add the new item separately
                inventory_items.get(inventory_items.indexOf(to_be_replaced))
                        .decreaseQuantity(1);
                addToInventory(to_be_added);

                return true;

            }
        }

        return false;
    }

    /**
     * Checks if adding the item to the inventory will push the Player over the max weight limit
     * @param item
     * @return
     */
    public boolean can_hold_item(Item item) {
        double current_weight = get_current_weight();

        if ((current_weight + item.get_weight_value()) > weight_limit) {
            System.out.println("Cannot pick up " + item.getName() + " this will put you overweight!");
            return false;
        }
        return true;
    }

    /**
     * Checks if replacing and item with another will put the Player over max weight limit.
     * @param added
     * @param replaced
     * @return
     */
    public boolean can_hold_item_replace(Item added, Item replaced) {
        double current_weight = get_current_weight();

        if ((current_weight + added.get_weight_value() - replaced.get_weight_value()) > weight_limit) {
            System.out.println("Cannot replace with " + added.getName() + " this will put you overweight!");
            return false;
        }
        return true;
    }


    /**
     * Checks if item will put player over weight
     * Checks if there are empty slots available
     * Checks if the item will be a duplicate, adds as a duplicate if necessary (increase original item quantity)
     *
     * If adds item, reduce empty slots
     * @param item
     */
    public void addToInventory(Item item) {
        if (can_hold_item(item)) {
            if (empty_slots > 0) {                          // If not slots left
                if (check_duplicate(item)) {        // If inventory contains instance of item
                    add_duplicate(item);            // Add to the duplicate instead of adding a new item
                } else {                                    // If new item, add to inventory and reduce slots
                    inventory_items.add(item);
                    this.empty_slots -= 1;
                }
            } else {
                return;
            }
        }
    }

    public int addToInventory(Item item, int number_of_items) {
        for (int i = 0; i < number_of_items; i++) {
            if (can_hold_item(item)) {
                if (empty_slots > 0) {                          // If not slots left
                    if (check_duplicate(item)) {        // If inventory contains instance of item
                        add_duplicate(item);            // Add to the duplicate instead of adding a new item
                    } else {                                    // If new item, add to inventory and reduce slots
                        inventory_items.add(item);
                        this.empty_slots -= 1;
                    }
                } else {
                    System.out.println("There is no space left in your inventory! Added " + i + " of: " +IO.T_B+ item.getName() +IO.T_RS);
                    return number_of_items - i; // Return the remainder if not all could be added
                }
            }
        }
        return 0; // If all added, return 0 (none left over)
    }


    /**
     * This just removes the item from the inventory
     *
     *
     * @param item - Item to drop
     */
    public void remove_item(Item item) {
        if (inventory_items.contains(item)) {
            inventory_items.remove(item);
            this.empty_slots += 1;
        } else {
            System.out.println("You do not have this item to drop!");
        }
    }

    /**
     * Returns the number of empty slots in player inventory
     *
     * @return
     */
    public int getEmpty_slots() {
        return empty_slots;
    }

    /**
     * Returns the number of free slots in the player inventory
     *
     * @return
     */
    public int getSlots() {
        return slots;
    }

    /**
     * Checks to see if the item parameter contains a duplicate within the player inventory
     *
     * @param item
     * @return
     */
    public boolean check_duplicate(Item item) {
        for (Item i: inventory_items) {
            if (i instanceof Formula && item instanceof Formula) {
                if (((Formula) i).getContainer() != ((Formula) item).getContainer()) {
                    return false;
                }
            }
            if (i.getName().equals(item.getName()) && i.getWeight().equals(item.getWeight())
            && i.getValue() == item.getValue()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a duplicate item to the player inventory
     * Equality checks are => Name, Weight, Value
     *
     * @param item
     */
    public void add_duplicate(Item item) {
        for (Item i: inventory_items) {
            if (i.getName().equals(item.getName()) && i.getWeight().equals(item.getWeight())
                    && i.getValue() == item.getValue()) {
                i.increaseQuantity(1);
            }
        }
    }

    /**
     * Displays the inventory in a format that allows the player to choose an item based on a numerical ID
     *
     */
    public void display_inventory() {

        // Might use this number system for getting the player to access the inventory items
        // i.e. whatever item they want just index the value of 'x' - 1

        System.out.format("+------+-------------------------------+-------------+-------+----------+%n");
        System.out.format("| ID   | Name                          | Type        | Value | Quantity |%n");
        System.out.format("+------+-------------------------------+-------------+-------+----------+%n");
        for (int i = 0; i < inventory_items.size(); i++) {
            System.out.format(leftAlignFormat,
                    i+1,
                    inventory_items.get(i).getName(),
                    inventory_items.get(i).getType(),
                    inventory_items.get(i).getValue(),
                    inventory_items.get(i).getQuantity(),
                    i * i);
        }
        System.out.format("+------+-------------------------------+-------------+-------+----------+%n");

    }

    /**
     * Displays all Ingredients currently in Player's inventory
     *
     * @return - true/false - depending on if player has any Ingredients
     */
    public boolean show_all_ingredients() {
        int index = 1;
        int j = 0;
        Ingredient currIngredient = null;
        boolean has_ingredients = false;

        // Clear heal map from previous uses (if applicable)
        if (!ingredient_map.isEmpty()) { ingredient_map.clear(); }

        for (Item i: inventory_items) {
            if (i instanceof Ingredient) {
                ingredient_map.put(index, (Ingredient) i);
                has_ingredients = true;
                index++;
            }
        }

        // If the Inventory contains at least 1 Ingredient Item, display the HashMap that was created above
        if (has_ingredients) {
            // Display items in HashMap
            System.out.format("+------+-------------------------------+-------+----------+%n");
            System.out.format("| ID   | Name                          | Value | Quantity |%n");
            System.out.format("+------+-------------------------------+-------+----------+%n");

            for (Map.Entry<Integer, Ingredient> set : ingredient_map.entrySet()) {

                System.out.format(leftAlignIngredient,
                        set.getKey(),
                        set.getValue().getName(),
                        set.getValue().getValue(),
                        set.getValue().getQuantity(),
                        j * j);

                j++;
            }
            System.out.format("+------+-------------------------------+-------+----------+%n");
            // Otherwise, return false, so the program can deal with it accordingly
        } else {
            return false;
        }
        return true;
    }

    /**
     * get an ingredient via index from the ingredient hashmap that is created above ^
     *
     * @param index
     * @return - Ingredient
     */
    public Ingredient choose_ingredient(int index) {
        Ingredient ingredient = null;

        if (ingredient_map.containsKey(index)) {
            ingredient = ingredient_map.get(index);
            ingredient_map.clear();
        }
        return ingredient;
    }

    public boolean show_all_weapons() {
        int index = 1;
        int j = 0;
        Weapon currentWeapon = null;
        boolean weapon_present = false;

        // Clear heal map from previous uses (if applicable)
        if (!weapon_map.isEmpty()) { weapon_map.clear(); }

        for (Item i: inventory_items) {
            if (i instanceof Weapon) {
                weapon_map.put(index, (Weapon) i);
                weapon_present = true;
                index++;
            }
        }

        // If the Inventory contains at least 1 Weapon Item, display the HashMap that was created above
        if (weapon_present) {
            // Display items in HashMap
            System.out.format("+------+-------------------------------+-------------+-------+----------+%n");
            System.out.format("| ID   | Name                          | Type        | Value | Quantity |%n");
            System.out.format("+------+-------------------------------+-------------+-------+----------+%n");

            for (Map.Entry<Integer, Weapon> set : weapon_map.entrySet()) {

                System.out.format(leftAlignFormat,
                        set.getKey(),
                        set.getValue().getName(),
                        set.getValue().getType(),
                        set.getValue().getValue(),
                        set.getValue().getQuantity(),
                        j * j);

                j++;
            }
            System.out.format("+------+-------------------------------+-------------+-------+----------+%n");
            // Otherwise, return false, so the program can deal with it accordingly
        } else {
            return false;
        }
        return true;
    }

    public Weapon choose_weapon(int index) {
        Weapon weapon = null;

        if (weapon_map.containsKey(index)) {
            weapon = weapon_map.get(index);
            weapon_map.clear();
        }
        return weapon;
    }

    /**
     * If Player 1+ healing Items in inventory, it will print them to console and return true
     * Otherwise, it returns false
     *
     * @return Boolean - depends on whether Inventory has healingItems.
     */
    public boolean show_healing_items() {
        int j = 0;
        int index = 1;
        boolean healing_item_present = false;

        // Clear heal map from previous uses (if applicable)
        if (!heal_map.isEmpty()) { heal_map.clear(); }

        // For each HealingItem in inventory, put into HashMap with incrementing index
        // i.e. 1, Bandage
        //      2, Tourniquet
        for (Item i: inventory_items) {
            if (i.isHealing_item()) {
                heal_map.put(index, i);
                healing_item_present = true;
                index++;
            }
        }

        // If the Inventory contains at least 1 healing Item, display the HashMap that was created above
        if (healing_item_present) {
            // Display items in HashMap
            System.out.format("+------+---------------------------+---------+-------+----------+%n");
            System.out.format("| ID   | Name                      | Healing | Value | Quantity |%n");
            System.out.format("+------+---------------------------+---------+-------+----------+%n");

            for (Map.Entry<Integer, Item> set : heal_map.entrySet()) {

                    System.out.format(leftAlignHealing,
                            set.getKey(),
                            set.getValue().getName(),
                            set.getValue().getHealingAmount(),
                            set.getValue().getValue(),
                            set.getValue().getQuantity(),
                            j * j);
                    j++;
            }
            System.out.format("+------+---------------------------+---------+-------+----------+%n");
            // Otherwise, return false, so the program can deal with it accordingly
        } else {
            return false;
        }
        return true;
    }

    /**
     * Player will choose index after show_healing_items() has been run and this will pass through this function
     * If index exists in the HashMap, player gains health equal to healing amount of Value HealingItem accessed by the index key
     *
     * (heal_map is cleared after every use)
     *
     * @param index the index of HashMap item that the player has chosen
     */
    public Item choose_healing_item(int index) {
        Item the_item = null;
        if (heal_map.containsKey(index)) {
            the_item = heal_map.get(index);
            heal_map.clear();
            System.out.println("You use "
                    + IO.T_BL
                    + the_item.getName()
                    + IO.T_RS
                    + " to heal for "
                    + IO.T_G
                    + the_item.getHealingAmount()
                    + IO.T_RS
                    + " hp");
        }
        return the_item;
    }

    public boolean show_all_containers() {
        int index = 1;
        int j = 0;
        Item currentContainer = null;
        boolean container_available = false;

        // Clear heal map from previous uses (if applicable)
        if (!temp_map.isEmpty()) { temp_map.clear(); }

        for (Item i: inventory_items) {
            if (Arrays.asList(i.get_item_type()).contains(ItemType.CONTAINER) ||
                    Arrays.asList(i.get_item_type()).contains(ItemType.POUCH)) {
                temp_map.put(index, i);
                container_available = true;
                index++;
            }
        }

        // If the Inventory contains at least 1 Weapon Item, display the HashMap that was created above
        if (container_available) {
            // Display items in HashMap
            System.out.format("+------+-------------------------------+-------------+-------+----------+%n");
            System.out.format("| ID   | Name                          | Type        | Value | Quantity |%n");
            System.out.format("+------+-------------------------------+-------------+-------+----------+%n");

            for (Map.Entry<Integer, Item> set : temp_map.entrySet()) {

                System.out.format(leftAlignFormat,
                        set.getKey(),
                        set.getValue().getName(),
                        set.getValue().getType(),
                        set.getValue().getValue(),
                        set.getValue().getQuantity(),
                        j * j);

                j++;
            }
            System.out.format("+------+-------------------------------+-------------+-------+----------+%n");
            // Otherwise, return false, so the program can deal with it accordingly
        } else {
            return false;
        }
        return true;
    }

    public Item choose_container(int index) {
        Item cont = null;

        if (temp_map.containsKey(index)) {
            cont = temp_map.get(index);
            temp_map.clear();
        }
        return cont;
    }

    /**
     * Shows all items within the inventory, with numerical ID for options
     *
     * @return Boolean - if contains any items
     */
    public boolean show_all_items() {
        int index = 1;
        int j = 0;
        boolean items = false;

        // Clear heal map from previous uses (if applicable)
        if (!temp_map.isEmpty()) { temp_map.clear(); }

        for (Item i: inventory_items) {
            temp_map.put(index, i);
            items = true;
            index++;
        }

        // If the Inventory contains at least 1 Weapon Item, display the HashMap that was created above
        if (items) {
            // Display items in HashMap
            System.out.format("+------+-------------------------------+-------------+-------+----------+%n");
            System.out.format("| ID   | Name                          | Type        | Value | Quantity |%n");
            System.out.format("+------+-------------------------------+-------------+-------+----------+%n");

            for (Map.Entry<Integer, Item> set : temp_map.entrySet()) {

                System.out.format(leftAlignFormat,
                        set.getKey(),
                        set.getValue().getName(),
                        set.getValue().getType(),
                        set.getValue().getValue(),
                        set.getValue().getQuantity(),
                        j * j);

                j++;
            }
            System.out.format("+------+-------------------------------+-------------+-------+----------+%n");
            // Otherwise, return false, so the program can deal with it accordingly
        } else {
            return false;
        }
        return true;
    }

    /**
     * Sum of all items in inventory, weighed
     *
     * @return
     */
    public double get_current_weight() {
        double current_weight = 0.00;
        for (Item i: inventory_items) {
            current_weight += i.get_weight_value();
        }
        return current_weight;
    }

    /**
     * Choose an item from a HashMap created when displaying the player inventory items
     *
     * HashMap -> <Item Index, Item>
     *
     * @param index
     * @return
     */
    public Item choose_item(int index) {
        Item item = null;

        if (temp_map.containsKey(index)) {
            item = temp_map.get(index);
            temp_map.clear();
        }
        return item;
    }

    // Returns a random Item ( healing item )
    public Item loot_random_healing_item() {
        return db_.return_random_healing_item();
    }

    // Returns a random Weapon
    public Weapon loot_random_weapon() {
        return db_.return_random_weapon();
    }

    // Returns a random valuable Item
    // Returns a random valuable Item
    public Item loot_random_valuable_item() {
        return db_.return_random_valuable_item();
    }

    public Item loot_random_normal_item() {
        return db_.generate_random_normal_item();
    }

    // Returns a random armour item
    public Armour loot_random_armour_item() { return db_.return_random_armour_item(); }

    // Returns the ArrayList object
    public ArrayList<Item> return_inventory() {
        return inventory_items;
    }

}
