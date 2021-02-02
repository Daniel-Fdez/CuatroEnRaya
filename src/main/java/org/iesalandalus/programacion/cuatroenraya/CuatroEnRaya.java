package org.iesalandalus.programacion.cuatroenraya;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.cuatroenraya.modelo.Tablero;
import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

public class CuatroEnRaya {
	private static final int NUMERO_JUGADORES = 2;
	private Jugador[] jugadores;
	private Tablero tablero;
	
	// Constructor al que se le pasan dos instancias de jugador para crearlos y crea la instancia de la clase Tablero
	public CuatroEnRaya(Jugador jugador1, Jugador jugador2) {
		if(jugador1 == null) {
			throw new NullPointerException("ERROR: El nombre del jugador no puede quedarse vacío.");
		}
		
		if(jugador2 == null) {
			throw new NullPointerException("ERROR: El nombre del jugador no puede quedarse vacío.");
		}
		
		jugadores = new Jugador[NUMERO_JUGADORES];
		jugadores[0] = jugador1;
		jugadores[1] = jugador2;
		
		tablero = new Tablero();
	}
	
	// Método que recibe el jugador que va a tirar y comprueba si la jugada ha sido ganadora o no
	private boolean tirar(Jugador jugador) {
		boolean partidaGanadora = false;
		boolean partidaValida = false;
		
		do {
			try {
				partidaGanadora = tablero.introducirFicha(Consola.leerColumna(jugador), jugador.getColorFichas());
				System.out.printf("%n%s%n", tablero);
				partidaValida = true;
			} catch(OperationNotSupportedException e) {
				System.out.println(e.getMessage());
				partidaValida = false;
			}
			
		} while(!partidaValida);
		
		return partidaGanadora;
	}
	
	// Método que mantiene el juego mientras tengamos casillas libres y no haya ganador
	public void jugar() {
		int quienJuega = 0;
		boolean seHaGanado = false;
		
		Jugador jugadorJugando = jugadores[quienJuega];
		
		while(!tablero.estaLleno() && !seHaGanado) {
			jugadorJugando = jugadores[quienJuega++ % NUMERO_JUGADORES];
			seHaGanado = tirar(jugadorJugando);
		}
		
		if(seHaGanado) {
			System.out.printf("¡Enhorabuena %s, has ganado la partida!", jugadorJugando.getNombre());
		} else {
			System.out.println("Empate, no quedan casillas libres.");
		}
	}
	
}
