package Estructuras;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TablaHash {
    private int size_table;
    private Nodo values[];
    
    public TablaHash(){
        this.size_table = 37;
        this.values = new Nodo[this.size_table];
    }

    private int hash_function(long dpi){
        return (int)(dpi % this.size_table);
    }

    private int collition_resolution(int key, long dpi){
        int new_key = this.hash_function(key+1);

        while (this.values[new_key] != null && this.values[new_key].dpi != dpi) {
            if (find_null() > 0) {
                new_key = this.hash_function(new_key+1);
            }else{
                new_key = -1;
                break;
            }
        }
        return new_key;
    }

    private int find_null(){
        int count = 0;
        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i] == null) {
                count++;
            }
        }
        return count;
    }

    private void rehashing(){
        Nodo valores[] = new Nodo[this.size_table];
        int contador = 0;
        for (Nodo nodo : this.values) {
            if (nodo != null) {
                valores[contador] = nodo;
            }
            contador++;
        }
        this.size_table = this.size_table * 2;
        this.values = new Nodo[this.size_table];
        for (Nodo nodo : valores) {
            this.insert(nodo.dpi, nodo.name, nodo.lastname,nodo.telefono, nodo.licencia, nodo.genero, nodo.direccion);
        }
    }

    public void insert(long dpi, String name, String lastname,String telefono, String licencia, String genero, String direccion) {
        int key = this.hash_function(dpi);
        if (this.values[key] == null) {
            this.values[key] = new Nodo(dpi, name, lastname,telefono, licencia, genero, direccion);
        }else if (this.values[key].dpi == dpi) {
            System.out.println("igual");
        }else{
            int collition_resolution = this.collition_resolution(key, dpi);
            if (collition_resolution != -1) {
                this.values[collition_resolution] = new Nodo(dpi, name, lastname,telefono, licencia, genero, direccion);
            }else{
                this.rehashing();
                this.insert(dpi, name, lastname, licencia,telefono, genero, direccion);
            }
        }
    }

    public void print() {
        for (Nodo nodo : this.values) {
            if (nodo != null) {
                System.out.print(nodo.dpi+" ");
                System.out.print(nodo.name+" ");
                System.out.print(nodo.licencia+" ");
                System.err.println();
            }else{
                System.out.println(nodo);
            }
        }
    }

    public void graficar(){
        String result = "digraph G{\nsubgraph cluster1{\nnode[shape=none];\na0[label=<\n<TABLE CELLSPACING=\"0\" CELLPADDING=\"1\" BORDER=\"1\">\n";
        result += "<TR>\n";
        result += "<TD border=\"3\">No.</TD>\n";
        result += "<TD border=\"3\">DPI</TD>\n";
        result += "<TD border=\"3\">Nombres</TD>\n";
        result += "<TD border=\"3\">Apellidos</TD>\n";
        result += "<TD border=\"3\">Telefono</TD>\n";
        result += "<TD border=\"3\">Genero</TD>\n";
        result += "<TD border=\"3\">Direccion</TD>\n";
        result += "<TD border=\"3\">Licencia</TD>\n";
        result += "</TR>\n";
        for (int i = 0; i < this.values.length; i++) {
            result += "<TR>\n";
            if (this.values[i] != null) {
                result += "<TD border=\"3\">"+i+"</TD>\n";
                result += "<TD border=\"3\">"+this.values[i].dpi+"</TD>\n";
                result += "<TD border=\"3\">"+this.values[i].name+"</TD>\n";
                result += "<TD border=\"3\">"+this.values[i].lastname+"</TD>\n";
                result += "<TD border=\"3\">"+this.values[i].telefono+"</TD>\n";
                result += "<TD border=\"3\">"+this.values[i].genero+"</TD>\n";
                result += "<TD border=\"3\">"+this.values[i].direccion+"</TD>\n";
                result += "<TD border=\"3\">"+this.values[i].licencia+"</TD>\n";
            }else{
                result += "<TD border=\"3\">"+i+"</TD>\n";
                result += "<TD border=\"3\"></TD>\n";
                result += "<TD border=\"3\"></TD>\n";
                result += "<TD border=\"3\"></TD>\n";
                result += "<TD border=\"3\"></TD>\n";
                result += "<TD border=\"3\"></TD>\n";
                result += "<TD border=\"3\"></TD>\n";
                result += "<TD border=\"3\"></TD>\n";
            }
            result += "</TR>\n";
        }
        result += "</TABLE>>];\n}\n}";
        archivo(result);
    }

    private void archivo(String resultado) {
        String ubicacion_dot = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_3\\Graphviz\\Archivos_TXT\\TablaHash.txt";
        String ubicacion_img = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_3\\Graphviz\\Imagenes\\TablaHash.png";
        File dotFile = new File(ubicacion_dot);
        try {dotFile.createNewFile();} catch (Exception e) {}
        FileWriter writer;
        try {
            writer = new FileWriter(ubicacion_dot);
            writer.write(resultado);
            writer.close();
        } catch (Exception e) {}
        String comando = "circo -Tpng " + ubicacion_dot + " -o " + ubicacion_img;
        ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c"+comando);
        builder.redirectErrorStream(true);
        try {builder.start();} catch (IOException e) {}
    }
}

class Nodo{
    public long dpi;
    public String name;
    public String lastname;
    public String telefono;
    public String licencia;
    public String genero;
    public String direccion;
    public Nodo(long dpi, String name, String lastname, String telefono, String licencia, String genero, String direccion) {
        this.dpi = dpi;
        this.name = name;
        this.lastname = lastname;
        this.telefono = telefono;
        this.licencia = licencia;
        this.genero = genero;
        this.direccion = direccion;
    }
}
