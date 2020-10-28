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
    private List<String> trechos = new ArrayList<>();
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
    
    public boolean verificarLocais(){
        boolean isDisponivel=true;
        int posicaoC1, posicaoC2, passagensDisponiveis;
        System.out.println("\nPassageiro "+numPassageiro + ": trechos q possuo " +trechos.size());
        for(int i=0; i<trechos.size()-1;i++){
            System.out.println("\nPassageiro "+numPassageiro + ": entrei aqui " +i);
            posicaoC1= grafo.getVertices().getPosicao(trechos.get(i));//pega a posicao do vertice na lista
            posicaoC2=grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getPosicao(trechos.get(i+1));
            if(posicaoC1!=-1 && posicaoC2!=-1){
                System.out.println("\nPassageiro "+numPassageiro +"Os vertices "+trechos.get(i)+" "+trechos.get(i+1)+"sao adjacentes");
                passagensDisponiveis= grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).getQuantPassagens();  
                if(passagensDisponiveis==0){ //Se não tem passagens disponiveis
                    isDisponivel=false;
                }
            }
            else{
                System.out.println("\nPassageiro "+numPassageiro +" Nao encontrei os vertices");
            }
        }
        if(isDisponivel){
            System.out.println("\nPassageiro "+numPassageiro + ": há passagens disponiveis");
        }
        else{
            System.out.println("\nPassageiro "+numPassageiro + ": não há passagens disponiveis");
        }
        return isDisponivel;
    }
    
    public void realizarCompra() throws InterruptedException{
        semaforo.acquire();
        for(int i=0; i<trechos.size()-1;i++){
            int posicaoC1= grafo.getVertices().getPosicao(trechos.get(i));//pega a posicao do vertice na lista
            int posicaoC2=grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getPosicao(trechos.get(i+1));
            if(posicaoC1!=-1 && posicaoC2!=-1){
                int passagensDisponiveis= grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).getQuantPassagens();
                System.out.println("\nPassageiro "+numPassageiro+ ": passagens antes da compra "+passagensDisponiveis);
                //diminui a quantidade de passagens
                grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).setQuantPassagens();
                passagensDisponiveis= grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).getQuantPassagens();
                System.out.println("\nPassageiro "+numPassageiro+ ": a compra do trecho "+trechos.get(i)+" a "+trechos.get(i+1)+" foi reaizada");
                System.out.println("\nRestam " +passagensDisponiveis+" passagens disponiveis de "+trechos.get(i)+" para "+trechos.get(i+1));
            } 
        }
        semaforo.release();
    }
      
    public void liberarPassagens(){
        System.out.println("\nPassageiro "+numPassageiro+ ": estou liberando as passagens");
        for(int i=0; i<trechos.size()-1;i++){
            int posicaoC1= grafo.getVertices().getPosicao(trechos.get(i));//pega a posicao do vertice na lista
            int posicaoC2=grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getPosicao(trechos.get(i+1));
            if(posicaoC1!=-1 && posicaoC2!=-1){
                grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).devolverPassagens();
                int passagensDisponiveis= grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).getQuantPassagens();
                System.out.println("\nPassageiro "+numPassageiro+ ": passagens"+passagensDisponiveis);
            }
        }
    }
    
    @Override
    public void run() { 
          /*  Scanner escanear= new Scanner(System.in);
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
        System.out.println("\nPassageiro "+numPassageiro+ "tamanho adjacencia"+adjacencia.length);
        for(int i = 0; i < adjacencia.length; i++) {//Até o fim do vetor
           System.out.println("\nPassageiro "+numPassageiro+ "adjacencia "+i+" "+adjacencia[i]);
          trechos.add(adjacencia[i]); //adiciona na lista de trechos
        }    
        System.out.println("\nPassageiro "+numPassageiro+ ", a compra da sua passagem será realizada neste exato momento");
        
       //Verifica se todos os destinos está disponivel
       boolean isDisponiveis= verificarLocais();
        if(isDisponiveis){ //Se os trechos estiverem disponiveis
            try {
                this.realizarCompra();
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
        else{
            System.out.println("Passageiro "+numPassageiro+ " alguns locais escolhidos da sua viagem não há passagens no momento, aguarde!");
               //Adiciona em uma lista de espera, mas quem tem a lista de espera?
        }
    }
    
}
