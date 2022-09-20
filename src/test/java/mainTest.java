import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class mainTest {

    // Test pedir carta.

    @Test
    @DisplayName("Pedir carta, funcionamiento normal.")
    public void testPedirCarta() {
        ArrayList<String> mazo = new ArrayList<>(Arrays.asList("3 de diamante", "4 de corazón"));
        ArrayList<String> mano = new ArrayList<>(Arrays.asList("3 de corazón", "4 de pica", "A de trébol"));
        ArrayList<String> manoTest = new ArrayList<>(Arrays.asList("3 de corazón", "4 de pica", "A de trébol", "3 de diamante"));

        main.pedirCarta(mazo, mano);
        assertEquals(manoTest, mano);
    }

    // Test repartir.

    @Test
    @DisplayName("Repartir carta cuando el mazo está vacío")
    public void testRepartirMazoVacio() {
        ArrayList<String> mazo = new ArrayList<>();
        ArrayList<String> mano = new ArrayList<>();

        assertThrows(IndexOutOfBoundsException.class,
                () -> main.repartir(mazo),
                "Se accedió a un índice del arreglo que no existe");
    }

    @Test
    @DisplayName("Repartir carta cuando la carta es nula")
    public void testRepartirCartaNula() {
        ArrayList<String> mazo = new ArrayList<>();
        mazo.add(null);
        ArrayList<String> mano = new ArrayList<>();

        assertThrows(NullPointerException.class,
                () -> main.repartir(mazo),
                "La carta repartida es nula.");
    }

    @Test
    @DisplayName("Repartir carta con una carta con formato incorrecto")
    public void testRepartirCartaIncorrecta() {
        ArrayList<String> mazo = new ArrayList<>();
        mazo.add("prueba");
        ArrayList<String> mano = new ArrayList<>();
        mano.add("2 de pica");

        assertThrows(IllegalArgumentException.class,
                () -> main.repartir(mazo),
                "La carta tiene un formato inválido");
    }

    // Test mapearValores.

    @Test
    @DisplayName("Para asegurarse que el mapa sea fiel a lo esperado")
    public void testMapearValores() {
        HashMap<String, Integer> mapa = new HashMap<String, Integer>();

        mapa.put("A", 1);
        mapa.put("2", 2);
        mapa.put("3", 3);
        mapa.put("4", 4);
        mapa.put("5", 5);
        mapa.put("6", 6);
        mapa.put("7", 7);
        mapa.put("8", 8);
        mapa.put("9", 9);
        mapa.put("D", 10);
        mapa.put("J", 10);
        mapa.put("Q", 10);
        mapa.put("K", 10);

        assertEquals(mapa, main.mapearValores());
    }

    // Test crearBaraja.

    @Test
    @DisplayName("Se comprueba la fidelidad del método crear baraja")
    public void testCrearBaraja() {
        ArrayList<String> mazoEsperado = new ArrayList<>(Arrays.asList(
                "A de corazón", "A de pica", "A de trébol", "A de diamante",
                "2 de corazón", "2 de pica", "2 de trébol", "2 de diamante",
                "3 de corazón", "3 de pica", "3 de trébol", "3 de diamante",
                "4 de corazón", "4 de pica", "4 de trébol", "4 de diamante",
                "5 de corazón", "5 de pica", "5 de trébol", "5 de diamante",
                "6 de corazón", "6 de pica", "6 de trébol", "6 de diamante",
                "7 de corazón", "7 de pica", "7 de trébol", "7 de diamante",
                "8 de corazón", "8 de pica", "8 de trébol", "8 de diamante",
                "9 de corazón", "9 de pica", "9 de trébol", "9 de diamante",
                "D de corazón", "D de pica", "D de trébol", "D de diamante",
                "J de corazón", "J de pica", "J de trébol", "J de diamante",
                "Q de corazón", "Q de pica", "Q de trébol", "Q de diamante",
                "K de corazón", "K de pica", "K de trébol", "K de diamante"));
        assertNotEquals(mazoEsperado, main.crearBaraja());
    }

    // Test barajar.

    @Test
    @DisplayName("Comprobar la fidelidad de barajar")
    public void testBarajar() {
        ArrayList<String> mazo = main.crearBaraja();
        ArrayList<String> mazoTest = main.crearBaraja();
        main.barajar(mazoTest);

        assertNotEquals(mazo, mazoTest);
    }

    @Test
    @DisplayName("Barajar debe tirar error de índice de arreglo al insertar un mazo vacío.")
    public void testBarajarVacio() {
        ArrayList<String> mazo = new ArrayList<>();

        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> main.barajar(mazo),
                "La baraja está vacía.");
    }

    //Test obtenerIndice.

    @Test
    @DisplayName("Caso default, para comprobar fidelidad")
    public void testObtenerIndice() {
        String carta = "D de pica";
        assertEquals("D", main.obtenerIndice(carta));
    }

    @Test
    @DisplayName("Carta con formáto inválido, índice inexistente")
    public void testObtenerIndiceInexistente() {
        String carta = "O de pica";
        assertThrows(IllegalArgumentException.class,
                () -> main.obtenerIndice(carta),
                "El índice no existe");
    }

    @Test
    @DisplayName("Carta inválida")
    public void testObtenerIndiceCartaInvalida() {
        String carta = "Test";
        assertThrows(IllegalArgumentException.class,
                () -> main.obtenerIndice(carta),
                "Carta inválida");
    }

    @Test
    @DisplayName("Carta nula")
    public void testObtenerIndiceCartaNula() {
        assertThrows(NullPointerException.class,
                () -> main.obtenerIndice(null),
                "Carta nula");
    }

    @Test
    @DisplayName("Carta como string vacía")
    public void testObtenerIndiceCartaVacia() {
        assertThrows(StringIndexOutOfBoundsException.class,
                () -> main.obtenerIndice(""),
                "Carta como string vacía");
    }


    // Test valorarCarta

    @Test
    @DisplayName("Se compru?eba la fidelidad de valorarCarta")
    public void testValorarCarta() {
        String carta = "D de pica";
        assertEquals(10, main.valorarCarta(carta));
    }

    @Test
    @DisplayName("Se comprueba el caso donde la carta no debiese ser válida")
    public void testValorarCartaInvalida() {
        String carta = "test";
        assertThrows(IllegalArgumentException.class,
                () -> main.valorarCarta(carta),
                "La carta tiene un formato inválido.");
    }

    @Test
    @DisplayName("Caso para cuando la carta es nula")
    public void testValorarCartaNula() {
        assertThrows(NullPointerException.class,
                () -> main.valorarCarta(null),
                "La carta es nula");
    }

    // Test valorarMano.

    @Test
    @DisplayName("Valorar mano, caso que debiese funcionar")
    public void testValorarMano() {
        ArrayList<String> mano = new ArrayList<>(Arrays.asList("3 de corazón", "K de diamante", "D de pica"));
        assertEquals(23, main.valorarMano(mano));
    }

    @Test
    @DisplayName("Valorar mano, mano vacía")
    public void testValorarManoVacia() {
        ArrayList<String> manoVacia = new ArrayList<>();
        assertThrows(IndexOutOfBoundsException.class,
                () -> main.valorarMano(manoVacia),
                "Mano vacía.");
    }

    @Test
    @DisplayName("Valorar mano, cartas inválidas")
    public void testValorarManoCartasInvalidas() {
        ArrayList<String> mano = new ArrayList<>(Arrays.asList("Test", "Hola mundo"));
        assertThrows(IllegalArgumentException.class,
                () -> main.valorarMano(mano),
                "Cartas inválidas.");
    }

    //Test esBlackjack.

    @Test
    @DisplayName("[esBlackjack] test para comprobar la fidelidad del método en un caso correcto.")
    public void testEsBlackjackCorrecto() {
        ArrayList<String> mano = new ArrayList<>(Arrays.asList("A de pica", "K de diamante"));
        assertTrue(main.esBlackjack(mano));
    }

    @Test
    @DisplayName("[esBlackjack] Test para comprobar la fidelidad del método en un caso incorrecto.")
    public void testEsBlackjackIncorrecto() {
        ArrayList<String> mano = new ArrayList<>(Arrays.asList("2 de pica", "K de diamante"));
        assertFalse(main.esBlackjack(mano));
    }

    @Test
    @DisplayName("[esBlackjack] Test para comprobar la fidelidad del método con una mano de más de dos cartas.")
    public void testEsBlackjackManoLarga() {
        ArrayList<String> mano = new ArrayList<>(Arrays.asList("2 de pica", "K de diamante", "3 de trébol"));
        assertFalse(main.esBlackjack(mano));
    }

    @Test
    @DisplayName("[esBlackjack] Test para comprobar la fidelidad del método con una mano sin cartas.")
    public void testEsBlackjackManoSinCartas() {
        ArrayList<String> mano = new ArrayList<>();
        assertFalse(main.esBlackjack(mano));
    }

    @Test
    @DisplayName("[esBlackjack] Test para comprobar la fidelidad del método con una mano con carta nula.")
    public void testEsBlackjackManoCartaNula() {
        ArrayList<String> mano = new ArrayList<>(Arrays.asList(null, "K de diamante", "3 de trébol"));
        assertThrows(NullPointerException.class,
                () -> main.esBlackjack(mano),
                "La mano contiene un valor nulo.");
    }

    // Test verificarGanador.

    @Test
    @DisplayName("[verificarGanador] Test para comprobar fidelidad frente a un caso correcto.")
    public void testVerificarGanadorCorrecto() {
        ArrayList<String> manoJugador = new ArrayList<>(Arrays.asList("D de diamante", "D de pica"));
        ArrayList<String> manoDealer = new ArrayList<>(Arrays.asList("D de diamante", "5 de pica"));
        assertTrue(main.verificarGanador(manoJugador,manoDealer));
    }

    @Test
    @DisplayName("[verificarGanador] Test para comprobar fidelidad frente al caso de manos con mismo valor.")
    public void testVerificarGanadorManosMismoValor() {
        ArrayList<String> manoJugador = new ArrayList<>(Arrays.asList("D de diamante", "9 de pica"));
        ArrayList<String> manoDealer = new ArrayList<>(Arrays.asList("9 de diamante", "D de pica"));
        assertFalse(main.verificarGanador(manoJugador,manoDealer));
    }

    @Test
    @DisplayName("[verificarGanador] Test para comprobar fidelidad frente al caso de Dealer con mejor puntuación.")
    public void testVerificarGanadorManosDealerGana() {
        ArrayList<String> manoJugador = new ArrayList<>(Arrays.asList("2 de diamante", "9 de pica"));
        ArrayList<String> manoDealer = new ArrayList<>(Arrays.asList("9 de diamante", "D de pica"));
        assertFalse(main.verificarGanador(manoJugador,manoDealer));
    }

    @Test
    @DisplayName("[verificarGanador] Test para comprobar que tira error cuando las manos contienen null.")
    public void testVerificarGanadorManosConNull() {
        ArrayList<String> manoJugador = new ArrayList<>(Arrays.asList("D de diamante", null));
        ArrayList<String> manoDealer = new ArrayList<>(Arrays.asList("9 de diamante", null));
        assertThrows(NullPointerException.class,
                () -> main.verificarGanador(manoJugador,manoDealer),
                "La(s) mano(s) contiene(n) un valor null");
    }

    @Test
    @DisplayName("[verificarGanador] Test para comprobar que tira error cuando una mano está vacía")
    public void testVerificarGanadorManoVacia() {
        ArrayList<String> manoJugador = new ArrayList<>();
        ArrayList<String> manoDealer = new ArrayList<>(Arrays.asList("9 de diamante", null));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> main.verificarGanador(manoJugador,manoDealer),
                "La(s) mano(s) está(n) vacía(s)");
    }

    // Test partirMano

    /* La situación con As no puede testearse, ya que se necesita un input humano, y los tests al respecto
    son realizados con Mockito, otro framework de testing. */

    @Test
    @DisplayName("[partirMano] Test para comprobar la fidelidad del método con una mano que no sume 20 o 21 sin As")
    public void testPartirManoValorManoInferior() {
        ArrayList<String> manoTest = new ArrayList<>(Arrays.asList("D de diamante", "2 de pica"));
        ArrayList<ArrayList<String>> jugadoresActivos = new ArrayList<>(Arrays.asList(manoTest));


        ArrayList<String> particion1 = new ArrayList<>(Arrays.asList("D de diamante"));
        ArrayList<String> particion2 = new ArrayList<>(Arrays.asList("2 de pica"));
        ArrayList<ArrayList<String>> jugadoresEsperados = new ArrayList<>(Arrays.asList(particion1, particion2));

        main.partirMano(jugadoresActivos, manoTest);
        assertNotEquals(jugadoresEsperados, jugadoresActivos);
    }

    @Test
    @DisplayName("[partirMano] Test para comprobar la fidelidad del método en manos con cantidad de cartas par")
    public void testPartirManoPar() {
        ArrayList<String> manoTest = new ArrayList<>(Arrays.asList("D de diamante", "D de pica"));
        ArrayList<ArrayList<String>> jugadoresActivos = new ArrayList<>(List.of(manoTest));


        ArrayList<String> particion1 = new ArrayList<>(Arrays.asList("D de diamante"));
        ArrayList<String> particion2 = new ArrayList<>(Arrays.asList("D de pica"));
        ArrayList<ArrayList<String>> jugadoresEsperados = new ArrayList<>(Arrays.asList(particion1, particion2));

        main.partirMano(jugadoresActivos, manoTest);
        assertEquals(jugadoresEsperados, jugadoresActivos);
    }

    @Test
    @DisplayName("[partirMano] Test para comprobar la fidelidad del método en manos con cantidad de cartas impar")
    public void testPartirManoImpar() {
        ArrayList<String> manoTest = new ArrayList<>(Arrays.asList("D de diamante", "9 de pica", "2 de trébol"));
        ArrayList<ArrayList<String>> jugadoresActivos = new ArrayList<>(List.of(manoTest));

        ArrayList<String> particion1 = new ArrayList<>(Arrays.asList("D de diamante"));
        ArrayList<String> particion2 = new ArrayList<>(Arrays.asList("9 de pica", "2 de trébol"));
        ArrayList<ArrayList<String>> jugadoresEsperados = new ArrayList<>(Arrays.asList(particion1, particion2));

        main.partirMano(jugadoresActivos, manoTest);
        assertEquals(jugadoresEsperados, jugadoresActivos);
    }

    @Test
    @DisplayName("[partirMano] Test para comprobar si tira error cuando uno de los arreglos está vacío")
    public void testPartirManoArregloVacio() {
        ArrayList<String> manoTest = new ArrayList<>();
        ArrayList<ArrayList<String>> jugadoresActivos = new ArrayList<>(List.of(manoTest));

        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> main.partirMano(jugadoresActivos, manoTest),
                "Uno de los arreglos está vacío.");
    }

    @Test
    @DisplayName("[partirMano] Test para comprobar si tira error cuando uno de los arreglos tiene un null")
    public void testPartirManoArregloConNull() {
        ArrayList<String> manoTest = null;
        ArrayList<ArrayList<String>> jugadoresActivos = new ArrayList<>();
        jugadoresActivos.add(manoTest);

        assertThrows(NullPointerException.class,
                () -> main.partirMano(jugadoresActivos, manoTest),
                "Uno de los arreglos es un null.");
    }
}

    //Los test de inputs se hacen con Mockito, el curso no aborda el procedimiento que se debe hacer con JUnit.

