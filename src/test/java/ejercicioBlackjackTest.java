import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class ejercicioBlackjackTest {

    @Test
    @DisplayName("Para cuando el arreglo de <<Mazo>> no tiene cartas disponibles")
    void testRepartir(){

        Logger logger = Logger.getLogger("ejercicioBlackjack.class");
        String[][] mazoVacio = new String[0][2];

        assertThrows(NegativeArraySizeException.class,
                ()->ejercicioBlackjack.repartir(mazoVacio),
                "La mano está vacia, no pueden restarse menos cartas");

        logger.info("""
                        [Error de arreglo]
                        Índice de arreglo negativo.
                        """);

    }

    @Test
    @DisplayName("Se cubre el caso donde la baraja es una variable del tipo null")
    void testBarajar(){

        Logger logger = Logger.getLogger("ejercicioBlackjack.class");

        assertThrows(NullPointerException.class,
                ()->ejercicioBlackjack.barajar(null),
                "La mano es nula.");

        logger.info("""
                [Error de tipo de objeto]
                La mano es nula.
                """);
    }

    @Test
    @DisplayName("El método se antepone al caso donde la mano del jugador es nula.")
    void testMostrarCartas(){

        Logger logger = Logger.getLogger("ejercicioBlackjack.class");
        assertThrows(NullPointerException.class,
                ()->ejercicioBlackjack.mostrarCartas(null),
                "La mano es nula.");

        logger.info("""
                [Error de tipo de objeto]
                La mano es nula.
                """);

    }

    @Test
    @DisplayName("Prueba cuando la mano inicial es nula.")
    void testPedirCarta(){

        Logger logger = Logger.getLogger("ejercicioBlackjack.class");
        String[][] mazo = ejercicioBlackjack.crearMazo();

        assertThrows(NullPointerException.class,
                ()->ejercicioBlackjack.pedirCarta(mazo, null,true),
                "La mano es nula.");

        logger.info("""
                [Error de tipo de objeto]
                La mano es nula.
                """);
    }

    @Test
    @DisplayName("Prueba cuando se hace pasar un arreglo que no es de strings mediante polimorfismo")
    void testValorMano(){

        Logger logger = Logger.getLogger("ejercicioBlackjack.class");
        Object[] mazo = {-1,null,true};

        assertThrows(ClassCastException.class,
                ()->ejercicioBlackjack.valorMano((String[])mazo),
                "Formato de objetos incorrectos.");

        logger.info("""
                [Error de tipo de objeto]
                El objeto no corresponde al esperado en el valor de entrada.
                """);

    }

    @Test
    @DisplayName("Test para cuando la string que representa una carta no tiene el formato respectivo")
    void testValorCarta(){

        Logger logger = Logger.getLogger("ejercicioBlackjack.class");
        String texto = "prueba";

        assertThrows(NumberFormatException.class,
                ()->ejercicioBlackjack.valorCarta(texto),
                "La carta es erronea..");

        logger.info("""
                [Error de tipo de objeto]
                El formato de la string no está hecho para ser transformado a Int.
                """);

    }

    @Test
    @DisplayName("Test para cuando la mano es nula")
    void testEsBlackjack(){
        Logger logger = Logger.getLogger("ejercicioBlackjack.class");

        assertThrows(NullPointerException.class,
                ()->ejercicioBlackjack.esBlackjack(null ),
                "La mano es nula.");

        logger.info("""
                [Error de tipo de objeto]
                La mano es nula.
                """);

    }

}