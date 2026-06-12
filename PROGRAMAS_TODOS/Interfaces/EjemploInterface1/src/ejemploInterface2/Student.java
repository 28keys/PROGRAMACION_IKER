package ejemploInterface2;

class Student implements Comparable<Student> {
	private int number;
	private String name;
	private int age;

	Student(int nu, String name, int age) {
		this.number = nu;
		this.name = name;
		this.age = age;
	}

	public int compareTo(Student st) {
		if (age == st.age)
			return 0;
		else if (age > st.age)
			return -1;
		else
			return 1;
	}
	/*
	public int compareTo(Student st) {
		return name.compareTo(st.name)*-1;
		
		//0 si son iguales
		//1 si va antes el de delante que el otro
		//-1 al reves
	}
	*/
	@Override
	public String toString() {
		return "Student [number=" + number + ", name=" + name + ", age=" + age + "]";
	}

}
