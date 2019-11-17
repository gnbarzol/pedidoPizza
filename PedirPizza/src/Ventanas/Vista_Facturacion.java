/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Animacion.TestPizza;
import Cliente.Persona;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.StageStyle;

/**
 *
 * @author GaryBarzola
 */
public class Vista_Facturacion {
    private final VBox root;
    private TextArea cedula;
    private TextArea nombre;
    private TextArea numTlf;
    private TextArea direccion;
    private final Calendar c = Calendar.getInstance();
    
    public Vista_Facturacion(){
        root= new VBox();
        agregarContenido();
        btn_finalizar();
    }
    
    
    public final VBox informacion(){
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 19 );
        Label ced= new Label("Cedula: "); ced.setFont(theFont);
        Label n= new Label("Nombre: "); n.setFont(theFont);
        Label num= new Label("Num Telefono: "); num.setFont(theFont);
        Label d= new Label("Direccion: "); d.setFont(theFont);
        VBox labels=new VBox();
        labels.setSpacing(50);
        labels.setPadding(new Insets(5,0,0,15));
        labels.getChildren().addAll(ced,n,num,d);
        return labels;
    }
    
    public final VBox entradaTexto(){
        cedula = new TextArea(); bordesTexto(cedula);
        nombre = new TextArea(); bordesTexto(nombre);
        numTlf = new TextArea(); bordesTexto(numTlf);
        direccion = new TextArea(); bordesTexto(direccion);
        direccion.setPrefSize(260, 80); 
        VBox contenido= new VBox();
        contenido.setSpacing(25);
        contenido.getChildren().addAll(cedula,nombre,numTlf,direccion);
        return contenido;
    }
    
    public static void bordesTexto(TextArea t){
        t.setPrefSize(260, 40); t.setStyle("-fx-font-size:16px;"+"-fx-border: none"+"-fx-border-style: solid inside;" + 
                      "-fx-border-width: 1;" + 
                      "-fx-border-color: black;"+"-fx-border-radius: 11;");
    }
    
    public final void agregarContenido(){
        HBox h= new HBox();
        h.setSpacing(30);
        h.getChildren().addAll(informacion(),entradaTexto());
        root.getChildren().add(h);
    }
    
    public final void btn_finalizar(){
        Button finalizar=new Button("Finalizar Compra"); finalizar.setTextFill(Color.WHITE);
        finalizar.setPrefSize(175, 38);
        finalizar.setStyle("-fx-font-size:17px;"+"-fx-border-style: solid inside;" +"-fx-border-width: 1;" + 
                      "-fx-border-color: black;"+"-fx-border-radius: 10;"+"-fx-background-color: #1376C5;");
        finalizar.setAlignment(Pos.CENTER);
        root.setSpacing(230);
        root.setAlignment(Pos.CENTER);
        finalizar.setOnMouseClicked((e)->{
            if(autenticacionCedula(cedula.getText().trim(),nombre.getText().trim(),numTlf.getText().trim(),
                    direccion.getText().trim() /*,Vista_Detalle.getTotal()*/)){
                if(Vista_Detalle.getTotalTamanio()!=0){
                    if(Vista_Detalle.getTotalIngrediente()!=0){
                        String dia = Integer.toString(c.get(Calendar.DATE));
                        String mes = Integer.toString(c.get(Calendar.MONTH)+1);
                        String anio = Integer.toString(c.get(Calendar.YEAR));
                        GuardarCliente(new Persona(cedula.getText().trim(),nombre.getText().trim(),numTlf.getText().trim(),
                                direccion.getText().trim(),String.format("%.2f",Vista_Detalle.getTotal()),dia+"/"+mes+"/"+anio));
                        TestPizza.scene.setRoot((new Pane_Inicial()).getRoot());
                    }else{
                        mensajeAlerta("** Escoja al menos 1 ingrediente **");
                    }
                }else{
                    mensajeAlerta("** Escoja el tamaño de su Pizza **");
                }
            }else{
                mensajeAlerta("Verifique los datos ingresados");
             }
        });
        root.getChildren().add(finalizar);  
    }
    
    
    public void GuardarCliente(Persona p){
        try(BufferedWriter bw=new BufferedWriter(new FileWriter("src/resources/Clientes.txt",true))){
            bw.write(p.toString());
            bw.newLine();
            
        }catch(IOException ex){
            System.out.println("No se pudo crear el archivo de clientes");
        }
    }
    
    
    public boolean autenticacionCedula(String cedula, String name, String telefono,String direccion /*,double total , double tT*/ ){
        if(cedula.length()==10 && !name.isEmpty() && !telefono.isEmpty() && !direccion.isEmpty() /*&& total!=0*/ ){
            //if(tT!=0){
                if(!verificarCedulaEnArchivo(cedula)){
                    return true;
                }
            //}
        }
        return false;
    }
    
    
    public boolean verificarCedulaEnArchivo(String cedula){
        try(BufferedReader br= new BufferedReader(new FileReader("src/resources/Clientes.txt"))){
            String linea;
            while((linea=br.readLine())!=null){
                String[] datos=linea.split(",");
                if(cedula.equals(datos[0])){
                    return true;
                }
            }
        }catch(IOException ex){
            System.out.println("No se pudo leer el archivo de clientes");
        }
        return false;
    }
    
    public static void mensajeAlerta(String name){
        Alert alerta= new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Ventana de Alerta");
        alerta.setHeaderText(null);
        alerta.setContentText(name);
        alerta.initStyle(StageStyle.UTILITY);
        alerta.showAndWait();
    }
    
    public VBox getRoot(){
        return root;
    }
}
