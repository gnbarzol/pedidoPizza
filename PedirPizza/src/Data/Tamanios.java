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
public class Tamanios implements Serializable{
    private final String name;
    private final int height;
    private final int width;
    private final double precio;
    static final long serialVersionUID = 1L;
    
    public Tamanios(String name, int height, int width, double precio) {
        this.name = name;
        this.height = height;
        this.width = width;
        this.precio=precio;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public double getPrecio() {
        return precio;
    }
    
    
}
