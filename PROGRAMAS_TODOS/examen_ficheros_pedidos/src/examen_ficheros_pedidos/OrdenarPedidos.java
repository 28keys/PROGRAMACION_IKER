package examen_ficheros_pedidos;

import java.util.Comparator;

public class OrdenarPedidos implements Comparator<Pedido> {
	@Override
	public int compare(Pedido o1, Pedido o2) {
		if (o1.getCodigoUsuario().compareTo(o2.getCodigoUsuario()) < 0) {
			return -1;
		}
		if (o1.getCodigoUsuario().compareTo(o2.getCodigoUsuario()) > 0) {
			return 1;
		}
		if (o1.getNumeroPedio() < o2.getNumeroPedio()) {
			return -1;
		}
		if (o1.getNumeroPedio() > o2.getNumeroPedio()) {
			return 1;
		}
		return 0;
	}

}
