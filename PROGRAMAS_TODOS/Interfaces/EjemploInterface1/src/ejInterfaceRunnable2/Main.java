package ejInterfaceRunnable2;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  // Creamos una instancia de la clase que implementa Runnable
        Tarea1 tarea = new Tarea1();
       

        // Creamos un hilo y le pasamos la instancia de la tarea
        Thread hilo = new Thread(tarea);
      

        // Iniciamos el hilo
        hilo.start();
      
       // El programa contin�a ejecut�ndose mientras el hilo est� en marcha
        // Hacemos una pausa en el hilo principal para esperar la finalizaci�n del hilo secundario
        try {
            hilo.join(); // Espera hasta que el hilo termine
          
        } catch (InterruptedException e) {
            e.printStackTrace();
            
        }

       
        // Una vez que el hilo termina, continuamos con el flujo del programa
        System.out.println("Tarea completada.");
    }
	

}
