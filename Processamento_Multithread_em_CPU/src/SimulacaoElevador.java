import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class SimulacaoElevador extends JPanel {

    // Parâmetros que serão utilizados:
    static final int F = 5; // Nº de andares
    static final int N = 8; // Nº de passageiros
    
    static int passageirosEntregues = 0;

    // Método semáforos
    static Semaphore mutex = new Semaphore(1); 
    static Semaphore acaoConcluida = new Semaphore(0);
    
    static Predio predio;
    static SimulacaoElevador painel; 

    public static void atualizarTela() {
        if (painel != null) painel.repaint();
    }

    public static void main(String[] args) {
    	
        // Interface gráfica sem usar libGDX
        JFrame frame = new JFrame("Super Elevador");
        painel = new SimulacaoElevador();
        painel.setBackground(Color.BLACK);
        frame.add(painel);
        frame.setSize(800, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Prédio e Elevador
        predio = new Predio(F);
        predio.elevador.start();

        // Passageiros por Threads
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            int origem = rand.nextInt(F);
            int destino = rand.nextInt(F);
            while (origem == destino) {
                destino = rand.nextInt(F);
            }
            
            Passageiro p = new Passageiro(i, origem, destino);
            p.start();
            
            // Sincronização em millisegundos
            try { Thread.sleep(600); } catch (Exception e) {} 
        }
    }

    // Parte de Renderização
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (predio == null) return;

        // Desenhar as linhas
        g.setColor(Color.CYAN);
        for (int i = 0; i < F; i++) {
            int yAndar = 500 - (i * 100);
            g.drawLine(50, yAndar, 750, yAndar);
            g.drawString("Andar " + i, 50, yAndar - 10);
        }

        // Desenhar os passageiros
        for (int i = 0; i < F; i++) {
            List<Passageiro> fila = predio.andares[i];
            for (int j = 0; j < fila.size(); j++) {
                Passageiro p = fila.get(j);
                g.setColor(p.cor);
                g.fillRect(120 + (j * 30), 470 - (i * 100), 15, 30);
                g.setColor(Color.WHITE);
                g.drawString(String.valueOf(p.andarDestino), 120 + (j * 30), 465 - (i * 100)); 
            }
        }

        // Desenhar Elevador 
        Elevador el = predio.elevador;
        g.setColor(Color.DARK_GRAY);
        g.fillRect(350, 50, 80, 500);

        // Desenhar cabine do Elevador
        int cabineY = 440 - (int) el.yVisual;
        g.setColor(el.portaAberta ? new Color(0, 200, 0) : new Color(200, 0, 0)); 
        g.fillRect(360, cabineY, 60, 60);
        g.setColor(Color.YELLOW);
        g.drawRect(360, cabineY, 60, 60);

        // Passageiro dentro do Elevador
        if (el.passageiroNoElevador != null) {
            g.setColor(el.passageiroNoElevador.cor);
            g.fillRect(382, cabineY + 20, 15, 30);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(el.passageiroNoElevador.andarDestino), 382, cabineY + 15);
        }
        
        // Estatísticas
        g.setColor(Color.WHITE);
        g.drawString("Passageiros entregues: " + passageirosEntregues + " / " + N, 600, 30);
    }
}

// Informações sobre o prédio
class Predio {
    List<Passageiro>[] andares;
    Elevador elevador;

    @SuppressWarnings("unchecked")
    public Predio(int numAndares) {
        andares = new ArrayList[numAndares];
        for (int i = 0; i < numAndares; i++) {
            andares[i] = new ArrayList<>();
        }
        elevador = new Elevador(0); 
    }
}

// Construtor das Threads
class Passageiro extends Thread {
    int idPassageiro;
    int andarOrigem;
    int andarDestino;
    Color cor; 
    
    Semaphore semaforoEmbarque = new Semaphore(0);
    Semaphore semaforoDesembarque = new Semaphore(0);

    public Passageiro(int id, int origem, int destino) {
        this.idPassageiro = id;
        this.andarOrigem = origem;
        this.andarDestino = destino;
        
        Color[] cores = {Color.MAGENTA, Color.ORANGE, Color.PINK, Color.LIGHT_GRAY, new Color(150, 150, 255)};
        this.cor = cores[id % cores.length];
    }

