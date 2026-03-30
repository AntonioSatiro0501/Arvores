public class Main {
    
    public static void main(String[] args){

        Integer[] valores = {3, 6, 5, 7, 8, 2, 3, 9};

        ArvoreAVL<Integer> arvore = new ArvoreAVL<Integer>();

        for (int i = 0; i < valores.length; i++){
            boolean inseriu = arvore.inserirNode(valores[i]);
            if (inseriu == true){
                System.out.println("No inserido para valor " + valores[i] + "\n");
                continue;
            } 
            
            System.out.println("Falha ao inserir no para valor " + valores[i] + "\n");
        }

        arvore.atualizaAltura(arvore.raiz,0);
        System.out.println(arvore.altura + "\n\n");

        arvore.printPreOrdem(arvore.raiz);
        arvore.ordenaVetor(valores, arvore.raiz);
        System.out.println("\n");
        for (int i = 0; i < valores.length; i++){
            System.out.println(valores[i]);
        }
    }
}
