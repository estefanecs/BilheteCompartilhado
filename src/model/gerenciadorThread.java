/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;


public class gerenciadorThread {
    public static List<ComprasPassagens> filaEspera = new ArrayList<>();
    public ComprasPassagens compras;
    
    public synchronized void acordar() {
       compras= filaEspera.get(0);
        try {
                synchronized (compras) {
                    compras.notifyAll();
                }
            } catch (Exception e) {
            }
    }
}
