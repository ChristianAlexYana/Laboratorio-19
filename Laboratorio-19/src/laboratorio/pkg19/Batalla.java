/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio.pkg19;

import java.util.Random;
import java.util.Scanner;

public class Batalla {
    private Ejercito ejercitoA;
    private Ejercito ejercitoB;
    private String[][] tablero = new String[10][10];
    private Scanner scanner = new Scanner(System.in);

    public Batalla(Ejercito ejercitoA, Ejercito ejercitoB) {
        this.ejercitoA = ejercitoA;
        this.ejercitoB = ejercitoB;
        inicializarTablero();
        posicionarSoldados(ejercitoA);
        posicionarSoldados(ejercitoB);
    }

    private void inicializarTablero() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tablero[i][j] = "__";
            }
        }
    }

    private void posicionarSoldados(Ejercito ejercito) {
        Random random = new Random();
        for (Soldado soldado : ejercito.getSoldados()) {
            int x, y;
            do {
                x = random.nextInt(10) + 1;
                y = random.nextInt(10) + 1;
            } while (!tablero[x - 1][y - 1].equals("__"));
            tablero[x - 1][y - 1] = soldado.getNombre();
            soldado.setPosicion(x, y);
        }
    }

    public void iniciarJuego() {
        boolean turnoA = true;
        while (!ejercitoA.getSoldados().isEmpty() && !ejercitoB.getSoldados().isEmpty()) {
            mostrarTablero();
            if (turnoA) {
                System.out.println("\nTurno de " + ejercitoA.nombre);
                jugarTurno(ejercitoA, ejercitoB);
            } else {
                System.out.println("\nTurno de " + ejercitoB.nombre);
                jugarTurno(ejercitoB, ejercitoA);
            }
            turnoA = !turnoA;
        }
        determinarGanador();
    }

    private void jugarTurno(Ejercito ejercitoActual, Ejercito ejercitoEnemigo) {
        while (true) {
            System.out.println("Ingrese las coordenadas del soldado a mover (fila columna, de 1 a 10):");
            int fila = scanner.nextInt();
            int columna = scanner.nextInt();
            if (esSoldadoPropio(ejercitoActual, fila, columna)) {
                System.out.println("Ingrese la dirección de movimiento (arriba, abajo, izquierda, derecha):");
                String direccion = scanner.next();
                if (moverSoldado(ejercitoActual, ejercitoEnemigo, fila, columna, direccion)) {
                    break;
                }
            } else {
                System.out.println("Posición inválida. Intente de nuevo.");
            }
        }
    }

    private boolean esSoldadoPropio(Ejercito ejercito, int fila, int columna) {
        return ejercito.getSoldados().stream()
                .anyMatch(s -> s.getPosX() == fila && s.getPosY() == columna);
    }

    private boolean moverSoldado(Ejercito ejercitoActual, Ejercito ejercitoEnemigo, int fila, int columna, String direccion) {
        Soldado soldado = ejercitoActual.getSoldados().stream()
                .filter(s -> s.getPosX() == fila && s.getPosY() == columna)
                .findFirst()
                .orElse(null);

        if (soldado == null) return false;

        int nuevoX = fila, nuevoY = columna;
        switch (direccion.toLowerCase()) {
            case "arriba": nuevoX--; break;
            case "abajo": nuevoX++; break;
            case "izquierda": nuevoY--; break;
            case "derecha": nuevoY++; break;
            default: System.out.println("Dirección inválida."); return false;
        }

        if (nuevoX < 1 || nuevoX > 10 || nuevoY < 1 || nuevoY > 10) {
            System.out.println("Movimiento fuera de los límites. Intente de nuevo.");
            return false;
        }

        if (!tablero[nuevoX - 1][nuevoY - 1].equals("__")) {
            Soldado enemigo = buscarSoldadoEnemigo(ejercitoEnemigo, nuevoX, nuevoY);
            if (enemigo != null) {
                resolverBatalla(soldado, enemigo, ejercitoEnemigo);
            } else {
                System.out.println("Posición ocupada por un aliado. Intente de nuevo.");
                return false;
            }
        } else {
            tablero[fila - 1][columna - 1] = "__";
            tablero[nuevoX - 1][nuevoY - 1] = soldado.getNombre();
            soldado.setPosicion(nuevoX, nuevoY);
        }
        return true;
    }

    private Soldado buscarSoldadoEnemigo(Ejercito ejercito, int fila, int columna) {
        return ejercito.getSoldados().stream()
                .filter(s -> s.getPosX() == fila && s.getPosY() == columna)
                .findFirst()
                .orElse(null);
    }

    private void resolverBatalla(Soldado atacante, Soldado defensor, Ejercito ejercitoEnemigo) {
        int vidaTotal = atacante.getVida() + defensor.getVida();
        double probAtacante = atacante.getVida() * 1.0 / vidaTotal;

        System.out.printf("Batalla entre %s y %s\n", atacante.getNombre(), defensor.getNombre());
        System.out.printf("Probabilidades - %s: %.2f%%, %s: %.2f%%\n",
                atacante.getNombre(), probAtacante * 100, defensor.getNombre(), (1 - probAtacante) * 100);

        if (Math.random() < probAtacante) {
            System.out.println(atacante.getNombre() + " gana la batalla.");
            atacante.incrementarVida();
            eliminarSoldado(ejercitoEnemigo, defensor);
        } else {
            System.out.println(defensor.getNombre() + " gana la batalla.");
            defensor.incrementarVida();
            eliminarSoldado(ejercitoA, atacante);
        }
    }

    private void eliminarSoldado(Ejercito ejercito, Soldado soldado) {
        ejercito.getSoldados().removeIf(s -> s.getNombre().equals(soldado.getNombre()));
    }

    private void determinarGanador() {
        if (ejercitoA.getSoldados().isEmpty()) {
            System.out.println("¡" + ejercitoB.nombre + " gana la batalla!");
        } else {
            System.out.println("¡" + ejercitoA.nombre + " gana la batalla!");
        }
    }

    private void mostrarTablero() {
    System.out.println("\nEstado del tablero:");
    int anchoFijo = 8; // Ancho fijo uniforme para todas las casillas
    String formato = "%-" + anchoFijo + "s";

    // Imprimir encabezados de columnas (1 a 10)
    System.out.print("    ");
    for (int col = 1; col <= 10; col++) {
        System.out.printf(formato, col);
    }
    System.out.println();

    // Imprimir filas con su índice y contenido
    for (int i = 0; i < 10; i++) {
        System.out.printf("%-4d", i + 1);
        for (int j = 0; j < 10; j++) {
            if (tablero[i][j].equals("__")) {
                System.out.printf(formato, "_______"); // Casillas vacías uniformes
            } else {
                System.out.printf(formato, tablero[i][j]); // Nombres de soldados con el mismo ancho
            }
        }
        System.out.println();
    }
    System.out.println();
}
}
