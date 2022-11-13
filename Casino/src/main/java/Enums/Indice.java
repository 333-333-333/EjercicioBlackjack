package Enums;

public enum Indice {
    AS(true, true),
    DOS(true, true),
    TRES(true, true),
    CUATRO(true, true),
    CINCO(true, true),
    SEIS(true, true),
    SIETE(true, true),
    OCHO(true, true),
    NUEVE(true, true),
    DIEZ(true, true),
    ONCE(true, false),
    DOCE(true, false),
    JOTA(false, true),
    QUINA(false, true),
    KAISER(false, true ),
    JOKER(false, true);
    ;
    private boolean Española;
    private boolean Inglesa;

    public boolean esEspañola() {
        return this.Española;
    }

    public boolean esInglesa() {
        return this.Inglesa;
    }


    private Indice(boolean esEspañola, boolean esInglesa) {
        this.Española = esEspañola;
        this.Inglesa = esInglesa;
    }
}

