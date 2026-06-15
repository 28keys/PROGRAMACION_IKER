package gym;

import java.util.Comparator;

import modelos.Reserva;

public class OrdenarReservas implements Comparator<Reserva> {

	@Override
	public int compare(Reserva o1, Reserva o2) {
		int comparar = o1.getFechaReserva().compareTo(o2.getFechaReserva());
		if(comparar != 0) {
			return comparar;
		}
		if(o1.getPreferencia() > o2.getPreferencia()) {
			return -1;
		}
		if (o1.getPreferencia() < o2.getPreferencia()) {
			return 1;
		}
		return 0;
		
	}



}
