/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: Estéfane Carmo de Souza
 * Data: 24-08-2019
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
import util.LinkedList;
import util.List;
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
 */
public class Grafo {

    private List vertices;
    private LinkedList caminho;

    public Grafo() {
        vertices = new List();
        caminho = new LinkedList();
    }

    public List getVertices() {
        return vertices;
    }

    public LinkedList getCaminho() {
        return caminho;
    }

    public void addVertice(String nome, String tipo) {
        Vertice vertice = new Vertice(nome, tipo);
        vertices.add(vertice);
    }

    public void addAresta(String nomeV1, String nomeV2, int peso) {
        Vertice v1 = vertices.procurarNo(nomeV1);
        Vertice v2 = vertices.procurarNo(nomeV2);

        Aresta aresta1 = new Aresta(v1, peso);
        Aresta aresta2 = new Aresta(v2, peso);

        vertices.procurarNo(nomeV2).getAdjacencias().add(aresta1);
        vertices.procurarNo(nomeV1).getAdjacencias().add(aresta2);
    }

    public void removerVertice(String nome) {
        Vertice removido = vertices.remove(nome);
        No auxiliar = removido.getAdjacencias().getPrimeiro();
        while (auxiliar != null) {
            auxiliar.getConteudo().getDestino().getAdjacencias().remove(removido.getNome());
            auxiliar = auxiliar.getNext();
        }
    }

    public void removerAresta(String nome1, String nome2) {
        //removendo a aresta do primeiro vertice
        int posicaoV1 = vertices.getPosicao(nome1);
        vertices.get(posicaoV1).getConteudo().getAdjacencias().remove(nome2);
        //removendo a aresta do segundo nó
        int posicaoV2 = vertices.getPosicao(nome2);
        vertices.get(posicaoV2).getConteudo().getAdjacencias().remove(nome1);
    }

    public void imprimir() {
        Node aux = vertices.getPrimeiro();
        int i = 1;
        while (aux != null) {
            System.out.print(i + " " + aux.getConteudo().getNome());
            imprimirArestas(aux.getConteudo());
            aux = aux.getNext();
            System.out.print("\n\n");
            i++;
        }
    }

    public void imprimirArestas(Vertice vertice) {
        No aux = vertice.getAdjacencias().getPrimeiro();
        int i = 0;
        while (aux != null) {
            System.out.print("  " + aux.getConteudo().getDestino().getNome() + "" + aux.getConteudo().getPeso());
            aux = aux.getNext();
            i++;
        }
    }

    public void alterarEstacionamento(String novaLocalizacao) {
        //Procura o estacionamento
        Node auxiliar = vertices.getPrimeiro();

        while (auxiliar.getConteudo().getTipo().compareToIgnoreCase("estacionamento") != 0 && auxiliar != null) {
            auxiliar = auxiliar.getNext();
        }
        //Se encontrar o estacionamento
        if (auxiliar != null) {
            String nome = auxiliar.getConteudo().getNome();
            String tipo = auxiliar.getConteudo().getTipo();

            Vertice novo2 = vertices.procurarNo(novaLocalizacao);

            auxiliar.getConteudo().setNome(novo2.getNome());
            auxiliar.getConteudo().setTipo(novo2.getTipo());

            vertices.procurarNo(novaLocalizacao).setNome(nome);
            vertices.procurarNo(novaLocalizacao).setTipo(tipo);
        }
    }

    public void importarArquivo(String nome_arq) throws IOException {
        BufferedReader ler = null;
        int contador = 0;
        try {
            ler = new BufferedReader(new FileReader(nome_arq));
            contador = Integer.parseInt(ler.readLine());
            while (contador > 0) {
                String linha = ler.readLine();//lê a linha
                String[] parts = linha.split(" "); //divide a linha em duas partes
                String nome = parts[0]; //primeira parte contém o nome da imagem
                String tipo = parts[1]; //converte a string com números para double
                this.addVertice(nome, tipo);
                contador--;//incrementa o contador de imagens importadas
            }
            String vertice1;
            String linha = ler.readLine(); //lê a linha e armazena na string
            while (linha != null) { //enquanto não for o fim do arquivo
                vertice1 = linha; //armazena os dados da linha anterior
                linha = ler.readLine();//lê a proxima linha
                String[] adjacencia = linha.split(" ");
                for (int i = 0; i < adjacencia.length; i = i + 2) {
                    String vertice2 = adjacencia[i];
                    int peso = Integer.parseInt(adjacencia[i + 1]);
                    this.addAresta(vertice1, vertice2, peso);
                }
                linha = ler.readLine();
            }
        } catch (FileNotFoundException fnfe) {
            java.lang.System.out.println("Arquivo não encontrado. Tente novamente");
        } finally {
            if (ler != null) {
                ler.close(); //fecha o arquivo
            }
        }
    }

    public void imprimirVertices() {
        Node aux = vertices.getPrimeiro();
        int i = 1;
        while (aux != null) {
            System.out.println(i + " " + aux.getConteudo().getNome() + " Tipo: " + aux.getConteudo().getTipo());
            aux = aux.getNext();
            i++;
        }
    }

    public void buscarCaminho(String vertice1) {
        Vertice partida = vertices.procurarNo(vertice1);
        vertices.procurarNo(vertice1).setVisitado(true);
    }

    public void caminho(String vertice) {
        vertices.procurarNo(vertice).setVisitado(true);
        int nTree = 1;
        Vertice v = vertices.procurarNo(vertice);
        No aux = v.getAdjacencias().getPrimeiro();
        while (aux != null) {
            Aresta aresta = new Aresta(v, aux.getConteudo().getPeso());
            caminho.add(aresta);
        }
        //
        while (nTree < vertices.size()) {
            int indexMin = getMin();//posição do vetor
            int minDist = caminho.get(indexMin).getPeso();
            caminho.get(indexMin).
            
            currentVert = indexMin;
            startToCurrent = path[indexMin].getPeso();

            vertices[currentVert].setVisitado(true);
            nTree++;
            ajustarPath();
        }
        //IMPRIME
        for (int i = 1; i < tamAtual; i++) {
            System.out.print(vertices[i].getNome() + ":");
            if (path[i].getPeso() == infinito) {
                System.out.print("inf");
            } else {
                System.out.print(" " + path[i].getPeso() + "(" + vertices[path[i].getDestino()].getNome() + ")");
            }
            System.out.println("");
        }
        nTree = 0;
        //Imprime o trajeto
    }

    public int getMin() {
        int posicao = 0;
        int posicaoMenor=0;
        int menor= caminho.getPrimeiro().getConteudo().getPeso();
        No caminhoAux= caminho.getPrimeiro();
        Node verticeAux=  vertices.getPrimeiro();
        while(verticeAux!=null){
            if (!verticeAux.getConteudo().isVisitado() && caminhoAux.getConteudo().getPeso()<menor) {
              menor= caminho.getPrimeiro().getConteudo().getPeso();
               posicaoMenor= posicao;
            }
            posicao++;
            caminhoAux=  caminhoAux.getNext();
            verticeAux=  verticeAux.getNext();
        }
        return posicaoMenor;
    }

    public void ajustarPath() {
        int coluna = 1;
        while (coluna < tamAtual) {
            if (vertices[coluna].isVisitado()) {
                coluna++;
            }
            int current = matriz[currentVert][coluna];
            int start = current + startToCurrent;
            int sPathDist = path[coluna].getPeso();
            if (start < sPathDist) {
                path[coluna].setDestino(currentVert);
                path[coluna].setPeso(start);
            }
            coluna++;
        }
    }

}
