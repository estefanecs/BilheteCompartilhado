/**
 * Componente Curricular:  Sistemas Operacionais
 * Autor: Estéfane Carmo de Souza e Messias Jr. Lira da Silva
 * Data: 27-10-2020
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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Estéfane Carmo de Souza
 * @author Messias Jr. Lira da Silva
 */
public class ComprasPassagens extends Thread {
    public static List<Cidade> trechos = new ArrayList<>();
    private Grafo grafo = new Grafo();
    
    @Override
    public void run() { 
        Scanner escanear= new Scanner(System.in);
        System.out.println("Cidades disponíveis para voos");
        grafo.listarCidades();
        
        System.out.println("Informe o número correpondente a cidade origem da sua viagem: ");
        int cidadePartida =escanear.nextInt();
        System.out.println("Informe o número correpondente a cidade destino da sua viagem: ");
        int cidadeDestino= escanear.nextInt();
        
        //Nome dos vertices selecionados
        String verticePartida= grafo.getVertices().get(cidadePartida).getConteudo().getNome();
        String verticeDestino= grafo.getVertices().get(cidadeDestino).getConteudo().getNome();
       
        ///FUNÇÃO QUE LISTA POSSIVEIS FORMAS DE CHEGAR ATE O DESTINO
        /*
        Deve vim sem numero
        String[] rota= grafo.calcularRota(verticePartida,verticeDestino);
        */
        
        System.out.println("Indique qual o número da opção da rota que você escolheu");
        int numCaminho =escanear.nextInt();
        
        //Separa a string rota em partes e adiciona o vertice na lista de trechos
        String[] adjacencia = rota[numCaminho].split("->");//separa em partes
        for (int i = 0; i < adjacencia.length; i++) {//Até o fim do vetor
           String nomeVertice= adjacencia[i]; //indica o nome do vertice
           int posicao= grafo.getVertices().getPosicao(nomeVertice); //pega a posicao do vertice na lista
           Cidade vertice= grafo.getVertices().get(posicao).getConteudo(); //pega a instancia do vertice
           trechos.add(vertice); //adiciona na lista de trechos
        }    
        System.out.println("A compra da sua passagem será realizada neste exato momento");
       
        //FUNCAO COMPRA DA PASSAGEM
        
    }
    
}
