package com.company;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * All exceptions will later be more specific and useful
 */
public class Darklighter  implements java.io.Serializable{

    public static int enemies_killed = 0;
    public static int rooms_encountered = 0;
    public static int room_number = 1;

    public static final Scanner INPUT = new Scanner(System.in);
    public static Encounter encounter = new Encounter();
    public static DB_ db_ = new DB_();

    public static Player player = null;
    public static String selection = "";

    public static boolean player_alive = true;

    public static Dungeon dungeon = null;

    /**
     * Run the main menu
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        main_menu();
    }

    public static void main_menu() throws InterruptedException, IOException {
        boolean status = false;
        do {
            IO.game_intro();
            //Thread.sleep(2000);
            IO.welcome();
            //print_current_directory();
            String selection = INPUT.nextLine();
            switch (selection) {
                case "1":
                    newGame();
                    status = true;
                    break;
                case "2":
                    loadGame();
                    status = true;
                    break;
                case "3":
                    //settings();
                    status = true;
                    break;
                case "4":
                    IO.credits();
                    status = true;
                    break;
                case "5":
                    System.exit(0);
            }

        } while (status == false);

    }

    public static void newGame() throws InterruptedException, IOException {
        boolean status = false;
        do {
            IO.new_game();
            selection = INPUT.nextLine();

            if (!selection.isEmpty()) {

                player = new Player(selection, "Human");
                dungeon = new Dungeon(player);

                player.add_to_inventory(db_.anise);
                player.add_to_inventory(db_.fox_claws);
                player.add_to_inventory(db_.dram);
                player.add_to_inventory(db_.holly);
                player.add_to_inventory(db_.viruscea);
                player.add_to_inventory(db_.toadstools);
                player.add_to_inventory(db_.bear_fat);
                player.add_to_inventory(db_.tess_essence);
                player.add_to_inventory(db_.graymold);
                player.add_to_inventory(db_.bunchweed);
                player.add_to_inventory(db_.eyk_de_velle_blossom);
                player.add_to_inventory(db_.cadmia);
                player.add_to_inventory(db_.wolf_pelt);
                player.add_to_inventory(db_.asptongue_mold);
                player.add_to_inventory(db_.mandrake);
                player.add_to_inventory(db_.generate_random_normal_item());
                player.add_to_inventory(db_.generate_random_normal_item());
                player.add_to_inventory(db_.generate_random_normal_item());
                player.add_to_inventory(db_.generate_random_normal_item());
                player.add_to_inventory(db_.generate_random_normal_item());
                player.add_to_inventory(db_.generate_random_normal_item());
                player.add_to_inventory(db_.generate_random_normal_item());
                player.add_to_inventory(db_.generate_random_normal_item());
                player.add_to_inventory(db_.generate_random_normal_item());
                player.add_to_inventory(db_.generate_random_normal_item());
                player.add_to_inventory(db_.generate_random_normal_item());
                player.add_to_inventory(db_.generate_random_normal_item());
                player.add_to_inventory(db_.generate_random_normal_item());
                player.add_to_inventory(db_.generate_random_normal_item());
                player.add_to_inventory(db_.generate_random_normal_item());
                player.add_to_inventory(db_.generate_random_normal_item());
                player.add_to_inventory(db_.generate_random_normal_item());
                /* dungeon.test_rooms(); */

                Room first_room = dungeon.get_room(room_number);
                first_room.enter_room();

