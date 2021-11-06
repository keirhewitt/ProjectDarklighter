package com.company;

/**
 * Types of formula's and what they do
 *
 * > POISON damages health over time
 * > STAT affects stat once for certain length of time
 * > ITEM is applied to weapons,armour
 * > OTHER has miscellaneous uses not categorized by the above delineations
 */
public enum FormulaType {
    POISON,         // Formula's that act as poisons
    STAT,           // Formula's that affect stats
    ITEM,           // Formula's that are applied to items i.e. Weapon oils, Armour protection
    OTHER;          // Formula's that have other uses i.e. sight in darkness, grant unique abilities

}
