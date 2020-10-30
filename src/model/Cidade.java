/**
 * Componente Curricular:  Sistemas Operacionais
 * Autor: Estéfane Carmo de Souza e Messias Jr. Lira da Silva
 * Data: 27-10-2020
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
package model;

import util.TrechoList;

/**
 * Esta classe armazena os dados de um vertice, bem como o nome,  se o
 * vertice foi visitado ou não e armazena a lista de adjacências que o o vertice
 * possui.
 *
 * Exemplo de uso:

 Cidade vertice= new Cidade(nome, tipo);
 *
 * @author Estéfane Carmo de Souza 
 * @author Messias Jr. Lira da Silva
 */
public class Cidade {

    private String nome;
    private boolean visitado;
    private TrechoList adjacencias;

    public Cidade(String nome) {
        this.nome = nome;
        this.visitado = false;
        this.adjacencias = new TrechoList();
    }

    /**
     * Método que obtém o nome do vértice
     *
     * @return String - nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que altera o nome do vértice
     *
     * @param nome - o novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método que obtém se o vertice foi visitado
     *
     * @return true - se estiver visitado
     */
    public boolean isVisitado() {
        return visitado;
    }

    /**
     * Método que altera a visitação do vértice
     *
     * @param visitado - nova visitação do vertice
     */
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    /**
     * Método que obtem a lista de adjacência do vértice
     *
     * @return TrechoList - lista de adjacencias;
     */
    public TrechoList getAdjacencias() {
        return adjacencias;
    }

}
