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

    public int getVida() {
        return vida;
    }

    // Métodos
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
