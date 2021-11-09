package com.company;

public class Scripture implements java.io.Serializable{

    private String name;
    private String description;
    private String deity_requirement;
    private Stat stat_affected;
    private String type_of_modifier;
    private final int faith_level_required;
    private int effect;
    private double stat_modifier;

    // For Scripture that has an effect
    public Scripture(String name, String description, String deity_requirement, int faith_level_required, int effect) {
        this.name = name;
        this.description = description;
        this.deity_requirement = deity_requirement;
        this.faith_level_required = faith_level_required;
        this.effect = effect;
    }

    // For Scripture that alters stats
    public Scripture(String name, String description, String deity_requirement,
                     int faith_level_required, double stat_modifier,
                     Stat stat_affected, String type_of_modifier) {
        this.name = name;
        this.description = description;
        this.deity_requirement = deity_requirement;
        this.faith_level_required = faith_level_required;
        this.stat_modifier = stat_modifier;
        this.stat_affected =stat_affected;
        this.type_of_modifier = type_of_modifier;
    }

    private String getName() { return name; }

    private String getDescription() { return description; }

    public String getDeity_requirement() { return deity_requirement; }

    public double getStat_modifier() { return stat_modifier; }

    public int getFaith_level_required() { return faith_level_required; }

    public int getEffect() { return effect; }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeity_requirement(String deity_requirement) {
        this.deity_requirement = deity_requirement;
    }

    public Stat getStat_affected() { return this.stat_affected; }

    public String getType_of_modifier() { return type_of_modifier; }

    public String toString() {
        return " +--------------------------------+"+'\n' +
                "|-" + getName() + '\n' +
                "|-" + getDescription() + '\n' +
                "|> Faith Required: " + getFaith_level_required() +'\n' +
                " +--------------------------------+"+'\n';
    }


}
