/**
 * Componente Curricular:  Sistemas Operacionais
 * Autor: Estéfane Carmo de Souza e Messias Jr. Lira da Silva
 * Data: 30-10-2020
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
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Passageiro extends Thread {
    private List<String> trechos = new ArrayList<>(); //Lista que armezena o nome das cidades 
    private int[] numCidadesEscala; //Vetor que armazena o número das cidades da passagem
    private Grafo grafo= Grafo.getInstance(); //Instancia do grafo
    private Semaphore semaforo;           //Semáforo
    private int numPassageiro;           //Número da thread
    private boolean statusCompra=false; //Status que indica se a compra foi realizada
    
    public Passageiro(Semaphore semaforo, int numero, int[] numCidadesEscala){
        this.semaforo= semaforo;
        numPassageiro= numero;
        this.numCidadesEscala=numCidadesEscala;
    }
    
   /**
    * Método que verifica se todos os trechos escolhidos possuem passagens disponíveis
    * @return boolean - true se todos trechos estiverem disponíveis
    * @throws InterruptedException 
    */
    public boolean verificarLocais() throws InterruptedException{
        boolean isDisponivel=true;
        int posicaoC1, posicaoC2, passagensDisponiveis;
        boolean trechoReservado=false;
        int i=0;
        System.out.println("\nPassageiro "+numPassageiro + ", estamos verificando se há passagens disponiveis");
        //Verifica todos os trechos
        for(i=0; i<trechos.size()-1;i++){
            //Posição da cidade na lista
            posicaoC1= grafo.getCidades().getPosicao(trechos.get(i));
            //Posição da outra cidade, dentro da lista de adjacencia da anterior
            posicaoC2=grafo.getCidades().get(posicaoC1).getConteudo().getAdjacencias().getPosicao(trechos.get(i+1));
            //Se as cidades foram encontradas
            if(posicaoC1!=-1 && posicaoC2!=-1){
                //Salva a quantidade de passagens que o trecho possui atualmente
                passagensDisponiveis= grafo.getCidades().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).getQuantPassagens(); 
                //Se não tem passagens disponiveis ou trecho está reservado
                if(passagensDisponiveis<=0 || trechoReservado==true){ 
                    isDisponivel=false; //muda a váriavel para falso
                }
                else if(passagensDisponiveis>0 && trechoReservado==false){ //Se estiver disponível
                    semaforo.acquire();//Garante exclusidade
                    //Reserva o trecho atual
                    grafo.getCidades().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).setReservado(true);
                    semaforo.release();
                }
            }  
        }
        if(isDisponivel==false){ //Se a váriavel estiver falsa
            //Altera o estado Reservado de todos os trechos da rota escolhido para falso
            for(i=0; i<trechos.size()-1;i++){
               //Posição da cidade na lista
                posicaoC1= grafo.getCidades().getPosicao(trechos.get(i));
                //Posição da outra cidade, dentro da lista de adjacencia da anterior
                posicaoC2=grafo.getCidades().get(posicaoC1).getConteudo().getAdjacencias().getPosicao(trechos.get(i+1));
                //Altera o estado reservado para falso
                grafo.getCidades().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).setReservado(false);
           }
       }
        return isDisponivel;
    }
    
    /**
     * Método que realiza a compra das passagens
     * @throws InterruptedException 
     */
    public void realizarCompra() throws InterruptedException{
        semaforo.acquire(); //Entra na região crítica
        for(int i=0; i<trechos.size()-1;i++){
            //Posição da cidade na lista
            int posicaoC1= grafo.getCidades().getPosicao(trechos.get(i));
            //Posição da outra cidade, dentro da lista de adjacencia da anterior
            int posicaoC2=grafo.getCidades().get(posicaoC1).getConteudo().getAdjacencias().getPosicao(trechos.get(i+1));
            //Se as cidades foram encontradas
            if(posicaoC1!=-1 && posicaoC2!=-1){
                //Diminui a quantidade de passagens
                grafo.getCidades().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).setQuantPassagens();
            } 
        }
        semaforo.release(); //Sai da região crítica
    }
    /**
     * Método que devolve as passagens, para que outros passageiros possam comprar
     */
    public void liberarPassagens(){
        statusCompra=true; //Altera o status da compra para true, que indica que já terminou a compra
        //Libera todos os trechos
        for(int i=0; i<trechos.size()-1;i++){
            //Posição da cidade na lista
            int posicaoC1= grafo.getCidades().getPosicao(trechos.get(i));
            //Posição da outra cidade, dentro da lista de adjacencia da anterior
            int posicaoC2=grafo.getCidades().get(posicaoC1).getConteudo().getAdjacencias().getPosicao(trechos.get(i+1));
            //Se as cidades foram encontradas
            if(posicaoC1!=-1 && posicaoC2!=-1){
                //Adiciona 1 na quantidade de passagens do trecho atual 
                grafo.getCidades().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).devolverPassagens();
                //Altera o estado Reservado para true;
                grafo.getCidades().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).setReservado(false);
                //Obtém a quantidade de passagens atuais do trecho
                int passagensDisponiveis= grafo.getCidades().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2).getQuantPassagens();
                System.out.println("\n*********************************************************************************************"
                        + "\nSurgiram novas passagens. Há "+passagensDisponiveis+" passagem(ns) disponível(is) de "+trechos.get(i)+" para "+trechos.get(i+1)
                        + "\n*********************************************************************************************");
            }
        }
    }

    @Override
    public void run() { 
        int cidade; //armazena o número da cidade
        String nomeCidade; //armazena o nome da cidade
        List<String> cidadesEscala = new ArrayList<>(); //lista para armazenar os nomes das cidades da escala
        for(int i=0; i<numCidadesEscala.length;i++){ //Pecorre todo o vetor
            cidade= numCidadesEscala[i]; //número da cidade
            nomeCidade= grafo.getCidades().get(cidade).getConteudo().getNome(); //Nome da cidade, na posição indicada 
            cidadesEscala.add(nomeCidade); //Adiciona na lista
        }
        
        System.out.println("\nPassageiro "+numPassageiro+",   a cidade origem da sua viagem é: "+cidadesEscala.get(0)
                + "\n\t\ta cidade destino da sua viagem é: "+cidadesEscala.get(cidadesEscala.size()-1));
       
        grafo.calcularRota(cidadesEscala);
        System.out.println("\nPassageiro "+numPassageiro+", a escala escolhida foi: " +grafo.getRota() 
                + "\n***************************INFORMAÇÕES SOBRE A ESCALA:***************************\n"+ grafo.sobreRota()
                + "\n*********************************************************************************\n");
        
        
        if(grafo.getRota()!=null){ //Se a rota foi encontrada
            String[] adjacencia = grafo.getRota().split("->"); //Separa a string rota
            for(int i = 0; i < adjacencia.length; i++) {//Até o fim do vetor
              trechos.add(adjacencia[i]); //adiciona na lista de trechos, os nomes das cidades
            }    
            while(!statusCompra){ //Irá repitir até a thread comprar as passagens e liberar as mesmas.
                boolean isDisponiveis;
                try {
                    isDisponiveis = verificarLocais(); //Verifica se todos os trechos estão disponíveis
                    if(isDisponiveis){ //Se os trechos estiverem disponiveis
                        System.out.println("\nPassageiro "+numPassageiro+ ", há passagens disponiveis para os "
                                + "trechos escolhidos. A compra da sua passagem será realizada neste exato momento");
                        try {
                            this.realizarCompra(); //Realiza a compra da passagem
                            System.out.println("\nPassageiro "+numPassageiro+ ", a compra da sua passagem foi "
                                    + "realizada com sucesso!");
                        } catch (InterruptedException ex) {
                             Logger.getLogger(Passageiro.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            Passageiro.sleep(10); //dorme por um tempo
                            this.liberarPassagens(); //devolve as passagens
                        } catch (InterruptedException ex) {
                             Logger.getLogger(Passageiro.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else{ //Se todos os trechos não estiverem disponíveis
                        System.out.println("\nPassageiro "+numPassageiro+ ", alguns locais escolhidos na sua "
                                + "viagem não há passagens no momento, aguarde!");    
                        try {
                            Passageiro.sleep(20); //Dorme por um tempo 
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Passageiro.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Passageiro.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
        }
   }
}
        
   

