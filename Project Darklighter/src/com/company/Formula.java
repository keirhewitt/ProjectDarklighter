package com.company;

/**
 * Ingredient formula blueprints:
 *
 *  _________________________
 *  |   Ingredient Types    |
 *  +-----------------------+
 *  |--   CREATURE_PART   --|
 *  |--   SPIRITUAL_HERB  --|
 *  |--    HEALING_HERB   --|
 *  |--   ENERGETIC_HERB  --|
 *  |--    UNIQUE_HERB    --|
 *  |--       FOOD        --|
 *  |--     MATERIAL      --|
 *  |--      MAGIC        --|
 *  +-----------------------+
 *
 * >> [Potions]/[Ointments] require empty [Vials] <<
 * >> [Salve's]/[Powders] require [scraps of paper] at least <<
 *
 * -- These will be lesser or greater depending on the potency of the ingredients used
 *
 * > Spiritual Herb x2 + Material = Armour protection potions
 *
 * > Healing Herb x2 = Healing potions
 *
 * > Energetic Herb x2 = Dexterity potions
 *
 *
 */
public class Formula extends Item {

    private boolean known;                  // Does the player know the formula?
    private Item container;                 // What container is used to store this
    private Ingredient[] recipe;            // Ingredients necessary to create object
    private Consistency group;              // What consistency the formula has
    private String stat_affected;           // What stat the formula targets, if one
    private int amount_affected;            // What amount of effect the formula has on the Stat/what it affects
    private double item_mod;                // If formula affects Item, how much by (percentage)
    private String operator;                // Determines what type of relation the affected amount has on the stat: ['+' ,'-' ,'*', '/']
    private boolean stat_formula;           // True if affects Stat
    private boolean item_formula;           // True if affects Item
    private int turns_stat_affected;        // Number of turns the stat formula is active for/spaces moved if not in combat
    private boolean weapon_formula=false;   // Is the formula made for applying to weapons?
    private boolean armour_formula=false;   // Is the formula made for applying to armour?

    /**
     * Constructor for Stat Formula's i.e. Health/dexterity potions etc.. These will be integer based
     *
     * Character will consume Formula and their Stats will be increased/decreased by a set amount
     *
     * For example:- Small Health Elixir will boost health by (up to) 20 points
     * @param recipe
     * @param name
     * @param price
     * @param group
     * @param stat_affected
     * @param amount_affected
     * @param container
     */
    public Formula(Ingredient[] recipe, String name, int price,
                   String description, Consistency group,
                   String stat_affected, int amount_affected,int num_turns,
                   String operator, Item container) {
        super(name, false, new ItemType[]{ItemType.NA});
        super.setValue(price);
        super.setType("Alchemy");
        super.setDescription(description);
        this.recipe=recipe;
        this.group=group;
        this.known=false;
        this.stat_affected=stat_affected;
        this.amount_affected=amount_affected;
        this.turns_stat_affected=num_turns;
        this.container=container;
        this.operator=operator;
        this.stat_formula=true;
        this.item_formula=false;
    }

    /**
     * Constructor for Item Formula's i.e. Weapon/Armour etc... These will be percentage based
     *
     * Character will apply this to their Item, and it will have a percentage effect
     *
     * For example:- Crude Weapon Oil will increase weapon damage vs. Humans by 20% (damage * 1.20)
     * @param recipe
     * @param name
     * @param price
     * @param group
     * @param item_mod
     * @param container
     */
    public Formula(Ingredient[] recipe, String name, int price, String description,
                   Consistency group, double item_mod, String operator, Item container,
                    boolean weapon, boolean armour) {
        super(name, false, new ItemType[]{ItemType.NA});
        super.setValue(price);
        super.setType("Alchemy");
        super.setDescription(description);
        this.recipe=recipe;
        this.group=group;
        this.known=false;
        this.amount_affected=amount_affected;
        this.item_mod=item_mod;
        this.container=container;
        this.operator=operator;
        this.stat_formula=false;
        this.item_formula=true;
        this.weapon_formula=weapon;
        this.armour_formula=armour;
    }

    /**
     *
     * -- Getters and Setters
     *
     */

    public boolean isStat_formula()     { return stat_formula; }

    public boolean isItem_formula()     { return item_formula; }

    public String getStat_affected()    { return stat_affected;}

    public int getAmount_affected()     { return amount_affected; }

    public Ingredient[] getRecipe()     { return recipe; }

    public boolean isKnown()            { return known; }

    public Consistency getGroup()      { return group; }

    public double getItem_mod()         { return item_mod; }

    public Item getContainer()          { return container; }

    public void learn_formula()         { this.known=true; }

    public String getOperator()         { return operator; }

    public int getTurns_stat_affected() { return turns_stat_affected; }

    public boolean isWeapon_formula() { return weapon_formula; }

    public boolean isArmour_formula() { return armour_formula; }
}
