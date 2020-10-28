/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: Estéfane Carmo de Souza
 * Data: 12-09-2019
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


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Classe teste para a análise na estrutura de dados grafos.
 *
 * @author Estéfane Carmo de Souza
 */
public class GrafoTeste {

    private Grafo grafo;

    @Before
    public void setUp() throws Exception {
        grafo = Grafo.getInstance();
    }

    @Test
    public void CadastrarPontosELigacoes() {
        assertTrue(grafo.getVertices().isEmpty());

        grafo.addVertice("A");
        assertEquals(1, grafo.getVertices().size());

        grafo.addVertice("B");
        grafo.addVertice("C");
        grafo.addVertice("D");
        assertEquals(4, grafo.getVertices().size());

        grafo.addVertice("E");
        grafo.addVertice("F");
        grafo.addVertice("G");
        grafo.addVertice("H");
        assertEquals(8, grafo.getVertices().size());

        assertEquals("A", grafo.getVertices().get(0).getConteudo().getNome());
        assertEquals("C", grafo.getVertices().get(2).getConteudo().getNome());
        assertEquals("E", grafo.getVertices().get(4).getConteudo().getNome());
        assertEquals("G", grafo.getVertices().get(6).getConteudo().getNome());

        grafo.addAresta("A", "B", 10,"anjo");
        grafo.addAresta("A", "D", 5,"anjo");
        grafo.addAresta("A", "C", 3,"anjo");
        
        assertEquals("B", grafo.getVertices().procurarNo("A").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals("D", grafo.getVertices().procurarNo("A").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        assertEquals("C", grafo.getVertices().procurarNo("A").getAdjacencias().get(2).getConteudo().getDestino().getNome());

        assertEquals("A", grafo.getVertices().procurarNo("B").getAdjacencias().get(0).getConteudo().getDestino().getNome());

        assertEquals("A", grafo.getVertices().procurarNo("D").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        
        assertEquals("A", grafo.getVertices().procurarNo("C").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        
        
        grafo.addAresta("D", "B", 2,"souza");
        grafo.addAresta("D", "C", 1,"anjo");
        
        assertEquals("B", grafo.getVertices().procurarNo("D").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        assertEquals("C", grafo.getVertices().procurarNo("D").getAdjacencias().get(2).getConteudo().getDestino().getNome());
       
        assertEquals("D", grafo.getVertices().procurarNo("B").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        
        assertEquals("D", grafo.getVertices().procurarNo("C").getAdjacencias().get(1).getConteudo().getDestino().getNome());
      
        
        grafo.addAresta("C", "B", 5,"lilas");
        
        assertEquals("B", grafo.getVertices().procurarNo("C").getAdjacencias().get(2).getConteudo().getDestino().getNome());
       
        assertEquals("C", grafo.getVertices().procurarNo("B").getAdjacencias().get(2).getConteudo().getDestino().getNome());
        
        //Remove todos os vertices
        grafo.removerTodosVertice();
        assertTrue(grafo.getVertices().isEmpty());
    }

    @Test
    public void RemoverPontos() {
        assertTrue(grafo.getVertices().isEmpty());

        grafo.addVertice("A");
        grafo.addVertice("B");
        grafo.addVertice("C");
        grafo.addVertice("D");
        grafo.addVertice("E");
        grafo.addVertice("F");
        grafo.addVertice("G");
        grafo.addVertice("H");

        grafo.addAresta("A", "H", 10,"azul");
        grafo.addAresta("A", "C", 3,"laranja");
        grafo.addAresta("B", "H", 4,"amarelho");
        grafo.addAresta("B", "C", 2,"azul");
        grafo.addAresta("B", "G", 3,"amarelho");
        grafo.addAresta("B", "D", 7,"azul");
        grafo.addAresta("C", "F", 6,"laranja");
        grafo.addAresta("H", "D", 10,"azul");
        grafo.addAresta("D", "E", 5,"amarelho");
        grafo.addAresta("G", "F", 4,"laranja");
        grafo.addAresta("F", "E", 3,"amarelho");

        assertEquals(8, grafo.getVertices().size());

        //Removendo o vertice "H"
        assertEquals("A", grafo.getVertices().procurarNo("H").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals("B", grafo.getVertices().procurarNo("H").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        assertEquals("D", grafo.getVertices().procurarNo("H").getAdjacencias().get(2).getConteudo().getDestino().getNome());

        grafo.removerVertice("H");

        assertEquals(7, grafo.getVertices().size());

        assertEquals("C", grafo.getVertices().procurarNo("A").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals(1, grafo.getVertices().procurarNo("A").getAdjacencias().size());

        assertEquals("C", grafo.getVertices().procurarNo("B").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals("G", grafo.getVertices().procurarNo("B").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        assertEquals("D", grafo.getVertices().procurarNo("B").getAdjacencias().get(2).getConteudo().getDestino().getNome());
        assertEquals(3, grafo.getVertices().procurarNo("B").getAdjacencias().size());

        assertEquals("B", grafo.getVertices().procurarNo("D").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals("E", grafo.getVertices().procurarNo("D").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        assertEquals(2, grafo.getVertices().procurarNo("D").getAdjacencias().size());

        //Removendo o vértice "F"
        assertEquals("C", grafo.getVertices().procurarNo("F").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals("G", grafo.getVertices().procurarNo("F").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        assertEquals("E", grafo.getVertices().procurarNo("F").getAdjacencias().get(2).getConteudo().getDestino().getNome());

        assertEquals(2, grafo.getVertices().procurarNo("E").getAdjacencias().size());
        assertEquals(2, grafo.getVertices().procurarNo("G").getAdjacencias().size());
        assertEquals(3, grafo.getVertices().procurarNo("C").getAdjacencias().size());

        grafo.removerVertice("F");

        assertEquals(6, grafo.getVertices().size());

        assertEquals("D", grafo.getVertices().procurarNo("E").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals(1, grafo.getVertices().procurarNo("E").getAdjacencias().size());

        assertEquals("B", grafo.getVertices().procurarNo("G").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals(1, grafo.getVertices().procurarNo("G").getAdjacencias().size());

        assertEquals("A", grafo.getVertices().procurarNo("C").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals("B", grafo.getVertices().procurarNo("C").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        assertEquals(2, grafo.getVertices().procurarNo("C").getAdjacencias().size());

        //Remove todos os vertices
        grafo.removerTodosVertice();
        assertTrue(grafo.getVertices().isEmpty());
    }

    @Test
    public void RemoverLigacoes() {
        assertTrue(grafo.getVertices().isEmpty());

        grafo.addVertice("A");
        grafo.addVertice("B");
        grafo.addVertice("C");
        grafo.addVertice("D");
        grafo.addVertice("E");
        grafo.addVertice("F");
        grafo.addVertice("G");
        grafo.addVertice("H");

        grafo.addAresta("A", "H", 10,"azul");
        grafo.addAresta("A", "C", 3,"laranja");
        grafo.addAresta("B", "H", 4,"amarelho");
        grafo.addAresta("B", "C", 2,"azul");
        grafo.addAresta("B", "G", 3,"amarelho");
        grafo.addAresta("B", "D", 7,"azul");
        grafo.addAresta("C", "F", 6,"laranja");
        grafo.addAresta("H", "D", 10,"azul");
        grafo.addAresta("D", "E", 5,"amarelho");
        grafo.addAresta("G", "F", 4,"laranja");
        grafo.addAresta("F", "E", 3,"amarelho");
 

        assertEquals(8, grafo.getVertices().size());

        assertEquals("A", grafo.getVertices().get(0).getConteudo().getNome());
        assertEquals("B", grafo.getVertices().get(1).getConteudo().getNome());
        assertEquals("C", grafo.getVertices().get(2).getConteudo().getNome());
        assertEquals("D", grafo.getVertices().get(3).getConteudo().getNome());
        assertEquals("E", grafo.getVertices().get(4).getConteudo().getNome());
        assertEquals("F", grafo.getVertices().get(5).getConteudo().getNome());
        assertEquals("G", grafo.getVertices().get(6).getConteudo().getNome());
        assertEquals("H", grafo.getVertices().get(7).getConteudo().getNome());

        //Remorção da Trecho AH
        assertEquals(2, grafo.getVertices().procurarNo("A").getAdjacencias().size());
        assertEquals(3, grafo.getVertices().procurarNo("H").getAdjacencias().size());

        assertEquals("A", grafo.getVertices().procurarNo("H").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals("H", grafo.getVertices().procurarNo("A").getAdjacencias().get(0).getConteudo().getDestino().getNome());

        grafo.removerAresta("A", "H");

        assertEquals(1, grafo.getVertices().procurarNo("A").getAdjacencias().size());
        assertEquals(2, grafo.getVertices().procurarNo("H").getAdjacencias().size());

        //Remorção da Trecho CB
        assertEquals(3, grafo.getVertices().procurarNo("C").getAdjacencias().size());
        assertEquals(4, grafo.getVertices().procurarNo("B").getAdjacencias().size());

        assertEquals("B", grafo.getVertices().procurarNo("C").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        assertEquals("C", grafo.getVertices().procurarNo("B").getAdjacencias().get(1).getConteudo().getDestino().getNome());

        grafo.removerAresta("C", "B");

        assertEquals(2, grafo.getVertices().procurarNo("C").getAdjacencias().size());
        assertEquals(3, grafo.getVertices().procurarNo("B").getAdjacencias().size());

        //Remorção da Trecho ED
        assertEquals(2, grafo.getVertices().procurarNo("E").getAdjacencias().size());
        assertEquals(3, grafo.getVertices().procurarNo("D").getAdjacencias().size());

        assertEquals("D", grafo.getVertices().procurarNo("E").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals("E", grafo.getVertices().procurarNo("D").getAdjacencias().get(2).getConteudo().getDestino().getNome());

        grafo.removerAresta("E", "D");

        assertEquals(1, grafo.getVertices().procurarNo("E").getAdjacencias().size());
        assertEquals(2, grafo.getVertices().procurarNo("D").getAdjacencias().size());

        //Remove todos os vertices
        grafo.removerTodosVertice();
        assertTrue(grafo.getVertices().isEmpty());

    }
    
    @Test
    public void CalcularCaminho(){
        assertTrue(grafo.getVertices().isEmpty());
        grafo.addVertice("A");
        grafo.addVertice("B");
        grafo.addVertice("C");
        grafo.addVertice("D");
        grafo.addVertice("E");
        grafo.addVertice("N");
        grafo.addVertice("I");

        grafo.addAresta("A", "B", 6,"azul");
        grafo.addAresta("A", "D", 4,"amarela");
        grafo.addAresta("B", "C", 10,"laranja");
        grafo.addAresta("B", "D", 7,"azul");
        grafo.addAresta("B", "E", 7,"amarela");
        grafo.addAresta("C", "D", 8,"laranja");
        grafo.addAresta("C", "N", 6,"azul");
        grafo.addAresta("C", "E", 5,"amarela");
        grafo.addAresta("C", "I", 3,"laranja");
        grafo.addAresta("N", "E", 7,"azul");
        grafo.addAresta("D", "E", 12,"amarela");

        assertEquals(7, grafo.getVertices().size());

        assertEquals("I->C->D->A", grafo.calcularRota("I", "A"));
        assertEquals("E->C->I", grafo.calcularRota("E", "I"));


        grafo.removerAresta("B", "E");
        grafo.removerAresta("C", "E");
        grafo.removerAresta("N", "E");
        grafo.removerAresta("D", "E");
        assertEquals("Não existem caminhos para E", grafo.calcularRota("E", "C"));

        //Remove todos os vertices
        grafo.removerTodosVertice();
        assertTrue(grafo.getVertices().isEmpty());
    }
    
}
