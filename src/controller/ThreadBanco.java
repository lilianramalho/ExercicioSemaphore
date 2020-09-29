package controller;

import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread {

	private int idOperacao;
	private Semaphore semaforo;

	public ThreadBanco(int idOperacao, Semaphore semaforo) {
		this.idOperacao = idOperacao;
		this.semaforo = semaforo;
	}

	public void run() {
		int i = 0;
		while (i<2) {
			calc();
			try {
				semaforo.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				transacao();
			}
			semaforo.release();
			if (idOperacao % 3 == 2 && idOperacao % 3 == 0) {
				
				calc();
				try {
					semaforo.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					transacao();	
				}
				semaforo.release();
			}
			i++;
		}
	}

	private void calc() {
		int tempo = 0;
		if (idOperacao % 3 == 1) {
			tempo = (int) ((Math.random() * 801) + 200);
		} else if (idOperacao % 3 == 2) {
			tempo = (int) ((Math.random() * 1001) + 500);
		} else if (idOperacao % 3 == 0) {
			tempo = (int) ((Math.random() * 1001) + 1000);
		}
		System.out.println("efetuando calculo");
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void transacao() {
		int tempo = 0;
		if (idOperacao % 3 == 1) {
			tempo = (int) (Math.random() * 1001);
		} else if (idOperacao % 3 == 2) {
			tempo = (int) (Math.random() * 1501);
		} else if (idOperacao % 3 == 0) {
			tempo = (int) (Math.random() * 1501);
		}
		System.out.println("transação de BD");
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
