package examen_montaña_rusa;

public class Normal extends Persona {

	public Normal(int edad, int altura) {
		super(edad, altura);
	}

	@Override
	public double pagar() {
		double total = 0;
		if (edad >= 0 && edad < 17) {
			total = 3;
		} else if (edad >= 18) {
			total = 4;
		}
		totalRecaudado+=total;
		return total;
	}

	@Override
	public String toString() {
		return "Normal :\n" + super.toString();
	}

}
