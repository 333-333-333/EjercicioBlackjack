public class main {

    public static void main(String[] args) {
        Baraja b = new Baraja();
        System.out.println(b.toString());

        Jugador j = new Jugador(50000, "Juan", b, false);
        System.out.println(j.toString());

        Mano m = j.getMano();
        System.out.println(m.toString());

        Juego juego = new Juego(50000);
        System.out.println(juego.toString());
        juego.iniciarJuego();
    }
}
