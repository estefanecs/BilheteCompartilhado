/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: Estéfane Carmo de Souza
 * Data: 04-09-2019
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

import model.Aresta;
import model.Vertice;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import util.ArestaList;

/**
 * Classe teste para a estrutura de dados lista, que armazena objetos do tipo
 * Aresta
 *
 * @author Estéfane Carmo de Souza
 */
public class ArestaListTeste {

    private ArestaList list;
    private Vertice v1, v2, v3;
    private Aresta aresta1, aresta2, aresta3;

    @Before
    public void setUp() {
        list = new ArestaList();
        v1 = new Vertice("A");
        v2 = new Vertice("B");
        v3 = new Vertice("C");
        aresta1 = new Aresta(v1, 10,"aresta1");
        aresta2 = new Aresta(v2, 5,"aresta2");
        aresta3 = new Aresta(v3, 8,"aresta3");
    }

    /**
     * Teste de unidade que verifica se a inserção de objetos na lista está
     * ocorrendo de forma correta.
     */
    @Test
    public void testInsert() {
        assertEquals(0, list.size());

        list.add(aresta1);
        assertEquals(1, list.size());

        list.add(aresta2);
        assertEquals(2, list.size());

        list.add(aresta3);
        assertEquals(3, list.size());

        assertEquals(aresta1, list.get(0).getConteudo());
        assertEquals(aresta2, list.get(1).getConteudo());
        assertEquals(aresta3, list.get(2).getConteudo());

        assertEquals(3, list.size());
    }

    /**
     * Teste de unidade que verifica se os dados presentes na lista estão sendo
     * recuperados corretamente.
     */
    @Test
    public void testGet() {
        list.add(aresta1);
        list.add(aresta2);
        list.add(aresta3);
        assertEquals(aresta1, list.get(0).getConteudo());
        assertEquals(aresta2, list.get(1).getConteudo());
        assertEquals(aresta3, list.get(2).getConteudo());
    }

    /**
     * Teste de unidade que verifica se a remoção de objetos na lista está sendo
     * feita corretamente.
     */
    @Test
    public void testDelete() {
        assertNull(list.remove("vB"));
        assertNull(list.remove("N"));

        list.add(aresta1);
        list.add(aresta2);
        list.add(aresta3);

        assertEquals(v1, list.remove("A"));
        assertEquals(2, list.size());

        assertEquals(v2, list.remove("B"));
        assertEquals(1, list.size());

        assertNull(list.remove("D"));

        assertEquals(v3, list.remove("C"));
        assertEquals(0, list.size());

        assertTrue(list.isEmpty());
    }

    /**
     * Teste de unidade que verifica se os métodos de inserção e remoção de
     * objetos na lista estão funcionando corretamente.
     */
    @Test
    public void testInsertDelete() {
        list.add(aresta1);
        list.add(aresta2);
        list.add(aresta3);
        assertFalse(list.isEmpty());

        assertEquals(v1, list.remove("A"));
        assertFalse(list.isEmpty());

        assertEquals(v2, list.remove("B"));
        assertFalse(list.isEmpty());

        assertEquals(v3, list.remove("C"));
        assertTrue(list.isEmpty());
    }

    /**
     * Teste de unidade que verifica se a lista está vazia ou não.
     */
    @Test
    public void testisEmpty() {
        assertTrue(list.isEmpty());
        list.add(aresta1);
        assertFalse(list.isEmpty());
        list.remove("A");
        assertTrue(list.isEmpty());
    }

    /**
     * Teste de unidade que verifica o tamanho da lista antes e após inserções e
     * remoções.
     */
    @Test
    public void testSize() {
        assertEquals(0, list.size());

        list.add(aresta1);
        assertEquals(1, list.size());

        list.add(aresta2);
        list.add(aresta3);
        assertEquals(3, list.size());

        list.remove("B");
        assertEquals(2, list.size());

        list.remove("a");
        list.remove("C");
        assertEquals(0, list.size());
    }

    public void testeGetPosicao() {
        list.add(aresta1);
        list.add(aresta2);
        list.add(aresta3);
        assertEquals(3, list.size());

        assertEquals(0, list.getPosicao("A"));
        assertEquals(2, list.getPosicao("C"));
        assertEquals(1, list.getPosicao("B"));
    }
}
