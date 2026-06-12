package ejMontañaRusa;

public class Bono extends Persona {

	private int viajes = 3;
	private boolean haPagado = false;

	public Bono(int edad, int altura) {
		super(edad, altura);
	}

	@Override
	public double pagar() {
		if (!haPagado) {
			haPagado = true;
			if (edad >= 18)
				return 10;
			else
				return 4;
		} else {
			viajes--;
			return 0;
		}
	}

	public boolean tieneViajes() {
		return viajes > 0;
	}
}