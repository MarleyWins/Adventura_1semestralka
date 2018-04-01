/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.DataStream;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.HerniPlan;
import util.ObserverZmenyProstoru;

/**
 *
 * @author MarleyWins
 */
public class TopPane implements ObserverZmenyProstoru {
    
    private HerniPlan plan;
    private HBox hBox;
    private AnchorPane mapPane;
    private Circle circle;
    
    public TopPane (HerniPlan plan){
        this.plan = plan;
        hBox = new HBox();
        init();
    }
    
    private void init(){
        plan.zaregistrujPozorovatele(this);
        hBox.setAlignment(Pos.CENTER);
        Image mapImage = new Image(DataStream.class.getResourceAsStream("/data/map.jpg"), 900.00 , 300.00, false, true);
        ImageView map = new ImageView(mapImage);
        
        mapPane = new AnchorPane();
        mapPane.getChildren().add(map);
        
        circle = new Circle(10, Paint.valueOf("blue"));
        AnchorPane.setTopAnchor(circle, plan.getAktualniProstor().getTopAnchor());
        AnchorPane.setLeftAnchor(circle, plan.getAktualniProstor().getLeftAnchor());
        circle.setVisible(true);
        
        mapPane.getChildren().add(circle);
        hBox.getChildren().add(mapPane);      
    }
    /**
     * Vraci panel ke zpracovani
     * @return vraci ke zpracovani
     */
    public HBox getSection(){
        circle.setVisible(true);
        return hBox;
    }

    @Override
    public void refresh() {
        mapPane.getChildren().remove(circle);        
        AnchorPane.setTopAnchor(circle, plan.getAktualniProstor().getTopAnchor());
        AnchorPane.setLeftAnchor(circle, plan.getAktualniProstor().getLeftAnchor());        
        mapPane.getChildren().add(circle);
        
    }
        
    
}
