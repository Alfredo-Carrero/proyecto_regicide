import java.io.*;
import java.util.*;
import com.google.gson.Gson;

/**
 * Clase que gestiona una partida en curso.
 * Permite guardar el estado de la partida en JSON y estadísticas en CSV.
 */
public class Partida {
    private Jugador jugador;
    private Enemigo enemigo;
    private ArrayList<Carta> mazoPosada;
    private ArrayList<Carta> mazoCartasJugadas;
    private ArrayList<Carta> mazoCartasDescartadas;

    // Constructor
    /**
     * Crea una nueva partida con todos los elementos necesarios.
     * @param jugador Jugador de la partida.
     * @param enemigo Enemigo y su castillo.
     * @param mazoPosada Mazo de cartas disponibles.
     * @param mazoCartasJugadas Cartas usadas en combate.
     * @param mazoCartasDescartadas Cartas descartadas por defensa o eliminación.
     */
    public Partida(Jugador jugador, Enemigo enemigo, ArrayList<Carta> mazoPosada, ArrayList<Carta> mazoCartasJugadas, ArrayList<Carta> mazoCartasDescartadas) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.mazoPosada = mazoPosada;
        this.mazoCartasJugadas = mazoCartasJugadas;
        this.mazoCartasDescartadas = mazoCartasDescartadas;
    }

    // Métodos
    /**
     * Guarda el estado actual de la partida en un archivo JSON.
     * El archivo se llama "partida_guardada.json".
     */
    public void guardarPartidaJSON() {
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter("partida_guardada.json")) {
            gson.toJson(this, writer);
            System.out.println("Partida guardada correctamente en partida_guardada.json");
        } catch (IOException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }

    /**
     * Guarda una línea con estadísticas de la partida en el archivo "estadisticas.csv".
     * Incluye fecha, cartas jugadas, vida restante del enemigo, mano actual y estado (victoria o no).
     */
    public void guardarEstadisticasCSV() {
        try (FileWriter fw = new FileWriter("estadisticas.csv", true);
             PrintWriter pw = new PrintWriter(fw)) {

            String fecha = new Date().toString();
            int numCartasJugadas = mazoCartasJugadas.size();
            int vidaEnemigo = enemigo.getVidaEnemigo();
            String estadoMano = jugador.getManoJugador().toString();
            String resultado = jugador.getManoJugador().isEmpty() ? "DERROTA" : "EN PROGRESO";

            pw.println(fecha + ";" + numCartasJugadas + ";" + vidaEnemigo + ";" + estadoMano + ";" + resultado);
            System.out.println("Estadísticas guardadas correctamente en estadisticas.csv");

        } catch (IOException e) {
            System.out.println("Error al guardar las estadísticas: " + e.getMessage());
        }
    }


}