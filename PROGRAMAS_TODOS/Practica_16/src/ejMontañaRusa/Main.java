package ejMontañaRusa;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Atraccion a = new Atraccion();
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        int opcion;

        do {
            System.out.println("1. Llegar persona");
            System.out.println("2. Ordenar cola");
            System.out.println("3. Montar");
            System.out.println("4. Iniciar");
            System.out.println("5. Parar");
            System.out.println("6. Mostrar");
            System.out.println("0. Salir");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    int edad = r.nextInt(60) + 10;
                    int altura = r.nextInt(140) + 70;

                    int tipo = r.nextInt(3);

                    if (tipo == 0) {
                        a.llegarPersona(new EntradaNormal(edad, altura));
                    } else if (tipo == 1) {
                        a.llegarPersona(new Bono(edad, altura));
                    } else {
                        a.llegarPersona(new Promocion(edad, altura, "ABC12"));
                    }
                    break;

                case 2:
                    a.ordenarCola();
                    break;

                case 3:
                    a.montar();
                    break;

                case 4:
                    a.iniciar();
                    break;

                case 5:
                    a.parar();
                    break;

                case 6:
                    a.mostrar();
                    break;
            }

        } while (opcion != 0);

        sc.close();
    }
}