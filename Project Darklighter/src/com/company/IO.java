package com.company;

import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.util.*;

/**
 * Handles the Input/Output of the program
 */
public final class IO  implements java.io.Serializable{

    // Place where you want text styling to end
    public static final String T_RS = "\u001B[0m";              // Reset text

    // Change text colour
    public static final String T_B = "\u001B[30m";              // Black
    public static final String T_R = "\u001B[31m";              // Red
    public static final String T_G = "\u001B[32m";              // Green
    public static final String T_Y = "\u001B[33m";              // Yellow
    public static final String T_BL = "\u001B[34m";             // Blue
    public static final String T_P = "\u001B[35m";              // Purple
    public static final String T_C = "\u001B[36m";              // Cyan
    public static final String T_W = "\u001B[37m";              // White

    public static final String T_IT = "\033[3m";                // Italics
    public static final String T_IT_RS = "\033[0m";             // Italics Reset text

    // Change text background colour
    public static final String TB_B = "\u001B[40m";             // Black
    public static final String TB_R = "\u001B[41m";             // Red
    public static final String TB_G = "\u001B[42m";             // Green
    public static final String TB_Y = "\u001B[43m";             // Yellow
    public static final String TB_BL = "\u001B[44m";            // Blue
    public static final String TB_P = "\u001B[45m";             // Purple
    public static final String TB_C = "\u001B[46m";             // Cyan
    public static final String TB_W = "\u001B[47m";             // White

    private static int DECISION;
    private static List combination_item = new ArrayList<>();

