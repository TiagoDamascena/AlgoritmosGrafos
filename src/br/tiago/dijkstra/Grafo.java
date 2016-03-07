package br.tiago.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Definição de um grafo
 *
 * @author bruno
 */
public class Grafo {

    private final List<No> nos = new ArrayList<>(); //Nós desse grafo

    public void addNo(No no) {
        nos.add(no);
    }

    public No getNo(int pos) {
        return nos.get(pos);
    }

    /**
     * Encontra o menor caminho entre dois pontos utilizando o algoritmo de
     * dijkstra
     *
     * @param start origem da busca
     * @param end destino da busca
     */
    public void dijkstra(int start, int end) {
        long tempoInicial = System.currentTimeMillis();
        
        No inicio = nos.get(start);
        No fim = nos.get(end);
        
        inicio.setCustoMinimo(0);  //O inicio tem custo 0

        List<No> nosAbertos = new ArrayList<>(); //Lista de nós abertos

        //Popula a lista com todos os nós abertos
        for (No no : nos) {
            nosAbertos.add(no);
        }

        No atual = inicio; //Nó em que estamos atualmente

        //Encontra os caminhos mínimos dos nós a partir da origem
        while (!nosAbertos.isEmpty()) {
            for (Aresta aresta : atual.getAdjacentes()) { //Calcula o custo para todos os nós adjacentes
                int novoCusto = atual.getCustoMinimo() + aresta.getCusto();
                int antigoCusto = aresta.getDestino().getCustoMinimo();
                
                if (novoCusto < antigoCusto) {
                    aresta.getDestino().setCustoMinimo(novoCusto); //Altera o custo mínimo de um nó
                    aresta.getDestino().setAntecessor(atual); //Define o nó antecessor
                }
            }

            atual.setEstadoNo(EstadoNo.FECHADO); //Fecha o nó atual
            nosAbertos.remove(atual); //Remove o nó atual da lista de nós abertos
            Collections.sort(nosAbertos); //Ordena a lista de nós abertos
            if (!nosAbertos.isEmpty()) {
                atual = nosAbertos.get(0); //Pega o nó com o menos custo
            }
        }
        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - tempoInicial;

        //Constroi o menor caminho da origem até o destino
        Stack<No> caminhoMinimo = new Stack<>(); //Uma pilha para montar o caminha mínimo do fim para o inicio
        caminhoMinimo.addElement(fim); //Adiciona o fim ao caminho minimo

        atual = fim; //Começa pelo fim
        while (!atual.equals(inicio)) { //Se repete até voltarmos ao início
            atual = atual.getAntecessor();
            caminhoMinimo.addElement(atual);
        }
        int count = caminhoMinimo.size();
        //Exibe todos os nós na pilha de caminho mínimo
        while (!caminhoMinimo.empty()) {
            atual = caminhoMinimo.pop();
            //System.out.println("NO:" + atual.getId());
        }
        
        resetGrafo();
        
        System.out.println("COM HEAP");
        System.out.println("\nTAMANHO: " + count);
        System.out.println("TEMPO: " + tempoTotal+"ms");
    }

    /**
     * Encontra o menor caminho entre dois pontos utilizando o algoritmo de
     * dijkstra implementado com heap
     *
     * @param start origem da busca
     * @param end destino da busca
     */
    public void dijkstraHeap(int start, int end) {
        long startTime = System.currentTimeMillis();
        
        No inicio = nos.get(start);
        No fim = nos.get(end);
        
        inicio.setCustoMinimo(0);  //O inicio tem custo 0

        HeapBinaria heap = new HeapBinaria(); //Heap de nós

        heap.addNo(inicio); //Adiciona a raiz a heap
        inicio.setEstadoNo(EstadoNo.VISITADO); //Altera o estado do nó inicial para visitado

        //Encontra os caminhos mínimos dos nós a partir da origem
        while (!heap.isEmpty()) {
            No atual = heap.removeNo();
            atual.setEstadoNo(EstadoNo.FECHADO);
            for (Aresta temp : atual.getAdjacentes()) {
                if (temp.getDestino().getEstadoNo() == EstadoNo.ABERTO) {
                    heap.addNo(temp.getDestino());
                    temp.getDestino().setEstadoNo(EstadoNo.VISITADO);
                }
                if (temp.getDestino().getCustoMinimo() > atual.getCustoMinimo() + temp.getCusto()) {
                    temp.getDestino().setCustoMinimo(atual.getCustoMinimo() + temp.getCusto());
                    temp.getDestino().setAntecessor(atual);
                    heap.heapSubir(temp.getDestino());
                }
            }
        }
        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - startTime;

        //Constroi o menos caminho da origem até o destino
        Stack<No> caminhoMinimo = new Stack<>(); //Uma pilha para montar o caminha mínimo do fim para o inicio
        caminhoMinimo.addElement(fim); //Adiciona o fim ao caminho minimo
        
        No atual = fim; //Começa pelo fim
        while (!atual.equals(inicio)) { //Se repete até voltarmos ao início
            atual = atual.getAntecessor();
            caminhoMinimo.addElement(atual);
        }
        int count = caminhoMinimo.size();
        //Exibe todos os nós na pilha de caminho mínimo
        while (!caminhoMinimo.empty()) {
            atual = caminhoMinimo.pop();
            //System.out.println("NO:" + atual.getId());
        }
        
        resetGrafo();
        
        System.out.println("COM HEAP");
        System.out.println("\nTAMANHO: " + count);
        System.out.println("TEMPO: " + tempoTotal+"ms");
    }
    
    private void resetGrafo() {
        for(No no: nos) {
            no.setCustoMinimo(Integer.MAX_VALUE-10);
            no.setAntecessor(null);
            no.setEstadoNo(EstadoNo.ABERTO);
        }
    }

}
