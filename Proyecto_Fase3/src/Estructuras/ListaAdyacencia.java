package Estructuras;

import Proyecto_Fase3.Actividades;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ListaAdyacencia {
    class Nodo{
        int id;
        AuxiliarAdyacencia list;
        Nodo next;
        public Nodo(int id, AuxiliarAdyacencia list) {
            this.id = id;
            this.list = list;
            this.next = null;
        }
    }

    Nodo head;
    Nodo cola;

    public void llenado_automatico(){
        if (!Actividades.listaRutas.isEmpty()) {
            AuxiliarAdyacencia auxIds = Actividades.listaRutas.getInicios();
            AuxiliarAdyacencia.Nodo nodo = auxIds.getHead();
            while (nodo !=null) {
                AuxiliarAdyacencia finales = Actividades.listaRutas.getFinales(nodo.id);
                this.insertar(nodo.id, finales);
                nodo = nodo.next;
            }
        }
    }

    public void insertar(int id, AuxiliarAdyacencia list){
        Nodo newNodo = new Nodo(id, list);

        if (head == null) {
            head = newNodo;
            cola = newNodo;
        }else{
            cola.next = newNodo;
            cola = newNodo;
        }
    }

    public void graficar(){
        String result = "digraph{\nrankdir=LR;\ngraph[splines=ortho];\nnode[shape=record];edge[tailclip=none arrowhead=dot, arrowtail=vee, dir=both];\n";
        Nodo temp = head;
        while (temp != null) {
            result += "N"+temp.hashCode()+"[label=\"{<data> "+temp.id+" | <ref>}\"];\n";
            if (temp.next != null) {
                result += "N"+temp.next.hashCode()+"[label=\"{<data> "+temp.next.id+" | <ref>}\"];\n";
                result += "{rank=same\nN"+temp.hashCode()+":data"+" -> "+"N"+temp.next.hashCode()+":data"+"[style=invis];\n}\n";
            }
            AuxiliarAdyacencia.Nodo aux = temp.list.getHead();
            result += "N"+temp.hashCode()+":ref"+" -> AD"+aux.hashCode()+aux.id+";";
            result += temp.list.graficar();
            temp = temp.next;
        }
        result += "\n}";
        archivo(result);
    }

    private void archivo(String resultado) {
        String ubicacion_dot = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_3\\Graphviz\\Archivos_TXT\\Adyacencia.txt";
        String ubicacion_img = "C:\\Users\\denni\\Documents\\Varios_Progra\\EDD_PROYECTOS\\FASE_3\\Graphviz\\Imagenes\\Adyacencia.png";
        File dotFile = new File(ubicacion_dot);
        try {dotFile.createNewFile();} catch (Exception e) {}
        FileWriter writer;
        try {
            writer = new FileWriter(ubicacion_dot);
            writer.write(resultado);
            writer.close();
        } catch (Exception e) {}
        String comando = "dot -Tpng " + ubicacion_dot + " -o " + ubicacion_img;
        ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c"+comando);
        builder.redirectErrorStream(true);
        try {builder.start();} catch (IOException e) {}
    }
}