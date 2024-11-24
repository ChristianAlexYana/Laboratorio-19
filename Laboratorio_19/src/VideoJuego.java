import java.util.*;
class Soldado {
    protected String nombre;
    protected int puntosVida;
    protected int fila;
    protected int columna;
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
    public int getFila() {
        return fila;
    }
    public int getColumna() {
        return columna;
    }
    @Override
    public String toString() {
        return nombre + " Vida: " + puntosVida + ", Posicion: " + fila + ", " + columna ;
    }
}
class Espadachin extends Soldado {
    private int longitudEspada;
    public Espadachin(String nombre, int puntosVida, int fila, int columna, int longitudEspada) {
        super(nombre, puntosVida, fila, columna);
        this.longitudEspada = longitudEspada;
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
class Ejercito {
    private Soldado[] soldados;
    private int numeroSoldados;
    private String nombreEjercito;
    private String[][] tablero;
    public Ejercito(String nombreEjercito, String[][] tablero) {
        this.nombreEjercito = nombreEjercito;
        this.soldados = new Soldado[10];
        this.numeroSoldados = 0;
        this.tablero = tablero;
    }
    public void crearSoldado(int tipoSoldado, int i) {
        Random rand = new Random();
        String nombre = "";
        int vida = 0, fila = rand.nextInt(10), columna = rand.nextInt(10);
        switch (tipoSoldado) {
            case 0://espadachin
                nombre = "E" + i + "X" + (nombreEjercito.equals("Ejército 1") ? "1" : "2");  //formato: E4X1
                vida = rand.nextInt(2) + 3;
                agregarSoldado(new Espadachin(nombre, vida, fila, columna, rand.nextInt(3) + 2));
                break;
            case 1:  //arquero
                nombre = "A" + i + "X" + (nombreEjercito.equals("Ejército 1") ? "1" : "2");  //formato: A8X2
                vida = rand.nextInt(3) + 1;
                agregarSoldado(new Arquero(nombre, vida, fila, columna, rand.nextInt(10) + 5));
                break;
            case 2:     //caballero
                nombre = "C" + i + "X" + (nombreEjercito.equals("Ejército 1") ? "1" : "2");  //formato: C1X1
                vida = rand.nextInt(3) + 3;
                agregarSoldado(new Caballero(nombre, vida, fila, columna));
                break;
        }
    }
    public void agregarSoldado(Soldado soldado) {
        if (numeroSoldados < 10) {
            soldados[numeroSoldados] = soldado;
            numeroSoldados++;
            tablero[soldado.getFila()][soldado.getColumna()] = soldado.getNombre(); //agregra nombre ne la posicion
        }
    }
    public void ordenarPorPoder() {    //burbuja
        for (int i = 0; i < numeroSoldados - 1; i++) {
            for (int j = i + 1; j < numeroSoldados; j++) {
                if (soldados[i].getPuntosVida() < soldados[j].getPuntosVida()) {
                    Soldado temp = soldados[i];
                    soldados[i] = soldados[j];
                    soldados[j] = temp;
                }
            }
        }
    }
    public Soldado[] getSoldados() {
        return soldados;
    }
    public String getNombreEjercito() {
        return nombreEjercito;
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
                    tablero[i][j] = "____";
                }
            }
            Ejercito ejercito1 = new Ejercito("Ejército 1", tablero);
            Ejercito ejercito2 = new Ejercito("Ejército 2", tablero);
            for (int i = 0; i < rand.nextInt(10) + 1; i++) {   //crea ejercito 1
                int tipoSoldado = rand.nextInt(3);
                ejercito1.crearSoldado(tipoSoldado, i);
            }
            for (int i = 0; i < rand.nextInt(10) + 1; i++) { //crea ejercito 2
                int tipoSoldado = rand.nextInt(3);
                ejercito2.crearSoldado(tipoSoldado, i);
            }
            System.out.println("Tablero combinado de ambos Ejércitos (10x10):");
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(tablero[i][j] + "|");
                }
                System.out.println();
            }
            System.out.println("\nSoldados del " + ejercito1.getNombreEjercito() + ":");//info ejercito1
            for (Soldado soldado : ejercito1.getSoldados()) {
                if (soldado != null) {
                    System.out.println(soldado);
                }
            }
            System.out.println("\nSoldados del " + ejercito2.getNombreEjercito() + ":"); //info ejercito 2
            for (Soldado soldado : ejercito2.getSoldados()) {
                if (soldado != null) {
                    System.out.println(soldado);
                }
            }
            Soldado mayorVidaEj1 = ejercito1.getSoldados()[0];
            Soldado mayorVidaEj2 = ejercito2.getSoldados()[0];
            for (Soldado soldado : ejercito1.getSoldados()) {
                if (soldado != null && soldado.getPuntosVida() > mayorVidaEj1.getPuntosVida()) {//mayor vida sold por ejercito
                    mayorVidaEj1 = soldado;
                }
            }
            for (Soldado soldado : ejercito2.getSoldados()) {
                if (soldado != null && soldado.getPuntosVida() > mayorVidaEj2.getPuntosVida()) {//mayor vida soldado por ejercito
                    mayorVidaEj2 = soldado;
                }
            }
            System.out.println("\nSoldado con mayor vida en el Ejército 1: " + mayorVidaEj1);
            System.out.println("Soldado con mayor vida en el Ejército 2: " + mayorVidaEj2);
            System.out.println("\nPromedio de vida del Ejército 1: " + calcularPromedioVida(ejercito1));
            System.out.println("Promedio de vida del Ejército 2: " + calcularPromedioVida(ejercito2));
            System.out.println("\nRanking de poder del Ejército 1:");
            ejercito1.ordenarPorPoder(); //burbuja
            for (Soldado soldado : ejercito1.getSoldados()) {
                if (soldado != null) {
                    System.out.println(soldado);
                }
            }
            System.out.println("\nRanking de poder del Ejército 2:");
            ejercito2.ordenarPorPoder();     //burbuja
            for (Soldado soldado : ejercito2.getSoldados()) {
                if (soldado != null) {
                    System.out.println(soldado);
                }
            }
            if (calcularPromedioVida(ejercito1) > calcularPromedioVida(ejercito2)) {
                System.out.println("\nEl Ejército 1 ganará la batalla.");
            } else if (calcularPromedioVida(ejercito1) < calcularPromedioVida(ejercito2)) {
                System.out.println("\nEl Ejército 2 ganará la batalla.");
            } else {
                System.out.println("\nEs un empate.");
            }
            System.out.print("\n¿Deseas jugar otra vez? (s/n): ");
            String respuesta = scanner.next();
            if (respuesta.equalsIgnoreCase("n")) {
                seguirJugando = false;
            }
        }
    }
    public static double calcularPromedioVida(Ejercito ejercito) {
        int totalVida = 0;
        int totalSoldados = 0;
        for (Soldado soldado : ejercito.getSoldados()) {
            if (soldado != null) {
                totalVida += soldado.getPuntosVida();
                totalSoldados++;
            }
        }
        return totalSoldados > 0 ? (double) totalVida / totalSoldados : 0;
    }
}