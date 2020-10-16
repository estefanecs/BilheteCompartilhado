/**
 * Componente Curricular: Módulo Integrado de Programação 
 * Autor: Estéfane Carmo de Souza
 * de Souza Data: 24-08-2019
 *
 * Declaro que este código foi elaborado por mim de forma individual e não
 * contém nenhum trecho de código de outro colega ou de outro autor, tais como
 * provindos de livros e apostilas, e páginas ou documentos eletrônicos da
 * Internet. Qualquer trecho de código de outra autoria que uma citação para o
 * não a minha está destacado com autor e a fonte do código, e estou ciente que
 * estes trechos não serão considerados para fins de avaliação. Alguns trechos
 * do código podem coincidir com de outros colegas pois estes foram discutidos
 * em sessões tutorias.
 */
package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe teste para a análise na criação de objetos do tipo Cidade e
 alterações em seus atributos
 *
 * @author Estéfane Carmo de Souza
 */
public class VerticeTeste {

    private Cidade vertice, vertice2;

    @Before
    public void setUp() throws Exception {
        vertice = new Cidade("D");
        vertice2 = new Cidade("E");
    }

    @Test
    public void TesteBasico() {
        assertEquals("D", vertice.getNome());
        assertFalse(vertice.isVisitado());
        assertTrue(vertice.getAdjacencias().isEmpty());

        vertice.setNome("X");
        assertEquals("X", vertice.getNome());;

        vertice.setVisitado(false);
        assertFalse(vertice.isVisitado());

        Trecho aresta = new Trecho(vertice2, 5,"novaAresta");
        vertice.getAdjacencias().add(aresta);
        assertFalse(vertice.getAdjacencias().isEmpty());

    }
}
