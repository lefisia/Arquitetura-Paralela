
public class Transacao extends Thread {
	int valor = 0;
	Wallet wallet;
	
	public Transacao(int _valor, Wallet _wallet) {
		valor = _valor;
		wallet = _wallet;
		
	}
	
	@Override
	public void run () {
		wallet.depositar(valor);
		
	}
		
}


