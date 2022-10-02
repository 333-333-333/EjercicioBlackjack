import java.util.ArrayList;

public class Juego {
    private Baraja Baraja;
    private boolean Jugando;
    private int Dinero;
    private Jugador Dealer;
    private ArrayList<Jugador> Jugadores,
            JugadoresActivos, JugadoresBajados,
            Ganadores, Perdedores;

    public Juego(int dinero) {
        this.Baraja = new Baraja();
        this.Dinero = dinero;

        this.Jugadores = new ArrayList<>();
        this.JugadoresActivos = new ArrayList<>();
        this.JugadoresBajados = new ArrayList<>();

        this.Jugando = false;

        this.Ganadores = new ArrayList<>();
        this.Perdedores = new ArrayList<>();
    }

    // Métodos asociados a las etapas del juego

    public void iniciarJuego() {
        limpiar();
        buscarJugadores();
        jugar();
        terminar();
        repartirApuestas();
        nuevoJuego();
    }

    public void opcionesBuscarJugadores() {
        System.out.println("""
                [Buscando jugadores para unirse a la partida]
                ¿Qué deseas hacer?
                [1] Crear jugador nuevo.
                [2] Añadir jugador existente.
                [3] Iniciar partida.
                """);
    }

    public void buscarJugadores() {
        opcionesBuscarJugadores();
        switch (Utilidades.validarInt(1,3)) {
            case 1 -> {
                crearJugador();
                buscarJugadores();
            }
            case 2 -> {
                ingresarJugador();
                buscarJugadores();
            }
            case 3-> System.out.println("Iniciando juego.");
        }
    }

    public void mostrarJugadores() {
        int indiceJugador = 0;
        for (Jugador registrado : this.Jugadores) {
            System.out.println("["+ indiceJugador +"]\n"
                    + registrado.getNombre()
                    + "\nFondos disponibles : " + registrado.getDinero());
            indiceJugador++;
        }
    }

    public void jugar() {
        this.Jugando = true;
        if (this.JugadoresActivos.size() == 0) {
            System.out.println("No hay jugadores, el juego no puede iniciar.");
        } else {
            while (this.Jugando) {
                for (Jugador jugador : this.JugadoresActivos) {
                    jugador.valorarMano();
                    if (blackjack(jugador) || victoria(jugador)) {
                        aGanadores(jugador);
                        continue;
                    }
                    if (enJuego(jugador)) {
                        turno(jugador);
                    } else {
                        aPerdedores(jugador);
                        continue;
                    }
                }
                if (this.JugadoresActivos.size() == 0) this.Jugando = false;
            }
        }
        System.out.println("El juego ha terminado.");
    }

    public void mostrarMenuTurno() {
        System.out.println("""
                ¿Qué deseas hacer?
                [1] Sacar carta
                [2] Bajarse
                [3] Partir carta (Solo si el valor de la mano es 20 o 21).
                """);
    }

    public void turno(Jugador jugador) {
        System.out.println("[Turno]" + jugador.toString());
        mostrarMenuTurno();

        switch (Utilidades.validarPositivo()) {
            case 1 -> jugador.pedirCarta(this.Baraja);
            case 2 -> bajarJugador(jugador);
            case 3 -> {

            }
            default -> {
                System.out.println("Ingrese una opción válida.");
                turno(jugador);
            }
        }
    }

    public void terminar() {
        generarDealer();
        if (blackjack(this.Dealer) || victoria(this.Dealer)) {
            for (Jugador jugador : this.JugadoresBajados) {
                aPerdedores(jugador);
            }
        } else {
            for (Jugador jugador : this.JugadoresBajados) {
                if (verificarGanador(jugador)) {
                    aGanadores(jugador);
                } else {
                    aPerdedores(jugador);
                }
            }
        }
    }

    public void repartirApuestas() {
        for (Jugador ganador : this.Ganadores) {
            ganador.setDinero(ganador.getDinero() + 2 * ganador.getApuesta());
            this.Dinero -= ganador.getApuesta();
        }
        for (Jugador perdedor : this.Perdedores) {
            this.Dinero += perdedor.getApuesta();
        }
    }

    public void nuevoJuego() {
        System.out.println("""
                ¿Deseas iniciar un nuevo juego?
                [1] Sí.
                [2] No.
                """);
        switch (Utilidades.validarInt(1,2)) {
            case 1 -> {
                iniciarJuego();
            }
            case 2 -> System.out.println("Gracias por jugar <3.");
        }
    }

