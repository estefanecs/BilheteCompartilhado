/**
 * Componente Curricular: Sistemas Operacionais
 * Autor: Estéfane Carmo de Souza e Messias Jr. Lira da Silva
 * Data: 30-10-2020
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

/**
 * Classe para objetos do tipo CidadeList. Esta classe, possui uma referência
 * para o primeiro nó da lista.
 *
 * Exemplo de uso:
 *
 * CidadeList lista = new CidadeList();
 *
 * @author Estéfane Carmo de Souza 
 * @author Messias Jr. Lira da Silva
 */
import java.util.ArrayList;
import model.Cidade;

public class CidadeList {

    private Node primeiro;

    public CidadeList() {
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

    /**
     * Método que procura o cidade com o nome indicado
     *
     * @param cidade - nome da cidade
     * @return Cidade - a cidade encontrada
     */
    public Cidade procurarNo(String cidade) {
        Node auxiliar = primeiro;
        Cidade encontrado = null;
        while (auxiliar != null) { //Enquanto não for o fim da lista
            if (auxiliar.getConteudo().getNome().compareToIgnoreCase(cidade) == 0) { //se o nó atual for o desejado
                encontrado = auxiliar.getConteudo(); //encontrado é igual a cidade encontrada
            }
            auxiliar = auxiliar.getNext();
        }
        return encontrado;
    }

    /**
     * Método que obtém a posição de uma cidade
     *
     * @param cidade - nome da cidade a ser procurada
     * @return int - posicao
     */
    public int getPosicao(String cidade) {
        Node aux = primeiro;
        int posicao = 0;
        while (aux != null) {//enquanto não for o final da lista
            //se o cidade atual possuir o mesmo nome, retorna a posicao
            if (aux.getConteudo().getNome().compareToIgnoreCase(cidade) == 0) {
                return posicao;
            }
            aux = aux.getNext();
            posicao++;
        }
        return -1; //retorna -1 se não encontrou a cidade com o nome indicado
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
    public void add(Cidade obj) {
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
     * @return Node - O objeto encontrado
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

    /**
     * Método para a remorção de uma cidade cujo o nome foi indicado
     *
     * @param nome - nome da cidade a ser removida
     * @return Cidade - a cidadee removida
     */
    public Cidade remove(String nome) {
        if (!this.isEmpty()) {
            Node aux = primeiro;
            Node aux2 = primeiro;
            if (primeiro.getConteudo().getNome().compareToIgnoreCase(nome) == 0) { //se for o primeiro elemento
                primeiro = primeiro.getNext();
                return aux.getConteudo();
            } else {//se não, procura até que seja o fim da lista ou encontrar a cidade com o mesmo nome
                while (aux2 != null && aux2.getConteudo().getNome().compareToIgnoreCase(nome) != 0) {
                    aux = aux2;
                    aux2 = aux2.getNext();
                }
                if (aux2 != null) {//se encontrar, retorna o vertice
                    aux.setNext(aux2.getNext());
                    return aux2.getConteudo();
                }
            }
        }
        return null;
    }
    
    /**
     * Método que lista todos as cidades da lista
     *
     * @return ArrayList- lista contendo o nome das cidades
     */
    public ArrayList listarTodosPontos() {
        Node auxiliar = this.getPrimeiro();
        ArrayList<String> lista = new ArrayList<>();
        while (auxiliar != null) {
            lista.add(auxiliar.getConteudo().getNome());
            auxiliar = auxiliar.getNext();
        }
        return lista;
    }
    
}
