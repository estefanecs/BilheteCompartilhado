/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;

/**
 *
 * @author casa
 */
public class Grafos2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       Grafo gr= new Grafo();
       gr.importarArquivo("arquivoGrafo.txt");
       gr.imprimirVertices();
       System.out.println("--------------------------------------------------------------------------------");
       gr.imprimir();

       /*gr.addVertice("A","Banco");
       gr.addVertice("B","Banco");
       gr.addVertice("C","Banco");
       gr.addVertice("D","Banco");
       gr.addVertice("E","Banco");
       gr.addVertice("F","Banco");
       
       gr.imprimirVertices();
       
       gr.addAresta("A", "B", 6);
       gr.addAresta("A", "D", 4);
       
       gr.addAresta("B", "C", 10);
       gr.addAresta("B", "D",7);
       gr.addAresta("B", "E", 7);
       
       gr.addAresta("C", "D",8);
       gr.addAresta("C", "F", 6);
       gr.addAresta("C","E",5);
       
       gr.addAresta("F", "E",7);
       gr.addAresta("D", "E",12);
       
       gr.imprimir();*/

       
      
    }
    
}
