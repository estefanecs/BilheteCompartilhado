/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: Estéfane Carmo de Souza
 * Data: 24-09-2019
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

import util.LinkedList;


/**
 *
 * @author casa
 */
public class Vertice {
     private String nome;
     private String tipo;
     private boolean visitado;
     private LinkedList adjacencias;

    public Vertice(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
        this.visitado = false;
        this.adjacencias = new LinkedList();
    }
     
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public LinkedList getAdjacencias() {
        return adjacencias;
    }

    public void setAdjacencias(LinkedList adjacencias) {
        this.adjacencias = adjacencias;
    }
     
}
