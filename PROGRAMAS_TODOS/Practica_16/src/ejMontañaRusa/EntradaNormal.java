package ejMontañaRusa;

public class EntradaNormal extends Persona {

    public EntradaNormal(int edad, int altura) {
        super(edad, altura);
    }

    @Override
    public double pagar() {
        if (edad >= 18) return 4;
        else return 3;
    }
}