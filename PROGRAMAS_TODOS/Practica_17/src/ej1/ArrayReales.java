package ej1;

public class ArrayReales implements Estadisticas {

	private double[] arrayReal = null;

	public ArrayReales(double[] arrayReal) {
		super();
		this.arrayReal = arrayReal;
	}

	@Override
	public double minimo() {
		double min = Double.MAX_VALUE; //99999
		for (int i = 0; i < arrayReal.length; i++) {
			if (arrayReal[i] < min)
				min = arrayReal[i];
			
		}
		return min;
	}
	/*Aqui lo q hace es, max value es un valor enorme tipo 999999, al recorrer el array, si uno de los numeros que tengo
	 * dentro es menor que el min 99999, pasa a ser el minimo mi arrayreal osea por ejemplo 3, pero sigue recorriendolo
	 * hasta terminarlo por lo que se puede encontrar con otro valor mas pequeño llegar a el minimi, ver que min = 3 y si
	 * se encuentra 2, min pasa a ser 2*/
		//Lo mismo para el MAX_VALUE

	@Override
	public double maximo() {
		double max = Double.MIN_VALUE;
		for (int i = 0; i < arrayReal.length; i++) {
			if (arrayReal[i] > max)
				max = arrayReal[i];
		}
		return max;
	}

	@Override
	public double sumatorio() {
		double suma = 0;
		for (double elemento : arrayReal) {
			suma += elemento;

		}
		return suma;

	}

}
