/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_examen2_esdrascarranza;

/**
 *
 * @author Usuario
 */
public class clase_entry {
    
    String username;
    long posicion;
    clase_entry siguiente;
    
    public clase_entry(String username, long posicion) {
        this.username = username;
        this.posicion = posicion;
        this.siguiente = null;
    }
    
    public String getusuario() {
        return username;
    }
    
    public long getposicion() {
        return posicion;
    }
    
    public clase_entry getsiguiente() {
        return siguiente;
    }
    
    public void setSiguiente(clase_entry siguiente) {
        this.siguiente = siguiente;
    }
}