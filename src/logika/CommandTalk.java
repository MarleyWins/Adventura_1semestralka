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

import java.util.Map;

/**
 * Class CommandTalk implements command talk.
 *
 * @author MarleyWins
 */
public class CommandTalk implements IPrikaz {

    private static final String NAZEV = "talk";
    private HerniPlan plan;

    /**
     * Class´s Constructor
     *
     * @param plan game plan for talking
     */
    public CommandTalk(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * This command enables player to talk with npc.
     *
     * @param parametry - npc´s name
     * @return message to player
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0 || parametry.length > 1) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "You have to tell me with whom to speak, " + plan.getPlayer().getName();
        }

        String npcName = parametry[0];

        if (plan.getAktualniProstor().isNpcIn(npcName)) {
            Npc npc = plan.getAktualniProstor().returnNpc(npcName);

            if (npc.getOpen()) {
                return doConversation(npcName);
            } else {
                return "Does not react to you.";
            }

        } else {
            return "You have the wrong name. Please check it, " + plan.getPlayer().getName();
        }

    }
/**
 * This method starts conversation with npc and enables to choose one of the five decisions.
 * 
 * @param npcName - name of targeted npc
 * @return message to player
 */
    private String doConversation(String npcName) {

        SecondInterface interface2 = plan.getInterface2();
        Npc npc = plan.getAktualniProstor().returnNpc(npcName);
        Map<Integer, String> conversation;
        conversation = npc.getConversation();

        interface2.printPrompt(npc.getIntro() + "\n");

        String convOpt = "Decide:";
        int i = 1;

        for (String option : conversation.values()) {
            convOpt += "\n     " + "(" + i + ".)" + option + " ";
            i++;
        }

        interface2.printPrompt(convOpt + "\n");

        while (true) {
            try {
                int choice = Integer.parseInt(interface2.useInput());
                if (choice > 5 || choice < 1) {
                    interface2.printPrompt(convOpt);
                    interface2.printPrompt("\n*** Only use numbers between 1-5 " + plan.getPlayer().getName() + "! ***\n");
                } else {
                    char result = evaluateConversation(choice, npc, interface2);
                    //interface2.printPrompt("\n***Roboti: " + plan.getPlayer().getLoyaltyRobCt() + "Lidi: " + plan.getPlayer().getLoyaltyHumCt() + "***\n");
                    if (result == 'n') {
                        return "Well..ok than.";
                    } else if (result == 'r') {
                        switch (npc.getLoyalty()) {
                            case 'r':
                                return "Good! I knew there is something more in you " + plan.getPlayer().getName() + ". I wish you safe travel.";
                            case 'h':
                                return "Look " + plan.getPlayer().getName() + "...Maybe you should go now.";
                            case 'n':
                                return "Sure, sure.";
                            default:
                                return "No idea, what just happened.";
                        }
                    } else if (result == 'h') {
                        switch (npc.getLoyalty()) {
                            case 'r':
                                return "Look " + plan.getPlayer().getName() + "...Maybe you should go now.";
                            case 'h':
                                return "Good! I knew there is something more in you " + plan.getPlayer().getName() + ". I wish you safe travel.";
                            case 'n':
                                return "Sure, sure.";
                            default:
                                return "No idea, what just happened.";
                        }
                    } else {
                        return "Sure, sure";
                    }

                }
            } catch (NumberFormatException e) {
                interface2.printPrompt(convOpt);
                interface2.printPrompt("\n*** Only use numbers," + plan.getPlayer().getName() + " ***\n");
            }
        }

    }
/**
 * This method will grant player loyalty points according to which decision have been made. 
 * @param choice - player made
 * @param npc - targeted npc
 * @param interface2 - relyes link on second text interface
 * @return char 
 */
    private char evaluateConversation(int choice, Npc npc, SecondInterface interface2) {

        npc.setClose();

        switch (choice) {
            case 1:
                plan.getPlayer().addLoyaltyRobCt(3 + npc.getBonus());
                interface2.printPrompt("Gained little loyalty to robots.\n");
                return 'r';
            case 2:
                plan.getPlayer().addLoyaltyRobCt(5 + npc.getBonus());
                interface2.printPrompt("Gained loyalty to robots.\n");
                return 'r';
            case 3:
                interface2.printPrompt("Hmm, interesting choice.\n");
                return 'n';
            case 4:
                plan.getPlayer().addLoyaltyHumCt(3 + npc.getBonus());
                interface2.printPrompt("Gained little loyalty to hoomans.\n");
                return 'h';
            case 5:
                plan.getPlayer().addLoyaltyHumCt(5 + npc.getBonus());
                interface2.printPrompt("Gained loyalty to hoomans.\n");
                return 'h';
            default:
                interface2.printPrompt("Something went wrong, Danny994 was 'round.\n");
                return 'n';
        }

    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
