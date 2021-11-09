package com.company;

/**
 * Implemented by Player and Enemy who both have a dynamic inventory
 */
public interface InventoryManagement {

    public boolean add_to_inventory(Item item);

    public boolean remove_from_inventory(Item item);
}
