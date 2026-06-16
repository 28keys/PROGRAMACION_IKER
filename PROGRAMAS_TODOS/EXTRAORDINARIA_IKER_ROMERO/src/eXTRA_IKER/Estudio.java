package eXTRA_IKER;

public class Estudio extends Solicitud {
	private double notaMedia;

	public Estudio(String tipoAyuda, String id, double importe, double notaMedia) {
		super(tipoAyuda, id, importe);
		this.notaMedia = notaMedia;
	}

	@Override
	public double calcularAyuda() throws Romero {
		double ayuda = 0;
		if (notaMedia < 6) {
			throw new Romero();
		}
		if (notaMedia >= 6 && notaMedia <= 8) {
			ayuda = importe * 0.5;
		}
		if (notaMedia > 8) {
			ayuda = importe * 0.8;
		}
		ayudasDadas++;
		return ayuda;
	}

	@Override
	public String toString() {
		return "Estudio [notaMedia=" + notaMedia + "\n" + super.toString();
	}

}
