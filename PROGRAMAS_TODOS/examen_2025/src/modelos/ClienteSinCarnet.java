package modelos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ClienteSinCarnet extends Cliente {

	public ClienteSinCarnet(String dni, String nombre, String telefono, String direccion, int carnet, int visitas) {
		super(dni, nombre, telefono, direccion, carnet, visitas);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pagar(Arreglo encontrado) {
		double importe;
		long difftotaldias = ChronoUnit.DAYS.between(encontrado.getFechaEntrada(), LocalDate.now()); //si lo comparas con la fecha de salida da error pq es NULL
		if (difftotaldias < 3) {
			importe = encontrado.getImporte() * 1.2;
			encontrado.setImporte(importe);
		}
		visitas++;
		encontrado.setFechaSalida(LocalDate.now());
	}

}
