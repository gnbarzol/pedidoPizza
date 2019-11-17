/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Animacion.TestPizza;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author GaryBarzola
 */
public class Pane_Pedido {
    private final BorderPane root;
    private VBox leftPane;
    private Label size;
    private Label ingredients;
    private Label billing;
    private final Vista_Tamanio vT;
    private final Vista_Ingredientes vI;
    private final Vista_Facturacion vF;
    private final Vista_Detalle vD;
    
    public Pane_Pedido(){
        root=new BorderPane();
        root.setTop(top("ARMA TU PIZZA"));
        root.setLeft(left());
        root.setCenter(new vistaPizzaInicial().getRoot());
        vT=new Vista_Tamanio();
        vI=new Vista_Ingredientes();
        vF=new Vista_Facturacion();
        vD=new Vista_Detalle();
        root.setRight(vD.getRoot());
        opcionesPizzas();
        leftPane.getChildren().add(btn_Atras());
        
    }
    
    public static final HBox top(String name){
        HBox topPane= new HBox();
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 19 );
        Label titulo=new Label(name);
        titulo.setTextFill(Color.web("#FFFFFF"));
        titulo.setFont(theFont);
        topPane.setPrefHeight(45);
        topPane.getChildren().add(titulo);
        topPane.setAlignment(Pos.CENTER);
        topPane.setStyle("-fx-background-color: #1376C5;");
        return topPane;
    }
    
    public final VBox left(){
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 16 );
        leftPane= new VBox();
        leftPane.setPadding(new Insets(35,0,0,0));
        leftPane.setPrefWidth(200);
        size= new Label("    1.Tamaño"); size.setFont(theFont);size.setTextFill(Color.web("#FFFFFF"));
        ingredients= new Label("    2.Ingredientes"); ingredients.setFont(theFont);ingredients.setTextFill(Color.web("#FFFFFF"));
        billing= new Label("    3.Facturación"); billing.setFont(theFont);billing.setTextFill(Color.web("#FFFFFF"));
        size.setPrefSize(200,45);
        ingredients.setPrefSize(200,45);
        billing.setPrefSize(200,45);
        VBox contenido= new VBox();
        contenido.getChildren().addAll(size,ingredients,billing);
        leftPane.getChildren().add(contenido);
        leftPane.setStyle("-fx-background-color: #333333");
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setSpacing(440);
        return leftPane;
    }
    
    public final void opcionesPizzas(){
        size.setOnMouseClicked((e)->{
            size.setStyle("-fx-background-color: #999999");
            ingredients.setStyle("-fx-background-color: #333333");
            billing.setStyle("-fx-background-color: #333333");
            root.setCenter(vT.getRoot());
            /*
            vD.setContenido(); vD.setTotal(0); vD.setTotalTamanio(0); vD.setTotal_pedido();
            vI.setPizzaMolde();*/
        });
        ingredients.setOnMouseClicked((e)->{
            ingredients.setStyle("-fx-background-color: #999999");
            size.setStyle("-fx-background-color: #333333");
            billing.setStyle("-fx-background-color: #333333");
            root.setCenter(vI.getRoot());
        });
        billing.setOnMouseClicked((e)->{
            billing.setStyle("-fx-background-color: #999999");
            ingredients.setStyle("-fx-background-color: #333333");
            size.setStyle("-fx-background-color: #333333");
            root.setCenter(vF.getRoot());
        });
    }
    
    public static final Button btn_Atras(){
        Button atras= new Button("Volver");atras.setTextFill(Color.WHITE);
        atras.setStyle("-fx-background-color: #333333;");
        atras.setPrefSize(200, 70);
        atras.setStyle("-fx-font-size:18px;"+"-fx-border-style: solid inside;" +"-fx-border-width: 1;" + 
                      "-fx-border-color: white;"+"-fx-border-radius: 11;"+"-fx-background-color: #333333;");
        atras.setOnMouseClicked((e)->{       
            TestPizza.scene.setRoot((new Pane_Inicial()).getRoot());
        });
        atras.setAlignment(Pos.CENTER);
        return atras;
    }
    
    public BorderPane getRoot(){
        return root;
    }
}
