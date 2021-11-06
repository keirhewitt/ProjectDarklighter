package com.company;

public interface Combat {

    public void attack(Character attacker, Weapon weapon, Character defender, Ability attack_type);

    int roll_stat(int stat);

    public boolean flee();

}