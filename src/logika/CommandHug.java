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
 * Class CommandHug implements command hug.
 * 
 * @author MarleyWins
 */
public class CommandHug implements IPrikaz {

    private static final String NAZEV = "hug";
    private HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan game plan
     */
    public CommandHug(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * This comand enables player to hug npc and grants bonus points for hugging if npc does not mind a hug.     
     *
     * @param parametry - name of the targeted npc
     * @return message to player
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0 || parametry.length > 1) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return plan.getPlayer().getName() + " ,who do you want to hug?";
        }

        String target = parametry[0];

        Npc npc;

        if (plan.getAktualniProstor().isNpcIn(target)) {
            npc = plan.getAktualniProstor().returnNpc(target);
            return plan.getHH().resolve(plan.getPlayer(), npc, plan, true);
        } else {
            return "There is nobody with this name.";
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
