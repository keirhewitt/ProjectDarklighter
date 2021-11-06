package com.company;

/**
 * An altar to a specific God, *MAY* have scripture which may be learned.
 */
public class Altar implements java.io.Serializable  {

    private Alignment god;
    private Scripture scripture;
    private String description;

    public Altar(String description, Alignment god, Scripture scripture) {
        this.description = description;
        this.god = god;
        this.scripture = scripture;
    }

    public String getAlignment() {
        return god.toString();
    }

    public String getDescription() {
        return description;
    }

    public Scripture getScripture() {
        return scripture;
    }

    public Alignment getGod() {
        return god;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAlignment(Alignment god) {
        this.god = god;
    }

    public void setScripture(Scripture scripture) {
        this.scripture = scripture;
    }

}

