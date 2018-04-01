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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.*;

/**
 * This class serves as a character informations carrier. Including inventory and
 * "loyalty counts".
 *
 * @author MarleyWins
 * @version 1.0
 */
public class Player implements SubjektPlayerChange{

    private String name;
    private int inventorySize;
    private Set<Item> inventory;
    private int loyaltyRobCt;
    private int loyaltyHumCt;
    private List<ObserverPlayerChange> observerListPlayer;

    /**
     * Constructor for the Player class. Inicializing all used variables.
     */
    public Player() {

        this.name = "Danny";
        this.inventorySize = 4;
        this.loyaltyRobCt = this.loyaltyHumCt = 0;
        this.inventory = new HashSet<>();
        this.observerListPlayer = new ArrayList<>();

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }

    /**
     * Adds set value to the total loyalty count for Robots.
     *
     * @param gain - ads given value to the count
     */
    public void addLoyaltyRobCt(int gain) {
        this.loyaltyRobCt += gain;
    }

    /**
     * Adds set value to the total loyalty count for Huumans.
     *
     * @param gain - ads given value to the count
     */
    public void addLoyaltyHumCt(int gain) {
        this.loyaltyHumCt += gain;
    }

    public String getName() {
        return name;
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public Set<Item> getInventory() {
        return inventory;
    }

    public int getLoyaltyRobCt() {
        return loyaltyRobCt;
    }

    public int getLoyaltyHumCt() {
        return loyaltyHumCt;
    }

    /**
     * Method inserts item into the inventory.
     *
     * @param item - inserted item
     * @return true if successfull
     */
    public boolean insertItem(Item item) {
        
        if(inventory.add(item)){
          notifyObservers();
          return true;
        }else{
          return false;
        }
    }

    /**
     * Removes item from the inventory.
     *
     * @param name - name of the removing item
     * @return removed item
     */
    public Item removeItem(String name) {

        Item removing = null;
        for (Item item : inventory) {
            if (item.getName().equals(name)) {
                removing = item;
                inventory.remove(removing);
                break;
            }
        }
        this.notifyObservers();
        return removing;
    }

    /**
     * Returns if the item is in the inventory.
     *
     * @param name - name of the selected item
     * @return found item
     */
    public boolean isInInventory(String name) {
        boolean found = false;
        for (Item item : inventory) {
            if (item.getName().equals(name)) {
                found = true;
                break;
            }
        }
        return found;

    }

    /**
     * Method returns list of items that are currently in the inventory.
     *
     * @return list of items
     */
    public String descriptionInventory() {
        String returnedItem = "Inventory:";
        
        if(inventory.isEmpty()){
            returnedItem += " Empty";            
        }else{
        for (Item next : inventory) {
            returnedItem += " " + next.getName();
        }
        }
        return returnedItem;
    }

    public Item returnItem(String name) {

        for (Item item : inventory) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        this.notifyObservers();
        return null;

    }

    @Override
    public void zaregistrujPozorovatele(ObserverPlayerChange pozorovatel) {
        System.out.println("Registrace hrace");
        observerListPlayer.add(pozorovatel);
    }

    @Override
    public void odregistrujPozorovatele(ObserverPlayerChange pozorovatel) {
        observerListPlayer.remove(pozorovatel);
    }

    @Override
    public void notifyObservers() {
        for(ObserverPlayerChange observer : observerListPlayer){
            observer.refresh();
        }
    }

}

