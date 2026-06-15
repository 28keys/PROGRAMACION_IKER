package modelos;

public class UsuarioNormal extends Usuario {

	public UsuarioNormal(String clave, int pin, String nombre, String apellidos, String tarifa, int numeromimebros,
			double descuento) {
		super(clave, pin, nombre, apellidos, tarifa, numeromimebros, descuento);
	}

	@Override
	public double pagar() {
		double precio = 45;
		if (descuento > 0) {
			precio = precio - descuento;
		}
		return precio;
	}

}
