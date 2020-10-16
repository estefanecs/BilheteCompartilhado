/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.ComprasPassagens;
import model.Grafo;
import model.Cidade;
import static model.ComprasPassagens.trechos;

/**
 *
 * @author mjlsj
 */
public class BilheteCompartilhado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        Grafo grafo = new Grafo();
        grafo.importarArquivo("Cidades.txt");
        List<Cidade> locais = new ArrayList<>();
    //---------------------------------------------------------------------------------------------------
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
       
        String rota1= grafo.calcularRota(verticePartida,verticeDestino);
         
        String[] rota= new String[2];
        rota[0]=rota1;
        rota[1]="ata tem nada salvo nãoooo";
        
        System.out.println(rota[0]);
        System.out.println("Indique qual o número da opção da rota que você escolheu");
        int numCaminho =escanear.nextInt();
        
        //Separa a string rota em partes
        String[] adjacencia = rota[numCaminho].split("->");//separa em partes
        for (int i = 0; i < adjacencia.length; i++) {//Até o fim do vetor
           String nomeVertice= adjacencia[i]; //indica o nome do vertice
           int posicao= grafo.getVertices().getPosicao(nomeVertice); //pega a posicao do vertice na lista
           Cidade vertice= grafo.getVertices().get(posicao).getConteudo(); //pega a instancia do vertice
            locais.add(vertice);
        }    
        System.out.println("A compra da sua passagem será realizada neste exato momento");
        
        for(int i=0; i<locais.size();i++){
            System.out.println(i+". "+locais.get(i).getNome());
        }
    
    }
}
