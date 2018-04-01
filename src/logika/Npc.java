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

import java.util.HashMap;
import java.util.Map;

/**
 * This class servers as an ingame NPC.
 * 
 * @author MarleyWins
 */
public class Npc {

    private String name;
    private String intro;
    private char loyalty;
    private int bonus;
    private boolean reaction;
    private Map<Integer, String> conversation;
    boolean open;
/**
 * Constructor inicializing npc´s parameters. 
 * 
 * @param name - npc´s name
 * @param loyalty - char of the fraction
 * @param intro - description of npc
 * @param bonus - bonus points
 * @param reaction - npc´s reaction to player´s decision
 */
    public Npc(String name, char loyalty, String intro, int bonus, boolean reaction) {

        this.name = name;
        this.loyalty = loyalty;
        this.intro = intro;
        this.bonus = bonus;
        this.reaction = reaction;
        conversation = new HashMap<>();
        open = true;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setLoyalty(char loyalty) {
        this.loyalty = loyalty;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public String getName() {
        return name;
    }

    public String getIntro() {
        return intro;
    }

    public char getLoyalty() {
        return loyalty;
    }

    public int getBonus() {
        return bonus;
    }
    /**
     * Returns Npc´s reaction to your decision.
     * 
     * @return true if hugger, false if hitter
     */
    public boolean getReaction() {
        return reaction;

    }

    public Map<Integer, String> getConversation() {
        return conversation;
    }
    /**
     * Adds conversation option
     * 
     * @param order - 1 = r3, 2= r5, 3=n, 4=h3, 5=h5
     * @param text of the conversation
     */
    public void addConversationOpt(int order, String text) {
        conversation.put(order, text);
    }
    
    /**
     * Returns NPCs interaction status.
     * 
     * @return true if avaible
     */
    public boolean getOpen(){
        return open;
    }
    
    public void setClose(){
        open = false;
    }
}
