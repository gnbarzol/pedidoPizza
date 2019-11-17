/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author GaryBarzola
 */
public class GeneracionData {
    private final List<Ingredientes> imagenes;
    private final List<Tamanios> tamanios;
    static final long serialVersionUID = 1L;
    
    static DateFormat f = DateFormat.getDateInstance(DateFormat.SHORT);
    
    public GeneracionData(){
        imagenes= new ArrayList();
        llenarIngredientes();
        guardarArchivoIngredientes();
        tamanios= new ArrayList();
        llenarTamanios();
        guardarArchivoTamanio();
    }
    
    /*
    public static void main(String[] args){
            //GeneracionData p= new GeneracionData();     
    }*/
    
    public final void guardarArchivoIngredientes(){
        try(ObjectOutputStream obj= new ObjectOutputStream(new FileOutputStream("src/resources/DataIngredientes"))){
            obj.writeObject(imagenes);
        }catch(IOException ex){
            System.out.println("No se puedo crear el ");
        }
    }
    
    public final void llenarIngredientes(){
        imagenes.add(new Ingredientes("src/resources/cebolla.png","Cebolla",0.90));
        imagenes.add(new Ingredientes("src/resources/champinon.png","Champinon",1.10));
        imagenes.add(new Ingredientes("src/resources/pina.png","Pina",1.15));
        imagenes.add(new Ingredientes("src/resources/pepperoni.png","Pepperoni",1));
        imagenes.add(new Ingredientes("src/resources/jamon.png","Jamon",1.2));
        imagenes.add(new Ingredientes("src/resources/atun.png","Atun",1.3));
        imagenes.add(new Ingredientes("src/resources/aceitunas.png","Aceitunas",1.05));
        imagenes.add(new Ingredientes("src/resources/pimiento.png","Pimiento",0.8));
        imagenes.add(new Ingredientes("src/resources/tomate.png","Tomate",0.75));
        
    }
    
    public final void guardarArchivoTamanio(){
        try(ObjectOutputStream obj= new ObjectOutputStream(new FileOutputStream("src/resources/DataTamanios"))){
            obj.writeObject(tamanios);
        }catch(IOException ex){
            System.out.println("No se puedo crear el archivo de imagenes");
        }
    }
    
    public final void llenarTamanios(){
        tamanios.add(new Tamanios("Mediana",220,220,10));
        tamanios.add(new Tamanios("Familiar",250,250,12));
        tamanios.add(new Tamanios("Suprema",280,280,14));
        
    }
    
}

