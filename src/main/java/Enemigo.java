import java.util.ArrayList;

    public class Enemigo {
        private ArrayList<Carta> castillo = new ArrayList<>(12);
        private int vidaEnemigo;

        // Getters y Setters
        public ArrayList<Carta> getCastillo() {
            return castillo;
        }

        public int getVidaEnemigo() {
            return vidaEnemigo;
        }

        public void setVidaEnemigo(int vida) {
            this.vidaEnemigo = vida;
        }

        // Generar castillo
        public void generarCastillo(ArrayList<Carta> cartasCastillo) {
            castillo.addAll(cartasCastillo);
            if (!castillo.isEmpty()) {
                vidaEnemigo = castillo.get(0).getVida();
            }
        }

    // Mostrar el enemigo sin eliminarlo
    public void mostrarEnemigo() {
        if (!castillo.isEmpty()) {
            System.out.println("Enemigo actual en el castillo:");
            System.out.println(castillo.get(0) + " (Vida: " + vidaEnemigo + ")");
        } else {
            System.out.println("El castillo está vacío.");
        }
    }

    // Restar vida al enemigo
    public void recibirAtaque(int dano) {
        if (!castillo.isEmpty()) {
            vidaEnemigo -= dano;
            System.out.println("Daño causado al enemigo: " + dano);

            if (vidaEnemigo <= 0) {
                System.out.println("¡El enemigo ha sido derrotado!");
                castillo.remove(0);

                // Asignar vida al enemigo si hay más en el castillo
                if (!castillo.isEmpty()) {
                    vidaEnemigo = castillo.get(0).getVida();
                    System.out.println("Nuevo enemigo en el castillo: " + castillo.get(0) + " (Vida: " + vidaEnemigo + ")");
                } else {
                    System.out.println("No quedan enemigos en el castillo.");
                }
            } else {
                System.out.println("Vida restante del enemigo: " + vidaEnemigo);
            }
        }
    }
}
