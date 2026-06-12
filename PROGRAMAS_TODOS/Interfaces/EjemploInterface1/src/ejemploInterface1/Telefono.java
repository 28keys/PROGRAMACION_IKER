package ejemploInterface1;

import java.util.HashMap;
import java.util.Objects;

public class Telefono implements Machine{
	private String numero;
	// Tabla Hash, guarda parejas por clave, en este caso parejas de la forma nombre,teléfono
	private HashMap<String, String> contactos;
	
	
	

	public Telefono(String numero) {
		this.numero = numero;
		this.contactos = new HashMap<String, String>();
		
	}


	public void addContacto(String nombre,String telefono){
		contactos.put(nombre, telefono);
	}

	@Override
	public String suena() {
		
		return "Riiiiing";
	}
	
	public void reset(){
		contactos.clear();
	}


	@Override
	public String toString() {
		return "Telefono [numero=" + numero + ", contactos=" + contactos + "]";
	}


	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefono other = (Telefono) obj;
		return Objects.equals(contactos, other.contactos) && Objects.equals(numero, other.numero);
	}
	
	

}
