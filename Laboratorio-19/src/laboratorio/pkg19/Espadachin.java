/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio.pkg19;

import java.util.Random;
public class Espadachin extends Soldado {
    
    private int longitudEspada;
    public Espadachin(String nombre) {
        super(nombre, new Random().nextInt(2) + 3);
        this.longitudEspada = new Random().nextInt(3) + 1;
    }
    public int getLongitudEspada() {
        return longitudEspada;
    }
    public void crearMuroEscudos() {
    }
}