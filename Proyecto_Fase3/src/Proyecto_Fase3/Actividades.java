package Proyecto_Fase3;

import Estructuras.*;

public class Actividades {
    public static Actividades objetos;
    public static ArbolB arbol_b = new ArbolB();
    public static ListaLugares listaLugares = new ListaLugares();
    public static ListaRutas listaRutas = new ListaRutas();
    public static TablaHash tabla = new TablaHash();
    public static ListaAdyacencia adyacencia = new ListaAdyacencia();

    public void initialize() {
        try {
            arbol_b = new ArbolB();
            listaLugares = new ListaLugares();
            listaRutas = new ListaRutas();
            tabla = new TablaHash();
            adyacencia = new ListaAdyacencia();
        } catch (Exception e) {}
    }

    public static Actividades getInstance() {
        if (objetos == null) {
            arbol_b = new ArbolB();
            listaLugares = new ListaLugares();
            listaRutas = new ListaRutas();
            objetos = new Actividades();
            tabla = new TablaHash();
            adyacencia = new ListaAdyacencia();
        }
        return objetos;
    }
}
