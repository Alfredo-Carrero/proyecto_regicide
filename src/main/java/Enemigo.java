import java.util.*;
import java.io.*;

public class Enemigo {
    private ArrayList<Carta> castillo = new ArrayList<>(12);
    private int vidaCarta;

    // Constructor

    // Getters y Setters
    public ArrayList<Carta> getCastillo() {
        return castillo;
    }

    public void setCastillo(ArrayList<Carta> castillo) {
        this.castillo = castillo;
    }

    // Método generar Castillo
    public void generarCastillo(ArrayList<Carta> baraja) {
        // Extraer las cartas con números 11, 12 y 13 de la baraja
        Iterator<Carta> it = baraja.iterator();
        while (it.hasNext()) {
            Carta carta = it.next();

            if (carta.getNumero() >= 11) {
                castillo.add(carta);
                it.remove();
            }
        }
    }

    // Método para mostrar el enemigo actual en el castillo
    public void mostrarEnemigo() {
        if (!castillo.isEmpty()) {
            System.out.println("Enemigo actual en el castillo: \n" + castillo.get(0));
        } else {
            System.out.println("El castillo está vacío.");
        }
    }
}
