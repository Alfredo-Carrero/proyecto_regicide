/**
* Clase Carta, que representa las cartas de la baraja, con su nº, palo y vida.
*/
public class Carta {
    private int numero;
    private String palo;
    private int vida;

    // Constructor
    /**
     * Crea una carta con un número y un palo, y asigna su vida automáticamente.
     * @param numero Número de la carta (1-13).
     * @param palo Palo de la carta (Picas, Corazones, etc).
     */
    public Carta(int numero, String palo) {
        this.numero = numero;
        this.palo = palo;
        this.vida = asignarVida(numero);
    }

    // Getters y Setters
    /**
     * Devuelve el nº de la carta
     * @return numero (1-13)
     */
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Devuelve el palo de la carta
     * @return palo (tipo String)
     */
    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
            this.palo = palo;
    }

    /**
     * Devuelve la vida asignada a la carta
     * @return vida (tipo int)
     */
    public int getVida() {
        return vida;
    }

    // Métodos
    /**
     * Método asignarVida()
     * Asigna los puntos de vida según el número de carta (pasado por parámetro).
     * @param numero Número de la carta.
     * @return Puntos de vida.
     */
    private int asignarVida(int numero) {
        if (numero == 11) {
            return 20;
        } else if (numero == 12) {
            return 30;
        } else if (numero == 13) {
            return 40;
        }
        return numero;
    }

    // toString
    /**
     * Devuelve información sobre la carta (nombre, palo...).
     */
    @Override
    public String toString() {
        String nombreCarta;

        if (numero == 11) {
            nombreCarta = "Jota";
        } else if (numero == 12) {
            nombreCarta = "Reina";
        } else if (numero == 13) {
            nombreCarta = "Rey";
        } else {
            nombreCarta = String.valueOf(numero);
        }

        return nombreCarta + " de " + palo;
    }
}
