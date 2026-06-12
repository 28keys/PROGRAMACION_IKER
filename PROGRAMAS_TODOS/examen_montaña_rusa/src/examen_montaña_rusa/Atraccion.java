package examen_montaña_rusa;

public class Atraccion {
	private boolean enMarcha;
	private Persona[] plazas = new Persona[4];

	public Atraccion() {
		super();
		enMarcha = false;
	}

	public boolean isEnMarcha() {
		return enMarcha;
	}

	public void setEnMarcha(boolean enMarcha) {
		this.enMarcha = enMarcha;
	}

	public Persona[] getPlazas() {
		return plazas;
	}

	public void setPlazas(Persona[] plazas) {
		this.plazas = plazas;
	}

	public void añadiraAtraccion(Persona p) {
		for (int i = 0; i < plazas.length; i++) {
			if (plazas[i] == null) {
				plazas[i] = p;
				break;
			}
		}
	}

	public Persona vaciarAtraccion() {
		for (int i = 0; i < plazas.length; i++) {
			if (plazas[i] != null) {
				Persona borrarPersona = plazas[i];
				plazas[i] = null;
				return borrarPersona;
			}
		}
		return null;
	}

	public Persona mostrarPersonas() {
		for (int i = 0; i < plazas.length; i++) {
			Persona p = plazas[i];
			return p;
		}
		return null;
	}

	public boolean estaLlena() {
		for (int i = 0; i < plazas.length; i++) {
			if (plazas[i] == null) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {

		return "Atraccion [enMarcha=" + enMarcha + "]";
	}

}

/*
 * no hace falta que me cree otra variable llena para que se sepa que lo está,
 * con el metodo estaLlena recorremos el array y si ninguno es nulo es pq hay
 * personas montadas por ende, retornando true decimos q está llena pq todas
 * estan ocupadas (ninguna es null)
 */
