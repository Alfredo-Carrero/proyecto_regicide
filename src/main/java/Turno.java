import java.io.*;
import java.util.*;
/**
 * Clase Turno, para gestionar un turno completo de la partida
 */
public class Turno {
    private Scanner sc;
    private Jugador jugador;
    private Enemigo enemigo;
    private Partida partida;
    private ArrayList<Carta> mazoPosada;
    private ArrayList<Carta> mazoCartasJugadas;
    private ArrayList<Carta> mazoCartasDescartadas;

    // Constructor
    /**
     * Crea un objeto turno, pasándole los sig parámetros:
     * @param sc Entrada del usuario por consola
     * @param jugador Objeto jugador
     * @param mazoPosada Mazo de cartas Posada
     * @param mazoCartasJugadas Mazo de Cartas Jugadas
     * @param mazoCartasDescartadas Mazo de Cartas Descartadas
     */
    public Turno(Scanner sc, Jugador jugador, Enemigo enemigo,
                 Partida partida, ArrayList<Carta> mazoPosada,
                 ArrayList<Carta> mazoCartasJugadas, ArrayList<Carta> mazoCartasDescartadas) {
        this.sc = sc;
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.partida = partida;
        this.mazoPosada = mazoPosada;
        this.mazoCartasJugadas = mazoCartasJugadas;
        this.mazoCartasDescartadas = mazoCartasDescartadas;
    }

    // Métodos
    /**
     * Método principal de la clase, que agrupa los demás métodos para ejecutar turno
     */
    public boolean ejecutarTurno() {
        ArrayList<Integer> indices = obtenerIndicesCartas();
        ArrayList<Carta> cartasJugadas = jugarCartas(indices);

        if(cartasJugadas.isEmpty()){
            System.out.println("No has jugado ninguna carta.");
            return true;
        }

        int danioTotal = 0;
        for(Carta carta : cartasJugadas){
            danioTotal += carta.getNumero();
        }

        enemigo.mostrarEnemigo();

        boolean continuar = procesarResultadoAtaque(cartasJugadas, danioTotal);
        if(!continuar){
            return false;
        }

        mostrarResumenTurno();
        boolean guardar = preguntarGuardarYSalir();

        if(guardar){
            return false;
        }else {
            return true;
        }
    }

    /**
     * Método privado de la Clase, que obtiene las cartas seleccionadas por el jugador
     * a través de la entrada estándar por consola.
     * Las guarda en un Array de String, separándolas por espacios.
     */
    private ArrayList<Integer> obtenerIndicesCartas(){
        System.out.println("Selecciona la/s carta/s a jugar (separadas por un espacio): ");
        // Guarda los índices seleccionados
        String[] entradas = sc.nextLine().trim().split("\\s+");

        ArrayList<Integer> indices = new ArrayList<>();
        for(String entrada : entradas){
            indices.add(Integer.parseInt(entrada));
        }

        return indices;
    }

    /**
     * Método privado de la Clase, que recoge las cartas seleccionadas para jugar el turno, las muestra
     * y añade al mazo de Cartas Jugadas.
     * @param indices Lista de los índices (cartas) seleccionados por el jugador.
     */
    private ArrayList<Carta> jugarCartas(ArrayList<Integer> indices){
        ArrayList<Carta> cartasJugadasTurno = new ArrayList<>();

        for(int indice : indices){
            Carta carta = jugador.jugarCarta(indice);

            if(carta != null){
                cartasJugadasTurno.add(carta);
                mazoCartasJugadas.add(carta);
            }
        }

        System.out.println("\nCartas jugadas este turno:");
        for(Carta c : cartasJugadasTurno){
            System.out.println(" - " + c);
        }

        return cartasJugadasTurno;
    }

