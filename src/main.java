//Nota: Se puede programar de forma más eficiente usando Collections, ArrayLists y sobre todo, clases.
import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        mostrarMenu();
    }

    //Objetos globales.
    public static Scanner input = new Scanner(System.in);
    public static String[] pintas = {"Diamante ", "Corazón ", "Pica ", "Trébol "};

    //Para legibibilidad del código, cree las dos siguientes funciones:
    public static int valorCarta(String carta) {
        for (String pinta : pintas) {
            carta = carta.replaceAll(pinta, "");
        }
        return (int) Integer.parseInt(carta);
    }
    public static int valorMano(String[] mano) {
        int[] valoresCarta = new int[mano.length];
        for (int i = 0; i < mano.length; i++) {
            valoresCarta[i] = (int) valorCarta(mano[i]);
        }
        int valorTotal = 0;
        for (int valor : valoresCarta) {
            valorTotal += valor;
        }
        return valorTotal;
    }
    public static void mostrarCartas(String[] mano) {
        for (String carta : mano) {
            System.out.println(carta);
        }
    }

    //Funciones que aparecen en el PDF (Algunas tienen retorno a diferencia de cómo aparece en el documento, ya que por algúna razón los arreglos daban paso por valor).
    public static String[][] crearMazo() {
        String[][] mazo = new String[52][2];
        for (int i = 0; i < 42; ) {
            for (String pinta : pintas) {
                for (int j = 1; j < 14; j++) {
                    mazo[i][0] = pinta;
                    if (j <= 10) {
                        mazo[i][1] = "" + j + "";
                    } else {
                        mazo[i][1] = "10";
                    }
                    i++;
                }
            }
        }

        barajar(mazo);
        return mazo;
    }
    public static String[][] repartir(String[][] mazo) {
        String[][] mazoNuevo = new String[mazo.length - 1][2];
        for (int j = 0; j < mazoNuevo.length; j++) {
            mazoNuevo[j] = mazo[j];
            mazoNuevo[j] = mazo[j];
        }
        return mazoNuevo;

    }
    public static String[] crearMano(String[][] mazo, boolean esHumano) {
        String[] mano = new String[0];
        mano = pedirCarta(mazo, mano, esHumano);

        return mano;
    }
    public static String[] pedirCarta(String[][] mazo, String[] mano, boolean esHumano) {
        String[] aux = new String[mano.length + 1];
        for (int i = 0; i < mano.length; i++) {
            aux[i] = mano[i];
        }

        String carta = "";
        if (!mazo[mazo.length - 1][1].equals("1")) {
            carta = mazo[mazo.length - 1][0] + mazo[mazo.length - 1][1];

        } else {
            boolean preguntarValor = esHumano;
            do {
                System.out.println("¿Que valor deseas que tome el As?\n[A] 1\n[B] 11");
                String opcion = input.nextLine();
                switch (opcion) {
                    case "A":
                        carta = mazo[mazo.length - 1][0] + "1";
                        preguntarValor = false;
                        break;
                    case "B":
                        carta = mazo[mazo.length - 1][0] + "11";
                        preguntarValor = false;
                        break;
                    default:
                        System.out.println("No se ha seleccionado una opción, ingresela nuevamente.");
                }
            } while (preguntarValor);
        }
        if (!esHumano) {
            int valorAs = (int)(Math.random()*2);
            if (valorAs==0){
                carta = mazo[mazo.length - 1][0] + "1";
            } else {
                carta = mazo[mazo.length - 1][0] + "11";
            }
        }
        mano = new String[aux.length];
        for (int i = 0; i < aux.length; i++) {
            mano[i] = aux[i];
        }

        mano[mano.length - 1] = carta;
        return mano;
    }
    public static boolean esBlackjack(String[] mano) {
        boolean retorno = false;
        if (valorMano(mano) == 21) {
            return true;
        }
        if (mano.length != 2) {
            if (valorMano(mano) == 21) {
                retorno = true;
            } else {
                retorno = false;
            }
        } else {
            boolean contiene10 = false;
            boolean contieneAs = false;
            for (String carta : mano) {
                if (valorCarta(carta) == 10) {
                    contiene10 = true;
                }
                if (valorCarta(carta) == 1 || valorCarta(carta) == 11) {
                    contieneAs = true;
                }
                if (contiene10 && contieneAs) {
                    retorno = true;
                    System.out.println("Tienes un As y un 10, tienes mucha suerte.");
                } else {
                    retorno = false;
                }
            }
        }
        return retorno;
    }
    public static boolean verificarGanador(String[] manoJugador, String[] manoCPU) {
        boolean retorno = false;
        System.out.println("\nMano de la CPU:");
        mostrarCartas(manoCPU);
        System.out.println("\nTu mano:");
        mostrarCartas(manoJugador);
        if (valorMano(manoJugador) > 21) {
            System.out.println("Sacaste más de 21 puntos, es más, sacaste " + valorMano(manoJugador));
            retorno = false;
        } else {
            if (valorMano(manoJugador) == valorMano(manoCPU)) {
                System.out.println("La CPU y tu sacaron el mismo valor de Mano (" + valorMano(manoCPU) + ")");
                retorno = false;
            } else {
                if (esBlackjack(manoJugador)) {
                    System.out.println("TUviste Blackjack.");
                    retorno = true;
                } else if (valorMano(manoJugador) > valorMano(manoCPU)) {
                    System.out.println("El valor de tu mano es mayor que el de la CPU (" + valorMano(manoJugador) + " v/s " + valorMano(manoCPU) + ")");
                    retorno = true;
                } else {
                    if (valorMano(manoCPU) <= 21) {
                        System.out.println("El valor de tu mano es menor que el de la CPU (" + valorMano(manoJugador) + " v/s " + valorMano(manoCPU) + ")");
                        retorno = false;
                    } else {
                        System.out.println("El valor de la mano de la CPU se pasó de 21, de hecho, tiene " + valorMano(manoCPU));
                        retorno = true;
                    }

                }
            }
        }
        return retorno;
    }
    public static void bajarse(String[][] mazo, String[] manoJugador, String[] manoCPU) {
        manoCPU = crearMano(mazo, false);
        boolean sacarCPU = true;
        while (sacarCPU) {
            manoCPU = pedirCarta(mazo, manoCPU, true);
            mazo = repartir(mazo);
            if (valorMano(manoCPU)>=21){
                sacarCPU = false;
            } else if (valorMano(manoCPU)>=11 &&valorMano(manoCPU)<21 ){
                int arriesgarse = (int)(Math.random()*2);
                    if (arriesgarse==0) {
                        sacarCPU = false;
                    }
                }
            }
        if (verificarGanador(manoJugador, manoCPU)) {
            System.out.println("Ganaste contra la CPU");
        } else {
            System.out.println("Perdiste contra la CPU.");
        }
    }
    public static void barajar(String[][] mazo) {
        for (int i = 0; i < mazo.length; i++) {
            int aux = (int) (Math.random() * 42);
            String[] temp = mazo[aux];
            mazo[aux] = mazo[i];
            mazo[i] = temp;
        }
    }
    public static void mostrarMenu() {
        System.out.println("¿Qué deseas hacer? \n[A] Jugar \n[Otro] Salir.");
        String opcion = input.nextLine();
        if (opcion.equals("A")) {
            jugar();
        } else {
            System.out.println("Fin del juego.");
        }
    }
    public static void jugar() {
        String[][] mazo = crearMazo();
        String[] manoJugador = crearMano(mazo,true);
        mazo = repartir(mazo);
        String[] manoCPU = new String[0];
        System.out.println("\n[BLACKJACK]\n");
        boolean juego = true;
        do {
            System.out.println("Tus cartas: ");
            mostrarCartas(manoJugador);
            System.out.println("\n¿Que deseas hacer? \n[A] Robar carta \n[B] Bajarse");
            String opcion = input.nextLine();
            switch (opcion) {
                case "A":
                    manoJugador = pedirCarta(mazo, manoJugador, true);
                    mazo = repartir(mazo);
                    System.out.println("Se robó la carta "+manoJugador[manoJugador.length-1]);
                    break;
                case "B":
                    bajarse(mazo, manoJugador, manoCPU);
                    juego = false;
                    break;
                default:
                    System.out.println("No se reconoce la opción.");
            }
            if (esBlackjack(manoJugador)) {
                System.out.println("BlackJack, ganaste!\n");
            }
        } while (juego && valorMano(manoJugador) < 21);
        if (valorMano(manoJugador) > 21) {
            System.out.println("Perdiste.");
        }
        System.out.println("\n[DESPLEGANDO MENÚ]\n");
        mostrarMenu();
    }
}

    /*
    Finalmente, quisiera dejar como duda el cómo se debe comportar la CPU, ya que solamente se explica que debe sacar cartas una vez que
    se baje el jugador, pero no de que forma.
    Alternativamente, había ideado de que sacara cartas al azar con un int randomizado, si el int daba 1, la CPU sacaba, si no, no.
    Luego de esto, para un juego más orgánico, randomizé tanto la eleccion del valor del As como la decisión de seguir sacando si es que los valores de la CPU eran mayores a 11 y menores a 21.
    */
