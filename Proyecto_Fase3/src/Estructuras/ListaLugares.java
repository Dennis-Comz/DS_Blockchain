package Estructuras;

public class ListaLugares {
    public class Nodo{
        int id;
        String departamento;
        String nombre;
        String sn_sucursal;
        Nodo next;
        public Nodo(int id, String departamento, String nombre, String sn_sucursal) {
            this.id = id;
            this.departamento = departamento;
            this.nombre = nombre;
            this.sn_sucursal = sn_sucursal;
            this.next = null;
        }
    }

    Nodo head;
    Nodo cola;

    public void insertar(int id, String departamento, String nombre, String sn_sucursal){
        Nodo newNodo = new Nodo(id, departamento, nombre, sn_sucursal);

        if (head == null) {
            head = newNodo;
            cola = newNodo;
        }else{
            cola.next = newNodo;
            cola = newNodo;
        }
    }

    public void print(){
        Nodo temp = head;
        while (temp != null) {
            System.out.println("ID: " + temp.id);
            System.out.println("Departamento: " + temp.departamento);
            System.out.println("Nombre: " + temp.nombre);
            System.out.println("Sucursal: " + temp.sn_sucursal);
            System.out.println();
            temp = temp.next;
        }
    }
}