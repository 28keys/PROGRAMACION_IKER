package ejInterfaceRunnable2;

public class Tarea1 implements Runnable {
	  public void run() {
	        // Esta es la tarea que se ejecutar� en un hilo separado
	        for (int i = 0; i < 10; i++) {
	            System.out.println("Ejecutando tarea1... " + i);
	            try {
	                Thread.sleep(1000); // Espera de 1 segundo
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
