/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

/**
 *
 * @author GaryBarzola
 */
public class Persona {
    private final String cedula;
    private final String name;
    private final String num;
    private final String street;
    private final String fecha;
    private final String total;

    public Persona(String cedula, String name, String num, String street,String total,String fecha) {
        this.cedula = cedula;
        this.name = name;
        this.num = num;
        this.street = street;
        this.fecha=fecha;
        this.total=total;
    }

    @Override
    public String toString() {
        return cedula + ","+name + "," + num + "," + street + "," + total + "," + fecha;
    }

    
    
    public String getCedula() {
        return cedula;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }

    public String getStreet() {
        return street;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTotal() {
        return total;
    }
    
    
    
    
}
