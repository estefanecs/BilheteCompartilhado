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

import util.ArestaList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import util.VerticeList;
import util.No;
import util.Node;

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
    private VerticeList vertices;
    private String rota;
    private String pontoPartida;
    private String pontoChegada;

    private Grafo(){
        vertices = new VerticeList();
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
     * @return VerticeList - a lista
     */
    public VerticeList getVertices() {
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
        if (rota != null && (rota.contains(nome1 + " - " + nome2) || rota.contains(nome2 + " - " + nome1))) {
            rota = null;
            return calcularRota(this.pontoPartida, this.pontoChegada);
        }
        return "";
    }

    /**
     * Método que altera a visitação de todos os vértices para false
     */
    public void alterarVisitacao() {
        Node auxiliar = vertices.getPrimeiro();
        while (auxiliar != null) { //Enquanto não for o fim da lista
            auxiliar.getConteudo().setVisitado(false); //altera a visitação
            auxiliar = auxiliar.getNext();
        }
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
    
   /**
     * Método que calcula o menor caminho entre o estacionamento ao ponto de
     * coleta e do ponto de coleta até o banco
     *
     * @param pontoColeta - o ponto de coleta
     * @param banco - o banco
     * @return String - a rota
     */
    public String calcularRota(String pontoColeta, String banco) {
            //Salva os pontos 
            this.pontoChegada = banco;
            this.pontoPartida = pontoColeta;

            vertices.procurarNo(pontoColeta).setVisitado(true);
            int verticesVisitados = 1;

            ArestaList caminho = new ArestaList();
            this.relacionarVertices(caminho); //Salva o caminho do ponto de coleta para todos os outros vertices     
            
            Cidade verticeAtual;
            int pesoAtual;
            int menorDistancia = caminho.get(getMinimo(caminho)).getConteudo().getTempoVoo();
            /*Enquanto não visitar todos os vertices e a menor distância for diferente de 1000, procura o menor
            caminho para todos os vertices, independente de serem adjacentes ao ponto de coleta. */
            while (verticesVisitados < vertices.size() && menorDistancia != 1000) {
                verticesVisitados++;
                int indexMin = getMinimo(caminho);//posicao do elemento com menor peso 
                menorDistancia = caminho.get(indexMin).getConteudo().getTempoVoo(); //peso do menor elemento
                if (menorDistancia != 1000) {
                    //Atualiza a referência do vertice atual e do peso do mesmo, e altera para visitado
                    verticeAtual = vertices.get(indexMin).getConteudo(); //Salva o vertice atual
                    pesoAtual = caminho.get(indexMin).getConteudo().getTempoVoo(); //Salva o peso da aresta com o vertice atual
                    vertices.get(indexMin).getConteudo().setVisitado(true); //Altera o vertice para visitado
                    ajustarVertices(verticeAtual, pesoAtual, caminho); //Atualiza os valores da lista de caminhos
                }
            }
            this.alterarVisitacao(); //Altera para não visitados todos os vertices
            this.salvarRota(caminho); //salva a rota
        return rota;
    }

    /**
     * Método que analisa todas as adjacencias do ponto de coleta com os outros
     * vertices. Salvando os caminhos existentes entre o ponto de coleta e os
     * outros vertices. Se o vertice não for adjacente, a aresta recebe o peso
     * 1000.
     *
     * @param caminho - lista de caminho
     */
    public void relacionarVertices(ArestaList caminho) {
        Cidade verticeDestino = vertices.procurarNo(pontoPartida);
        Node auxiliar = vertices.getPrimeiro();
        Trecho aresta;
        while (auxiliar != null) { //Enquanto não for o fim da lista
            //procura se o vertice atual é adjacente ao ponto de coleta
            int posicao = auxiliar.getConteudo().getAdjacencias().getPosicao(pontoPartida);
            if (posicao == -1) { //Se não for adjacente
                //a aresta é salva com o valor 1000, o que indica que não há caminho do ponto de coleta para o vertice
                aresta = new Trecho(verticeDestino, 1000,"semAdjacencia");
            } else { //Se forem adjacentes, salva a aresta contendo o ponto de coleta como destino e o peso entre eles
                aresta = new Trecho(verticeDestino, auxiliar.getConteudo().getAdjacencias().get(posicao).getConteudo().getTempoVoo(),auxiliar.getConteudo().getAdjacencias().get(posicao).getConteudo().getCompanhia());
            }
            caminho.add(aresta); //Adiciona a aresta
            auxiliar = auxiliar.getNext(); //Aponta para o próximo nó da lista de vertices
        }
    }

    /**
     * Método que procura o menor peso da lista de caminho
     *
     * @param caminho - lista com o caminho
     * @return posicao - posicao do objeto com menor peso
     */
    private int getMinimo(ArestaList caminho) {
        int valorMinimo = 1000;
        int posicao = 0;
        for (int i = 0; i < vertices.size(); i++) { //percorre toda lista
            //Se o nó atual for menor, salva o peso e a posição
            if (!vertices.get(i).getConteudo().isVisitado() && caminho.get(i).getConteudo().getTempoVoo() < valorMinimo) {
                valorMinimo = caminho.get(i).getConteudo().getTempoVoo();
                posicao = i;
            }
        }
        return posicao;
    }

    /**
     * Método que atualiza as distâncias mais curtas para todos os vertices
     * conhecidos atualmente
     *
     * @param verticeAtual - vertice atual
     * @param pesoAtual - peso para chegar até o vertice atual
     * @param caminho - lista de caminho
     */
    private void ajustarVertices(Cidade verticeAtual, int pesoAtual, ArestaList caminho) {
        int coluna = 0;
        int peso = 0;
        while (coluna < vertices.size()) { //Enquanto não percorrer a lista toda
            if (!vertices.get(coluna).getConteudo().isVisitado()) { //Se o vertice não for visitado
                //procura a posição do vertice atual(Se esse vertice é adjacente ao atual)
                int posicao = vertices.get(coluna).getConteudo().getAdjacencias().getPosicao(verticeAtual.getNome());
                if (posicao != -1) { //Se forem adjacentes, salva o peso entre ele e o vertice atual 
                    peso = vertices.get(coluna).getConteudo().getAdjacencias().get(posicao).getConteudo().getTempoVoo();
                } else {
                    peso = 1000;
                }
                int pesoAtualizado = peso + pesoAtual; //soma o peso para chegar até ele partindo do ponto de coleta
                int pesoAntigo = caminho.get(coluna).getConteudo().getTempoVoo();
                if (pesoAtualizado < pesoAntigo) { //Se o peso atualizado for menor do que o anterior
                    caminho.get(coluna).getConteudo().setDestino(verticeAtual); //altera o ponto de destino até ele
                    caminho.get(coluna).getConteudo().setTempoVoo(pesoAtualizado); //altera o peso do caminho
                }
            }
            coluna++; //passa para o próximo vertice
        }
    }

      /**
     * Método que salva a rota e o tempo da rota
     *
     * @param caminho - a lista com os caminho para todos os vertices
     */
    private void salvarRota(ArestaList caminho) {
        rotaPartidaADestino(caminho);
    }
      /**
     * Método que salva a rota do ponto de coleta até o banco
     *
     * @param tempoRota - tempo da rota até o momento
     * @param caminho - lista de caminhos
     */
    private void rotaPartidaADestino(ArestaList caminho) {
        int posicao = vertices.getPosicao(pontoChegada);
        No auxiliar = caminho.get(posicao);
        int tempoRota = caminho.get(posicao).getConteudo().getTempoVoo(); //soma o tempo para chegar no ponto de chegada
        rota= pontoPartida;
        String invertida = "->" + pontoChegada;
        //Enquanto o vertice destino não for o ponto de coleta e o grafo não for desconexo
        while (auxiliar.getConteudo().getDestino().getNome().compareToIgnoreCase(pontoPartida) != 0 && auxiliar.getConteudo().getTempoVoo() != 1000) {
            String stringAuxiliar = "->" + auxiliar.getConteudo().getDestino().getNome(); //escreve o vertice destino
            invertida = stringAuxiliar.concat(invertida);
            //Aponta para o vertice destino na lista
            posicao = vertices.getPosicao(auxiliar.getConteudo().getDestino().getNome());
            auxiliar = caminho.get(posicao);
        }
        if (auxiliar.getConteudo().getTempoVoo() != 1000) //Se é possível chegar no ponto de coleta, escreve o mesmo
        {
            rota = rota.concat(invertida /*+ "\nO tempo do trajeto é de " + tempoRota + " horas"*/);
        } else {
            rota = "Não existem caminhos para " + pontoPartida;
        }
    }
         
    /**
     * Método que remove todos os vertices do grafo
     */
    public void removerTodosVertice() {
        while (vertices.getPrimeiro() != null) {
            vertices.setPrimeiro(vertices.getPrimeiro().getNext());
        }
    }
    
   
    public String listarRotas(){
        String texto="";
        int count=0;
        Cidade origemAux;
        for(int i=0; i<vertices.size();i++){
            origemAux = vertices.get(i).getConteudo();
            for(int j=0; j<origemAux.getAdjacencias().size(); j++){
                count++;
                texto+=(count+". "+origemAux.getNome()+"->"+origemAux.getAdjacencias().get(j).getConteudo().getDestino().getNome()+
                        " - "+ origemAux.getAdjacencias().get(j).getConteudo().getCompanhia()+"\n");
            }
        }
        return texto;
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