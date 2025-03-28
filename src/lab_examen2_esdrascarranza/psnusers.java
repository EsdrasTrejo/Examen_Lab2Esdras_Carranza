/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_examen2_esdrascarranza;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class psnusers {
    
    private RandomAccessFile raf;
    private hashTable usuarios;
    
    public psnusers() throws IOException {
        raf = new RandomAccessFile("psn.ply", "rw");
        this.usuarios = new hashTable();
        recargar_hashTable();
    }
    
    private void recargar_hashTable() throws IOException {
        usuarios = new hashTable();
        raf.seek(0);
        while (raf.getFilePointer() < raf.length()) {
            long posicion = raf.getFilePointer();
            String username = raf.readUTF();
            int points = raf.readInt();
            int trofeos = raf.readInt();
            boolean activo = raf.readBoolean();
            usuarios.agregar(username, posicion);
        }
    }
    
    public boolean buscar_usuario(String username) {
        return usuarios.buscar(username) != -1;
    }
    
   public boolean agregar_user(String username) throws IOException {
    if (username == null || username.trim().isEmpty()) { // Evita nombres vacíos o con solo espacios
        return false;
    }
    
    if (buscar_usuario(username)) {
        return false;
    }

    raf.seek(raf.length());
    long posicion = raf.getFilePointer();
    raf.writeUTF(username);
    raf.writeInt(0);
    raf.writeInt(0);
    raf.writeBoolean(true);
    usuarios.agregar(username, posicion);
    return true;
}
    public void desactivar_usuario(String username) throws IOException {
        long posicion = usuarios.buscar(username);
        if (posicion != -1) {
            raf.seek(posicion);
            raf.readUTF();
            raf.readInt();
            raf.readInt();
            raf.writeBoolean(false);
        }
    }
    
    public void agregar_trofeo(String username, String trophyGame, String trophyName, 
            Trofeos type) throws IOException {
        if (username == null || username.trim().isEmpty() || 
            trophyGame == null || trophyGame.trim().isEmpty() || 
            trophyName == null || trophyName.trim().isEmpty() || 
            type == null) {
           
            return;
        }

        long posicion = usuarios.buscar(username);
        if (posicion != -1) {
            raf.seek(posicion);
            raf.readUTF();
            int points = raf.readInt();
            int trofeos = raf.readInt();
            boolean activo = raf.readBoolean();
            
            if (!activo) {
                System.out.println("El usuario se encuentra desactivado");
                return;
            }
            
            points += type.points;
            trofeos++;
            raf.seek(posicion + username.length() + 2);
            raf.writeInt(points);
            raf.writeInt(trofeos);
            
            try (RandomAccessFile trofeosFile = new RandomAccessFile("Trofeos.ply", "rw")) {
                trofeosFile.seek(trofeosFile.length());
                trofeosFile.writeUTF(username);
                trofeosFile.writeUTF(type.name());
                trofeosFile.writeUTF(trophyGame);
                trofeosFile.writeUTF(trophyName);
                SimpleDateFormat fechaFormateada = new SimpleDateFormat("dd/MM/yyyy");
                trofeosFile.writeUTF(fechaFormateada.format(new Date()));
            }
        }
    }
    
    public String usuario_info(String username) throws IOException {
        long posicion = usuarios.buscar(username);
        StringBuilder infoTxt = new StringBuilder();
        
        if (posicion != -1) {
            raf.seek(posicion);
            String nombre = raf.readUTF();
            infoTxt.append("Usuario: ").append(nombre).append("\n");
            int points = raf.readInt();
            infoTxt.append("Puntos: ").append(points).append("\n");
            int trofeos = raf.readInt();
            boolean estaActivo = raf.readBoolean();
            infoTxt.append("Activo: ").append(estaActivo ? "Si" : "No").append("\n");
            infoTxt.append("Cantidad de trofeos: ").append(trofeos).append("\n");
            infoTxt.append("\nTrofeos:\n");
            
            try (RandomAccessFile trofeosFile = new RandomAccessFile("Trofeos.ply", "rw")) {
                trofeosFile.seek(0);
                while (trofeosFile.getFilePointer() < trofeosFile.length()) {
                    if (trofeosFile.readUTF().equals(username)) {
                        String tipo = trofeosFile.readUTF();
                        String juego = trofeosFile.readUTF();
                        String descripcion = trofeosFile.readUTF();
                        String fecha = trofeosFile.readUTF();
                        infoTxt.append(fecha).append(" - ").append(tipo).append(" - ").append(juego)
                            .append(" - ").append(descripcion).append("\n");
                    } else {
                        trofeosFile.readUTF();
                        trofeosFile.readUTF();
                        trofeosFile.readUTF();
                        trofeosFile.readUTF();
                    }
                }
            }
        } else {
            return "El usuario no fue encontrado";
        }
        return infoTxt.toString();
    }
}

