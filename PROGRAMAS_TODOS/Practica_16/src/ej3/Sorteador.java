package ej3;

import java.util.ArrayList;
import java.util.Random;

public class Sorteador<T> /* clase que guarda cualquier tipo de objeto */ {
	/*
	 * Al poner T queremos decir que en esta clase aún no se que tipo voy a guardar,
	 * porque ya se lo diré cuando lo use
	 * T = type, de tipo generico.
	 */

	private ArrayList<T> elementos;
	/*
	 * Esto es una lista que no guarda un dato fijo sino objetos tipo T, yo le voy a
	 * pasar la Cuenta que he creado en el main por lo que la T se convertirá en
	 * Cuenta... pero puede ser de cualquier cosa, como un String
	
	 */

	public Sorteador() {
		this.elementos = new ArrayList<>();
		//Constructor
		//crea la lista vacía
	}

	public void add(T elemento) {
		elementos.add(elemento);
		
		/*En este metodo se pasa un objeto nuevamente de
		 * tipo generico, asi que si yo le paso cuenta sería
		 * public void add(Cuenta elemento)*, depende de que tipo
		 * voy a llamar al sorteador en el main, en este caso:
		 *Sorteador<Cuenta> sorteo = new Sorteador<>(); */
	}

	public T get() {
		if (elementos.isEmpty()) { /*Comprueba si la lista está vacía
		 							si esta vacia deuelve null, (osea basicamente si
		 							hay cuentas dadas de alta o no :p))*/
			return null;
		}
		Random r = new Random();
		int pos = r.nextInt(elementos.size()); // esto es una posicion aleatoria entre 0
											  // y size () - 1 , osea,  hasta el ultimo espacio creado en la
												// la coleccion
		return elementos.get(pos);

	}

	public boolean cuentonVacio() {
		return elementos.isEmpty(); //devuelve true si la lista está vacía y false si tiene elementos :b
	}

}
