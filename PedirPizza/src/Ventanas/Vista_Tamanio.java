/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import Data.Tamanios;
import java.text.NumberFormat;

/**
 *
 * @author GaryBarzola
 */
public class Vista_Tamanio {
    private final VBox root;
    private final StackPane pizza;
    private ImageView imgPizza;
    public static Label tipo_Tamanio;
    
    public Vista_Tamanio(){
        root= new VBox();
        pizza= new StackPane();
        tipo_Tamanio = new Label();
        pizza.getChildren().add(imgPizza(260,260));
        root.getChildren().add(pizza);
        actualizarTamanioPizza();
    }

    public final ImageView imgPizza(int height, int width){
        pizza.setPrefSize(320,320);
        try {
            imgPizza=new ImageView(new Image(new FileInputStream("src/resources/fondo_pizza.png")));
            imgPizza.setFitHeight(height);
            imgPizza.setFitWidth(width);
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro la ruta de la pizza Molde");
        }
        pizza.setPadding(new Insets(45,0,0,0));
        return imgPizza;
    }
        
    
    public final void actualizarTamanioPizza(){
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 22 );
        root.setSpacing(90);
        HBox namePizza= new HBox();
        namePizza.setAlignment(Pos.CENTER);
        namePizza.setSpacing(30);
        List<Tamanios> tamanios=(List<Tamanios>)leerData("DataTamanios");
        for(Tamanios t:tamanios){
            Label l= new Label(t.getName()); l.setFont(theFont);
            l.setOnMouseClicked((e)->{
                imgPizza.setFitHeight(t.getHeight());
                imgPizza.setFitWidth(t.getWidth());
                //precio=t.getPrecio();
                tipo_Tamanio.setText(l.getText()+": "+NumberFormat.getCurrencyInstance().format(t.getPrecio()));
                Vista_Detalle.aniadirDetalleTamanio(t.getPrecio(),tipo_Tamanio);               
            });
            namePizza.getChildren().add(l);
        }
        root.getChildren().addAll(namePizza);
    }
    
    
    public static Object leerData(String name){
        Object objeto=null;
        try(ObjectInputStream obj= new ObjectInputStream(new FileInputStream("src/resources/"+name))){
            objeto=obj.readObject();
        } catch (ClassNotFoundException ex) {
            System.out.println("No se encontro la clase del objeto a  leer");
        }catch(IOException ex){
            System.out.println("No se pudo leer el archivo de tamanios");
        }
        return objeto;
    }

    public VBox getRoot() {
        return root;
    }

    public StackPane getPizza() {
        return pizza;
    }

    public ImageView getImgPizza() {
        return imgPizza;
    }
    
    
    
    
    
    
}
