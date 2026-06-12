package ejInterfaceRunnable;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Creamos una instancia de la clase que implementa Runnable
		Tarea1 tarea = new Tarea1();
		Tarea2 tarea2 = new Tarea2();

		// Creamos un hilo y le pasamos la instancia de la tarea
		Thread hilo = new Thread(tarea);
		Thread hilo2 = new Thread(tarea2);

		// Iniciamos el hilo
		hilo.start();
		hilo2.start();

		for (int i = 1; i < 100000; i++) {
			System.out.println("Main " + i);
			/*
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		}
		// El programa contin�a ejecut�ndose mientras el hilo est� en marcha
		// Hacemos una pausa en el hilo principal para esperar la finalizaci�n del hilo
		// secundario
		try {
			hilo.join(); // Espera hasta que el hilo termine
			hilo2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}

		// Una vez que el hilo termina, continuamos con el flujo del programa
		System.out.println("Tarea completada.");
	}

}
