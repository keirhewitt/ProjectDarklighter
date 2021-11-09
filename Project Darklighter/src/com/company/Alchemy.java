package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Alchemy {

    private DB_ db_ = new DB_();
    private ArrayList<Formula> known_recipes = new ArrayList<>();
    private ArrayList<Formula> unknown_recipes = new ArrayList<>();
    public String leftAlignFormat = "| %-13s | %-47s |%n";
    public String middleAlign = "| %-71s |";

    private Ingredient ingredient1 = null;
    private Ingredient ingredient2 = null;
    private Ingredient ingredient3 = null;
    private Ingredient ingredient4 = null;
    private Ingredient ingredient5 = null;
    public Formula the_formula = null;

    // Formula Ingredient lists
    public Ingredient[] mini_health_plus = new Ingredient[2];
    public Ingredient[] weapon_damage_oil_plus = new Ingredient[3];
    public Ingredient[] mini_poison = new Ingredient[2];
    public Ingredient[] potent_poison = new Ingredient[3];
    public Ingredient[] mini_defence_plus = new Ingredient[3];
    public Ingredient[] mini_defence_minus = new Ingredient[3];
    public Ingredient[] mini_intelligence_plus = new Ingredient[2];
    public Ingredient[] mini_intelligence_minus = new Ingredient[3];
    public Ingredient[] mini_dexterity_plus = new Ingredient[3];
    public Ingredient[] mini_dexterity_minus = new Ingredient[3];
    public Ingredient[] mini_strength_plus = new Ingredient[3];
    public Ingredient[] mini_strength_minus = new Ingredient[3];



    /*
        --Formula Objects
     When these are crafted, the function that handles that will create a new object the same as these
     ... and will replace the 'null' with the container and the Player will gain this Formula in their
     ... inventory
     These objects are created here initially so that each property for a Formula only has to be entered
     ... once and I can just call these values when creating each ^usable instance

     ^usable in the sense that the Player will interact with them
     */
    public Formula mini_health_elixir =
            new Formula(mini_health_plus, "Mini Health Elixir", 45,
                    "The mixture revitalises the consumer a small amount", Consistency.LIQUID,
                    "health",25,0, "+",null);    // Increase health
    public Formula truer_strike_oil =
            new Formula(weapon_damage_oil_plus, "Truer Strike Oil", 50,
                    "Gives the applied weapon an edge against humanoids.", Consistency.LIQUID,
                    1.15, "*",null);   // Increased weapon damage

    public Formula menial_poison =
            new Formula(mini_poison, "Menial Poison", 32,
                    "Applies sickly disposition to targeted creature.", Consistency.LIQUID,
                     "health",3,3,"-",null);   // Lesser poison

    public Formula vigorous_poison =
            new Formula(potent_poison, "Vigorous Poison", 75,
                    "Potent poisonous concoction, inducing convulsions and extreme nausea.", Consistency.LIQUID,
                     "health",5,4,"-",null);   // More potent poison

    public Formula discernment_essence =
            new Formula(mini_intelligence_plus, "Discernment Essence", 38,
                    "Sanctions more astute observations, temporarily.", Consistency.POWDER,
                    "intelligence", 4,5,"+",null);   // Increased intelligence

    public Formula weed_brain =
            new Formula(mini_intelligence_minus, "Weed-Brain Draught", 43,
                    "When the grass grows around the brain.", Consistency.POWDER,
                    "intelligence", 4,4,"-",null);   // Decreased intelligence

    public Formula fleet_foot_elixir =
            new Formula(mini_dexterity_plus, "Fleet-Foot Elixir", 30,
                    "Elixir that quickens movement, ensures nimbleness.", Consistency.LIQUID,
                    "dexterity", 5,5,"+",null);   // Increase dexterity

    public Formula club_foot_concoction =
            new Formula(mini_dexterity_minus, "Club-Foot Concoction", 36,
                    "Ensure your foe remains grounded and sluggish.", Consistency.LIQUID,
                    "dexterity", 4,5,"-",null);   // Decreased dexterity

    public Formula potion_of_thick_skin =
            new Formula(mini_defence_plus, "Potion of Thick-Skin", 43,
                    "Improves one's defensive capabilities.", Consistency.LIQUID,
                    "defence", 3,3,"+",null);   // Increased defense

    public Formula draught_of_frailty =
            new Formula(mini_defence_minus, "Draught of Frailty", 52,
                    "Spicy ale mixture which lessens the hardiness of your target.", Consistency.LIQUID,
                    "defence", 4,3,"-",null);   // Decreased defence

    public Formula elixir_of_might =
            new Formula(mini_strength_plus, "Elixir of Might", 58,
                    "Makes the consumer a more powerful force.", Consistency.LIQUID,
                    "strength", 4,3,"+",null);   // Increased strength

    public Formula potion_of_diminutive_size =
            new Formula(mini_strength_plus, "Potion of Diminutive Size", 64,
                    "Decrease your foe's power.", Consistency.LIQUID,
                    "strength", 4,3,"-",null);   // Decreased strength



    public Alchemy() { init_formulas();}    // Constructor just initializes the formulas

    /**
     * Initializes all Ingredients for Formulas
     *
     * Adds formulas to the recipe lists
     */
    public void init_formulas() {

        /**
         * ================ RECIPES ======================
         */
        mini_health_plus[0]= db_.comfrey_root;
        mini_health_plus[1]= db_.rat_tail;

        weapon_damage_oil_plus[0]= db_.holly;
        weapon_damage_oil_plus[1]= db_.cadmia;
        weapon_damage_oil_plus[2]= db_.dram_soot;

        mini_defence_plus[0]=db_.viruscea;
        mini_defence_plus[1]=db_.dram_soot;
        mini_defence_plus[2]=db_.chalk;

        mini_defence_minus[0]=db_.crypt_shrooms;
        mini_defence_minus[1]=db_.dram;
        mini_defence_minus[2]=db_.wormgrass;

        potent_poison[0]=db_.toadstools;
        potent_poison[1]=db_.bear_fat;
        potent_poison[2]=db_.cadmia;

        mini_poison[0]=db_.glowing_nettle;
        mini_poison[1]=db_.cloves;

        mini_intelligence_plus[0]=db_.asptongue_mold;
        mini_intelligence_plus[1]=db_.tess_essence;

        mini_intelligence_minus[0]=db_.asptongue_mold;
        mini_intelligence_minus[1]=db_.tess_essence;
        mini_intelligence_minus[2]=db_.anise;

        mini_dexterity_plus[0]=db_.lemon;
        mini_dexterity_plus[1]=db_.eyk_de_velle_blossom;
        mini_dexterity_plus[2]=db_.cinnamon;

        mini_dexterity_minus[0]=db_.caraway_seed;
        mini_dexterity_minus[1]=db_.tsuebell;
        mini_dexterity_minus[2]=db_.glowing_nettle;

        mini_strength_plus[0]=db_.eyk_de_velle_blossom;
        mini_strength_plus[1]=db_.ghostly_matter;
        mini_strength_plus[2]=db_.mandrake;

        mini_strength_minus[0]=db_.bunchweed;
        mini_strength_minus[0]=db_.graymold;
        mini_strength_minus[0]=db_.tess_essence;

        // viruscea, dram soot + chalk          > + mini defence@   ###
        // crypt shrooms, dram, wormgrass       > - mini defence@  ###
        // toadstools, bear fat, cadmia         > potent poison@   ##
        // glowing nettle, cloves               > mini poison@   ##
        // asptongue mold, tess essence         > + mini intelligence@  ##
        // asptongue mold, tess essence, anise  > - mini intelligence@  ##
        // lemon, EDV blossom, cinnamon         > + mini dexterity@  ###
        // carraway seed, tsuebell, g nettles   > - mini dexterity@  ###
        // EDV blossom, ghostly m, mandrake     > + mini strength@
        // bunchweed, graymold, tess essence    > - mini strength

        /**
         * ===============================================
         */

        unknown_recipes.add(truer_strike_oil);
        unknown_recipes.add(mini_health_elixir);
        unknown_recipes.add(menial_poison);
        unknown_recipes.add(vigorous_poison);
        unknown_recipes.add(discernment_essence);
        unknown_recipes.add(weed_brain);
        unknown_recipes.add(potion_of_diminutive_size);
        unknown_recipes.add(potion_of_thick_skin);
        unknown_recipes.add(elixir_of_might);
        unknown_recipes.add(fleet_foot_elixir);
        unknown_recipes.add(draught_of_frailty);
        unknown_recipes.add(club_foot_concoction);

    }

    public void discover_formula(Formula formula) {
        known_recipes.add(formula);
    }

    /**
     * Returns true if the formula is in the 'known_recipes' arraylist
     * @param formula
     * @return
     */
    public boolean is_formula_known(Formula formula) {
        if (known_recipes.contains(formula)) {
            return true;
        }
        return false;
    }

    /**
     * Add all ingredients that aren't null to an arraylist and convert to an array
     *
     * Pass this array through 'is_formula()' and return true/false
     *
     * @return true/false
     */
    public boolean check_formula() {
        ArrayList<String> check_current_ingredients = new ArrayList<>();
        if (ingredient1 != null) { check_current_ingredients.add(ingredient1.getName()); }
        if (ingredient2 != null) { check_current_ingredients.add(ingredient2.getName()); }
        if (ingredient3 != null) { check_current_ingredients.add(ingredient3.getName()); }
        if (ingredient4 != null) { check_current_ingredients.add(ingredient4.getName()); }
        if (ingredient5 != null) { check_current_ingredients.add(ingredient5.getName()); }


        String[] check = check_current_ingredients.toArray(new String[check_current_ingredients.size()]);

        if (is_formula(check)) {                // This function will alter the 'the_formula' String if true
            return true;
        }
        return false;
    }

    /**
     * Container will be prompted for in IO as well as final checks
     * This method will create the formula and return it
     * @param container
     * @return Formula
     */
    public Formula craft_formula(Item container) {

        if (container != null) {

            if (check_formula()) {                                  // If it is a correct formula

                /**
                 * **************************************************
                 * If Item given to hold Formula is of type CONTAINER
                 * **************************************************
                 */
                if (container.has_item_type(ItemType.CONTAINER)) {  // If the container has the ItemType 'CONTAINER'

                    /**
                     * -- CONTAINER items can hold LIQUID,GAS,SALVE,POWDER + MAGIC formula's
                     */
                    if (the_formula.getGroup() == Consistency.LIQUID
                            || the_formula.getGroup() == Consistency.GAS
                            || the_formula.getGroup() == Consistency.SALVE
                            || the_formula.getGroup() == Consistency.POWDER
                            || the_formula.getGroup() == Consistency.MAGIC) {  // Containers can hold these types

                        /**
                         * -- If it's a stat altering formula, create with this constructor
                         */
                        if (the_formula.isStat_formula()) {     // If formula is a stat altering formula
                            Formula crafted_stat_formula = new Formula(the_formula.getRecipe(),
                                    the_formula.getName(),
                                    the_formula.getValue(),
                                    the_formula.getDescription(),
                                    the_formula.getGroup(),
                                    the_formula.getStat_affected(),
                                    the_formula.getAmount_affected(),
                                    the_formula.getTurns_stat_affected(),
                                    the_formula.getOperator(),
                                    container);

                            return crafted_stat_formula;

                        /**
                         * -- If it's an item altering formula, create with the other constructor
                         */
                        } else if (the_formula.isItem_formula()) { // If formula is an Item formula

                            Formula crafted_item_formula = new Formula(the_formula.getRecipe(),
                                    the_formula.getName(),
                                    the_formula.getValue(),
                                    the_formula.getDescription(),
                                    the_formula.getGroup(),
                                    the_formula.getItem_mod(),
                                    the_formula.getOperator(),
                                    container);

                            return crafted_item_formula;
                        } else {
                            return null;                // Return null if the formula cannot go into container
                        }
                    }

                /**
                 * **************************************************
                 * If Item given to hold Formula is of type POUCH
                 * **************************************************
                 */
                } else if (container.has_item_type(ItemType.POUCH)) {              // If the container is a pouch

                    /**
                     * -- POUCH items can hold SALVE,POWDER or SOLID formula's
                     */
                    if (the_formula.getGroup() == Consistency.SALVE
                            || the_formula.getGroup() == Consistency.POWDER
                            || the_formula.getGroup() == Consistency.SOLID) {     // Pouches can hold these types

                        /**
                         * -- If it's a stat altering formula, create with this constructor
                         */
                        if (the_formula.isStat_formula()) {     // If formula is a stat altering formula
                            Formula crafted_stat_formula = new Formula(the_formula.getRecipe(),
                                    the_formula.getName(),
                                    the_formula.getValue(),
                                    the_formula.getDescription(),
                                    the_formula.getGroup(),
                                    the_formula.getStat_affected(),
                                    the_formula.getAmount_affected(),
                                    the_formula.getTurns_stat_affected(),
                                    the_formula.getOperator(),
                                    container);

                            return crafted_stat_formula;

                        /**
                         * -- If it's an item altering formula, create with the other constructor
                         */
                        } else if (the_formula.isItem_formula()) { // If formula is an Item formula

                            Formula crafted_item_formula = new Formula(the_formula.getRecipe(),
                                    the_formula.getName(),
                                    the_formula.getValue(),
                                    the_formula.getDescription(),
                                    the_formula.getGroup(),
                                    the_formula.getItem_mod(),
                                    the_formula.getOperator(),
                                    container);

                            return crafted_item_formula;
                        }

                    /**
                     * **************************************************************
                     * -- If Container is not of Type CONTAINER or POUCH, return NULL
                     * **************************************************************
                     */
                    } else {
                        return null;                // Return null if the formula cannot go into container
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns true if the list given is a recipe for a Formula object
     *
     * @param i_list
     * @return true/false
     */
    public Boolean is_formula(String[] i_list) {
        List<Ingredient> user_list = new ArrayList(Arrays.asList(i_list));
        int size = user_list.size();

        for (Formula f: unknown_recipes) {                 // Loop through all unknown recipes (it has every recipe)

            ArrayList<String> compare_list = new ArrayList<>();

            for (Ingredient i: f.getRecipe()) {            // Add each recipe ingredient to their own lists
                compare_list.add(i.getName());
            }

            if (size == compare_list.size()) { // Make sure size is correct first, save processing time

                user_list = new ArrayList(Arrays.asList(i_list)); // Re-init user_list (was removing all elements before)
                user_list.retainAll(compare_list);         // Find all common elements

                if (user_list.size() == size) {            // If every element is common, lists are match
                    the_formula = f;                       // Set this var to the formula
                    return true;
                }
            }
        }
        the_formula = null;
        return false;
    }

    /**
     * Presents a UI for the Player to add ingredients
     *
     */
    public void alchemy_crafting_UI() {

        String ing = IO.T_BL+"Ingredient";
        String ing1 = " - ";            // Placeholder names
        String ing2 = " - ";
        String ing3 = " - ";
        String ing4 = " - ";
        String ing5 = " - ";
        String formula = IO.T_R+"? ? ?"+IO.T_RS;       // Placeholder - this will change dynamically - if Player knows formula it will be shown in text

        if (ingredient1 != null) {      // Set names if Ingredients exist
            ing1 = ingredient1.getName();
        }
        if (ingredient2 != null) {
            ing2 = ingredient2.getName();
        }
        if (ingredient3 != null) {
            ing3 = ingredient3.getName();
        }
        if (ingredient4 != null) {
            ing4 = ingredient4.getName();
        }
        if (ingredient5 != null) {
            ing5 = ingredient5.getName();
        }
        if (the_formula != null) {        // If formula has been found, display the name
            formula = IO.T_G+the_formula.getName()+IO.T_RS+"  <<  Enter ["+IO.T_Y+"Z"+IO.T_RS+"] to craft!";
        } else {
            formula = IO.T_R+"? ? ?"+IO.T_RS;
        }


        System.out.println("\n\n\n\n\n\n\n\n\n");
        System.out.println("+►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄+");
        System.out.println("|                       ALCHEMY CRAFTING                         |");
        System.out.println("+►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄+");
        System.out.format(leftAlignFormat, ing + " 1"+IO.T_RS, ing1);
        System.out.println("+ -------------+------------------------------------------------ +");
        System.out.format(leftAlignFormat, ing + " 2"+IO.T_RS, ing2);
        System.out.println("+ -------------+------------------------------------------------ +");
        System.out.format(leftAlignFormat, ing + " 3"+IO.T_RS, ing3);
        System.out.println("+ -------------+------------------------------------------------ +");
        System.out.format(leftAlignFormat, ing + " 4"+IO.T_RS, ing4);
        System.out.println("+ -------------+------------------------------------------------ +");
        System.out.format(leftAlignFormat, ing + " 5"+IO.T_RS, ing5);
        System.out.println("+ -------------+------------------------------------------------ +");
        System.out.println("|  > Enter up to 5 ingredients                                   |");
        System.out.println("+ -------------------------------------------------------------- +");
        System.out.format(middleAlign,formula);
        System.out.println();
        System.out.println("+►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►+◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►+");


    }

    /**
     *
     * Getting and setting Ingredients
     *
     */

    public boolean ing1() {
        if (ingredient1 == null) {
            return true;
        }
        return false;
    }
    public boolean ing2() {
        if (ingredient2 == null) {
            return true;
        }
        return false;
    }
    public boolean ing3() {
        if (ingredient3 == null) {
            return true;
        }
        return false;
    }
    public boolean ing4() {
        if (ingredient4 == null) {
            return true;
        }
        return false;
    }public boolean ing5() {
        if (ingredient5 == null) {
            return true;
        }
        return false;
    }


    public Ingredient getIngredient1() {
        return ingredient1;
    }

    public Ingredient getIngredient2() {
        return ingredient2;
    }

    public Ingredient getIngredient3() {
        return ingredient3;
    }

    public Ingredient getIngredient4() {
        return ingredient4;
    }

    public Ingredient getIngredient5() {
        return ingredient5;
    }

    public void setIngredient1(Ingredient ingredient) {
        this.ingredient1 = ingredient;
    }

    public void setIngredient2(Ingredient ingredient) {
        this.ingredient2 = ingredient;
    }

    public void setIngredient3(Ingredient ingredient) {
        this.ingredient3 = ingredient;
    }

    public void setIngredient4(Ingredient ingredient) {
        this.ingredient4 = ingredient;
    }

    public void setIngredient5(Ingredient ingredient) {
        this.ingredient5 = ingredient;
    }

    /**
     * Resets all ingredients back to null
     *
     */
    public void reset_ingredients() {
        this.ingredient1=null;
        this.ingredient2=null;
        this.ingredient3=null;
        this.ingredient4=null;
        this.ingredient5=null;
        the_formula = null;
    }
}
