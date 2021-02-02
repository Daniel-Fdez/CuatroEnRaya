package org.iesalandalus.programacion.cuatroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private Consola() {
		
	}
	
	// Método que lee el nombre del jugador
	public static String leerNombre() {
		String nomJugador;
		
		do {
			System.out.print("Introduce el nombre del jugador:");
			nomJugador = Entrada.cadena();
		} while(nomJugador == null || nomJugador.equals(""));
		
		return nomJugador;
	}
	
	// Método que permite elegir el color de las fichas
	public static Ficha elegirColorFichas() {
		int eleccion;
		
		do {
			System.out.print("Elige el color de tus fichas (0-AZUL, 1-VERDE):");
			eleccion = Entrada.entero();
		} while(eleccion != 0 && eleccion != 1);
		
		Ficha colFicha;
		
		if(eleccion == 0) {
			colFicha = Ficha.AZUL;
		} else {
			colFicha = Ficha.VERDE;
		}
		
		return colFicha;
	}
	
	// Método que lee los datos del primer jugador (nombre y color de ficha)
	public static Jugador leerJugador() {
		System.out.println("Introduce los datos del PRIMER jugador");
	
		String nombre = leerNombre();
		Ficha colFichas = elegirColorFichas();
		Jugador primerJugador = new Jugador(nombre, colFichas);
		
		return primerJugador;
	}
	
	// Método que lee los datos del segundo jugador (nombre y color de ficha)
	public static Jugador leerJugador(Ficha ficha) {
		System.out.println("Introduce los datos del SEGUNDO jugador");
		
		String nombre = leerNombre();
		Jugador segundoJugador = new Jugador(nombre, ficha);
		
		return segundoJugador;
	}
	
	// // Método que lee la columna donde se va a introducir la ficha
	public static int leerColumna(Jugador jugador) {
		String nombre = jugador.getNombre();
		int columna;
		
		do {
			System.out.print(nombre + ", introduce la columna en la que deseas introducir la ficha (0 - 6):");
			columna = Entrada.entero();
		} while(columna < 0 || columna > 6);
		
		return columna;
	}
}
