public class ArvoreAVL< T extends Comparable<T>> {
    
    int altura = - 1;
    NodeAVL<T> raiz = null;

    public ArvoreAVL(){}

    private int altura(NodeAVL<T> no) {

        return no == null ? -1 : no.altura;
    }

    private int fatorBalanceamento(NodeAVL<T> no) {
        return no == null ? 0 : altura(no.esq) - altura(no.dir);
    }


    private void atualizaAltura(NodeAVL<T> no) {
        no.altura = 1 + Math.max(altura(no.esq), altura(no.dir));
    }


    public NodeAVL<T> rotacaoDireita(NodeAVL<T> z) {
        NodeAVL<T> y = z.esq;
        NodeAVL<T> T3 = y.dir;

        y.dir = z;
        z.esq = T3;

        atualizaAltura(z);
        atualizaAltura(y);

        return y;
}

    public NodeAVL<T> rotacaoEsquerda(NodeAVL<T> z) {
        NodeAVL<T> y = z.dir;
        NodeAVL<T> T3 = y.esq;

        y.esq = z;
        z.dir = T3;

        atualizaAltura(z);
        atualizaAltura(y);

        return y;
    }


    public NodeAVL<T> checaBalanceamento(NodeAVL<T> no) {
        atualizaAltura(no);
        int fb = fatorBalanceamento(no);

        if (fb > 1 && fatorBalanceamento(no.esq) >= 0)
            return rotacaoDireita(no);

    
        if (fb > 1 && fatorBalanceamento(no.esq) < 0) {
            no.esq = rotacaoEsquerda(no.esq);
            return rotacaoDireita(no);
    }

        // Caso Right-Right → rotação esquerda simples
        if (fb < -1 && fatorBalanceamento(no.dir) <= 0)
            return rotacaoEsquerda(no);

        // Caso Right-Left → rotação direita no filho + rotação esquerda na raiz
        if (fb < -1 && fatorBalanceamento(no.dir) > 0) {
            no.dir = rotacaoDireita(no.dir);
            return rotacaoEsquerda(no);
        }

        return no; // já balanceado
}


    public NodeAVL<T> inserirRecursivo(NodeAVL<T> noAtual, T valor, boolean[] jaExistente) {
        // Inserção BST padrão
        if (noAtual == null){
            return new NodeAVL<T>(valor);
        }

        int cmp = valor.compareTo(noAtual.valor);

        if (cmp > 0)
            noAtual.dir = inserirRecursivo(noAtual.dir, valor, jaExistente);
        else if (cmp < 0)
            noAtual.esq = inserirRecursivo(noAtual.esq, valor, jaExistente);
        else{
            jaExistente[0] = true;
            return noAtual;
        }
        // Reequilibra na volta da recursão
        return checaBalanceamento(noAtual);
    }

    public boolean inserirNode(T valor) {
        boolean[] jaExistente = {false};
        raiz = inserirRecursivo(raiz, valor, jaExistente);
        return !jaExistente[0];
    }


    public void printEmOrdem(NodeAVL<T> no){

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

    public void printPreOrdem(NodeAVL<T> no){

        System.out.println(no.valor);

        if (no.esq != null){
            printPreOrdem(no.esq);
        }

        if(no.dir != null){
            printPreOrdem(no.dir);
        }
    }

    public void printPosOrdem(NodeAVL<T> no){

        if (no.esq != null){
            printPosOrdem(no.esq);
        }

        if(no.dir != null){
            printPosOrdem(no.dir);
        }

        System.out.println(no.valor);
    }

    public void atualizaAltura(NodeAVL<T> no, int contador){

        if (raiz == null){
            return;
        }

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

    public void ordenaVetor(T vetor[], NodeAVL<T> no){
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

    public void ordenaVetor(T vetor[], int indice[], NodeAVL<T> no){
        
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
