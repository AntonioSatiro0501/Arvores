public class NodeAVL<T> {
    
    public T valor;
    public NodeAVL<T> esq = null;
    public NodeAVL<T> dir = null;
    public int altura = 0;
    
    public NodeAVL(T valor){
        this.valor = valor;
    }

}