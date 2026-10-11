package io.github.projetos_consoles;

public class Elevador extends Thread {
    public volatile float x = 390; 
    public volatile float y = 20; 
    public volatile int andarAtual = 0;
    public volatile boolean portaAberta = false;
    private int direcao = 1;

    @Override
    public void run() {
        while (true) {
            try {
                Predio.mutex.acquire();
                boolean deveParar = verificarParada(andarAtual);
                Predio.mutex.release();

                if (deveParar) {
                    abrirPorta();
                    realizarDesembarques();
                    realizarEmbarques();
                    fecharPorta();
                }

                Predio.mutex.acquire();
                boolean semPedidos = semChamadas();
                Predio.mutex.release();

                if (semPedidos) {
                    Thread.sleep(100);
                    continue;
                }

                if (andarAtual == 6) direcao = -1;
                else if (andarAtual == 0) direcao = 1;

                visitarAndar(andarAtual + direcao);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean verificarParada(int andar) {
        boolean alguemQuerDescer = false;
        for (Passageiro p : Predio.passageirosNoElevador) {
            if (p.andarDestino == andar) alguemQuerDescer = true;
        }
        boolean alguemQuerSubir = !Predio.filasAndares[andar].isEmpty() && Predio.passageirosNoElevador.size() < Predio.CAPACIDADE;
        return alguemQuerDescer || alguemQuerSubir;
    }

    private boolean semChamadas() {
        if (!Predio.passageirosNoElevador.isEmpty()) return false;
        for (int i = 0; i < Predio.NUM_ANDARES; i++) {
            if (!Predio.filasAndares[i].isEmpty()) return false;
        }
        return true;
    }

    private void abrirPorta() throws InterruptedException {
        Thread.sleep(500); 
        portaAberta = true;
        Thread.sleep(500);
    }

    private void fecharPorta() throws InterruptedException {
        Thread.sleep(500);
        portaAberta = false;
        Thread.sleep(500);
    }

    private void realizarDesembarques() throws InterruptedException {
        while (true) {
            Predio.mutex.acquire();
            boolean alguemDesce = false;
            for (Passageiro p : Predio.passageirosNoElevador) {
                if (p.andarDestino == andarAtual) alguemDesce = true;
            }
            Predio.mutex.release();

            if (!alguemDesce) break;

            Predio.semDesembarque[andarAtual].release();
            Predio.semAcaoConcluida.acquire(); 
        }
    }

    private void realizarEmbarques() throws InterruptedException {
        while (true) {
            Predio.mutex.acquire();
            boolean podeEmbarcar = !Predio.filasAndares[andarAtual].isEmpty() && Predio.passageirosNoElevador.size() < Predio.CAPACIDADE;
            Predio.mutex.release();

            if (!podeEmbarcar) break;

            Predio.semEmbarque[andarAtual].release();
            Predio.semAcaoConcluida.acquire(); 
        }
    }

    private void visitarAndar(int destino) throws InterruptedException {
        float targetY = 20 + (destino * 114); 
        
        while (this.y != targetY) {
            if (this.y < targetY) this.y += 2;
            else this.y -= 2;
            Thread.sleep(10); 
        }
        this.andarAtual = destino;
    }
}