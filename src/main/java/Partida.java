import java.io.*;
import java.util.*;
import com.google.gson.Gson;

public class Partida {
    private Jugador jugador;
    private Enemigo enemigo;
    private ArrayList<Carta> mazoPosada;
    private ArrayList<Carta> mazoCartasJugadas;
    private ArrayList<Carta> mazoCartasDescartadas;

    // Constructor
    public Partida(Jugador jugador, Enemigo enemigo, ArrayList<Carta> mazoPosada, ArrayList<Carta> mazoCartasJugadas, ArrayList<Carta> mazoCartasDescartadas) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.mazoPosada = mazoPosada;
        this.mazoCartasJugadas = mazoCartasJugadas;
        this.mazoCartasDescartadas = mazoCartasDescartadas;
    }

    // Métodos
    public void guardarPartidaJSON() {
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter("partida_guardada.json")) {
            gson.toJson(this, writer);
            System.out.println("Partida guardada correctamente en partida_guardada.json");
        } catch (IOException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }

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