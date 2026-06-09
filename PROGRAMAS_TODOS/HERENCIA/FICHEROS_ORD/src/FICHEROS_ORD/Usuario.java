package FICHEROS_ORD;

public class Usuario {
	private String nif, nombre, apellido, email;

	public Usuario(String nif, String nombre, String apellido, String email) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [nif=" + nif + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + "]";
	}

}