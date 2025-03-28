/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_examen2_esdrascarranza;

/**
 *
 * @author Usuario
 */
public enum Trofeos {
    Platino(5), Oro(3), Plata(2), Bronce(1);

    public int points;
    
    Trofeos(int points) {
        this.points = points;
    }
    
    public int getPoints() {
        return points;
    }
}