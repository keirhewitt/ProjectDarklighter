package com.company;

public class Ingredient extends Item {

    public boolean poisonous;       // Whether player will take damage from consuming this
    public boolean known=false;     // Whether player knows what this does#
    public boolean pungent;         // If it emanates a strong smell - will prompt player from a distance
    public IngredientType type;     // What type of ingredient? (Enum)
    public String vague_desc;       // Vague description if player does not know Ingredient

    public Ingredient(String name, String description, double weight, int value, IngredientType type) {
        super(name, description, weight, value, false, 0, new ItemType[]{});
        super.setType("Ingredient");
        this.type=type;
    }

    public void setVague_desc(String desc) {this.vague_desc = desc;}
    public boolean isKnown() {
        return known;
    }

    public boolean isPoisonous() {
        return poisonous;
    }

    public boolean isPungent() {
        return pungent;
    }

    public IngredientType getIngredientType() {
        return type;
    }

    public void gain_knowledge() { this.known=true;}

    public String toString() {
        return getDescription();
    }
}
