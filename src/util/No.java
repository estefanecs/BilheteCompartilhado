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

import model.Trecho;

/**
 * Classe para objetos do tipo No, que contém seus atributos e métodos. Possui
 * uma referência para o conteúdo do nó e a referência para o nó seguinte.
 *
 * Exemplo de uso:
 *
 * No no= new No("conteudo");
 *
 * @author Estéfane Carmo de Souza
 * @author Messias Jr. Lira da Silva
 */
public class No {

    private Trecho conteudo;
    private No next;

    public No() {
    }

    public No(Trecho conteudo) {
        this.conteudo = conteudo;
    }

    /**
     * Método que obtém o próximo Nó
     *
     * @return No - O próximo nó
     */
    public No getNext() {
        return next;
    }

    /**
     * Método que obtém o conteúdo do Nó
     *
     * @return Trecho - O conteúdo
     */
    public Trecho getConteudo() {
        return conteudo;
    }

    /**
     * Método que modifica a referência do próximo, para um outro nó.
     *
     * @param newNext - O novo próximo nó
     */
    public void setNext(No newNext) {
        next = newNext;
    }

    /**
     * Método que modifica o conteúdo do nó
     *
     * @param novoConteudo - O novo conteúdo do nó
     */
    public void setConteudo(Trecho novoConteudo) {
        conteudo = novoConteudo;
    }
}
