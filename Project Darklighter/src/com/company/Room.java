package com.company;

import java.util.ArrayList;

/**
 * Rooms will be the stages for each encounter
 * Player will move between randomly generated rooms, populated with enemies, loot etc.
 * Rooms will contain a grid and players will move within this grid.
 * Each tile on the grid will either be 0, 1 or 2
 *  1 - Instance (Could be loot or enemy or other..)
 *  0 - Free tile (Nothing)
 *  2 - Door
 *
 *  Doors will be mostly randomly generated and then later rooms will have to have their doors
 *  based on the entry and exit door placements of previous and later rooms, depending on their type.
 *
 */
public class Room implements java.io.Serializable  {

    public Dungeon dungeon;
    public Dice d1 = new Dice();
    public int[][] tiles = new int[7][7];
    public Player player;
    public String middleAlignRoom = "| %-63s|";
    public boolean visited = false;
    public boolean completed = false;
    private String type = "Room";
    int[][] previous_door;
    int[][] next_door;
    ArrayList<int[][]> discovered_tiles = new ArrayList<>();
    Room prev_room = null;
    Room next_room = null;
    Room next_next_room = null;

    /**


             0,0     0,1     0,2     0,3     0,4     0,5     0,6
             < >     < >     < >     < >     < >     < >     < >

             1,0     1,1     1,2     1,3     1,4     1,5     1,6
             < >     < >     < >     < >     < >     < >     < >

             2,0     2,1     2,2     2,3     2,4     2,5     2,6
             < >     < >     < >     < >     < >     < >     < >

             3,0     3,1     3,2     3,3     3,4     3,5     3,6
             < >     < >     < >     < >     < >     < >     < >

             4,0     4,1     4,2     4,3     4,4     4,5     4,6
             < >     < >     < >     < >     < >     < >     < >

             5,0     5,1     5,2     5,3     5,4     5,5     5,6
             < >     < >     < >     < >     < >     < >     < >

             6,0     6,1     6,2     6,3     6,4     6,5     6,6
             < >     < >     < >     < >     < >     < >     < >

    */


    public Room(Player player, Dungeon dungeon) {
        create_standard_grid();
        this.dungeon=dungeon;
        this.player=player;
    }

    /**
     * Set the players position on first entering the room:
     *
     *      If it's the first room, always set to 6,3
     *      Otherwise, set starting position to position of door to previous room
     *
     *  Archive the room
     *
     *  Set the player's current room to this
     *
     *  If the player has not visited, set visited = true
     * @return
     */
    public boolean enter_room() {
        if (dungeon.return_rooms_array().indexOf(this) > 0) {
            player.set_current_position(getPrevious_door()[0][0], getPrevious_door()[0][1]);
        } else {
            player.set_current_position(6, 3);
        }
        player.archive_current_room();
        player.set_current_room(this);
        if (!has_visited()) {               // If player hasn't visited before
            visit_room();                   // Set visit = true
            return false;                   // Return false (no xp gain)
        }
        return true;
    }

    /**
     * Return to previously visited room (from room ahead)
     * @return
     */
    public boolean return_to_room() {
        player.set_current_position(getNext_door()[0][0], getNext_door()[0][1]);
        player.set_current_room(this);
        return false;   // No XP gain
    }

    /**
     * Check if player can move in this direction
     * @return - true/false
     */
    public boolean check_north() {
        if (player.getCurrent_pos_x() < 1) {
            return false;
        }
        return true;
    }

    /**
     * Check if player can move in this direction
     * @return - true/false
     */
    public boolean check_east() {
        if (player.getCurrent_pos_y() > 5) {
            return false;
        }
        return true;
    }

    /**
     * Check if player can move in this direction
     * @return - true/false
     */
    public boolean check_west() {
        if (player.getCurrent_pos_y() < 1) {
            return false;
        }
        return true;
    }

    /**
     * Check if player can move in this direction
     * @return - true/false
     */
    public boolean check_south() {
        if (player.getCurrent_pos_x() > 5) {
            return false;
        }
        return true;
    }

    /**
     * Returns true/false if player has visited the room before
     * @return
     */
    public boolean has_visited() {
        return visited;
    }

    public void visit_room() {
        this.visited=true;
    }

    public void setType(String type) {
        this.type=type;
    }

    public String getType() { return type; }

