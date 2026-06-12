package ej1;

public class Main {

	public static void main(String[] args) {

		double[] numeros = { 12.4, 56.7, 2.4, 9.1, 10.70 };

		ArrayReales estadisticas = new ArrayReales(numeros);
		
		System.out.println("El maximo es : "+estadisticas.maximo());
		System.out.println("El minimo es : "+estadisticas.minimo());
		System.out.println("El sumatorio es : "+estadisticas.sumatorio());
	}

}
