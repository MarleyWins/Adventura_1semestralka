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
 * Class CommandDrop implements command drop.
 * 
 * @author MarleyWins
 */
public class CommandDrop implements IPrikaz {

    private static final String NAZEV = "drop";
    private HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan game plan
     */
    public CommandDrop(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * This command enables player to hug npc and grants bonus points for hugging
     * if npc does not mind a hug.
     *
     * @param parametry - name of the targeted npc
     * @return message to player
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0 || parametry.length > 1) {
            return plan.getPlayer().getName() + " ,what do you want to drop?";
        }

        String target = parametry[0];

        if (plan.getPlayer().isInInventory(target)) {

            if (!plan.getAktualniProstor().addItem(plan.getPlayer().removeItem(target))) {
                return "***Something went wrong.***";
            } else {
                return target + " was dropped from your inventory " + plan.getPlayer().getName();
            }

        } else {
            return plan.getPlayer().getName() + ", you don't have such an item.";
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
