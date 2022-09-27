package Estructuras;

public class AuxiliarAdyacencia {
    class Nodo{
        int id;
        Nodo next;
        public Nodo(int id){
            this.id = id;
            this.next = null;
        }
    }

    Nodo head;
    Nodo cola;
    public void insertar(int id) {
        Nodo newNodo = new Nodo(id);
        if (head == null) {
            head = newNodo;
            cola = newNodo;
        }else{
            cola.next = newNodo;
            cola = newNodo;
        }
    }

    public Nodo getHead() {
        return head;
    }

    public boolean exist(int id){
        if (head != null) {
            Nodo temp = head;
            while (temp != null) {
                if (temp.id == id) {
                    return true;
                }
                temp = temp.next;
            }
            return false;                
        }
        return false;                
    }
    public String graficar() {
        String resutado = "";
        Nodo temp = head;
        while (temp != null) {
            resutado += "AD"+temp.hashCode()+temp.id+"[label=\"{ <data> "+temp.id+" | <ref>}\"];\n";
            if (temp.next != null) {
                resutado += "AD"+temp.next.hashCode()+temp.next.id+"[label=\"{ <data> "+temp.next.id+" | <ref>}\"];\n";
                resutado += "AD"+temp.hashCode()+temp.id+":ref -> "+"AD"+temp.next.hashCode()+temp.next.id+":data;";
            }
            temp = temp.next;
        }

        return resutado;
    }
}