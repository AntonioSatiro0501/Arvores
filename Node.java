public class Node<T extends Comparable<T>>{

    public T valor;
    public Node<T> esq = null;
    public Node<T> dir = null;
    
    public Node(T valor){
        this.valor = valor;
    }

}