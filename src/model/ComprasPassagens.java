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

import exception.HasNotTicketException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Estéfane Carmo de Souza
 * @author Messias Jr. Lira da Silva
 */
public class ComprasPassagens extends Thread {
    public static List<String> trechos = new ArrayList<>();
    private Grafo grafo= Grafo.getInstance();
    private Semaphore semaforo;
    private int numPassageiro;
    private int cidadePartida;
    private int cidadeDestino;
    
    public  ComprasPassagens(Semaphore semaforo, int numero, int cidadePartida, int cidadeDestino){
        this.semaforo= semaforo;
        numPassageiro= numero;
        this.cidadePartida=cidadePartida;
        this.cidadeDestino=cidadeDestino;
    }
    
      public void realizarCompra(List<String> locais) throws InterruptedException{
        for(int i=0; i<locais.size()-1;i++){
            int posicaoC1= grafo.getVertices().getPosicao(locais.get(i));//pega a posicao do vertice na lista
            int posicaoC2=grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getPosicao(locais.get(i+1));
            int passagensDisponiveis= grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).getQuantPassagens();
            if(passagensDisponiveis>0){
                semaforo.acquire();
                System.out.println("\nPassageiro "+numPassageiro+ ":passagens antes da compra "+passagensDisponiveis);
                //diminui a quantidade de passagens
                grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).setQuantPassagens();
                passagensDisponiveis= grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).getQuantPassagens();
                System.out.println("\nPassageiro "+numPassageiro+ "a compra do trecho "+locais.get(i)+" a "+locais.get(i+1)+" foi reaizada");
                //Poderia salvar essa pasagem em algum lugar né?
                System.out.println("\nRestam " +passagensDisponiveis+" passagens disponiveis de "+locais.get(i)+" para "+locais.get(i+1));
                semaforo.release();
            }
            else{
                System.out.println("Passageiro "+numPassageiro+ " :Não existe passagens disponiveis de "+locais.get(i)+" para "+locais.get(i+1));
               //Adiciona em uma lista de espera, mas quem tem a lista de espera?
           }
        }   
    }
    public void liberarPassagens(){
        for(int i=0; i<trechos.size()-1;i++){
            int posicaoC1= grafo.getVertices().getPosicao(trechos.get(i));//pega a posicao do vertice na lista
            int posicaoC2=grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getPosicao(trechos.get(i+1));
            grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).devolverPassagens();
        }
    }
    
    @Override
    public void run() { 
            /*Scanner escanear= new Scanner(System.in);
            System.out.println("Cidades disponíveis para voos");
            grafo.listarCidades();*/
            
            /*System.out.println("Passageiro "+numPassageiro+" , informe o número correpondente a cidade origem da sua viagem: ");
            int cidadePartida =escanear.nextInt();
            System.out.println("Passageiro "+numPassageiro+" , informe o número correpondente a cidade destino da sua viagem: ");
            int cidadeDestino= escanear.nextInt();*/
            
            //Nome dos vertices selecionados
        String verticePartida= grafo.getVertices().get(cidadePartida).getConteudo().getNome();
        String verticeDestino= grafo.getVertices().get(cidadeDestino).getConteudo().getNome();
        
        //apagar dps
        System.out.println("\nPassageiro "+numPassageiro+" , a cidade origem da sua viagem é: "+verticePartida);
        System.out.println("\nPassageiro "+numPassageiro+" , a cidade destino da sua viagem é: "+verticeDestino);
       
        grafo.calcularRota(verticePartida,verticeDestino);
        System.out.println("\nPassageiro "+numPassageiro);
        System.out.println(grafo.getRota());
        
        /*System.out.println("Passageiro "+numPassageiro+ " , indique qual o número da opção da rota que você escolheu");
        int numCaminho =escanear.nextInt();*/
        
        //Separa a string rota em partes e adiciona o vertice na lista de trechos
        
        //String[] adjacencia = grafo.getRotas().get(numCaminho).split("->");//separa em partes
        
        String[] adjacencia = grafo.getRota().split("->");
        for (int i = 0; i < adjacencia.length; i++) {//Até o fim do vetor
          trechos.add(adjacencia[i]); //adiciona na lista de trechos
        }    
        System.out.println("\nPassageiro "+numPassageiro+ ", a compra da sua passagem será realizada neste exato momento");
       
        try {
            //FUNCAO COMPRA DA PASSAGEM
            this.realizarCompra(trechos);
        } catch (InterruptedException ex) {
            Logger.getLogger(ComprasPassagens.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ComprasPassagens.sleep(10);
            this.liberarPassagens();
        } catch (InterruptedException ex) {
            Logger.getLogger(ComprasPassagens.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
