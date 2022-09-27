package Interfaz;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Proyecto_Fase3.Actividades;

public class NewUser implements ActionListener{
    JFrame frame = new JFrame("Crear Nuevo Usuario");
    JPanel panel = new JPanel();
    JTextField dpi_field, name_field, username_field, email_field, pass_field;
    JTextField telefono_field, direccion_field, municipio_field;
    JFrame log;
    JButton registrar;

    public NewUser(){
        display();
    }

    public NewUser(JFrame fr_login){
        log = fr_login;
        log.setVisible(false);
        display();
    }

    public void display() {
        frame.setTitle("UDrawing Paper");
        frame.setSize(500,800);
        frame.setLocationRelativeTo(null);
        if (log != null) {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            
        }else{
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        panel.setBackground(Color.BLACK);
        frame.add(panel);

        panel.setLayout(null);
        JLabel bienvenida = new JLabel("Bienvenido a UDrawing Paper");
        bienvenida.setForeground(Color.WHITE);
        bienvenida.setBounds(113,50,275,25);
        bienvenida.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(bienvenida);

        JLabel aviso = new JLabel("Ingrese los datos que se le solicitan.");
        aviso.setForeground(Color.WHITE);
        aviso.setBounds(150,80,200,25);
        aviso.setFont(new Font("Arial", Font.PLAIN, 13));
        panel.add(aviso);

        JLabel dpi = new JLabel("Ingrese su DPI:");
        dpi.setForeground(Color.WHITE);
        dpi.setBounds(50,115,110,25);
        dpi.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(dpi);

        dpi_field = new JTextField();
        dpi_field.setForeground(Color.WHITE);
        dpi_field.setBackground(Color.decode("#2E2E2E"));
        dpi_field.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        dpi_field.setBounds(50, 150, 400, 25);
        dpi_field.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(dpi_field);

        JLabel nombre = new JLabel("Ingrese su nombre:");
        nombre.setForeground(Color.WHITE);
        nombre.setBounds(50,185,200,25);
        nombre.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(nombre);

        name_field = new JTextField();
        name_field.setForeground(Color.WHITE);
        name_field.setBackground(Color.decode("#2E2E2E"));
        name_field.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        name_field.setBounds(50, 220, 400, 25);
        name_field.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(name_field);

        JLabel contrasena = new JLabel("Ingrese su contraseña:");
        contrasena.setForeground(Color.WHITE);
        contrasena.setBounds(50,255,200,25);
        contrasena.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(contrasena);

        pass_field = new JTextField();
        pass_field.setForeground(Color.WHITE);
        pass_field.setBackground(Color.decode("#2E2E2E"));
        pass_field.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        pass_field.setBounds(50, 290, 400, 25);
        pass_field.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(pass_field);

        // NUEVOS FIELDS
        JLabel usernameJLabel = new JLabel("Ingrese su nombre de usuario:");
        usernameJLabel.setForeground(Color.WHITE);
        usernameJLabel.setBounds(50,325,250,25);
        usernameJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(usernameJLabel);

        username_field = new JTextField();
        username_field.setForeground(Color.WHITE);
        username_field.setBackground(Color.decode("#2E2E2E"));
        username_field.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        username_field.setBounds(50, 355, 400, 25);
        username_field.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(username_field);
        
        JLabel emailJLabel = new JLabel("Ingrese su correo:");
        emailJLabel.setForeground(Color.WHITE);
        emailJLabel.setBounds(50,390,200,25);
        emailJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(emailJLabel);
        
        email_field = new JTextField();
        email_field.setForeground(Color.WHITE);
        email_field.setBackground(Color.decode("#2E2E2E"));
        email_field.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        email_field.setBounds(50, 420, 400, 25);
        email_field.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(email_field);
        
        JLabel telefonoJLabel = new JLabel("Ingrese su telefono:");
        telefonoJLabel.setForeground(Color.WHITE);
        telefonoJLabel.setBounds(50,455,200,25);
        telefonoJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(telefonoJLabel);
        
        telefono_field = new JTextField();
        telefono_field.setForeground(Color.WHITE);
        telefono_field.setBackground(Color.decode("#2E2E2E"));
        telefono_field.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        telefono_field.setBounds(50, 485, 400, 25);
        telefono_field.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(telefono_field);
        
        JLabel direccionJLabel = new JLabel("Ingrese su direccion:");
        direccionJLabel.setForeground(Color.WHITE);
        direccionJLabel.setBounds(50,520,200,25);
        direccionJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(direccionJLabel);
        
        direccion_field = new JTextField();
        direccion_field.setForeground(Color.WHITE);
        direccion_field.setBackground(Color.decode("#2E2E2E"));
        direccion_field.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        direccion_field.setBounds(50, 555, 400, 25);
        direccion_field.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(direccion_field);
        
        JLabel municipioJLabel = new JLabel("Ingrese el numero de su municipio:");
        municipioJLabel.setForeground(Color.WHITE);
        municipioJLabel.setBounds(50,590,250,25);
        municipioJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(municipioJLabel);
        
        municipio_field = new JTextField();
        municipio_field.setForeground(Color.WHITE);
        municipio_field.setBackground(Color.decode("#2E2E2E"));
        municipio_field.setBorder(new LineBorder(Color.decode("#0EA9F5"),2));
        municipio_field.setBounds(50, 620, 400, 25);
        municipio_field.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(municipio_field);

        registrar = new JButton("Registrar");
        registrar.setForeground(Color.WHITE);
        registrar.setFont(new Font("Arial", Font.PLAIN, 15));
        registrar.setBackground(Color.decode("#1C8622"));
        registrar.setBounds(50,680,400,25);
        registrar.addActionListener(this);
        registrar.setActionCommand("registrar");
        panel.add(registrar);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("registrar")) {
            long dpi = Long.parseLong(dpi_field.getText());
            String name = name_field.getText();
            String password = pass_field.getText();
            String username = username_field.getText();
            String email = username_field.getText();
            String telefono = username_field.getText();
            String direccion = username_field.getText();
            int id_municipio = Integer.parseInt(municipio_field.getText());
            boolean existe = Actividades.arbol_b.insertar(dpi, name, username, email, password, telefono, direccion, id_municipio);
            if (!existe) {
                desplegar_info("Registrado con Exito");
                frame.dispose();
                if (log != null) {
                    log.setVisible(true);                    
                }
            }else{
                desplegar_error("El usuario ya existe");
            }
        }        
    }

    private void desplegar_error(String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void desplegar_info(String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, "Exito", JOptionPane.INFORMATION_MESSAGE);
    }

}
