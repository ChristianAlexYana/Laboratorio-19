/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio.pkg19;

import java.util.Random;
public class Caballero extends Soldado {
    private String arma;
    private boolean montado;

    public Caballero(String nombre) {
        super(nombre, new Random().nextInt(3) + 3);
        this.arma = "espada";
        this.montado = false;
    }
    public String getArma() {
        return arma;
    }
    public boolean estaMontado() {
        return montado;
    }
    public void alternarArma() {
        arma = arma.equals("espada") ? "lanza" : "espada";
    }
    public void montar() {
        if (!montado) {
            montado = true;
            arma = "lanza";
        }
    }
    public void desmontar() {
        if (montado) {
            montado = false;
            arma = "espada";
        }
    }
    public void envestir() {
        int ataques = montado ? 3 : 2;
        for (int i = 0; i < ataques; i++) {
        }
    }
}