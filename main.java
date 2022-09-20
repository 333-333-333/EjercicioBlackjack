
public class main {

    public static void main(String[] args) {
    }

    public static void crearBaraja(ArrayList<String> baraja) {

        String[] indices = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'D', 'J', 'Q', 'K'};
        String[] pintas = {" de corazón", " de trébol", " de pica", "de diamante"};

        for (String pinta : pintas) {
            for (String indice : indices) {
                baraja.add(indice + pinta);
            }
        }

    }

    public static void barajar(ArrayList<String> baraja) {
        collections.shuffle(baraja);
    }

    public static int valorarCarta (String carta) {

    }

}
