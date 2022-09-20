import java.util.*;

public class main {

    public static void main(String[] args) {
        iniciarJuego();
    }

    // Se inicializan los datos requeridos para el juego
    public static void iniciarJuego() {
        try {
            ArrayList<String> baraja = crearBaraja();
            ArrayList<String> primerJugador = crearMano(baraja);
            ArrayList<ArrayList<String>> jugadoresActivos = new ArrayList<>();
            jugadoresActivos.add(primerJugador);
            boolean partioMano = false;
            jugar(baraja, jugadoresActivos, partioMano);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Mientras la lista de jugadores activo no esté vacía, se sigue jugando.
    // Esta se vacía a medida que los jugadores pierdan, ganen, o se bajen.
    public static void jugar(ArrayList<String> baraja,
                             ArrayList<ArrayList<String>> jugadoresActivos,
                             boolean partioMano) {
        do {
            // No se itera sobre la lista de jugadores activos, ya que causa la exepción CurrentModificationException.
            int cantidadJugadoresActivos = jugadoresActivos.size();
            for (int i = 0; i < cantidadJugadoresActivos; i++) {
                ArrayList<String> manoJugador = jugadoresActivos.get(i);
                int valorMano = valorarMano(manoJugador);

                if (valorMano == 21) {
                    if (esBlackjack(manoJugador)) System.out.println("Este jugador tiene blackjack en mano!");
                    else System.out.println("Ganaste!");
                    jugadoresActivos.remove(manoJugador);
                }
                else if (valorMano < 21) {
                    System.out.println("Valor de tu mano : "+valorMano);
                    mostrarMano(manoJugador);
                    mostrarMenu();
                    preguntarOpciones(baraja, jugadoresActivos, manoJugador, partioMano);
                }
                else {
                    System.out.println("Perdiste");
                    jugadoresActivos.remove(manoJugador);
                    }
                }
            cantidadJugadoresActivos = jugadoresActivos.size();
        } while (jugadoresActivos.size()!=0);
    }

    // Apartado gráfico de las opciones.
    public static void mostrarMenu() {
        System.out.println("""
                ¿Qué deseas hacer?
                [1] Robar carta
                [2] Bajarse
                [3] Partir mano (Solo posible si tu mano inicial vale 20 o 21)
                """);
    }

    // Switch de opciones.
    public static void preguntarOpciones(ArrayList<String> baraja,
                                         ArrayList<ArrayList<String>> jugadoresActivos,
                                         ArrayList<String> manoJugador,
                                         boolean partioMano) {
        switch (validarInt()) {
            case 1 -> pedirCarta(baraja, manoJugador);
            case 2 -> {
                ArrayList<String> manoDealer = new ArrayList<>();
                generarManoDealer(baraja, manoDealer);
                bajarse(jugadoresActivos, manoDealer);
            }
            case 3 -> partirMano(jugadoresActivos, manoJugador, partioMano);
            default -> preguntarOpciones(baraja, jugadoresActivos, manoJugador, partioMano);
        }
    }

    // Función de partir mano (Recién añadida)
    public static void partirMano(ArrayList<ArrayList<String>> jugadoresActivos,
                                  ArrayList<String> manoJugador,
                                  boolean partioMano) {
        if (jugadoresActivos == null || manoJugador == null) {
            throw new NullPointerException("Hay un valor nulo en las variables de entrada");
        }
        if (jugadoresActivos.size() == 0 || manoJugador.size()== 0) {
            throw new ArrayIndexOutOfBoundsException("Uno,u ambos arreglos, están vacíos");
        }

        int valorMano = valorarMano(manoJugador);
        if (jugadoresActivos.size()==1 && (valorMano== 20 ||valorMano==21) && !partioMano) {
            int mitadMano = (int) (manoJugador.size()/2);
            jugadoresActivos.remove(0);
            ArrayList<String> particion1 = new ArrayList<>(manoJugador.subList(0, mitadMano));
            jugadoresActivos.add(particion1);
            ArrayList<String> particion2 = new ArrayList<>(manoJugador.subList(mitadMano, manoJugador.size()));
            jugadoresActivos.add(particion2);
            partioMano = true;
        } else {
            System.out.println("La mano no se puede partir!");
        }
    }

    // Bajarse del juego: Se bajan ambas manos (Si es que se juegan)
    public static void bajarse(ArrayList<ArrayList<String>> jugadoresActivos, ArrayList<String> manoDealer) {
        System.out.println("Mano del dealer:");
        mostrarMano(manoDealer);

        ArrayList<ArrayList<String>> manosGanadoras = new ArrayList<>();
        for (ArrayList<String> manoJugador : jugadoresActivos) {
            if (verificarGanador(manoJugador, manoDealer)) {
                manosGanadoras.add(manoJugador);
            }
        }

        if(manosGanadoras.size()!= 0) {
            System.out.println("Ha(n) ganado la(s) siguiente(s) mano(s) contra la casa: ");
            for (ArrayList<String> manoJugador : manosGanadoras) {
                System.out.println("-");
                mostrarMano(manoJugador);
            }
        } else System.out.println("Ganó la casa.");

        jugadoresActivos.clear();
    }

    public static void generarManoDealer(ArrayList<String> baraja, ArrayList<String> manoDealer) {
        int valorAleatorioMinimo = (int) (Math.random() * 4) + 17;
        do {
            pedirCarta(baraja, manoDealer);
        } while (valorarManoAuto(manoDealer) < valorAleatorioMinimo);
    }

    // Itera sobre la mano e imprime las cartas.
    public static void mostrarMano(ArrayList<String> mano) {
        try {
            for (String carta : mano) {
                System.out.println(carta);
            }
        } catch (NullPointerException e) {
            System.out.println("Hay un valor nulo en la mano.");
        }
    }

    // Si es verdadero, gana el jugador, si es falso, la casa.
    public static boolean verificarGanador(ArrayList<String> manoJugador, ArrayList<String> manoDealer) {
        int valorJugador = valorarMano(manoJugador);
        int valorDealer = valorarManoAuto(manoDealer);
        if (manoDealer.size()== 0 || manoJugador.size() == 0) {
            throw new ArrayIndexOutOfBoundsException("Mano(s) vacía(s)");
        }

        if (manoDealer.contains(null) || manoJugador.contains(null)) {
            throw new NullPointerException("La(s) mano(s) contiene(n) al menos un valor nulo.");
        }

        return valorJugador<21 && (valorJugador>valorDealer || valorDealer>21) ;
    }

    // Verifica si se tiene blackjack en mano (2 cartas, una de ellas es un as, otra vale 10).
    public static boolean esBlackjack(ArrayList<String> mano) {
        if (mano.contains(null)) throw new NullPointerException("La mano contiene un valor nulo.");

        if (mano.size() == 2) {
            ArrayList<String> indices = new ArrayList<>();
            for (String carta : mano) {
                indices.add(obtenerIndice(carta));
            }
            return indices.contains("A") && (valorarManoAuto(mano) == 11 || valorarManoAuto(mano) == 21);
            }

        return false;
    }

    // Pedir carta.
    public static void pedirCarta(ArrayList<String> baraja, ArrayList<String> mano) {
        try {
            mano.add(repartir(baraja));
        }
        catch (NullPointerException | ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Para valorar la mano en base a un mapa de índices.
    public static int valorarMano(ArrayList <String> mano) {
        if (mano.size() == 0) throw new IndexOutOfBoundsException("El arreglo no tiene cartas.");

        int valorMano = 0;
        for (String carta : mano) {
            if (valorarCarta(carta) == 1){
                valorMano += preguntarValorAs();
            } else {
                valorMano += valorarCarta(carta);
            }
        }

        return valorMano;
    }

    // La CPU no toma una decisión orgánica de valorar el as como 1 u 11, entonces, lo hace de forma aleatoria.
    public static int valorarManoAuto(ArrayList <String> mano) {
        if (mano.size() == 0) throw new IndexOutOfBoundsException("El arreglo no tiene cartas.");

        int valorMano = 0;
        for (String carta : mano) {
            if (valorarCarta(carta) == 1){
                int opcion = (int) (Math.random() * 2);
                switch (opcion) {
                    case 0 -> valorMano+=1;
                    case 1 -> valorMano+=11;
                }
            } else {
                valorMano += valorarCarta(carta);
            }
        }

        return valorMano;
    }

    // El usuario toma la desición de valorar el As como 1 u 11.
    public static int preguntarValorAs() {
        try {
            System.out.println("¿Qué valor deseas que tenga el ás? (1 u 11) ");
            int valorAs = validarInt();
            return (valorAs == 1 || valorAs == 11) ? valorAs : preguntarValorAs();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            return preguntarValorAs();
        }
    }

    // Valora las cartas con un número en base a un mapa.
    public static int valorarCarta(String carta) {
        HashMap<String, Integer> valoresIndice = mapearValores();
        String indice = obtenerIndice(carta);

        return valoresIndice.get(indice);
    }

    // Toma las cartas como string literales, y extrae el índice.
    public static String obtenerIndice(String carta) {
        HashMap<String, Integer> valoresIndice = mapearValores();
        String indice = String.valueOf(carta.charAt(0));

        if (carta == null) throw new NullPointerException("No hay carta.");
        if (!valoresIndice.containsKey(indice)) throw new IllegalArgumentException("El índice no es válido.");
        if (carta.equals("")) throw new StringIndexOutOfBoundsException("La carta es una string vacía");

        return indice;
    }

    // Mapea los valores que tienen los índices de las cartas.
    public static HashMap<String, Integer> mapearValores() {
        HashMap<String, Integer> mapaValores = new HashMap<>();

        String[] indices = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "D", "J", "Q", "K"};
        int valor = 1;

        for (String indice : indices) {
            mapaValores.put(indice, valor);
            if (valor<10) {
                valor++;
            }
        }

        return mapaValores;
    }

    // Se extrae una carta del mazo.
    public static String repartir(ArrayList<String> baraja) {
        String carta = baraja.get(0);

        if (carta == null) throw new NullPointerException("La carta es nula");
        if (baraja.size() == 0) throw new ArrayIndexOutOfBoundsException("La baraja está vacía.");
        if (!mapearValores().containsKey(obtenerIndice(carta))) throw new IllegalArgumentException("Carta inválida");

        baraja.remove(0);
        System.out.println("Carta repartida : " + carta);
        return carta;
    }

    // Se sacan dos cartas del mazo y se crea una mano
    public static ArrayList<String> crearMano(ArrayList<String> mazo) {
        ArrayList<String> mano = new ArrayList<>();
        do {
            pedirCarta(mazo, mano);
        } while (mano.size()<2);
        return mano;
    }

    // Se revuelve el mazo.
    public static void barajar(ArrayList<String> baraja) {
        if (baraja.size()==0) throw new ArrayIndexOutOfBoundsException("La baraja está vacía.");
        Collections.shuffle(baraja);
    }

    // Se crea un mazo.
    public static ArrayList<String> crearBaraja() {
        ArrayList<String> baraja = new ArrayList<>();
        String[] indices = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "D", "J", "Q", "K"};
        String[] pintas = {" de corazón", " de pica", " de trébol", " de diamante"};

        for (String indice : indices) {
            for (String pinta : pintas) {
                baraja.add(indice + pinta);
            }
        }
        barajar(baraja);
        return baraja;
    }

    // Validar un input de int.
    public static int validarInt() {
        Scanner input = new Scanner(System.in);
        try {
            return input.nextInt();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("No se ha ingresado un número entero.");
        }
    }

}