    /**
     * Display the grid
     */
    public void print_grid() {

        int player_percentage = Math.round((player.getHealth().getStat_level() * 10) / player.getMaxHealth().getStat_level());
        String health_bar = "[ ";
        for (int i = 1; i <= player_percentage; i++) { health_bar+="▓ "; }
        for(int i = 1; i <= 10-player_percentage;i++) { health_bar+="░ "; }
        health_bar += "]";

        System.out.println("\n\n\n\n\n\n\n\n\n");
        System.out.println("+►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄+");
        System.out.println("|               HEALTH - "+health_bar+"                 |");
        System.out.println("+►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄+");
        System.out.format(middleAlignRoom, "              Currently Equipped: " + player.getEquipped_weapon().getName());
        System.out.println();
        System.out.println("| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -|");
        System.out.println("|                                                                |");
        System.out.println("|                                                                |");

        for (int i = 0; i < tiles.length; i++) {
            System.out.print("|           ");
            for (int j = 0; j < tiles[0].length; j++) {

                // Print position of player
                if (player.getCurrent_pos_x() == i && player.getCurrent_pos_y() == j) {
                    System.out.print("[ "+IO.T_Y +"X"+IO.T_RS +" ] ");
                } else if (
                        player.getCurrent_pos_x() == i && player.getCurrent_pos_y()+1 == j ||
                        player.getCurrent_pos_x() == i && player.getCurrent_pos_y()-1 == j ||
                        player.getCurrent_pos_y() == j && player.getCurrent_pos_x()-1 == i ||
                        player.getCurrent_pos_y() == j && player.getCurrent_pos_x()+1 == i ||
                        player.getCurrent_pos_x()+1 == i && player.getCurrent_pos_y()+1 == j ||
                        player.getCurrent_pos_x()-1 == i && player.getCurrent_pos_y()-1 == j ||
                        player.getCurrent_pos_y()+1 == j && player.getCurrent_pos_x()-1 == i ||
                        player.getCurrent_pos_y()-1 == j && player.getCurrent_pos_x()+1 == i){
                    System.out.print("[ " + tiles[i][j] + " ] "
                    );
                } else {
                    System.out.print("[ "+IO.T_B +"#"+IO.T_RS + " ] ");
                }
            }
            System.out.println("           |");
        }
        System.out.println("|                                                                |");
        System.out.println("|                                                                |");
        System.out.println("+►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄+");
        System.out.println("|                                                                |");
        System.out.println("|                For info on controls type 'help'                |");
        System.out.println("| --------------------------------+----------------------------- |");
        System.out.println("|   X → PLAYER  |  0 → FREE TILE  |  1 → ENCOUNTER  |  2 → DOOR  |");
        System.out.println("+►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►+◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►+");
        System.out.println("Room " + (dungeon.return_rooms_array().indexOf(this)+1));
        if (getNext_door() != null) {
            System.out.println("Next door: " + getNext_door()[0][0] + ", " + getNext_door()[0][1]);
        }
        if (getPrevious_door() != null) {
            System.out.println("Previous door: " + getPrevious_door()[0][0] + ", " + getPrevious_door()[0][1]);
        }
    }



