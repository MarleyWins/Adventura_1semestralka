/*
 * Copyright (C) 2017 MarleyWins
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package logika;

/**
 * Class EndingSequence is called at the end of the game.
 * Results in case specific ending.
 * 
 * @author MarleyWins
 */
public class EndingSequence {
/**
 * Method stars final decision making. Enables to choose final decision. 
 * 
 * @param player - current player
 * @param interface2 - relyes link on second text interface
 * @return message to player
 */
    public String endingText(Player player, SecondInterface interface2) {
        
        if(player.getLoyaltyHumCt() + player.getLoyaltyRobCt() == 0){
            return "You are a true neutral. But you are also useless to this world. I expell you " + player.getName() + "." 
                    + "\n             --\"Hal 9000\"";
        }

        interface2.printPrompt("Now you have to make your final decision. Who are you?!");
        interface2.printPrompt("\n(1) I want to be a robot!");
        interface2.printPrompt("\n(2) I want to remain neutral ...");
        interface2.printPrompt("\n(3) I want to be a human!");

        interface2.printPrompt("\nYour choice: ");
        

        while (true) {
            try {
                int choice = Integer.parseInt(interface2.useInput());
                if (choice > 3 || choice < 1) {
                    interface2.printPrompt("\n*** Only use numbers between 1-3 " + player.getName() + "! ***\n");
                } else {                    
                    switch (choice) {

                        case 1:
                            return robotEnds(player.getLoyaltyRobCt(), player.getLoyaltyHumCt());
                        case 2:
                            return neutralEnds(player.getLoyaltyRobCt(), player.getLoyaltyHumCt());
                        case 3:
                            return hoomanEnds(player.getLoyaltyRobCt(), player.getLoyaltyHumCt());
                        default:
                            return "I am so so sorry, but something went terribly wrong.";

                    }

                }

            } catch (NumberFormatException e) {
                interface2.printPrompt("\n*** Only use numbers," + player.getName() + " ***\n");
            }

        }

    }
/**
 * Method for robot ending.
 * 
 * @param robots - total points in robots
 * @param humans - total points in humans
 * @return good or bad outro
 */
    private String robotEnds(int robots, int humans) {

        String goodOutro;
        String badOutro;

        goodOutro = "Wery well. You are robot now.";
        badOutro = "You have died! *Malfuntion in core procedures, more human than robot.*";

        
        
        if (robots >= humans) {

            return goodOutro;

        } else {
            return badOutro;
        }

    }
/**
 * Method for neutral ending.
 * @param robots - total points in robots
 * @param humans - total points in humans
 * @return good or bad outro
 */
    private String neutralEnds(int robots, int humans) {

        String goodOutro;
        String badOutro;

        goodOutro = "You have became a god of this world. May your wisdom guide us all.";
        badOutro = "You are destined to wonder endlesly.";

        if (robots == humans) {
            return goodOutro;
        } else {
            return badOutro;
        }
    }
/**
 * Method for human ending.
 * @param robots - total points in robots
 * @param humans - total points in humans
 * @return good or bad outro
 */
    private String hoomanEnds(int robots, int humans) {
        String goodOutro;
        String badOutro;

        goodOutro = "Wery well. You are a living breathig person now.";
        badOutro = "You have died. CPU is not heart.";

        if (robots <= humans) {
            return goodOutro;
        } else {
            return badOutro;
        }
    }
}
