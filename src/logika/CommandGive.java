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
 * Class CommandGive implements command give.
 *
 * @author MarleyWins
 */
public class CommandGive implements IPrikaz {

    private static final String NAZEV = "give";
    private HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan game plan
     */
    public CommandGive(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * This comand enables player to give an item to npc and grants bonus points
     * if the item is from the same fraction as npc. Also grants loyalty points
     * for giving if npc does not mind a gifted item.
     *
     * @param parametry - name of the targeted npc and item
     * @return message to player
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length != 2) {
            return plan.getPlayer().getName() + " , to whom should I give what? \n Remember it is give WHAT WHO";
        }

        String targetItem = parametry[0];
        String targetNpc = parametry[1];

        Item item;
        Npc npc;

        if (plan.getPlayer().isInInventory(targetItem)) {
            if (plan.getAktualniProstor().isNpcIn(targetNpc)) {

                item = plan.getPlayer().removeItem(targetItem);
                npc = plan.getAktualniProstor().returnNpc(targetNpc);

                if (item.getType() == npc.getLoyalty()) {

                    if (item.getType() == 'r') {
                        plan.getPlayer().addLoyaltyRobCt(npc.getBonus() + item.getBonusGain() + item.getLoyaltyGain());
                        return "Gained loyalty to robots.";
                    } else {
                        plan.getPlayer().addLoyaltyHumCt(npc.getBonus() + item.getBonusGain() + item.getLoyaltyGain());
                        return "Gained loyalty to hoomans.";
                    }

                } else {

                    String fraction;

                    if (npc.getLoyalty() == 'r') {
                        fraction = "robots.";
                        plan.getPlayer().addLoyaltyRobCt(item.getBonusGain());
                    } else {
                        fraction = "humans.";
                        plan.getPlayer().addLoyaltyHumCt(item.getBonusGain());
                    }
                    return npc.getName() + " did not appreciated so much. " + plan.getPlayer().getName() + ".\n But you have gain little loyalty towards " + fraction;
                }

            } else {
                return plan.getPlayer().getName() + ", there is nobody with this name (* " + targetNpc + " *).";
            }

        } else {
            return plan.getPlayer().getName() + ", you don't have such an item ( *" + targetItem + "* ) with you.";
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
