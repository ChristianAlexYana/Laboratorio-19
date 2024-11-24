/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package laboratorio.pkg19;

public class Laboratorio19 {
    public static void main(String[] args) {
        Ejercito ejercito1 = new Ejercito("Ejército 1", 1);
        Ejercito ejercito2 = new Ejercito("Ejército 2", 2);
        Batalla batalla = new Batalla(ejercito1, ejercito2);
        batalla.iniciarJuego();
    }
}
