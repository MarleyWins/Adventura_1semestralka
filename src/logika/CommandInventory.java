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
 * Class CommandInventory implements command inventory.
 * 
 * @author MarleyWins
 */
public class CommandInventory implements IPrikaz {
    
    private static final String NAZEV = "inventory";
    private HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan game plan
     */
    public CommandInventory(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * This comand enables player to inspect the inventory.
     *
     * @param parametry - inventory
     * @return description of inventory
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return plan.getPlayer().getName() + ", write just inventory";
        }

        return plan.getPlayer().descriptionInventory();    

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
