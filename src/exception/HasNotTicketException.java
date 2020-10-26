/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: Estéfane Carmo de Souza e Messias Jr. Lira da Silva
 * Data: 26-10-2019
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
package exception;
/**
 * Exceção lançada quando o usuário tentar comprar uma passagem que já acabou
 * 
 * @author Estéfane Carmo de Souza
 * @author Messias Jr. Lira da Silva
 */

public class HasNotTicketException extends Exception {

    public HasNotTicketException() {
        super();
    }

}
