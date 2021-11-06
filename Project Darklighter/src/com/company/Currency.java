package com.company;

public class Currency extends Item implements java.io.Serializable {

    /**
     * Currency's weight is determined by it's quantity 0.01:1 ratio
     * @param amount
     */
    public Currency(int amount) {
        super("Currency", false,new ItemType[]{});
        super.setQuantity(amount);
        super.setWeight(amount * 0.01);
        super.setType("Currency");
    }

    /**
     * Each gold piece counts as 1 quantity, also add weight for each gold piece
     * @param amount
     */
    public void add_gold(int amount) {
        this.increaseQuantity(amount);
        this.setWeight(getQuantity() * 0.01);
    }

    /**
     * If the amount decreasing by is more than the quantity the player currently has,
     *     // then display error message
     * @param amount
     */
    public void decrease_gold(int amount) {
        if (amount > getQuantity()) {
            System.out.println("You do not have enough gold!");
        } else {
            decreaseQuantity(amount);
            setWeight(getQuantity() * 0.01);
        }
    }
}
