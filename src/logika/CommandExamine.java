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
 * Class CommandExamine implements command examine.
 *
 * @author MarleyWins
 */
public class CommandExamine implements IPrikaz {

    private static final String NAZEV = "examine";
    private HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan game plan
     */
    public CommandExamine(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * This command enables player to examine an item in the room or in the
     * inventroy and gives you information about an item.
     *
     * @param parametry - name of the targeted item.
     * @return message to player
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0 || parametry.length > 1) {
            return plan.getPlayer().getName() + " ,what do you want to examine?";
        }

        String target = parametry[0];

        if (plan.getAktualniProstor().isItemIn(target) || plan.getPlayer().isInInventory(target)) {

            if (plan.getAktualniProstor().isItemIn(target)) {
                return plan.getAktualniProstor().returnItem(target).getInfo();
            } else {
                return plan.getPlayer().returnItem(target).getInfo();
            }

        } else {
            return plan.getPlayer().getName() + " ,such item is not here.";
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
