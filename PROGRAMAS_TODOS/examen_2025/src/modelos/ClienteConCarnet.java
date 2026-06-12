package modelos;

import java.time.LocalDate;

public class ClienteConCarnet extends Cliente {

	public ClienteConCarnet(String dni, String nombre, String telefono, String direccion, int carnet, int visitas) {
		super(dni, nombre, telefono, direccion, carnet, visitas);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pagar(Arreglo encontrado) {
		double importe;
		if (encontrado.getImporte() > 200) {
			importe = (encontrado.getImporte() * 0.90);
			encontrado.setImporte(importe);
		}
		importe = encontrado.getImporte() - ((visitas / 4) * 50);
		encontrado.setImporte(importe);
		visitas++;
		encontrado.setFechaSalida(LocalDate.now());
	}

}
