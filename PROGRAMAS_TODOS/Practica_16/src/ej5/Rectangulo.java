package ej5;

public class Rectangulo extends Poligono {
	private double lado;

	public Rectangulo(double lado) {
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
