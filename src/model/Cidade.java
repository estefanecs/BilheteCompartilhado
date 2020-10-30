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
 * Esta classe armazena os dados de uma cidade, bem como o nome,
 * armazena a lista de adjacências que a cidade possui.
 *
 * Exemplo de uso:

 Cidade cidade= new Cidade(nome);
 *
 * @author Estéfane Carmo de Souza 
 * @author Messias Jr. Lira da Silva
 */
public class Cidade {

    private String nome;
    private TrechoList adjacencias;

    public Cidade(String nome) {
        this.nome = nome;
        this.adjacencias = new TrechoList();
    }

    /**
     * Método que obtém o nome da cidade
     *
     * @return String - nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que altera o nome da cidade
     *
     * @param nome - o novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método que obtem a lista de adjacência da cidade
     *
     * @return TrechoList - lista de adjacencias;
     */
    public TrechoList getAdjacencias() {
        return adjacencias;
    }

}
