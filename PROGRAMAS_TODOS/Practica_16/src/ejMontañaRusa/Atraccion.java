package ejMontañaRusa;

import java.util.*;

public class Atraccion {

	private Persona[] asientos = new Persona[4];
	private Queue<Persona> cola = new LinkedList<>();
	private boolean enMarcha = false;
	private double recaudado = 0;

	private HashMap<String, Integer> contador = new HashMap<>();

	public void llegarPersona(Persona p) {
		cola.add(p);
	}

	public void ordenarCola() {
		List<Persona> lista = new ArrayList<>(cola);
		Collections.sort(lista, (a, b) -> a.compareTo(b));
		cola = new LinkedList<>(lista);
	}

	public void montar() {
		int i = 0;

		while (i < 4 && !cola.isEmpty()) {
			Persona p = cola.poll();

			try {
				comprobarAltura(p);

				recaudado += p.pagar();
				asientos[i] = p;

				String tipo = p.getClass().getSimpleName();
				contador.put(tipo, contador.getOrDefault(tipo, 0) + 1);

				i++;

			} catch (Exception e) {
				System.out.println("No apto: " + e.getMessage());
			}
		}
	}

	private void comprobarAltura(Persona p) throws Exception {
		if (p.getAltura() < 100 || p.getAltura() > 190) {
			throw new Exception("Altura no válida");
		}
	}

	public void iniciar() {
		if (llena())
			enMarcha = true;
	}

	public void parar() {
		if (enMarcha) {
			enMarcha = false;

			for (int i = 0; i < 4; i++) {
				Persona p = asientos[i];

				if (p instanceof Bono) {
					Bono b = (Bono) p;
					if (b.tieneViajes()) {
						cola.add(b);
					}
				}
				asientos[i] = null;
			}
		}
	}

	public boolean llena() {
		for (Persona p : asientos) {
			if (p == null)
				return false;
		}
		return true;
	}

	public void mostrar() {
		System.out.println("COLA:");
		for (Persona p : cola) {
			System.out.println(p);
		}

		System.out.println("ATRACCION:");
		for (Persona p : asientos) {
			System.out.println(p);
		}

		System.out.println("Recaudado: " + recaudado);
		System.out.println("En marcha: " + enMarcha);

		System.out.println("Contador:");
		System.out.println(contador);
	}
}