package com.company;

public class RoomRiver extends Room {

    private int[][] tiles = new int[7][7];
    private String middleAlignRoom = "| %-63s|";
    private Player player;
    /**
     * 0,0     0,1     0,2     0,3     0,4     0,5     0,6
     * < >     < >     < >     |__|    < >     < >     < >
     *
     * 1,0     1,1     1,2     1,3     1,4     1,5     1,6
     * < >     < >     < >     < >     < >     < >     < >
     *
     * 2,0     2,1     2,2     2,3     2,4     2,5     2,6
     * < >     < >     < >     < >     < >     < >     < >
     *
     * 3,0     3,1     3,2     3,3     3,4     3,5     3,6
     *  #       #       #      | |      #       #       #
     *
     * 4,0     4,1     4,2     4,3     4,4     4,5     4,6
     * < >     < >     < >     < >     < >     < >     < >
     *
     * 5,0     5,1     5,2     5,3     5,4     5,5     5,6
     * < >     < >     < >     < >     < >     < >     < >
     *
     * 6,0     6,1     6,2     6,3     6,4     6,5     6,6
     * < >     < >     < >     |``|    < >     < >     < >
     *
     * @param player
     */
    public RoomRiver(Player player, Dungeon dungeon) {
        super(player, dungeon);
        create_river_grid();
        this.player=player;
        player.set_current_position(6, 3);
        super.setType("River Room");
    }

    public int check_tile(int x, int y) {
        if (x==3 && y==3) {
            return 3;
        } else if (x==0 && y==3) {
            return 2;
        }
        return 0;
    }

    /**
     * River located at x=3, check next position not on bridge (3,3)
     * @return true/false
     */
    public boolean check_river_north() {
        if (player.getCurrent_pos_x() - 1 == 3 && tiles[player.getCurrent_pos_x()-1][player.getCurrent_pos_y()] != tiles[3][3]) {
            System.out.println();
            System.out.println("A river flows through the middle of this area, you cannot cross it!");
            return false;
        }
        if (player.getCurrent_pos_x() < 1) {
            return false;
        }
        return true;
    }

    /**
     * River located at x=3, check next position not on bridge (3,3)
     * @return true/false
     */
    public boolean check_river_south() {
        if (player.getCurrent_pos_x() + 1 == 3 && tiles[player.getCurrent_pos_x()+1][player.getCurrent_pos_y()] != tiles[3][3]) {
            System.out.println();
            System.out.println("A river flows through the middle of this area, you cannot cross it!");
            return false;
        }
        if (player.getCurrent_pos_x() > 5) {
            return false;
        }
        return true;
    }

    /**
     * Create the room, river across the middle with bridge in the centre
     */
    public void create_river_grid() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles[i][j] = 0;
                if (i == 3) {
                    tiles[i][j] = 8;
                }
                if (i==3 && j==3) {
                    tiles[i][j] = 3;
                }
            }
        }
        // Door will always be this position for these rooms
        tiles[0][3] = 2;
        tiles[6][3] = 2;
    }

    public boolean check_river_east() {
        if (player.getCurrent_pos_x() == 3 && player.getCurrent_pos_y() == 3) {
            System.out.println("There is a flowing river either side of this bridge!");
            return false;
        } else if (player.getCurrent_pos_y() > 5) {
            System.out.println("You cannot move any further East.");
            return false;
        }
        return true;
    }

    public boolean check_river_west() {
        if (player.getCurrent_pos_x() == 3 && player.getCurrent_pos_y() == 3) {
            System.out.println("There is a flowing river either side of this bridge!");
            return false;
        } else if (player.getCurrent_pos_y() < 1) {
            System.out.println("You cannot move any further West.");
            return false;
        }
        return true;
    }

    public void print_grid_river() {

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
                    System.out.print("< "+IO.T_Y +"X"+IO.T_RS +" > ");
                } else if (i==3 && j==3) {
                    System.out.print("| " + tiles[i][j] + " | ");
                } else {
                    System.out.print("< " + tiles[i][j] + " > ");
                }
            }
            System.out.println("           |");
        }
        System.out.println("|                                                                |");
        System.out.println("|                                                                |");
        System.out.println("+►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄+");
        System.out.println("|                                                                |");
        System.out.println("|    Movement:  'w','a','s','d'   |      Inventory: 'i'          |");
        System.out.println("| --------------------------------+----------------------------- |");
        System.out.println("|   X → PLAYER  |  0 → FREE TILE  |  1 → ENCOUNTER  |  2 → DOOR  |");
        System.out.println("+►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►+◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►+");
    }

    public String toString() {
        return "Part of this room's roof has caved in, where the moonlight shines through - it flickers off the\n" +
                "Ripples in the river that flows through the middle of the room. There is a small bridge at\nThe centre of this river " +
                "where a Sphinx guards...\nHow will you fight a Sphinx?";
    }
}
