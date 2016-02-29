package br.tiago.dijkstra;

import java.util.Random;

/**
 *
 * @author tiago
 */
public class GrafoAleatorio {
    
    public static Grafo gerar(int tamanho) {
        Grafo grafo = new Grafo();
        
        No[] nos = new No[tamanho];
        
        int i = 0;
        for(i=0; i<tamanho; i++) {
            nos[i] = new No(String.valueOf(i+1));
            grafo.addNo(nos[i]);
        }
        
        Random rand = new Random();
        
        for(i=0; i<tamanho; i++) {
            int arestas = rand.nextInt(tamanho/3)+tamanho/3;
            for(int j=0; j<arestas; j++) {
                int dPos = rand.nextInt(tamanho);
                int custo = rand.nextInt(190)+10;
                No node = nos[dPos];
                new Aresta(nos[i], nos[dPos], custo);
            }
        }
        
        return grafo;
    }
}
