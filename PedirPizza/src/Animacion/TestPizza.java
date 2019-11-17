/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animacion;

import Ventanas.Pane_Inicial;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author GaryBarzola
 */
public class TestPizza extends Application {
    private Pane_Inicial pI;
    public static Scene scene;
    
    @Override
    public void start(Stage primaryStage) {
        pI= new Pane_Inicial();
        scene= new Scene(pI.getRoot());

        primaryStage.setTitle("La pizza de Don Cangrejo!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
