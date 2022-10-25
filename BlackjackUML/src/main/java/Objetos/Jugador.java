package Objetos;

import Enums.Estado;

public class Jugador {

	private int Apuesta;
	private Enums.Estado Estado;
	private int Fondos;
	private Mano Mano;
	private String Nombre;

	public Jugador(int fondos, String nombre) {
		this.Apuesta = 0;
		this.Estado = Estado.CREADO;
		this.Fondos = fondos;
		this.Mano = new Mano();
		this.Nombre = nombre;
	}

	public void generarMano(Baraja baraja) {
		while (this.Mano.obtenerCantidadCartas() != 2) {
			pedirCarta(baraja);
		}
	}

	public void pedirCarta(Baraja baraja) {
		this.Mano.robarCarta(baraja);
	}

	public boolean tieneBlackjack() {
		if (this.Mano.obtenerCantidadCartas() != 2) {
			return false;
		}
		return this.Mano.contieneAs() && this.Mano.obtenerValor() == 21;
	}

	public int valorarMano() {
		return this.Mano.obtenerValor();
	}

	public int getApuesta() {
		return this.Apuesta;
	}

	public int getCantidadCartas() {
		return this.Mano.obtenerCantidadCartas();
	}

	public Estado getEstado() {
		return this.Estado;
	}

	public int getFondos() {
		return this.Fondos;
	}

	public String getNombre() {
		return this.Nombre;
	}

	public void setApuesta(int apuesta) {
		this.Apuesta = apuesta;
	}

	public void setEstado(Estado estado) {
		this.Estado = estado;
	}

	public void setFondos(int fondos) {
		this.Fondos = fondos;
	}

	public void setMano(Mano mano) {
		this.Mano = mano;
	}

	@Override
	public String toString() {
		return "Nombre : " + this.Nombre
			+ "\n Estado : "  + this.Estado.getEstado()
			+ "\n Fondos : " + this.Fondos
			+ "\n Mano : " + this.Mano.toString();
	}

}