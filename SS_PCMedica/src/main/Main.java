/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import conexion.control.Control;

/**
 *
 * @author Usuario
 */
public class Main {

    public static void main(String[] args) {
        
        Control control = new Control();
        
        control.getExpediente("002");
        
        while(true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}