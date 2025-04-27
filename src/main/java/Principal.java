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

        // Gestor guardar partida
        Partida partida = new Partida(jugador1, enemigo, mazoPosada, mazoCartasJugadas, mazoCartasDescartadas);

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
        System.out.println("Selecciona la/s carta/s que deseas jugar (separadas por espacio si es más de una):");
        String[] entradas = sc.nextLine().trim().split("\\s+");

        ArrayList<Integer> indices = new ArrayList<>();
        for (String entrada : entradas) {
            try {
                indices.add(Integer.parseInt(entrada));
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida: " + entrada);
            }
        }

        // Ordenar los índices en orden descendente para evitar errores al eliminar
        indices.sort(Collections.reverseOrder());

        ArrayList<Carta> cartasJugadasEsteTurno = new ArrayList<>();
        int danoTotal = 0;

        for (int indice : indices) {
            Carta carta = jugador1.jugarCarta(indice);
            if (carta != null) {
                cartasJugadasEsteTurno.add(carta);
                mazoCartasJugadas.add(carta);
                danoTotal += carta.getNumero();
            }
        }


        System.out.println("\nCartas jugadas este turno:");
        for (Carta c : cartasJugadasEsteTurno) {
            System.out.println(" - " + c);
        }
        
        enemigo.mostrarEnemigo();

        //System.out.println("Daño total al enemigo: " + danoTotal);
        enemigo.recibirAtaque(danoTotal);

        int vidaEnemigoAntes = enemigo.getVidaEnemigo() + danoTotal;

        if(danoTotal == vidaEnemigoAntes){
            System.out.println("Daño exacto! Enemigo a mazoPosada");
            mazoPosada.add(enemigo.getCastillo().remove(0));
            mazoCartasDescartadas.addAll(cartasJugadasEsteTurno);
            cartasJugadasEsteTurno.clear();

            // Mostrar siguiente enemigo
            enemigo.mostrarEnemigo();

        }else if(danoTotal > vidaEnemigoAntes){
            System.out.println("Enemigo derrotado!!");
            mazoCartasDescartadas.addAll(cartasJugadasEsteTurno);
            cartasJugadasEsteTurno.clear();

            // Eliminar enemigo
            enemigo.getCastillo().remove(0);

            // Mostrar sig enemigo si queda alguno
            if(!enemigo.getCastillo().isEmpty()){
                enemigo.setVidaEnemigo(enemigo.getCastillo().get(0).getVida());
                enemigo.mostrarEnemigo();
            }else{
                System.out.println("Has derrotado a todos los enemigos!");
            }
        }else{
            System.out.println("El enemigo sobrevive y contraataca");
            int ataque = enemigo.getVidaEnemigo();
            System.out.println("Poder del ataque enemigo: " + ataque);

            int defensaTotal = 0;
            ArrayList<Carta> defensa = new ArrayList<>();

            while (defensaTotal < ataque && !jugador1.getManoJugador().isEmpty()) {
                System.out.println("\nTu mano actual:");
                jugador1.mostrarMano();
                System.out.println("Debes defenderte. Elige carta para descartar (valor acumulado: " + defensaTotal + "/" + ataque + "):");
                String entrada = sc.nextLine();

                try {
                    int indice = Integer.parseInt(entrada);
                    Carta cartaDescartada = jugador1.jugarCarta(indice);
                    if (cartaDescartada != null) {
                        defensa.add(cartaDescartada);
                        defensaTotal += cartaDescartada.getNumero();
                        System.out.println("Has descartado: " + cartaDescartada);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida.");
                }
            }

            if (defensaTotal < ataque) {
                System.out.println("¡No has podido defenderte! Has perdido la partida.");
                // Aquí termina el juego
                System.exit(0);
            } else {
                System.out.println("¡Has sobrevivido al ataque del enemigo!");
                mazoCartasDescartadas.addAll(defensa);

                // Opción guardar la partida
                System.out.print("\n¿Deseas guardar y terminar la partida? (S/N): ");
                String opcion = sc.nextLine().trim().toUpperCase();

                if (opcion.equals("S")) {
                    partida.guardarPartidaJSON();
                    partida.guardarEstadisticasCSV();
                    System.out.println("Partida guardada correctamente. ¡Hasta la próxima!");
                    System.exit(0);
                }
            }

            System.out.println("\n======= RESUMEN DEL TURNO =======");
            System.out.println("Mano actual:");
            jugador1.mostrarMano();

            System.out.println("\nCartas jugadas:");
            for (Carta c : mazoCartasJugadas) {
                System.out.println(" - " + c);
            }

            System.out.println("\nVida restante del enemigo:");
            enemigo.mostrarEnemigo();

            System.out.println("\nEstado de los mazos:");
            mostrarCartasRestantes(enemigo, mazoPosada, mazoCartasJugadas, mazoCartasDescartadas);
            System.out.println("==================================");

            // Opción guardar la partida
            System.out.print("\n¿Deseas guardar y terminar la partida? (S/N): ");
            String opcion = sc.nextLine().trim().toUpperCase();

            if (opcion.equals("S")) {
                partida.guardarPartidaJSON();
                partida.guardarEstadisticasCSV();
                System.out.println("Partida guardada correctamente. ¡Hasta la próxima!");
                System.exit(0);
            }

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
