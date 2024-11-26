<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class VideoJuego {

    static class Soldado {
        private String nombre;
        private int vida;
        private int posX;
        private int posY;

        public Soldado(String nombre, int vida) {
            this.nombre = nombre;
            this.vida = vida;
        }

        public int getVida() {
            return vida;
        }

        public String getNombre() {
            return nombre;
        }

        public int getPosX() {
            return posX;
        }

        public int getPosY() {
            return posY;
        }

        public void setPosicion(int x, int y) {
            this.posX = x;
            this.posY = y;
        }

        public void incrementarVida() {
            this.vida += 1;
        }
    }
=======
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
