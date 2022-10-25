package Interfaces;
import Enums.*;
import Objetos.*;

import java.util.List;

public class Juego {

	private Baraja Baraja;
	private int Fondos;
	private List<Jugador> Jugadores;

	public Juego () {
		this.Baraja = new Baraja();
		this.Fondos = 0;
		iniciar();
	}

	public void bajarse(Jugador jugador) {
		jugador.setEstado(Estado.BAJADO);
	}

	private boolean hayJugadoresActivos() {
		boolean hayJugadoresActivos = false;
		for (Jugador jugador : this.Jugadores) {
			if (jugador.getEstado() == Estado.ACTIVO) {
				hayJugadoresActivos = true;
			}
		}
		return hayJugadoresActivos;
	}

	public void iniciar() {

		while (hayJugadoresActivos()) {
			for (Jugador jugador : this.Jugadores) {
				turno(jugador);
			}
		}
		terminar();
	}

	public void turno(Jugador jugador) {
		establecerEstado(jugador);
		if (jugador.getEstado() == Estado.ACTIVO) {

		} else {
			System.out.println("El jugador ");
		}
	}

	private void establecerEstado(Jugador jugador) {
		verificarGanador(jugador);
		verificarPerdedor(jugador);
	}

	private void verificarGanador(Jugador jugador) {
		if (jugador.tieneBlackjack() || jugador.valorarMano() == 21) {
			jugador.setEstado(Estado.GANADOR);
		}
	}

	private void verificarPerdedor(Jugador jugador) {
		if (jugador.valorarMano()>21) {
			System.out.println("El jugador " );
			jugador.setEstado(Estado.PERDEDOR);
		}
	}

	public void terminar() {
		for (Jugador jugador : this.Jugadores) {
			repartirApuesta(jugador);
			System.out.println(jugador.toString());
		}
		this.Jugadores.clear();
	}

	private void repartirApuesta(Jugador jugador) {
		if (jugador.getEstado() == Estado.GANADOR) {
			jugador.setFondos(jugador.getFondos() + jugador.getApuesta());
			this.Fondos -= jugador.getApuesta();
		}
		if (jugador.getEstado() == Estado.PERDEDOR) {
			jugador.setFondos(jugador.getFondos() - jugador.getApuesta());
			this.Fondos += jugador.getApuesta();
		}
		jugador.setApuesta(0);
	}

}