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
 * Class CommandPick implements command pick.
 * 
 * @author MarleyWins
 */
public class CommandPick implements IPrikaz {

    private static final String NAZEV = "pick";
    private HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan game plan
     */
    public CommandPick(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * This comand enables player to pick up an item that could be picked or if there is space in the inventory. 
     *
     * @param parametry - name of the targeted item
     * @return message to player
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0 || parametry.length > 1) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return plan.getPlayer().getName() + " ,what do you want to pick up?";
        }

        String target = parametry[0];

        if (plan.getAktualniProstor().isItemIn(target)) {
            
            if(!plan.getAktualniProstor().returnItem(target).isCollectable()){
                return "I am sorry " + plan.getPlayer().getName() + ", I'm afraid I can't do that. (**Hal 9000 - flies away**)";
            }           

            if(plan.getPlayer().getInventorySize() > plan.getPlayer().getInventory().size()){
                if(plan.getPlayer().insertItem(plan.getAktualniProstor().returnItem(target))){
                    plan.getAktualniProstor().removeItem(target);
                    return target +" succesfully placed into your inventory, " + plan.getPlayer().getName();
                }else{
                    return "***Something went wrong.***";
                }
            }else{
                return plan.getPlayer().getName() + " ,you dont have any space left in your bag.";
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
