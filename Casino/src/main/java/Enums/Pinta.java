package Enums;

public enum Pinta {
    DIAMANTE(false, true),
    CORAZÓN(false, true),
    PICA(false, true),
    TREBOL(false, true),
    JOKER(false, true),
    ORO(true, false),
    COPA(true, false),
    ESPADA(true, false),
    BASTO(true, false);

    private boolean Española;
    private boolean Inglesa;

    public boolean esEspañola() {
        return Española;
    }

    public boolean esInglesa() {
        return Inglesa;
    }

    private Pinta(boolean esEspañola, boolean esInglesa) {
        this.Española = esEspañola;
        this.Inglesa = esInglesa;
    }
}
