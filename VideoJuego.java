import java.util.*;
class Soldado {
    private String nombre;
    private int puntosVida;
    private int fila;
    private int columna;
    public Soldado(String nombre, int puntosVida, int fila, int columna) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.fila = fila;
        this.columna = columna;
    }
    public String getNombre() {
        return nombre;
    }
    public int getPuntosVida() {
        return puntosVida;
    }
    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }
    public int getFila() {
        return fila;
    }
    public void setFila(int fila) {
        this.fila = fila;
    }
    public int getColumna() {
        return columna;
    }
    public void setColumna(int columna) {
        this.columna = columna;
    }
    @Override
    public String toString() {
        return nombre + " Vida: " + puntosVida + ", Posicion: " + fila + ", " + columna;
    }
}
class Espadachin extends Soldado {
    private int longitudEspada;

    public Espadachin(String nombre, int puntosVida, int fila, int columna, int longitudEspada) {
        super(nombre, puntosVida, fila, columna);
        this.longitudEspada = longitudEspada;
    }

    public int getLongitudEspada() {
        return longitudEspada;
    }

    @Override
    public String toString() {
        return super.toString() + ", Longitud de espada: " + longitudEspada;
    }
}
class Arquero extends Soldado {
    private int flechas;

    public Arquero(String nombre, int puntosVida, int fila, int columna, int flechas) {
        super(nombre, puntosVida, fila, columna);
        this.flechas = flechas;
    }
    @Override
    public String toString() {
        return super.toString() + ", Flechas disponibles: " + flechas;
    }
}
class Caballero extends Soldado {
    private String armaActual;
    private boolean montado;

    public Caballero(String nombre, int puntosVida, int fila, int columna) {
        super(nombre, puntosVida, fila, columna);
        this.armaActual = "Espada";
        this.montado = false;
    }
    @Override
    public String toString() {
        return super.toString() + ", Arma: " + armaActual + ", Montado: " + montado;
    }
}
>>>>>>> ChristianYana 
class Ejercito {
    private ArrayList<Soldado> soldados;
    private String nombreEjercito;
    private String[][] tablero;

    public Ejercito(String nombreEjercito, String[][] tablero) {
        this.nombreEjercito = nombreEjercito;
        this.soldados = new ArrayList<>();
        this.tablero = tablero;
    }

    public void crearSoldado(int tipoSoldado, int i) {
        Random rand = new Random();
        String nombre = "";
        int vida = 0, fila = rand.nextInt(10), columna = rand.nextInt(10);
        switch (tipoSoldado) {
            case 0: // Espadachin
                nombre = "E" + i + "X" + (nombreEjercito.equals("Ejército 1") ? "1" : "2");
                vida = rand.nextInt(2) + 3;
                agregarSoldado(new Espadachin(nombre, vida, fila, columna, rand.nextInt(3) + 2));
                break;
            case 1: // Arquero
                nombre = "A" + i + "X" + (nombreEjercito.equals("Ejército 1") ? "1" : "2");
                vida = rand.nextInt(3) + 1;
                agregarSoldado(new Arquero(nombre, vida, fila, columna, rand.nextInt(10) + 5));
                break;
            case 2: // Caballero
                nombre = "C" + i + "X" + (nombreEjercito.equals("Ejército 1") ? "1" : "2");
                vida = rand.nextInt(3) + 3;
                agregarSoldado(new Caballero(nombre, vida, fila, columna));
                break;
        }
    }

    public void agregarSoldado(Soldado soldado) {
        soldados.add(soldado);
        tablero[soldado.getFila()][soldado.getColumna()] = soldado.getNombre();
    }

    public ArrayList<Soldado> getSoldados() {
        return soldados;
    }

    public String getNombreEjercito() {
        return nombreEjercito;
    }

