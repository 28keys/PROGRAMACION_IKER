package ej3;

import java.time.LocalDate;

class Student {
	private int number;
	private String name;
	private int age;
	private LocalDate fechaIncor;
	
	Student(int nu, String name, int age, LocalDate fechaIncor) {
		this.fechaIncor = fechaIncor;
		this.number = nu;
		this.name = name;
		this.age = age;
	}

	public LocalDate getFechaIncor() {
		return fechaIncor;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/*
	//Para ordenar por edad de menor a mayor a misma edad por fincorporacion
	public int compareTo(Student st) {
		if (age < st.age)
			return -1; // el primero quiero que se coloque delante del segundo
		if (age > st.age)
			return 1;
		return fechaIncor.compareTo(st.fechaIncor);
	}
º	*/
	@Override
	public String toString() {
		return "Student [number=" + number + ", name=" + name + ", age=" + age + " Fecha de incorporacion: "
				+ fechaIncor + "]";
	}

}
