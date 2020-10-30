/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import model.Passageiro;
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
        Passageiro passageiro1= new Passageiro(semaforo,1,vetor1);
        
        int vetor2[] = new int[] {15,7,12};
        int vetor3[] = new int[] {7,12,0,15};
        int vetor4[] = new int[] {5,6,1};
        int vetor5[] = new int[] {6,1,5};
        int vetor6[] = new int[] {1,5,6};
        
       // Passageiro passageiro1= new Passageiro(semaforo,1,vetor1);
        Passageiro passageiro2= new Passageiro(semaforo,2,vetor2);
        Passageiro passageiro3= new Passageiro(semaforo,3,vetor3);
        Passageiro passageiro4= new Passageiro(semaforo,4,vetor4);
        Passageiro passageiro5= new Passageiro(semaforo,5,vetor5);
        Passageiro passageiro6= new Passageiro(semaforo,6,vetor6);
    
        
        passageiro1.start();
        passageiro2.start();
        passageiro3.start();
        passageiro4.start();
        passageiro5.start();
        passageiro6.start();
        
        passageiro1.join();
        passageiro2.join();
        passageiro3.join();
        passageiro4.join();
        passageiro5.join();
        passageiro6.join();
       
    }
}
