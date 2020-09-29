package view;

import java.util.concurrent.Semaphore;

import controller.ThreadBanco;

public class Main {

	
		public static void main(String[] args) {
			int perm=1;
			Semaphore semaforo = new Semaphore(perm);
			for (int i = 0; i <21 ; i++) {
				Thread tBanco = new ThreadBanco(i,semaforo);
				tBanco.start();
			}
		}
	

}
