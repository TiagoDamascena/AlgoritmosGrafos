package br.tiago.dijkstra;

/**
 * Cria uma aresta em um grafo
 * @author tiago
 */
public abstract class NovaAresta {
    
    /**
     * Cria aresta de um grafo não orientado
     * @param no1 Primeiro nó
     * @param no2 Segundo Nó
     * @param custo Custo da aresta
     */
    public static void criaAresta(No no1, No no2, int custo) {
        Aresta aresta1 = new Aresta(no1, no2, custo);
        
        Aresta aresta2 = new Aresta(no2, no1, custo);
    }
}
