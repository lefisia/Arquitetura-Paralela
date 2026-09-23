
public class Wallet {
	
	int saldo = 0;
	
	public void depositar(int valor) {
		for (int i= 0; i < valor; i++) {
			saldo +=1;
			
		}
	}
		public int getSaldo() {
			return saldo;
		}
		
	}

