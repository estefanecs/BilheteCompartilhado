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
 a lista com todos os cidades presentes.
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
    private CidadeList cidades;
    private String rota;

    private Grafo(){
        cidades = new CidadeList();
        rota = null;
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
     * Método que obtém a lista de cidades do grafo
     *
     * @return CidadeList - a lista
     */
    public CidadeList getCidades() {
        return cidades;
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
     * Método que adiciona uma cidade a lista de cidades
     *
     * @param nome - nome da cidade
     */
    public void addCidade(String nome) {
        Cidade cidade = new Cidade(nome);
        cidades.add(cidade);
    }

    /**
     * Método que adiciona um trecho entre dois cidades do grafo
     *
     * @param nomeC1 - nome de uma cidade
     * @param nomeC2 - nome da outroacidade
     * @param peso - peso do trecho
     * @param companhia - nome do trecho
     */
    public void addTrecho(String nomeC1, String nomeC2, int peso,String companhia){
        Cidade v1 = cidades.procurarNo(nomeC1);
        Cidade v2 = cidades.procurarNo(nomeC2);
        //Cria as arestas, com o peso e o cidade de destino
        Trecho trecho1 = new Trecho(v1, peso,companhia);
        Trecho trecho2 = new Trecho(v2, peso,companhia);
        //Adiciona nos cidades a aresta criada
        cidades.procurarNo(nomeC2).getAdjacencias().add(trecho1);
        cidades.procurarNo(nomeC1).getAdjacencias().add(trecho2);
    }

    /**
     * Método que remove uma cidade com o nome indicado
     *
     * @param nome - nome da cidade
     */
    public void removerCidade(String nome) {
        Cidade removido = cidades.remove(nome);
        //Remove a referência da cidade removida, na lista de adjacencias das cidades que se ligavam com ela
        No auxiliar = removido.getAdjacencias().getPrimeiro();
        while (auxiliar != null) {
            auxiliar.getConteudo().getDestino().getAdjacencias().remove(removido.getNome());
            auxiliar = auxiliar.getNext();
        }
    }

    /**
     * Remove uma ligação entre duas cidades indicadas
     *
     * @param nome1 - nome de uma das cidades
     * @param nome2 - nome da outra cidade
     * @return String - a rota atualizada
     */
    public String removerTrecho(String nome1, String nome2) {
        //Obtém as posições das cidades
        int posicaoV1 = cidades.getPosicao(nome1);
        int posicaoV2 = cidades.getPosicao(nome2);
        //Se as cidades foram encontradas, remove da lista de adjacência da outra cidade
        if (posicaoV1 != -1 && posicaoV2 != -1) {
            cidades.get(posicaoV1).getConteudo().getAdjacencias().remove(nome2);
            cidades.get(posicaoV2).getConteudo().getAdjacencias().remove(nome1);
        }
        return "";
    }
    
    /**
     * Método que calcula o menor caminho entre o estacionamento ao ponto de
     * coleta e do ponto de coleta até o banco
     *
     * @param cidadesEscala
     */
    public void calcularRota(List<String> cidadesEscala) {
        rota= cidadesEscala.get(0); //Adiciona o primeiro nome da lista na string Rota
        for(int i=0; i<cidadesEscala.size()-1;i++){
            //Posição da cidade na lista
            int posicaoC1= this.getCidades().getPosicao(cidadesEscala.get(i));
            //Posição da outra cidade, dentro da lista de adjacencia da anterior
            int posicaoC2=this.getCidades().get(posicaoC1).getConteudo().getAdjacencias().getPosicao(cidadesEscala.get(i+1));
            if(posicaoC1!=-1 && posicaoC2!=-1){//Se as cidades foram encontradas
               //Adiciona o nome da próxima cidade na string rota
               rota= rota.concat("->"+cidadesEscala.get(i+1)); 
            }
            else{ //Se as cidades não foram encontradas
                System.out.println("Não existem voos cadastrados de "+cidadesEscala.get(i)+" a "+cidadesEscala.get(i+1));
                rota=null; //Muda a rota para nulo.
            }
       }
    }
    /**
     * Método que salva as informações da rota, como a companhia
     * de cada trecho e o tempo de voo.
     * @return String - a string com as informações
     */
    public String sobreRota(){
        int tempo=0;
        String texto = "";
        String[] cidades = rota.split("->");
        for(int i=0; i<cidades.length-1; i++){
            //Posição da cidade na lista
            int posicaoC1= this.getCidades().getPosicao(cidades[i]);
            //Posição da outra cidade, dentro da lista de adjacencia da anterior
            int posicaoC2=this.getCidades().get(posicaoC1).getConteudo().getAdjacencias().getPosicao(cidades[i+1]);
            //Salva o trecho atual
            Trecho trecho =this.getCidades().get(posicaoC1).getConteudo().getAdjacencias().getTrecho(posicaoC2);
            tempo += trecho.getTempoVoo(); //Soma o tempo de voo
            texto += cidades[i]+"->"+cidades[i+1]+trecho.toString()+"\n"; //Concatena na string as informações do trecho
        }
        texto+="Tempo total de vôo: "+tempo+ " horas"; 
        return texto ;
    }
    
    /**
     * Método que lista todas as cidades cadastradas no grafo;
     */
    public void listarCidades(){
        ArrayList<String> lista = this.getCidades().listarTodosPontos();
        for(int i=0; i<lista.size();i++){ 
            System.out.println(i+". "+lista.get(i));
        }
    }

    /**
     * Método para importação de um grafo a partir de um arquivo, em que contém
     * o número de cidades, cada linha contém o nome do cidade.
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
                String nome = linha; //contém o nome do cidade
                this.addCidade(nome);
                contador--;//decrementa no contador
            }
            importarArquivoCompanhia("Azul.txt"); //importa o arquivo da companhia
            importarArquivoCompanhia("Gol.txt"); //importa o arquivo da companhia
            importarArquivoCompanhia("Tam.txt"); //importa o arquivo da companhia
        } catch (FileNotFoundException exception) {
            throw new IOException();
        } finally {
            if (ler != null) {
                ler.close(); //fecha o arquivo
            }
        }
    }
        
    /**
     * Importa o arquivo com as rotas de uma companhia. A primeira linha
     * contém o nome da companhia aérea. Cada linha contém o nome da cidade.
     * Após isso, cada uma linha com o nome da cidade e a seguinte com a cidade 
     * adjacente e o peso da ligação(de voo) separada por espaço.
     *
     * @param nomeArquivo - nome do arquivo
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
                vertice1 = linha; //a linha lida é o cidade
                linha = ler.readLine();//lê a proxima linha
                String[] adjacencia = linha.split(" ");//separa em partes
                for (int i = 0; i < adjacencia.length; i = i + 2) {//Até o fim do vetor
                    String vertice2 = adjacencia[i]; //indica o segundo cidade
                    int peso = Integer.parseInt(adjacencia[i + 1]); //converte para int a string
                    this.addTrecho(vertice1, vertice2, peso, nomeAresta); //adiciona a aresta no grafo.
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