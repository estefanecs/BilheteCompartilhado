/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: Estéfane Carmo de Souza
 * Data: 24-08-2019
 *
 * Declaro que este código foi elaborado por mim de forma individual e
 * não contém nenhum trecho de código de outro colega ou de outro autor,
 * tais como provindos de livros e apostilas, e páginas ou documentos
 * eletrônicos da Internet. Qualquer trecho de código de outra autoria que
 * uma citação para o  não a minha está destacado com  autor e a fonte do
 * código, e estou ciente que estes trechos não serão considerados para fins
 * de avaliação. Alguns trechos do código podem coincidir com de outros
 * colegas pois estes foram discutidos em sessões tutorias.
 */
package util;

import model.Aresta;
import model.Vertice;

/**
 * Classe para objetos do tipo ArestaList. Esta classe, possui uma referência
 * para o primeiro nó da lista.
 *
 * Exemplo de uso:
 *
 * ArestaList lista = new ArestaList();
 *
 * @author Estéfane Carmo de Souza
 */
public class ArestaList {

    protected No primeiro;

    public ArestaList() {
    }

    /**
     * Método que obtém o primeiro elemento da lista
     *
     * @return No - Primeiro nó
     */
    public No getPrimeiro() {
        return primeiro;
    }

    /**
     * Método que altera a referência do primeiro elemento da lista
     *
     * @param primeiro - Novo primeiro
     */
    public void setPrimeiro(No primeiro) {
        this.primeiro = primeiro;
    }

    /**
     * Método que retorna o tamanho da lista.
     *
     * @return int - O tamanho
     */
    public int size() {
        int tamanho = 0; //Váriavel para armazenar o tamanho da lista
        No auxiliar = primeiro; //Variável para percorrer 
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
    public void add(Aresta obj) {
        No novoNo = new No(obj); //Novo nó a ser adicionado
        if (isEmpty()) {
            primeiro = novoNo;
        } else {
            No auxiliar = primeiro;
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
     * @return No - O objeto encontrado
     */
    public No get(int index) {
        int posicao = 0; //indica a posição atual
        No aux = primeiro; //variável para percorrer a lista
        if (isEmpty() || index < 0 || index >= this.size()) {
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

    /**
     * Método que obtém a posição de um vertice
     *
     * @param vertice - nome do vertice a ser procurado
     * @return int - posicao
     */
    public int getPosicao(String vertice) {
        No aux = primeiro;
        int posicao = 0;
        while (aux != null) { //enquanto não for o final da lista
            //se o vertice atual possuir o mesmo nome, retorna a posicao
            if (aux.getConteudo().getDestino().getNome().compareToIgnoreCase(vertice) == 0) {
                return posicao;
            }
            aux = aux.getNext();
            posicao++;
        }
        return -1;
    }

    /**
     * Método para a remorção de um vertice cujo o nome foi indicado
     *
     * @param nome - nome do vertice a ser removido
     * @return Vertice - o vertice removido
     */
    public Vertice remove(String nome) {
        if (!this.isEmpty()) {
            No aux = primeiro;
            No aux2 = primeiro;
            if (primeiro.getConteudo().getDestino().getNome().compareToIgnoreCase(nome) == 0) { //se for o primeiro elemento
                primeiro = primeiro.getNext();
                return aux.getConteudo().getDestino();
            } else { //se não, procura até que seja o fim da lista ou encontrar o vertice com o mesmo nome
                while (aux2 != null && aux2.getConteudo().getDestino().getNome().compareToIgnoreCase(nome) != 0) {
                    aux = aux2;
                    aux2 = aux2.getNext();
                }
            }
            if (aux2 != null) { //se encontrar, retorna o vertice
                aux.setNext(aux2.getNext());
                return aux2.getConteudo().getDestino();
            }
        }
        return null;
    }

}
