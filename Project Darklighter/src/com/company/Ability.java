package com.company;

/**
 * Abilities will be available to the player if they meet the stat requirement
 */
public class Ability {

    private Stat stat;                              // What Stat the Ability requires to use
    private int stat_requirement;                   // The above Stat requirement level to use Ability
    private int faith_requirement = 0;              // If faith based, faith level requirement to use Ability
    private String name;                            // Name of Ability
    private String description;                     // Description
    private AttackType weapon_type;                 // What kind of Weapon you need equipped to use Ability
    private boolean weapon;                         // If the Ability requires a Weapon
    private boolean shield;                         // If the Ability requires a Shield
    private Alignment god;                          // If faith based, what Alignment do you need
    private Scripture scripture;                    // If faith based, what Scripture do you need
    private StatusEffect statusEffect;              // If attack has chance to inflict status effect

    /**
     * Constructor for regular combat abilities
     * @param name
     * @param stat
     * @param stat_requirement
     * @param weapon
     * @param shield
     * @param weapon_type
     * @param statusEffect
     */
    public Ability(String name, Stat stat, int stat_requirement,
                   boolean weapon, boolean shield, AttackType weapon_type, StatusEffect statusEffect) {
        this.weapon=weapon;
        this.shield=shield;
        this.name=name;
        this.stat=stat;
        this.stat_requirement=stat_requirement;
        this.weapon_type=weapon_type;
        this.statusEffect=statusEffect;
    }

    /**
     * Constructor for faith based combat abilities
     * @param name
     * @param stat
     * @param stat_requirement
     * @param weapon
     * @param shield
     * @param weapon_type
     * @param god
     * @param scripture
     */
    public Ability(String name, Stat stat, int stat_requirement, int faith_requirement,
                   boolean weapon, boolean shield, AttackType weapon_type, StatusEffect statusEffect,
                   Alignment god, Scripture scripture) {
        this.weapon=weapon;
        this.shield=shield;
        this.name=name;
        this.stat=stat;
        this.faith_requirement=faith_requirement;
        this.stat_requirement=stat_requirement;
        this.weapon_type=weapon_type;
        this.statusEffect=statusEffect;
        this.god=god;
        this.scripture=scripture;
    }

    public StatusEffect getStatusEffect() { return statusEffect; }

    public int getStat_requirement() {
        return stat_requirement;
    }

    public Stat getStat() {
        return stat;
    }

    public boolean isShield() {
        return shield;
    }

    public boolean isWeapon() {
        return weapon;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public AttackType getWeapon_type() {
        return weapon_type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public void setStat_requirement(int stat_requirement) {
        this.stat_requirement = stat_requirement;
    }

    public void setWeapon_type(AttackType weapon_type) {
        this.weapon_type = weapon_type;
    }

    public void setStatusEffect(StatusEffect statusEffect) {
        this.statusEffect = statusEffect;
    }

    public void setScripture(Scripture scripture) {
        this.scripture = scripture;
    }
}

