package com.company;

import java.util.ArrayList;

/**
 * Player inherits from Character and contains the unique field 'current_xp' which will track xp progress over time
 * Also employs the use of an array of integers to signify the level up stages.
 */
public class Player extends Character implements InventoryManagement, java.io.Serializable {

    private int current_xp = 1;
    private final int[] xp_stages = {25, 50, 80, 115, 170, 250, 380, 500, 700, 950, 1150, 1400, 1700, 2100, 2550};
    private ArrayList<Formula> known_formulas;
    private ArrayList<Room> previous_rooms = new ArrayList<>();
    private int current_pos_x;
    private int current_pos_y;
    private Room current_room;
    private Religion religion;
    private Alchemy alchemy;

    /**
     * Init Player with a name, set xp -> 1 and initialise the Player's inventory (16 slots)
     *
     * @param name
     */
    public Player(String name, String type) {
        super(name, type);
        current_xp = 1;
        setInventory(28);     // PLEASE CHANGE THIS BACK (16)
        pre_populate_player();
        religion = new Religion();
        known_formulas = new ArrayList<>();
        alchemy = new Alchemy();
    }

    /**
     * Pre-populate Player character so they start with some stuff
     */
    public void pre_populate_player() {

        Weapon loot_weapon = return_levelled_weapon_item();
        add_to_inventory(loot_weapon);
        setEquipped_weapon(loot_weapon);

        Armour loot_armour = return_levelled_armour_item();
        add_to_inventory(loot_armour);
        quick_equip(loot_armour);

        Item loot_heal_item = getInventory().loot_random_healing_item();
        if (loot_heal_item != null) {
            add_to_inventory(loot_heal_item);
        }

    }

    /**
     * Increases Player xp by [amount]
     * @param amount
     */
    public void add_xp(int amount) {
        this.current_xp += amount;
        System.out.println("You have gained " + IO.T_BL + amount + IO.T_RS + " xp");
        getPlayerLevel();
    }

    // xp cannot go below 1
    public void reduce_xp(int amount) {
        this.current_xp -= amount;
        if (current_xp <= 1) {
            current_xp = 1;
        }
        getPlayerLevel();
    }

    // For every index of xp_stages the players current xp has reached, add 1 to the starting level
    public void getPlayerLevel() {
        int level = getLevel().getStat_level();

        // Start from the next value in the xp_stages array each time you level up
        // Instead of always checking if your xp is larger than the 1st stage
        for (int i = getLevel().getStat_level() - 3; i < xp_stages.length; i++) {
            if (current_xp >= xp_stages[i]) {
                level += 1;
                System.out.println("\n\n\n");
                System.out.println( "+ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - +");
                System.out.println( "   ██     ███████ █    ██ ██████ █         ██    ██ █████  \n" +
                                    "   ██     ██     ██    █ ██     ██         ██    █ ██   ██ \n" +
                                    "   ██     █████  ██    █ █████  ██         ██    █ ██████  \n" +
                                    "   ██     ██      ██  ██ ██     ██         ██    █ ██      \n" +
                                    "   ██████ ███████  ████  ██████ ███████     ██████ ██      ");
                System.out.println( "+ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - +");
                super.setLevel(level);
                System.out.println("| You are now level " +IO.T_BL + getLevel().getStat_level() + IO.T_RS);
            }
        }

    }

    /**
     * Player will choose a stat each level to increase by 1
     */
    public void choose_stat_to_increase() {

    }

    /**
     *  -- Player position
     * @param pos_x - set the x co-ord
     * @param pos_y - set the y co-ord
     */
    public void set_current_position(int pos_x, int pos_y) {
        this.current_pos_x = pos_x;
        this.current_pos_y = pos_y;
    }

    public int getCurrent_pos_x() { return current_pos_x; }

    public int getCurrent_pos_y() { return current_pos_y; }

    public void print_room_grid() {
        if (current_room instanceof RoomHallway) {
            ((RoomHallway) current_room).print_grid_hallway();
        } else if (current_room instanceof RoomRiver) {
            ((RoomRiver) current_room).print_grid_river();
        } else if (current_room instanceof RoomBoss) {
            // print boss
        } else {
            current_room.print_grid();
        }
    }


    /**
     * --- Room navigation
     */
    public void move_north() {

        if (current_room instanceof RoomHallway) {
            if (((RoomHallway) current_room).check_north_hallway()) {
                current_pos_x -= 1;
            }

        } else if (current_room instanceof RoomRiver) {
            if (((RoomRiver) current_room).check_river_north()) {
                current_pos_x-=1;
            }

        } else if (current_room instanceof RoomBoss) {
            // Check boss room

        } else {
            if ((current_room.check_north())) {
                current_pos_x -= 1;
            } else {
                System.out.println("You cannot move North!");
            }
        }
    }

