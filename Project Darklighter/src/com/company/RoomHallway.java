package com.company;

/**
 * This type of Room contains a single hallway which the player must walk through
 * The hallway may contain an encounter.
 */
public class RoomHallway extends Room {

    public String[][] tiles = new String[7][7];
    private Dice d1 = new Dice();

    /**
              0,0     0,1     0,2     0,3     0,4     0,5     0,6
              <░>     <░>     <░>     |..|    <░>     <░>     <░>

              1,0     1,1     1,2     1,3     1,4     1,5     1,6
              <░>     <░>     <░>     | |     <░>     <░>     <░>

              2,0     2,1     2,2     2,3     2,4     2,5     2,6
              <░>     <░>     <░>     | |     <░>     <░>     <░>

              3,0     3,1     3,2     3,3     3,4     3,5     3,6
              <░>     <░>     <░>     | |     <░>     <░>     <░>

              4,0     4,1     4,2     4,3     4,4     4,5     4,6
              <░>     <░>     <░>     | |     <░>     <░>     <░>

              5,0     5,1     5,2     5,3     5,4     5,5     5,6
              <░>     <░>     <░>     | |     <░>     <░>     <░>

              6,0     6,1     6,2     6,3     6,4     6,5     6,6
              <░>     <░>     <░>    |``|     <░>     <░>     <░>

     * @param player
     */
    public RoomHallway(Player player) {
        super(player);
        create_hallway_room();
        this.player=player;
        player.set_current_position(6, 3);
        tiles[0][3] = String.valueOf(2);                // Door will always be 0,3 in this room
    }

    /**
     * Return integer value of tile
     * @param x - x co-ord
     * @param y - y co-ord
     * @return
     */
    public int check_tile(int x, int y) {
        return Integer.parseInt(tiles[x][y]);
    }

    public void print_grid_hallway() {

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

                if (player.getCurrent_pos_x() == i && player.getCurrent_pos_y() == j) { // Print position of player
                    System.out.print("| "+IO.T_Y +"X"+IO.T_RS +" | ");
                } else if (j == 3) {
                    System.out.print("| " + tiles[i][j] + " | ");
                } else {
                    System.out.print("< "+IO.T_B +"#"+IO.T_RS + " > ");
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


    public void create_hallway_room() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles[i][j] = "░";
                if (j == 3) {
                    if (d1.chance_roll() < 0.05) {
                        tiles[i][j] = "1";      // 5% chance for encounter on each tile
                    } else {
                        tiles[i][j] = "0";
                    }
                }
            }
        }

        tiles[6][3] = "2";                      // Door will always be this position for these rooms
    }

    public boolean check_north_hallway() {
        if (player.getCurrent_pos_x() < 1) {
            System.out.println("You need to go through the door to move North!");
            return false;
        }
        return true;
    }

    public boolean check_south_hallway() {
        if (player.getCurrent_pos_x() > 5) {
            System.out.println("You need to go through the door to move South!");
            return false;
        }
        return true;
    }

    public boolean check_east_hallway() {
        System.out.println("You are in a narrow brick hallway, you can neither move East nor West.");
        return false;
    }

    public boolean check_west_hallway() {
        System.out.println("You are in a narrow brick hallway, you can neither move East nor West.");
        return false;
    }

    public String toString() {
        return "Before you is a long and narrow hallway, the bricks are old and mossy and\n" +
                "You are unable to see the other side though it\n" +
                "Must end somewhere. As the door shuts behind you,\nIts' echo " +
                "travels along the dark recess in front of you.";
    }
}
