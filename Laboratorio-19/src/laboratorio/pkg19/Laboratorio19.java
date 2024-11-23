/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package laboratorio.pkg19;

public class Laboratorio19 {
    public static void main(String[] args) {
        Ejercito ejercitoA = new Ejercito("Ejército Águila", 1);
        Ejercito ejercitoB = new Ejercito("Ejército León", 2);
        Batalla batalla = new Batalla(ejercitoA, ejercitoB);
        batalla.iniciarJuego();
    }
}
