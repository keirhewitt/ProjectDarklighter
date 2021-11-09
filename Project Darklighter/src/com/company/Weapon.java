package com.company;

import java.text.Normalizer;

/**
 * Weapon derives from base class Item
 *
 * Specialized properties :
 *   - Max Damage
 *   - Attack Style
 *   - Applied Formula
 */
public class Weapon extends Item implements java.io.Serializable{

    private Dice d1 = new Dice();

    private String signifier = "(oil)";     // For when the weapon has a Formula applied to it

    private int max_damage;                 // Max hit value
    private AttackType attack_style;        // Slash,crush,stab etc...
    private Formula current_effect;         // Any formula applied to the weapon

    public Weapon(String name,
                  AttackType attack_style,
                  double weight,
                  int max_damage,
                  ItemType[] itemTypes) {
        super(name, false, itemTypes);
        super.setWeight(weight);
        super.setType("Weapon");
        this.max_damage=max_damage;
        this.attack_style=attack_style;
        super.setValue(calculate_weapon_value());
        current_effect = null;
    }

    // Returns weapon damage (used for attack) NOT for returning the max damage value
    // Returns value from 1 - max damage of weapon
    public int getAttackDamage() {
        return d1.manualDiceRoll(max_damage);
    }

    public AttackType getAttack_style() { return attack_style; }

    public int calculate_weapon_value () {
        return 3 * (max_damage + (int)(max_damage*1.2));
    }

    public int getMax_damage() { return max_damage; }



    /**
     * Adds a formula and processes its effects to the Weapon
     * @param f
     */
    public void add_effect(Formula f) {
        this.current_effect=f;
        if (current_effect.getOperator() == "*") {
            this.max_damage*=current_effect.getItem_mod();
        } else if (current_effect.getOperator() == "/") {
            if (this.max_damage > 0) {
                this.max_damage /= current_effect.getItem_mod();
            } else {
                this.max_damage=0;
            }
        } else if (current_effect.getOperator() == "+") {
            this.max_damage+=(int)current_effect.getItem_mod();
        }
        this.set_contains_formula();
    }

    public Formula getCurrent_effect() {
        return current_effect;
    }

    public void remove_current_effect() {
        this.current_effect=null;
        this.remove_formula();
    }

    public void setAttack_style(AttackType attack_style) {
        this.attack_style=attack_style;
    }

}
