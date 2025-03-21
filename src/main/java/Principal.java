import java.util.*;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Jugador y Enemigo
        Jugador jugador1 = new Jugador();
        Enemigo enemigo = new Enemigo();

        // Mazos
        ArrayList<Carta> baraja = generarBaraja();
        ArrayList<Carta> mazoPosada = new ArrayList<>();
        ArrayList<Carta> mazoCartasJugadas = new ArrayList<>();
        ArrayList<Carta> mazoCartasDescartadas = new ArrayList<>();

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

        // Seleccionar carta a jugar
        System.out.println("Selecciona una carta para jugar (1-8):");
        int cartaSeleccionada = sc.nextInt();
        Carta cartaJugada = jugador1.jugarCarta(cartaSeleccionada);

        if (cartaJugada != null) {
            System.out.println("Has jugado: " + cartaJugada);
            // Mover la carta al mazo de cartas jugadas
            mazoCartasJugadas.add(cartaJugada);

            // Daño al enemigo
            enemigo.recibirAtaque(cartaJugada.getNumero());

            System.out.println();

            // Mostrar estado después del ataque
            jugador1.mostrarMano();

            System.out.println();

            // Mostrar Mazos
            mostrarCartasRestantes(enemigo, mazoPosada, mazoCartasJugadas, mazoCartasDescartadas);
        }
    }

    // Método para generar la baraja completa y mezclarla
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
    private static void mostrarCartasRestantes(Enemigo enemigo, ArrayList<Carta> mazoPosada, ArrayList<Carta> mazoCartasUsadas, ArrayList<Carta> mazoCartasDescartadas) {
        System.out.println("Cartas restantes:");
        System.out.println("\t - Castillo: " + enemigo.getCastillo().size() + " enemigos");
        System.out.println("\t - Mazo de Posada: " + mazoPosada.size() + " cartas");
        System.out.println("\t - Mazo de Cartas Jugadas: " + mazoCartasUsadas.size() + " cartas");
        System.out.println("\t - Mazo de Descartes: " + mazoCartasDescartadas.size() + " cartas");
    }
}
