package com.company;

/**
 * This class is to show how different Item types will interact
 *
 * i.e. If a Knife is made ItemType 'CUTTER', for example and a piece of Cloth is made ItemType 'SPLITS'
 *
 *  >   Then it will be easy to implement the code for when the knife is used on the cloth
 *
 *  Will be useful if these can be implemented in pairs
 *
 *  Item can have more than 1 type -> Paper can be: Split, Written on, Fuel (perhaps even absorbs)
 */
public enum ItemType {

    NA,           /*  ->   */             // <- Item does not have a practical use
    /* ------------------------------------------------------------------------------- */
    CUTTER,     /*  ->   */       SPLITS, // <- Alters into different items/lesser items, increase quantity
    SOAKS,     /*  ->   */      ABSORBS,  // <- Becomes heavier, absorbs properties: perhaps used for poisons etc. ?
    WRITABLE, /*  ->   */       WRITES,   // <- Store information that the game can then prompt the player with?
    FLAME,   /*  ->   */         FUEL,    // <- Destroy/Char the item
    HITS,   /*  ->   */       SHAPES,     // <- Something can be hit and reshaped, i.e. hammer to metal plate

    CONTAINER, POUCH, PAPYRUS;      // <- Containers (For crafting purposes [alchemy solutions])

    /**
     * ITEM IDEAS:

        Papyrus, Book, Knife, Charcoal, Torch, Rock, Jug, Wooden shavings, Oil, Backpack, Scrolls,

     */
}
