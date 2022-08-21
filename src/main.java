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
        //Para el caso humano:
        public static String preguntarValorAs() {
            System.out.println("¿Que valor deseas que tome el As?\n[A] 1\n[B] 11");
            String opcion = input.nextLine();
            switch (opcion) {
                case "A":
                    return "1";
                case "B":
                    return "11";
                default:
                    System.out.println("No se ha seleccionado una opción, ingresela nuevamente.");
                    return preguntarValorAs();
            }
        }
        //Para el caso CPU:
        public static String preguntarValorAs(int opcion){
            switch ((opcion)){
                case 0:
                    return "1";
                case 1:
                    return "11";
                default:
                    return "-1";
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
            String[] retorno = new String[mano.length + 1];
            for (int i = 0; i < mano.length; i++) {
                retorno[i] = mano[i];
            }
            String carta = "";
            if (!mazo[mazo.length - 1][0].equals("1")) {
                carta = mazo[mazo.length - 1][0];
            } else {
                if (esHumano) {
                    carta = (String) preguntarValorAs();
                } else {
                    int valorAs = (int) (Math.random() * 2);
                    carta = (String) preguntarValorAs(valorAs);
                }
            }
            carta+= mazo[mazo.length - 1][1];
            retorno[retorno.length - 1] = carta;
            return retorno;
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
                if (contiene10 && contieneAs) {
                    System.out.println("Tienes un As y un 10, tienes mucha suerte.");
                }
            }
            return (valorMano(mano)==21 || (mano.length == 2 && (contiene10 && contieneAs)));
        }
        public static boolean verificarGanador(String[] manoJugador, String[] manoCPU) {
            if (valorMano(manoCPU)!=0){
                System.out.println("\nMano de la CPU:");
                mostrarCartas(manoCPU);
                System.out.println("\nTu mano:");
                mostrarCartas(manoJugador);
                System.out.println("\nValor de tu mano vs la mano de la CPU: ["+valorMano(manoJugador)+"] v/s ["+valorMano(manoCPU)+"]");
                return ((valorMano(manoJugador)>valorMano(manoCPU) && valorMano(manoJugador)<=21)||valorMano(manoJugador)<=21 && valorMano(manoCPU)>21);
            } else {
                System.out.println("Valor de tu mano: "+valorMano(manoJugador));
                return (valorMano(manoJugador)<=21);
                }
            }
        public static void bajarse(String[][] mazo, String[] manoJugador, String[] manoCPU) {
            manoCPU = crearMano(mazo, false);
            mazo = repartir(mazo);
            boolean sacarCPU = true;
            while (sacarCPU) {
                manoCPU = pedirCarta(mazo, manoCPU, false);
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
            if (verificarGanador(manoJugador,manoCPU)){
                System.out.println("\nGanaste.");
            } else {
                System.out.println("\nPerdiste.");
            }
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
            } while (juego && valorMano(manoJugador)<=21);
            if(valorMano(manoJugador)>21){
                System.out.println("Perdiste. ");
            }
            mostrarMenu();
        }

}

    /*
    Finalmente, quisiera dejar como duda el cómo se debe comportar la CPU, ya que solamente se explica que debe sacar cartas una vez que
    se baje el jugador, pero no de que forma.
    Alternativamente, había ideado de que sacara cartas al azar con un int randomizado, si el int daba 1, la CPU sacaba, si no, no.
    Luego de esto, para un juego más orgánico, randomizé tanto la eleccion del valor del As como la decisión de seguir sacando si es que los valores de la CPU eran mayores a 11 y menores a 21.
    */
