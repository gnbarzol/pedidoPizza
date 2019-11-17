/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author GaryBarzola
 */
public class vistaPizzaInicial {
    private final VBox root;
    private StackPane resultado;
    private Label text;
    
    public vistaPizzaInicial(){
        root= new VBox();
        root.setSpacing(80);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(imgPizza(),vistaTexto());
    }
    
    public final StackPane imgPizza(){
        resultado= new StackPane();
        try {
            ImageView pizza=new ImageView(new Image(new FileInputStream("src/resources/fondo_pizza.png")));
            pizza.setFitHeight(250);
            pizza.setFitWidth(250);
            resultado.getChildren().add(pizza);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(vistaPizzaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultado.setPadding(new Insets(70,0,0,0));
        return resultado;
    }
    
    
    
    public final Label vistaTexto(){
        Font theFont = Font.font("Baloo", FontWeight.BOLD, 22 );
        text= new Label("BIENVENIDOS A LA PIZZA DE DON CANGREJO\n  ACONTINUACION PODRA ARMAR SU PIZZA.");
        text.setFont(theFont);
        return text;
    }
    
    public VBox getRoot(){
        return root;
    }
    
}
