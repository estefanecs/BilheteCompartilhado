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

/**
 * Esta classe armazena os dados de uma aresta, o segundo vertice que está
 ligada, o tempo de voo que possui e companhia.
 *
 * Exemplo de uso:

 Trecho aresta= new Trecho(vertice,15);
 *
 * @author Estéfane Carmo de Souza e Messias Jr. Lira da Silva
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
     * Método que obtém um dos vértices
     *
     * @return Cidade - vertice destino
     */
    public Cidade getDestino() {
        return destino;
    }

    /**
     * Método que obtém o tempo de Trecho da aresta
     *
     * @return int - tempoVoo
     */
    public int getTempoVoo() {
        return tempoVoo;
    }
    
    /**
     * Método que obtém o companhia da aresta
     *
     * @return String - nomCompanhia
     */
    public String getCompanhia() {
        return companhia;
    }
    
    /**
     * Método que obtém a quantidade de passagens do voo
     * @return int - quantPassagens
     */
    public int getQuantPassagens() {
        return quantPassagens;
    }
    
    
    /**
     * Método que altera o vértice de destino
     *
     * @param destino- o novo vértice
     */
    public void setDestino(Cidade destino) {
        this.destino = destino;
    }

    /**
     * Método que altera o tempoVoo da aresta
     *
     * @param tempoVoo- o novo tempoVoo da aresta
     */
    public void setTempoVoo(int tempoVoo) {
        this.tempoVoo = tempoVoo;
    }
    
    /**
     * Método que altera o companhia da aresta
     *
     * @param companhia- o novo companhia da aresta
     */
    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    } 
    
    /**
     * Método que diminui um na quantidade de passagens desse voo
     *
     */
    public void setQuantPassagens() {
        this.quantPassagens= this.quantPassagens-1;
    }
    
    public void devolverPassagens(){
        this.quantPassagens= this.quantPassagens+1;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }
    
    @Override
    public String toString(){
        return ("\tCompanhia: "+this.companhia + "\tTempo de vôo: "+ this.tempoVoo+ " horas");
    }
    
}
