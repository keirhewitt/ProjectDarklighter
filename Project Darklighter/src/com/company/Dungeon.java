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
        System.out.println("Dungeon size: " + size);
        set_doors_for_rooms();
    }

    public void generate_dungeon() {
        if (d1.chance_roll() < 0.33) {
            create_small_dungeon();
            this.size=7;
        } else if (d1.chance_roll() < 0.66) {
            create_medium_dungeon();
            this.size=14;
        } else if (d1.chance_roll() < 1.01) {
            create_large_dungeon();
            this.size=21;
        }
    }

    /* TEST FUNCTION
    public void test_rooms() {
        int index = 1;
        for (Room r: dungeon) {
            System.out.println(IO.T_IT+"Room "+index +"("+r.getType()+"):"+IO.T_IT_RS+"\n     Previous door: " +IO.T_R+ r.get_Previous_Door_TEST() +IO.T_RS+ " / Next Door: " +IO.T_G+ r.get_Next_Door_TEST()+IO.T_RS);
            System.out.println();
            index++;
        }
    }
     */

    public void dungeon_creation(int size) {
        for ( int i = 1; i <= size; i++ ) {
            if (i == size) {
                // boss room
                Room boss = new Room(player, this);
                //System.out.println(boss.getType());
                dungeon.add(boss);
            }
            else if (i % 4 == 0) {
                RoomRiver alt_room = new RoomRiver(player, this);
                //System.out.println(alt_room.getType());
                dungeon.add(alt_room);
            } else {
                if (i == 1) {
                    Room room = new Room(player, this);
                    //System.out.println(room.getType());
                    dungeon.add(room);
                }
                else if (i > 1) {
                    if (d1.chance_roll() < 0.10 && (dungeon.get(i - 2) instanceof RoomHallway) == false) {
                        RoomHallway hallway = new RoomHallway(player, this);
                        //System.out.println(hallway.getType());
                        dungeon.add(hallway);
                    } else {
                        Room room = new Room(player, this);
                        //System.out.println(room.getType());
                        dungeon.add(room);
                    }
                }
            }
        }
    }

    /**
     * Sets the doors for each room
     */
    public void set_doors_for_rooms() {
        for (Room r: dungeon) {
            r.set_doors();
        }
    }

    public void create_small_dungeon() {
        dungeon_creation(7);
    }

    public void create_medium_dungeon() {
        dungeon_creation(14);
    }

    public void create_large_dungeon() {
        dungeon_creation(21);
    }

    public Room get_room(int room_num) {
        return dungeon.get(room_num-1);
    }

    public int get_size() { return size; }

    public ArrayList<Room> return_rooms_array() { return dungeon; }

}
