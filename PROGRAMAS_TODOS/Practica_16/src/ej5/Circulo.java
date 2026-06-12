package ej5;

public class Circulo extends Poligono {
	public static final double pi = 3.14;
	private double radio;

	public Circulo(double radio) {
		super();
		this.radio = radio;
	}

	@Override
	public void calculaArea() {
		area = pi * (radio * radio);
	}

	@Override
	public void calcularPerimetro() {
		perimetro = 2 * pi * radio;
	}
}
