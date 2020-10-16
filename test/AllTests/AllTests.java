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
package AllTests;

import util.ArestaListTeste;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import model.ArestaTeste;
import model.VerticeTeste;
import model.GrafoTeste;
import util.VerticeListTest;

/**
 * Classe que unifica todas as classes de teste
 *
 * @author Estéfane Carmo de Souz
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    ArestaListTeste.class,
    ArestaTeste.class,
    VerticeTeste.class,
    GrafoTeste.class,
    VerticeListTest.class,})

public class AllTests {
}
