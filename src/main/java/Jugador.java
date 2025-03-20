import java.util.ArrayList;

public class Jugador {
    private int numeroJugador;
    private ArrayList<Carta> manoJugador = new ArrayList<>(8);

    // Constructor
    public Jugador() {
        this.numeroJugador = (int) (Math.random() * 4 + 1);
    }

    // Getter y Setter
    public int getNumeroJugador() {
        return numeroJugador;
    }

    public ArrayList<Carta> getManoJugador() {
        return manoJugador;
    }

    public void setManoJugador(ArrayList<Carta> manoJugador) {
        this.manoJugador = manoJugador;
    }

    // Método para robar 8 cartas del mazoPosada
    public void robar(ArrayList<Carta> mazoPosada) {
        // Asegurar que no se robe más de lo que hay
        int cartasARobar = Math.min(8, mazoPosada.size());

        for (int i = 0; i < cartasARobar; i++) {
            // Robar la primera carta del mazoPosada
            manoJugador.add(mazoPosada.remove(0));
        }
    }

    public void mostrarMano() {
        if (manoJugador.isEmpty()) {
            System.out.println("El Jugador " + numeroJugador + " no tiene cartas en la mano.");
        } else {
            for (int i = 0; i < manoJugador.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + manoJugador.get(i));
            }
        }
    }


    // toString
    @Override
    public String toString() {
        return "Jugador nº: " + numeroJugador;
    }
}
