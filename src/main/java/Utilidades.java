import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilidades {

    public static int validarInt() {
        Scanner input = new Scanner(System.in);
        try {
            return input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("No se ha ingresado un número,");
            input.nextLine();
            return validarInt();
        }
    }

    public static int validarInt(int cotaInferior, int cotaSuperior) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Inserte un número entre "
                                + cotaInferior + " y " + cotaSuperior );
            int retorno = input.nextInt();
            return ((retorno >= cotaInferior) && (retorno <= cotaSuperior))
                    ? retorno : validarInt(cotaInferior, cotaSuperior);
        } catch (InputMismatchException e) {
            System.out.println("No se ha ingresado un número,");
            input.nextLine();
            return validarInt(cotaInferior, cotaSuperior);
        }
    }

    public static int validarPositivo() {
        Scanner input = new Scanner(System.in);
        try {
            int retorno = input.nextInt();
            return (retorno > 0) ? retorno : validarPositivo();
        } catch (InputMismatchException e) {
            System.out.println("No se ha ingresado un número,");
            input.nextLine();
            return validarInt();
        }
    }

    public static String validarString() {
        Scanner input = new Scanner(System.in);
        try {
            return input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("No se ha ingresado texto,");
            return validarString();
        }
    }
}
