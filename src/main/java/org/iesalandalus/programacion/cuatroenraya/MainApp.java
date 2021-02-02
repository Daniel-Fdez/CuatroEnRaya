package org.iesalandalus.programacion.cuatroenraya;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

public class MainApp {

	public static void main(String[] args) {
		Ficha colorFicha;
		Jugador primerJugador = Consola.leerJugador();
		
		if(primerJugador.getColorFichas() == Ficha.AZUL) {
			colorFicha = Ficha.VERDE;
		} else {
			colorFicha = Ficha.AZUL;
		}
		
		Jugador segundoJugador = Consola.leerJugador(colorFicha);
		CuatroEnRaya cuatroEnRaya = new CuatroEnRaya(primerJugador, segundoJugador);
		cuatroEnRaya.jugar();
	}
}
