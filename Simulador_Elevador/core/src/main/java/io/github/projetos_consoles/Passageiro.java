package io.github.projetos_consoles;

public class Passageiro extends Thread {
    public String nomeTextura;
    public int andarOrigem;
    public int andarDestino;
    
    public volatile float x;
    public volatile float y;
    public volatile int estado; // Se, 0= Aguardando, 1= Dentro do Elevador, 2= Saindo

    public Passageiro(String nomeTextura, int andarOrigem, int andarDestino) {
        this.nomeTextura = nomeTextura;
        this.andarOrigem = andarOrigem;
        this.andarDestino = andarDestino;
        // O elevador começara no estado de Aguardo
        this.estado = 0;
        this.x = 80; //Eixo x para demarcar a entrada das pôneis
        this.y = 20 + (andarOrigem * 114);
    }

    @Override
    public void run() {
        try {
            Predio.mutex.acquire();
            Predio.filasAndares[andarOrigem].add(this);
            
            this.x = 80 + (Predio.filasAndares[andarOrigem].indexOf(this) * 50);
            Predio.mutex.release();

            Predio.semEmbarque[andarOrigem].acquire();

            Predio.mutex.acquire();
            Predio.filasAndares[andarOrigem].remove(this);
            Predio.passageirosNoElevador.add(this);
            this.estado = 1; 
            Predio.mutex.release();
            
            Predio.semAcaoConcluida.release();

            Predio.semDesembarque[andarDestino].acquire();

            Predio.mutex.acquire();
            Predio.passageirosNoElevador.remove(this);
            this.estado = 2; 
            this.x = 520;
            this.y = 20 + (andarDestino * 114);
            Predio.mutex.release();
            
            Predio.semAcaoConcluida.release();

            // O pônei andará até o final da tela, até a secção saída
            while(this.x < 950) {
                this.x += 2;
                Thread.sleep(15);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}