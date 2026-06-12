package ejemploInterface1;

import java.util.LinkedList;
import java.util.Vector;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Telefono tel = new Telefono("666666666");
		Vehiculo ve = new Vehiculo("6666XXX");

		tel.addContacto("Pepe", "444444444");
		tel.addContacto("Ana", "222222222");

		ve.recorrer(20);

		/* Podemos hacer un container con objetos que pertenecen al mismo interface */
		LinkedList<Machine> maquinas = new LinkedList<Machine>();
		maquinas.add(ve);
		maquinas.add(tel);

		for (int i = 0; i < maquinas.size(); i++) {
			/*
			 * Esto ser�a algo parecido al polimorfismo, ejecuta el m�todo el objeto
			 * adecuado
			 */
			System.out.println(maquinas.get(i).toString());
			System.out.println(maquinas.get(i).suena());
			maquinas.get(i).reset();
			
		}

		/* Muestro las m�quinas una vez reseteadas */
		for (int i = 0; i < maquinas.size(); i++) {
			System.out.println(maquinas.get(i).toString());
		}

	}

}
