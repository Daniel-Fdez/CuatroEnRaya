package org.iesalandalus.programacion.cuatroenraya.modelo;

import java.util.Arrays;

import javax.naming.OperationNotSupportedException;

public class Tablero {
	public static final int FILAS = 6;
	public static final int COLUMNAS = 7;
	public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;
	private Casilla[][] casillas;
	
	// Crea un tablero y pasa por cada uno de los elementos del array para crear una casilla
	public Tablero() {
		casillas = new Casilla[FILAS][COLUMNAS];
		for(int i = 0; i < FILAS; i++) {
			for(int j = 0; j < COLUMNAS; j++) {
				casillas[i][j] = new Casilla();
			}
		}
	}
	
	/* Devuelve true o false dependiendo de si la columna pasada por parámetro está vacía o no
	   Para ello, comprobamos que la casilla por encima de una potencial ficha esté libre */
	private boolean columnaVacia(int columna) {
		return !casillas[0][columna].estaOcupada();
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
		return casillas[FILAS - 1][columna].estaOcupada();
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
		if(ficha == null) {
			throw new NullPointerException("ERROR: La ficha no puede ser nula.");
		}
		if(ficha != Ficha.AZUL && ficha != Ficha.VERDE) {
			throw new IllegalArgumentException("ERROR: La ficha introducida no es correcta.");
		}
	}
	
	// Comprueba que la columna introducida sea correcta
	private void comprobarColumna(int columna) {
		if(columna < 0 || columna > 6) {
			throw new IllegalArgumentException("ERROR: Columna incorrecta.");
		}
	}
	
	// Devuelve la primera fila vacía de la columna que se pase por el parámetro
	private int getPrimeraFilaVacia(int columna) {
		int valor = 0;
		for(int i = 0; i < FILAS; i++) {
			if(!casillas[i][columna].estaOcupada()) {
				valor = i;
				return valor;
			}
		}
		return valor;
	}

	// Comprueba si el número de fichas del jugador ha alcanzado el número de fichas necesarias para ganar
	private boolean objetivoAlcanzado(int numFichas) {
		return numFichas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS;
	}
	
	// Comprueba las fichas de forma horizontal
	private boolean comprobarHorizontal(int fila, Ficha ficha) {
		int numFicha = 0;
		for(int i = 0; i < COLUMNAS; i++) {
			if(casillas[fila][i].getFicha() == ficha) {
				numFicha++;
				if(objetivoAlcanzado(numFicha)) {
					return true;
				}
			} else {
				numFicha = 0;
			}
		}
		return false;
	}
	
	// Comprueba las fichas de forma vertical
	private boolean comprobarVertical(int columna, Ficha ficha) {
		int numFicha = 0;
		for(int i = 0; i < FILAS; i++) {
			if(casillas[i][columna].getFicha() == ficha) {
				numFicha++;
				if(objetivoAlcanzado(numFicha)) {
					return true;
				}
			} else {
				numFicha = 0;
			}
		}
		return false;
	}
	
	// Comprueba dos números enteros y devuelve el menor de ellos
	private int menor(int num1, int num2) {
		return Math.min(num1, num2);
	}
	
	// Comprueba la diagonal desde abajo a la izquierda hacia arriba a la derecha
	private boolean comprobarDiagonalNE(int fila, int columna, Ficha ficha) {
		int conteoFicha = 0;
		int desplazamientoInicial = menor(fila, columna);
		fila -= desplazamientoInicial;
		columna -= desplazamientoInicial;
		
		while(fila < FILAS && columna < COLUMNAS) {
			if(casillas[fila][columna].getFicha() == ficha) {
				conteoFicha++;
			} else {
				conteoFicha = 0;
			}
			
			if(conteoFicha == FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
				return true;
			}
			
			fila++;
			columna++;
		}
		return false;
	}
	
	// Comprueba la diagonal desde abajo a la derecha hacia arriba a la izquierda
	private boolean comprobarDiagonalNO(int fila, int columna, Ficha ficha) {
		int conteoFicha = 0;
		int desplazamientoInicial = menor(fila, COLUMNAS - 1 - columna);
		fila -= desplazamientoInicial;
		columna += desplazamientoInicial;
		
		while(fila < FILAS && columna >= 0) {
			if(casillas[fila][columna].getFicha() == ficha) {
				conteoFicha++;
			} else {
				conteoFicha = 0;
			}
			
			if(conteoFicha == FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
				return true;
			}
			
			fila++;
			columna--;
		}
		return false;
	}
	
	// Comprueba la tirada para ver si el jugador ha ganado
	private boolean comprobarTirada(int fila, int columna, Ficha ficha) {
		return comprobarDiagonalNE(fila, columna, ficha)|| 
			   comprobarDiagonalNO(fila, columna, ficha)|| 
			   comprobarHorizontal(fila, ficha) || 
			   comprobarVertical(columna, ficha);
	
	}
	
	/* Método que acepta como parámetros la ficha y la columna donde se va a introducir.
	   Valida los datos de entrada e indica si la jugada ha sido ganadora o no */
	public boolean introducirFicha(int columna, Ficha ficha) throws OperationNotSupportedException {
		comprobarColumna(columna);
		comprobarFicha(ficha);
		
		if(columnaLlena(columna)) {
			throw new OperationNotSupportedException("ERROR: Columna llena.");
		} else {
			int fila = getPrimeraFilaVacia(columna);
			casillas[fila][columna].setFicha(ficha);
			return comprobarTirada(fila, columna, ficha);
		}
	}

	// Método que construye el tablero
	@Override
	public String toString() {
		StringBuilder sbTablero = new StringBuilder(75);
		for(int fila = FILAS - 1; fila >= 0; fila--) {
			sbTablero.append('|');
			for(int columna = 0; columna < COLUMNAS; columna++) {
				Ficha ficha = casillas[fila][columna].getFicha();
				if(ficha == Ficha.AZUL) {
					sbTablero.append('A');
				} else if(ficha == Ficha.VERDE) {
					sbTablero.append('V');
				} else {
					sbTablero.append(' ');
				}
			}
			sbTablero.append('|');
			sbTablero.append('\n');
		}
		sbTablero.append(" -------\n");
		return sbTablero.toString();
	}
}
