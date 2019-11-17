/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Animacion.TestPizza;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author GaryBarzola
 */
public class Pane_Inicial {
    private final StackPane root;
    private Pane_Pedido pP;
    private Pane_Ventas pV;
    private final Font theFont = Font.font("Helvetica", FontWeight.BOLD, 19 );
    
    
    public Pane_Inicial(){
        root=new StackPane();
        pantalla();
        aniadir_botones();
    }
    
    public final void pantalla(){
        try {
            ImageView img= new ImageView(new Image(new FileInputStream("src/resources/ppp.jpg")));
            img.setFitHeight(700);
            img.setFitWidth(1000);
            root.getChildren().add(img);
        } catch (FileNotFoundException ex) {
            System.out.println("El ruta del fondo de la ventana de bienvenida no se ha encontrado");
        }   
    }
    
    public final Button btn_tomarPedido(){
        Button pedido= new Button("NUEVO PEDIDO");
        pedido.setPrefSize(200, 70);
        pedido.setFont(theFont);
        fondoBotones(pedido);
        pedido.setOnMouseClicked((e)->{
            pP= new Pane_Pedido();
            TestPizza.scene.setRoot(pP.getRoot());
        });
        return pedido;
    }
    
    public final Button btn_Ventas(){
        Button ventas= new Button("VENTAS");
        ventas.setPrefSize(200, 70);
        ventas.setFont(theFont);
        fondoBotones(ventas);
        ventas.setOnMouseClicked((e)->{
            pV=new Pane_Ventas();
            TestPizza.scene.setRoot(pV.getRoot());
        });
        return ventas;
    }
    
    public final Button btn_Salir(){
        Button salir= new Button("SALIR");
        salir.setPrefSize(200, 70);
        salir.setFont(theFont);
        fondoBotones(salir);
        salir.setOnMouseClicked((e)->Platform.exit());
        return salir;
    }
    
    public final void aniadir_botones(){
        VBox btns= new VBox();
        btns.setSpacing(75);
        btns.setAlignment(Pos.CENTER);
        btns.getChildren().addAll(btn_tomarPedido(),btn_Ventas(),btn_Salir());
        root.getChildren().add(btns);
    }
    
    public static void fondoBotones(Button btn){
        btn.setAlignment(Pos.CENTER);
        btn.setPadding(new Insets(12,0,0,0));
        btn.setTextFill(Color.GOLD);
        try {
            Image img = new Image(new FileInputStream("src/resources/apple.png"));
            BackgroundImage image = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(233,80,false,false,false,false));
            Background background = new Background(image);
            btn.setBackground(background);
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado la ruta del fondo de los botones.");
        }
        
    }
    
    public StackPane getRoot(){
        return root;
    }
    
    
}
