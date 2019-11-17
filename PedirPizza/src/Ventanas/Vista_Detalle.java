/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.text.NumberFormat;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author GaryBarzola
 */
public class Vista_Detalle {
    private final VBox rightPane;
    public static VBox contenido;
    private static double total=0;
    private static double totalTamanio=0;
    private static double totalIngrediente=0;
    private static Label total_pedido;
    
    public Vista_Detalle(){
        total=0;
        totalTamanio=0;
        totalIngrediente=0;
        rightPane= new VBox();
        contenido= new VBox();
        right();
    }
    
    public final void right(){
        contenido.setPrefSize(200, 600);
        contenido.setSpacing(15);
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 19 );
        rightPane.setPrefWidth(200);
        rightPane.setStyle("-fx-padding: 5;" + 
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 1;" + 
                      "-fx-border-color: black;");
        Label detalle= new Label("     Detalle Pedido"); detalle.setFont(theFont);
        detalle.setPadding(new Insets(0,0,30,0));
        rightPane.getChildren().add(detalle);
        rightPane.getChildren().add(contenido);
        
        HBox t= new HBox(); t.setSpacing(15);
        Label tot= new Label("TOTAL"); tot.setFont(theFont);
        total_pedido= new Label(String.valueOf(NumberFormat.getCurrencyInstance().format(total))); 
        total_pedido.setFont(theFont);

        t.getChildren().addAll(tot,total_pedido);
        t.setAlignment(Pos.BOTTOM_CENTER);
        rightPane.getChildren().add(t);
    }
    
    
    public static void aniadirDetalleTamanio(double precio,Label l){
        double precioAnteriorTamanio=totalTamanio;
        
        totalTamanio=precio;
        total-=precioAnteriorTamanio;
        total+=totalTamanio;
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 16 );
        l.setFont(theFont); l.setTextFill(Color.BLACK);
        try{
            contenido.getChildren().add(l);
        }catch(IllegalArgumentException ex){}
        total_pedido.setText(String.valueOf(NumberFormat.getCurrencyInstance().format(total))); 
        
    }
    
    public static void aniadirDetalleIngrediente(double precio,Label l,StackPane st, HBox hb){
        totalIngrediente+=precio;
        total+=precio;
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 16 );
        l.setFont(theFont); l.setTextFill(Color.BLACK);
        try{
            contenido.getChildren().add(l);
        }catch(IllegalArgumentException ex){System.out.println(ex.getMessage());}
        total_pedido.setText(String.valueOf(NumberFormat.getCurrencyInstance().format(total))); 
        l.setOnMouseClicked((e)->{
            Vista_Ingredientes.numIngredientes--;
            contenido.getChildren().remove(l);
            totalIngrediente-=precio;
            total-=precio;
            total_pedido.setText(String.valueOf(NumberFormat.getCurrencyInstance().format(total)));
            st.getChildren().remove(hb);
        });
    }
 
    
    public VBox getRoot() {
        return rightPane;
    }

    public static double getTotal() {
        return total;
    }

    public static double getTotalTamanio() {
        return totalTamanio;
    }

    public void setContenido() {
        Vista_Detalle.contenido.getChildren().clear();
    }

    public void setTotal(double total) {
        Vista_Detalle.total = total;
    }

    public static double getTotalIngrediente() {
        return totalIngrediente;
    }
    

    public void setTotalTamanio(double totalTamanio) {
        Vista_Detalle.totalTamanio = totalTamanio;
    }
    

    public void setTotal_pedido() {
        Vista_Detalle.total_pedido.setText(String.valueOf(NumberFormat.getCurrencyInstance().format(total)));
    }
    
    
    
    
}


