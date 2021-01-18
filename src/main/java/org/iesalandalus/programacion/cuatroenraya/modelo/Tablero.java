package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Tablero {
	public static final int FILAS = 6;
	public static final int COLUMNAS = 7;
	public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;
	private Casilla[][] casillas;
	
	// Crea un tablero y pasa por cada uno de los elementos del array para crear una casilla
	public Tablero() {
		casillas = new Casilla[FILAS][COLUMNAS];
		for(int i = 0; i < casillas.length; i++) {
			for(int j = 0; j < casillas[i].length; j++) {
				casillas[i][j] = new Casilla();
			}
		}
	}
	
	/* Devuelve true o false dependiendo de si la columna pasada por parámetro está vacía o no
	   Para ello, comprobamos que la casilla por encima de una potencial ficha esté libre */
	private boolean columnaVacia(int columna) {
		for(int i = 0; i < FILAS; i++) {
			if(casillas[i][columna].estaOcupada()) {
				return false;
			}
		}
		return true;
	}
	
	// Devuelve "true" o "false" dependiendo de si el tablero está vacío o no
	public boolean estaVacio() {
		for(int i = 0; i < COLUMNAS; i++) {
			boolean colVacia = columnaVacia(i);
			if(!colVacia) {
				return false;
			}	
		}
		return true;
	}
	
	// Devuelve "true" o "false" dependiendo de si la columna está llena o no
	private boolean columnaLlena(int columna) {
		for(int i = 0; i < FILAS; i++) {
			if(!(casillas[i][columna].estaOcupada())) {
				return false;
			}
		}
		return true;
	}
	
	// Devuelve "true" o "false" dependiendo de si el tablero está lleno o no
	public boolean estaLleno() {
		for(int i = 0; i < COLUMNAS; i++) {
			if(!columnaLlena(i)) {
				return false;
			}	
		}
		return true;
	}
	
	// Comprueba que la ficha introducida sea correcta
	private void comprobarFicha(Ficha ficha) {
		if(ficha != Ficha.AZUL && ficha != Ficha.VERDE) {
			throw new IllegalArgumentException("ERROR: La ficha introducida no es correcta.");
		}
	}
	
	// Comprueba que la columna introducida sea correcta
	private void comprobarColumna(int columna) {
		if(columna < 0 || columna > 6) {
			throw new IllegalArgumentException("ERROR: La columna introducida no es correcta.");
		}
	}
	
	// Devuelve la primera fila vacía de la columna que se pase por el parámetro
	// INCOMPLETO
	private int getPrimeraFilaVacia(int columna) {
		int valor = 0;
		for(int i = 0; i < FILAS; i++) {
			if(estaVacio()) {
				valor = i;
			}
		}
		return valor;
	}
	
	// Comprueba dos números enteros y devuelve el menor de ellos
	private int menor(int num1, int num2) {
		return Math.min(num1, num2);
	}
	
	/* Método que acepta como parámetros la ficha y la columna donde se va a introducir.
	   Valida los datos de entrada e indica si la jugada ha sido ganadora o no */
	// INCOMPLETO
	public boolean introducirFicha(int columna, Ficha ficha) {
		if(ficha == null) {
			throw new NullPointerException("ERROR: La ficha no puede ser nula.");
		} else if(columna < COLUMNAS|| columna > COLUMNAS) {
			throw new NullPointerException();
		}
	}
}
