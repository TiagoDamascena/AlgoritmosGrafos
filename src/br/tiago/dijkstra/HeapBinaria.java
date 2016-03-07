package br.tiago.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Define uma heap binaria
 *
 * @author tiago
 */
public class HeapBinaria {

    private final List<No> heap; //Nós dessa heap

    public HeapBinaria() {
        heap = new ArrayList<>();
    }

    public void addNo(No no) {
        heap.add(no);
        subirNo(heap.size() - 1);
    }

    public No removeNo() {
        No v = heap.get(0);
        trocar(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        heapDescer(0);
        return v;
    }

    public void heapSubir(No u) {
        for (int i = 0; i < heap.size(); i++) {
            if (u.equals(heap.get(i))) {
                subirNo(i);
                break;
            }
        }
    }

    private void subirNo(int idNo) {
        int idAtual = idNo;
        No noAtual = heap.get(idAtual);
        int idPai = (idAtual - 1) / 2;
        No noPai = heap.get(idPai);
        while (noAtual.compareTo(noPai) == -1) {
            trocar(idAtual, idPai);
            idAtual = idPai;
            if (idAtual == 0) {
                break;
            }
            noAtual = heap.get(idAtual);
            idPai = (idAtual - 1) / 2;
            noPai = heap.get(idPai);
        }
    }

    public void heapDescer(int idNo) {
        if (heap.isEmpty()) {
            return;
        }

        int idAtual = idNo;
        No noAtual = heap.get(idAtual);
        int idFilhoEsquerda = idAtual * 2 - 2 + 2;
        int idFilhoDireita = idAtual * 2 - 2 + 3;
        int idFilho;
        if (idFilhoEsquerda >= heap.size()) {
            return;
        }
        if (idFilhoDireita >= heap.size()) {
            idFilho = idFilhoEsquerda;
        } else if (heap.get(idFilhoDireita).compareTo(heap.get(idFilhoEsquerda)) == -1) {
            idFilho = idFilhoDireita;
        } else {
            idFilho = idFilhoEsquerda;
        }
        No noFilho = heap.get(idFilho);
        while (noAtual.compareTo(noFilho) == 1) {
            trocar(idAtual, idFilho);
            idAtual = idFilho;
            noAtual = heap.get(idAtual);
            idFilhoEsquerda = idAtual * 2 - 2 + 2;
            idFilhoDireita = idAtual * 2 - 2 + 3;
            if (idFilhoEsquerda >= heap.size()) {
                return;
            }
            if (idFilhoDireita >= heap.size()) {
                idFilho = idFilhoEsquerda;
            } else if (heap.get(idFilhoDireita).compareTo(heap.get(idFilhoEsquerda)) == -1) {
                idFilho = idFilhoDireita;
            } else {
                idFilho = idFilhoEsquerda;
            }
        }
    }

    public void trocar(int idNo1, int idNo2) {
        Collections.swap(heap, idNo1, idNo2);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
