package examenlibros;

public class Propietario {
	private String nif,nombre,apellidos;

	public Propietario(String nif, String nombre, String apellidos) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	public String getNomAp() {
		return nombre+","+apellidos;
	}
}
