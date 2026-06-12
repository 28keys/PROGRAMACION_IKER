package ej5;

public class Main {

	public static void main(String[] args) {
		Poligono figura[] = new Poligono[4];
		figura[0] = new Cuadrado(2.3);
		figura[1] = new Rectangulo(4.1);
		figura[2] = new Triangulo(3.2, 5.3, 1.2);
		figura[3] = new Circulo(10.1);

		for (int i = 0; i < 4; i++) {
			System.out.println();
			figura[i].calculaArea(); // Esto significa que por cada espacio (i) del array voy a calcular el area
			figura[i].calcularPerimetro(); // Esto significa que por cada espacio (i) del array voy a calcular el perimetro
			
			System.out.println(figura[i].toString());

		}
	}

}
