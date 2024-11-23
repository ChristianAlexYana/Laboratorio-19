/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio.pkg19;

import java.util.Random;
public class Arquero extends Soldado {
   private int flechas;

    public Arquero(String nombre) {
        super(nombre, new Random().nextInt(3) + 1); 
        this.flechas = new Random().nextInt(10) + 10; 
    }
    public int getFlechas() {
        return flechas;
    }
    public void dispararFlecha() {
        if (flechas > 0) {
            flechas--;  
        }
    }
}