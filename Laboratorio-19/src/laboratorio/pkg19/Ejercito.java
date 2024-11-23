/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio.pkg19;

import java.util.ArrayList;
import java.util.Random;
public class Ejercito {
    
    String nombre;
    ArrayList<Soldado> soldados;
    public Ejercito(String nombre) {
        this.nombre = nombre;
        this.soldados = new ArrayList<>();
        generarSoldados();
    }
    private void generarSoldados() {
        Random random = new Random();
        int cantidadSoldados = random.nextInt(10) + 1;
        for (int i = 0; i < cantidadSoldados; i++) {
            int tipoSoldado = random.nextInt(3);
            Soldado soldado;
            switch (tipoSoldado) {
                case 0 : soldado = new Espadachin(nombre + ":E" + (i + 1));break;
                case 1 :  soldado = new Arquero(nombre + ":A" + (i + 1));break;
                default : soldado = new Caballero(nombre + ":C" + (i + 1));break;
            }
            soldados.add(soldado);
        }
    }
    public ArrayList<Soldado> getSoldados() {
        return soldados;
    }
    public double promedioVida() {
        return soldados.stream().mapToInt(Soldado::getVida).average().orElse(0);
    }

    public Soldado getSoldadoMayorVida() {
        return soldados.stream().max((s1, s2) -> Integer.compare(s1.getVida(), s2.getVida())).orElse(null);
    }
}

