/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.HerniPlan;
import logika.Item;
import util.ObserverZmenyProstoru;

/**
 * Vytváří sekci s popisem a obrázky předmětů
 * @author MarleyWins
 */
public class SectionItems implements ObserverZmenyProstoru{
    
    private HerniPlan plan;
    private GridPane imageGrid;
    private VBox vBox;
    /**
     * Zobrazuje mřížku s předměty
     * @param plan 
     */
    public SectionItems(HerniPlan plan){
        plan.zaregistrujPozorovatele(this);
        this.plan = plan;
        vBox = new VBox();
        vBox.setPrefWidth(Region.USE_COMPUTED_SIZE);
        vBox.setAlignment(Pos.TOP_CENTER);
        init();
    }
    /**
     * Inicializuje label a seznam předmětů
     */
    private void init(){
        
        sectionLabel();
        
        imageGrid = new GridPane();
        vBox.getChildren().add(imageGrid);
        imageGrid.setHgap(2.00);
        imageGrid.setVgap(2.00);
        imageGrid.setAlignment(Pos.CENTER);
        
        loadResources();
    }
/**
 * Nastaví nadpis sekce + styl
 */
    private void sectionLabel() {
        Label itemsLabel = new Label("Room contents");
        itemsLabel.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 15.00));
        itemsLabel.setAlignment(Pos.CENTER);
        
        vBox.getChildren().add(itemsLabel);
    }
/**
 * Načte z inventáře obrázky předměty 
 */
    private void loadResources() {
        int coll = 0;
        int row = 0;
        
        for(Item item : plan.getAktualniProstor().getItems()){
            if (coll == 3) {
                row++;
                coll = 0;
            }
            imageGrid.add(item.getPicture(), coll, row);
            coll++;
        }
    }
    /**
     * Vrací sekci k dalšímu použití
     * @return vrací ke zpracování
     */
    public VBox getSection(){
        return vBox;
    }

    @Override
    public void refresh() {
        imageGrid.getChildren().clear();
        loadResources();
    }
    
}
