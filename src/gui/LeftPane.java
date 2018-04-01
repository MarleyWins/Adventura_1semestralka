/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import logika.HerniPlan;

/**
 * Vytvoří levý panel
 * @author MarleyWins
 */
public class LeftPane {

    private HerniPlan plan;
    private VBox vBox;
/**
 * Konstruktor pro levý panel - zobrazuje obsah místnosti a východy
 * @param plan herní plán
 */
    public LeftPane(HerniPlan plan) {
        this.plan = plan;
        this.vBox = new VBox();
        vBox.setPrefWidth(Region.USE_COMPUTED_SIZE);
        vBox.setMinHeight(400.00);
        init();
    }
/**
 * Volá sekce levého panelu
 */
    private void init() {

        SectionItems sectionItems = new SectionItems(plan);
        vBox.getChildren().add(sectionItems.getSection());
             
        SectionExits sectionExits = new SectionExits(plan);
        vBox.getChildren().add(sectionExits.getSection());        
    }
/**
 * Vrací panel pro další zpracování
 * @return uzel levý pane
 */
    public VBox getLeftPane() {
        return vBox;
    }

}
