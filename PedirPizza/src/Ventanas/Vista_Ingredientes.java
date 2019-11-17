/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Data.Ingredientes;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author GaryBarzola
 */
public class Vista_Ingredientes {
    private final VBox root;
    private static StackPane pizzaMolde;
    private FlowPane ingredientes;
    public static int numIngredientes=0;
    
    public Vista_Ingredientes(){
        numIngredientes=0;
        root= new VBox();
        pizzaMolde= new StackPane();
        pizzaMolde.getChildren().add(imgPizza("fondo_pizza"));
        root.getChildren().add(pizzaMolde);
        aniadirIngredientes();
    }
    
    public final void aniadirIngredientes(){
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 16 );
        ingredientes=new FlowPane();
        ingredientes.setHgap(40);
        ingredientes.setVgap(40);
        List<Ingredientes> ingre=(List<Ingredientes>)Vista_Tamanio.leerData("DataIngredientes");
        ingre.forEach((i) -> {
            HBox contenido= new HBox();
            contenido.setSpacing(5);
            ImageView ingrediente=imgIngredientes(i.getRuta(),70,70);
            contenido.setAlignment(Pos.CENTER);
            Label l= new Label(i.getName()); l.setFont(theFont);
            contenido.getChildren().addAll(ingrediente,l);
            ingredientes.getChildren().add(contenido);
            contenido.setOnMouseClicked((e)->{
                
                if(numIngredientes<12){
                    Label detalle= new Label(i.getName()+":    "+NumberFormat.getCurrencyInstance().format(i.getPrecio()));
                    Vista_Detalle.aniadirDetalleIngrediente(i.getPrecio(),detalle,pizzaMolde,ingredientesPizza(i));
                    numIngredientes++;
                }else{
                    Vista_Facturacion.mensajeAlerta("** Máximo de ingredientes alcanzado **\n\nPara eliminar un ingrediente haga click"
                            + " en él,\nen Detalle Pedido");
                }
            });
        });
        root.getChildren().add(ingredientes);
    }
    
    public final ImageView imgIngredientes(String ruta,int height, int width){
        ImageView img=null;
        try {
            img=new ImageView(new Image(new FileInputStream(ruta)));
            img.setFitHeight(height);
            img.setFitWidth(width);
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro la ruta de la imagen de uno de los ingredientes.");
        }
        return img;
    }
        
    public HBox ingredientesPizza(Ingredientes ingrediente){
        Pane thumd= new Pane();
        for(int i=0;i<7;i++){
            ImageView imgI=imgPizza(ingrediente.getName()+"_thumb");
            imgI.setFitHeight(35); imgI.setFitWidth(35);
            imgI.setLayoutX((double)(Math.random()*166)); imgI.setLayoutY((double)(Math.random()*166));
            thumd.getChildren().add(imgI);
        }
        HBox c= new HBox();
        c.setPrefSize(167,167);
        c.setAlignment(Pos.CENTER);
        c.setPadding(new Insets(32,12,0,5));
        c.getChildren().add(thumd);
        pizzaMolde.getChildren().add(c);
        return c;
    }
    

    public final ImageView imgPizza(String name){
        ImageView img=null;
        try {
            img=new ImageView(new Image(new FileInputStream("src/resources/"+name+".png")));
            img.setFitHeight(290);
            img.setFitWidth(290);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(vistaPizzaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
    
    public VBox getRoot(){
        return root;
    }

    public void setPizzaMolde() {
        pizzaMolde.getChildren().clear();
        pizzaMolde.getChildren().add(imgPizza("fondo_pizza"));
    }
    
    
}