                status = true;

            }
        } while (status == false);

        game_loop(true);

    }

    public static void loadGame() throws IOException, InterruptedException {
        player = player_load();
        if (player != null) {
            IO.load_game_intro();
            game_loop(false);
        }
        main_menu();

    }

    public static void game_loop(boolean new_game) throws InterruptedException, IOException {

        if (new_game) {

            //IO.spawn_intro();

            //Thread.sleep(10000);

            //IO.room_intro();

            //Thread.sleep(3000);

            //IO.enter_continue();
        }

        while (!player.hasDied()) {

            player.print_room_grid();

            String choice = IO.standard_turn();

            switch (choice) {
                case "w", "W":
                    player.move_north();
                    if (player.is_encounter()) {                            // Move up
                        create_encounter(player.return_encounter_type());
                    } else if (player.at_previous_door()) {
                        IO.at_previous_door();
                    } else if (player.at_next_door()) {
                        IO.at_next_door();
                    }
                    break;
                case "s", "S":
                    player.move_south();
                    if (player.is_encounter()) {                            // Move down
                        create_encounter(player.return_encounter_type());
                    } else if (player.at_previous_door()) {
                        IO.at_previous_door();
                    } else if (player.at_next_door()) {
                        IO.at_next_door();
                    }
                    break;
                case "d", "D":
                    player.move_east();                                     // Move right
                    if (player.is_encounter()) {
                        create_encounter(player.return_encounter_type());
                    } else if (player.at_previous_door()) {
                        IO.at_previous_door();
                    } else if (player.at_next_door()) {
                        IO.at_next_door();
                    }
                    break;
                case "a", "A":
                    player.move_west();                                     // Move left
                    if (player.is_encounter()) {
                        create_encounter(player.return_encounter_type());
                    } else if (player.at_previous_door()) {
                        IO.at_previous_door();
                    } else if (player.at_next_door()) {
                        IO.at_next_door();
                    }
                    break;
                case "i", "I":
                    IO.show_player_inventory(player);                       // Show player inventory
                    break;
                case "p", "P":
                    System.out.println(player);
                    break;
                case "q", "Q":
                    IO.player_quit();                                       // Quits the game
                case "e","E":
                    System.out.println();
                    System.out.println(player.getCurrent_room());           // Prints current room object
                    Thread.sleep(2500);
                    System.out.println("\n\n");
                    break;
                case "f", "F":
                    IO.open_alchemy_crafting();
                    break;
                case "help","HELP":
                    IO.print_help();
                    Thread.sleep(2500);
                    IO.enter_continue();
                    break;
                default:
                    System.out.println("Invalid command.");
                    IO.enter_continue();
                    break;
            }
        }

        // On player death
        IO.player_death();
        System.out.println("Enemies slain - " + enemies_killed);
        System.out.println("Rooms encountered - " + rooms_encountered);
        IO.credits();

    }

    /**
     * Checks the current room to see if there are any 3's or 1's left
     *
     * If not, print message to player and gain xp
     *
     * NOTE: Only checks if room has not been completed yet.
     *
     * ...sets room = complete when room has been completed so it doesn't get checked again
     * @param current_room
     */
    public static boolean check_if_room_complete(Room current_room) {

        int room = dungeon.return_rooms_array().indexOf(player.getCurrent_room());

        if (!current_room.is_complete()) {
            if (current_room.check_tiles_for_completion()) {
                current_room.set_room_to_complete();
                player.add_xp((room * 15) + 20);
                return true;
            }
        }
        return false;
    }

    /**
     * Move to the next room, gets the next index in the dungeon arraylist
     */
    public static void next_room() {
        if (room_number < dungeon.get_size()) {
            room_number += 1;
        } else {

        }
        Room next_room = dungeon.get_room(room_number);
        if (!next_room.enter_room()) {
            player.add_xp(room_number*10);
        }
    }

    /**
     * Move back a room, gets the previous index, if > 0, of dungeon arraylist
     */
    public static void previous_room() {
        if (room_number > 1) {
            room_number -= 1;
            Room prev_room = dungeon.get_room(room_number);
            prev_room.enter_room();
        } else {
            System.out.println("You cannot leave this place!");
        }
    }


    public static void sphinx_encounter() {
        Sphinx sphinx = new Sphinx("Sphinx", "Mythological Creature", player);
        IO.sphinx_event(sphinx);
        player.complete_encounter();

    }
    /**
     * Creates an encounter based on the Room number, higher room number =
     *      Higher enemy level (all I've implemented so far)
     *      ...
     *      ...
     *      ...
     * @param encounter_type - What number is on the current tile
     * @throws IOException
     * @throws InterruptedException
     */
    public static void create_encounter(int encounter_type) throws IOException, InterruptedException {

        // What type of encounter?
            // Loot
            // Enemy
            // Quest?
            // Religious monument/altar?

        if (encounter_type == 1) {

            //int chance_encounter = db_.random_loot_chance(6);
            int chance_encounter = 1;

            switch (chance_encounter) {
                case 1:                                                     // Fight
                    System.out.println("Generating enemy from Room " + room_number);
                    fight(player, encounter.generate_enemy(player, room_number));
                    player.complete_encounter();
                    Thread.sleep(1500);
                    break;
                case 2:                                                     // Healing Loot
                    Item random_loot = encounter.generate_random_healing_item();
                    IO.pickup_event(random_loot);
                    player.complete_encounter();
                    Thread.sleep(1500);
                    break;
                case 3:                                                     // Weapon
                    Weapon random_weapon = encounter.generate_random_weapon();
                    IO.pickup_event(random_weapon);
                    player.complete_encounter();
                    Thread.sleep(1500);
                    break;
                case 4:                                                     // Altar
                    Altar random_altar = encounter.generate_random_altar();
                    IO.altar_event(random_altar);
                    player.complete_encounter();
                    Thread.sleep(1500);
                    break;
                case 5:                                                     // Food
                    Item food_item = encounter.generate_food_item();
                    IO.pickup_event(food_item);
                    player.complete_encounter();
                    Thread.sleep(1500);
                    break;
                case 6:                                                     // Regular item
                    Item normal_item = encounter.generate_random_item();
                    IO.pickup_event(normal_item);
                    player.complete_encounter();
                    Thread.sleep(1500);
                    break;
                default:
                    System.out.println("This is not an encounter. Error");
                    break;
            }

        } else if (encounter_type == 3) {

            sphinx_encounter();
        }
    }

    public static void fight(Player player, Enemy enemy) throws IOException, InterruptedException {

        Ability attack_ability = null;
        boolean combat = true;
        boolean player_turn = false;
        boolean enemy_turn = false;
        int p_roll = 0;
        int e_roll = 0;
        int choice = 0;

        do {
            p_roll = player.roll_stat(player.getInitiative().getStat_level());
            e_roll = enemy.roll_stat(enemy.getInitiative().getStat_level());
        } while (p_roll != e_roll);

        if (p_roll > e_roll) {
            player_turn = true;
        } else {
            enemy_turn = true;
        }

        while (combat) {

            while (player_turn) {

                int attack_option = 0;

                System.out.println();

                IO.display_player_info();
                player.decrease_active_status_effects();                // decreases active status effects

                System.out.println();
                IO.show_combat_information(player, enemy);

                Thread.sleep(2000);

                choice = IO.combat_message("Select your move > ");

                switch (choice) {

                    // Attack
                    case 1:
                        do {
                            attack_option = IO.show_player_attacks();
                            attack_ability = player.compile_active_abilities_and_return().get(attack_option);
                        } while (!player.isAbility(attack_option));

                        player.attack(player, player.getEquipped_weapon(), enemy, attack_ability);

                        if (enemy.hasDied()) { combat = false; }

                        enemy_turn = true;
                        player_turn = false;
                        break;

                    // Heal
                    case 2:
                        IO.show_player_healing_items(player);
                        enemy_turn = true;
                        player_turn = false;
                        break;
                        // Change Weapon
                    case 3:
                        IO.show_player_weapons(player);
                        enemy_turn = true;
                        player_turn = false;
                        break;
                        // Flee Attempt
                    case 4:
                        System.out.println("Test Later");
                        enemy_turn = true;
                        player_turn = false;
                        break;

                    default:
                        System.out.println("Combat continuing...");
                        continue;
                }
            }
            while (enemy_turn) {

                System.out.println();

                IO.display_enemy_info(enemy);

                enemy.decrease_active_status_effects();
                IO.show_combat_information(player, enemy);

                Thread.sleep(2000);

                // Check if enemy has died each loop
                // If so, add xp reward, end turn&combat
                if (enemy.hasDied()) {

                    enemies_killed += 1;
                    System.out.println();
                    player.add_xp(enemy.getXpReward());

                    combat = false;
                    enemy_turn = false;

                } else {

                    int enemy_choice = enemy.roll_combat_choice();

                    if (enemy_choice == 1) {
                        Ability enemy_ability = enemy.roll_combat_ability();
                        enemy.attack(enemy, enemy.getEquipped_weapon(), player, enemy_ability);
                        if (player.hasDied()) { combat = false; }
                        enemy_turn = false;
                        player_turn = true;

                    } else if (enemy_choice == 2) {

                        if (!enemy.check_healing()) {
                            enemy.attack(enemy, enemy.getEquipped_weapon(), player, enemy.roll_combat_ability());
                            if (player.hasDied()) { combat = false; }
                        }

                        enemy_turn = false;
                        player_turn = true;

                    } else if (enemy_choice == 3) {
                        System.out.println(enemy.getName() + " manages to flee!");

                        combat = false;
                        enemy_turn = false;

                    }
                    enemy_turn = false;

                    Thread.sleep(2000);
                }

            }
        }

    }

    // Serializes all player data
    public static void player_save() throws IOException {

        try {
            File the_file = File.createTempFile("save_data", ".ser", new File("/save_game"));
            FileOutputStream f_out = new FileOutputStream(the_file.getAbsolutePath());
            ObjectOutputStream o_out = new ObjectOutputStream(f_out);
            o_out.writeObject(player);
            o_out.close();
            f_out.close();
            System.out.println("Game state saved.");
        } catch (FileNotFoundException fe) {
            File new_dir = new File("save_game");
            new_dir.mkdir();
            new_dir.createNewFile();
            player_save();
        } catch (IOException e) {
            File new_dir = new File("save_game");
            new_dir.mkdir();
            new_dir.createNewFile();
            player_save();
        }
        System.exit(0);
    }

    public static void print_current_directory() {
        Path currentDirectoryPath = Paths.get("").toAbsolutePath();
        String currentPath = currentDirectoryPath.toString();
        File dir = new File(currentPath + "/save_game");
        String[] children = dir.list();

        if (children == null) {
            System.out.println("Cannot find dir or no files to show!");
        } else {
            for (int i = 0; i < children.length; i++) {
                String filename = children[i];
                System.out.println(filename);
            }
        }
    }


    // Deserializes player data to load into the game
    public static Player player_load() {
        Player player_data = null;
        System.out.println("Loading game state....");

        try {
            FileInputStream fin = new FileInputStream("player.ser");
            ObjectInputStream oin = new ObjectInputStream(fin);
            player_data = (Player) oin.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved games available!");
        }
        return player_data;
    }

}
