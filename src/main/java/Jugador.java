public class Jugador {

    private boolean Dealer;
    private int Apuesta, Dinero, ValorMano;
    private Mano Mano;
    private String Nombre;

    // Constructor simple

    public Jugador (int dinero, String nombre) {
        this.Dinero = dinero;
        this.Nombre = nombre;
    }

    // Constructor complejo

    public Jugador(int dinero, String nombre, Baraja baraja, boolean Dealer) {
        this.Dinero = dinero;
        this.Nombre = nombre;
        this.Dealer = Dealer;
        this.Mano = new Mano(baraja);
    }

    // Constructor de mano aparte, para cuando se usa el primer constructor

    public void crearMano(Baraja baraja) {
        this.Mano = new Mano(baraja);
    }

    public void limpiarMano() {
        this.Mano = null;
    }

    public void valorarMano(){
        this.ValorMano = 0;
        for (Carta carta : this.Mano.getCartas()) {
            if (carta.getIndice().equals("A")) {
                carta.valorAs(this.esDealer());
            }
            this.ValorMano += carta.getValor();
        }
    }

    // Métodos

    public void apostar() {
        System.out.println("[" + this.Nombre + "]\n"
                            + "¿Cuánta cantidad deseas apostar?"
                            + "\nPuedes apostar hasta $" + this.Dinero + ".");
        this.Apuesta = Utilidades.validarInt(0, this.getDinero());
        this.Dinero -= this.Apuesta;
    }

    public void pedirCarta(Baraja baraja) {
        this.Mano.obtenerCarta(baraja);
        this.valorarMano();
    }

    // Getters

    public boolean esDealer() {
        return this.Dealer;
    }

    public int getApuesta() {
        return this.Apuesta;
    }

    public int getDinero() {
        return this.Dinero;
    }

    public Mano getMano() {
        return this.Mano;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public int getValorMano() {
        return this.ValorMano;
    }

    // Setters

    public void setMano(Mano mano) {
        this.Mano = mano;
    }

    public void setDinero(int dinero) {
        this.Dinero = dinero;
    }

    // toString

    @Override
    public String toString() {
        return this.Nombre
                + "\nFondos: " + this.Dinero
                + "\nCantidad apostada: " + this.Apuesta
                + "\n" + this.Mano.toString();
    }

}
