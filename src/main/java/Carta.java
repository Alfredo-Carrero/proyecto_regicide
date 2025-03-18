public class Carta {
    private int numero;
    private String palo;

    // Constructor
    public Carta(){
        this(0, "");
    }

    public Carta(int numero, String palo){
        setNumero(numero);
        setPalo(palo);
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

    @Override
    public String toString() {
        return numero + " de " + palo;
    }
}
