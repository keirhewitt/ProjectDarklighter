package com.company;

/**
 * Characters will have HashMap's of Debuffs <Debuff, Integer> => Debuff, Num of turns affected
 * These HashMaps will be called every combat loop and the Num of turns affected will be reduced each time
 */
public class StatusEffect {

    private String name;     // Minus, plus, static - What kind of logic happens to the stat affected
    private Stat stat_affected;
    private int amount_per_turn;
    private int num_of_turns_exists;
    private StatusEffectType type;

    public StatusEffect(String name, StatusEffectType type, Stat stat_affected, int amount_per_turn, int num_of_turns_exists) {
        this.name=name;
        this.type=type;
        this.stat_affected=stat_affected;
        this.amount_per_turn=amount_per_turn;
        this.num_of_turns_exists=num_of_turns_exists;
    }

    public int getNum_of_turns_exists() {
        return num_of_turns_exists;
    }

    public Stat getStat_affected() {
        return stat_affected;
    }

    public String getName() {
        return name;
    }

    public int getAmount_per_turn() {
        return amount_per_turn;
    }

    public StatusEffectType getType() {
        return type;
    }
}
