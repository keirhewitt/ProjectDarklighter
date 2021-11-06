package com.company;

public class Encounter implements java.io.Serializable {

    private DB_ db_  = new DB_();
    private Dice d1 = new Dice();
    private Player player = null;
    private Enemy enemy = null;
    private Altar altar = null;
    private Item item = null;

    public Enemy generate_enemy(Player player, int room) {
        enemy = new Enemy(db_.generate_enemy_name(), "Human", player, room);
        return enemy;
    }

    public Item generate_valuable_item() {
        return db_.return_random_valuable_item();
    }

    public Item generate_random_healing_item() {
        return db_.return_random_healing_item();
    }

    public Weapon generate_random_weapon() { return db_.return_random_weapon(); }

    public Altar generate_random_altar() { return db_.return_random_altar(); }

    public Armour generate_random_armour() { return db_.return_random_armour_item(); }

    public Item generate_random_item() { return db_.generate_random_normal_item(); }

    public Item generate_food_item() { return db_.generate_food_item(); }

    public Altar getAltar() {
        return altar;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public Item getItem() {
        return item;
    }

    public Player getPlayer() {
        return player;
    }

    public void setAltar(Altar altar) {
        this.altar = altar;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