    public void limpiar() {
        this.JugadoresActivos = new ArrayList<>();
        this.JugadoresBajados = new ArrayList<>();

        this.Ganadores = new ArrayList<>();
        this.Perdedores = new ArrayList<>();

        this.Dealer = null;

        for (Jugador registrado : this.Jugadores) {

        }
    }

    // Métodos asociados al estado de juego.

    public boolean blackjack(Jugador jugador) {
        Mano manoJugador = jugador.getMano();
        if (manoJugador.getCantidadCartas() == 2
        && (manoJugador.contieneIndice("A") && manoJugador.contieneValor(10))){
            System.out.println(jugador.getNombre() + "Tiene blackjack en mano!");
            return true;
        }
        return false;
    }

    public boolean enJuego(Jugador jugador) {
        if (jugador.getValorMano()>21) {
            return false;
        }
        return true;
    }

    private boolean verificarGanador(Jugador jugador) {
        if (jugador.getValorMano() > this.Dealer.getValorMano()
                && jugador.getValorMano()<= 21) {
            return true;
        } return false;
    }

    public boolean victoria(Jugador jugador) {
        if (jugador.getValorMano() == 21) {
            return true;
        }
        return false;
    }

    // Métodos asociados a la gestión de jugadores en partida.

    public void aPerdedores(Jugador jugador) {
        this.JugadoresActivos.remove(jugador);
        this.Perdedores.add(jugador);
    }

    public void aGanadores(Jugador jugador) {
        this.JugadoresActivos.remove(jugador);
        this.Ganadores.add(jugador);
    }

    private void bajarJugador (Jugador jugador) {
        System.out.println(jugador.getNombre() + " se ha bajado.");
        this.JugadoresActivos.remove(jugador);
        this.JugadoresBajados.add(jugador);
    }

    public void ingresarJugador() {
        if (this.Jugadores.size() != 0) {
            mostrarJugadores();
            System.out.println("Seleccione un jugador de la lista escribiendo su índice.");
            int indiceJugador = Utilidades.validarInt(0, this.Jugadores.size() - 1);
            this.Jugadores.get(indiceJugador).apostar();
            jugadorAJuego(this.Jugadores.get(indiceJugador));
        } else {
            System.out.println("No hay jugadores para ingresar, por favor, cree alguno.");
        }
    }

    public void jugadorAJuego(Jugador jugador) {
        jugador.crearMano(this.Baraja);
        this.JugadoresActivos.add(jugador);
    }

    public void crearJugador() {
        System.out.println("¿Cuál es el nombre del jugador?");
        String nombreJugador = Utilidades.validarString();

        System.out.println("¿Con cuánto dinero llega para apostar?");
        int dineroJugador = Utilidades.validarInt();

        this.Jugadores.add(new Jugador(dineroJugador, nombreJugador));
    }

    // Método especial para generar al dealer.

    private void generarDealer() {
        this.Dealer = new Jugador(this.Dinero, "Dealer", this.Baraja, true);
        while(this.Dealer.getValorMano()<=17) {
            this.Dealer.pedirCarta(this.Baraja);
        }
        int randomSacarCarta = 0;
        while (randomSacarCarta == 0) {
            this.Dealer.pedirCarta(this.Baraja);
            randomSacarCarta = (int) (Math.random() * 2);
        }
    }

    // Apartados de strings.

    private String estadoDeJuego() {
        String estadoDeJuego = "[Estado de juego] \n";

        estadoDeJuego += "Jugadores registrados : \n";
        for (Jugador jugador : this.Jugadores) {
            estadoDeJuego += jugador.toString();
        }

        estadoDeJuego += "Jugando : \n";
        for (Jugador jugador : this.JugadoresActivos) {
            estadoDeJuego += jugador.toString();
        }

        estadoDeJuego += "Bajados : \n";
        for (Jugador jugador : this.JugadoresBajados) {
            estadoDeJuego += jugador.toString();
        }

        estadoDeJuego += "Ganadores : \n";
        for (Jugador jugador : this.Ganadores) {
            estadoDeJuego += jugador.toString();
        }

        estadoDeJuego += "Perdedores : \n";
        for (Jugador jugador : this.Perdedores) {
            estadoDeJuego += jugador.toString();
        }

        if (this.Dealer == null) {
            estadoDeJuego += "No hay dealer.\n";
        } else {
            estadoDeJuego += this.Dealer.toString();
        }

        estadoDeJuego += this.Baraja.toString();
        return estadoDeJuego;
    }

    @Override
    public String toString() {
        return estadoDeJuego();
    }


}
