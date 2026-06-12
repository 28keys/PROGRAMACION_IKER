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

}
