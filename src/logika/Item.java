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

import data.DataStream;
import java.util.Objects;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class serves as an ingame item.
 *
 * @author MarleyWins
 */
public class Item {

    private String name;
    private String info;
    private boolean collectable;
    private char type;
    private int loyaltyGain;
    private int bonusGain;
    private ImageView picture;
    private final double dimensions = 80.00;

    /**
     * Constructor for item - uncollectable.
     *
     * @param name - name of the item.
     * @param info - lore description.
     */
    public Item(String name, String info) {
        this.name = name;
        this.info = info;
        this.collectable = false;
        this.picture = new ImageView(new Image((DataStream.class.getResourceAsStream("/data/"+"icon.png")), dimensions, dimensions, false, false));
        Tooltip.install(picture, new Tooltip(name + "\n" + info));

    }

    /**
     * Constructor for item - collectable
     *
     * @param name - name of the item
     * @param info - lore description
     * @param type - ingame fraction, R/H/N - Robots/Humans/Neutral
     * @param loyaltyGain - gained loyalty points
     * @param bonusGain - gained bonus points from NPC
     */
    public Item(String name, String info, char type, int loyaltyGain, int bonusGain) {
        this.name = name;
        this.info = info;
        this.type = type;
        this.loyaltyGain = loyaltyGain;
        this.bonusGain = bonusGain;
        this.collectable = true;
        this.picture = new ImageView(new Image((DataStream.class.getResourceAsStream("/data/"+"icon.png")), dimensions, dimensions, false, false));
        Tooltip.install(picture, new Tooltip(name + "\n" + info));
    }
    
    

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public boolean isCollectable() {
        return collectable;
    }

    public char getType() {
        return type;
    }

    public int getLoyaltyGain() {
        return loyaltyGain;
    }

    public int getBonusGain() {
        return bonusGain;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    public ImageView getPicture(){
        return picture;
    }

}
