/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab_examen2_esdrascarranza;

import java.io.IOException;
import javax.swing.SwingUtilities;

/**
 *
 * @author Usuario
 */
public class LAB_Examen2_EsdrasCarranza {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
         SwingUtilities.invokeLater(() -> {
            try {
                new Play_GUI(); 
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    
}
