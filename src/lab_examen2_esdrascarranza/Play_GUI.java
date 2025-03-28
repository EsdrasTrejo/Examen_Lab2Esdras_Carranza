/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_examen2_esdrascarranza;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Usuario
 */
public class Play_GUI {
    private psnusers usuarios;
    private JFrame frame;
    private JTextField usuarioTxt, juegoTxt, usernameTxt;
    private JComboBox<Trofeos> trofeosBox;
    private JTextArea usuarioInfo;

    public Play_GUI() throws IOException {
        usuarios = new psnusers();
    frame = new JFrame("CENTRAL DE PLAY ESTATION :D");
        frame.setSize(800, 700); 
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(140, 180, 140)); 
        frame.add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fuente = new Font("Consolas", Font.BOLD, 18);
        Font fuenteBtn = new Font("Consolas", Font.PLAIN, 16);
        Color colorBtn = new Color(120, 120, 120);

        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usuarioLabel, gbc);

        usuarioTxt = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usuarioTxt, gbc);

        JLabel trofeosLabel = new JLabel("Ingrese el nombre Videojuego:");
        trofeosLabel.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(trofeosLabel, gbc);

        juegoTxt = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(juegoTxt, gbc);

        JLabel nombreTrofeo = new JLabel("Ingrese el nombre trofeo:");
        nombreTrofeo.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(nombreTrofeo, gbc);

        usernameTxt = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(usernameTxt, gbc);

        JLabel tipoTrofeo = new JLabel("Ingrese el tipo de trofeo:");
        tipoTrofeo.setFont(fuente);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(tipoTrofeo, gbc);

        trofeosBox = new JComboBox<>(Trofeos.values());
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(trofeosBox, gbc);

        gbc.gridy = 5;
        gbc.gridwidth = 2;
        JButton addBtn = new JButton("Agregar un usuario");
        configurarBoton(addBtn, colorBtn, fuenteBtn, e -> agregarUsuario());
        panel.add(addBtn, gbc);

        gbc.gridy = 6;
        JButton deactivateBtn = new JButton("Desactivar el usuario");
        configurarBoton(deactivateBtn, colorBtn, fuenteBtn, e -> desactivar_usuario());
        panel.add(deactivateBtn, gbc);

        gbc.gridy = 7;
        JButton searchBtn = new JButton("Buscar un usuario");
        configurarBoton(searchBtn, colorBtn, fuenteBtn, e -> buscar_usuario());
        panel.add(searchBtn, gbc);

        gbc.gridy = 8;
        JButton addTrofeoBtn = new JButton("Agregar el trofeo");
        configurarBoton(addTrofeoBtn, colorBtn, fuenteBtn, e -> agregar_trofeo());
        panel.add(addTrofeoBtn, gbc);

        usuarioInfo = new JTextArea();
        usuarioInfo.setEditable(false);
        usuarioInfo.setFont(new Font("Consolas", Font.PLAIN, 14));
        usuarioInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JScrollPane scrollPane = new JScrollPane(usuarioInfo);
        scrollPane.setBorder(BorderFactory.createTitledBorder("datos del usuario"));
        gbc.gridy = 9;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        frame.setVisible(true);
    }

    private void configurarBoton(JButton boton, Color color, Font fuente, ActionListener action) {
        boton.setFont(fuente);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(color, 1));
        boton.addActionListener(action);
    }

    private JLabel crearEtiqueta(String texto, Font fuente, Color color) {
        JLabel label = new JLabel(texto);
        label.setFont(fuente);
        label.setForeground(color);
        return label;
    }

    private JTextField crearCampoTexto() {
        JTextField campo = new JTextField(20);
        campo.setFont(new Font("Arial", Font.PLAIN, 16));
        return campo;
    }

    private JButton crearBoton(String texto, Color color, Font fuente, ActionListener action) {
        JButton boton = new JButton(texto);
        boton.setFont(fuente);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        boton.addActionListener(action);
        return boton;
    }

    private void agregarUsuario() {
        String username = usuarioTxt.getText().trim();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            if (usuarios.buscar_usuario(username)) {
                JOptionPane.showMessageDialog(null, "El usuario ingresado ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                usuarios.agregar_user(username);
                JOptionPane.showMessageDialog(null, "Usuario creado con exito!", "Creación", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
            }
        } catch (IOException ex) {
            mostrarError(ex, "Error al agregar usuario");
        }
    }

    private void desactivar_usuario() {
        String username = usuarioTxt.getText();
        try {
            if (!usuarios.buscar_usuario(username)) {
                JOptionPane.showMessageDialog(null, "no se encontro el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                usuarios.desactivar_usuario(username);
                JOptionPane.showMessageDialog(null, "Se desactivo el usuario ", "Desactivación", 1);
                limpiar();
            }
        } catch (IOException ex) {
            mostrarError(ex, "Error al desactivar usuario");
        }
    }

    private void buscar_usuario() {
        String username = usuarioTxt.getText();
        try {
            if (!usuarios.buscar_usuario(username)) {
                JOptionPane.showMessageDialog(null, "No se encontro el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                usuarioInfo.setText(usuarios.usuario_info(username));
            }
        } catch (IOException ex) {
            mostrarError(ex, "Error al buscar usuario");
        }
    }

    private void agregar_trofeo() {
        String username = usuarioTxt.getText();
        String trophyGame = juegoTxt.getText();
        String trophyName = usernameTxt.getText();
        Trofeos type = (Trofeos) trofeosBox.getSelectedItem();
        if (username.trim().isEmpty() || trophyGame.trim().isEmpty() || trophyName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            if (!usuarios.buscar_usuario(username)) {
                JOptionPane.showMessageDialog(null, "El usuario no fue encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                usuarios.agregar_trofeo(username, trophyGame, trophyName, type);
                JOptionPane.showMessageDialog(null, "Trofeo agregado!", "Creación", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                buscar_usuario();
            }
        } catch (IOException ex) {
            mostrarError(ex, "Error al agregar trofeo");
        }
    }

    private void limpiar() {
        usuarioTxt.setText("");
        juegoTxt.setText("");
        usernameTxt.setText("");
        trofeosBox.setSelectedIndex(0);
    }
      private void mostrarError(Exception ex, String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje + "\nDetalles: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}
