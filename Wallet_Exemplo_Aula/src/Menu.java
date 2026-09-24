
public class Menu {

	public Menu() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
			Wallet wallet = new Wallet();
			// Erro não determinístico - Sincronização:
			// Sequenciar a serialização do programa
			// Uso do Mutex no menor trecho possível
			Transacao Tw1 = new Transacao (1000, wallet);
			Transacao Tw2 = new Transacao (3000, wallet);
			
			Tw1.start();
			Tw2.start();
		
			try {
				Tw1.join(); 

			} catch (InterruptedException e) {
					
				e.printStackTrace(); }
				
				
			
			try {
				Tw2.join();
			} catch (InterruptedException e) {
					
				e.printStackTrace(); }
				
			System.out.println("Saldo Atual:" + wallet.getSaldo()); 
			}
	{
				
			}
	
			}
			
