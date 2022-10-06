package com.company;

import java.util.HashMap;

/**
 * Sphinx will ask the player a riddle, if they get it right, they may pass with a gift, if not they have to fight it
 * They are high in intelligence and health, lower in dexterity and attack
 */
public class Sphinx extends Enemy implements InventoryManagement, java.io.Serializable {

    private HashMap<String, String> riddles = new HashMap<>();
    private Dice d1 = new Dice();

    public Sphinx(String name, String type, Character player) {
        super(name, type, player, 1);
        super.setAttack(d1.manualDiceRoll(player.getAttack().getStat_level()));
        super.setDefence(d1.manualDiceRoll(player.getAttack().getStat_level()+4));
        super.setStrength(d1.manualDiceRoll(player.getAttack().getStat_level()+1));
        super.setDexterity(d1.manualDiceRoll((int) ((int)player.getDexterity().getStat_level()* 0.2)));
        super.setInitiative(d1.manualDiceRoll(player.getInitiative().getStat_level()+2));
        super.setIntelligence(d1.manualDiceRollBetween(player.getLevel().getStat_level()+2, player.getLevel().getStat_level()+6));
        super.setMaxHealth(d1.manualDiceRollBetween(player.getLevel().getStat_level()+2, player.getLevel().getStat_level()+6));
        super.getHealth().setStat_level(getMaxHealth().getStat_level());
        super.setType("Sphinx");
        super.setLevel(calculate_level());
        populate_riddles();
        setInventory(4);
    }

    public void populate_riddles() {
        riddles.put("I never was, am always to be." +
                " No one ever saw me, nor ever will," +
                " and yet, I am the confidence of all who " +
                "live and breathe. What am I?",
                // Question

                "Tomorrow");
                // Answer
        riddles.put("There are two sisters: " +
                "one gives birth to the other and she, " +
                "in turn, gives birth to the first." +
                " Who are the two sisters?",
                // Question

                "Day and Night");
                // Answer
        riddles.put("What has rivers with no water, forests, " +
                "but no trees, and cities with no buildings?"
                // Question

               ,"Map");
                // Answer
        riddles.put("This thing all things devours, birds, beasts, trees, flowers.\n" +
                "Gnaws iron, bites steel,\n" +
                "Grinds hard stone to meal.\n" +
                "Slays kings, ruins towns,\n" +
                "and beats high mountains down.\n"
                // Question

                ,"Time");
                // Answer
    }

    /**
     * Returns a random question from the HashMap riddles
     * @return
     */
    public String display_riddle() {
        String[] questions = riddles.keySet().toArray(new String[0]);
        // String[] answers = riddles.values().toArray(new String[0]);

        return questions[d1.manualDiceRoll(questions.length)-1];
    }

    public boolean answerRiddle(String playerGuess) {
        return true;
    }

    /**
     * Ideally, the guess and answer will be identical
     * Although, the player will be allowed 1 char mistake
     * @param answer
     * @param guess
     * @return - Boolean
     */
    public boolean guess_riddle(String answer, String guess) {
        int allowance = 1;

        if (guess.contains(answer)) {                               // True if guess contains the answer i.e. "The Map" will be accepted for "Map"
            return true;
        }

        if (answer.toLowerCase().trim().equals(guess.toLowerCase().trim())) {
            return true;
        }

        if(answer.length() == guess.length() + 1 || answer.length() == guess.length() - 1 || answer.length() == guess.length()) { // if answer is same length +- 1 as guess
            for(int i = 0; i < answer.length(); i++) {
                if(answer.charAt(i) != answer.charAt(i)) {          // Check each chars in the strings and see if they are the same
                    allowance--;                                    // reduce num of mistakes
                    if(allowance <= 0) {                            // If you have no mistakes left, you lose the game
                        return false;                               // return false
                    }
                }
            }
        }
        return true;
    }

    public String toString() {
        return "A Half human, half lion female.. she guards something precious..";
    }
}
