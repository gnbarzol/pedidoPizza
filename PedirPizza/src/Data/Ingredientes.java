/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;
import java.io.Serializable;
/**
 *
 * @author GaryBarzola
 */
public class Ingredientes implements Serializable{
    private final String ruta;
    private final String name;
    private final double precio;
    static final long serialVersionUID = 1L;

    public Ingredientes(String ruta, String name,double precio) {
        this.ruta = ruta;
        this.name = name;
        this.precio=precio;
    }

    public String getRuta() {
        return ruta;
    }

    public String getName() {
        return name;
    }
    
    public double getPrecio(){
        return precio;
    }
    
}



