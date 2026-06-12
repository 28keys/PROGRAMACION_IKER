package examen_ficheros;

public class Pedido implements Comparable<Pedido> {

	private int numeroPedido;
	private double precio;
	private String codigoUsuario;
	private String destino;

	public Pedido(int numeroPedido, double precio, String codigoUsuario, String destino) {
		super();
		this.numeroPedido = numeroPedido;
		this.precio = precio;
		this.codigoUsuario = codigoUsuario;
		this.destino = destino;
	}

	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	@Override
	public int compareTo(Pedido o) {
		// pq si comparas Ana con carlos va primero ana por eso lower
		String codigo1 = this.codigoUsuario.toLowerCase();
		String codigo2 = this.codigoUsuario.toLowerCase();
		if (codigo1.compareTo(codigo2) > 0) {
			return 1; // va despues
		}
		if (codigo1.compareTo(codigo2) < 0) {
			return -1; // va antes
		}

		if (this.numeroPedido > o.getNumeroPedido()) {
			return 1;
		}

		if (this.numeroPedido < o.getNumeroPedido()) {
			return -1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "Pedido [numeroPedido=" + numeroPedido + ", precio=" + precio + ", codigoUsuario=" + codigoUsuario
				+ ", destino=" + destino + "]";
	}

}
