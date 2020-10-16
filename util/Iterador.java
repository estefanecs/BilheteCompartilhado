/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: Estéfane Carmo de Souza
 * Data: 13-07-2019
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

import java.util.Iterator;


/**
 * Classe para objetos do tipo Iterador que implementa a interface Iterator,
 * contendo seus métodos e referência para um nó. Exemplo de uso:
 *
 * Iterator iterador = list.iterator();
 *
 * @author Estéfane Carmo de Souza
 */
public class Iterador implements Iterator {

    private Node atual;

    public Iterador(Node primeiro) {
        atual = primeiro;
    }

    /**
     * Método que analisa se existe um próximo nó na lista.
     *
     * @return true - Se o nó atual não for nulo
     */
    @Override
    public boolean hasNext() {
        return (atual != null);
    }

    /**
     * Método que retorna o nó atual, e altera a referência do atual para o
     * proximo nó da lista, desde que este não seja nulo.
     *
     * @return Object - O conteúdo do antigo nó atual
     */
    @Override
    public Object next() {
        if (hasNext()) { 
            Node next = atual;
            atual = atual.getNext();
            return next.getConteudo();
        } else {
            return null;
        }
    }
}
