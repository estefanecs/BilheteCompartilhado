/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ComprasPassagens;
import model.Grafo;
import model.Cidade;
import static model.ComprasPassagens.trechos;

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
        
        ComprasPassagens cp1= new ComprasPassagens();
        ComprasPassagens cp2= new ComprasPassagens();
        ComprasPassagens cp3= new ComprasPassagens();
        ComprasPassagens cp4= new ComprasPassagens();
       
        cp1.start();
        cp2.start();
        cp3.start();
        cp4.start();
        
        
        cp1.join();
        cp2.join();
        cp3.join();
        cp4.join();
        
    }
}
