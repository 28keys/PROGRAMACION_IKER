package eXTRA_IKER;

import java.util.Objects;

public abstract class Solicitud {
	protected static double ayudasDadas = 0;
	protected static double ayudasMedico = 0;
	private String tipoAyuda, id;
	protected double importe;

	public Solicitud(String tipoAyuda, String id, double importe) {
		super();
		this.tipoAyuda = tipoAyuda;
		this.id = id;
		this.importe = importe;
	}


	public static double porcentajeAyudas() {
		if (ayudasDadas == 0) {
			return 0;
		}
		double ayudas = (ayudasMedico / ayudasDadas) * 100;
		return  ayudas;
	}

	abstract double calcularAyuda() throws Romero;

	public String getTipoAyuda() {
		return tipoAyuda;
	}

	public void setTipoAyuda(String tipoAyuda) {
		this.tipoAyuda = tipoAyuda;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "tipoAyuda=" + tipoAyuda + ", id=" + id + ", importe=" + importe + "]";
	}




	
}
