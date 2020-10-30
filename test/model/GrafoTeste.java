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
        assertTrue(grafo.getCidades().isEmpty());

        grafo.addCidade("A");
        assertEquals(1, grafo.getCidades().size());

        grafo.addCidade("B");
        grafo.addCidade("C");
        grafo.addCidade("D");
        assertEquals(4, grafo.getCidades().size());

        grafo.addCidade("E");
        grafo.addCidade("F");
        grafo.addCidade("G");
        grafo.addCidade("H");
        assertEquals(8, grafo.getCidades().size());

        assertEquals("A", grafo.getCidades().get(0).getConteudo().getNome());
        assertEquals("C", grafo.getCidades().get(2).getConteudo().getNome());
        assertEquals("E", grafo.getCidades().get(4).getConteudo().getNome());
        assertEquals("G", grafo.getCidades().get(6).getConteudo().getNome());

        grafo.addTrecho("A", "B", 10,"anjo");
        grafo.addTrecho("A", "D", 5,"anjo");
        grafo.addTrecho("A", "C", 3,"anjo");
        
        assertEquals("B", grafo.getCidades().procurarNo("A").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals("D", grafo.getCidades().procurarNo("A").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        assertEquals("C", grafo.getCidades().procurarNo("A").getAdjacencias().get(2).getConteudo().getDestino().getNome());

        assertEquals("A", grafo.getCidades().procurarNo("B").getAdjacencias().get(0).getConteudo().getDestino().getNome());

        assertEquals("A", grafo.getCidades().procurarNo("D").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        
        assertEquals("A", grafo.getCidades().procurarNo("C").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        
        
        grafo.addTrecho("D", "B", 2,"souza");
        grafo.addTrecho("D", "C", 1,"anjo");
        
        assertEquals("B", grafo.getCidades().procurarNo("D").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        assertEquals("C", grafo.getCidades().procurarNo("D").getAdjacencias().get(2).getConteudo().getDestino().getNome());
       
        assertEquals("D", grafo.getCidades().procurarNo("B").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        
        assertEquals("D", grafo.getCidades().procurarNo("C").getAdjacencias().get(1).getConteudo().getDestino().getNome());
      
        
        grafo.addTrecho("C", "B", 5,"lilas");
        
        assertEquals("B", grafo.getCidades().procurarNo("C").getAdjacencias().get(2).getConteudo().getDestino().getNome());
       
        assertEquals("C", grafo.getCidades().procurarNo("B").getAdjacencias().get(2).getConteudo().getDestino().getNome());
        
        //Remove todos os vertices
        grafo.removerTodasCidades();
        assertTrue(grafo.getCidades().isEmpty());
    }

    @Test
    public void RemoverPontos() {
        assertTrue(grafo.getCidades().isEmpty());

        grafo.addCidade("A");
        grafo.addCidade("B");
        grafo.addCidade("C");
        grafo.addCidade("D");
        grafo.addCidade("E");
        grafo.addCidade("F");
        grafo.addCidade("G");
        grafo.addCidade("H");

        grafo.addTrecho("A", "H", 10,"azul");
        grafo.addTrecho("A", "C", 3,"laranja");
        grafo.addTrecho("B", "H", 4,"amarelho");
        grafo.addTrecho("B", "C", 2,"azul");
        grafo.addTrecho("B", "G", 3,"amarelho");
        grafo.addTrecho("B", "D", 7,"azul");
        grafo.addTrecho("C", "F", 6,"laranja");
        grafo.addTrecho("H", "D", 10,"azul");
        grafo.addTrecho("D", "E", 5,"amarelho");
        grafo.addTrecho("G", "F", 4,"laranja");
        grafo.addTrecho("F", "E", 3,"amarelho");

        assertEquals(8, grafo.getCidades().size());

        //Removendo o vertice "H"
        assertEquals("A", grafo.getCidades().procurarNo("H").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals("B", grafo.getCidades().procurarNo("H").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        assertEquals("D", grafo.getCidades().procurarNo("H").getAdjacencias().get(2).getConteudo().getDestino().getNome());

        grafo.removerCidade("H");

        assertEquals(7, grafo.getCidades().size());

        assertEquals("C", grafo.getCidades().procurarNo("A").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals(1, grafo.getCidades().procurarNo("A").getAdjacencias().size());

        assertEquals("C", grafo.getCidades().procurarNo("B").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals("G", grafo.getCidades().procurarNo("B").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        assertEquals("D", grafo.getCidades().procurarNo("B").getAdjacencias().get(2).getConteudo().getDestino().getNome());
        assertEquals(3, grafo.getCidades().procurarNo("B").getAdjacencias().size());

        assertEquals("B", grafo.getCidades().procurarNo("D").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals("E", grafo.getCidades().procurarNo("D").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        assertEquals(2, grafo.getCidades().procurarNo("D").getAdjacencias().size());

        //Removendo o vértice "F"
        assertEquals("C", grafo.getCidades().procurarNo("F").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals("G", grafo.getCidades().procurarNo("F").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        assertEquals("E", grafo.getCidades().procurarNo("F").getAdjacencias().get(2).getConteudo().getDestino().getNome());

        assertEquals(2, grafo.getCidades().procurarNo("E").getAdjacencias().size());
        assertEquals(2, grafo.getCidades().procurarNo("G").getAdjacencias().size());
        assertEquals(3, grafo.getCidades().procurarNo("C").getAdjacencias().size());

        grafo.removerCidade("F");

        assertEquals(6, grafo.getCidades().size());

        assertEquals("D", grafo.getCidades().procurarNo("E").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals(1, grafo.getCidades().procurarNo("E").getAdjacencias().size());

        assertEquals("B", grafo.getCidades().procurarNo("G").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals(1, grafo.getCidades().procurarNo("G").getAdjacencias().size());

        assertEquals("A", grafo.getCidades().procurarNo("C").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals("B", grafo.getCidades().procurarNo("C").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        assertEquals(2, grafo.getCidades().procurarNo("C").getAdjacencias().size());

        //Remove todos os vertices
        grafo.removerTodasCidades();
        assertTrue(grafo.getCidades().isEmpty());
    }

    @Test
    public void RemoverLigacoes() {
        assertTrue(grafo.getCidades().isEmpty());

        grafo.addCidade("A");
        grafo.addCidade("B");
        grafo.addCidade("C");
        grafo.addCidade("D");
        grafo.addCidade("E");
        grafo.addCidade("F");
        grafo.addCidade("G");
        grafo.addCidade("H");

        grafo.addTrecho("A", "H", 10,"azul");
        grafo.addTrecho("A", "C", 3,"laranja");
        grafo.addTrecho("B", "H", 4,"amarelho");
        grafo.addTrecho("B", "C", 2,"azul");
        grafo.addTrecho("B", "G", 3,"amarelho");
        grafo.addTrecho("B", "D", 7,"azul");
        grafo.addTrecho("C", "F", 6,"laranja");
        grafo.addTrecho("H", "D", 10,"azul");
        grafo.addTrecho("D", "E", 5,"amarelho");
        grafo.addTrecho("G", "F", 4,"laranja");
        grafo.addTrecho("F", "E", 3,"amarelho");
 

        assertEquals(8, grafo.getCidades().size());

        assertEquals("A", grafo.getCidades().get(0).getConteudo().getNome());
        assertEquals("B", grafo.getCidades().get(1).getConteudo().getNome());
        assertEquals("C", grafo.getCidades().get(2).getConteudo().getNome());
        assertEquals("D", grafo.getCidades().get(3).getConteudo().getNome());
        assertEquals("E", grafo.getCidades().get(4).getConteudo().getNome());
        assertEquals("F", grafo.getCidades().get(5).getConteudo().getNome());
        assertEquals("G", grafo.getCidades().get(6).getConteudo().getNome());
        assertEquals("H", grafo.getCidades().get(7).getConteudo().getNome());

        //Remorção da Trecho AH
        assertEquals(2, grafo.getCidades().procurarNo("A").getAdjacencias().size());
        assertEquals(3, grafo.getCidades().procurarNo("H").getAdjacencias().size());

        assertEquals("A", grafo.getCidades().procurarNo("H").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals("H", grafo.getCidades().procurarNo("A").getAdjacencias().get(0).getConteudo().getDestino().getNome());

        grafo.removerTrecho("A", "H");

        assertEquals(1, grafo.getCidades().procurarNo("A").getAdjacencias().size());
        assertEquals(2, grafo.getCidades().procurarNo("H").getAdjacencias().size());

        //Remorção da Trecho CB
        assertEquals(3, grafo.getCidades().procurarNo("C").getAdjacencias().size());
        assertEquals(4, grafo.getCidades().procurarNo("B").getAdjacencias().size());

        assertEquals("B", grafo.getCidades().procurarNo("C").getAdjacencias().get(1).getConteudo().getDestino().getNome());
        assertEquals("C", grafo.getCidades().procurarNo("B").getAdjacencias().get(1).getConteudo().getDestino().getNome());

        grafo.removerTrecho("C", "B");

        assertEquals(2, grafo.getCidades().procurarNo("C").getAdjacencias().size());
        assertEquals(3, grafo.getCidades().procurarNo("B").getAdjacencias().size());

        //Remorção da Trecho ED
        assertEquals(2, grafo.getCidades().procurarNo("E").getAdjacencias().size());
        assertEquals(3, grafo.getCidades().procurarNo("D").getAdjacencias().size());

        assertEquals("D", grafo.getCidades().procurarNo("E").getAdjacencias().get(0).getConteudo().getDestino().getNome());
        assertEquals("E", grafo.getCidades().procurarNo("D").getAdjacencias().get(2).getConteudo().getDestino().getNome());

        grafo.removerTrecho("E", "D");

        assertEquals(1, grafo.getCidades().procurarNo("E").getAdjacencias().size());
        assertEquals(2, grafo.getCidades().procurarNo("D").getAdjacencias().size());

        //Remove todos os vertices
        grafo.removerTodasCidades();
        assertTrue(grafo.getCidades().isEmpty());

    }
    
    @Test
    public void CalcularCaminho(){
        assertTrue(grafo.getCidades().isEmpty());
        grafo.addCidade("A");
        grafo.addCidade("B");
        grafo.addCidade("C");
        grafo.addCidade("D");
        grafo.addCidade("E");
        grafo.addCidade("N");
        grafo.addCidade("I");

        grafo.addTrecho("A", "B", 6,"azul");
        grafo.addTrecho("A", "D", 4,"amarela");
        grafo.addTrecho("B", "C", 10,"laranja");
        grafo.addTrecho("B", "D", 7,"azul");
        grafo.addTrecho("B", "E", 7,"amarela");
        grafo.addTrecho("C", "D", 8,"laranja");
        grafo.addTrecho("C", "N", 6,"azul");
        grafo.addTrecho("C", "E", 5,"amarela");
        grafo.addTrecho("C", "I", 3,"laranja");
        grafo.addTrecho("N", "E", 7,"azul");
        grafo.addTrecho("D", "E", 12,"amarela");

        assertEquals(7, grafo.getCidades().size());

        assertEquals("I->C->D->A", grafo.calcularRota("I", "A"));
        assertEquals("E->C->I", grafo.calcularRota("E", "I"));


        grafo.removerTrecho("B", "E");
        grafo.removerTrecho("C", "E");
        grafo.removerTrecho("N", "E");
        grafo.removerTrecho("D", "E");
        assertEquals("Não existem caminhos para E", grafo.calcularRota("E", "C"));

        //Remove todos os vertices
        grafo.removerTodasCidades();
        assertTrue(grafo.getCidades().isEmpty());
    }
    
}
