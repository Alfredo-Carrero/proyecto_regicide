public class Carta {
    private int numero;
    private String palo;
    private int vida;

    // Constructor
    public Carta(int numero, String palo) {
        this.numero = numero;
        this.palo = palo;
        this.vida = asignarVida(numero);
    }

    // Getters y Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
            this.palo = palo;
    }

    // Métodos
    private int asignarVida(int numero) {
        if (numero == 11) {
            return 20; // J tiene 20 de vida
        } else if (numero == 12) {
            return 30; // Q tiene 30 de vida
        } else if (numero == 13) {
            return 40; // K tiene 40 de vida
        }
        return 0;
    }

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


        return nombreCarta + " de " + palo + " (Vida: " + vida + ")";
    }
}
