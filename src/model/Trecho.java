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

/**
 * Esta classe armazena os dados de um Trecho, a segunda cidade que está
 ligada, o tempo de voo que possui, a companhia aérea, a quantidade de passagens
 * e o estado reservado.
 *
 * Exemplo de uso:

 Trecho trecho= new Trecho(vertice,15, "Tam");
 *
 * @author Estéfane Carmo de Souza
 * @author Messias Jr. Lira da Silva
 */
public class Trecho {

    private Cidade destino;
    private int tempoVoo;
    private String companhia;
    private int quantPassagens=1;
    private boolean reservado=false;

    public Trecho(Cidade destino, int peso,String nomeCompanhia) {
        this.destino = destino;
        this.tempoVoo = peso;
        this.companhia= nomeCompanhia;
    }

    /**
     * Método que obtém uma das cidades
     *
     * @return Cidade - cidade destino
     */
    public Cidade getDestino() {
        return destino;
    }

    /**
     * Método que obtém o tempo de Voo do trecho
     *
     * @return int - tempoVoo
     */
    public int getTempoVoo() {
        return tempoVoo;
    }
    
    /**
     * Método que obtém o companhia aérea do trecho
     *
     * @return String - nome da Companhia
     */
    public String getCompanhia() {
        return companhia;
    }
    
    /**
     * Método que obtém a quantidade de passagens do trecho
     * @return int - quantPassagens
     */
    public int getQuantPassagens() {
        return quantPassagens;
    }
    
    
    /**
     * Método que altera a cidade de destino
     *
     * @param destino- a nova cidade
     */
    public void setDestino(Cidade destino) {
        this.destino = destino;
    }

    /**
     * Método que altera o tempo de voo do trecho
     *
     * @param tempoVoo- o novo tempo de voo da trecho
     */
    public void setTempoVoo(int tempoVoo) {
        this.tempoVoo = tempoVoo;
    }
    
    /**
     * Método que altera a companhia aérea do trecho
     *
     * @param companhia- a nova companhia aérea do trecho
     */
    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    } 
    
    /**
     * Método que diminui um na quantidade de passagens do trecho
     */
    public void setQuantPassagens() {
        this.quantPassagens= this.quantPassagens-1;
    }
    
    /**
     * Método que adiciona um na quantidade de passagens do trecho
     */
    public void devolverPassagens(){
        this.quantPassagens= this.quantPassagens+1;
    }
    
    /**
     * Método que obtém verifica se o trecho está reservado
     * @return boolean - true se estiver reservado e false caso contrário
     */
    public boolean isReservado() {
        return reservado;
    }
    
    /**
     * Método que altera o estado reservado
     * @param reservado - novo valor 
     */
    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }
    
    /**
     * Método que retona as informações do trecho
     * @return String - as informações
     */
    @Override
    public String toString(){
        return ("\tCompanhia: "+this.companhia + "\tTempo de vôo: "+ this.tempoVoo+ " horas");
    }
    
}
