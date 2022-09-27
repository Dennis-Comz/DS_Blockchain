package Interfaz;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Estructuras.ArbolB;
import Proyecto_Fase3.Actividades;

public class Administracion implements ActionListener{
    JFrame frame = new JFrame("UDrawing Paper Administracion");
    JPanel panel = new JPanel();
    JPanel mensajeros, rutas, lugares;
    JButton cargaMasiva, insertar_cliente, modificar_cliente, eliminar_cliente, buscar_cliente;
    JButton listar_Clientes, cerrar_sesion, cargar_mensajeros, cargar_rutas, cargar_lugares;
    JButton ver_mensajeros, ver_rutas, ver_adyacencia;
    JTabbedPane tabbedPane;
    DefaultTableModel modelClientes;
    JTable tableClientes;
    JScrollPane scrollClientes; //Scroll de tabla
    JFrame lg_frame;
    JFileChooser fileChooser;
    JLabel img_label, img_label_mensajeros, img_label_rutas, img_label_lugares;

    public Administracion(JFrame lg){
        lg_frame = lg;
        lg_frame.dispose();;
        display();
    }

    public void display() {
        frame.setSize(1200,800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane = new JTabbedPane();

        //Panel de cargas masivas de clientes
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        cargaMasiva = new JButton("Cargar Clientes");
        cargaMasiva.setForeground(Color.WHITE);
        cargaMasiva.setFont(new Font("Arial", Font.PLAIN, 15));
        cargaMasiva.setBounds(800, 100, 150,50);
        cargaMasiva.setBackground(Color.decode("#0EA9F5"));
        cargaMasiva.addActionListener(this);
        cargaMasiva.setActionCommand("cargar");
        panel.add(cargaMasiva);

        insertar_cliente = new JButton("Crear Cliente");
        insertar_cliente.setForeground(Color.WHITE);
        insertar_cliente.setFont(new Font("Arial", Font.PLAIN, 15));
        insertar_cliente.setBounds(975, 100, 150,50);
        insertar_cliente.setBackground(Color.decode("#0EA9F5"));
        insertar_cliente.addActionListener(this);
        insertar_cliente.setActionCommand("insertar");
        panel.add(insertar_cliente);

        modificar_cliente = new JButton("Modificar Cliente");
        modificar_cliente.setForeground(Color.WHITE);
        modificar_cliente.setFont(new Font("Arial", Font.PLAIN, 15));
        modificar_cliente.setBounds(800, 175, 150,50);
        modificar_cliente.setBackground(Color.decode("#0EA9F5"));
        modificar_cliente.addActionListener(this);
        modificar_cliente.setActionCommand("modificar");
        panel.add(modificar_cliente);

        eliminar_cliente = new JButton("Eliminar Cliente");
        eliminar_cliente.setForeground(Color.WHITE);
        eliminar_cliente.setFont(new Font("Arial", Font.PLAIN, 15));
        eliminar_cliente.setBounds(975, 175, 150,50);
        eliminar_cliente.setBackground(Color.decode("#0EA9F5"));
        eliminar_cliente.addActionListener(this);
        eliminar_cliente.setActionCommand("eliminar");
        panel.add(eliminar_cliente);

        buscar_cliente = new JButton("Buscar Cliente");
        buscar_cliente.setForeground(Color.WHITE);
        buscar_cliente.setFont(new Font("Arial", Font.PLAIN, 15));
        buscar_cliente.setBounds(800, 250, 150,50);
        buscar_cliente.setBackground(Color.decode("#0EA9F5"));
        buscar_cliente.addActionListener(this);
        buscar_cliente.setActionCommand("buscar");
        panel.add(buscar_cliente);

        listar_Clientes = new JButton("Listar Clientes");
        listar_Clientes.setForeground(Color.WHITE);
        listar_Clientes.setFont(new Font("Arial", Font.PLAIN, 15));
        listar_Clientes.setBounds(975, 250, 150,50);
        listar_Clientes.setBackground(Color.decode("#0EA9F5"));
        listar_Clientes.addActionListener(this);
        listar_Clientes.setActionCommand("listar");
        panel.add(listar_Clientes);

        tabla();

        JLabel descripcion = new JLabel("Arbol de Clientes:");
        descripcion.setBounds(50, 385, 150, 25);
        descripcion.setForeground(Color.WHITE);
        panel.add(descripcion);

        JPanel panel_imagen = new JPanel();
        panel_imagen.setBackground(Color.decode("#2E2E2E"));
        img_label = new JLabel();
        img_label.setOpaque(true);
        panel_imagen.add(img_label);
        JScrollPane scrollPane = new JScrollPane(panel_imagen);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 415, 1100, 300);
        scrollPane.setBackground(Color.decode("#2E2E2E"));
        panel.add(scrollPane);

        cerrar_sesion = new JButton("Cerrar Sesion");
        cerrar_sesion.setBounds(1000, 10, 150, 25);
        cerrar_sesion.setForeground(Color.WHITE);
        cerrar_sesion.setFont(new Font("Arial", Font.PLAIN, 15));
        cerrar_sesion.setBackground(Color.decode("#0EA9F5"));
        cerrar_sesion.addActionListener(this);
        cerrar_sesion.setActionCommand("salir");
        panel.add(cerrar_sesion);
        
        tabbedPane.add("Gestiones", panel);

        //Panel de mensajeros
        mensajeros = new JPanel();
        mensajeros.setLayout(null);
        mensajeros.setBackground(Color.BLACK);

        JPanel panel_imagen_mensajeros = new JPanel();
        panel_imagen_mensajeros.setBackground(Color.decode("#2E2E2E"));
        img_label_mensajeros = new JLabel();
        img_label_mensajeros.setOpaque(true);
        panel_imagen_mensajeros.add(img_label_mensajeros);
        JScrollPane scrollPaneMensajeros = new JScrollPane(panel_imagen_mensajeros);
        scrollPaneMensajeros.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneMensajeros.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneMensajeros.setBounds(25, 25, 725, 700);
        scrollPaneMensajeros.setBackground(Color.decode("#2E2E2E"));
        mensajeros.add(scrollPaneMensajeros);

        cargar_mensajeros = new JButton("Cargar Mensajeros");
        cargar_mensajeros.setForeground(Color.WHITE);
        cargar_mensajeros.setFont(new Font("Arial", Font.PLAIN, 15));
        cargar_mensajeros.setBounds(775, 125, 175,50);
        cargar_mensajeros.setBackground(Color.decode("#0EA9F5"));
        cargar_mensajeros.addActionListener(this);
        cargar_mensajeros.setActionCommand("mensajeros");
        mensajeros.add(cargar_mensajeros);

        ver_mensajeros = new JButton("Ver Mensajeros");
        ver_mensajeros.setForeground(Color.WHITE);
        ver_mensajeros.setFont(new Font("Arial", Font.PLAIN, 15));
        ver_mensajeros.setBounds(975, 125, 175,50);
        ver_mensajeros.setBackground(Color.decode("#0EA9F5"));
        ver_mensajeros.addActionListener(this);
        ver_mensajeros.setActionCommand("ver_mensajeros");
        mensajeros.add(ver_mensajeros);

        tabbedPane.add("Mensajeros", mensajeros);

        //Panel de rutas
        rutas = new JPanel();
        rutas.setLayout(null);
        rutas.setBackground(Color.BLACK);

        JPanel panel_imagen_rutas = new JPanel();
        panel_imagen_rutas.setBackground(Color.decode("#2E2E2E"));
        img_label_rutas = new JLabel();
        img_label_rutas.setOpaque(true);
        panel_imagen_rutas.add(img_label_rutas);
        JScrollPane scrollPaneRutas = new JScrollPane(panel_imagen_rutas);
        scrollPaneRutas.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneRutas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneRutas.setBounds(25, 25, 725, 700);
        scrollPaneRutas.setBackground(Color.decode("#2E2E2E"));
        rutas.add(scrollPaneRutas);

        cargar_rutas = new JButton("Cargar Rutas");
        cargar_rutas.setForeground(Color.WHITE);
        cargar_rutas.setFont(new Font("Arial", Font.PLAIN, 15));
        cargar_rutas.setBounds(775, 125, 175,50);
        cargar_rutas.setBackground(Color.decode("#0EA9F5"));
        cargar_rutas.addActionListener(this);
        cargar_rutas.setActionCommand("rutas");
        rutas.add(cargar_rutas);

        ver_rutas = new JButton("Ver Rutas & Lugares");
        ver_rutas.setForeground(Color.WHITE);
        ver_rutas.setFont(new Font("Arial", Font.PLAIN, 15));
        ver_rutas.setBounds(975, 125, 175,50);
        ver_rutas.setBackground(Color.decode("#0EA9F5"));
        ver_rutas.addActionListener(this);
        ver_rutas.setActionCommand("ver_rutas");
        rutas.add(ver_rutas);

        cargar_lugares = new JButton("Cargar Lugares");
        cargar_lugares.setForeground(Color.WHITE);
        cargar_lugares.setFont(new Font("Arial", Font.PLAIN, 15));
        cargar_lugares.setBounds(775, 50, 175,50);
        cargar_lugares.setBackground(Color.decode("#0EA9F5"));
        cargar_lugares.addActionListener(this);
        cargar_lugares.setActionCommand("lugares");
        rutas.add(cargar_lugares);

        ver_adyacencia = new JButton("Ver Lista Adyacencia");
        ver_adyacencia.setForeground(Color.WHITE);
        ver_adyacencia.setFont(new Font("Arial", Font.PLAIN, 15));
        ver_adyacencia.setBounds(975, 50, 175,50);
        ver_adyacencia.setBackground(Color.decode("#0EA9F5"));
        ver_adyacencia.addActionListener(this);
        ver_adyacencia.setActionCommand("ver_adyacencia");
        rutas.add(ver_adyacencia);

        tabbedPane.add("Rutas & Lugares", rutas);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    public void tabla() {
        String[] columnasClientes = {"DPI", "Nombre", "Usuario", "Correo", "Contraseña", "Telefono", "Direccion", "Municipio"};
        modelClientes = new DefaultTableModel(null, columnasClientes){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableClientes = new JTable(modelClientes);
        tableClientes.setOpaque(true);
        tableClientes.setFillsViewportHeight(true);
        tableClientes.setBackground(Color.decode("#2E2E2E"));
        tableClientes.setForeground(Color.WHITE);
        scrollClientes = new JScrollPane(tableClientes);
        scrollClientes.setBackground(Color.decode("#2E2E2E"));
        tableClientes.getTableHeader().setBackground(Color.decode("#2E2E2E"));
        tableClientes.getTableHeader().setForeground(Color.WHITE);
        scrollClientes.setBounds(50,25,700,350);
        panel.add(scrollClientes);
    }
    
    public boolean leer_clientes(String path) {
        boolean exito = false;
        Path filename = Path.of(path);
        String contenido = "";
        try {
            contenido = Files.readString(filename);
            // Se lee el archivo 
            JsonParser parser = new JsonParser();
            JsonArray gsonArr = parser.parse(contenido).getAsJsonArray();
            // Ciclo para los objetos json
            for (JsonElement obj : gsonArr) {
                JsonObject gsonObj = obj.getAsJsonObject();
                long dpi = gsonObj.get("dpi").getAsLong();
                String name = gsonObj.get("nombre_completo").getAsString();
                String password = gsonObj.get("contrasenia").getAsString();                
                String username = gsonObj.get("nombre_usuario").getAsString();
                String email = gsonObj.get("correo").getAsString();
                String telefono = gsonObj.get("telefono").getAsString();
                String direccion = gsonObj.get("direccion").getAsString();
                int id_municipio = gsonObj.get("id_municipio").getAsInt();
                Actividades.arbol_b.insertar(dpi, name, username, email, password, telefono, direccion, id_municipio);            
            }
            exito = true;
            Actividades.arbol_b.traversal();
        } catch (Exception e) {}
        return exito;
    }

    public boolean leer_mensajeros(String path) {
        boolean exito = false;
        Path filename = Path.of(path);
        String contenido = "";
        try {
            contenido = Files.readString(filename);
            // Se lee el archivo 
            JsonParser parser = new JsonParser();
            JsonArray gsonArr = parser.parse(contenido).getAsJsonArray();
            // Ciclo para los objetos json
            for (JsonElement obj : gsonArr) {
                JsonObject gsonObj = obj.getAsJsonObject();
                long dpi = gsonObj.get("dpi").getAsLong();
                String name = gsonObj.get("nombres").getAsString();
                String lastname = gsonObj.get("apellidos").getAsString();
                String telefono = gsonObj.get("telefono").getAsString();
                String licencia = gsonObj.get("tipo_licencia").getAsString();
                String genero = gsonObj.get("genero").getAsString();
                String direccion = gsonObj.get("direccion").getAsString();
                //ACA DEBO AÑADIR LOS CAMPOS A LA TABLA HASH
                Actividades.tabla.insert(dpi, name, lastname, telefono,licencia, genero, direccion);
            }
            exito = true;
        } catch (Exception e) {}
        Actividades.tabla.graficar();
        return exito;
    }

    public boolean leer_rutas(String path) {
        boolean exito = false;
        Path filename = Path.of(path);
        String contenido = "";
        try {
            contenido = Files.readString(filename);
            // Se lee el archivo 
            JsonParser parser = new JsonParser();
            JsonObject gsonOb = parser.parse(contenido).getAsJsonObject();
            JsonArray gsonArr = gsonOb.get("Grafo").getAsJsonArray();
            // Ciclo para los objetos json
            for (JsonElement obj : gsonArr) {
                JsonObject gsonObj = obj.getAsJsonObject();               
                int inicio = gsonObj.get("inicio").getAsInt();
                int final_ = gsonObj.get("final").getAsInt();
                int peso = gsonObj.get("peso").getAsInt();
                Actividades.listaRutas.insertar(inicio, final_, peso);
            }
            exito = true;
        } catch (Exception e) {}
        Actividades.listaRutas.graficar();
        Actividades.adyacencia.llenado_automatico();
        Actividades.adyacencia.graficar();
        return exito;
    }

    public boolean leer_lugares(String path) {
        boolean exito = false;
        Path filename = Path.of(path);
        String contenido = "";
        try {
            contenido = Files.readString(filename);
            // Se lee el archivo 
            JsonParser parser = new JsonParser();
            JsonObject gsonOb = parser.parse(contenido).getAsJsonObject();
            JsonArray gsonArr = gsonOb.get("Lugares").getAsJsonArray();
            // Ciclo para los objetos json
            for (JsonElement obj : gsonArr) {
                JsonObject gsonObj = obj.getAsJsonObject();               
                int id = gsonObj.get("id").getAsInt();
                String departamento = gsonObj.get("departamento").getAsString();
                String nombre = gsonObj.get("nombre").getAsString();
                String sn = gsonObj.get("sn_sucursal").getAsString();
                Actividades.listaLugares.insertar(id, departamento, nombre, sn);
            }
            exito = true;
        } catch (Exception e) {System.out.println(e);}
        Actividades.listaLugares.print();
        return exito;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("cargar")){
            fileChooser = new JFileChooser();
            int op = fileChooser.showOpenDialog(frame);
            if (op == JFileChooser.APPROVE_OPTION) {
                if (leer_clientes(fileChooser.getSelectedFile().getAbsolutePath())) {
                    desplegar_info("Los clientes han sido cargados al sistema.");
                }else{
                    desplegar_error("El archivo seleccionado no es valido.");
                }
            }else{
                desplegar_error("No se ha seleccionado un archivo.");
            }
        }else if (e.getActionCommand().equals("insertar")) {
            new NewUser();
            llenar_tabla();
        }else if(e.getActionCommand().equals("modificar")){
            new Admin_Modificar();
            llenar_tabla();
        }else if(e.getActionCommand().equals("eliminar")){
            new Admin_Eliminar();
            llenar_tabla();
        }else if(e.getActionCommand().equals("buscar")){
            new Admin_Buscar();
        }else if(e.getActionCommand().equals("listar")){
            llenar_tabla();
            // Codigo para mostrar el arbol
            File img = new File("C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_3\\Graphviz\\Imagenes\\Arboles\\B\\ArbolB.png");
            if (img.exists()) {
                try {
                    BufferedImage picture = ImageIO.read(img);
                    img_label.setIcon(new ImageIcon(picture));
                } catch (Exception ex) {}
            }else{
                desplegar_error("No existen clientes en el sistema.");
            }
        }else if(e.getActionCommand().equals("mensajeros")){
            fileChooser = new JFileChooser();
            int op = fileChooser.showOpenDialog(frame);
            if (op == JFileChooser.APPROVE_OPTION) {
                if (leer_mensajeros(fileChooser.getSelectedFile().getAbsolutePath())) {
                    desplegar_info("Los mensajeros han sido cargados al sistema.");
                }else{
                    desplegar_error("El archivo seleccionado no es valido.");
                }
            }else{
                desplegar_error("No se ha seleccionado un archivo.");
            }
        }else if(e.getActionCommand().equals("rutas")){
            fileChooser = new JFileChooser();
            int op = fileChooser.showOpenDialog(frame);
            if (op == JFileChooser.APPROVE_OPTION) {
                if (leer_rutas(fileChooser.getSelectedFile().getAbsolutePath())) {
                    desplegar_info("Las rutas han sido cargados al sistema.");
                }else{
                    desplegar_error("El archivo seleccionado no es valido.");
                }
            }else{
                desplegar_error("No se ha seleccionado un archivo.");
            }
        }else if(e.getActionCommand().equals("lugares")){
            fileChooser = new JFileChooser();
            int op = fileChooser.showOpenDialog(frame);
            if (op == JFileChooser.APPROVE_OPTION) {
                if (leer_lugares(fileChooser.getSelectedFile().getAbsolutePath())) {
                    desplegar_info("Los lugares han sido cargados al sistema.");
                }else{
                    desplegar_error("El archivo seleccionado no es valido.");
                }
            }else{
                desplegar_error("No se ha seleccionado un archivo.");
            }
        }else if(e.getActionCommand().equals("ver_mensajeros")){
            File img = new File("C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_3\\Graphviz\\Imagenes\\TablaHash.png");
            if (img.exists()) {
                try {
                    BufferedImage picture = ImageIO.read(img);
                    img_label_mensajeros.setIcon(new ImageIcon(picture));
                } catch (Exception ex) {}
            }else{
                desplegar_error("No existen mensajeros en el sistema.");
            }
        }else if(e.getActionCommand().equals("ver_rutas")){
            File img = new File("C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_3\\Graphviz\\Imagenes\\GrafoRutas.png");
            if (img.exists()) {
                try {
                    BufferedImage picture = ImageIO.read(img);
                    img_label_rutas.setIcon(new ImageIcon(picture));
                } catch (Exception ex) {}
            }else{
                desplegar_error("No existen rutas en el sistema.");
            }
        }else if(e.getActionCommand().equals("ver_adyacencia")){
            File img = new File("C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_3\\Graphviz\\Imagenes\\Adyacencia.png");
            if (img.exists()) {
                try {
                    BufferedImage picture = ImageIO.read(img);
                    img_label_rutas.setIcon(new ImageIcon(picture));
                } catch (Exception ex) {}
            }else{
                desplegar_error("No existen rutas y lugares en el sistema.");
            }
        }else if(e.getActionCommand().equals("salir")){
            frame.setVisible(false);
            lg_frame.setVisible(true);
        }
    }

    // EN ESTE SEGMENTO SE OBTIENE EL ARREGLO QUE PROPORCIONA EL ARBOL
    // PARA LLENAR LA TABLA
    public void llenar_tabla() {
        ArrayList<ArbolB.Nodo> clientes = new ArrayList<ArbolB.Nodo>();
        clientes = Actividades.arbol_b.get_clientes_array();
        modelClientes.setRowCount(0);
        for (int i = 0; i < clientes.size(); i++) {
            String[] datos = new String[8];
            datos[0] = String.valueOf(clientes.get(i).dpi);
            datos[1] = String.valueOf(clientes.get(i).name);
            datos[2] = String.valueOf(clientes.get(i).username);
            datos[3] = String.valueOf(clientes.get(i).email);
            datos[4] = String.valueOf(clientes.get(i).password);
            datos[5] = String.valueOf(clientes.get(i).telefono);
            datos[6] = String.valueOf(clientes.get(i).direccion);
            datos[7] = String.valueOf(clientes.get(i).id_municipio);
            modelClientes.addRow(datos);
        }
    }

    private void desplegar_error(String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void desplegar_info(String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, "Exito", JOptionPane.INFORMATION_MESSAGE);
    }

}