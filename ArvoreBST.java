public class ArvoreBST<T extends Comparable<T>>{
    
    public int altura = 0;
    public Node<T> raiz = null;

    public ArvoreBST(){
    }

    public boolean procuraPai(Node<T> noInserir, Node<T> noAtual){

        if (noInserir.valor.compareTo(noAtual.valor) > 0){

            if (noAtual.dir == null){
                noAtual.dir = noInserir;
                return true;
            }

            return procuraPai(noInserir, noAtual.dir);
        }

        if (noInserir.valor.compareTo(noAtual.valor) < 0){

            if (noAtual.esq == null){
                noAtual.esq = noInserir;
                return true;
            }

            return procuraPai(noInserir, noAtual.esq);
        }

        return false;
        
    }

    public boolean inserirNo(T valor){

        Node<T> no = new Node<T>(valor);

        if (raiz == null){
            raiz = no;
            return true;
        }

        return procuraPai(no, raiz);
    }

    public void printEmOrdem(Node<T> no){

        if (no.esq == null && no.dir == null){
            System.out.println(no.valor + " ");
            return;
        }

        if (no.esq != null){
            printEmOrdem(no.esq);
        }
        
        System.out.println(no.valor + " ");
        
        if (no.dir != null){
            printEmOrdem(no.dir);
        }
        
        return;
    }

    public void printPreOrdem(Node<T> no){

        System.out.println(no.valor);

        if (no.esq != null){
            printPreOrdem(no.esq);
        }

        if(no.dir != null){
            printPreOrdem(no.dir);
        }
    }

    public void printPosOrdem(Node<T> no){

        if (no.esq != null){
            printPosOrdem(no.esq);
        }

        if(no.dir != null){
            printPosOrdem(no.dir);
        }

        System.out.println(no.valor);
    }

    public void atualizaAltura(Node<T> no, int contador){

        if (contador > altura){
            altura = contador;
        }

        if (no.esq != null){
            atualizaAltura(no.esq, contador + 1);
        }

        if (no.dir != null){
            atualizaAltura(no.dir, contador + 1);
        }
    }

    public void ordenaVetor(T vetor[], Node<T> no){
        int indice[] = {0};

        ordenaVetor(vetor, indice, no);

        for (int i = 0; i < vetor.length - 1; i++){
            if (vetor[i].compareTo(vetor[i + 1]) > 0 || vetor[i].compareTo(vetor[i + 1]) == 0){
                for (int j = i + 1; j < vetor.length; j ++ ){
                    vetor[j] = null;
                }
                break;
            }
        }
    }

    public void ordenaVetor(T vetor[], int indice[], Node<T> no){
        
        if (no.esq == null && no.dir == null){
            
            vetor[indice[0]] = no.valor;
            indice[0] += 1;
            return;
        }

        if (no.esq != null){
            ordenaVetor(vetor, indice, no.esq);
        }
        
        vetor[indice[0]] = no.valor;
        indice[0] += 1;
        
        if (no.dir != null){
            ordenaVetor(vetor, indice, no.dir);
        }
        
        return;
    }
}