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

import util.TrechoList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import util.CidadeList;
import util.No;

/**
 * Esta classe armazena os dados de um grafo, no qual possui uma referência para
 * a lista com todos os vertices presentes.
 *
 * Exemplo de uso:
 *
 * Grafo grafo= new Grafo();
 *
 * @author Estéfane Carmo de Souza
 * @author Messias Jr. Lira da Silva
 */
public class Grafo {

    private static Grafo instancia;
    private CidadeList vertices;
    private String rota;
    private String pontoPartida;
    private String pontoChegada;
    private List<Passageiro> filaEspera= new ArrayList<>();

    private Grafo(){
        vertices = new CidadeList();
        rota = null;
        pontoPartida = null;
        pontoChegada = null;
    }

    /**
     * Método que retorna a única instancia do grafo. Caso não exista, cria a
     * mesma.
     *
     * @return Grafo- a instância do grafo
     */
    public static synchronized Grafo getInstance() {
        if (instancia == null) {
            instancia = new Grafo();
        }
        return instancia;
    }

    /**
     * Método que obtém a lista de vértices do grafo
     *
     * @return CidadeList - a lista
     */
    public CidadeList getVertices() {
        return vertices;
    }

    /**
     * Método que obtém a rota calculada
     *
     * @return String - rota
     */
    public String getRota() {
        return rota;
    }

    /**
     * Método que obtém o ponto de coleta rota calculada
     *
     * @return String - o ponto de coleta
     */
    public String getPontoPartida() {
        return pontoPartida;
    }

    /**
     * Método que obtém o ponto de chegada rota calculada
     *
     * @return String - o ponto de chegada
     */
    public String getPontoChegada() {
        return pontoChegada;
    }

    /**
     * Método que adiciona um vertice a lista de vertices
     *
     * @param nome - nome do vertice
     */
    public void addVertice(String nome) {
        Cidade vertice = new Cidade(nome);
        vertices.add(vertice);
    }

    /**
     * Método que adiciona uma aresta entre dois vertices do grafo
     *
     * @param nomeV1 - nome de um vertice
     * @param nomeV2 - nome do outro vertice
     * @param peso - peso da aresta
     * @param nomeAresta - nome da aresta
     */
    public void addAresta(String nomeV1, String nomeV2, int peso,String nomeAresta){
        Cidade v1 = vertices.procurarNo(nomeV1);
        Cidade v2 = vertices.procurarNo(nomeV2);
        //Cria as arestas, com o peso e o vertice de destino
        Trecho aresta1 = new Trecho(v1, peso,nomeAresta);
        Trecho aresta2 = new Trecho(v2, peso,nomeAresta);
        //Adiciona nos vertices a aresta criada
        vertices.procurarNo(nomeV2).getAdjacencias().add(aresta1);
        vertices.procurarNo(nomeV1).getAdjacencias().add(aresta2);
    }

    /**
     * Método que remove um vertice com o nome indicado
     *
     * @param nome - nome do vertice
     */
    public void removerVertice(String nome) {
        Cidade removido = vertices.remove(nome);
        //Remove a referência do vertice removido, na lista de adjacencias dos vertices que se ligavam com ele
        No auxiliar = removido.getAdjacencias().getPrimeiro();
        while (auxiliar != null) {
            auxiliar.getConteudo().getDestino().getAdjacencias().remove(removido.getNome());
            auxiliar = auxiliar.getNext();
        }
    }

    /**
     * Remove uma ligação entre dois vertices indicados
     *
     * @param nome1 - nome de um dos vertice
     * @param nome2 - nome do outro vetice
     * @return String - a rota atualizada
     */
    public String removerAresta(String nome1, String nome2) {
        //Obtém as posições dos vertices
        int posicaoV1 = vertices.getPosicao(nome1);
        int posicaoV2 = vertices.getPosicao(nome2);
        //Se os vertices foram encontrados, remove da lista de adjacência o outro vertice
        if (posicaoV1 != -1 && posicaoV2 != -1) {
            vertices.get(posicaoV1).getConteudo().getAdjacencias().remove(nome2);
            vertices.get(posicaoV2).getConteudo().getAdjacencias().remove(nome1);
        }
        //Se a ligação estiver na rota, atualiza a mesma
        /*if (rota != null && (rota.contains(nome1 + " - " + nome2) || rota.contains(nome2 + " - " + nome1))) {
            rota = null;
            return calcularRota(this.pontoPartida, this.pontoChegada);
        }*/
        return "";
    }
    
