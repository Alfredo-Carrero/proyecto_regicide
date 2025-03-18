import java.util.*;
import java.io.*;

public class Principal {
    public static void main(String[] args) {
        // Baraja de 52 cartas
        ArrayList<Carta> baraja = new ArrayList<>(52);
        ArrayList<Carta> castillo = new ArrayList<>(12);
        ArrayList<Carta> mazoPosada = new ArrayList<>(40);
        ArrayList<Carta> manoJugador = new ArrayList<>(8);
        ArrayList<Carta> mazoCartasUsadas = new ArrayList<>();
        ArrayList<Carta> mazoCartasDescartadas = new ArrayList<>();

        String[] palos = {"Picas", "Corazones", "Tréboles", "Diamantes"};

        // Generar las 52 cartas
        for (String palo : palos) {
            for (int numero = 1; numero <= 13; numero++) {
                baraja.add(new Carta(numero, palo));
            }
        }

        // Imprimir baraja
        for (Carta carta : baraja) {
            System.out.println(carta);
        }
    }
}
