package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Character is the base class for all sentient entities
 * Every instance of class Character will have:
 *  - Inventory
 *  - Equipped Weapon (even if it's 'fists')
 *  - Max Health/ health
 *  - Level
 *  - Attack
 *  - Defence
 *  - Strength
 *  - Boolean 'died' variable
 *  - Instance of Dice to roll for actions
 */
public class Character implements Combat, java.io.Serializable  {

    private Dice d1 = new Dice();
    private DB_ db_ = new DB_();

    // Character's position in a room
    private int position_x;
    private int position_y;

    /**
           ****** RESISTANCES --- YET TO IMPLEMENT *******
                                                                */
    private double fire_resistance = 0.0;         // i.e. If this is 50%, take 50% of potential fire damage
    private double frost_resistance = 0.0;
    private double poison_resistance = 0.0;       // i.e. If this is 90%, take 10% of potential poison damage
    private double spiritual_resistance = 0.0;
    private double bleed_resistance = 0.0;
    private double lightning_resistance = 0.0;

    private Inventory inventory;                  // Inventory is of TYPE 'Inventory'

    /**
       Stat altering effect collections
                                        */
    private HashMap<StatusEffect, Integer> active_status_effects = new HashMap<>();      // Contains status effects and Num of turns affected

    private Alchemy alchemy;                      // Init alchemy

    public final ArrayList<Stat> stats = new ArrayList<>();                 // ArrayList of all Stat's
    private final HashMap<Ability, Boolean> abilities = new HashMap<>();    // HashMap of Ability/Boolean - whether they can use them
    private HashMap<Integer, Ability> active_abilities = new HashMap<>();   // HashMap of active Ability's Characters can use

    private String name;                           // Character's name
    private String type;                           // Human/Goblin/Rat/etc
    private Weapon equipped_weapon;                // Fists or a weapon
    private Scripture active_scripture;            // What current scripture they have active
    private Weapon fists = new Weapon("Fists", AttackType.UNARMED, 0.00, 3, new ItemType[]{ItemType.HITS});

    /**
        Stats
                */
    public Stat maxHealth = new Stat("Max Health", "Maximum Health Value.", 100);
    public Stat health = new Stat("Health", "Current Health.", 100);
    public Stat level = new Stat("Level", "Current Level.", 3);
    public Stat strength = new Stat("Strength", "Melee Power.", d1.manualDiceRollBetween(3,10));
    public Stat attack = new Stat("Attack", "Attack accuracy.", d1.manualDiceRollBetween(3,10));
    public Stat defence = new Stat("Defence", "Defence against attacks.", d1.manualDiceRollBetween(3,10));
    public Stat initiative = new Stat("Initiative", "Combat readiness.", d1.manualDiceRollBetween(3,10));


    // How to increase this?
    // Like RS - doing activities which take time? Rolling successful dexterity checks? +Dexterity XP for failing them?
    public Stat dexterity= new Stat("Dexterity", "Movement capability.", d1.manualDiceRollBetween(3,10));

    // How to increase Intelligence?
    // Reading? Experience? Problem-solving outcomes for tasks?
    public Stat intelligence= new Stat("Intelligence", "Problem-solving prowess.", d1.manualDiceRollBetween(3,10));

    // How to increase Faith?
    // This will affect the Scripture a player can use, anything else?
    // Reading, performing actions which don't directly benefit you, voluntarily taking affliction from someone else's pain?
    public Stat faith = new Stat("Faith", "Devotion in service to ideals.", d1.manualDiceRollBetween(3,10));

    /**
         Body parts
                        */
    private Weapon main_hand = fists;               // Main hand can only be Weapons
    private Item off_hand = null;                   // This can either be a Weapon or a Shield (Item main class as standard)
    private Armour chest = null;                    // Null if no Armour is equipped
    private Armour head = null;                     // Null if no Armour is equipped


    // Need to work out the mechanics of this, after the boolean value
    private boolean died = false;

    /**
         Status Effects
                            */
    /*      Negative        */
    public StatusEffect bleed = new StatusEffect("Bleed", StatusEffectType.MINUS_PROGRESSIVE, health, (int) ((int)maxHealth.getStat_level()*0.05), 3);
    public StatusEffect confuse = new StatusEffect("Confuse", StatusEffectType.STATIC_MINUS, intelligence, 5, 3);
    public StatusEffect smite_effect = new StatusEffect("Smite", StatusEffectType.DISABLE, faith, 0, 4);
    public StatusEffect cripple = new StatusEffect("Cripple", StatusEffectType.STATIC_MINUS, dexterity, 5, 2);
    public StatusEffect singe = new StatusEffect("Singe", StatusEffectType.MINUS_PROGRESSIVE, health, 1, 2);
    public StatusEffect freeze = new StatusEffect("Freeze", StatusEffectType.MINUS_PROGRESSIVE, health, 1, 2);
    public StatusEffect contaminate = new StatusEffect("Contaminate", StatusEffectType.MINUS_PROGRESSIVE, health, 1, 2);

    // -- From Alchemy
    public StatusEffect menial_p = new StatusEffect("Menial Poison", StatusEffectType.MINUS_PROGRESSIVE, health, 3, 3);
    public StatusEffect vigorous_p = new StatusEffect("Vigorous Poison",StatusEffectType.MINUS_PROGRESSIVE,health,4,5);
    public StatusEffect weed_brain = new StatusEffect("Weed-Brain Draught",StatusEffectType.STATIC_MINUS,intelligence,4,4);
    public StatusEffect c_foot_con = new StatusEffect("Club-Foot Concoction",StatusEffectType.STATIC_MINUS,dexterity,4,5);
    public StatusEffect d_frailty = new StatusEffect("Draught of Frailty",StatusEffectType.STATIC_MINUS,defence,4,3);
    public StatusEffect p_dim_size = new StatusEffect("Potion of Diminutive Size",StatusEffectType.STATIC_MINUS,strength,4,3);

    /*      Positive        */

    // -- From Alchemy
    public StatusEffect discernment_e = new StatusEffect("Discernment Essence",StatusEffectType.STATIC_PLUS,intelligence,4,5);
    public StatusEffect f_foot_elixir = new StatusEffect("Fleet-Foot Elixir",StatusEffectType.STATIC_PLUS,dexterity,5,5);
    public StatusEffect p_thick_skin = new StatusEffect("Potion of Thick Skin", StatusEffectType.STATIC_PLUS,defence,3,3);
    public StatusEffect e_might = new StatusEffect("Elixir of Might",StatusEffectType.STATIC_PLUS,strength,4,3);



    /**
            Abilities
                            */
    Ability punch = new Ability("Punch", strength, 1, true, false, AttackType.UNARMED, null);
    Ability slash = new Ability("Slash", attack, 1, true, false, AttackType.SLASH, null);
    // Slash - [Damage]
    Ability stab = new Ability("Stab", attack, 1, true, false, AttackType.STAB, null);
    // Stab - [Damage]

    Ability crush = new Ability("Crush", strength, 1, true, false, AttackType.CRUSH, null);
    // Crush - [Damage]

    Ability pirouette_strike = new Ability("Pirouette Strike", dexterity, 6, true, false, AttackType.SLASH , null);
    // Pirouette - [Damage] + [dexterity roll / 2] + [1]

    Ability maul = new Ability("Maul", strength, 5, true, false, AttackType.CRUSH, this.cripple);
    // Maul - [Damage] + [strength roll / 2] + [1]

    Ability lunging_stab = new Ability("Lunging Stab", dexterity, 4, true, false, AttackType.STAB, null);
    // Lunging Stab - [Damage] + [dexterity roll / 3] + [3]

    Ability steadfast_block = new Ability("Steadfast Block", defence, 4, false, true,AttackType.NA, stat_buff(defence, 4, 1));
    // Steadfast Block - [Defence += 4] for [1] turn

    Ability shield_bash = new Ability("Shield Bash", defence, 5, false, true,AttackType.SHIELD, stat_debuff(defence,3, 2));
    // Shield Bash - [defence roll / 2] + [3] > opponent defence -= 3 for 2 turns

    Ability combat_profile = new Ability("Combat Profile", intelligence, 6, false, false, AttackType.NA, null);
    // Combat Profile - reveal opponents resistance

    Ability spinning_kick = new Ability("Spinning Kick", dexterity, 7, false, false, AttackType.NA, stat_debuff(defence,2, 2));
    // Spinning Kick - [Damage] + [dexterity roll / 3] + [6] > opponent defence -= 2 for 2 turns

    Ability haymaker = new Ability("Haymaker", strength, 6, true, false, AttackType.UNARMED, stat_debuff(defence,4, 2));
    // Haymaker - [Damage] + [strength roll / 2] + [2] > opponent defence -= 4 for 2 turns

    Ability smite = new Ability("Smite", strength, 7, 7, true, false, AttackType.CRUSH, this.smite_effect, Alignment.GOD, null);
    // Smite - [Damage] + [strength roll / 2] + [6] > opponent religion disabled for 4 turns

    Ability hermes_rush = new Ability("Hermes Rush", dexterity, 8, 6, true, false, AttackType.STAB, null, Alignment.GREEKS, null);
    // Hermes Rush - [Damage] + [dexterity roll / 2] + [4] + [faith roll / 2 + 3]

    Ability upward_slash = new Ability("Upward Slash", strength, 7, true, false, AttackType.SLASH, this.bleed);
    // Upward Slash - [Damage] + [strength roll / 2] + [7]
    // 40% chance to inflict Bleed


    /**
     * Constructor
     * @param name - Character name
     * @param type - Character race -> Will have an effect on properties at some point
     */
    public Character(String name, String type) {
        this.name = name;
        this.type = type;
        inventory = null;
        alchemy = new Alchemy();
        equipped_weapon = fists;
        active_scripture = null;
        stats.add(attack);
        stats.add(defence);
        stats.add(strength);
        stats.add(initiative);
        stats.add(intelligence);
        stats.add(level);
        stats.add(dexterity);
        stats.add(faith);
        stats.add(maxHealth);
        stats.add(health);
        this.setLevel(calculate_level());
        init_abilities_array();
    }

    public int getPosition_x() { return position_x; }

    public int getPosition_y() { return position_y; }

    public Stat getDefence() {
        return defence;
    }

    public Stat getAttack() {
        return attack;
    }

    public Stat getStrength() {
        return strength;
    }

    public Stat getMaxHealth() { return maxHealth; }

    public Stat getHealth() { return health; }

    public Stat getInitiative() { return initiative; }

    public Stat getDexterity() { return dexterity; }

    public Stat getIntelligence() { return intelligence; }

    public Stat getFaith() { return faith; }

    public Stat getLevel() { return level; }

    public String getName() { return name; }

    public String getType() { return type; }

    public String display_level(Stat stat) {
        if (stat.get_temp_stat_increase()) {
            return IO.T_G + stat.getStat_level() + IO.T_RS;
        } else if (stat.get_temp_stat_decrease()) {
            return IO.T_R + stat.getStat_level() + IO.T_RS;
        } else if (stat.get_temp_stat_increase() && stat.get_temp_stat_decrease()){
            return IO.T_BL + stat.getStat_level() + IO.T_RS;
        } else {
            return Integer.toString(stat.getStat_level());
        }
    }

    public void setDefence(int defence){ this.defence.setStat_level(defence); }

    public void setAttack(int attack){ this.attack.setStat_level(attack); }

    public void setStrength(int strength){ this.strength.setStat_level(strength); }

    public void setMaxHealth(int maxHealth) { this.maxHealth.setStat_level(maxHealth); }

    public void setHealth(int health) { this.health.setStat_level(health);}

    public void setInitiative(int initiative) {this.initiative.setStat_level(initiative);  }

    public void setDexterity(int dexterity) { this.dexterity.setStat_level(dexterity); }

    public void setIntelligence(int intelligence) { this.intelligence.setStat_level(intelligence); }

    public void unequip_weapon() {
        this.equipped_weapon = fists;
    }

    public void unequip_head_armour() {
        this.head = null;
    }

    public void equipHeadArmour(Armour head_armour) {
        this.head = head_armour;
    }

    public boolean isEquipped(Item item) {
        if (item == chest || item == head || item == equipped_weapon || item == off_hand) {
            return true;
        }
        return false;
    }

    public void unequip_shield() {  this.off_hand = null;   }

    public void unequip_chest_armour() {
        this.chest = null;
    }

    public void equipChestArmour(Armour chest_armour) {
        this.chest = chest_armour;
    }

    public void equip_shield(Shield shield) { this.off_hand = shield;}

    public void setEquipped_weapon(Weapon weapon) {
        this.equipped_weapon = weapon;
    }

    public void setLevel(int level) { this.level.setStat_level(level); }

    public Weapon getEquipped_weapon() { return equipped_weapon; }

    public Item getOff_hand() { return off_hand; }

    public int calculate_level() {
        int total = 0;
        for (Stat s: stats) {
            if (!s.getName().toLowerCase().equals("health")) {
                total += s.getStat_level();
            }
        }
        return total / 10;
    }

    public void set_off_hand(Item item) { this.off_hand = item; }

    public void set_main_hand(Weapon weapon) { this.main_hand = weapon; }

    public void setPosition(int x, int y) { this.position_x=x; this.position_y=y; }

    public void increaseLevel(int amount) { this.level.increaseStatLevel(amount); }

    public void setType(String type) { this.type = type; }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * This will initialise the inventory with option for no. of slots
     */
    public void setInventory(int slots) {
        inventory = new Inventory(slots);
    }

    public Inventory getInventory() { return inventory; }

    public void display_inventory() { inventory.display_inventory(); }

    public void loseHealth(int amount) {
        this.health.decreaseStatLevel(amount);
        if (this.health.getStat_level() <= 0) {
            this.health.setStat_level(0);
            this.died = true;
            System.out.println(this.getName() + " has died!");
        }
    }

    public Armour getChestArmour() {
        return chest;
    }

    public Armour getHeadArmour() {
        return head;
    }

    /**
     * Add health, to a maximum value of maxHealth
     * @param amount
     */
    public void gainHealth(int amount) {
        this.health.increaseStatLevel(amount);
        if (this.health.getStat_level() > this.getMaxHealth().getStat_level()) {
            this.health.setStat_level(this.maxHealth.getStat_level());
        }
    }

    /**
     * Consume a Formula as long as it is not an Item Formula
     *
     * Will return false if Formula is item formula
     *
     * @param formula - Formula to be consumed
     * @return - boolean
     */
    public boolean consume_formula(Formula formula) {
        if (!formula.isItem_formula()) {
            System.out.println("Throwing your head back, you hastily ingest the " +
                    formula.getName() +
                    ", contorting your expression at the taste.");
            add_status_effect(get_effect_from_alchemy(formula));
            inventory.replace_with(formula.getContainer(), formula);
            return true;
        }
        return false;
    }

    public boolean is_equipped(Item i) {
        return head.equals(i) || chest.equals(i) || main_hand.equals(i) || off_hand.equals(i);
    }

    /**
     * Apply Formula to Weapon or Armour item
     *
     * If the item already has Formula applied, method will return false
     *
     * @param formula - Formula to be applied
     * @param item - Item to have Formula applied to
     * @return - boolean
     */
    public boolean apply_formula(Formula formula, Item item) {

        if (item instanceof Weapon) {
            if (((Weapon) item).getCurrent_effect() == null) {
                System.out.println("Carefully, you apply the " +
                        formula.getName() +
                        " to the aged metal of the " +
                        item.getName() +
                        ".");
                ((Weapon) item).add_effect(formula);
                inventory.replace_with(formula.getContainer(), formula);
                return true;
            }
        } else if (item instanceof Armour) {
            if (((Armour) item).getCurrent_effect() == null) {
                System.out.println("You lavish the " +
                        formula.getName() +
                        " into the crevasses of the " +
                        item.getName() +
                        " with no discrimination.");
                ((Armour) item).add_effect(formula);
                inventory.replace_with(formula.getContainer(), formula);
                return true;
            }
        }
        return false;

    }

    public HashMap<StatusEffect, Integer> getActive_status_effects() {
        return active_status_effects;
    }

    /**
     * Checks if the weapon has a Formula applied to it
     * @param w
     * @return - Boolean
     */
    public boolean weapon_has_formula(Weapon w) {
        if (w.getCurrent_effect() != null) {
            return true;
        }
        return false;
    }
    /**
     * When a weapon attacks, pass the Formula from it through here to get the StatusEffect it
     *
     * will inflict on the opponent from the Formula
     *
     * @param f - Weapon Formula
     * @return StatusEffect/null
     */
    public StatusEffect get_effect_from_alchemy(Formula f) {

        if (f.getName().equals("Elixir of Might")) {
            return e_might;
        } else if (f.getName().equals("Draught of Frailty")) {
            return d_frailty;
        } else if (f.getName().equals("Menial Poison")) {
            return menial_p;
        } else if (f.getName().equals("Vigorous Poison")) {
            return vigorous_p;
        } else if (f.getName().equals("Discernment Essence")) {
            return discernment_e;
        } else if (f.getName().equals("Fleet-Foot Elixir")) {
            return f_foot_elixir;
        } else if (f.getName().equals("Club-Foot Concoction")) {
            return c_foot_con;
        } else if (f.getName().equals("Potion of Thick-Skin")) {
            return p_thick_skin;
        } else if (f.getName().equals("Potion of Diminutive Size")) {
            return p_dim_size;
        } else if (f.getName().equals("Weed-Brain Concoction")) {
            return weed_brain;
        }
        return null;
    }

    /**
     * Add a status effect to HashMap
     *
     * If status effect already exists, replace old value with new value i.e. restart the amount of turns
     *
     * @param statusEffect
     */
    public void add_status_effect(StatusEffect statusEffect) {
        if (active_status_effects.containsKey(statusEffect)) {
            for (Map.Entry<StatusEffect, Integer> set : active_status_effects.entrySet()) {
                if (set.getKey().equals(statusEffect)) {
                    active_status_effects.replace(set.getKey(), set.getValue(), statusEffect.getNum_of_turns_exists());
                }
            }
        } else {
            active_status_effects.put(statusEffect, statusEffect.getNum_of_turns_exists());
        }

        if (statusEffect.getType() == StatusEffectType.STATIC_PLUS) {               // Set level to this for x turns
            process_static_status_effects(statusEffect);
            System.out.println(this.name + " has increased their "+ statusEffect.getStat_affected().getName() + " by "
                    + statusEffect.getAmount_per_turn()
                    + " points due to " +IO.T_P + statusEffect.getName() +IO.T_RS + "."
            );
        } else if (statusEffect.getType() == StatusEffectType.STATIC_MINUS) {       // Set level to this for x turns
            process_static_status_effects(statusEffect);
            System.out.println(this.name + " has had their "+ statusEffect.getStat_affected().getName() + " reduced by "
                    + statusEffect.getAmount_per_turn()
                    + " points due to " +IO.T_R + statusEffect.getName() +IO.T_RS + "."
            );
        }
    }

    /**
     * Decreases all active status effects values by 1
     * If value == 0, remove the status effects
     *
     * This is called every combat loop to decrease each effect as combat progresses
     */
    public void decrease_active_status_effects() {
        if (!active_status_effects.isEmpty()) {
            for (Map.Entry<StatusEffect, Integer> set : active_status_effects.entrySet()) {
                process_status_effects(set.getKey());                                    // Process the status effect, unless it's static
                set.setValue(set.getValue() - 1);                                        // Reduce the num of turns by 1
                if (set.getValue() == 0) {                                               // If the num of turns = 0, remove status effect
                    if (set.getKey().getType() == StatusEffectType.STATIC_MINUS
                     || set.getKey().getType() == StatusEffectType.STATIC_PLUS) {        // Process static status effects
                        reset_stat_static_effect(set.getKey());                          // Reset affected stat
                    }
                    System.out.println("    - " + this.name + "'s " +IO.T_R + set.getKey().getName() +IO.T_RS + " has expired.");
                    active_status_effects.remove(set.getKey(), set.getValue());          // Remove progressive effects

                }
            }
        }
    }

    /**
     * This is called during 'add_status_effect' so that the value is only added ONCE.
     *
     * Does the Stat alter for STATIC status effects
     *
     * @param statusEffect
     */
    public void process_static_status_effects(StatusEffect statusEffect) {
        for (Stat s: this.stats) {
            if (s.getName().toLowerCase()
                    .equals(statusEffect.getStat_affected()
                            .getName().toLowerCase())) {
                if (statusEffect.getType() == StatusEffectType.STATIC_MINUS) {
                    s.temporary_stat_decrease(statusEffect.getAmount_per_turn());           // If it's a static minus, add the value back on to the stat
                } else {
                    s.temporary_stat_increase(statusEffect.getAmount_per_turn());                 // Else, subtract from the stat to get to the original value
                }
            }
        }
    }

    /**
     * After STATIC StatusEffect expires, reset the stat to the original value
     *
     * @param statusEffect
     */
    public void reset_stat_static_effect(StatusEffect statusEffect) {
        for (Stat s: this.stats) {
            if (s.getName().toLowerCase()
                    .equals(statusEffect.getStat_affected()
                            .getName().toLowerCase())) {
                if (statusEffect.getType() == StatusEffectType.STATIC_MINUS) {
                    s.increaseStatLevel(statusEffect.getAmount_per_turn());                 // If it's a static minus, add the value back on to the stat
                    s.remove_temp_stat_decrease();
                } else {
                    s.decreaseStatLevel(statusEffect.getAmount_per_turn());                 // Else, subtract from the stat to get to the original value
                    s.remove_temp_stat_increase();
                }
            }
        }
    }

    /**
     * Processes each status effect << Called by decrease_active_status_effects() every turn
     *
     * @param statusEffect
     */
    public void process_status_effects(StatusEffect statusEffect) {
        for (Stat s: this.stats) {
            if (s.getName().toLowerCase().
                    equals(statusEffect.getStat_affected()
                            .getName().toLowerCase())) {

                if (statusEffect.getType() == StatusEffectType.MINUS_PROGRESSIVE) {
                    s.decreaseStatLevel(statusEffect.getAmount_per_turn());                    // Minus [amount] each turn
                    System.out.println(this.name + " takes "
                            + statusEffect.getAmount_per_turn()
                            + " damage due to " + statusEffect.getName() + "."
                    );

                } else if (statusEffect.getType() == StatusEffectType.PLUS_PROGRESSIVE) {
                    s.increaseStatLevel(statusEffect.getAmount_per_turn());                    // Add [amount] each turn
                    System.out.println(this.name + " increases " + statusEffect.getStat_affected().getName() + " by "
                            + statusEffect.getAmount_per_turn()
                            + " due to " + statusEffect.getName() +"."
                    );
                }

            }
        }
    }

    /**
     * Creates custom StatusEffect for stat debuff
     *
     * Stat is reduced to [amount] for [num_turns] turns
     *
     * @param amount
     * @param num_turns
     * @return StatusEffect
     */
    public StatusEffect stat_debuff(Stat stat, int amount, int num_turns) {
        StatusEffect stat_debuff = new StatusEffect(stat.getName() + " Debuff", StatusEffectType.STATIC_MINUS, stat, amount, num_turns);
        return stat_debuff;
    }

    /**
     * Creates custom StatusEffect for stat debuff
     *
     * Stat is increased to [amount] for [num_turns] turns
     *
     * @param amount
     * @param num_turns
     * @return StatusEffect
     */
    public StatusEffect stat_buff(Stat stat, int amount, int num_turns) {
        StatusEffect stat_buff = new StatusEffect(stat.getName() + " Buff", StatusEffectType.STATIC_PLUS, stat, amount, num_turns);
        return stat_buff;
    }

    /**
     * Returns status of 'died' variable
     *
     * @return boolean
     */
    public boolean hasDied() {
        return health.getStat_level() == 0;
    }

    /**
     * Initialises ability descriptions and adds them to the abilities HashMap
     *
     */
    public void init_abilities_array() {

        slash.setDescription("Swipe your blade across your enemy.");
        stab.setDescription("Extend your weapon point into your foe.");
        crush.setDescription("Swing your weapon from up high, down upon your enemy!");
        pirouette_strike.setDescription("Deft spinning strike, slashing at a unique angle.");
        maul.setDescription("Bring your weapon down upon your foe!");
        lunging_stab.setDescription("Exert your frame, extending your lead leg, dipping down and thrusting your blade into your opponents abdomen.");
        shield_bash.setDescription("Thrust your shield into your opponent, dazing them momentarily.");
        combat_profile.setDescription("Analyse your foe's weaknesses and reveal them.");
        steadfast_block.setDescription("Assume a defensive position, guarding against your opponents next turn.");
        spinning_kick.setDescription("Unleash an impressive spinning kick, knocking your opponent back.");
        haymaker.setDescription("Throw a hook at your opponent's head, stunning them for a short time.");
        smite.setDescription("Crush your enemy and paralyze their faith for 3 turns.");
        hermes_rush.setDescription("Charge forth with the swiftness of Hermes and " +
                "cast your sword into your foe!");
        upward_slash.setDescription("Drag your sword from stem to stern with immense force.");


        abilities.put(punch, false);
        abilities.put(slash, false);
        abilities.put(stab, false);
        abilities.put(crush, false);
        abilities.put(pirouette_strike, false);
        abilities.put(spinning_kick, false);
        abilities.put(smite, false);
        abilities.put(haymaker, false);
        abilities.put(upward_slash, false);
        abilities.put(combat_profile, false);
        abilities.put(hermes_rush, false);
        abilities.put(steadfast_block, false);
        abilities.put(lunging_stab, false);
        abilities.put(maul, false);
        abilities.put(shield_bash, false);

        // Loop through Abilities and set them to True/False depending on player stat levels.
        for (Map.Entry<Ability, Boolean> set : abilities.entrySet()) {
            int player_stat_level = set.getKey().getStat().getStat_level();
            int level_req = set.getKey().getStat_requirement();
            if (player_stat_level >= level_req) {
                set.setValue(true);
            }
        }

    }

    /**
     * Displays this characters resistances during combat
     *
     */
    public void show_character_resistances() {
        System.out.println("Fire resistance      | " + this.fire_resistance + "%");
        System.out.println("Frost resistance     | " + this.frost_resistance + "%");
        System.out.println("Poison resistance    | " + this.poison_resistance + "%");
        System.out.println("Spiritual resistance | " + this.spiritual_resistance + "%");
        System.out.println("Bleed resistance     | " + this.bleed_resistance + "%");
    }

    /**
     * Checks all Abilities against Character Stats and returns those that the Character can use
     *
     * Returned in HashMap format <Int, Ability>
     *     This is so that it can be displayed in a combat menu as:
     *     (int)1. (ability)Pirouette Strike
     *     (int)2. (ability)Upward Slash
     *     ...etc ..
     *
     * @return HashMap<Integer, Ability>
     */
    public HashMap<Integer, Ability> compile_active_abilities_and_return() {

        active_abilities.clear();

        int i = 1;
        for (Map.Entry<Ability, Boolean> set : abilities.entrySet()) {
            if (meet_ability_req(set.getKey(), this)) {
                active_abilities.put(i, set.getKey());
                i++;
            }
        }
        return active_abilities;
    }

    public boolean isAbility(int choice) {
        if (choice > 0 && choice <= active_abilities.size()) {
            return true;
        }
        return false;
    }

    /**
     * Display all active abilities to the console in format:
     * 1. Slash
     * 2. Crush
     * 3. ...etc
     *
     */
    public void show_player_abilities_in_battle() {
        compile_active_abilities_and_return();
        for (Map.Entry<Integer, Ability> set : active_abilities.entrySet()) {
            System.out.println("> " + IO.TB_P+"["+set.getKey()+"]"+IO.T_RS+" " + set.getValue().getName());
        }
    }

    /**
     * Finds the given Ability Stat in Character's stats and checks if Character meet's the requirement to use it
     *
     * @param ability - Ability to check
     * @param character - Character
     * @return true/false
     */
    public boolean meet_ability_req(Ability ability, Character character) {
        for (Stat s: character.stats) {
            if (s.equals(ability.getStat())) {

                /* Does character stat level match? */
                if (s.getStat_level() >= ability.getStat_requirement())     {

                    /* Is the attack_type an attack? */
                    if (ability.getWeapon_type() != AttackType.NA)     {

                        /* Does the weapon attack style match? */
                        if (character.getEquipped_weapon().getAttack_style() == ability.getWeapon_type()) {
                                return true;

                        /* If Shield ATTACK ability, does character have a shield? */
                        } else if (ability.isShield() && character.off_hand instanceof Shield) {
                            return true;
                        } else  {
                            return false;
                        }

                        /* If Shield DEFENCE ability, does character have a shield? */
                    } else if (ability.isShield() && character.off_hand instanceof Shield){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Return an Armour item which is levelled to the Character
     *
     * @return Armour
     */
    public Armour return_levelled_armour_item() {
        Armour armour = null;
        int max_defence_rating = (int) Math.floor(this.getLevel().getStat_level()/10+5);
        do {
            armour = db_.return_random_armour_item();
        } while (armour.getDefence_rating() > max_defence_rating);
        return armour;
    }



    /**
     * Called during attack function
     *
     * This will calculate how much damage the defender deflects and returns final number
     *
     * @param initial_damage
     * @param defender
     * @return int - final damage
     */
    public int return_damage_after_armour_defense(int initial_damage, Character defender) {
        int damage = initial_damage;

        if (defender.getChestArmour() != null && defender.getChestArmour().getArmour_durability() > 0) {

            if (defender.getChestArmour().getDamage_absorption_modifier() != 0.00) {

                int armour_absorption = (int) Math.floor((damage * defender.getChestArmour().getDamage_absorption_modifier()) - damage);

                defender.getChestArmour().damage_armour(armour_absorption);

                if (armour_absorption > 0) {
                    System.out.println(defender.getName() + "'s " + defender.getChestArmour().getName() + " deflects " + IO.T_P + armour_absorption + IO.T_RS + " points of damage.");
                }
                if (defender.getChestArmour().getArmour_durability() <= 0) {
                    System.out.println(defender.getName() + "'s " + defender.getChestArmour().getName() + " has been broken!");
                }

                damage = damage - armour_absorption;
            }
        }


        if (defender.getHeadArmour() != null && defender.getHeadArmour().getArmour_durability() > 0) {

            if (defender.getHeadArmour().getDamage_absorption_modifier() != 0.00) {

                int head_armour_absorption = (int) Math.floor((damage * defender.getHeadArmour().getDamage_absorption_modifier()) - damage);

                defender.getHeadArmour().damage_armour(head_armour_absorption);

                if (head_armour_absorption > 0) {
                    System.out.println(defender.getName() + "'s " + defender.getHeadArmour().getName() + " deflects " + IO.T_P + head_armour_absorption + IO.T_RS + " points of damage.");
                }
                if (defender.getHeadArmour().getArmour_durability() <= 0) {
                    System.out.println(defender.getName() + "'s " + defender.getHeadArmour().getName() + " has been broken!");
                }

                damage = damage - head_armour_absorption;
            }

        }

        return damage;
    }

    /**
     * If one of the characters gains a status effect, prints info to console and adds status effect to them
     *
     * @param statusEffect
     * @param attacker
     * @param defender
     */
    public void attack_status_effects_processing(StatusEffect statusEffect, Character attacker, Character defender) {
        if (statusEffect.getType().equals(StatusEffectType.STATIC_MINUS) || statusEffect.getType().equals(StatusEffectType.MINUS_PROGRESSIVE)) {
            System.out.println(attacker.getName()
                    +" inflicts "
                    + statusEffect.getName()
                    + " on "
                    + defender.getName()
                    +" for "
                    + statusEffect.getNum_of_turns_exists()
                    + " turns.");
            defender.add_status_effect(statusEffect);
        } else if (statusEffect.getType().equals(StatusEffectType.STATIC_PLUS) || statusEffect.getType().equals(StatusEffectType.PLUS_PROGRESSIVE)) {
            System.out.println(attacker.getName()
                    + " increases their "
                    + statusEffect.getStat_affected().getName()
                    + " for "
                    + statusEffect.getNum_of_turns_exists()
                    + " turns.");
            attacker.add_status_effect(statusEffect);
        }
    }

    /**
     * Returns any Formula applied to the Weapon, else returns null
     *
     * @return Formula
     */
    public Formula get_weapon_formula() {
        if (this.equipped_weapon.getCurrent_effect() != null) {
            return this.equipped_weapon.getCurrent_effect();
        }
        return null;
    }


    public String toString() {
        return  "              | " + name + '\n' +
                "---------------------------" + '\n' +
                "Attack        | " + display_level(getAttack()) + " |" + '\n' +
                "Strength      | " + display_level(getStrength()) + " |" + '\n' +
                "Defence       | " + display_level(getDefence()) + " |" + '\n' +
                "Dexterity     | " + display_level(getDexterity()) + " |" + '\n' +
                "Intelligence  | " + display_level(getIntelligence()) + " |" + '\n' +
                "Faith         | " + display_level(getFaith()) + " |" + '\n' +
                "Initiative    | " + display_level(getInitiative()) + " |" + '\n' +
                "Max Health    | " + display_level(getMaxHealth()) + " |" + '\n' +
                "---------------------------" + '\n' +
                "Level:        | " + getLevel().getStat_level();
    }

    // Interface methods
    @Override
    public void attack(Character attacker, Weapon weapon, Character defender, Ability attack_type) {
        int damage = 0;                     // Base damage
        int bonus_damage = 0;               // Weapon specific bonus damage
        double s_e_inflict_chance = 0.8;    // Chance to inflict Status Effect
        boolean attack = true;              // Is the combat move an attack?
        StatusEffect statusEffect = null;   // The Status Effect attempted to inflict(if applicable)
        Formula formula = null;             // Applied Weapon formula i.e. Poison on Sword

        if (attack_type.getWeapon_type() != AttackType.NA) {

            damage = weapon.getAttackDamage()
                    + attacker.roll_stat(attacker.getStrength().getStat_level()/2)
                    - defender.roll_stat(defender.getDefence().getStat_level()/2);

        }

        if ( attack_type.getName().toLowerCase().equals("punch") )
        {
            System.out.println(attacker.getName() + " throws a punch !");
        }
        if ( attack_type.getName().toLowerCase().equals("slash") )
        {
            System.out.println(attacker.getName() + " slashes their blade towards " + defender.getName() + "!");
        }
        else if ( attack_type.getName().toLowerCase().equals("stab") )
        {
            System.out.println(attacker.getName() + " stabs at " + defender.getName() + "!");
        }
        else if ( attack_type.getName().toLowerCase().equals("crush") )
        {
            System.out.println(attacker.getName() + " attempts to crush " + defender.getName() + "!");
        }
        else if ( attack_type.getName().toLowerCase().equals("pirouette strike") )
        {
            System.out.println(attacker.getName()+ " skillfully pirouettes into a slashing strike..");
            if (damage > 0)
            {
                bonus_damage = (int)(d1.manualDiceRoll(attacker.getDexterity().statRoll())/3 + 1);
                damage += bonus_damage;

                if (d1.chance_roll() < s_e_inflict_chance)
                {
                    statusEffect = pirouette_strike.getStatusEffect();
                }
            }
        }
        else if ( attack_type.getName().toLowerCase().equals("maul") )
        {
            System.out.println(attacker.getName() + " brings their "
                    + attacker.getEquipped_weapon().getName()
                    + " down upon " + defender.getName() + " in a crushing blow!");
            if (damage > 0)
            {
                bonus_damage = (int)(d1.manualDiceRoll(attacker.getStrength().statRoll())/3 + 1);
                damage += bonus_damage;

                if (d1.chance_roll() < s_e_inflict_chance)
                {
                    statusEffect = maul.getStatusEffect();
                }
            }
        }
        else if ( attack_type.getName().toLowerCase().equals("lunging stab") )
        {
            System.out.println(attacker.getName() + " quickly exerts their body and thrusts their blade" +
                    " towards the midriff of " + defender.getName()+ "!");
            if (damage > 0)
            {
                bonus_damage = (int)(d1.manualDiceRoll(attacker.getDexterity().statRoll())/4 + 3);
                damage += bonus_damage;

                if (d1.chance_roll() < s_e_inflict_chance)
                {
                    statusEffect = lunging_stab.getStatusEffect();
                }
            }
        }
        else if ( attack_type.getName().toLowerCase().equals("steadfast block") )
        {
            System.out.println(attacker.getName() + " assumes a defensive position, raising their shield " +
                    "in anticipation of an attack.");

            // No roll chance for steadfast_block
            statusEffect = steadfast_block.getStatusEffect();         // FOR 1 TURN
            attack = false;
        }
        else if ( attack_type.getName().toLowerCase().equals("spinning kick") )
        {
            System.out.println(attacker.getName() + " launches from their standing position and sends a swinging kick" +
                    " towards the head of " + defender.getName() + "!");

            if (damage > 0)
            {
                bonus_damage = (int)(d1.manualDiceRoll((attacker.getDexterity().statRoll()) /3) + (strength.getStat_level() / 3) + 1);
                damage += bonus_damage;

                if (d1.chance_roll() < s_e_inflict_chance)
                {
                    statusEffect = spinning_kick.getStatusEffect();
                }
            }

        }
        else if ( attack_type.getName().toLowerCase().equals("smite") )
        {
            System.out.println(attacker.getName() + " smites their opponent with " + attacker.getEquipped_weapon().getName() + "!");
            if (damage > 0)
            {
                bonus_damage = (int)(d1.manualDiceRoll((attacker.getStrength().statRoll()) / 4) + 6);
                damage += bonus_damage;

                if (d1.chance_roll() < s_e_inflict_chance)
                {
                    statusEffect = smite.getStatusEffect();
                }
            }

        }
        else if ( attack_type.getName().toLowerCase().equals("haymaker") )
        {
            System.out.println(attacker.getName() + " throws a haymaker towards " + defender.getName() + "!");

            if (damage > 0)
            {
                bonus_damage = (int)(d1.manualDiceRoll(attacker.getStrength().statRoll())/3 + 2);
                damage += bonus_damage;

                if (d1.chance_roll() < s_e_inflict_chance)
                {
                    statusEffect = haymaker.getStatusEffect();
                }
            }

        }
        else if ( attack_type.getName().toLowerCase().equals("shield bash") )
        {
            System.out.println(attacker.getName() + " bashes " + defender.getName() + " with their shield!");

            if (damage > 0)
            {
                bonus_damage = (int)(d1.manualDiceRoll(attacker.getDefence().statRoll())/3 + 2);
                if (d1.chance_roll() < s_e_inflict_chance)
                {
                    statusEffect = shield_bash.getStatusEffect();
                }
            }

        }
        else if ( attack_type.getName().toLowerCase().equals("upward slash") )
        {
            System.out.println(attacker.getName() + " angles their sword towards " + defender.getName() + " and slices upwards " +
                    "with great force!");

            if (damage > 0)
            {
                bonus_damage = (int)(d1.manualDiceRoll(attacker.getStrength().statRoll())/4 + 7);
                damage += bonus_damage;

                if (d1.chance_roll() < s_e_inflict_chance)
                {
                    statusEffect = upward_slash.getStatusEffect();
                }
            }

        }
        else if ( attack_type.getName().toLowerCase().equals("hermes rush") )
        {
            System.out.println(attacker.getName() + " darts forward with godly might and thrusts " +
                    "their " + attacker.getEquipped_weapon().getName() + " into " + defender.getName() + "!");

            if (damage > 0)
            {
                bonus_damage = (int)(d1.manualDiceRoll((attacker.getDexterity().statRoll()) / 3) + 4 + (faith.statRoll()) / 3);
                damage += bonus_damage;
                if (d1.chance_roll() < s_e_inflict_chance)
                {
                    statusEffect = hermes_rush.getStatusEffect();
                }
            }

        }
        else if ( attack_type.getName().toLowerCase().equals("combat profile") )
        {
            System.out.println(attacker.getName() + " analyses "+ defender.getName() + " and identifies" +
                    " their weaknesses.");

            // REVEAL DEFENDERS WEAKNESSES
            defender.show_character_resistances();
            attack = false;

        }

        /*  Stores value inside formula property of weapon  */
        formula = get_weapon_formula();

        /*  If armour is present and has > 0 durability, block some damage  */
        if (damage > 0) {
            damage = return_damage_after_armour_defense(damage, defender);
        }

        /* Only print this if attack was supposed to do damage
            i.e. not for steadfast block or combat profile  */
        if (attack) {
            if (damage <= 0) {

                damage = 0;

                System.out.println(attacker.getName() +
                        " does no damage to " + defender.getName()
                        );
            } else if (damage > defender.getHealth().getStat_level()) {

                damage = defender.getHealth().getStat_level();

                System.out.println(attacker.getName() +
                        " finishes " + defender.getName() +
                        " with " + attacker.getEquipped_weapon().getName() +
                        "!");
                defender.loseHealth(damage);

                return;     // Exit function here.

            } else {

                System.out.println(attacker.getName() +
                        " deals "
                        + damage + " damage to " +
                        defender.getName() + "!");
                defender.loseHealth(damage);

            }
        }
        if (statusEffect != null) {         // Process statusEffects, if attack rolled for it
            attack_status_effects_processing(statusEffect, attacker, defender);
        }

        /**
         * If weapon has Formula applied, apply it to defender and remove it from the Weapon
         */
        if (weapon_has_formula(this.equipped_weapon)) {

            if (formula != null) {
                attack_status_effects_processing(get_effect_from_alchemy(this.equipped_weapon.getCurrent_effect()), attacker, defender);
                System.out.println(attacker.getName() +
                        " applies " +
                        formula.getName() +
                        " to " +
                        defender.getName() +
                        "!");
                this.equipped_weapon.remove_current_effect(); // Remove formula from Weapon once used
            }
        }
    }

    @Override
    public int roll_stat(int stat) { return d1.manualDiceRoll(stat); }

    @Override
    public boolean flee() {
        int flee_chance = 0;
        int rand_num = d1.diceRoll();

        if (rand_num > 3) {
            return true;
        }
        return false;
    }
}
