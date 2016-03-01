package br.tiago.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Definição de um grafo
 * @author bruno
 */
public class Grafo {
    private List<No> nos = new ArrayList<>(); //Nós desse grafo
    
    public void addNo(No no) {
        nos.add(no);
    }
    
    public No getNo(int pos) {
        return nos.get(pos);
    }
    
    /**
     * Encontra o menor caminho entre dois pontos utilizando o algoritmo de dijkstra
     * @param inicio origem da busca
     * @param fim destino da busca
     */
    public void dijkstra(No inicio, No fim) {
        inicio.setCustoMinimo(0);  //O inicio tem custo 0
        
        List<No> nosAbertos = new ArrayList<>(); //Lista de nós abertos
        
        //Popula a lista com todos os nós abertos
        for(No no: nos) {
            nosAbertos.add(no);
        }
        
        No atual = inicio; //Nó em que estamos atualmente
        
        //Encontra os caminhos mínimos para todos os nós a partir da origem
        while(!nosAbertos.isEmpty()) {
            for(Aresta aresta: atual.getAdjacentes()) { //Calcula o custo para todos os nós adjacentes
                int novoCusto = atual.getCustoMinimo() + aresta.getCusto();
                int antigoCusto = aresta.getDestino().getCustoMinimo();
                if(novoCusto < 0) {
                    novoCusto = Integer.MAX_VALUE;
                }
                if(novoCusto <= antigoCusto) {
                   aresta.getDestino().setCustoMinimo(novoCusto); //Altera o custo mínimo de um nó
                   aresta.getDestino().setAntecessor(atual); //Define o nó antecessor
                }
            }
            
            atual.setEstadoNo(EstadoNo.FECHADO); //Fecha o nó atual
            nosAbertos.remove(atual); //Remove o nó atual da lista de nós abertos
            Collections.sort(nosAbertos); //Ordena a lista de nós abertos
            if(!nosAbertos.isEmpty()) {
                atual = nosAbertos.get(0); //Pega o nó com o menos custo
            }
        }        
        
        //Controi o menor caminho da origem até o destino
        Stack<No> caminhoMinimo = new Stack<>(); //Uma pilha para montar o caminha mínimo do fim para o inicio
        caminhoMinimo.addElement(fim); //Adiciona o fim ao caminho minimo
        
        atual = fim; //Começa pelo fim
        if(atual.getAntecessor() != null) {
            while(!atual.equals(inicio)) { //Se repete até voltarmos ao início
                atual = atual.getAntecessor();
                caminhoMinimo.addElement(atual);
            }
            int cont = caminhoMinimo.size();
            //Exibe todos os nós na pilha de caminho mínimo
            while(!caminhoMinimo.empty()) {
                atual = caminhoMinimo.pop();
                System.out.println("NO:" + atual.getId());
            }
            
            System.out.println("\nTAMANHO: "+cont);
        } else {
            System.out.println("Não há caminho até esse Nó");
        }
    }
    
    /**
     * Encontra o menor caminho entre dois pontos utilizando o algoritmo de dijkstra implementado com heap
     * @param inicio origem da busca
     * @param fim destino da busca
     */
    public void dijkstraHeap(No inicio, No fim) {
        inicio.setCustoMinimo(0);  //O inicio tem custo 0
        
        List<No> nosAbertos = new ArrayList<>(); //Lista de nós abertos
        
        //Popula a lista com todos os nós abertos
        for(No no: nos) {
            nosAbertos.add(no);
        }
        
        No atual = inicio; //Nó em que estamos atualmente
        
        //Encontra os caminhos mínimos para todos os nós a partir da origem
        while(!nosAbertos.isEmpty()) {
            for(Aresta aresta: atual.getAdjacentes()) { //Calcula o custo para todos os nós adjacentes
                int novoCusto = atual.getCustoMinimo() + aresta.getCusto();
                
                if(novoCusto < aresta.getDestino().getCustoMinimo()) {
                   aresta.getDestino().setCustoMinimo(novoCusto); //Altera o custo mínimo de um nó
                   aresta.getDestino().setAntecessor(atual); //Define o nó antecessor
                }
            }
            
            atual.setEstadoNo(EstadoNo.FECHADO); //Fecha o nó atual
            nosAbertos.remove(atual); //Remove o nó atual da lista de nós abertos
            Collections.sort(nosAbertos); //Ordena a lista de nós abertos
            if(!nosAbertos.isEmpty()) {
                atual = nosAbertos.get(0); //Pega o nó com o menos custo
            }
        }        
        
        //Controi o menor caminho da origem até o destino
        Stack<No> caminhoMinimo = new Stack<>(); //Uma pilha para montar o caminha mínimo do fim para o inicio
        caminhoMinimo.addElement(fim); //Adiciona o fim ao caminho minimo
        
        atual = fim; //Começa pelo fim
        while(!atual.equals(inicio)) { //Se repete até voltarmos ao início
            atual = atual.getAntecessor();
            caminhoMinimo.addElement(atual);
        }
        
        //Exibe todos os nós na pilha de caminho mínimo
        while(!caminhoMinimo.empty()) {
            atual = caminhoMinimo.pop();
            System.out.println("NO:" + atual.getId());
        }
    }
}
    
