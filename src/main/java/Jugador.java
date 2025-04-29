import java.util.ArrayList;
/**
 * Clase Jugador, para crear a un jugador del juego
 * Se le asigna un nº aleatorio y una mano de cartas
 */
public class Jugador {
    private int numeroJugador;
    private ArrayList<Carta> manoJugador = new ArrayList<>(8);

    // Constructor
    /**
     * Crea un jugador con número aleatorio del 1 al 4.
     */
    public Jugador() {
        this.numeroJugador = (int) (Math.random() * 4 + 1);
    }

    // Getter y Setter
    /**
     * Devuelve el número del jugador.
     * @return Número aleatorio del jugador.
     */
    public int getNumeroJugador() {
        return numeroJugador;
    }

    /**
     * Devuelve la mano actual del jugador.
     * @return Lista de cartas en mano.
     */
    public ArrayList<Carta> getManoJugador() {
        return manoJugador;
    }

    public void setManoJugador(ArrayList<Carta> manoJugador) {
        this.manoJugador = manoJugador;
    }

    // Método para robar 8 cartas del mazoPosada
    /**
     * Roba hasta 8 cartas del mazoPosada y las añade a la mano del jugador.
     * @param mazoPosada Mazo del cual se roban las cartas.
     */
    public void robar(ArrayList<Carta> mazoPosada) {
        // Asegurar que no se robe más de lo que hay
        int cartasARobar = Math.min(8, mazoPosada.size());

        for (int i = 0; i < cartasARobar; i++) {
            // Robar la primera carta del mazoPosada
            manoJugador.add(mazoPosada.remove(0));
        }
    }

    /**
     * Muestra la mano del jugador por consola.
     */
    public void mostrarMano() {
        if (manoJugador.isEmpty()) {
            System.out.println("El Jugador " + numeroJugador + " no tiene cartas en la mano.");
        } else {
            for (int i = 0; i < manoJugador.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + manoJugador.get(i));
            }
        }
    }

    // Seleccionar una carta para jugar
    /**
     * Elimina y devuelve la carta elegida por índice.
     * @param indice Índice de la carta a jugar.
     * @return Carta jugada o null si no es válida.
     */
    public Carta jugarCarta(int indice) {
        if (indice < 1 || indice > manoJugador.size()) {
            System.out.println("Selección no válida. Intente de nuevo.");
            return null;
        }
        // Jugar la carta (eliminarla de la mano)
        Carta cartaJugada = manoJugador.remove(indice - 1);

        return cartaJugada;
    }

    // toString
    /**
     * Devuelve información del jugador
     */
    @Override
    public String toString() {
        return "Jugador nº: " + numeroJugador;
    }
}
