package ej3;

import java.util.Comparator;

public class StudentPorEdad implements Comparator<Student> {

	@Override
	public int compare(Student arg0, Student arg1) {
		if (arg0.getAge() == arg1.getAge())
			return 0;
		if (arg0.getAge() < arg1.getAge())
			return -1;
		else
			return 1;

		
		
		/*
		 * Puedo llegar al getAge porque estoy comparando a los estudiantes que he creado
		 * en el main. Esos estudiantes creados pertenecen a la clase Student donde estan creados 
		 * los metodos y características, donde uno de ellos es getAge, o tambien con getName :))
		 * */
	}

}
