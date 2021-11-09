package com.company;

/**
 * Combinations of certain IngredientType's will make specific potions/powders/salves etc..
 * +--------------------------------------------+-------------------------------------------+
 * |  Combination                               |       Result                              |
 * +--------------------------------------------+-------------------------------------------+
 * |  SPIRITUAL_HERB + MATERIAL                 =>      +Weapon Damage / -Weapon Damage     |
 * |                                            |                                           |
 * |  HEALING_HERB + CREATURE_PART              =>      +Health                             |
 * |                                            |                                           |
 * |  HEALING_HERB + FOOD                       =>      ++Health / Poison                   |
 * |                                            |                                           |
 * |  ENERGETIC_HERB + FOOD                     =>      +Dexterity / -Dexterity             |
 * |                                            |                                           |
 * |  UNIQUE_HERB + MATERIAL + CREATURE_PART    =>      Poison / -Health                    |
 * |                                            |                                           |
 * |  SPIRITUAL_HERB + MAGIC                    =>      +Intelligence / -Intelligence       |
 * |                                            |                                           |
 * |  SPIRITUAL_HERB + MATERIAL                 =>      +Defence / -Defence                 |
 * |                                            |                                           |
 * |  ENERGETIC_HERB + UNIQUE_HERB + MAGIC      =>      +Strength / -Strength               |
 * +--------------------------------------------+-------------------------------------------+
 *
 */
public enum IngredientType {
    CREATURE_PART,
    SPIRITUAL_HERB,
    HEALING_HERB,
    ENERGETIC_HERB,
    UNIQUE_HERB,
    FOOD,
    MATERIAL,
    MAGIC;
}
