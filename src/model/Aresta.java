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

/**
 * Esta classe armazena os dados de uma aresta, o segundo vertice que está
 * ligada, o peso que possui e nome.
 *
 * Exemplo de uso:
 *
 * Aresta aresta= new Aresta(vertice,15);
 *
 * @author Estéfane Carmo de Souza e Messias Jr. Lira da Silva
 */
public class Aresta {

    private Vertice destino;
    private int peso;
    private String nome;

    public Aresta(Vertice destino, int peso,String nomeCompanhia) {
        this.destino = destino;
        this.peso = peso;
        this.nome= nomeCompanhia;
    }

    /**
     * Método que obtém um dos vértices
     *
     * @return Vertice - vertice destino
     */
    public Vertice getDestino() {
        return destino;
    }

    /**
     * Método que obtém o peso da aresta
     *
     * @return int - peso
     */
    public int getPeso() {
        return peso;
    }
    
    /**
     * Método que obtém o nome da aresta
     *
     * @return String - nomCompanhia
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Método que altera o vértice de destino
     *
     * @param destino- o novo vértice
     */
    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    /**
     * Método que altera o peso da aresta
     *
     * @param peso- o novo peso da aresta
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    /**
     * Método que altera o nome da aresta
     *
     * @param nome- o novo nome da aresta
     */
    public void setNome(String nome) {
        this.nome = nome;
    } 
}
