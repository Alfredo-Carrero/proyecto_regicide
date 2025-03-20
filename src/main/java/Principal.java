import java.util.*;
import java.io.*;

public class Principal {
    public static void main(String[] args) {
        // Jugador(es)
        Jugador jugador1 = new Jugador();

        // Enemigo
        Enemigo enemigo = new Enemigo();

        // Baraja de 52 cartas
        ArrayList<Carta> baraja = new ArrayList<>(52);
        ArrayList<Carta> mazoPosada = new ArrayList<>(40);
        ArrayList<Carta> mazoCartasUsadas = new ArrayList<>();
        ArrayList<Carta> mazoCartasDescartadas = new ArrayList<>();

        // Generar las 52 cartas
        String[] palos = {"Picas", "Corazones", "Tréboles", "Diamantes"};
        for (String palo : palos) {
            for (int numero = 1; numero <= 13; numero++) {
                baraja.add(new Carta(numero, palo));
            }
        }

        // Generar el castillo
        enemigo.generarCastillo(baraja);

        // Agregar cartas restantes al mazoPosada y vaciar la baraja
        mazoPosada.addAll(baraja);
        baraja.clear();

        // Jugador roba 8 cartas
        jugador1.robar(mazoPosada);

        // Mostrar mano jugador
        System.out.println("Tu mano actual:");
        jugador1.mostrarMano();

        System.out.println();

        // Mostrar el enemigo actual
        enemigo.mostrarEnemigo();

        System.out.println();

        // Cartas restantes
        System.out.println("Cartas restantes:");
        System.out.println("\t - Castillo: " + enemigo.getCastillo().size() + " enemigos" + "\n" +
                "\t - Mazo de Posada: " + mazoPosada.size() + " cartas" + "\n" +
                "\t - Mazo de Cartas Jugadas: " + mazoCartasUsadas.size() + " cartas" + "\n" +
                "\t - Mazo de Descartes: " + mazoCartasDescartadas.size() + " cartas");
    }
}
