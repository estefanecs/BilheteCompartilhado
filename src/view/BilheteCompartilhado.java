/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import model.ComprasPassagens;
import model.Grafo;

/**
 *
 * @author mjlsj
 */
public class BilheteCompartilhado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException{
        Grafo grafo = Grafo.getInstance();
        grafo.importarArquivo("Cidades.txt");
        Semaphore semaforo = new Semaphore(1);
        int vetor1[] = new int[] {0,15,7};
        int vetor2[] = new int[] {15,7,12};
        int vetor3[] = new int[] {7,12,0,15};
        
        ComprasPassagens cp1= new ComprasPassagens(semaforo,1,vetor1);
        ComprasPassagens cp2= new ComprasPassagens(semaforo,2,vetor2);
        ComprasPassagens cp3= new ComprasPassagens(semaforo,3,vetor3);
    
        cp1.start();
        cp2.start();
        cp3.start();
     
        /*cp1.join();
        cp2.join();
        cp3.join();
       */
    }
}
