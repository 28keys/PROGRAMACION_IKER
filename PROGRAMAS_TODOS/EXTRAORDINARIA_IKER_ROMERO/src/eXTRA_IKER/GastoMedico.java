package eXTRA_IKER;

import java.util.HashSet;

public class GastoMedico extends Solicitud {
	private String concepto;

	private static HashSet<String> conceptos = new HashSet<String>();

	public GastoMedico(String tipoAyuda, String id, double importe, String concepto) {
		super(tipoAyuda, id, importe);
		this.concepto = concepto;
	}

	public static void añadirConceptos(HashSet<String> conceptosTipo) {
		conceptos = conceptosTipo;

	}

	@Override
	public double calcularAyuda() throws Romero {
		double ayuda = 0;
		if (importe < 20) {
			throw new Romero();
		}
		if (conceptos.contains(concepto)) {
			if (concepto.equals("dentista")) {
				ayuda = importe * 0.4;
			}
			if (concepto.equals("gafas")) {
				ayuda = importe * 0.8;
			}
			if (concepto.equals("fisio")) {
				ayuda = importe * 0.2;
			}
			if (concepto.equals("otros")) {
				ayuda = importe * 0.1;
			}

		}

		if (ayuda > 500) {
			ayuda = 500;
		}
		ayudasDadas++;
		ayudasMedico++;
		return ayuda;
	}

	@Override
	public String toString() {
		return "GastoMedico [concepto=" + concepto + "\n" + super.toString();
	}

}