    public boolean tieneSoldados() {
        return !soldados.isEmpty();
    }
}
public class VideoJuego {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        boolean seguirJugando = true;
        while (seguirJugando) {
            String[][] tablero = new String[10][10];
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    tablero[i][j] = "__";
                }
            }
            Ejercito ejercito1 = new Ejercito("Ejército 1", tablero);
            Ejercito ejercito2 = new Ejercito("Ejército 2", tablero);
            for (int i = 0; i < rand.nextInt(10) + 1; i++) {
                int tipoSoldado = rand.nextInt(3);
                ejercito1.crearSoldado(tipoSoldado, i);
            }
            for (int i = 0; i < rand.nextInt(10) + 1; i++) {
                int tipoSoldado = rand.nextInt(3);
                ejercito2.crearSoldado(tipoSoldado, i);
            }
            while (ejercito1.tieneSoldados() && ejercito2.tieneSoldados()) { //inicia juego
                mostrarTablero(tablero);
                System.out.println("\nTurno del " + ejercito1.getNombreEjercito() + ":");
                moverSoldado(ejercito1, ejercito2, tablero, scanner);
                mostrarTablero(tablero);
                if (!ejercito2.tieneSoldados()) break;

                System.out.println("\nTurno del " + ejercito2.getNombreEjercito() + ":");
                moverSoldado(ejercito2, ejercito1, tablero, scanner);
                mostrarTablero(tablero);
            }

            if (!ejercito1.tieneSoldados()) {
                System.out.println("¡El Ejército 2 ha ganado!");
            } else if (!ejercito2.tieneSoldados()) {
                System.out.println("¡El Ejército 1 ha ganado!");
            }

            System.out.print("\n¿Deseas jugar otra vez? (s/n): ");
            String respuesta = scanner.next();
            if (respuesta.equalsIgnoreCase("n")) {
                seguirJugando = false;
            }
        }
    }

    public static void mostrarTablero(String[][] tablero) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(tablero[i][j] + "|");
            }
            System.out.println();
        }
    }

    public static void moverSoldado(Ejercito ejercito, Ejercito enemigo, String[][] tablero, Scanner scanner) {
        while (true) {
            System.out.println("Ingresa las coordenadas (fila y columna) del soldado a mover (por ejemplo: 2 3): ");
            int fila = scanner.nextInt();
            int columna = scanner.nextInt();
            Soldado soldado = null;
            for (Soldado s : ejercito.getSoldados()) {   // buscar soldado por coordenadas
                if (s.getFila() == fila && s.getColumna() == columna) {
                    soldado = s;
                    break;
                }
            }
            if (soldado != null) {
                System.out.println("Ingresa las nuevas coordenadas (fila y columna) para mover el soldado: ");
                int nuevaFila = scanner.nextInt();
                int nuevaColumna = scanner.nextInt();
                mover(soldado, tablero, nuevaFila, nuevaColumna, ejercito, enemigo);
                break;
            } else {
                System.out.println("No hay ningún soldado en esa posición. Intenta de nuevo.");
            }
        }
    }

    public static void mover(Soldado soldado, String[][] tablero, int nuevaFila, int nuevaColumna, Ejercito ejercito, Ejercito enemigo) {
        if (nuevaFila < 0 || nuevaFila >= 10 || nuevaColumna < 0 || nuevaColumna >= 10) {
            System.out.println("Movimiento fuera de los límites del tablero.");
            return;
        }

        if (!tablero[nuevaFila][nuevaColumna].equals("__")) {
            Soldado enemigoSoldado = buscarSoldado(tablero[nuevaFila][nuevaColumna], ejercito, enemigo);
            if (enemigoSoldado != null) {
                realizarBatalla(soldado, enemigoSoldado, tablero, nuevaFila, nuevaColumna, ejercito, enemigo);
            }
        } else {
            tablero[soldado.getFila()][soldado.getColumna()] = "__";//mover al soldado si casilla esta vacia
            soldado.setFila(nuevaFila);  // se actualiza fila y columna
            soldado.setColumna(nuevaColumna);
            tablero[nuevaFila][nuevaColumna] = soldado.getNombre();
        }
    }
    public static Soldado buscarSoldado(String nombre, Ejercito ejercito1, Ejercito ejercito2) {
        for (Soldado s : ejercito1.getSoldados()) {
            if (s.getNombre().equals(nombre)) {
                return s;
            }
        }
        for (Soldado s : ejercito2.getSoldados()) {
            if (s.getNombre().equals(nombre)) {
                return s;
            }
        }
        return null;
    }
    public static void realizarBatalla(Soldado soldado1, Soldado soldado2, String[][] tablero, int fila, int columna, Ejercito ejercito1, Ejercito ejercito2) {
        int vidaTotal = soldado1.getPuntosVida() + soldado2.getPuntosVida();
        double probabilidad1 = (double) soldado1.getPuntosVida() / vidaTotal * 100;
        double probabilidad2 = (double) soldado2.getPuntosVida() / vidaTotal * 100;
        Random rand = new Random();
        double random = rand.nextDouble() * 100;

        if (random <= probabilidad1) {
            System.out.println(soldado1.getNombre() + " gana la batalla.");
            soldado1.setPuntosVida(soldado1.getPuntosVida() + 1);  //actualiza puntos de vida +1
            tablero[soldado2.getFila()][soldado2.getColumna()] = "__"; // se elimina al soldado del tablero
            ejercito2.getSoldados().remove(soldado2);       // aqui del ejercito
        } else {
            System.out.println(soldado2.getNombre() + " gana la batalla.");
            soldado2.setPuntosVida(soldado2.getPuntosVida() + 1);
            tablero[soldado1.getFila()][soldado1.getColumna()] = "__";
            ejercito1.getSoldados().remove(soldado1);
        }
    }
}