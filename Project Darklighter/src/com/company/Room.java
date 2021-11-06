package com.company;

/**
 * Rooms will be the stages for each encounter
 * Player will move between randomly generated rooms, populated with enemies, loot etc.
 * Rooms will contain a grid and players will move within this grid.
 * Each tile on the grid will either be 0, 1 or 2
 *  1 - Instance (Could be loot or enemy or other..)
 *  0 - Free tile (Nothing)
 *  2 - Door
 *
 *  Doors will HAVE to be - at least 1 where you came in (bottom middle - [6][3])**
 *                        - Then one at another mid point in the room
 *                          i.e. [0][3], [3][0], [3][6]
 *
 * Player will always be facing North - 1 Door will always be [6][3]
 */
public class Room implements java.io.Serializable  {

    public Dice d1 = new Dice();
    private int[][] tiles = new int[7][7];
    public Player player;
    public String middleAlignRoom = "| %-63s|";
    public boolean visited = false;

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


    public Room(Player player) {
        create_standard_grid();
        set_door();
        this.player = player;
    }

    public boolean enter_room() {
        player.set_current_position(6, 3);
        player.archive_current_room();
        player.set_current_room(this);
        if (!has_visited()) {               // If player hasn't visited before
            visit_room();                   // Set visit = true
            return false;                   // Return false (no xp gain)
        }
        return true;
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
                } else if (player.getCurrent_pos_x() == i && player.getCurrent_pos_y()+1 == j ||
                        player.getCurrent_pos_x() == i && player.getCurrent_pos_y()-1 == j ||
                        player.getCurrent_pos_y() == j && player.getCurrent_pos_x()-1 == i ||
                        player.getCurrent_pos_y() == j && player.getCurrent_pos_x()+1 == i ||
                        player.getCurrent_pos_x()+1 == i && player.getCurrent_pos_y()+1 == j ||
                        player.getCurrent_pos_x()-1 == i && player.getCurrent_pos_y()-1 == j ||
                        player.getCurrent_pos_y()+1 == j && player.getCurrent_pos_x()-1 == i ||
                        player.getCurrent_pos_y()-1 == j && player.getCurrent_pos_x()+1 == i){
                    System.out.print("[ " + tiles[i][j] + " ] ");
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
    }



    /**
     * loops through tiles and sets each tile to 1 or 0 if not already a door (2)
     */
    public void create_standard_grid() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {

                // Set starting point in room to 0
                if (i == 6 && j == 3) {
                    tiles[i][j] = 2;
                }

                if (tiles[i][j] != 2) {
                    tiles[i][j] = d1.tile_spawn(); // Returns 1 or 0 randomly
                }
            }
        }
    }


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
     * When an encounter has been completed, set the tile to 0
     * @param x - x co-ord
     * @param y - y co-ord
     */
    public void encounter_completed(int x, int y) {
        tiles[x][y] = 0;
    }

    /**
     * Choose random other door position - ({0,3}, {3,0}, {3,6}) - to create a door
     */
    public void set_door() {
        if (this instanceof Room) {

            int[][] doors = {{0, 3}, {3, 0}, {3, 6}};

            int[] option = doors[d1.manualDiceRoll(3) - 1];

            tiles[option[0]][option[1]] = 2;
        }
    }

    public String toString() {
        return "A dark room lies before you.. you can't make out walls that are not immediately adjacent,\nAnd there" +
                " isn't enough visibility to make out a roof - perhaps verticality\nIs of no consequence to you in here.";
    }
}
