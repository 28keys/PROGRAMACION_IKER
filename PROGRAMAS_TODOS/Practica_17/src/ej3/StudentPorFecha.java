package ej3;

import java.util.Comparator;

public class StudentPorFecha implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		if (o1.getFechaIncor().isBefore(o2.getFechaIncor()))
			return 1;
		if (o1.getFechaIncor().isAfter(o2.getFechaIncor()))
			return -1;
		else
			return 0;
	}

}
