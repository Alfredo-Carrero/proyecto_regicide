public class Jugador {
    private int numeroJugador;

    // Constructor
    public Jugador() {
        this.numeroJugador = (int) (Math.random() * 4 + 1);
    }

    // Getter
    public int getNumeroJugador() {
        return numeroJugador;
    }

    // Métodos
    /*robarCarta, mostrarMano, descartar*/

    // toString
    @Override
    public String toString() {
        return "Jugador nº: " + numeroJugador;
    }
}
