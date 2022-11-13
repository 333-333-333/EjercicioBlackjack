package Juegos;

import Barajas.*;
import java.util.ArrayList;

public abstract class Juego {
    private ArrayList<Jugador> Jugadores;
    private Baraja Baraja;
    private int Fondos, Pozo;

    public abstract void jugar();

    public abstract void turno(Jugador jugador);

    public abstract void ingresarJugador();

    public abstract Jugador crearJugador();

    public abstract void repartirCarta(Jugador jugador);

    public abstract void verificarGanador();

    public void repartirFondos() {
        for (Jugador jugador : this.getJugadores()) {
            switch (jugador.getEstadoJuego()) {
                case GANADOR -> reparteCasa(jugador);
                case PERDEDOR -> recolectaCasa(jugador);
                default -> throw new RuntimeException("Estado no válido.");
            }
        }
    }

    private void reparteCasa(Jugador ganador) {
        ganador.setFondos(ganador.getFondos() + ganador.getApuesta());
        this.Pozo -= ganador.getApuesta();
        ganador.setApuesta(0);
    }

    private void recolectaCasa(Jugador perdedor) {
        perdedor.setFondos(perdedor.getFondos() - perdedor.getApuesta());
        this.Pozo += perdedor.getApuesta();
        perdedor.setApuesta(0);
    }

    public ArrayList<Jugador> getJugadores() {
        return this.Jugadores;
    }

    public Barajas.Baraja getBaraja() {
        return this.Baraja;
    }

    public int getFondos() {
        return this.Fondos;
    }

    public int getPozo() {
        return this.Pozo;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.Jugadores = jugadores;
    }

    public void setBaraja(Barajas.Baraja baraja) {
        this.Baraja = baraja;
    }

    public void setFondos(int fondos) {
        this.Fondos = fondos;
    }

    public void setPozo(int pozo) {
        this.Pozo = pozo;
    }

}
