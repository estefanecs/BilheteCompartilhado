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

import model.Cidade;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import util.CidadeList;

/**
 * Classe teste para a estrutura de dados lista, que armazena objetos do tipo
 Cidade
 *
 * @author Estéfane Carmo de Souza
 */
public class VerticeListTest {

    private CidadeList list;
    private Cidade vertice1, vertice2, vertice3;

    @Before
    public void setUp() {
        list = new CidadeList();
        vertice1 = new Cidade("A");
        vertice2 = new Cidade("B");
        vertice3 = new Cidade("C");

    }

    /**
     * Teste de unidade que verifica se a inserção de objetos na lista está
     * ocorrendo de forma correta.
     */
    @Test
    public void testInsert() {
        assertEquals(0, list.size());

        list.add(vertice1);
        assertEquals(1, list.size());

        list.add(vertice2);
        assertEquals(2, list.size());

        list.add(vertice3);
        assertEquals(3, list.size());

        assertEquals(vertice1, list.get(0).getConteudo());
        assertEquals(vertice2, list.get(1).getConteudo());
        assertEquals(vertice3, list.get(2).getConteudo());

        assertEquals(3, list.size());
    }

    /**
     * Teste de unidade que verifica se os dados presentes na lista estão sendo
     * recuperados corretamente.
     */
    @Test
    public void testGet() {
        list.add(vertice1);
        list.add(vertice2);
        list.add(vertice3);
        assertEquals(vertice1, list.get(0).getConteudo());
        assertEquals(vertice2, list.get(1).getConteudo());
        assertEquals(vertice3, list.get(2).getConteudo());
    }

    /**
     * Teste de unidade que verifica se a remoção de objetos na lista está sendo
     * feita corretamente.
     */
    @Test
    public void testDelete() {
        assertNull(list.remove("vB"));
        assertNull(list.remove("N"));

        list.add(vertice1);
        list.add(vertice2);
        list.add(vertice3);

        assertEquals(vertice1, list.remove("A"));
        assertEquals(2, list.size());

        assertEquals(vertice2, list.remove("B"));
        assertEquals(1, list.size());

        assertNull(list.remove("D"));

        assertEquals(vertice3, list.remove("C"));
        assertEquals(0, list.size());

        assertTrue(list.isEmpty());
    }

    /**
     * Teste de unidade que verifica se os métodos de inserção e remoção de
     * objetos na lista estão funcionando corretamente.
     */
    @Test
    public void testInsertDelete() {
        list.add(vertice1);
        list.add(vertice2);
        list.add(vertice3);
        assertFalse(list.isEmpty());

        assertEquals(vertice1, list.remove("A"));
        assertFalse(list.isEmpty());

        assertEquals(vertice2, list.remove("B"));
        assertFalse(list.isEmpty());

        assertEquals(vertice3, list.remove("C"));
        assertTrue(list.isEmpty());
    }

    /**
     * Teste de unidade que verifica se a lista está vazia ou não.
     */
    @Test
    public void testisEmpty() {
        assertTrue(list.isEmpty());
        list.add(vertice1);
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

        list.add(vertice1);
        assertEquals(1, list.size());

        list.add(vertice2);
        list.add(vertice3);
        assertEquals(3, list.size());

        list.remove("B");
        assertEquals(2, list.size());

        list.remove("a");
        list.remove("C");
        assertEquals(0, list.size());
    }

    public void testeGetPosicao() {
        list.add(vertice1);
        list.add(vertice2);
        list.add(vertice3);
        assertEquals(3, list.size());

        assertEquals(0, list.getPosicao("A"));
        assertEquals(2, list.getPosicao("C"));
        assertEquals(1, list.getPosicao("B"));
    }

    @Test
    public void testProcurarNo() {
        list.add(vertice1);
        list.add(vertice2);
        list.add(vertice3);
        assertFalse(list.isEmpty());

        assertEquals(vertice1, list.procurarNo("A"));
        assertEquals(vertice2, list.procurarNo("B"));
        assertEquals(vertice3, list.procurarNo("C"));

    }
}
