package ej5;

public class Cuadrado extends Poligono {
	private double lado;

	public Cuadrado(double lado) {
		super(); // puedo poner super vacio aunque no es necesario, no hace falta porque poligono no contiene nada
		this.lado = lado;
	}

	@Override
	public void calculaArea() {
		area = lado * lado;
	}

	@Override
	public void calcularPerimetro() {
		perimetro = 4 * lado;
	}
}
