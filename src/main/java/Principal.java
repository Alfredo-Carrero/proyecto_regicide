import java.util.*;

/**
 * Clase principal del juego Regicide en modo solitario.
 * Se encarga de preparar la partida, repartir las cartas y comenzar el primer turno.
 * @author Alfredo Carrero Muñiz
 * @version Regicide 2.0
 */
public class Principal {
    /**
     * Método principal que lanza el juego.
     * @param args Argumentos desde la consola (no utilizados).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Crear Jugador y Enemigo
        Jugador jugador1 = new Jugador();
        Enemigo enemigo = new Enemigo();

        // Crear Mazos y Generar Baraja
        ArrayList<Carta> baraja = generarBaraja();
        ArrayList<Carta> mazoPosada = new ArrayList<>();
        ArrayList<Carta> mazoCartasJugadas = new ArrayList<>();
        ArrayList<Carta> mazoCartasDescartadas = new ArrayList<>();

        // Gestor guardar partida
        Partida partida = new Partida(jugador1, enemigo, mazoPosada, mazoCartasJugadas, mazoCartasDescartadas);

        // Gestor turno
        Turno turno = new Turno(sc, jugador1, enemigo, partida, mazoPosada, mazoCartasJugadas, mazoCartasDescartadas);

        // Extraer cartas del castillo (11, 12 y 13)
        ArrayList<Carta> cartasCastillo = new ArrayList<>();
        Iterator<Carta> it = baraja.iterator();
        while (it.hasNext()) {
            Carta carta = it.next();
            if (carta.getNumero() >= 11) {
                cartasCastillo.add(carta);
                it.remove();
            }
        }

        // Asignar cartas al castillo del enemigo
        enemigo.generarCastillo(cartasCastillo);

        // Las cartas restantes van al mazoPosada
        mazoPosada.addAll(baraja);
        baraja.clear();

        // El jugador roba 8 cartas (su mano)
        jugador1.robar(mazoPosada);

        System.out.println("=====================================");
        System.out.println("\t\tBIENVENIDO A REGICIDE\t\t\n" +
                            "\t\t\tMODO SOLITARIO\t\t\t");
        System.out.println("=====================================");

        System.out.println();

        System.out.println("=====================================");
        // Mano del jugador
        System.out.println("Tu mano actual:");
        jugador1.mostrarMano();

        System.out.println();

        // Carta del Enemigo (castillo)
        enemigo.mostrarEnemigo();

        System.out.println();

        // Mostrar cartas restantes en los mazos
        mostrarCartasRestantes(enemigo, mazoPosada, mazoCartasJugadas, mazoCartasDescartadas);


        System.out.println("=====================================");
        System.out.println("¡Comienza la pelea!");
        System.out.println();

        // Ejecutar el turno
        turno.ejecutarTurno();

    }

    // Método para generar la baraja completa y mezclarla
    /**
     * Genera una baraja de 52 cartas (13 por cada uno de los 4 palos).
     * @return ArrayList con las cartas mezcladas.
     */
    private static ArrayList<Carta> generarBaraja() {
        ArrayList<Carta> baraja = new ArrayList<>(52);
        String[] palos = {"Picas", "Corazones", "Tréboles", "Diamantes"};

        for (String palo : palos) {
            for (int numero = 1; numero <= 13; numero++) {
                baraja.add(new Carta(numero, palo));
            }
        }

        // Barajear
        Collections.shuffle(baraja);
        return baraja;
    }

    // Método para mostrar las cartas restantes en los mazos
    /**
     * Muestra por consola cuántas cartas quedan en cada mazo del juego.
     * @param enemigo Objeto enemigo para consultar el castillo.
     * @param mazoPosada Cartas restantes que se pueden robar.
     * @param mazoCartasUsadas Cartas jugadas en combate.
     * @param mazoCartasDescartadas Cartas descartadas por defensa o final de turno.
     */
    private static void mostrarCartasRestantes(Enemigo enemigo, ArrayList<Carta> mazoPosada, ArrayList<Carta> mazoCartasUsadas, ArrayList<Carta> mazoCartasDescartadas) {
        System.out.println("Cartas restantes:");
        System.out.println("\t - Castillo: " + enemigo.getCastillo().size() + " enemigos");
        System.out.println("\t - Mazo de Posada: " + mazoPosada.size() + " cartas");
        System.out.println("\t - Mazo de Cartas Jugadas: " + mazoCartasUsadas.size() + " cartas");
        System.out.println("\t - Mazo de Descartes: " + mazoCartasDescartadas.size() + " cartas");
    }
}
