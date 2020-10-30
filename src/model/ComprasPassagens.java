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
    private int[] numCidadesEscala;
    private Grafo grafo= Grafo.getInstance();
    private Semaphore semaforo;
    private int numPassageiro;
    private boolean statusCompra=false;
    //private gerenciadorThread gerenciar= new gerenciadorThread();
    
    public ComprasPassagens(Semaphore semaforo, int numero, int[] numCidadesEscala){
        this.semaforo= semaforo;
        numPassageiro= numero;
        this.numCidadesEscala=numCidadesEscala;
    }
    
    public boolean verificarLocais() throws InterruptedException{
        boolean isDisponivel=true;
        int posicaoC1, posicaoC2, passagensDisponiveis;
        boolean trechoReservado=false;
        int i=0;
        System.out.println("\nPassageiro "+numPassageiro + ", estamos verificando se há passagens disponiveis");
         System.out.println("\n******************Passageiro "+numPassageiro + ", quantidade de trecho"+trechos.size());
       for(i=0; i<trechos.size()-1;i++){
            posicaoC1= grafo.getVertices().getPosicao(trechos.get(i));//pega a posicao do vertice na lista
            posicaoC2=grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getPosicao(trechos.get(i+1));
            if(posicaoC1!=-1 && posicaoC2!=-1){
                passagensDisponiveis= grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).getQuantPassagens(); 
                trechoReservado=grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).isReservado();
                System.out.println("\n*******************Passageiro "+numPassageiro + " valor de passagens "+passagensDisponiveis
               +"Passageiro "+numPassageiro + ", trecho reservado "+trechoReservado+" de "+trechos.get(i)+" para "+trechos.get(i+1));
             
                if(passagensDisponiveis<=0 || trechoReservado==true){ //Se não tem passagens disponiveis
                  
                    isDisponivel=false;
                    System.out.println("\n*************Passageiro "+numPassageiro + ", não tem passagens disponíveis");
                }
                else if(passagensDisponiveis>0 && trechoReservado==false){
                    semaforo.acquire();
                    grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).setReservado(true);
                    semaforo.release();
                }
            }  
        }
       if(isDisponivel==false){
           for(i=0; i<trechos.size()-1;i++){
                posicaoC1= grafo.getVertices().getPosicao(trechos.get(i));//pega a posicao do vertice na lista
                posicaoC2=grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getPosicao(trechos.get(i+1));
                grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).setReservado(false);
           }
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
                System.out.println("\n***************************Passageiro "+numPassageiro + ", há passagens " +passagensDisponiveis+ " disponiveis de "+trechos.get(i)+ " para "+trechos.get(i+1));
                    //diminui a quantidade de passagens
                grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).setQuantPassagens();
                passagensDisponiveis= grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).getQuantPassagens();
                System.out.println("\n***************************Passageiro "+numPassageiro +" restam " +passagensDisponiveis+" passagens disponiveis de "+trechos.get(i)+" para "+trechos.get(i+1));
            } 
        }
        semaforo.release();
    }
      
    public void liberarPassagens(){
        statusCompra=true;
        for(int i=0; i<trechos.size()-1;i++){
            int posicaoC1= grafo.getVertices().getPosicao(trechos.get(i));
            int posicaoC2=grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getPosicao(trechos.get(i+1));
            if(posicaoC1!=-1 && posicaoC2!=-1){
                grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).devolverPassagens();
                grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).setReservado(false);
                boolean reservado = grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).isReservado();
                int passagensDisponiveis= grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).getQuantPassagens();
                System.out.println("\n*********************************************************************************************");
                System.out.println("Surgiram novas passagens. Há "+passagensDisponiveis+" passagem(ns) disponível(is) de "+trechos.get(i)+" para "+trechos.get(i+1));
                System.out.println("8888888 obs - variavel reservado é  "+reservado);
                System.out.println("*********************************************************************************************");
            }
        }
       // gerenciar.filaEspera.add(this);
   /*    synchronized(this){
           this.notifyAll();
        }*/
    }
    
    public synchronized void emEspera() throws InterruptedException{
        System.out.println("\nPassageiro "+numPassageiro+"estou indo dormir");
        this.wait();
    }

    
    @Override
    public void run() { 
       //while(!statusCompra){
        //Procura o nome das cidades que compõe a viagem
        int cidade;
        String nomeCidade;
        List<String> cidadesEscala = new ArrayList<>();
        for(int i=0; i<numCidadesEscala.length;i++){
            cidade= numCidadesEscala[i];
            nomeCidade= grafo.getVertices().get(cidade).getConteudo().getNome();
            cidadesEscala.add(nomeCidade);        
        }
        System.out.println("\nPassageiro "+numPassageiro+", a cidade origem da sua viagem é: "+cidadesEscala.get(0));
        System.out.println("\nPassageiro "+numPassageiro+", a cidade destino da sua viagem é: "+cidadesEscala.get(cidadesEscala.size()-1));
       
        grafo.calcularRota(cidadesEscala);
        System.out.println("\nPassageiro "+numPassageiro+", a escala escolhida foi: " +grafo.getRota());
        
        if(grafo.getRota()!=null){
            String[] adjacencia = grafo.getRota().split("->");
            for(int i = 0; i < adjacencia.length; i++) {//Até o fim do vetor
              trechos.add(adjacencia[i]); //adiciona na lista de trechos
            }    
            while(!statusCompra){
            //Verifica se todos os destinos está disponivel
            boolean isDisponiveis;
            try {
                isDisponiveis = verificarLocais();
                if(isDisponiveis){ //Se os trechos estiverem disponiveis
               System.out.println("\nPassageiro "+numPassageiro+ ", há passagens disponiveis para os trechos escolhidos. A compra da sua passagem será realizada neste exato momento");
                try {
                    this.realizarCompra();
                     System.out.println("\nPassageiro "+numPassageiro+ ", a compra da sua passagem foi realizada com sucesso!");
                } catch (InterruptedException ex) {
                    Logger.getLogger(ComprasPassagens.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    ComprasPassagens.sleep(10);
                    this.liberarPassagens();
                   // gerenciar.acordar();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ComprasPassagens.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                System.out.println("Passageiro "+numPassageiro+ ", alguns locais escolhidos na sua viagem não há passagens no momento, aguarde!");    
                try {
                    /*  try {
                    synchronized (this) {
                    System.out.println("indo dormir");
                    wait();
                    }
                    } catch (InterruptedException ex) {
                    Logger.getLogger(ComprasPassagens.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                    ComprasPassagens.sleep(20);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ComprasPassagens.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ComprasPassagens.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            }
           
            for(int i=0; i<trechos.size()-1;i++){
                int posicaoC1= grafo.getVertices().getPosicao(trechos.get(i));
                int posicaoC2=grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getPosicao(trechos.get(i+1));
                if(posicaoC1!=-1 && posicaoC2!=-1){
                    boolean reservado = grafo.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).isReservado();
                    System.out.println("\n***********************Passageiro "+numPassageiro+"booleano reservado "+reservado);
                }
            }
            
            } //While
        }
    }
        
   

