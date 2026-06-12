package examen_ficheros_pedidos;

public class Pedido {
	private int numeroPedio;
	private double precio;
	private String codigoUsuario, destino;

	public Pedido(int numeroPedio, double precio, String codigoUsuario, String destino) {
		super();
		this.numeroPedio = numeroPedio;
		this.precio = precio;
		this.codigoUsuario = codigoUsuario;
		this.destino = destino;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getNumeroPedio() {
		return numeroPedio;
	}

	public void setNumeroPedio(int numeroPedio) {
		this.numeroPedio = numeroPedio;
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
	public String toString() {
		return "Pedido [numeroPedio=" + numeroPedio + ", precio=" + precio + ", codigoUsuario=" + codigoUsuario
				+ ", destino=" + destino + "]";
	}

}
