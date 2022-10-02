import java.util.ArrayList;

public class Mano {
    private ArrayList<Carta> Cartas;
    private int CantidadCartas;

    public Mano(Baraja baraja) {
        this.Cartas = new ArrayList<>();
        while (this.Cartas.size()<2) {
            obtenerCarta(baraja);
        }
        this.CantidadCartas = this.Cartas.size();
    }

    public Mano() {
        this.Cartas = new ArrayList<>();
        this.CantidadCartas = 0;
    }

    public ArrayList<Carta> getCartas() {
        return Cartas;
    }

    public void obtenerCarta(Baraja baraja){
        if (baraja.getCantidadCartas() > 0) {
            agregarCarta(baraja.darCarta());
        } else {
            System.out.println("No hay más cartas en la baraja para agregar.");
        }
    }

    public void agregarCarta (Carta carta) {
        this.Cartas.add(carta);
        this.CantidadCartas = this.Cartas.size();
    }

    public int getCantidadCartas() {
        return this.CantidadCartas;
    }

    public boolean contieneIndice(String indice) {
        for (Carta carta : this.Cartas) {
            if (carta.getIndice() == indice) return true;
        }
        return false;
    }

    public boolean contieneValor(int valor) {
        for (Carta carta : this.Cartas) {
            if (carta.getValor() == valor) return true;
        }
        return false;
    }

    @Override
    public String toString(){
        String stringMano = "Cartas en mano: \n";
        for(Carta carta: this.Cartas) {
            stringMano += carta + "\n";
        }
        return stringMano;
    }

}