    public void move_east() {
        if (current_room instanceof RoomHallway) {
            if (((RoomHallway) current_room).check_east_hallway()) {
                current_pos_y += 1;
            }

        } else if (current_room instanceof RoomRiver) {
            if (((RoomRiver) current_room).check_river_east()) {
                current_pos_y += 1;
            }

        } else if (current_room instanceof RoomBoss) {
            // Check boss room

        } else {
            if ((current_room.check_east())) {
                current_pos_y += 1;
            } else {
                System.out.println("You cannot move East!");
            }
        }
    }

    public void move_west() {
        if (current_room instanceof RoomHallway) {
            if (((RoomHallway) current_room).check_west_hallway()) {
                current_pos_y -= 1;
            }

        } else if (current_room instanceof RoomRiver) {
            if (((RoomRiver) current_room).check_river_west()) {
                current_pos_y -= 1;
            }

        } else if (current_room instanceof RoomBoss) {
            // Check boss room

        } else {
            if ((current_room.check_west())) {
                current_pos_y -= 1;
            } else {
                System.out.println("You cannot move West!");
            }
        }
    }

    public void move_south() {
        if (current_room instanceof RoomHallway) {
            if (((RoomHallway) current_room).check_south_hallway()) {
                current_pos_x += 1;
            }

        } else if (current_room instanceof RoomRiver) {
            if (((RoomRiver) current_room).check_river_south()) {
                current_pos_x+=1;
            }

        } else if (current_room instanceof RoomBoss) {
            // Check boss room

        } else {
            if ((current_room.check_south())) {
                current_pos_x += 1;
            } else {
                System.out.println("You cannot move South!");
            }
        }
    }

    public void archive_current_room() {
        if (current_room instanceof Room) {
            previous_rooms.add(current_room);
            current_room = null;
        }
    }

    /**
     * Checks if the Player's current tile is an encounter
     * @return true if encounter (1) or false if not (0 or 2)
     */
    public boolean is_encounter() {
        if (current_room.check_tile(current_pos_x, current_pos_y) != 0 && current_room.check_tile(current_pos_x, current_pos_y) != 2) {
            return true;
        }

        return false;
    }

    /**
     * Returns the int type of encounter (number on the tile)
     * @return
     */
    public int return_encounter_type() {
        return current_room.check_tile(current_pos_x, current_pos_y);
    }


