package Interfaces;
import Enums.*;
import Objetos.*;
import Utilidades.Validaciones;

import java.util.List;

public class Juego {

	private Baraja Baraja;
	private int Apuesta, Fondos;
	private List<Jugador> Jugadores;

	public Juego () {
		this.Baraja = new Baraja();
		this.Fondos = 0;
		this.Jugadores.clear();
		crearJuego();
	}

	public void bajarse(Jugador jugador) {
		jugador.setEstado(Estado.BAJADO);
	}

	private void crearJuego() {
		try {
			this.Jugadores.clear();
			establecerFondos();
			ingresarJugadores();
			iniciar();
		} catch (Exception e) {
			System.err.println("[Error]\n" + e.getMessage());
			System.out.println("Se creará nuevamente la partida");
			crearJuego();
		}
	}

	private void establecerFondos() {
		System.out.println("¿Cuántos fondos le deseas aportar a la partida?");
		this.Fondos = Validaciones.validarPositivo();
	}

	private void ingresarJugadores() {
		do {
			Jugador nuevo = crearJugador();
			establecerApuesta(nuevo);
			ingresarJugador(nuevo);
		} while (seguirIngresandoJugadores());
	}

	private boolean seguirIngresandoJugadores() {
		System.out.println("¿Deseas añadir más jugadores? [1] Si, [2] No");
		switch (Validaciones.validarInt(1, 2)) {
			case 1: return true;
			case 2: return false;
			default: return seguirIngresandoJugadores();
		}
	}

	private Jugador crearJugador() {
		System.out.println("¿Qué nombre debe tener el jugador?");
		String nombre = Validaciones.validarString();
		System.out.println("¿Cuántos fondos deseas asignarles al jugador?");
		int fondos = Validaciones.validarPositivo();
		return new Jugador(false, fondos, nombre);
	}

	private void ingresarJugador(Jugador jugador) {
		jugador.setEstado(Estado.ACTIVO);
		jugador.generarMano(this.Baraja);
		this.Jugadores.add(jugador);
	}

	private void establecerApuesta(Jugador nuevo) {
		System.out.println("¿Cuánto deseas apostar?");
		if (this.Fondos < nuevo.getFondos()) {
			nuevo.setApuesta(Validaciones.validarInt(0, this.Fondos));
		} else {
			nuevo.setApuesta(Validaciones.validarInt(0, nuevo.getFondos()));
		}
		this.Fondos -= nuevo.getApuesta();
		this.Apuesta += nuevo.getApuesta();
	}

	private void iniciar() {
		if (!hayJugadoresActivos()) {
			throw new ArrayIndexOutOfBoundsException("No hay jugadores");
		}
		while (hayJugadoresActivos()) {
			for (Jugador jugador : this.Jugadores) {
				turno(jugador);
			}
		}
		terminar();
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



	public void turno(Jugador jugador) {
		try {
			establecerEstado(jugador);
			if (jugador.getEstado() == Estado.ACTIVO) {
				mostrarOpcionesTurno();
				seleccionarOpcionTurno(jugador);
			} else {
				System.out.println("El jugador ");
			}
		} catch (Exception e) {
			System.err.println("[Error]\n" + e.getMessage());
			turno(jugador);
		}
	}

	private void mostrarOpcionesTurno() {
		System.out.println("""
				¿Qué deseas hacer?
    			
				[1] Repartir carta
				[2] Bajarse
				""");
	}

	private void seleccionarOpcionTurno(Jugador jugador) {
		switch (Validaciones.validarInt(1,2)) {
			case 1: repartir(jugador);
			case 2: bajarse(jugador);
			default: turno(jugador);
		}
 	}

	private void repartir(Jugador jugador) {
		jugador.pedirCarta(this.Baraja);
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
		this.Fondos += this.Apuesta;
		this.Apuesta = 0;
	}

	private void repartirApuesta(Jugador jugador) {
		if (jugador.getEstado() == Estado.GANADOR) {
			jugador.setFondos(jugador.getFondos() + jugador.getApuesta());
			this.Apuesta -= jugador.getApuesta();
		}
		if (jugador.getEstado() == Estado.PERDEDOR) {
			jugador.setFondos(jugador.getFondos() - jugador.getApuesta());
			this.Apuesta += jugador.getApuesta();
		}
		jugador.setApuesta(0);
	}

}