    /**
     * Método que calcula o menor caminho entre o estacionamento ao ponto de
     * coleta e do ponto de coleta até o banco
     *
     * @param cidadesEscala
     */
    public void calcularRota(List<String> cidadesEscala) {
        rota= cidadesEscala.get(0);
        for(int i=0; i<cidadesEscala.size()-1;i++){
            int posicaoC1= this.getVertices().getPosicao(cidadesEscala.get(i));//pega a posicao do vertice na lista
            int posicaoC2=this.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getPosicao(cidadesEscala.get(i+1));
            if(posicaoC1!=-1 && posicaoC2!=-1){
               rota= rota.concat("->"+cidadesEscala.get(i+1)); 
            }
            else{
                System.out.println("Não existem voos cadastrados de "+cidadesEscala.get(i)+" a "+cidadesEscala.get(i+1));
                rota=null;
            }
       }
    }
    
    public String sobreRota(){
        int tempo=0;
        String texto = "";
        String[] cidades = rota.split("->");
        for(int i=0; i<cidades.length-1; i++){
            int posicaoC1= this.getVertices().getPosicao(cidades[i]);//pega a posicao do vertice na lista
            int posicaoC2=this.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getPosicao(cidades[i+1]);
            Trecho trecho =this.getVertices().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2);
            tempo += trecho.getTempoVoo();
            texto += cidades[i]+"->"+cidades[i+1]+trecho.toString()+"\n";
        }
        texto+="Tempo total de vôo: "+tempo+ " horas"; 
        return texto ;
    }
         
    /**
     * Método que remove todos os vertices do grafo
     */
    public void removerTodosVertice() {
        while (vertices.getPrimeiro() != null) {
            vertices.setPrimeiro(vertices.getPrimeiro().getNext());
        }
    }
  
    public void listarCidades(){
        ArrayList<String> lista = this.getVertices().listarTodosPontos();
        for(int i=0; i<lista.size();i++){
            System.out.println(i+". "+lista.get(i));
        }
    }

    /**
     * Método para importação de um grafo a partir de um arquivo, em que contém
     * o número de vertices, cada linha contém o nome do vertice e o tipo do
     * mesmo, separados por um espaço. Após isso, cada uma linha com o nome do
     * vertice e a seguinte com o vertice adjacente e o peso da ligação separado
     * por espaço.
     *
     * @param nomeArquivo - nome do arquivo
     * @throws IOException - exceção de arquivo
     */
    public void importarArquivo(String nomeArquivo) throws IOException {
        BufferedReader ler = null;
        int contador = 0;
        try {
            ler = new BufferedReader(new FileReader(nomeArquivo));
            contador = Integer.parseInt(ler.readLine());
            while (contador > 0) {//enquanto contador for maior que 0
                String linha = ler.readLine();//lê a linha
                String nome = linha;//parts[0]; //primeira parte contém o nome do vertice
                this.addVertice(nome);
                contador--;//decrementa no contador
            }
            importarArquivoCompanhia("Azul.txt");
            importarArquivoCompanhia("Gol.txt");
            importarArquivoCompanhia("Tam.txt");
        } catch (FileNotFoundException exception) {
            throw new IOException();
        } finally {
            if (ler != null) {
                ler.close(); //fecha o arquivo
            }
        }
    }
        
    /**
     * Importa o arquivo com as rotas de uma companhia
     * @param nomeArquivo
     * @throws IOException
     */
    public void importarArquivoCompanhia(String nomeArquivo) throws IOException{
        BufferedReader ler = null;
        try {
            ler = new BufferedReader(new FileReader(nomeArquivo));
            String vertice1, nomeAresta, linha;
            nomeAresta = ler.readLine(); //lê a linha e armazena na string
            linha = ler.readLine();//lê a proxima linha
            while (linha != null) { //enquanto não for o fim do arquivo
                vertice1 = linha; //a linha lida é o vertice
                linha = ler.readLine();//lê a proxima linha
                String[] adjacencia = linha.split(" ");//separa em partes
                for (int i = 0; i < adjacencia.length; i = i + 2) {//Até o fim do vetor
                    String vertice2 = adjacencia[i]; //indica o segundo vertice
                    int peso = Integer.parseInt(adjacencia[i + 1]); //converte para int a string
                    this.addAresta(vertice1, vertice2, peso, nomeAresta); //adiciona a aresta no grafo.
                }
                linha = ler.readLine();
            }
        } catch (FileNotFoundException exception) {
            throw new IOException();
        } finally {
            if (ler != null) {
                ler.close(); //fecha o arquivo
            }
        }
    }
      
}