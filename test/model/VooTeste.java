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

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe teste para a análise na criação de objetos do tipo Trecho e alterações
 em seus atributos
 *
 * @author Estéfane Carmo de Souza
 */
public class VooTeste {

    private Cidade v1, v2, v3;
    private Trecho aresta;

    @Before
    public void setUp() throws Exception {
        v1 = new Cidade("A");
        v2 = new Cidade("B");
        v3 = new Cidade("C");
    }

    @Test
    public void testeBasico() {
        aresta = new Trecho(v1, 10,"aresta1");

        assertEquals(v1, aresta.getDestino());
        assertEquals(10, aresta.getTempoVoo());
        assertEquals("aresta1", aresta.getCompanhia());
        
        aresta.setDestino(v2);
        assertEquals(v2, aresta.getDestino());
        assertEquals(10, aresta.getTempoVoo());

        aresta.setDestino(v3);
        assertEquals(v3, aresta.getDestino());

        aresta.setTempoVoo(19);
        assertEquals(19, aresta.getTempoVoo());

    }

}
