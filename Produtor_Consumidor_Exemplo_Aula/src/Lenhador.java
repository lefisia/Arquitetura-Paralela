public class Lenhador implements Runnable {
	private final Deposito deposito;
	private int contador = 0;

	public Lenhador(Deposito deposito) { 
		this.deposito = deposito; 
	}

	@Override
	public void run() {
		try {
			// Aqui tentarei produzir no depósito
			while (true) {
				Madeira item = new Madeira("Tora " + contador++);
				deposito.produzir(item); // Pode bloquear

				// Simula o tempo para cortar outra árvore
				Thread.sleep((long) (Math.random() * 800 + 200)); 
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}