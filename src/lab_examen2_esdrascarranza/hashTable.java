/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_examen2_esdrascarranza;

/**
 *
 * @author Usuario
 */
public class hashTable {
        clase_entry lista;
    
    public void agregar(String username, long posicion) {
        clase_entry elemento = new clase_entry(username, posicion);
        if (lista == null) {
            lista = elemento;
        } else {
            clase_entry actual = lista;
            while (actual.getsiguiente() != null) {
                actual = actual.getsiguiente();
            }
            actual.setSiguiente(elemento);
        }
    }
    
    public void remover(String username) {
        if (lista == null) return;
        if (lista.getusuario().equals(username)) {
            lista = lista.getsiguiente();
            return;
        }
        
        clase_entry actual = lista;
        while (actual.getsiguiente() != null) {
            if (actual.getsiguiente().getusuario().equals(username)) {
                actual.setSiguiente(actual.getsiguiente().getsiguiente());
                return;
            }
            actual = actual.getsiguiente();
        }
    }
    
    public long buscar(String username) {
        clase_entry actual = lista;
        
        while (actual != null) {
            if (actual.getusuario().equals(username)) {
                return actual.getposicion();
            }
            actual = actual.getsiguiente();
        }
        return -1;
    }
}
