/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio.pkg19;

import java.util.ArrayList;
import java.util.Random;
public class Ejercito {
    String nombre;
    private ArrayList<Soldado> soldados;

    public Ejercito(String nombre, int numeroEjercito) {
        this.nombre = nombre;
        this.soldados = new ArrayList<>();
        generarSoldados(numeroEjercito);
    }

    private void generarSoldados(int numeroEjercito) {
        Random random = new Random();
        int cantidadSoldados = random.nextInt(10) + 5;
        int contadorArquero = 1, contadorEspadachin = 1, contadorCaballero = 1;

        for (int i = 0; i < cantidadSoldados; i++) {
            int tipoSoldado = random.nextInt(3);
            Soldado soldado;

            switch (tipoSoldado) {
                case 0:
                    soldado = new Espadachin(numeroEjercito + ":E" + contadorEspadachin++);
                    break;
                case 1:
                    soldado = new Arquero(numeroEjercito + ":A" + contadorArquero++);
                    break;
                default:
                    soldado = new Caballero(numeroEjercito + ":C" + contadorCaballero++);
                    break;
            }

            soldados.add(soldado);
        }
    }

    public ArrayList<Soldado> getSoldados() {
        return soldados;
    }
}
