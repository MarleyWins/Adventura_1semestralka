/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.HerniPlan;
import logika.Item;
import util.ObserverPlayerChange;

/**
 * Vytváří zobrazení inventáře
 *
 * @author MarleyWins
 */
public class RightPane implements ObserverPlayerChange {

    private HerniPlan plan;
    private GridPane imageGrid;
    private VBox vBox;

    /**
     * Konstruktor pro pravý panel - zobrazuje inventář
     *
     * @param plan herní plán
     */
    public RightPane(HerniPlan plan) {
        this.plan = plan;
        vBox = new VBox();
        vBox.setPrefWidth(246.00);
        vBox.setMinHeight(400.00);
        vBox.setAlignment(Pos.TOP_CENTER);

        init();
    }

    /**
     * Inicializuje mřížku s obrázky
     */
    private void init() {
        plan.getPlayer().zaregistrujPozorovatele(this);
        sectionLabel();
        imageGrid = new GridPane();
        vBox.getChildren().add(imageGrid);
        imageGrid.setHgap(2.00);
        imageGrid.setVgap(2.00);
        imageGrid.setAlignment(Pos.CENTER);

        loadResources();
    }

    /**
     * Nastaví nadpis sekce
     */
    private void sectionLabel() {
        Label itemsLabel = new Label("Inventory");
        itemsLabel.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 15.00));
        itemsLabel.setAlignment(Pos.CENTER);

        vBox.getChildren().add(itemsLabel);
    }

    /**
     * Načte zdroje
     */
    private void loadResources() {
        int coll = 0;
        int row = 0;

        for (Item item : plan.getPlayer().getInventory()) {
            if (coll == 3) {
                row++;
                coll = 0;
            }
            imageGrid.add(item.getPicture(), coll, row);
            //System.out.println("RIGHT PANE VLOZENO: " + item.getName());
            coll++;
        }
    }

    /**
     * Vrací sekci k dalšímu použití
     *
     * @return ke zpracování
     *
     */
    public VBox getSection() {
        return vBox;
    }

    @Override
    public void refresh() {
        // System.out.println("REFRESH RIGHT PANE");
        imageGrid.getChildren().clear();
        loadResources();
    }

}
