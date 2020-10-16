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
package model;

/**
 * Esta classe armazena os dados de uma aresta, o segundo vertice que está
 * ligada e o peso que possui.
 *
 * Exemplo de uso:
 *
 * Aresta aresta= new Aresta(vertice,15);
 *
 * @author Estéfane Carmo de Souza
 */
public class Aresta {

    private Vertice destino;
    private int peso;

    public Aresta(Vertice destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }

    /**
     * Método que obtém um dos vértices
     *
     * @return Vertice
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
}
