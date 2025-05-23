import java.util.ArrayList;
/**
 * Clase Enemigo, para crear el enemigo con su castillo de cartas y vida
 */
    public class Enemigo {
        private ArrayList<Carta> castillo = new ArrayList<>(12);
        private int vidaEnemigo;

        // Getters y Setters
        /**
         * Devuelve las cartas del castillo.
         * @return Lista de enemigos.
         */
        public ArrayList<Carta> getCastillo() {
            return castillo;
        }

        /**
         * Devuelve la vida del enemigo actual.
         * @return Puntos de vida.
         */
        public int getVidaEnemigo() {
            return vidaEnemigo;
        }

        /**
         * Establece la vida del enemigo actual.
         * @param vida Valor de vida.
         */
        public void setVidaEnemigo(int vida) {
            this.vidaEnemigo = vida;
        }

        // Generar castillo
        /**
         * Inicializa el castillo con las cartas dadas y asigna vida al primer enemigo.
         * @param cartasCastillo Lista de cartas de valor 11, 12, 13.
         */
        public void generarCastillo(ArrayList<Carta> cartasCastillo) {
            castillo.addAll(cartasCastillo);
            if (!castillo.isEmpty()) {
                vidaEnemigo = castillo.get(0).getVida();
            }
        }

        // Mostrar el enemigo sin eliminarlo
        /**
         * Muestra el enemigo actual del castillo sin eliminarlo.
         */
        public void mostrarEnemigo() {
            if (!castillo.isEmpty()) {
                System.out.println("Enemigo actual en el castillo:");
                System.out.println(castillo.get(0) + " (Vida: " + vidaEnemigo + ")");
            } else {
                System.out.println("El castillo está vacío.");
            }
        }

        // Restar vida al enemigo
        /**
         * Aplica daño al enemigo actual. Si su vida llega a 0 o menos, pasa al siguiente.
         * @param dano Daño recibido.
         */
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
