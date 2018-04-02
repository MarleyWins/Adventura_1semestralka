/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.HerniPlan;
import main.Start;
import util.ObserverZmenyProstoru;

/**
 * Vytváří sekci s popisem východu a implementuje pozorovatele změny prostoru
 *
 * @author MarleyWins
 */
public class SectionExits implements ObserverZmenyProstoru {
    
    private final HerniPlan plan;
    private ListView<String> exitList;
    private ObservableList<String> data;
    private VBox vBox;

    /**
     * Konstruktor sekce východů
     *
     * @param plan herní plán
     */
    public SectionExits(HerniPlan plan) {
        this.plan = plan;
        vBox = new VBox();
        vBox.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(3.00);
        init();
    }

    /**
     * inicializuje label a seznam místností
     */
    private void init() {
        plan.zaregistrujPozorovatele(this);
        exitList = new ListView<>();
        data = FXCollections.observableArrayList();
        exitList.setItems(data);
        sectionLabel();
        loadData();
        exitList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            /**
             * Při kliknutím na seznam zpracuje prikaz go.
             */
            public void handle(MouseEvent event) {
                String clickedName = exitList.getSelectionModel().getSelectedItem();
                //System.out.println(clickedName);
                String text = plan.getHra().zpracujPrikaz("go " + clickedName);
                Start.addToText("\n go " + clickedName + "\n");
                Start.addToText("\n" + text + "\n");
            }
        });
        vBox.getChildren().add(exitList);
    }

    /**
     * Načte seznam východů z auktuálního prostoru
     */
    private void loadData() {
        String exits = plan.getAktualniProstor().exitLists();
        
        String[] separated = exits.split(" ");
        for (int i = 1; i < separated.length; i++) {
            data.add(separated[i]);
        }
    }

    /**
     * Nastaví popisek sekce + styl
     */
    private void sectionLabel() {
        Label itemsLabel = new Label("Exits");
        itemsLabel.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 15.00));
        itemsLabel.setAlignment(Pos.CENTER);
        
        vBox.getChildren().add(itemsLabel);
    }

    /**
     * Vrací sekci k dalšímu použití
     *
     * @return ke zpracování
     */
    public VBox getSection() {
        return vBox;
    }
    
    @Override
    public void refresh() {
        // System.out.println("CHANGE LIST EXITS");
        data.clear();
        loadData();
    }
    
}
