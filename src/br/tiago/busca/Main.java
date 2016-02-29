package br.tiago.busca;

/**
 * Classe principal do programa
 * @author tiago
 */
public class Main {
    
    /**
     * Monta o grafo e executas as buscas em largura e profundidade
     * @param args argumentos de execução
     */
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        
        //Cria os nós do grafo
        No no1 = new No(1);
        grafo.addNo(no1);
        
        No no2 = new No(2);
        grafo.addNo(no2);
        
        No no3 = new No(3);
        grafo.addNo(no3);
        
        No no4 = new No(4);
        grafo.addNo(no4);
        
        No no5 = new No(5);
        grafo.addNo(no5);
        
        No no6 = new No(6);
        grafo.addNo(no6);
        
        No no7 = new No(7);
        grafo.addNo(no7);
        
        //Cria as arestas do nó
        grafo.criarAresta(no1, no2);
        grafo.criarAresta(no1, no3);
        grafo.criarAresta(no1, no4);
        
        grafo.criarAresta(no2, no4);
        grafo.criarAresta(no2, no6);
        
        grafo.criarAresta(no4, no7);
        
        grafo.criarAresta(no5, no6);
        grafo.criarAresta(no5, no7);
        
        grafo.criarAresta(no6, no7);
        
        //Realiza as buscas
        System.out.println("Busca em profundidade");
        grafo.buscaEmProfundidade(no1);
        System.out.println("\nBusca em largura");
        grafo.buscaEmLargura(no1);
        
        //Exibe o resultado final no grafo
        System.out.println("\n\nGrafo completo");
        grafo.mostrarGrafo();
    }   
}