    @Override
    public void run() {
        try {

            SimulacaoElevador.mutex.acquire();
            SimulacaoElevador.predio.andares[andarOrigem].add(this);
            SimulacaoElevador.mutex.release();
            SimulacaoElevador.atualizarTela();


            semaforoEmbarque.acquire();

    
            SimulacaoElevador.acaoConcluida.release();
            SimulacaoElevador.atualizarTela();


            semaforoDesembarque.acquire();

            SimulacaoElevador.acaoConcluida.release();
            SimulacaoElevador.atualizarTela();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Elevador extends Thread {
    int andarAtual;
    boolean portaAberta = false;
    Passageiro passageiroNoElevador = null;
    float yVisual; 

    public Elevador(int andarInicial) {
        this.andarAtual = andarInicial;
        this.yVisual = andarInicial * 100; 
    }

    public void AbrirPorta() {
        portaAberta = true;
        SimulacaoElevador.atualizarTela();
        try { Thread.sleep(600); } catch (Exception e) {} 
    }

    public void FecharPorta() {
        portaAberta = false;
        SimulacaoElevador.atualizarTela();
        try { Thread.sleep(600); } catch (Exception e) {}
    }

    public void VisitarAndar(int destino) {
        if (andarAtual == destino) return;


        int step = andarAtual < destino ? 1 : -1;
        float targetY = destino * 100;
        
        while (Math.abs(yVisual - targetY) > 1.0f) {
            yVisual += step * 2.5f; 
            SimulacaoElevador.atualizarTela();
            try { Thread.sleep(20); } catch (Exception e) {}
        }
        
        andarAtual = destino;
        yVisual = andarAtual * 100;
        SimulacaoElevador.atualizarTela();
    }

    @Override
    public void run() {
        try {

            while (SimulacaoElevador.passageirosEntregues < SimulacaoElevador.N) {
                
                SimulacaoElevador.mutex.acquire();
                
                int destino = -1;
                if (passageiroNoElevador != null) {
                    destino = passageiroNoElevador.andarDestino; // Se tem alguém, vai para o destino dele
                } else {
                    destino = buscarAndarComPassageiro(); // Se está vazio, procura quem apanhar
                }

                if (destino == -1) {
                    SimulacaoElevador.mutex.release();
                    Thread.sleep(100); 
                    continue;
                }
                
                SimulacaoElevador.mutex.release();


                VisitarAndar(destino);

                SimulacaoElevador.mutex.acquire();
                boolean vaiDesembarcar = (passageiroNoElevador != null && passageiroNoElevador.andarDestino == andarAtual);
                boolean vaiEmbarcar = (passageiroNoElevador == null && !SimulacaoElevador.predio.andares[andarAtual].isEmpty());
                SimulacaoElevador.mutex.release();

                if (vaiDesembarcar || vaiEmbarcar) {
                    AbrirPorta();

                    // Desembarque dos passageiros
                    if (vaiDesembarcar) {
                        SimulacaoElevador.mutex.acquire();
                        passageiroNoElevador.semaforoDesembarque.release(); 
                        SimulacaoElevador.mutex.release();

                        SimulacaoElevador.acaoConcluida.acquire(); 

                        SimulacaoElevador.mutex.acquire();
                        passageiroNoElevador = null;
                        SimulacaoElevador.passageirosEntregues++;
                        SimulacaoElevador.mutex.release();
                    }


                    SimulacaoElevador.mutex.acquire();
                    if (passageiroNoElevador == null && !SimulacaoElevador.predio.andares[andarAtual].isEmpty()) {
                        Passageiro p = SimulacaoElevador.predio.andares[andarAtual].remove(0);
                        p.semaforoEmbarque.release(); 
                        SimulacaoElevador.mutex.release();

                        SimulacaoElevador.acaoConcluida.acquire(); 

                        SimulacaoElevador.mutex.acquire();
                        passageiroNoElevador = p;
                        SimulacaoElevador.mutex.release();
                    } else {
                        SimulacaoElevador.mutex.release();
                    }

                    FecharPorta();
                }
            }
            System.out.println("FIM!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private int buscarAndarComPassageiro() {
        for (int i = 0; i < SimulacaoElevador.F; i++) {
            int andar = (andarAtual + i) % SimulacaoElevador.F;
            if (!SimulacaoElevador.predio.andares[andar].isEmpty()) {
                return andar;
            }
        }
        return -1; 
    }
}