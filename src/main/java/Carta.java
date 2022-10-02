public class Carta {
    private String Indice, Pinta;
    private int Valor;

    public Carta(String indice, String pinta, int valor) {
        this.Indice = indice;
        this.Pinta = pinta;
        this.Valor = valor;
    }

    public void valorAs(boolean Dealer) {
        if (this.Indice.equals("A")) {
            if (Dealer) {
                int opcionValor = (int) (Math.random() * 2);
                switch (opcionValor) {
                    case 0 -> this.Valor = 1;
                    case 1 -> this.Valor = 11;
                }
            } else {
                System.out.println("""
                        Selecciona para el valor de A:
                        [0] 1
                        [1] 11
                        """);
                switch (Utilidades.validarInt(0,1)){
                    case 0 -> this.Valor = 1;
                    case 1 -> this.Valor = 11;
                }
            }
        }
    }

    public String getIndice() {
        return this.Indice;
    }

    public int getValor() {
        return this.Valor;
    }

    @Override
    public String toString() {
        return this.Indice + " de " + this.Pinta
                + " [Valor = "+ this.Valor +"]";
    }

}
