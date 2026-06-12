package ej5;

public class Triangulo extends Poligono {
	private double lado;
	private double altura;
	private double base;

	public Triangulo(double lado, double altura, double base) {
		this.lado = lado;
		this.altura = altura;
		this.base = base;
	}

	@Override
	public void calculaArea() {
		
		area = (base * altura) /2;
	}

	@Override
	public void calcularPerimetro() {
		
		perimetro =  3 * lado;

	}

}
