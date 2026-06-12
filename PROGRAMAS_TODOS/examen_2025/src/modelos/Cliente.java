package modelos;

public abstract class Cliente {
	private String dni, nombre, telefono, direccion;
	private int carnet; 
	protected int visitas;

	public Cliente(String dni, String nombre, String telefono, String direccion, int carnet, int visitas) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.carnet = carnet;
		this.visitas = visitas;
	}
	
	public abstract void pagar(Arreglo encontrado);

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCarnet() {
		return carnet;
	}

	public void setCarnet(int carnet) {
		this.carnet = carnet;
	}

	public int getVisitas() {
		return visitas;
	}

	public void setVisitas(int visitas) {
		this.visitas = visitas;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion
				+ ", carnet=" + carnet + ", visitas=" + visitas + "\n";
	}

}