    /**
     * Método privado de la Clase, que gestiona el ataque del Jugador al Enemigo, y si éste sobrevive.
     * @param jugadas Lista de Cartas jugadas por el Jugador.
     * @param danioTotal Daño causado por las cartas del Jugador.
     */
    private boolean procesarResultadoAtaque(ArrayList<Carta> jugadas, int danioTotal){
        int vidaEnemigoAntes = enemigo.getVidaEnemigo();
        enemigo.recibirAtaque(danioTotal);

        if(danioTotal == vidaEnemigoAntes){
            System.out.println("Daño exacto! Enemigo eliminado y enviado a mazoPosada.");
            mazoPosada.add(enemigo.getCastillo().remove(0));
            mazoCartasDescartadas.addAll(jugadas);
            jugadas.clear();

            if(!enemigo.getCastillo().isEmpty()){
                enemigo.setVidaEnemigo(enemigo.getCastillo().get(0).getVida());
                enemigo.mostrarEnemigo();
            }else{
                System.out.println("¡Has derrotado a todos los enemigos!");
                return false;
            }
        }else if(danioTotal > vidaEnemigoAntes){
            System.out.println("Enemigo derrotado!");
            mazoCartasDescartadas.addAll(jugadas);
            jugadas.clear();
            enemigo.getCastillo().remove(0);

            if(!enemigo.getCastillo().isEmpty()){
                enemigo.setVidaEnemigo(enemigo.getCastillo().get(0).getVida());
                enemigo.mostrarEnemigo();
            }else{
                System.out.println("¡Has derrotado a todos los enemigos!");
                return false;
            }
        }else{
            System.out.println("El enemigo sobrevive y contraataca:");
            int ataque = enemigo.getVidaEnemigo();
            boolean sobrevive = defenderse(ataque);

            if(!sobrevive){
                return false;
            }
        }

        return true;
    }

    /**
     * Método privado de la Clase, para que el Jugador se descarte de sus cartas
     * para poder defenderse del ataque del Enemigo.
     * @param ataque Vida restante del enemigo
     */
    private boolean defenderse(int ataque){
        int defensaTotal = 0;
        ArrayList<Carta> defensa = new ArrayList<>();

        while(defensaTotal < ataque && !jugador.getManoJugador().isEmpty()){
            System.out.println("\nTu mano actual:");
            jugador.mostrarMano();
            System.out.println("Debes defenderte. Elige carta para descartar(valor acumulado: " + defensaTotal + "/" + ataque + "):");

            String entrada = sc.nextLine();
            int indice = Integer.parseInt(entrada);
            Carta cartaDescartada = jugador.jugarCarta(indice);

            if(cartaDescartada != null){
                defensa.add(cartaDescartada);
                defensaTotal += cartaDescartada.getNumero();
                System.out.println("Has descartado: " + cartaDescartada);
            }
        }

        if(defensaTotal < ataque){
            System.out.println("No has podido defenderte, has perdido la partida");
            return false;
        }else{
            System.out.println("Has sobrevivido al ataque del enemigo!");
            mazoCartasDescartadas.addAll(defensa);
            return true;
        }
    }

    /**
     * Método privado de la Clase, para mostrar resumen del turno.
     */
    private void mostrarResumenTurno() {
        System.out.println("\n======= RESUMEN DEL TURNO =======");
        System.out.println("Mano actual:");
        jugador.mostrarMano();

        System.out.println("\nCartas jugadas:");
        for (Carta c : mazoCartasJugadas) {
            System.out.println(" - " + c);
        }

        System.out.println("\nVida restante del enemigo:");
        enemigo.mostrarEnemigo();

        System.out.println("\nEstado de los mazos:");
        mostrarCartasRestantes();
        System.out.println("==================================");
    }

    /**
     * Método privado de la Clase, que muestra las cartas restantes en la partida.
     */
    private void mostrarCartasRestantes() {
        System.out.println("Cartas restantes:");
        System.out.println("\t - Castillo: " + enemigo.getCastillo().size() + " enemigos");
        System.out.println("\t - Mazo de Posada: " + mazoPosada.size() + " cartas");
        System.out.println("\t - Mazo de Cartas Jugadas: " + mazoCartasJugadas.size() + " cartas");
        System.out.println("\t - Mazo de Descartes: " + mazoCartasDescartadas.size() + " cartas");
    }

    /**
     * Método privado de la Clase, para preguntar si se quiere guardar la partida y salir.
     * Guarda la partida en formato .json, y las estadísticas de la partida en formato .csv.
     */
    private boolean preguntarGuardarYSalir() {
        System.out.print("\n¿Deseas guardar y terminar la partida? (S/N): ");
        String opcion = sc.nextLine().trim().toUpperCase();
        if (opcion.equals("S")) {
            partida.guardarPartidaJSON();
            partida.guardarEstadisticasCSV();
            System.out.println("Partida guardada correctamente. ¡Hasta la próxima!");
            return true;
        }
        return false;
    }
}