    /**
     * Checks if the Player's current tile is a door
     * @return true if door (2) false if not (0 or 1)
     */
    public boolean at_next_door() {
        if (current_room.getNext_door() == null) {
            return false;
         }else if (current_room.check_tile(current_pos_x, current_pos_y) == 2
                && this.current_pos_x == current_room.getNext_door()[0][0] && this.current_pos_y == current_room.getNext_door()[0][1] ) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the player's current tile is the door that leads to the previous room (door the player came in through)
     * @return
     */
    public boolean at_previous_door() {
        if (current_room.getPrevious_door() == null) {
            return false;
        } else if (current_room.check_tile(current_pos_x, current_pos_y) == 2
        && this.current_pos_x == current_room.getPrevious_door()[0][0] && this.current_pos_y == current_room.getPrevious_door()[0][1] ) {
            return true;
        }
        return false;
    }

    /**
     * Call when encounter has been completed
     */
    public void complete_encounter() {
        current_room.encounter_completed(current_pos_x, current_pos_y);
    }

    public void set_current_room(Room room) {
        this.current_room = room;
    }

    public Room getCurrent_room() { return current_room; }

    public Alchemy get_alchemy() { return alchemy; }


    /**
     * Returns true if the player has any healing items in their inventory
     * False if not
     * @return - true or false
     */
    public boolean has_healing_items() {
        for (Item i: getInventory().return_inventory()) {
            if (i.isHealing_item()) {
                return true;
            }
        }
        return false;
    }

    public Scripture get_active_scripture() { return getReligion().getActive(); }

    /**
     * Uses the item given in the parameter and if there is more than 1, reduce the quantity by 1
     * Otherwise, remove the item from the inventory
     * @param item - Item to heal
     */
    public void heal(Item item) {
        Item healing_item = null;
        for (Item i: getInventory().return_inventory()) {
            if (i.equals(item)) {
                healing_item = i;
                System.out.println(getName() + " healed for " + IO.T_G + i.getHealingAmount() +IO.T_RS + " hp!");
                this.gainHealth(i.getHealingAmount());
            }
        }
        if (healing_item != null) {
            has_used(healing_item);
        }
    }

    /**
     * After Item has been used this function will be called
     *
     * If item is a duplicate, reduce quantity by 1
     * else remove item
     * @param item
     */
    public void has_used(Item item) {
        boolean duplicate = false;
        for (Item i: getInventory().return_inventory()) {
            if (i.getName().equals(item.getName()) && i.get_weight_value() == item.get_weight_value() && i.getValue() == item.getValue()) {
                if (i.getQuantity() > 1) {
                    duplicate = true;
                    i.decreaseQuantity(1);
                }
            }
        }
        if (duplicate == false) {
            remove_from_inventory(item);
        }
    }

    /**
     * Quickly equip item, determines item Type and sorts accordingly
     * @param item
     */
    public void quick_equip(Item item) {
        if (item instanceof Shield) {
            this.equip_shield((Shield) item);
        }
        else if (item instanceof Armour) {
            if (((Armour) item).chestArmour()) {
                this.equipChestArmour((Armour) item);
            } else if (((Armour) item).headArmour()) {
                this.equipHeadArmour((Armour) item);
            }
        }  else if (item instanceof Weapon) {
            this.setEquipped_weapon((Weapon) item);
        }
    }

    /**
     * Return true/false if inventory contains object
     * @param item
     * @return
     */
    public boolean is_in_inventory(Item item) {
        if (getInventory().return_inventory().contains(item)) {
            return true;
        }
        return false;
    }

    /**
     * Return boolean value depending on if inventory contains at least 1 Weapon
     * @return
     */
    public boolean has_weapons() {
        for (Item i: getInventory().return_inventory()) {
            if (i instanceof Weapon) {
                return true;
            }
        }
        return false;
    }

    public void set_active_scripture(Scripture scripture) {

        if (scripture.getType_of_modifier() != null) {

            if (scripture.getType_of_modifier().toLowerCase().equals("plus")) {
                for (Stat s : stats) {
                    if (s.equals(scripture.getStat_affected())) {
                        s.increaseStatLevel(scripture.getEffect());
                    }
                }
            } else if (scripture.getType_of_modifier().toLowerCase().equals("minus")) {
                for (Stat s : stats) {
                    if (s.equals(scripture.getStat_affected())) {
                        s.decreaseStatLevel(scripture.getEffect());
                    }
                }
            }
        } else {
            System.out.println("Waiting for divine evidence of this monument's effect on you.. you are left wanting." +
                    "\nIt seems the gods wish not to bestow their powers upon you.");
        }
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
            System.out.println(item.getName() + " added to inventory.");
            return true;
        }
        System.out.println("No space in inventory to add " + item.getName() + "!");
        return false;
    }

    /**
     * Overloaded version of 'add_to_inventory' - for adding multiple of one item
     * @param item
     * @param number
     * @return
     */
    public int add_to_inventory(Item item, int number) {
        int remainder = 0;
        if (getInventory().getEmpty_slots() >= 1) {
            remainder = getInventory().addToInventory(item, number);
        } else {
            System.out.println("No space in inventory to add " + item.getName() + "!");
            return -1;
        }
        if (remainder > 0) {
            return remainder;
        }
        return 0;
    }

    /**
     * Removes given item from inventory, if it exists in arraylist
     * @param item - item to add
     * @return - true if removed, false if not
     */
    @Override
    public boolean remove_from_inventory(Item item) {
        if (is_in_inventory(item)) {
            getInventory().return_inventory().remove(item);
            return true;
        }
        return false;
    }

    public Religion getReligion() { return religion; }

    public String toString() {

        return  "              | " + getName().toUpperCase() +                 '\n' +
                "````````````````````````" +                                   '\n' +
                "Attack        | " + display_level(getAttack()) +       " |" + '\n' +
                "Strength      | " + display_level(getStrength())+      " |" + '\n' +
                "Defence       | " + display_level(getDefence()) +      " |" + '\n' +
                "Dexterity     | " + display_level(getDexterity()) +    " |" + '\n' +
                "Intelligence  | " + display_level(getIntelligence()) + " |" + '\n' +
                "Faith         | " + display_level(getFaith()) +        " |" + '\n' +
                "Initiative    | " + display_level(getInitiative()) +   " |" + '\n' +
                "Max Health    | " + display_level(getMaxHealth()) +    " |" + '\n' +
                "------------------------" + '\n' +
                "Level:        | " + IO.T_Y+ getLevel().getStat_level() + IO.T_RS + " |";

    }

}
