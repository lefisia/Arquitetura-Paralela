import java.util.concurrent.Semaphore;

public class Wallet {
	
	int saldo = 0;
	// O nosso Semáforo com 1 thread apenas:
	Semaphore sem = new Semaphore(1);
	
	public void depositar(int valor) {
		try {
			// 1. Bloquear o Token (Tranca a porta)
			sem.acquire();
			
			// 2. Região crítica: O loop roda por completo em segurança
			for (int i = 0; i < valor; i++) {
				saldo += 1;
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 3. Devolver o Token: Acontece apenas uma vez após o término do loop
			sem.release(); 
		}
	}
	
	public int getSaldo() {
		return saldo;
	}
}
