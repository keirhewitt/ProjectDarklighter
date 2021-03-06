package com.company;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Dungeon will hold all of the rooms and determine which type of Room will be generated
 * This class will create the 'route' - which determines what Rooms will be placed in what order
 *
 * Dungeon example (initialised at beginning of program):
 *  - Maybe 3 standard rooms and then 1 alt room i.e. 3-1-3-1
 *  - Alt rooms could reveal more of the story?
 *     '+' = Standard Room
 *     'o' = alt room ?
 *
 *
 *
 */
public class Dungeon implements Serializable {

    private ArrayList<Room> dungeon = new ArrayList<>();
    private Dice d1 = new Dice();
    private Player player = null;
    private int size;

    public Dungeon(Player player) {
        this.player = player;
        generate_dungeon();
    }

    public void generate_dungeon() {
        if (d1.chance_roll() < 0.33) {
            create_small_dungeon();
            this.size=7;
        } else if (d1.chance_roll() < 0.66) {
            create_medium_dungeon();
            this.size=12;
        } else if (d1.chance_roll() < 1.01) {
            create_large_dungeon();
            this.size=20;
        }
    }

    public void dungeon_creation(int size) {
        for ( int i = 1; i <= size; i++ ) {
            if (i == size) {
                // boss room
                RoomBoss boss = new RoomBoss(player);
                dungeon.add(boss);
            }
            if (i % 3 == 0) {
                RoomRiver alt_room = new RoomRiver(player);
                dungeon.add(alt_room);
            } else {
                if (i == 1) {
                    Room room = new Room(player);
                    dungeon.add(room);
                }
                if (i > 1) {
                    if (d1.chance_roll() < 0.40 && (dungeon.get(i - 1) instanceof RoomHallway) == false) {
                        RoomHallway hallway = new RoomHallway(player);
                        dungeon.add(hallway);
                    } else {
                        Room room = new Room(player);
                        dungeon.add(room);
                    }
                } else {
                    Room room = new Room(player);
                    dungeon.add(room);
                }
            }
        }
    }

    public void create_small_dungeon() {
        dungeon_creation(7);
    }

    public void create_medium_dungeon() {
        dungeon_creation(12);
    }

    public void create_large_dungeon() {
        dungeon_creation(20);
    }

    public Room get_room(int room_num) {
        return dungeon.get(room_num-1);
    }

    public int get_size() { return size; }

    public ArrayList<Room> return_rooms_array() { return dungeon; }

}
