package br.tiago.dijkstra;

/**
 * Classe principal do programa
 * @author bruno
 */
public class Main {
    
    /**
     * Monta o grafo e busca o menor caminho entre dois pontos
     * @param args argumentos de execução
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        /*Grafo grafo = new Grafo();
        
        //Cria os nós do grafo
        No no1 = new No("SF");
        grafo.addNo(no1);
        No no2 = new No("LA");
        grafo.addNo(no2);
        No no3 = new No("D");
        grafo.addNo(no3);
        No no4 = new No("C");
        grafo.addNo(no4);
        No no5 = new No("A");
        grafo.addNo(no5);
        No no6 = new No("B");
        grafo.addNo(no6);
        No no7 = new No("NY");
        grafo.addNo(no7);
        No no8 = new No("M");
        grafo.addNo(no8);
        
        //Cria as arestas do nó
        new Aresta(no1, no2, 39);
        new Aresta(no2, no1, 99);
        new Aresta(no1, no3, 89);
        new Aresta(no1, no4, 120);
        new Aresta(no1, no7, 210);
        new Aresta(no2, no3, 50);
        new Aresta(no2, no7, 170);
        new Aresta(no3, no4, 65);
        new Aresta(no4, no6, 79);
        new Aresta(no4, no7, 59);
        new Aresta(no4, no5, 99);
        new Aresta(no5, no7, 80);
        new Aresta(no5, no8, 70);
        new Aresta(no6, no7, 20);
        new Aresta(no7, no8, 66);
        
        //Descobre o menor caminho
        grafo.dijkstra(no1,no8);*/
        
        Grafo grafoAleatorio = GrafoAleatorio.gerar(5000, 3, 10, 200);
        
        grafoAleatorio.dijkstra(0, 4512);
        grafoAleatorio.dijkstraHeap(0, 4512);
    }
}