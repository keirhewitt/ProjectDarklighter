package com.company;

import java.util.Arrays;

public class Item implements java.io.Serializable{

    private String name;

    private String name_soaked;     // Name when item is soaked with Formula/liquid
    private String name_oiled;      // Name when oiled (Armour + Weapons)
    private String name_alight;     // Name when alight

    private String description;
    private String type;
    private double weight;
    private int value;
    private int quantity;
    private int healing_value = 0;
    private boolean healing_item;
    private boolean ingredient;
    private String leftAlignFormat = "| %-30s | %-7d | %-9s | %-8d |\n| %-68s |%n";
    private String leftAlignFormatHealing = "| %-21s | %-41s | %-12s | %-14s | %-9d |%n";
    private String leftAlignFormatWeapon = "| %-33s | %-13s | %-10d | %-8s |%n";
    private String leftAlignFormatArmour = "| %-35s | %-13s | %-10s | %-10s |%n";
    private ItemType[] itemType;

    private String writings = "";           // If anything has been written on this
    private Item absorbed_liquid;           // If this has been soaked with anything
    private boolean soaked = false;         // Check if item has been soaked
    private boolean is_container = false;   // Check if item is a container
    private boolean contains_formula = false; // Check if item contains formula
    public boolean alight = false;          // Item on fire?


    // This will increment by 1 for every Item created
    // Allows program to identify specific items by their ID
    private static int ID = 1;

    public Item(String name,
                String description,
                double weight,
                int value,
                boolean healing_item,
                int healing_value,
                ItemType[] itemType) {
        this.name = name;
        this.name_oiled = name + " (oil)";
        this.name_soaked = name + " (damp)";
        this.name_alight = name + " (lit)";
        this.description = description;
        this.weight = weight;
        this.value = value;
        this.quantity = 1;
        this.ID++;
        this.healing_item = healing_item;
        this.healing_value = healing_value;
        this.type="Misc";
        this.itemType=itemType;
        if (healing_value > 0) {type="Healing";}
    }

