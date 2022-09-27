package Estructuras;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ListaRutas {
    public class Nodo{
        int inicio, final_, peso;
        Nodo next;
    
        public Nodo(int inicio, int final_, int peso) {
            this.inicio = inicio;
            this.final_ = final_;
            this.peso = peso;
            this.next = null;
        }
        
    }

    Nodo head;
    Nodo cola;

    public void insertar(int inicio, int final_, int peso){
        Nodo newNodo = new Nodo(inicio, final_, peso);

        if (head == null) {
            head = newNodo;
            cola = newNodo;
        }else{
            cola.next = newNodo;
            cola = newNodo;
        }
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }
    public AuxiliarAdyacencia getInicios(){
        AuxiliarAdyacencia lista = new AuxiliarAdyacencia();
        Nodo temp = head;
        while (temp != null) {
            if (!lista.exist(temp.inicio)) {
                lista.insertar(temp.inicio);
            }
            temp = temp.next;
        }
        return lista;
    }

    public AuxiliarAdyacencia getFinales(int id){
        AuxiliarAdyacencia lista = new AuxiliarAdyacencia();
        Nodo temp = head;
        while (temp != null) {
            if (temp.inicio == id) {
                lista.insertar(temp.final_);
            }
            temp =temp.next;
        }
        return lista;
    }

    public void print() {
        Nodo temp = head;
        while (temp != null) {
            System.out.println("Inicio: " + temp.inicio);
            System.out.println("Final: " + temp.final_);
            System.out.println("Peso: " + temp.peso);
            System.out.println();
            temp = temp.next;
        }
    }

    public void graficar(){
        Nodo temp = head;
        String result = "digraph{";
        ArrayList<String> Nodos = new ArrayList<String>();
        ArrayList<String> conexiones = new ArrayList<String>();
        while (temp != null) {
            if (!Nodos.contains("N"+temp.inicio+"[label=\""+temp.inicio+"\"];\n") && !Nodos.contains("N"+temp.final_+"[label=\""+temp.final_+"\"];\n")) {
                Nodos.add("N"+temp.inicio+"[label=\""+temp.inicio+"\"];\n");
                Nodos.add("N"+temp.final_+"[label=\""+temp.final_+"\"];\n");
                conexiones.add("N"+temp.inicio+" -> " + "N"+temp.final_+"[xlabel=\""+temp.peso+"\"];\n");
            }else if(!Nodos.contains("N"+temp.final_+"[label=\""+temp.final_+"\"];\n")){
                Nodos.add("N"+temp.final_+"[label=\""+temp.final_+"\"];\n");
                conexiones.add("N"+temp.inicio+" -> " + "N"+temp.final_+"[xlabel=\""+temp.peso+"\"];\n");
            }else if (!Nodos.contains("N"+temp.inicio+"[label=\""+temp.inicio+"\"];\n")) {
                Nodos.add("N"+temp.inicio+"[label=\""+temp.inicio+"\"];\n");
                conexiones.add("N"+temp.inicio+" -> " + "N"+temp.final_+"[xlabel=\""+temp.peso+"\"];\n");
            }else{
                conexiones.add("N"+temp.inicio+" -> " + "N"+temp.final_+"[xlabel=\""+temp.peso+"\"];\n");
            }
            temp = temp.next;
        }
        for (String string : Nodos) {
            result += string;
        }
        for (String string : conexiones) {
            result += string;
        }
        result += "}";
        archivo(result);
    }
    
    private void archivo(String resultado) {
        String ubicacion_dot = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_3\\Graphviz\\Archivos_TXT\\GrafoRutas.txt";
        String ubicacion_img = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_3\\Graphviz\\Imagenes\\GrafoRutas.png";
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