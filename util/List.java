
package util;

import model.Vertice;
import java.util.Iterator;

public class List {

    private Node primeiro;

    public List() {
    }

    public Node getPrimeiro() {
        return primeiro;
    }

    /**
     * Método que altera a referência do primeiro elemento da lista
     *
     * @param primeiro - Novo primeiro
     */
    public void setPrimeiro(Node primeiro) {
        this.primeiro = primeiro;
    }

    public Vertice procurarNo(String m) {
        Node auxiliar = primeiro;
        Vertice encontrado = null;
        while (auxiliar != null) {
            if (auxiliar.getConteudo().getNome().equals(m)) {
                encontrado = auxiliar.getConteudo();
            }
            auxiliar = auxiliar.getNext();
        }
        return encontrado;
    }

    public int getPosicao(String n) {
        Node aux = primeiro;
        int posicao = 0;
        while (aux != null) {
            if (aux.getConteudo().getNome().equals(n)) {
                return posicao;
            }
            aux = aux.getNext();
            posicao++;
        }
        return posicao;
    }

    /**
     * Método que retorna o tamanho da lista.
     *
     * @return int - O tamanho
     */
    public int size() {
        int tamanho = 0; //Váriavel para armazenar o tamanho da lista
        Node auxiliar = primeiro; //Variável para percorrer 
        while (auxiliar != null) { //Enquanto não for o fim da lista
            tamanho++; //Incrementa-se 1 ao tamanho
            auxiliar = auxiliar.getNext(); //Passa para o próximo nó
        }
        return tamanho;
    }

    /**
     * Método que adiciona um elemento no fim da lista. Se a lista estiver
     * vazia, será adicionada ao inicio.
     *
     * @param obj - Objeto a ser adicionado
     */
    public void add(Vertice obj) {
        Node novoNo = new Node(obj); //Novo nó a ser adicionado
        if (isEmpty()) {
            primeiro = novoNo;
        } else {
            Node auxiliar = primeiro;
            while (auxiliar.getNext() != null) { //Percorre a lista até o ultimo elemento
                auxiliar = auxiliar.getNext();
            }
            auxiliar.setNext(novoNo); //Adiciona o nó
        }
    }

    /**
     * Método que retorna um nó da lista, que está na posição indicada
     *
     * @param index - Posição do nó a ser buscado
     * @return Object - O objeto encontrado
     */
    public Node get(int index) {
        int posicao = 0; //indica a posição atual
        Node aux = primeiro; //variável para percorrer a lista
        if (isEmpty() || index < 0 || index > this.size()) {
            return null;
        } else { //Se o index for um número dentro do tamanho da lista 
            while (posicao != index) { //Percorre até encontrar a posição
                aux = aux.getNext();
                posicao++;
            }
            return aux;
        }
    }

    /**
     * Método que verifica se a lista está vazia
     *
     * @return true - Se estiver vazia
     */
    public boolean isEmpty() {
        return (primeiro == null);
    }
    public Vertice remove(String nome) {
        Node aux = primeiro;
        Node aux2 = primeiro;
        while (aux2 != null && aux2.getConteudo().getNome().equals(nome)) {
            aux = aux2;
            aux2 = aux2.getNext();
        }
        if (aux2 != null) {
            aux.setNext(aux2.getNext());
            return aux2.getConteudo();
        }
        return null;
    }
      /**
     * Procura na lista todos os procedimentos iguais ao indicado, e adiciona em
     * uma lista auxiliar.
     *
     * @param ponto
     * @param procedimento- procedimento a ser buscado na lista
     * @return Iterator - iterador da lista com os agendamentos encontrados
     */
    public Iterator procurarPonto(String ponto) {
        List listaAuxiliar = new List(); //Lista auxiliar
        Iterator iterar = new Iterador(primeiro); //Iterador para percorrer a lista
        Vertice percorrer;//Permite analisar o conteúdo do nó
        while (iterar.hasNext()) {
            percorrer = (Vertice) iterar.next(); //Aponta para o proximo nó
            if (percorrer.getTipo().compareToIgnoreCase(ponto)==0) { //Se o procedimento for igual
                listaAuxiliar.add(percorrer); //Adciona a lista
            }
        }
        return (Iterador) listaAuxiliar.iterator();
    }
     /**
     * Método que retorna o Iterador da Lista
     *
     * @return Iterator
     */
    public Iterator iterator() {
        Iterador it = new Iterador(primeiro);
        return it;
    } 
}
