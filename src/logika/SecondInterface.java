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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;
import main.Start;

/**
 *
 * @author MarleyWins
 */
public class SecondInterface implements ITextInterface{
    
    public SecondInterface() {
    }

    /**
     * Zpracuje příkaz
     *
     * @param reason Reason of the dialog, displayed as Header
     * @return vrací načtenou hodnotu
     */
    @Override
    public String useInput(String reason) {
        
        if (Start.getVersion().equals("GUI")) {

            TextInputDialog dialog = new TextInputDialog();          
            
            dialog.setTitle("Choice");    
            dialog.setHeaderText(reason);
            dialog.setContentText("Your choice:");            
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                return result.get();
            }else{
                return "UnknownEntity";
            }

        } else {
            return readInt();
        }
        
    }

    /**
     * Vypise prompt na konzoli
     *
     * @param prompta text k vypsání
     */
    @Override
    public void printPrompt(String prompta) {
        
        if (Start.getVersion().equals("GUI")) {
            Start.addToText(prompta);
        } else {
            System.out.print(prompta);
        }
        
    }

    /**
     * Precte vstup z konzole
     *
     * @return načtený vstup
     */
    private String readInt() {
        String vstupniRadek = "";
        //System.out.print(prompta);        // vypíše se prompt
        System.out.print(">");
        BufferedReader vstup
                = new BufferedReader(new InputStreamReader(System.in));
        try {
            vstupniRadek = vstup.readLine();
        } catch (java.io.IOException exc) {
            System.out.println("Vyskytla se chyba během čtení z konzole "
                    + exc.getMessage());
        }
        return vstupniRadek;
    }
    
}
