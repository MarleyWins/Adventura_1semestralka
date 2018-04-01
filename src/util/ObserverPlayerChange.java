/*
 * Vypracovani zkouskoveho prikladu Java 4T101
 * Autor: Marija Lebeděva  * 
 */
package util;

/**
 *Návrhový vzor observer
 * @author MarleyWins
 */
public interface ObserverPlayerChange {

    /**
     * Metoda, ve které proběhne aktualizace pozorovatele, je volána
     * prostřednictvím metody upozorniPozorovatele z rozhraní
     * SubjektZmenyProstoru
     *
     */
    void refresh();

}
