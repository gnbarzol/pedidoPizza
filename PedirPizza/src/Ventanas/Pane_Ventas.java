/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author GaryBarzola
 */
public class Pane_Ventas {
    private final BorderPane root;
    private VBox center;
    private TextArea fechaI;
    private TextArea fechaF;
    private GridPane table;
    private final ScrollPane sP;
    
    public Pane_Ventas(){
        sP = new ScrollPane();
        root=new BorderPane();
        root.setTop(P_top());
        root.setLeft(volver());
        mostrarFechas();
        root.setCenter(center);
        root.setBottom(btn_Consultar());
        
    }
    
    public BorderPane getRoot(){
        return root;
    }
    
    public final HBox P_top(){
        HBox topPane= Pane_Pedido.top("DETALLE DE VENTAS");
        topPane.setStyle("-fx-background-color: #333333");
        return topPane;
    }
    
    public final void mostrarFechas(){
        center= new VBox(); center.setAlignment(Pos.CENTER); center.setSpacing(20); center.setPadding(new Insets(0,0,0,40));
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 19 );
        HBox inicio= new HBox(); inicio.setSpacing(15);
        HBox fin= new HBox(); fin.setSpacing(36);
        Label i=new Label("Fecha Inicio: "); i.setFont(theFont);
        Label f=new Label("Fecha Fin: "); f.setFont(theFont);
        Label formato= new Label("(dd/mm/yyyy)"); formato.setStyle("-fx-font-size: 16px;");
        fechaI= new TextArea();  Vista_Facturacion.bordesTexto(fechaI);
        fechaF= new TextArea(); Vista_Facturacion.bordesTexto(fechaF); 
        inicio.getChildren().addAll(i,fechaI,formato);
        fin.getChildren().addAll(f,fechaF);
        center.getChildren().addAll(inicio,fin); 
    }
    
    public final VBox volver(){
        VBox left=new VBox();
        left.setAlignment(Pos.TOP_CENTER);
        left.setPrefHeight(150);
        Button vol=Pane_Pedido.btn_Atras(); vol.setPrefSize(120, 30);
        vol.setStyle("-fx-border-color: #000000;"+"-fx-font-size:18px;"+"-fx-border-style: solid inside;" +"-fx-border-width: 1;" + 
                    "-fx-border-radius: 11;"+"-fx-background-color: #FFFFFF;");
        vol.setTextFill(Color.BLACK);
        left.getChildren().add(vol);
        return left;
    }
    
    public final HBox btn_Consultar(){
        HBox bottonPane= new HBox();
        bottonPane.setPrefHeight(45);
        bottonPane.setAlignment(Pos.CENTER_RIGHT);
        Button consultar=new Button("Consultar"); consultar.setTextFill(Color.WHITE);
        consultar.setPrefSize(160, 38);
        consultar.setStyle("-fx-font-size:17px;"+"-fx-border-style: solid inside;" +"-fx-border-width: 1;" + 
                      "-fx-border-color: #FFFFFF;"+"-fx-border-radius: 10;"+"-fx-background-color: #333333;");
        consultar.setAlignment(Pos.CENTER);
        consultar.setOnMouseClicked((e)->{
            if(!fechaI.getText().trim().isEmpty() && !fechaF.getText().trim().isEmpty() && verificarIngresoFechas()){
                mostrarClientes(leerArchivoClientes());  
            }else{
                Vista_Facturacion.mensajeAlerta("LLene todos los campos correctamente\n\nFormato de fecha: dd/mm/aaaa");
            }
        });
        bottonPane.setStyle("-fx-background-color: #333333");
        bottonPane.getChildren().add(consultar);
        return bottonPane;
    }
   
    
    public void mostrarClientes(List<String> list){
        sP.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sP.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sP.setPannable(true);
        double Total = 0;
        if(center.getChildren().contains(sP)){
            //sP.setContent(null);
            center.getChildren().remove(sP);
        }
        table=new GridPane();
        table.setPadding(new Insets(30,0,0,135));
        Label cedula=new Label("Cedula"); cedula.setStyle("-fx-border-color: #FFFFFF; -fx-font-size: 25; -fx-background-color: #333333;");
        cedula.setPrefWidth(150); cedula.setAlignment(Pos.CENTER); cedula.setTextFill(Color.WHITE);
        Label f=new Label("Fecha"); f.setStyle("-fx-border-color: #FFFFFF; -fx-font-size: 25; -fx-background-color: #333333;");
        f.setPrefWidth(150); f.setAlignment(Pos.CENTER); f.setTextFill(Color.WHITE);
        Label t=new Label("Total"); t.setStyle("-fx-border-color: #FFFFFF; -fx-font-size: 25; -fx-background-color: #333333;");
        t.setPrefWidth(150); t.setAlignment(Pos.CENTER); t.setTextFill(Color.WHITE);
        table.add(cedula, 0, 0);
        table.add(f, 1, 0);
        table.add(t, 2, 0);
        int fila=1;
        for(String d:list){
            String[] dato=d.split(",");
            Label id= new Label(dato[0]); id.setStyle("-fx-border-color: black; -fx-font-size: 18;");
            id.setPrefWidth(150); id.setAlignment(Pos.CENTER);
            Label fecha= new Label(dato[5]);fecha.setStyle("-fx-border-color: black; -fx-font-size: 18;");
            fecha.setPrefWidth(150); fecha.setAlignment(Pos.CENTER);
            Label total= new Label(dato[4]);total.setStyle("-fx-border-color: black; -fx-font-size: 18;");
            total.setPrefWidth(150); total.setAlignment(Pos.CENTER);
            table.add(id, 0, fila);
            table.add(fecha, 1, fila);
            table.add(total, 2, fila);
            fila++;
            Total+=Double.valueOf(dato[4]);
        }
        Label tot=new Label("Total"); tot.setStyle("-fx-border-color: black; -fx-font-size: 19;");
        tot.setPrefWidth(150); tot.setAlignment(Pos.CENTER);
        Label suma=new Label(String.valueOf(NumberFormat.getCurrencyInstance().format(Total)));
        suma.setStyle("-fx-border-color: black; -fx-font-size: 19;");
        suma.setPrefWidth(150); suma.setAlignment(Pos.CENTER);
        table.add(tot, 1, fila);
        table.add(suma, 2, fila);
        sP.setContent(table);
        center.getChildren().add(sP);
    }
        
    
    public boolean filtrar_Fecha(String fecha) throws ParseException{ 
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();
        Calendar cal3 = new GregorianCalendar();
        cal1.setTime(sdf.parse(fechaI.getText().trim()));
        cal2.setTime(sdf.parse(fechaF.getText().trim()));
        cal3.setTime(sdf.parse(fecha));
        return cal3.after(cal1) && cal3.before(cal2);
    }
    
    
    public List<String> leerArchivoClientes(){
        List<String> fechasInRango= new ArrayList();
        try(BufferedReader br= new BufferedReader(new FileReader("src/resources/Clientes.txt"))){
            String linea;
            while((linea=br.readLine())!=null){
                String[] datos=linea.split(",");
                if(filtrar_Fecha(datos[5])){
                    fechasInRango.add(linea);
                }
            } 
        }catch (FileNotFoundException ex) {
            System.out.println("Archivo clientes no encontrado");
        }catch (ParseException ex) {
            Vista_Facturacion.mensajeAlerta("Los campos no permiten el ingreso de letras");
        }catch(IOException ex){
            System.out.println("Error inesperado en la clase: "+getClass());
        }    
        return fechasInRango;
    }
    
    
    public boolean verificarIngresoFechas(){
        if(fechaI.getText().trim().length()==10 && fechaF.getText().trim().length()==10){
            String diaI= fechaI.getText().trim().substring(0,2);
            String mesI=fechaI.getText().trim().substring(3,5);
            String yyI=fechaI.getText().trim().substring(6,10);
            String diaF= fechaF.getText().trim().substring(0,2);
            String mesF=fechaF.getText().trim().substring(3,5);
            String yyF=fechaF.getText().trim().substring(6,10);
            if(diaI.matches("[0-9]+") && mesI.matches("[0-9]+") && yyI.matches("[0-9]+") &&
                diaF.matches("[0-9]+") && mesF.matches("[0-9]+") && yyF.matches("[0-9]+")){
                return true;
            }
        }
        return false;
    }
    
    
}
