package com.company;

import java.util.ArrayList;

/**
 * Faith works like Inventory, as it is an instance which is assigned to a living entity
 * *** Need to work out how to constrain access to each scripture so Players only have access to some/1/none of them
 */
public class Religion implements java.io.Serializable {

    private ArrayList<Scripture> scripture = new ArrayList<Scripture>();
    private Scripture active;
    private Alignment alignment;

    /**
    String name, String description, String deity_requirement,
    int faith_level_required, double stat_modifier,
    Stat stat_affected, String type_of_modifier
     */

    // +2 damage to foes with lighter faith
    private Scripture cain = new Scripture("Cain",
            "Spite foes whom are more benevolent than yourself.",
            "God",
            2,
            2);

    // +2 damage to foes with darker faith
    private Scripture abel = new Scripture("Abel"
            , "Drive malevolence from those seeking to promulgate its' alluring qualities.",
            "God",
            2,
            2);

    // All scripture has half of the intended effects on you
    private Scripture job = new Scripture("Job",
            "You will not crumble under the weight of spiritual bombardment.",
            "God",
            3,
            0);

    // Do +3 damage on the 1st strike in battle
    private Scripture adam = new Scripture("Adam",
            "In the beginning, there was increase damage",
            "God",
            4,
            3);

    // Do +2 damage against undead
    private Scripture hades = new Scripture("Hades",
            "",
            "Greek Gods",
            4,
            2);

    // +1 extra level for each level up in str, att + def
    private Scripture ares = new Scripture("Ares",
            "The son of Zeus and Hera - a tremendous lust for war",
            "Greek Gods",
            5,
            1);

    // Less chance for drops, but of the drops, have a better chance to get a valuable item
    // ---- WORK ON THIS
    private Scripture matthew = new Scripture("Matthew",
            "From those who have nothing, more will be taken, to those who have everything, more will be given",
            "God",
            6,
            0);

    public Religion() {
        scripture.add(cain);
        scripture.add(abel);
        scripture.add(job);
        scripture.add(hades);
        scripture.add(adam);
        scripture.add(matthew);
        active = null;
    }

    public void display_faith() {

    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public void activateScripture(Scripture scripture) {
        active = scripture;
    }

    public void deactivateScripture() {
        active = null;
    }

    public String get_alignment() {
        return alignment.toString();
    }

    public String toString() {
        return active.toString();
    }

    public Scripture getActive() { return active; }

    public Scripture getCain() {return cain;}

    public Scripture getAbel() {return abel;}

    public Scripture getJob() {return job;}

    public Scripture getAdam() {return adam;}

    public Scripture getHades() {return hades;}

    public Scripture getAres() {return ares;}

    public Scripture getMatthew() {return matthew;}
}
