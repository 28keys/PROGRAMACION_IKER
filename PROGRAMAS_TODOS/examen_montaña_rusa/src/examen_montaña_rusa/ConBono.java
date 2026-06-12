package examen_montaña_rusa;

public class ConBono extends Persona {
	private int viajes = 3;

	public ConBono(int edad, int altura) {
		super(edad, altura);

	}

	@Override
	public double pagar() {
		double total = 0;
		if (viajes == 3) {
			if (edad >= 0 && edad < 17) {
				total = 4;
			} else if (edad >= 18) {
				total = 10;
			}
		}
		viajes--;

		return total;
	}

	@Override
	public String toString() {
		return "ConBono : Tienes " + viajes + " viajes\n" + super.toString();
	}

}
