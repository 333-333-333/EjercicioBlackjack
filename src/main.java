//Nota: Se puede programar de forma más eficiente usando Collections, ArrayLists y sobre todo, clases.

import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        mostrarMenu();
    }

    //Objetos globales.
        public static Scanner input = new Scanner(System.in);
        public static String[] pintas = {" de diamante", " de corazón", " de pica", " de trébol"};
    //Para legibibilidad del código, cree las dos siguientes funciones:
        public static int valorCarta(String carta) {
            for (String pinta : pintas) {
                carta = carta.replaceAll(pinta, "");
            }
            return Integer.parseInt(carta);
        }
        public static int valorMano(String[] mano) {
            int[] valoresCarta = new int[mano.length];
            for (int i = 0; i < mano.length; i++) {
                valoresCarta[i] = valorCarta(mano[i]);
            }
            int valorTotal = 0;
            for (int valor : valoresCarta) {
                valorTotal += valor;
            }
            return valorTotal;
        }
        //Para el caso humano:
        public static String preguntarValorAs(boolean esHumano) {
            if (esHumano) {
                System.out.println("¿Que valor deseas que tome el As?\n[A] 1\n[B] 11");
                String opcion = input.nextLine();
                return switch (opcion) {
                    case "A" -> "1";
                    case "B" -> "11";
                    default -> preguntarValorAs(true);
                };
            } else {
                int i = (int) (Math.random()*2);
                return switch (i) {
                    case 0 -> "1";
                    case 1 -> "11";
                    default -> "-1";
                };
            }
        }
        public static void mostrarCartas(String[] mano) {
            for (String carta : mano) {
                System.out.println(carta);
            }
        }
        public static void mostrarMazo(String[][] mazo){
            for (String[] datosOrdenados : mazo){
                System.out.println(datosOrdenados[0]+" "+datosOrdenados[1]);
            }
        }
    //Funciones que aparecen en el PDF (Algunas tienen retorno a diferencia de cómo aparece en el documento, ya que por algúna razón los arreglos daban paso por valor).
        public static String[][] crearMazo() {
            String[][] mazo = new String[52][2];
            for (int i = 0; i < 52; ) {
                for (String pinta : pintas) {
                    for (int j = 1; j < 14; j++) {
                        mazo[i][1] = pinta;
                        if (j < 10) {
                            mazo[i][0] = ""+j+"";
                        } else {
                            mazo[i][0] = "10";
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
            System.arraycopy(mazo, 0, mazoNuevo, 0, mazoNuevo.length);
            return mazoNuevo;
        }
        public static String[] pedirCarta(String[][] mazo, String[] mano, boolean esHumano) {
            String[] manoNueva = new String[mano.length + 1];
            System.arraycopy(mano, 0, manoNueva, 0, mano.length);
            String carta = "";
            if ("1".equals(mazo[mazo.length - 1][0])) {
                carta += preguntarValorAs(esHumano) + mazo[mazo.length - 1][1];
            } else {
                carta += mazo[mazo.length - 1][0] + mazo[mazo.length - 1][1];
            }
            manoNueva[manoNueva.length - 1] = carta;
            return manoNueva;
        }
        public static String[] crearMano(String[][] mazo, boolean esHumano) {
            return pedirCarta(mazo, new String[0], esHumano);
        }
        public static boolean verificarGanador(String[] manoJugador, String[] manoCPU) {
            switch (valorMano(manoCPU)) {
                case 0 -> {
                    System.out.println("Valor de tu mano: " + valorMano(manoJugador));
                    return (valorMano(manoJugador) <= 21);
                }
                case 11, 21 -> {
                    System.out.println("\nMano de la CPU:");
                    mostrarCartas(manoCPU);
                    System.out.println("\nTu mano:");
                    mostrarCartas(manoJugador);
                    return (!esBlackjack(manoCPU) && esBlackjack(manoJugador));
                }
                default -> {
                    System.out.println("\nMano de la CPU:");
                    mostrarCartas(manoCPU);
                    System.out.println("\nTu mano:");
                    mostrarCartas(manoJugador);
                    return ((valorMano(manoJugador) <= 21 && (valorMano(manoJugador) > valorMano(manoCPU) || valorMano(manoCPU) >= 21)));
                }
            }
        }
        public static boolean esBlackjack(String[] mano) {
            boolean contiene10 = false;
            boolean contieneAs = false;
            for (String carta : mano) {
                if (valorCarta(carta) == 10) {
                    contiene10 = true;
                }
                if (valorCarta(carta) == 1 || valorCarta(carta) == 11) {
                    contieneAs = true;
                }
            }
            return (valorMano(mano)==21 || (mano.length == 2 && (contiene10 && contieneAs)));
        }
        public static void bajarse(String[][] mazo, String[] manoJugador) {
            String[] manoCPU = crearMano(mazo, false);
            mazo = repartir(mazo);
            int arriesgarse = 0;
            while (valorMano(manoCPU)<21 && arriesgarse==0) {
                manoCPU = pedirCarta(mazo, manoCPU, false);
                mazo = repartir(mazo);
                if (valorMano(manoCPU)>=17 &&valorMano(manoCPU)<21 ){
                    arriesgarse = (int)(Math.random()*2);
                }
            }
            if (verificarGanador(manoJugador, manoCPU)){
                System.out.println("\nGanaste.");
            } else {
                System.out.println("\nPerdiste.");
            }
        }
        public static void jugar(String[][] mazo, String[] manoJugador) {
            boolean juego = true;
            do {
                System.out.println("Tus cartas: ");
                mostrarCartas(manoJugador);
                System.out.println("\n¿Que deseas hacer? \n[A] Robar carta \n[B] Bajarse ");
                switch (input.nextLine()) {
                    case "A" -> {
                        manoJugador = pedirCarta(mazo, manoJugador, true);
                        mazo = repartir(mazo);
                        System.out.println("Se robó la carta " + manoJugador[manoJugador.length - 1]);
                    }
                    case "B" -> {
                        bajarse(mazo, manoJugador);
                        juego = false;
                    }
                    default -> System.out.println("No se reconoce la opción.");
                }
                if (juego) {
                    System.out.println("Valor de tu mano: "+valorMano(manoJugador));
                }
            } while ((juego && valorMano(manoJugador)<=21) && !esBlackjack(manoJugador));
            mostrarMenu();
        }
        public static void barajar(String[][] mazo) {
            for (int i = 0; i < mazo.length; i++) {
                int aux = (int) (Math.random() * 52);
                String[] temp = mazo[aux];
                mazo[aux] = mazo[i];
                mazo[i] = temp;
            }
        }
        public static void mostrarMenu() {
            System.out.println("\n[MOSTRANDO MENÚ] \n¿Qué deseas hacer? \n[A] Jugar \n[Otro] Salir.");
            String opcion = input.nextLine();
            if (opcion.equals("A")) {
                String[][] mazo = crearMazo();
                String[] manoJugador = crearMano(mazo,true);
                mazo = repartir(mazo);
                jugar(mazo, manoJugador);
            } else {
                System.out.println("Fin del juego.");
            }
        }

}
    /*
    Finalmente, quisiera dejar como duda el cómo se debe comportar la CPU, ya que solamente se explica que debe sacar cartas una vez que
    se baje el jugador, pero no de que forma.
    Alternativamente, había ideado de que sacara cartas al azar con un int randomizado, si el int daba 1, la CPU sacaba, si no, no.
    Luego de esto, para un juego más orgánico, randomizé tanto la eleccion del valor del As como la decisión de seguir sacando si es que los valores de la CPU eran mayores a 11 y menores a 21.
    */
