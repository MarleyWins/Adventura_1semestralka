/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package main;

import data.DataStream;
import gui.LeftPane;
import gui.RightPane;
import gui.TopPane;
import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 * *****************************************************************************
 * Třída Start je hlavní třídou projektu, který představuje jednoduchou textovou
 * adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author Jarmila Pavlíčková
 * @version ZS 2016/2017
 */
public class Start extends Application {

    private static String runVersion;
    private static final String PARAMS = "-text";
    private MenuBar menuBar;
    private IHra hra;
    private BorderPane border;
    private static TextArea textArea;
    private FlowPane bottomPane;
    private static TextField textField;
    private static LeftPane leftPane;
    private static RightPane rightPane;
    

    /**
     * *************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku pro volbu typu spuštění (gui,text)
     */
    public static void main(String[] args) {

        if (args.length == 0) {
            runVersion = "GUI";
            launch(args);
        } else {
            runVersion = "TEXT";
            if (args[0].equals(PARAMS)) {
                IHra hra = new Hra();
                TextoveRozhrani ui = new TextoveRozhrani(hra);
                ui.hraj();
            } else {
                System.out.println("Wrong param/s used.");
            }
        }
    }
    
    /**
     * Generuje, spouští centrální okno aplikace
     * @param primaryStage primary stage
     * @throws Exception 
     */

    @Override
    public void start(Stage primaryStage) throws Exception {

        hra = new Hra();
        border = new BorderPane();
        border.setMinHeight(400.00);
        border.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        border.setStyle("-fx-background-color: white;");

        createTextArea();
        border.setCenter(textArea);

        createBottomPane();
        border.setBottom(bottomPane);
        
        leftPane = new LeftPane(hra.getHerniPlan());
        border.setLeft(leftPane.getLeftPane());
        
        rightPane = new RightPane(hra.getHerniPlan());
        border.setRight(rightPane.getSection());
        
        TopPane topPane = new TopPane(hra.getHerniPlan());
        border.setTop(topPane.getSection());
        
        createMenu(primaryStage);
        VBox root = new VBox();   
        root.setVgrow(border, Priority.ALWAYS);
        root.setStyle("-fx-background-color: grey;");
        
        //Scene scene = new Scene(root, 1024.00, 768.00);
        Scene scene = new Scene(root);
        
        root.getChildren().add(menuBar);
        root.getChildren().add(border);
        
        textField.requestFocus();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Crossroads");
        primaryStage.show();
    }
/**
 * Vytvoří texovou areu a vypíše do ní uvítání
 */
    private void createTextArea() {
        textArea = new TextArea();                
        textArea.setText(hra.vratUvitani());
        textArea.setEditable(false);
        textArea.setWrapText(true);
    }

    /**
     * Method fills MenuBar with items.
     *
     * @param stage primary application stage
     */
    private void createMenu(Stage stage) {
        menuBar = new MenuBar();

        Menu file = new Menu("File");
        Menu help = new Menu("Help");

        menuBar.getMenus().add(file);
        menuBar.getMenus().add(help);

        MenuItem newGame = new MenuItem("New Game");
        MenuItem forceEnd = new MenuItem("Quit");

        newGame.setAccelerator(KeyCombination.keyCombination("CTRL+N"));
        newGame.setOnAction((ActionEvent e) -> {
                stage.close();
                Stage stage1 = new Stage();
            try {
                start(stage1);
            } catch (Exception ex) {
                System.out.println("Something went wrong");
            }
        });
        
        forceEnd.setAccelerator(KeyCombination.keyCombination("CTRL+E"));
        forceEnd.setOnAction((ActionEvent e) -> {
            stage.close();
        });

        file.getItems().addAll(newGame, new SeparatorMenuItem(), forceEnd);

        MenuItem helpItem = new MenuItem("Help");
        helpItem.setAccelerator(KeyCombination.keyCombination("CTRL+I"));
        helpItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                WebView browser = new WebView();
                Scene helpScene = new Scene(browser);
                Stage helpStage = new Stage();
                helpStage.setTitle("Help page");
                helpStage.setScene(helpScene);
                URL url = DataStream.class.getResource("/data/napoveda.html");
                browser.getEngine().load(url.toExternalForm());
                
                helpStage.show();
            }
        });
        
        help.getItems().add(helpItem);
        
    }
/**
 * Vrací právě běžící variantu hry
 * @return varianta spuštění (text, gui)
 */
    public static String getVersion() {
        return runVersion;
    }
/**
 * Vytvoří dolní panel a vloží text field a jeho label
 */
    private void createBottomPane() {
        Label zadejPrikazLabel = new Label("Command input:");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        bottomPane = new FlowPane();
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.getChildren().add(zadejPrikazLabel);

        textField = new TextField();
        bottomPane.getChildren().add(textField);

        textField.setOnAction((ActionEvent event) -> {
            String radek = textField.getText();
            String text = hra.zpracujPrikaz(radek);
            textArea.appendText("\n\n" + radek + "\n");
            textArea.appendText("\n" + text + "\n");
            textField.setText("");
            if (hra.konecHry()) {
                textField.setEditable(false);
            }
        });
    }
    
/**
 * Přidává nový řádek na konec zobrazovaného textu
 * @param text text k vypsání
 */
    public static void addToText(String text) {
        textArea.appendText(text);
    }
    
    public static void lockAll(){
       leftPane.lock();
       textField.setEditable(false);
       rightPane.lock();
    }

}
