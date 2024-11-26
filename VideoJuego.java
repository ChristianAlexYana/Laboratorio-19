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