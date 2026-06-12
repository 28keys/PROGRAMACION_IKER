package ej5;

public abstract class Poligono {
	protected double area, perimetro;

	public Poligono() {
		super();
	}

	public String toString() {
		return "El area es:" + area + ", el perímetro es:" + perimetro;
	}

	abstract public void calculaArea();

	abstract public void calcularPerimetro();
}


/* - Poligono es la clase principal , tiene metodos area y perimetro que es
 *  lo que se les pasa a las hijas.
 *  
 * - Poligono no tienen nada en el constructor porque el numero de lados no es correcto
 *  porque por ejemplo, circulo no tiene lados, necesita radio, asi que en su clase Circulo
 *  necesita de su propia variable radio para funcionar. Si ponemos nLados en poligono
 *  para que lo hereden las hijas, no es correcto y no se hace asi el ejercicio, tu ya pasas los metodos
 * 
 * - ¡No es necesario que para que se herede se utilize super, con extends ya se entienda la herencia!( esto
 *    es un poco ambiguo pero es para entender que no hace falta poner super (); en las hijas...)
 *    
 *    
 *  abstract public void calculaArea();
	abstract public void calcularPerimetro();
	Para estos metodos abstractos lo que yo estoy queriendo decir en la clase Padre es que yo no puedo
	realizar dichos metodos desde aquí y por eso necesito que las hijas lo hagan en sus clases, en cada
	clase si podremos hacer el metodo de perimetro y area. Los usamos para todas las clases para guardar
	en una sola clase (Poligono), todos los metodos hechos por cada clase cuadrado, circulo, triangulo... (osea así
	esta todo mas ordenadito y no tengo que poner muchas lineas de codigo en cada clase)
	
	
 */