    public Item(String name,
                boolean healing_item,
                ItemType[] itemType) {
        this.name = name;
        this.quantity = 1;
        this.ID++;
        this.type="Misc";
        this.healing_item = healing_item;
        this.itemType=itemType;
    }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) { this.type = type; }

    public void setWeight(double weight) { this.weight = weight; }

    public void setValue(int value) { this.value = value; }

    public void increaseQuantity(int value) { this.quantity += value; }

    public void setQuantity(int value) { this.quantity = value; }

    public void decreaseQuantity(int value) { this.quantity -= value; }

    public ItemType[] getItemType() {return itemType;}

    /**
     * Soak and item and change it's integrity
     * Weight, status as FUEL potentially, add absorbed liquid property
     * @param liquid
     * @return boolean
     */
    public boolean soak(Item liquid) {
        if (Arrays.asList(itemType).contains(ItemType.ABSORBS)){
            soaked=true;
            absorbed_liquid=liquid;

            // If liquid soaked was not oil and Item had type 'FUEl', remove FUEL now
            if (Arrays.asList(itemType).contains(ItemType.FUEL) && !liquid.getName().toLowerCase().equals("oil")){
                Arrays.asList(itemType).remove(ItemType.FUEL);
            }

            this.weight=weight*1.3;         // Increase weight of item
            // Return weight of item + weight of liquid
            return true;
        }
        return false;
    }

    public void dry(Item item) {
        if (this.soaked == true) {
            this.soaked = false;
        }
    }

    public boolean write_on(String note) {
        if (Arrays.asList(itemType).contains(ItemType.WRITABLE)) {
            add_writing(note);
            return true;
        }
        return false;
    }

    /**
     * Check if this item has the given itemType
     * @param it
     * @return
     */
    public boolean has_item_type(ItemType it) {
        if (Arrays.asList(itemType).contains(it)) {
            return true;
        }
        return false;
    }

    public void add_writing(String note) { this.writings += note;}

    public String get_writings() { return writings; }

    // Returns weapon weight in the format of String (to include 'kg')
    public String getWeight() { return String.format("%.2f", weight) + " kg"; }

    public String returnValue() { return value + " g"; }

    /**
     * Gets the relevant name dependent on the state of the Item
     * @return String name
     */
    public String getName() {
        if (this instanceof Ingredient) {       // If player does not know the ingredient
            if (((Ingredient) this).known == false) {
                return "? ? ?";
            }
        } else {
            if (soaked == true) {
                return name_soaked;
            } else if (contains_formula == true) {
                return name_oiled;
            } else if (alight == true) {
                return name_alight;
            }
        }
        return name;
    }

    public String getDescription() {
        if (this instanceof Ingredient) {       // If player does not know Ingredient, give vague description
            if (((Ingredient) this).known==false) {
                return ((Ingredient) this).vague_desc;
            }
        }
        return description;
    }

    public String getType() { return type; }

    public int getValue() { return value; }

    public int getQuantity() { return quantity; }

    public void set_alight() { this.alight=true;}

    public void extinguish() { this.alight=false;}

    public void set_contains_formula() { this.contains_formula = true; }

    public void remove_formula() { this.contains_formula = false; }

    public void print_to_string() {
        String desc = "";
        if (this.description.lastIndexOf(this.description.charAt(this.description.length()-1)) > 60) {
            desc = this.description
                    .substring(0, this.description.charAt(60))
                    + "|"+'\n'+"| " +
                    this.description
                            .substring(this.description.charAt(60));
        } else {
            desc = this.description;
        }
        System.out.format("+--------------------------------+---------+-----------+----------+%n");
        System.out.format("| Name                           | Value   | Weight    | Quantity |%n");
        System.out.format("+--------------------------------+---------+-----------+----------+%n");
        System.out.format(leftAlignFormat,
                this.getName(),
                this.getValue(),
                this.getWeight(),
                this.getQuantity(),
                IO.T_IT+desc+IO.T_IT_RS
        );
        System.out.format("+--------------------------------+---------+-----------+----------+%n");
    }
    public void print_to_string_healing() {
        System.out.format("+-----------------------+-------------------------------------------+--------------+----------------+----------+%n");
        System.out.format("| Name                  | Description                               | Healing Item | Healing Amount | Quantity |%n");
        System.out.format("+-----------------------+-------------------------------------------+--------------+----------------+----------+%n");
        System.out.format(leftAlignFormatHealing,
                this.getName(),
                this.getDescription(),
                this.healing_item,
                this.getHealingAmount(),
                this.getQuantity()
        );
        System.out.format("+-----------------------+-------------------------------------------+--------------+----------------+----------+%n");
    }

    public void print_to_string_weapon() {
        if (this instanceof Weapon) {
            System.out.format("+-----------------------------------+---------------+------------+----------+%n");
            System.out.format("| Name                              | Attack Style  | Max Damage | Weight   |%n");
            System.out.format("+-----------------------------------+---------------+------------+----------+%n");
            System.out.format(leftAlignFormatWeapon,
                    this.getName(),
                    ((Weapon) this).getAttack_style(),
                    ((Weapon) this).getMax_damage(),
                    this.getWeight()
            );
            System.out.format("+-----------------------------------+---------------+------------+----------+%n");
        }
    }

    public void print_to_string_armour_shield() {

        String type = "";

        if (this instanceof Armour || this instanceof Shield) {
            if (((Armour) this) instanceof Armour) {
                if (((Armour) this).headArmour()) {type= "Head";}
                else {type="Chest";}
            } else {type="Shield";}

            System.out.format("+-------------------------------------+---------------+------------+------------+%n");
            System.out.format("| Name                                | Armour Type   | Durability | Weight     |%n");
            System.out.format("+-------------------------------------+---------------+------------+------------+%n");
            System.out.format(leftAlignFormatArmour,
                    this.getName(),
                    type,
                    ((Armour) this).getArmour_durability() + "/" + ((Armour) this).getMax_armour_durability(),
                    this.getWeight()
            );
            System.out.format("+-------------------------------------+---------------+------------+------------+%n");
        }
    }

    public void setHealing_amount(int amount) { this.healing_value=amount; }

    public void setHealing_item(Boolean heal) { this.healing_item = heal;}

    public int getHealingAmount() {
        return healing_value;
    }

    public boolean isHealing_item() { return healing_item; }

    public ItemType[] get_item_type() { return itemType; }

    public String toString() {
        return String.format("%s: %13s%n" +           // Format - String: String\n
                             "%s: %3s%n" +           // Format - String: String\n
                             "%s: %8b%n" +           // Format - Boolean: String\n
                             "%s: %4d%n",            // Format - String: Int\n
                             "Item",name,
                             "Description",description,
                             "Healing",healing_item,
                             "Quantity",quantity
        );
    }
}
