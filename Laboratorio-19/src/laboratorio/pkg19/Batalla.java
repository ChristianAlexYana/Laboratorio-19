/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio.pkg19;
import java.util.Random;
public class Batalla {
    Ejercito ejercitoA;
    Ejercito ejercitoB;
    public Batalla(Ejercito ejercitoA, Ejercito ejercitoB){
        this.ejercitoA = ejercitoA;
        this.ejercitoB = ejercitoB;       
    }
    public void mostrarResumenBatalla() {
        mostrarTablero();
        mostrarDatosSoldados();
        mostrarMayorVida();
        mostrarPromedioVida();
        mostrarOrdenadosPorCreacion();
        mostrarOrdenadosPorVida();
        determinarGanador();
    }
    public void mostrarTablero() {
    String[][] tablero = new String[10][10];
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
            tablero[i][j] = "__";
        }
    }
    Random random = new Random();
    for (Soldado soldado : ejercitoA.getSoldados()) {
        int x, y;
        do {
            x = random.nextInt(10);
            y = random.nextInt(10);
        } while (!tablero[x][y].equals("__"));
        tablero[x][y] = soldado.getNombre();
    }
    for (Soldado soldado : ejercitoB.getSoldados()) {
        int x, y;
        do {
            x = random.nextInt(10);
            y = random.nextInt(10);
        } while (!tablero[x][y].equals("__"));
        tablero[x][y] = soldado.getNombre();
    }
    System.out.println("\nTablero:");
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
            System.out.printf("%-8s", tablero[i][j]);
        }
        System.out.println();
    }
    System.out.println();
}    
    public void mostrarDatosSoldados() {
        System.out.println("Datos de Ejercito " + ejercitoA.nombre);
        mostrarSoldadosPorTipo(ejercitoA);
        System.out.println("\nDatos de Ejercito " + ejercitoB.nombre);
        mostrarSoldadosPorTipo(ejercitoB);
    }
    private void mostrarSoldadosPorTipo(Ejercito ejercito) {
        for (Soldado s : ejercito.getSoldados()) {
            if (s instanceof Espadachin) {
                System.out.println("Espadachin: " + s.getNombre() + " Vida: " + s.getVida());
            }
        }
        for (Soldado s : ejercito.getSoldados()) {
            if (s instanceof Arquero) {
                System.out.println("Arquero: " + s.getNombre() + " Vida: " + s.getVida());
            }
        }
        for (Soldado s : ejercito.getSoldados()) {
            if (s instanceof Caballero) {
                System.out.println("Caballero: " + s.getNombre() + " Vida: " + s.getVida());
            }
        }
    }
    private void mostrarMayorVida() {
        Soldado mayorVidaA = obtenerMayorVida(ejercitoA);
        Soldado mayorVidaB = obtenerMayorVida(ejercitoB);

        System.out.println("\nSoldado con mayor vida de Ejercito A: " + mayorVidaA.getNombre() + " Vida: " + mayorVidaA.getVida());
        System.out.println("Soldado con mayor vida de Ejercito B: " + mayorVidaB.getNombre() + " Vida: " + mayorVidaB.getVida());
    }
    private Soldado obtenerMayorVida(Ejercito ejercito) {
        Soldado mayor = ejercito.getSoldados().get(0);
        for (Soldado s : ejercito.getSoldados()) {
            if (s.getVida() > mayor.getVida()) {
                mayor = s;
            }
        }
        return mayor;
    }
    private void mostrarPromedioVida() {
        System.out.printf("\nPromedio de vida de Ejercito A: %.2f\n", ejercitoA.promedioVida());
        System.out.printf("Promedio de vida de Ejercito B: %.2f\n", ejercitoB.promedioVida());
    }
    private void mostrarOrdenadosPorCreacion() {
        System.out.println("\nOrden de creacion Ejercito A:");
        for (Soldado s : ejercitoA.getSoldados()) {
            System.out.println(s.getNombre() + " Vida: " + s.getVida());
        }
        System.out.println("Orden de creacion Ejercito B:");
        for (Soldado s : ejercitoB.getSoldados()) {
            System.out.println(s.getNombre() + " Vida: " + s.getVida());
        }
    }
    private void mostrarOrdenadosPorVida() {
        System.out.println("\nSoldados de Ejercito A ordenados por vida:");
        ordenarPorVida(ejercitoA);
        for (Soldado s : ejercitoA.getSoldados()) {
            System.out.println(s.getNombre() + " Vida: " + s.getVida());
        }        
        System.out.println("Soldados de Ejercito B ordenados por vida:");
        ordenarPorVida(ejercitoB);
        for (Soldado s : ejercitoB.getSoldados()) {
            System.out.println(s.getNombre() + " Vida: " + s.getVida());
        }
    }
    private void ordenarPorVida(Ejercito ejercito) {
        for (int i = 0; i < ejercito.getSoldados().size() - 1; i++) {
            for (int j = 0; j < ejercito.getSoldados().size() - i - 1; j++) {
                if (ejercito.getSoldados().get(j).getVida() < ejercito.getSoldados().get(j + 1).getVida()) {
                    Soldado temp = ejercito.getSoldados().get(j);
                    ejercito.getSoldados().set(j, ejercito.getSoldados().get(j + 1));
                    ejercito.getSoldados().set(j + 1, temp);
                }
            }
        }
    }
    private void determinarGanador() {
        String ganador = ejercitoA.promedioVida() > ejercitoB.promedioVida() ? ejercitoA.nombre : ejercitoB.nombre;
        System.out.println("\nEl ganador de la batalla es: " + ganador);
    }
}