    /**
     * The Main Menu
     *
     *
     *
     */
    public static void welcome() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("");
        System.out.println("+ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - +");
        System.out.println("|   +--------------------------------------------------------+  |");
        System.out.println("|   |  █▀▄▀█ ██   ▄█    ▄       █▀▄▀█ ▄███▄      ▄     ▄     |  |\n" +
                           "|   |  █ █ █ █ █  ██     █      █ █ █ █▀   ▀      █     █    |  |\n" +
                           "|   |  █ ▄ █ █▄▄█ ██ ██   █     █ ▄ █ ██▄▄    ██   █ █   █   |  |\n" +
                           "|   |  █   █ █  █ ▐█ █ █  █     █   █ █▄   ▄▀ █ █  █ █   █   |  |\n" +
                           "|   |     █     █  ▐ █  █ █        █  ▀███▀   █  █ █ █▄ ▄█   |  |\n" +
                           "|   |    ▀     █     █   ██       ▀           █   ██  ▀▀▀    |  |\n" +
                           "|   |         ▀                                              |  |");
        System.out.println("|   +--------------------------------------------------------+  |");
        System.out.println("+ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - +");
        System.out.println();
        System.out.println("| [1]------------------->  New Game  <--------------------------|");
        System.out.println();
        System.out.println("| [2]------------------->  Load Game  <-------------------------|");
        System.out.println();
        System.out.println("| [3]------------------->  Settings  <--------------------------|");
        System.out.println();
        System.out.println("| [4]-------------------->  Credits   <-------------------------|");
        System.out.println();
        System.out.println("| [5]------------------->  Exit Game  <-------------------------|");
        System.out.println("|                                                               |");
        System.out.println("+ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - +");
        System.out.print("Choose an option > ");
    }

    /**
     * Prompts player to enter their character's name
     *
     *
     */
    public static void new_game() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("+~~~~~~~~~~~~~~~~~~~~~~+ ");
        System.out.println("↓    ENTER YOUR NAME   ↓");
        System.out.println("+~~~~~~~~~~~~~~~~~~~~~~+");
        System.out.println();
        System.out.println();
        System.out.print(">  ");
    }

    /**
     * Splash/title screen for the game
     *
     *
     */
    public static void game_intro() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("+--------------------------------------------------------------+");
        System.out.println("|                                                              |");
        System.out.println("|                                                              |");
        System.out.println("|                         Welcome to..                         |");
        System.out.println("|                                                              |");
        System.out.println("|                                                              |");
        System.out.println("|                                                              |");
        System.out.println("|                 ╔╦╗╔═╗╦═╗╦╔═╦  ╦╔═╗╦ ╦╔╦╗╔═╗╦═╗              |");
        System.out.println("|                  ║║╠═╣╠╦╝╠╩╗║  ║║ ╦╠═╣ ║ ║╣ ╠╦╝              |");
        System.out.println("|                 ═╩╝╩ ╩╩╚═╩ ╩╩═╝╩╚═╝╩ ╩ ╩ ╚═╝╩╚═              |");
        System.out.println("|                                                              |");
        System.out.println("|                                                              |");
        System.out.println("|                 A Rogue-Like Dungeon Crawler                 |");
        System.out.println("|                                                              |");
        System.out.println("|                                                              |");
        System.out.println("|                                                              |");
        System.out.println("|                  Survive as long as you can!                 |");
        System.out.println("|                                                              |");
        System.out.println("|                                                              |");
        System.out.println("+--------------------------------------------------------------+");
        System.out.println("\n\n\n\n\n");

    }

    /**
     * Player is prompted for Tutorial at the start of each new game
     *
     * Gets player familiar with Grid system and controls
     *
     * @throws InterruptedException
     */
    public static void tutorial() throws InterruptedException {
        Room room = new Room(Darklighter.player);

        Darklighter.player.getCurrent_room().print_grid();
        System.out.println();
        System.out.println();
        System.out.println("-------------------"+IO.T_G +"TUTORIAL"+IO.T_RS +" ---------------------------");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("The grid above shows you each"+IO.T_BL +" tile "+IO.T_RS +"in the room");
        System.out.println();
        Thread.sleep(2500);
        IO.enter_continue();
        System.out.println("The yellow '" +IO.T_Y +"X"+IO.T_RS +"' is YOU.");
        Thread.sleep(2500);
        System.out.println();
        System.out.println("Each '"+IO.T_BL +"0"+IO.T_RS +"' on the grid represents a free tile");
        System.out.println();
        Thread.sleep(2500);
        IO.enter_continue();
        System.out.println();
        System.out.println("Each '"+IO.T_BL +"1"+IO.T_RS +"' on the grid represents an encounter");
        Thread.sleep(2500);
        System.out.println();
        System.out.println("This could be loot, an altar or a battle - you will only know once you move to the tile");
        System.out.println();
        IO.enter_continue();
        System.out.println("Each '"+IO.T_BL +"2"+IO.T_RS +"' on the grid represents a door");
        System.out.println();
        Thread.sleep(2500);
        System.out.println("This will prompt you to go into the next room, leaving this one to come back to - if you wish.");
        System.out.println();
        IO.enter_continue();

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        System.out.println("Now we're going to go over the controls");
        System.out.println();
        Thread.sleep(2500);
        System.out.print("To move forward, you must enter a command - enter 'w' and then hit enter");
        System.out.print(" > ");
        while (!Darklighter.INPUT.nextLine().equals("w")) {
           System.out.println("Please input 'w' and hit the Enter key");
           System.out.print(" > ");
        }
        Darklighter.player.move_north();
        System.out.println("You have moved north 1 tile space");
        System.out.println();
        Thread.sleep(2500);
        IO.enter_continue();

        System.out.println("\n\n\n");
        System.out.println("At the moment we don't have any weapons or armour equipped, let's change that" +
                "in case we run into an enemy!");
        System.out.println();
        Thread.sleep(2500);
        System.out.println("To open your inventory, input 'i' and press Enter");
        System.out.print(" > ");
        while (!Darklighter.INPUT.nextLine().equals("i")) {
            System.out.println("Please input 'i' and hit the Enter key");
            System.out.print(" > ");
        }
        Darklighter.player.display_inventory();
        System.out.println();
        System.out.println("This screen shows us each item in our inventory and valuable information about it.");
        Thread.sleep(2500);
        System.out.println("We select items from it by inputting the ID number and pressing Enter.");
        IO.enter_continue();
        System.out.println("Try and equip your weapon by inputting '1' and pressing Enter");
        System.out.println(" > ");
        while (!Darklighter.INPUT.nextLine().equals("1")) {
            System.out.println("Please input '1' and hit the Enter key");
            System.out.print(" > ");
        }

        String choice = Darklighter.INPUT.nextLine();

        try {
            Item chosen_item = Darklighter.player.getInventory().return_inventory().get(Integer.parseInt(choice)-1);
            item_options(chosen_item, Darklighter.player);
        } catch (IndexOutOfBoundsException io) {
            System.out.println("Please enter a '1' and hit Enter");
            show_player_inventory(Darklighter.player);
        } catch (NumberFormatException nf) {
            System.out.println("You need to enter a number");
        } catch (NullPointerException | InterruptedException np) {
            System.out.println("** Please choose an item from the list **");
            show_player_inventory(Darklighter.player);
        }

        Thread.sleep(2500);
        System.out.println();
        System.out.println("You have now equipped a weapon!");
        System.out.println();
        Thread.sleep(2500);
        System.out.println("It's now time to equip armour");
        IO.enter_continue();
        System.out.println();
        System.out.println("Try and equip your armour by inputting '2' and pressing Enter");
        System.out.println(" > ");
        try {
            choice = Darklighter.INPUT.nextLine();
            while (!Darklighter.INPUT.nextLine().equals("2")) {
                System.out.println("Please input '2' and hit the Enter key");
                System.out.print(" > ");
            }
            Item chosen_item = Darklighter.player.getInventory().return_inventory().get(Integer.parseInt(choice)-1);
            item_options(chosen_item, Darklighter.player);
        } catch (IndexOutOfBoundsException io) {
            System.out.print("Try again. Input '2' and hit Enter > ");
            choice = Darklighter.INPUT.nextLine();
        } catch (ClassCastException ce) {
            System.out.print("Try again. Input '2' and hit Enter > ");
            choice = Darklighter.INPUT.nextLine();
        } catch (NullPointerException ne) {
            System.out.print("Try again. Input '2' and hit Enter > ");
            choice = Darklighter.INPUT.nextLine();
        }

        Thread.sleep(2500);
        System.out.println();
        System.out.println("You have now equipped some armour!");
        System.out.println();
        Thread.sleep(2500);
        IO.enter_continue();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        System.out.println("We're now ready for battle!");
        IO.enter_continue();
    }

    /**
     * Called at the start of a new game
     *
     *
     */
    public static void spawn_intro() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("You awaken in a dark room...");
        System.out.println("... . . .");
        System.out.print("It is impossible to see the other side, and you feel the darkness around you, clawing " +
                "at your soul - beckoning it towards capitulation and\ninevitable corruption.");
        System.out.println("... . . .");
        System.out.print("You wanted to find this.. ... place? You chose this path, yet you cannot remember " +
                "why you would choose such a sullen and destitute journey..\nyou have the urge to wake up " +
                "from all of this, go back to whatever it was that preceded these events - for merely being conscious " +
                "at \nthis point is dangerous enough, you could fall back asleep and lose yourself again.. never " +
                "to be troubled or hurt.");
        System.out.println("... . . .");
        System.out.print("Another, smaller, and more potent part of you implores you to continue, to find" +
                " what is darker than even this, should such a dwelling exist.\nThis part of you scares you, " +
                "it means to destroy you.. why would your instincts draw your toward such chaos?");
        System.out.println("... . . .");
        System.out.println(".. .");
        System.out.println("In spite of your cherished comforts, you wade forward.");
        System.out.println("\n\n");
    }

    public static void room_intro() {
        System.out.println("\n\n\n");
        System.out.println("Your best move is to find your way around, no point sitting here\n" +
                "and waiting for death.. See what you can find.");
    }

    /**
     * Quick method for stopping the game until the player has entered any button
     *
     * (Provides the Enter button as the command)
     */
    public static void enter_continue() {
        System.out.println();
        System.out.println(IO.T_Y +"Press [Enter] to continue"+IO.T_RS);
        Darklighter.INPUT.nextLine();
        Darklighter.INPUT.nextLine();
    }

    public static void credits() throws InterruptedException, IOException {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("You stumble, and fall to your knees.. slain in Darklighter.");
        System.out.println("\n\n");
        System.out.println("Created by Keir Hewitt");
        Thread.sleep(2500);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.print("Would you like to return to the main menu? (y/n)");
        if (Darklighter.INPUT.nextLine().equals("y")) {
            Darklighter.main_menu();
        }
    }

    /**
     * This will be called when a player is on a '2' tile
     *
     * If the player accepts('y'):
     *      Creates a new instance of room and assigns player to it
     *
     * Otherwise:
     *      Player remains in this room
     */
    public static void at_next_door() {
        System.out.println("\n\n\n");
        System.out.println("      ______\n" +
                "   ,-' ;  ! `-.\n" +
                "  / :  !  :  . \\\n" +
                " |_ ;   __:  ;  |\n" +
                " )| .  :)(.  !  |\n" +
                " |\"    (##)  _  |\n" +
                " |  :  ;`'  (_) (\n" +
                " |  :  :  .     |\n" +
                " )_ !  ,  ;  ;  |\n" +
                " || .  .  :  :  |\n" +
                " |\" .  |  :  .  |\n" +
                " |mt-2_;----.___|");
        System.out.println();
        System.out.println("You encounter a door... walk through it? (y/n) > ");
        if (Darklighter.INPUT.next().equals("y")) {
            Darklighter.next_room();
            System.out.println("You walk through the door");
            System.out.println("\n\n\n\n\n\n\n");
            System.out.println("You enter a similarly dark and gloomy room..");
            enter_continue();
            System.out.println();
        } else if (Darklighter.INPUT.next().equals("n")) {
            System.out.println();
            System.out.println("You choose not to walk through the door.");
            System.out.println();
        }
    }

    public static void at_previous_door() {
        System.out.println("\n\n\n");
        System.out.println(" ____________\n" +
                "|\\   .,______| |\n" +
                "|  ,-'   -_.  | |\n" +
                "| /   ,,_   \"  | |\n" +
                "| |  `  ,'  |   | |\n" +
                "| |  ', x   ,   | |\n" +
                "| )  ~     |%== | |\n" +
                "| |     /] ,|   | |\n" +
                "| |  :  [# ()   | |\n" +
                "| |  '    ; |   | |\n" +
                "| |  ; ., ' |   | |\n" +
                "| )       ` |   | |\n" +
                "| || :  ,, ,'   | |\n" +
                "| |    ,'       | |\n" +
                "|_| ,'__________|_|");
        System.out.println();
        System.out.println("You walk back to the previous door, walk through it? (y/n) > ");
        if (Darklighter.INPUT.nextLine().equals("y")) {
            Darklighter.previous_room();
            System.out.println("\n\n\n\n\n\n\n");
            System.out.println("You re-enter this room.");
            enter_continue();
            System.out.println();
        } else if (Darklighter.INPUT.nextLine().equals("n")) {
            System.out.println();
            System.out.println("You turn away from the previous room.");
            System.out.println();
        }
    }

    public static void finish_dungeon() throws IOException, InterruptedException {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("Well done! You have beaten this dungeon.");
        Darklighter.main_menu();
    }

    /**
     * Presented to the player for each(mostly, so far) non-combat turn
     *
     * Shows Player level, rooms encountered, num of enemies killed
     *
     * Prompts for next command
     */
    public static String standard_turn() {
        String choice = "";
        System.out.println(Darklighter.player.getName()
                + " [ LEVEL-"
                + Darklighter.player.getLevel().getStat_level()
                + " ]"
                + " | Rooms encountered - "
                + T_Y
                + Darklighter.rooms_encountered
                + T_RS
                + " | Enemies killed - "
                + T_R
                + Darklighter.enemies_killed
                + T_RS);
        System.out.println();
        System.out.print("Enter a command > ");

        choice = Darklighter.INPUT.next();

        return choice;

    }

    /**
     * Prints controls to player
     *
     */
    public static void print_help() {

        System.out.println("--Movement:");
        System.out.println("    "+IO.T_Y+"'w' - move North"+IO.T_RS);
        System.out.println("    "+IO.T_Y+"'d' - move East"+IO.T_RS);
        System.out.println("    "+IO.T_Y+"'s' - move South"+IO.T_RS);
        System.out.println("    "+IO.T_Y+"'a' - move West"+IO.T_RS);
        System.out.println("--Quit: ");
        System.out.println(IO.T_Y+"    'q'"+IO.T_RS);
        System.out.println("--Examine: ");
        System.out.println(IO.T_Y+"    'e'"+IO.T_RS);
        System.out.println("Inventory:");
        System.out.println(IO.T_Y+"    'i'"+IO.T_RS);
        System.out.println("Alchemy:");
        System.out.println(IO.T_Y+"    'f'"+IO.T_RS);

    }

    public static void e_to_exit() {
        System.out.println(T_Y + "Info: enter 'e' to exit this menu." + T_RS);
        System.out.println();
    }

    /**
     * Opens the alchemy crafting UI
     *
     * Player can select Ingredients to combine and make a solution
     *
     * @return boolean
     * @throws IOException
     *
     *
     *
     * ************************************
     * ************************************
     * ************************************
     *
     * <<< ERROR WITH THIS LOOP WHEN EXITING AFTER TRYING TO CRAFT ITEM, CHOOSING INVALID CONTAINER
     * THEN ENTERING 'E' TO EXIT THE MENU, IT RE_PROMPTS MULTIPLE TIMES BEFORE ACCEPTING THE EXIT COMMAND
     *
     * ************************************
     * ************************************
     * ************************************
     *
     *
     *
     *
     *
     *
     *
     */
    public static boolean open_alchemy_crafting() throws IOException, InterruptedException {

        while (true) {

            Darklighter.player.get_alchemy().check_formula();
            Darklighter.player.get_alchemy().alchemy_crafting_UI();
            String choice = "";

            e_to_exit();

            System.out.println("Enter one of the numbers to edit the ingredient section: ");
            System.out.print(" > ");

            choice = Darklighter.INPUT.nextLine();

            if (!choice.equals("e")) {                  // Enter 'e' to exit this menu

                if (choice.toLowerCase().equals("z")
                        && Darklighter.player.get_alchemy().check_formula()) {   // Enter 'z' to craft the item
                    if (craft_alchemy_item()) {
                        return true;
                    } else {
                        open_alchemy_crafting();            // Return to alchemy UI on unsuccessful craft
                    }
                }

                try {

                    DECISION = Integer.parseInt(choice);  // Otherwise, try to cast as int

                } catch (NumberFormatException e) {                             // Catch Format exception for errors
                    System.out.println("Please enter a relevant command.");
                    System.out.println();
                    open_alchemy_crafting();
                }

                switch (DECISION) {
                    case 1:
                        if (Darklighter.player.get_alchemy().getIngredient1() != null) {// Check if ingredient already set
                            if (change_or_remove_ingredient()) {                        // If player decides to change ingredient

                                Ingredient ing_1 = choose_ingredient(1);           // Choose ingredient
                                if (ing_1 == null) {
                                    System.out.println("No ingredient chosen.");
                                } else {
                                    Darklighter.player.get_alchemy().setIngredient1(ing_1);
                                }

                            } else {
                                Darklighter.player.get_alchemy().setIngredient1(null);  // If they decide to remove
                            }
                        } else {
                            Ingredient ing_1 = choose_ingredient(1);           // Choose ingredient
                            if (ing_1 == null) {
                                System.out.println("No ingredient chosen.");
                            } else {
                                Darklighter.player.get_alchemy().setIngredient1(ing_1);
                            }
                        }
                        break;
                    case 2:
                        if (Darklighter.player.get_alchemy().getIngredient2() != null) {
                            if (change_or_remove_ingredient()) {

                                Ingredient ing_2 = choose_ingredient(2);
                                if (ing_2 == null) {
                                    System.out.println("No ingredient chosen.");
                                } else {
                                    Darklighter.player.get_alchemy().setIngredient2(ing_2);
                                }

                            } else {
                                Darklighter.player.get_alchemy().setIngredient2(null);
                            }
                        } else {
                            Ingredient ing_2 = choose_ingredient(2);
                            if (ing_2 == null) {
                                System.out.println("No ingredient chosen.");
                            } else {
                                Darklighter.player.get_alchemy().setIngredient2(ing_2);
                            }
                        }
                        break;
                    case 3:
                        if (Darklighter.player.get_alchemy().getIngredient3() != null) {

                            if (change_or_remove_ingredient()) {

                                Ingredient ing_3 = choose_ingredient(3);
                                if (ing_3 == null) {
                                    System.out.println("No ingredient chosen.");
                                } else {
                                    Darklighter.player.get_alchemy().setIngredient3(ing_3);
                                }

                            } else {
                                Darklighter.player.get_alchemy().setIngredient3(null);
                            }
                        } else {
                            Ingredient ing_3 = choose_ingredient(3);
                            if (ing_3 == null) {
                                System.out.println("No ingredient chosen.");
                            } else {
                                Darklighter.player.get_alchemy().setIngredient3(ing_3);
                            }
                        }
                        break;
                    case 4:
                        if (Darklighter.player.get_alchemy().getIngredient4() != null) {

                            if (change_or_remove_ingredient()) {

                                Ingredient ing_4 = choose_ingredient(4);
                                if (ing_4 == null) {
                                    System.out.println("No ingredient chosen.");
                                } else {
                                    Darklighter.player.get_alchemy().setIngredient4(ing_4);
                                }

                            } else {
                                Darklighter.player.get_alchemy().setIngredient4(null);
                            }
                        } else {
                            Ingredient ing_4 = choose_ingredient(4);
                            if (ing_4 == null) {
                                System.out.println("No ingredient chosen.");
                            } else {
                                Darklighter.player.get_alchemy().setIngredient4(ing_4);
                            }
                        }
                        break;
                    case 5:
                        if (Darklighter.player.get_alchemy().getIngredient5() != null) {

                            if (change_or_remove_ingredient()) {

                                Ingredient ing_5 = choose_ingredient(5);
                                if (ing_5 == null) {
                                    System.out.println("No ingredient chosen.");
                                } else {
                                    Darklighter.player.get_alchemy().setIngredient5(ing_5);
                                }

                            } else {
                                Darklighter.player.get_alchemy().setIngredient5(null);
                            }
                        } else {
                            Ingredient ing_5 = choose_ingredient(5);
                            if (ing_5 == null) {
                                System.out.println("No ingredient chosen.");
                            } else {
                                Darklighter.player.get_alchemy().setIngredient5(ing_5);
                            }
                        }
                        break;
                    default:
                        System.out.println("Incorrect value entered.");
                        break;
                }
            } else {
                Darklighter.player.get_alchemy().reset_ingredients();
                return false;
            }
        }
    }

    /**
     * Shows user the available ingredients
     * Prompts them to choose one via ID
     * Accepts 'e' to quit menu
     * @return Ingredient / Null - notify quit
     */
    public static Ingredient choose_ingredient(int num) {

        boolean chosen = false;
        Item ingredient_chosen = null;
        String choice = "";

        while (!chosen) {

            Darklighter.player.getInventory().show_all_ingredients();           // Display Ingredients

            try {
                System.out.print("Select an ingredient ("+num+") > ");

                choice = Darklighter.INPUT.nextLine();

                if (choice.equals("e")) {                                       // If player wants to exit this
                    return null;
                }

                ingredient_chosen = Darklighter                                 // Otherwise get the ingredient
                        .player
                        .getInventory()
                        .choose_ingredient(Integer.parseInt(choice));

                chosen = true;                                                  // Set 'chosen' to true
            } catch (IndexOutOfBoundsException ie) {                            // If index not in array
                System.out.println("That was not an ingredient.");
                choose_ingredient(num);
            } catch (NumberFormatException ne) {                                // If num not given
                System.out.println("Please enter 'e' to quit or select a number from the Ingredients list.");
                choose_ingredient(num);
            }
        }
        return (Ingredient) ingredient_chosen;
    }

    /**
     * When player selects Ingredient slot that already has ingredient in it
     * Prompt them to change or remove it
     * @return = true=change/false=remove
     */
    public static boolean change_or_remove_ingredient() {
        String choice = "";
        System.out.println("Would you like to ("+T_Y+"c"+T_RS+")hange or ("+T_Y+"r"+T_RS+")emove ingredient?");

        try {
            choice = Darklighter.INPUT.nextLine();
        } catch (ClassCastException ce) {
            System.out.println("Please enter either 'c' or 'r'");
            System.out.println();
            change_or_remove_ingredient();
        }
        if (choice.equals("c")) {
            return true;
        } else if (choice.equals("r")) {
            return false;
        } else {
            System.out.println("Please enter 'c' or 'r' for change/remove");
            System.out.println();
            change_or_remove_ingredient();
        }
        return false;
    }

    public static boolean craft_alchemy_item() throws InterruptedException {
        String choice = "";
        Item container = null;
        Formula crafted_formula = null;

        System.out.println();
        Darklighter.player.getInventory().show_all_items();
        System.out.println();

        e_to_exit();

        System.out.println("Choose an item to store this in.");
        System.out.print(" > ");

        choice = Darklighter.INPUT.nextLine();

        if (!choice.equals("e")) {

            try {

                DECISION = Integer.parseInt(choice);
                container = Darklighter.player.getInventory().choose_item(DECISION);
                crafted_formula = Darklighter.player.get_alchemy().craft_formula(container);

                if (crafted_formula == null) {
                    System.out.println("Cannot store "
                            + Darklighter.player.get_alchemy().the_formula.getName()
                            + " inside " + container.getName()
                            + ".");
                    Thread.sleep(2500);
                    return false;
                } else {
                    System.out.println(crafted_formula.getName() + " created.");
                    System.out.println(crafted_formula.getName());          // Works
                    System.out.println(crafted_formula.getGroup());         // Works
                    System.out.println(crafted_formula.getContainer());     // Works
                    System.out.println(crafted_formula.getType());          // This is null ?? Should be populated
                    System.out.println(crafted_formula.getDescription());   // This is null ?? Should be ""
                    if (Darklighter.player.getInventory().replace_with(crafted_formula,container)) {
                        return true;// Remove container
                    } else {
                        System.out.println("Error adding to inventory.");
                    }
                }

            } catch (NumberFormatException ne) {
                System.out.println("Please enter a numerical value.");
                craft_alchemy_item();
            } catch (NullPointerException ne) {
                System.out.println("That is not an item, please choose a number from the list.");
                craft_alchemy_item();
            }

        }
        return false;

    }

    /**
     * Calls the display_inventory() method from the player object
     *
     * Gives the player the option to choose an item from the inventory
     *
     * Or enter 'x' to leave
     * @param player
     */
    public static void show_player_inventory(Player player) {

        System.out.println("\n\n\n\n\n\n\n");

        player.display_inventory();
        System.out.println();
        System.out.println("Choose an Item or press 'X' to return > ");

        String choice = Darklighter.INPUT.next();

        if (!choice.equals("x")) {                                      // If player hasn't chosen to exit

            try {                                                       // Try and use the item selected
                DECISION = Integer.parseInt(choice);
                Item chosen_item = player.getInventory().return_inventory().get(Integer.parseInt(choice)-1);

                item_options(chosen_item, Darklighter.player);

            } catch (IndexOutOfBoundsException io) {                    // If choice not in the list shown
                System.out.println("** Invalid choice, please choose from the list **");
                show_player_inventory(player);
            } catch (NumberFormatException | InterruptedException e) {  // If input is not a number
                System.out.println("Exiting inventory...");
            } catch (NullPointerException np) {  // If item is null
                System.out.println("** Please choose an item from the list **");
                show_player_inventory(player);
            }
        }
    }

    public static boolean prompt_use(Item item) throws InterruptedException {
        System.out.println();
        System.out.println(item);
        System.out.println();

        System.out.print(TB_G+"Use item? (y/n)"+T_RS+" > ");

        String choice = Darklighter.INPUT.nextLine();

        if (choice.equals("y")) {
            return true;
        }
        return false;
    }

    /**
     * Finds a use for the selected Item
     *
     * Checks what type of Item it is: Weapon,Armour,Healing,Shield
     *
     * Prompts player for relevant options depending on this type
     * @param item - item to use
     * @param player  - player object
     * @throws InterruptedException
     */
    public static void item_options(Item item, Player player) throws InterruptedException {

        if (item instanceof Weapon || item instanceof Shield || item instanceof Armour) {

            use_weapon_armour_shield_item(item);
        }
        else if (item instanceof Formula || item instanceof Ingredient) {

            use_formula_ingredient(item);
        }
        else if (item instanceof Currency) {

            use_currency_item(item);
        }
        else {

            use_general_item(item);
        }

        System.out.println();
        System.out.println();
        System.out.println("Going back to inventory...");

        Thread.sleep(2500);

        System.out.println();
        System.out.println();

        show_player_inventory(player);
    }

    /**
     * Use casting and ItemType enum to combine two Items
     * @param list
     */
    public static void combine_items(List list) throws InterruptedException {

        // If both are of type 'Item'
        if (list.get(0) instanceof Item && list.get(1) instanceof Item) {

            Item i1 = (Item) list.get(0);
            Item i2 = (Item) list.get(1);

            // CUTTER and SPLITS
            if (i1.has_item_type(ItemType.CUTTER) && i2.has_item_type(ItemType.SPLITS) ||
                    i2.has_item_type(ItemType.CUTTER) && i1.has_item_type(ItemType.SPLITS)) {

                if (i2.has_item_type(ItemType.SPLITS)) {
                    if (confirm_action("Do you want to split " +T_BL+ i2.getName() +T_RS+ "?")) {
                        splits(i2);
                        System.out.println(i2.getName() + " split.");
                    }
                } else {
                    if (confirm_action("Do you want to split " +T_BL+ i1.getName() +T_RS+ "?")) {
                        splits(i1);
                        System.out.println(i1.getName() + " split.");
                    }
                }

            // WRITES and WRITABLE
            } else if (i1.has_item_type(ItemType.WRITES) && i2.has_item_type(ItemType.WRITABLE) ||
                    i2.has_item_type(ItemType.WRITES) && i1.has_item_type(ItemType.WRITABLE)) {

                if (i2.has_item_type(ItemType.WRITABLE)) {
                    write_on(i2);
                    System.out.println("You scribble hastily with " +T_B+ i1.getName() + T_RS+".");
                } else {
                    write_on(i1);
                    System.out.println("You scribble hastily with " +T_B+ i2.getName() + T_RS+".");
                }

            // SOAKS and ABSORBS
            } else if (i1.has_item_type(ItemType.SOAKS) && i2.has_item_type(ItemType.ABSORBS) ||
                    i2.has_item_type(ItemType.SOAKS) && i1.has_item_type(ItemType.ABSORBS)) {

                if (i2.has_item_type(ItemType.ABSORBS)) {
                    if (confirm_action("Soak " +T_BL+ i2.getName() +T_RS+ " with " +T_BL+ i1.getName() +T_RS+ "?")) {
                        absorbs(i2, i1);
                        System.out.println("You soak " + i2.getName() + " with " + i1.getName() + ".");
                    }
                } else {
                    if (confirm_action("Soak " +T_BL+ i1.getName() +T_RS+ " with " +T_BL+ i2.getName() +T_RS+ "?")) {
                        absorbs(i1, i2);
                        System.out.println("You soak " + i1.getName() + " with " + i2.getName() + ".");
                    }
                }

            // FLAME and FUEL
            } else if (i1.has_item_type(ItemType.FLAME) && i2.has_item_type(ItemType.FUEL) ||
                    i2.has_item_type(ItemType.FLAME) && i1.has_item_type(ItemType.FUEL)) {

                if (i1.has_item_type(ItemType.FUEL)) {
                    if (confirm_action("Set " +T_BL+ i1.getName() +T_RS+ " alight?")) {
                        i1.set_alight();
                        System.out.println(i1.getName() + " set alight.");
                    }
                } else {
                    if (confirm_action("Set " +T_BL+ i2.getName() +T_RS+ " alight?")) {
                        i2.set_alight();
                        System.out.println(i2.getName() + " set alight.");
                    }
                }

            } else {
                System.out.println("This does not produce anything.");
            }

        // If one is type Formula and one is type Weapon
        } else if (list.get(0) instanceof Formula && list.get(1) instanceof Weapon ||
                list.get(1) instanceof Formula && list.get(0) instanceof Weapon) {

            Formula f = null;
            Weapon w = null;

            if (list.get(0) instanceof Formula) {
                f = (Formula) list.get(0);
                w = (Weapon) list.get(1);
            } else {
                f = (Formula) list.get(1);
                w = (Weapon) list.get(0);
            }

            if (confirm_action("Coat " +T_BL+ w.getName() +T_RS+ " with " +T_G+ f.getName() +T_RS+ "?")) {

                w.add_effect(f);

                System.out.println(w.getName() +
                        " coated with " +
                        f.getName() +
                        ".");
            }

        } else {
            System.out.println("This does not produce anything.");
        }
        Thread.sleep(2500);

    }

    /**
     * Split in item into multiple items
     * @param item
     * @return
     */
    public static boolean splits(Item item) {
        // Vial, Scrap of Paper, Wooden Bowl, Quill, Basket
        if (item.has_item_type(ItemType.SPLITS)) {
            if (item.getName().equals("Cloth") || item.getName().equals("Bandage")) {
                Item rags = new Item("Rags","Torn and used cloth rag.", 0.0012, 2, false, 0,
                        new ItemType[]{ItemType.WRITABLE,ItemType.ABSORBS,ItemType.WRITABLE});
                Darklighter.player.getInventory().replace_with(rags, item);
                Darklighter.player.add_to_inventory(rags);
                System.out.println("You cut " + item.getName() + " into rags.");
                return true;
            } else if (item.getName().equals("Tunic")) {
                Item rags = new Item("Rags","Torn and used cloth rag.", 0.0012, 2, false, 0,
                        new ItemType[]{ItemType.WRITABLE,ItemType.ABSORBS,ItemType.WRITABLE});
                Darklighter.player.getInventory().replace_with(rags, item);
                Darklighter.player.add_to_inventory(rags);
                Darklighter.player.add_to_inventory(rags);
                Darklighter.player.add_to_inventory(rags);
                Darklighter.player.add_to_inventory(rags);
                return true;
            } else if (item.getName().equals("Rags")) {
                Item torn_cloth_strands = new Item("Torn cloth strands","Ripped from a larger thread.", 0.0004, 3, false, 0,
                        new ItemType[]{ItemType.WRITABLE,ItemType.ABSORBS});
                Darklighter.player.getInventory().replace_with(torn_cloth_strands, item);
                Darklighter.player.add_to_inventory(torn_cloth_strands);
                Darklighter.player.add_to_inventory(torn_cloth_strands);
            } else if (item.getName().equals("Vial")) {
                Item glass_shards = new Item("Glass Shards","Sharp glass fragments, splintered from a small object.", 0.00013, 5, false, 0,
                        new ItemType[]{ItemType.NA});
                Darklighter.player.getInventory().replace_with(glass_shards, item);
                Darklighter.player.add_to_inventory(glass_shards);
                Darklighter.player.add_to_inventory(glass_shards);
            } else if (item.getName().equals("Scrap of Paper")) {

            } else if (item.getName().equals("Wooden Bowl")) {

            } else if (item.getName().equals("Quill")) {

            } else if (item.getName().equals("Basket")) {

            }
        }
        return false;
    }

    public static boolean absorbs(Item item, Item liquid) {
        if (item.has_item_type(ItemType.ABSORBS)) {
            item.soak(liquid);
            System.out.println("You soak " + item.getName() + " with " + liquid.getName() + ".");
            return true;
        }
        return false;
    }

    public static void set_alight(Item fuel) {
        fuel.set_alight();
    }

    public static void extinguish(Item fuel) { fuel.extinguish(); }

    public static void write_on(Item surface) {

        String note = "";
        System.out.println("Write something on the " + surface.getName() +".");
        System.out.print(" > ");

        note = Darklighter.INPUT.next();

        surface.write_on(note);
    }

    /**
     * Displays all of the Players healing items in their inventory
     *
     * Does this by calling the Inventory method 'show_healing_items()' via the Player object
     *
     * @param player
     */
    public static void show_player_healing_items(Player player) {
        if (!player.has_healing_items()) {
            System.out.println("You don't have any healing items!");
        } else {
            player.getInventory().show_healing_items();             // Calls the Inventory method via Player
            System.out.println();

            System.out.print("Select a healing item > ");

            try {
                int choice = Integer.parseInt(Darklighter.INPUT.nextLine());
                player.heal(player
                        .getInventory()
                        .choose_healing_item(choice)
                        );

            } catch (IndexOutOfBoundsException ie) {
                System.out.println();
                System.out.println("Please choose an item from the list!");
                show_player_healing_items(player);
            } catch (ClassCastException ce) {
                System.out.println();
                System.out.println("Please enter an ID from the list!");
                show_player_healing_items(player);
            }
        }

    }

    /**
     * Displays all the Player's weapons in their inventory
     *
     * Calls the Inventory method 'show_all_weapons()'
     *
     * @param player
     */
    public static void show_player_weapons(Player player) {
        if (!player.has_weapons()) {
            System.out.println("You don't have any other weapons!");
        } else {
            player.getInventory().show_all_weapons();
            System.out.println();

            System.out.print("Select a weapon > ");
            int choice = Integer.parseInt(Darklighter.INPUT.nextLine());

            try {
                player.setEquipped_weapon(player
                        .getInventory()
                        .choose_weapon(choice));

                System.out.println("You equip your " + T_BL + player
                        .getEquipped_weapon()
                        .getName()
                        + T_RS);
            } catch (NullPointerException e) {
                System.out.println();
                System.out.println("Please choose a weapon shown in the table via ID");
                show_player_weapons(player);
            }

        }
    }

    /**
     * Shows all relevant combat information related to the enemy
     *
     * Equipped items, abilities, active status effects
     *
     *
     * @param enemy
     */
    public static void display_enemy_info(Enemy enemy) {
        System.out.println("-------------------------------");
        System.out.println("Enemy Active status effects: ");
        if (!enemy.getActive_status_effects().isEmpty()) {
            for (Map.Entry<StatusEffect, Integer> se : enemy.getActive_status_effects().entrySet()) {
                System.out.println(IO.T_R +se.getKey().getName()+IO.T_RS);
            }
        }
        // KEEP - decreases active status effects
        System.out.println("-------------------------------");
        System.out.println();

        System.out.println("---------ENEMY------------");
        System.out.println(enemy);
        System.out.println("--------EQUIPPED------------");
        if (enemy.getEquipped_weapon() != null) {
            System.out.println("Equipped: " + enemy.getEquipped_weapon().getName());
        }
        if (enemy.getChestArmour() != null) {
            System.out.println("Armour: " + enemy.getChestArmour().getName());
        }
        if (enemy.getOff_hand() != null) {
            System.out.println("Off-Hand: " + enemy.getOff_hand().getName());
        }
        System.out.println("--------ABILITIES-------------");
        enemy.show_player_abilities_in_battle();
        System.out.println("-----------------------------");
    }

    /**
     * Does the same as above ^^
     *
     *
     *
     */
    public static void display_player_info() {
        System.out.println();
        System.out.println("-------------------------------");
        System.out.println("Player Active status effects: ");
        if (!Darklighter.player.getActive_status_effects().isEmpty()) {
            for (Map.Entry<StatusEffect, Integer> se : Darklighter.player.getActive_status_effects().entrySet()) {
                System.out.println(IO.T_R +se.getKey().getName()+IO.T_RS);
            }
        }
        System.out.println("-------------------------------");
        System.out.println(Darklighter.player);
        System.out.println("--------EQUIPPED------------");
        if (Darklighter.player.getEquipped_weapon() != null) {
            System.out.println("Equipped: " + Darklighter.player.getEquipped_weapon().getName());
        }
        if (Darklighter.player.getChestArmour() != null) {
            System.out.println("Armour: " + Darklighter.player.getChestArmour().getName());
        }
        if (Darklighter.player.getOff_hand() != null) {
            System.out.println("Off-Hand: " + Darklighter.player.getOff_hand().getName());
        }
    }


    /**
     * Displays player and enemy health stats with text graphic
     *
     *
     * @param player
     * @param enemy
     */
    public static void show_combat_information(Player player, Enemy enemy) {
        int player_health = player.getHealth().getStat_level();
        int player_maxHealth = player.getMaxHealth().getStat_level();
        int enemy_health = enemy.getHealth().getStat_level();
        int enemy_maxHealth = enemy.getMaxHealth().getStat_level();

        // Translate health to show it as a percentage, to the nearest 10% of 100 - but using 1-10
        // This is PURELY for UI sake, actual health stats will not be affected
        int player_percentage = Math.round((player_health * 10) / player_maxHealth);    // Show health as 'out of 10'
        int enemy_percentage = Math.round((enemy_health  * 10) / enemy_maxHealth);

        System.out.print("[Level-"
                + T_P
                +player
                .getLevel()
                .getStat_level()
                + T_RS
                +"]"
                + T_G
                +player.getName()
                + T_RS
                + " - [");
        for (int i = 1; i <= player_percentage; i++) { System.out.print("▓"); }
        for(int i = 1; i <= 10-player_percentage;i++) { System.out.print("░"); }

        System.out.println("] (" + player.getHealth().getStat_level() + "/" + player.getMaxHealth().getStat_level()+")");

        System.out.print("[Level-"
                + T_P
                +enemy
                .getLevel()
                .getStat_level()
                + T_RS
                +"]"
                + T_R
                +enemy
                .getName()
                + T_RS
                + " - [");
        for (int i = 1; i <= enemy_percentage; i++) { System.out.print("▓"); }
        for(int i = 1; i <= 10-enemy_percentage;i++) { System.out.print("░"); }

        System.out.println("] (" + enemy.getHealth().getStat_level() + "/" + enemy.getMaxHealth().getStat_level()+")");

        System.out.println();
    }

    /**
     * Displays the attacks available to the player in combat
     *
     * Prompts the player to choose one
     *
     * @return
     */
    public static int show_player_attacks() {
        int response = 0;
        String ret = "";
        Darklighter.player.show_player_abilities_in_battle();
        Darklighter.INPUT.nextLine();
        try {
            ret = Darklighter.INPUT.next();
            response = Integer.parseInt(ret);
        } catch (ClassCastException ce) {
            System.out.println();
            System.out.println("Please enter a numeric value from the list!");
            Darklighter.player.show_player_abilities_in_battle();
        } catch (NumberFormatException nf) {
            System.out.println();
            System.out.println("Please enter a numeric value from the list!");
            Darklighter.player.show_player_abilities_in_battle();
        }
        return response;
    }

    public static int combat_message(String message) throws IOException {
        int response = 0;
        String ret = "";
        System.out.println("1. Attack");
        System.out.println("2. Heal");
        System.out.println("3. Change Weapon");
        System.out.println("4. Flee");
        System.out.println(message);
        try {
            ret = Darklighter.INPUT.next();
            response = Integer.parseInt(ret);
        } catch (ClassCastException ce) {
            System.out.println();
            System.out.println("Please enter a numeric value from the list!");
            combat_message(message);
        } catch (NumberFormatException ne) {
            System.out.println();
            System.out.println("Please enter a numerical value.");
            combat_message(message);
        }
        return response;
    }

    public static void use_currency_item(Item item) {
        DECISION = 0;

        give_item_options(item);        // Display options for interacting with a general item

        while(true) {

            try {
                DECISION = Darklighter.INPUT.nextInt();
                Darklighter.INPUT.nextLine();
            } catch (InputMismatchException ie) {
                System.out.println();
            }

            if (DECISION < 1 || DECISION > 4) {
                System.out.println("Please enter a value from 1-4");
                System.out.print(" > ");
            } else {
                break;
            }

            Darklighter.INPUT.nextLine();
        }

        switch (DECISION) {
            case 1:
                System.out.println("Split, Implement later");
                break;
            case 2:
                System.out.println(T_IT+item.getDescription()+T_IT_RS);
                if (!item.get_writings().isEmpty()) {
                    System.out.println();
                    System.out.println(T_Y+"Writings: "+T_RS);
                    System.out.println(T_IT+item.get_writings()+T_IT_RS);
                }
                break;
            case 3:
                if (confirm_action("Are you sure you want to drop " + item.getName()+"?")) {
                    Darklighter.player.remove_from_inventory(item);
                }
                break;
            case 4:
                System.out.println("Yeet it");
                Darklighter.player.remove_from_inventory(item);
                break;
            default:
                System.out.println("Error in code logic");          // ##### TEMP
                break;
        }

    }

    /**
     * Provide options for general items
     * Shows user options for interacting with item
     * User picks options for either:
     *  Normal Items
     *      or
     *  Healing Items
     * @param item
     */
    public static void use_general_item(Item item ) {
        DECISION = 0;

        give_item_options(item);        // Display options for interacting with a general item

        if (item.isHealing_item()) {

            while(true) {

                try {
                    DECISION = Darklighter.INPUT.nextInt();
                    Darklighter.INPUT.nextLine();
                } catch (InputMismatchException ie) {
                    System.out.println();
                }

                if (DECISION < 1 || DECISION > 5) {
                    System.out.println("Please enter a value from 1-5");
                    System.out.print(" > ");
                } else {
                    break;
                }

                Darklighter.INPUT.nextLine();
            }

            switch (DECISION) {
                case 1:
                    // Clear elements list, to add next items
                    combination_item.clear();

                    System.out.println();
                    Darklighter.player.display_inventory();
                    System.out.println("Select an item to combine with: ");
                    System.out.print(" > ");

                    String choice = Darklighter.INPUT.nextLine();

                    if (!choice.equals("x")) {                                      // If player hasn't chosen to exit

                        try {                                                       // Try and use the item selected
                            DECISION = Integer.parseInt(choice);
                            Item chosen_item = Darklighter.player.getInventory().return_inventory().get(Integer.parseInt(choice)-1);

                            combination_item = new ArrayList();
                            combination_item.add(item); combination_item.add(chosen_item);
                            combine_items(combination_item);

                        } catch (IndexOutOfBoundsException io) {                    // If choice not in the list shown
                            System.out.println("** Invalid choice, please choose from the list **");
                            show_player_inventory(Darklighter.player);
                        } catch (NumberFormatException e) {  // If input is not a number
                            System.out.println("Exiting inventory...use_general_item");
                        } catch (NullPointerException np) {  // If item is null
                            System.out.println("** Please choose an item from the list **");
                            show_player_inventory(Darklighter.player);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    System.out.println(T_IT+item.getDescription()+T_IT_RS);
                    if (!item.get_writings().isEmpty()) {
                        System.out.println();
                        System.out.println(T_Y+"Writings: "+T_RS);
                        System.out.println(T_IT+item.get_writings()+T_IT_RS);
                    }
                    break;
                case 3:
                    Darklighter.player.heal(item);
                    break;
                case 4:
                    if (confirm_action("Are you sure you want to drop " +T_BL+ item.getName() +T_RS+"?")) {
                        Darklighter.player.remove_from_inventory(item);
                    }
                    break;
                case 5:
                    System.out.println("Yeet it");
                    Darklighter.player.remove_from_inventory(item);
                default:
                    System.out.println("Error in code logic");          // ##### TEMP
                    break;
            }

        } else {                // Non-healing general item

            while(true) {

                try {
                    DECISION = Darklighter.INPUT.nextInt();
                    Darklighter.INPUT.nextLine();
                } catch (InputMismatchException ie) {
                    System.out.println();
                }

                if (DECISION < 1 || DECISION > 4) {
                    System.out.println("Please enter a value from 1-4");
                    System.out.print(" > ");
                } else {
                    break;
                }

                Darklighter.INPUT.nextLine();
            }

            switch (DECISION) {
                case 1:
                    // Clear elements list, to add next items
                    combination_item.clear();

                    System.out.println();
                    Darklighter.player.display_inventory();
                    System.out.println("Select an item to combine with: ");
                    System.out.print(" > ");

                    String choice = Darklighter.INPUT.next();

                    if (!choice.equals("x")) {                                      // If player hasn't chosen to exit

                        try {                                                       // Try and use the item selected
                            DECISION = Integer.parseInt(choice);
                            Item chosen_item = Darklighter.player.getInventory().return_inventory().get(Integer.parseInt(choice)-1);

                            combination_item = new ArrayList();
                            combination_item.add(item); combination_item.add(chosen_item);
                            combine_items(combination_item);

                        } catch (IndexOutOfBoundsException io) {                    // If choice not in the list shown
                            System.out.println("** Invalid choice, please choose from the list **");
                            show_player_inventory(Darklighter.player);
                        } catch (NumberFormatException e) {  // If input is not a number
                            System.out.println("Exiting inventory...use_general_item");
                        } catch (NullPointerException | InterruptedException np) {  // If item is null
                            System.out.println("** Please choose an item from the list **");
                            show_player_inventory(Darklighter.player);
                        }
                    }
                    break;
                case 2:
                    System.out.println(T_IT+item.getDescription()+T_IT_RS);
                    if (!item.get_writings().isEmpty()) {
                        System.out.println();
                        System.out.println(T_Y+"Writings: "+T_RS);
                        System.out.println(T_IT+item.get_writings()+T_IT_RS);
                    }
                    break;
                case 3:
                    if (confirm_action("Are you sure you want to drop " +T_BL+ item.getName() +T_RS+"?")) {
                        Darklighter.player.remove_from_inventory(item);
                    }
                    break;
                case 4:
                    System.out.println("Yeet it");
                    Darklighter.player.remove_from_inventory(item);
                    break;
                default:
                    System.out.println("Error in code logic");          // ##### TEMP
                    break;
            }

        }

    }

    /**
     * If a potion is stored poorly, it has a chance to spill and go all over you, applying the potion's effects to you
     * @param f
     */
    public static void potion_accidently_activates(Formula f) {
        System.out.println("Perhaps you should have stored this more appropriately, " + '\n' +
                "Your " +T_G+ f.getName()+T_RS+ " spills from the "+T_BL+ f.getContainer().getName() +T_RS+ " that it was held in!");
        System.out.println();
        System.out.println("The contents go all over you!");
        Darklighter.player.consume_formula(f);
    }

    public static void use_weapon_armour_shield_item(Item item) {

        DECISION = 0;

        give_item_options(item);        // Display options for interacting with a general item

        while(true) {

            try {
                DECISION = Darklighter.INPUT.nextInt();
                Darklighter.INPUT.nextLine();
            } catch (InputMismatchException ie) {
                System.out.println();
            }

            if (DECISION < 1 || DECISION > 5) {
                System.out.println("Please enter a value from 1-5");
                System.out.print(" > ");
            } else {
                break;
            }

            Darklighter.INPUT.nextLine();
        }

        if (Darklighter.player.isEquipped(item)) {

            switch (DECISION) {
                case 1:
                    combination_item.clear();

                    System.out.println();
                    Darklighter.player.display_inventory();
                    System.out.println("Select an item to combine with: ");
                    System.out.print(" > ");

                    String choice = Darklighter.INPUT.nextLine();

                    if (!choice.equals("x")) {                                      // If player hasn't chosen to exit

                        try {                                                       // Try and use the item selected
                            DECISION = Integer.parseInt(choice);
                            Item chosen_item = Darklighter.player.getInventory().return_inventory().get(Integer.parseInt(choice)-1);

                            combination_item = new ArrayList();
                            combination_item.add(item); combination_item.add(chosen_item);
                            combine_items(combination_item);

                        } catch (IndexOutOfBoundsException io) {                    // If choice not in the list shown
                            System.out.println("** Invalid choice, please choose from the list **");
                            show_player_inventory(Darklighter.player);
                        } catch (NumberFormatException e) {  // If input is not a number
                            System.out.println("Exiting inventory...use_general_item");
                        } catch (NullPointerException | InterruptedException np) {  // If item is null
                            System.out.println("** Please choose an item from the list **");
                            show_player_inventory(Darklighter.player);
                        }
                    }
                    break;
                case 2:
                    System.out.println(T_IT+item.getDescription()+T_IT_RS);
                    if (!item.get_writings().isEmpty()) {
                        System.out.println();
                        System.out.println(T_Y+"Writings: "+T_RS);
                        System.out.println(T_IT+item.get_writings()+T_IT_RS);
                    }
                    break;
                case 3:
                    if (item instanceof Shield) {
                        Darklighter.player.unequip_shield();                // If shield armour, unequip
                    } else if (item instanceof Armour) {
                        if (((Armour) item).headArmour()) {
                            Darklighter.player.unequip_head_armour();       // If head armour, unequip
                        } else {
                            Darklighter.player.unequip_chest_armour();      // If chest armour, unequip
                        }
                    } else if (item instanceof Weapon) {
                        if (Darklighter.player.getOff_hand().equals(item)) { // If the weapon is in off-hand
                            Darklighter.player.set_off_hand(null);           // Set off-hand to null (dual wield)
                        } else {
                            Darklighter.player.unequip_weapon();             // If in main_hand, just unequip it
                        }
                    } else {
                        System.out.println("Item not equipped!");
                    }
                    System.out.println(T_BL
                            + item.getName()
                            + T_RS
                            + " un-equipped."
                    );
                    break;
                case 4:
                    if (confirm_action("Are you sure you want to drop " +T_BL+ item.getName()+T_RS +"?")){
                        Darklighter.player.remove_from_inventory(item);
                    }
                    break;
                case 5:
                    System.out.println("Yeet it");
                    Darklighter.player.remove_from_inventory(item);
                    break;
                default:
                    System.out.println("Error in code logic");          // ##### TEMP
                    break;
            }

        } else {

            switch (DECISION) {
                case 1:
                    combination_item.clear();

                    System.out.println();
                    Darklighter.player.display_inventory();
                    System.out.println("Select an item to combine with: ");
                    System.out.print(" > ");

                    String choice = Darklighter.INPUT.nextLine();

                    if (!choice.equals("x")) {                                      // If player hasn't chosen to exit

                        try {                                                       // Try and use the item selected
                            DECISION = Integer.parseInt(choice);
                            Item chosen_item = Darklighter.player.getInventory().return_inventory().get(Integer.parseInt(choice)-1);

                            combination_item = new ArrayList();
                            combination_item.add(item); combination_item.add(chosen_item);
                            combine_items(combination_item);

                        } catch (IndexOutOfBoundsException io) {                    // If choice not in the list shown
                            System.out.println("** Invalid choice, please choose from the list **");
                            show_player_inventory(Darklighter.player);
                        } catch (NumberFormatException e) {  // If input is not a number
                            System.out.println("Exiting inventory...use_general_item");
                        } catch (NullPointerException np) {  // If item is null
                            System.out.println("** Please choose an item from the list **");
                            show_player_inventory(Darklighter.player);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    System.out.println(T_IT+item.getDescription()+T_IT_RS);
                    if (!item.get_writings().isEmpty()) {
                        System.out.println();
                        System.out.println(T_Y+"Writings: "+T_RS);
                        System.out.println(T_IT+item.get_writings()+T_IT_RS);
                    }
                    break;
                case 3:
                    Darklighter.player.quick_equip(item);
                    System.out.println(T_BL
                            + item.getName()
                            + T_RS
                            + " equipped."
                    );
                    break;
                case 4:
                    if (confirm_action("Are you sure you want to drop " +T_BL+ item.getName()+T_RS +"?")){
                        Darklighter.player.remove_from_inventory(item);
                    }
                    break;
                case 5:
                    System.out.println("Yeet it");
                    Darklighter.player.remove_from_inventory(item);
                    break;
                default:
                    System.out.println("Error in code logic");          // ##### TEMP
                    break;
            }
        }
    }

    public static void use_formula_ingredient(Item item) throws InterruptedException {

        DECISION = 0;

        give_item_options(item);           // Display options for interacting with Formula

        while(true) {

            try {
                DECISION = Darklighter.INPUT.nextInt();
                Darklighter.INPUT.nextLine();
            } catch (InputMismatchException ie) {
                System.out.println();
            }

            if (DECISION < 1 || DECISION > 5) {
                System.out.println("Please enter a value from 1-5");
                System.out.print(" > ");
            } else {
                break;
            }

            Darklighter.INPUT.nextLine();
        }

        switch(DECISION) {
            case 1:
                combination_item.clear();

                System.out.println();
                Darklighter.player.display_inventory();
                System.out.println("Select an item to combine with: ");
                System.out.print(" > ");

                String choice = Darklighter.INPUT.nextLine();

                if (!choice.equals("x")) {                                      // If player hasn't chosen to exit

                    try {                                                       // Try and use the item selected
                        DECISION = Integer.parseInt(choice);
                        Item chosen_item = Darklighter.player.getInventory().return_inventory().get(Integer.parseInt(choice)-1);

                        combination_item = new ArrayList();
                        combination_item.add(item); combination_item.add(chosen_item);
                        combine_items(combination_item);

                    } catch (IndexOutOfBoundsException io) {                    // If choice not in the list shown
                        System.out.println("** Invalid choice, please choose from the list **");
                        show_player_inventory(Darklighter.player);
                    } catch (NumberFormatException e) {  // If input is not a number
                        System.out.println("Exiting inventory...use_general_item");
                    } catch (NullPointerException np) {  // If item is null
                        System.out.println("** Please choose an item from the list **");
                        show_player_inventory(Darklighter.player);
                    }
                }
                break;
            case 2:
                System.out.println(T_IT+item.getDescription()+T_IT_RS);
                if (!item.get_writings().isEmpty()) {
                    System.out.println();
                    System.out.println(T_Y+"Writings: "+T_RS);
                    System.out.println(T_IT+item.get_writings()+T_IT_RS);
                }
                break;
            case 3:
                if (confirm_action("Consume " + item.getName() +"?")) {
                    if (item instanceof Formula) {
                        Darklighter.player.consume_formula((Formula) item);
                    } else {
                        // Consume ingredient
                    }

                }
                break;
            case 4:
                if (confirm_action("Are you sure you want to drop " + item.getName() + "?")) {
                    Darklighter.player.remove_from_inventory(item);
                }
                break;
            case 5:
                System.out.println("**throw");
                break;
            default:
                System.out.println("Enter a value between 1-4");
                break;
        }


    }

    /**
     * Prompts player with yes/no to confirm an action
     * @param message
     * @return
     */
    public static boolean confirm_action(String message) {
        String ret = "";
        System.out.println(message + " (y/n)");
        System.out.print(" > ");


        ret = Darklighter.INPUT.next().trim().toLowerCase();

        while (!ret.equals("y") && !ret.equals("n")) {

            System.out.println();
            System.out.print("Invalid input, please enter 'y' or 'n' > ");

            ret = Darklighter.INPUT.nextLine().trim().toLowerCase();
        }

        if (ret.equals("y")) {
            return true;
        }
        return false;

    }

    public static void altar_event(Altar altar) throws InterruptedException {
        String ret = "";

        System.out.println();
        System.out.print("You come across an altar, inspect it? (y/n) > ");

        ret = Darklighter.INPUT.nextLine().trim().toLowerCase();

        while (!ret.equals("y") && !ret.equals("n")) {

            System.out.println();
            System.out.print("Invalid input, please enter 'y' or 'n' > ");

            ret = Darklighter.INPUT.nextLine().trim().toLowerCase();
        }

        if (ret.equals("y")) {
            System.out.println();
            System.out.println(altar.getDescription());
            System.out.println();

            if (altar.getScripture() != null && altar.getAlignment() != null) {
                System.out.println("On a dusty plaque at the foot of the monument, you make out a word ... ");
                System.out.println("... .");
                System.out.println("'" + altar.getAlignment().toString() + "'");
                System.out.println();
                System.out.print("Accept the lessons from this altar? (y/n) > ");
                if (Darklighter.INPUT.nextLine().equals("y")) {
                    Darklighter.player.getReligion().setAlignment(altar.getGod());
                    System.out.println();
                    System.out.println();
                    System.out.println("Alignment changed - " + Darklighter
                            .player
                            .getReligion()
                            .get_alignment()
                            .toString());
                    if (Darklighter.player.getFaith().getStat_level() >= altar.getScripture().getFaith_level_required()) {
                        Darklighter.player.set_active_scripture(altar.getScripture());
                        System.out.println();
                        System.out.println("Your understanding and experience with theology grows, this\n" +
                                "Lesson has bolstered your spirit and aligned your faith with a certain purpose.\n");
                        Thread.sleep(2000);
                        System.out.println("Active" +IO.T_BL + "scripture " +IO.T_RS +"set - ");
                        Thread.sleep(1500);
                        System.out.println(Darklighter.player.get_active_scripture());
                        Thread.sleep(1000);
                        IO.enter_continue();
                    } else {
                        System.out.println();
                        System.out.println("You attempt to understand the scripture.. the translation is hazy and\n " +
                                "Leaves you unfulfilled, perhaps you should re-visit this once your connection\n" +
                                "With theology is stronger.");
                        Thread.sleep(1000);
                        IO.enter_continue();
                    }
                } else {
                    System.out.println("You reject this prophecy in search of your own values.");
                    System.out.println();
                    System.out.println();
                }
            }
        }
        else {
            System.out.println("Leaving this religious monument, you continue forward.");
        }
    }

    public static void pickup_event(Item item) throws IOException {
        String ret = "";

        System.out.println();
        System.out.print("You find '" + item.getName() + "', would you like to pick it up? (y/n) > ");

        ret = Darklighter.INPUT.nextLine().trim().toLowerCase();

        while (!ret.equals("y") && !ret.equals("n")) {

            System.out.println();
            System.out.print("Invalid input, please enter 'y' or 'n' > ");

            ret = Darklighter.INPUT.nextLine().trim().toLowerCase();
        }

        if (ret.equals("y")) {
            System.out.println();
            Darklighter.player.add_to_inventory(item);
            System.out.println();
        }
        else {
            System.out.println("You move on from this item, leaving it to gather dust..");
        }
    }


    public static void give_item_options(Item i) {

        System.out.println();
        System.out.println("What would you like to do with this?");
        System.out.println();

        if (i instanceof Armour || i instanceof Shield || i instanceof Weapon) {

            if ( i instanceof Armour || i instanceof Shield) {
                i.print_to_string_armour_shield();
            } else {
                i.print_to_string_weapon();
            }

            System.out.println();

            if (Darklighter.player.isEquipped(i)) {
                System.out.println("1. Use");           // Use armour/shield/weapon with another item
                System.out.println("2. Examine");       // Examine armour/shield/weapon
                System.out.println("3. Un-Equip");      // Unequip armour/shield/weapon
                System.out.println("4. Drop");          // Drop armour/shield/weapon
                System.out.println("5. Throw");         // Throw armour/shield/weapon
                System.out.print(" > ");

            } else {
                System.out.println("1. Use");
                System.out.println("2. Examine");
                System.out.println("3. Equip");
                System.out.println("4. Drop");
                System.out.println("5. Throw");
                System.out.print(" > ");
            }

        } else if (i instanceof Formula || i instanceof Ingredient) {

            System.out.println("1. Use");               // Use ingredient with other item
            System.out.println("2. Examine");
            System.out.println("3. Consume");           // Consume ingredient - gain some knowledge of it
            System.out.println("4. Drop");              // Drop ingredient
            System.out.println("5. Throw");             // Throw ingredient
            System.out.print(" > ");

        } else if (i instanceof Currency) {

            System.out.println("1. Split");              // Split currency into separate amounts
            System.out.println("2. Examine");
            System.out.println("3. Drop");               // Drop the currency
            System.out.println("4. Throw");              // Throw the currency
            System.out.print(" > ");

        } else {

            if (!i.isHealing_item()) {

                i.print_to_string();
                System.out.println();

                System.out.println("1. Use");                // General item - Use with another item
                System.out.println("2. Examine");
                System.out.println("3. Drop");               // General item - Drop item
                System.out.println("4. Throw");              // General item - Throw item
                System.out.print(" > ");

            } else {

                i.print_to_string_healing();
                System.out.println();

                System.out.println("1. Use");                // General item - Use with another item
                System.out.println("2. Examine");
                System.out.println("3. Heal");               // Heal - Heal with item
                System.out.println("4. Drop");               // General item - Drop item
                System.out.println("5. Throw");              // General item - Throw item
                System.out.print(" > ");

            }
        }
    }

    public static void give_altar_options(Altar a) {

        System.out.println();
        System.out.println("What would you like to do with this?");

        System.out.println("1. Pray");               // Devote player to Altar/God
        System.out.println("2. Examine");
        System.out.println("3. Offer");              // Offer an Item to Altar
        System.out.println("4. Curse");              // Curse the Altar
        System.out.println("5. Destroy");             // Destroy the Altar
        System.out.print(" > ");
    }

    public static void player_death() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("You have died!");
    }

    /**
     * Prompts player to save their game on exit
     *
     * If yes, player data is serialized and saved
     *
     * If not, program terminates without saving data.
     *
     * @throws IOException
     */
    public static void player_quit() throws IOException {
        System.out.println("\n\n\n");
        System.out.print("Would you like to save your game? (y/n)");
        if (Darklighter.INPUT.nextLine().equals("y")) {
            Darklighter.player_save();
        } else {
            System.exit(0);
        }
    }

    /**
     * ASCII image of potion
     */
    public static void potion() {
        System.out.println("");
    }

}
