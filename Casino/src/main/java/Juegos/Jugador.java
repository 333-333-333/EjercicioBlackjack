package Juegos;

import Enums.*;

public class Jugador {
    private String Nombre;
    private Mano Cartas;
    private Integer Fondos, Apuesta;
    private Estado EstadoJuego;

    public Jugador(String nombre, Integer fondos, Integer apuesta) {
        this.Nombre = nombre;
        this.Cartas = new Mano();
        this.Fondos = fondos;
        this.Apuesta = apuesta;
        this.EstadoJuego = Estado.CREADO;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public Mano getCartas() {
        return this.Cartas;
    }

    public Integer getFondos() {
        return this.Fondos;
    }

    public void setFondos(Integer fondos) {
        this.Fondos = fondos;
    }

    public Integer getApuesta() {
        return this.Apuesta;
    }

    public void setApuesta(Integer apuesta) {
        this.Apuesta = apuesta;
    }

    public Estado getEstadoJuego() {
        return this.EstadoJuego;
    }

    public void setEstadoJuego(Estado estadoJuego) {
        this.EstadoJuego = estadoJuego;
    }
}
