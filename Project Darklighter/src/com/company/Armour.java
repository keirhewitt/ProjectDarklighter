package com.company;

/**
 * Armour extends Item, creating the specialised fields for durability and defence modifiers
 */
public class Armour extends Item implements java.io.Serializable {

    private String leftAlignFormat = "| %-29s | %-12d | %-22s | %-5d |%n";

    private int defence_rating;                         // Character must have this rating to equip armour piece

    private boolean chest;                              // Checks for whether the armour is for chest or head
    private boolean head;                               // Checks for whether the armour is for chest or head

    private double damage_absorption_modifier;          // Determines the percentage of an attack that is deflected
    private int max_armour_durability;                  // How much damage armour can take before it breaks
    private int armour_durability;                      // It's current durability

    private Formula current_effect;                     // Current applied Formula

    // Either 'LIGHT_ARMOUR', 'MEDIUM_ARMOUR' or 'HEAVY_ARMOUR'
    // This will affect things like dexterity checks - eventually
    // private ItemCategory armour_type;
    // ---------- TOOK THIS OUT--- FOR NOW

    public Armour(String name, String description, double weight,
                  int value, int defence_rating, int max_armour_durability,
                  double damage_absorption_modifier, boolean head, boolean chest,
                  ItemType[] itemTypes) {
        super(name, description, weight, value, false, 0, itemTypes);
        this.defence_rating=defence_rating;
        this.max_armour_durability=max_armour_durability;
        this.armour_durability=max_armour_durability;
        this.damage_absorption_modifier=damage_absorption_modifier;
        this.head=head;
        this.chest=chest;
        super.setType("Armour");
    }

    public void setDamage_absorption_modifier(double modifier) {
        this.damage_absorption_modifier = modifier;
    }

    public void setArmour_durability(int durability) {
        this.armour_durability = durability;
    }

    public void setDefence_rating(int defence_rating) { this.defence_rating = defence_rating; }

    /**
     * Damage armour from a strike/any other methods
     * @param amount - integer amount that the armour is damaged
     */
    public void damage_armour(int amount) {
        this.armour_durability -= amount;
        if (this.armour_durability < 0) {
            this.armour_durability = 0;
        }
    }

    /**
     * Armour may be repaired
     * @param amount - amount that it is repaired
     */
    public void repair_armour(int amount) {
        this.armour_durability += amount;
        if (this.armour_durability > max_armour_durability) {
            this.armour_durability = max_armour_durability;
        }
    }

    public Boolean chestArmour() { return chest; }
    public Boolean headArmour() { return head; }

    public double getDamage_absorption_modifier() { return damage_absorption_modifier; }

    public int getArmour_durability() { return armour_durability; }

    public int getMax_armour_durability() { return max_armour_durability; }

    public int getDefence_rating() { return defence_rating; }

    //public String getArmour_type() { return armour_type.toString(); }

    public void add_effect(Formula f) { this.current_effect=f; }

    public void remove_current_effect() { this.current_effect=null; }

    public Formula getCurrent_effect() { return current_effect; }

    public void print_to_string() {
        System.out.format("+-------------------------------+--------------+------------------------+-------+%n");
        System.out.format("| Name                          | Defence Req. | Current/Max Durability | Value |%n");
        System.out.format("+-------------------------------+--------------+------------------------+-------+%n");
        System.out.format(leftAlignFormat,
                this.getName(),
                this.getDefence_rating(),
                this.getArmour_durability() + "/" + this.getMax_armour_durability(),
                this.getValue()
        );
        System.out.format("+-------------------------------+--------------+------------------------+-------+%n");
    }

}