    /**
     * loops through tiles and sets each tile to 1 or 0 if not already a door (2)
     */
    public void create_standard_grid() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles[i][j] = d1.tile_spawn(); // Returns 1 or 0 randomly
            }
        }
    }

    /**
     * Loops through all tiles and checks to see if the Player has completed all encounters
     *
     * (If every tile is set to = 0)
     * @return - Boolean
     */
    public boolean check_tiles_for_completion() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (check_tile(i, j) == 1 || check_tile(i, j) == 3) {
                    return false;
                }
            }
        }
        return true;
    }

    public void set_room_to_complete() { this.completed = true; }

    public boolean is_complete() { return completed; }

    public int[][] getPrevious_door() { return previous_door; }

    public int[][] getNext_door() { return next_door; }

    /**
     * Return the number stored at the given tile
     * @param x - x co-ord
     * @param y - y co-ord
     * @return number at tile (0,1 or 2)
     */
    public int check_tile(int x, int y) {
        return (int)tiles[x][y];
    }

    /**
     * Once player has moved to tile, set it to discovered so that it will be shown on the grid
     * @param x - x co-ord
     * @param y - y co-ord
     */
    public void discoverTile(int x, int y) {
        discovered_tiles.add(new int[][] {{x, y}});
    }

    /**
     * When an encounter has been completed, set the tile to 0
     * @param x - x co-ord
     * @param y - y co-ord
     */
    public void encounter_completed(int x, int y) {
        tiles[x][y] = 0;
    }

    /**
     * Sets the current 'previous_door' based on the previous rooms next door
     * @param prev_room_door
     */
    public void set_door_based_on_previous(int[][] prev_room_door) {
        if (prev_room_door[0][0] == 0) {        // x value of previous door coordinate = 0 (TOP)
            this.previous_door = new int[][] {{6,3}};
            tiles[6][3] = 2;
        } else if(prev_room_door[0][0] == 3) {  // x value of previous door coordinate = 3 (MIDDLE)
            if (prev_room_door[0][1] == 0) {
                this.previous_door = new int[][] {{3,6}};
                tiles[3][6] = 2;
            } else {
                this.previous_door = new int[][] {{3,0}};
                tiles[3][0] = 2;
            }
        } else {                                // Else BOTTOM
            this.previous_door = new int[][] {{0,3}};
            tiles[0][3] = 2;
        }
    }


    /**
     * Sets a random co-orindate from the int multidimensional array to a '2' (door)
     *
     * If the co-ordinate is already a door, pick another random door point.
     *
     * If bottom = false, set random door without chance to put door at bottom; (6, 3) on grid
     */
    public void set_random_door(boolean bottom) {
        int[] option = {};
        int[][] doors = {};
        if (bottom) {
            doors = new int[][]{{0, 3}, {3, 0}, {3, 6}, {6, 3}};
        } else {
            doors = new int[][]{{0, 3}, {3, 0}, {3, 6}};
        }

        while(true) {
            if (doors.length == 4) {
                option = doors[d1.manualDiceRoll(4)-1];
            } else {
                option = doors[d1.manualDiceRoll(3)-1];
            }

            if (tiles[option[0]][option[1]] != 2) {
                tiles[option[0]][option[1]] = 2;
                next_door = new int[][] {{option[0],option[1]}};
                break;
            }

        }
        if (next_door == null) {
            System.out.println("No door instance found for " + this.getType());
        }
    }

    /**
     * Choose random other door position - ({0,3}, {3,0}, {3,6}) - to create a door
     */
    public void set_doors() {

        // Index of current room
        int this_room = dungeon.return_rooms_array().indexOf(this);

        // If this index is at the least, 1 less than the max index, store the next available Room object in next_room
        if (this_room <= dungeon.get_size() - 2 ) {
            next_room = dungeon.return_rooms_array().get(this_room + 1);
        }

        // If this index is at the least, 2 less than the max index, store the next, next available Room object in next_next_room
        if (this_room <= dungeon.get_size() - 3) {
            //System.out.println("Getting this room, Room " + this_room + " index + 2");
            next_next_room = dungeon.return_rooms_array().get(this_room + 2);
        }

        // If this room not the first room, store the previous available Room object in prev_room
        if (this_room > 0) {
            prev_room = dungeon.return_rooms_array().get(this_room - 1);
        }

        /**
         * If this room is the starting room, set one random door. No previous door.
         * If there is a Hallway/River room 2 rooms from now, no bottom door leading out of this one
         */
        if (this_room == 0) {
            if (next_room instanceof RoomHallway || next_room instanceof RoomRiver) {
                tiles[0][3] = 2;
                next_door = new int[][] {{0,3}};
            } else {
                set_random_door(false);
            }

        /**
         * If this room is NOT the final room
         */
        } else if (this_room != dungeon.return_rooms_array().size()-1) {

            /**
             * If the room is instance of hallway or river, doors will always be TOP and BOTTOM
             */
            if (this instanceof RoomHallway || this instanceof RoomRiver) {
                tiles[0][3] = 2;
                tiles[6][3] = 2;
                previous_door = new int[][] {{6,3}};
                next_door = new int[][] {{0,3}};
                /**
                 If this is instance Room each 2 subsequent rooms are instance Room
                 */
            }
            /**
             * If this room instance of Room and the next room is hallway or river
             *
             *  > Previous door has to be LEFT, RIGHT or BOTTOM
             *
             *  > Next door will always be TOP
             */
            else if (this instanceof Room && (next_room instanceof RoomHallway || next_room instanceof RoomRiver)) {
                if (prev_room instanceof Room) {    // Set the previous door based on previous rooms next door
                    set_door_based_on_previous(prev_room.next_door);
                } else {            // If previous room was not instance of Room, then door will have been TOP
                    tiles[6][3] = 2;
                    previous_door = new int[][] {{6,3}};
                }
                tiles[0][3] = 2;    // Set this room's next door to be TOP
                next_door = new int[][] {{0,3}};
            }

            /**
             * If this room instance of Room and next next room instance of Hallway or River
             *
             * > Set the previous door depending on the previous room
             *
             * > The next door cannot be bottom
             *  -- The reason is it would be strange to go into BOTTOM of this one, come out
             *  the next room at the TOP and then go back into the TOP into the following River/Hallway Room
             */
            else if (this instanceof Room && (next_next_room instanceof RoomHallway || next_next_room instanceof RoomRiver)) {
                if (prev_room instanceof Room) {
                    set_door_based_on_previous(prev_room.next_door);
                    set_random_door(false);
                } else {
                    tiles[6][3] = 2;
                    previous_door = new int[][] {{6,3}};
                    set_random_door(false);
                }

            }
            else {
                if (prev_room instanceof Room) {
                    set_door_based_on_previous(prev_room.next_door);
                    set_random_door(true);
                } else {
                    tiles[6][3] = 2;
                    previous_door = new int[][] {{6,3}};
                    set_random_door(true);
                }
            }

            /**
             * If this room IS the final room, set the previous door, no next door
             */
        } else {
            if (prev_room instanceof Room) {
                set_door_based_on_previous(prev_room.next_door);
            } else {
                tiles[6][3] = 2;
                previous_door = new int[][] {{6,3}};
            }
        }
    }





    /**
     *  =============================================================================
     *  ============ TEST FUNCTIONS
     *
     */

    /**
     * On entering each room, prints the location of the next and previous doors.
     */
    public void TEST_GET_ROOM_DOORS() {
        System.out.println();

        if (dungeon.return_rooms_array().indexOf(this) > 0) {
            if (getPrevious_door()[0][0] == 0 && getPrevious_door()[0][1] == 3) {
                System.out.println("Previous: TOP");
            } else if (getPrevious_door()[0][0] == 3 && getPrevious_door()[0][1] == 0) {
                System.out.println("Previous: LEFT");
            } else if (getPrevious_door()[0][0] == 3 && getPrevious_door()[0][1] == 6) {
                System.out.println("Previous: RIGHT");
            } else if (getPrevious_door()[0][0] == 6 && getPrevious_door()[0][1] == 3) {
                System.out.println("Previous: BOTTOM");
            } else {
                System.out.println();
            }
        }

        if (dungeon.return_rooms_array().indexOf(this) < dungeon.get_size() - 1) {
            if (getNext_door()[0][0] == 0 && getNext_door()[0][1] == 3) {
                System.out.println("Next: TOP");
            } else if (getNext_door()[0][0] == 3 && getNext_door()[0][1] == 0) {
                System.out.println("Next: LEFT");
            } else if (getNext_door()[0][0] == 3 && getNext_door()[0][1] == 6) {
                System.out.println("Next: RIGHT");
            } else if (getNext_door()[0][0] == 6 && getNext_door()[0][1] == 3) {
                System.out.println("Next: BOTTOM");
            } else {
                System.out.println();
            }
            System.out.println();
        }
    }

    /**
     * TEST FUNCTION
     * @return String - TOP, BOTTOM, LEFT, RIGHT
     */
    public String get_Previous_Door_TEST() {
        try {
            if (getPrevious_door()[0][0] == 0 && getPrevious_door()[0][1] == 3) {
                return "TOP";
            } else if (getPrevious_door()[0][0] == 3 && getPrevious_door()[0][1] == 0) {
                return "LEFT";
            } else if (getPrevious_door()[0][0] == 3 && getPrevious_door()[0][1] == 6) {
                return "RIGHT";
            } else if (getPrevious_door()[0][0] == 6 && getPrevious_door()[0][1] == 3) {
                return "BOTTOM";
            }
        } catch (NullPointerException ne) {
            return IO.T_BL+"None"+IO.T_RS;
        }
        return "";
    }

    /**
     * TEST FUNCTION
     * @return String - TOP, BOTTOM, LEFT, RIGHT
     */
    public String get_Next_Door_TEST() {
        try {
            if (getNext_door()[0][0] == 0 && getNext_door()[0][1] == 3) {
                return "TOP";
            } else if (getNext_door()[0][0] == 3 && getNext_door()[0][1] == 0) {
                return "LEFT";
            } else if (getNext_door()[0][0] == 3 && getNext_door()[0][1] == 6) {
                return "RIGHT";
            } else if (getNext_door()[0][0] == 6 && getNext_door()[0][1] == 3) {
                return "BOTTOM";
            }
        } catch (NullPointerException ne) {
            return IO.T_BL+"None"+IO.T_RS;
        }
        return "";
    }

    /**
     *  =============================================================================
     *  ============ END OF TEST FUNCTIONS
     *
     */





    public String toString() {
        return "A dark room lies before you.. you can't make out walls that are not immediately adjacent,\nAnd there" +
                " isn't enough visibility to make out a roof - perhaps height\nIs of no consequence to you in here.";
    }
}
