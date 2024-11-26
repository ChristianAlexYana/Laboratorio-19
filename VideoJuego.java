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