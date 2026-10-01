package io.github.projetos_consoles;

// Semelhante ao jogo de corrida, usarei o .Random para cada carrinho
import java.util.Random;
import java.util.concurrent.Semaphore;

public class CarroThreadRandom extends Thread {
    public String nome;
    public int aceleracao;
    
    // Corrdenadas de cada matriz
    public volatile float x;
    public volatile float y;
    
    private float dirX;
    private float dirY;
    
    public volatile int distanciaPercorrida = 0; 
    public int distanciaTotal;
    public boolean ativo = true;

    // Criação do semáforo (Mutex) - Aqui eu estipularei a zona de risco
    private static Semaphore mutexCruzamento = new Semaphore(1);
    private boolean noCruzamento = false;

    private static Random gerador = new Random();

    
    // Informações dos carros
    public CarroThreadRandom(String nome, int aceleracaoMaxima, float startX, float startY, float dirX, float dirY, int distanciaTotal) {
        this.nome = nome;
        this.aceleracao = aceleracaoMaxima;
        this.x = startX;
        this.y = startY;
        this.dirX = dirX;
        this.dirY = dirY;
        this.distanciaTotal = distanciaTotal;
    }

    @Override
    public void run() {
        while (distanciaPercorrida < distanciaTotal) {
            int avanco = gerador.nextInt(aceleracao) + 1;
            distanciaPercorrida += avanco;
            
            // Para os carros avançarem na tela
            x += avanco * dirX;
            y += avanco * dirY;
            
            // Especificação da Zona de risco (intersecçao do cruzamento)
            boolean zonaDeRisco = (x > 320 && x < 480) && (y > 220 && y < 380);
            
            // Condição de espera das threads
            if (zonaDeRisco && !noCruzamento) {
                try {
                    mutexCruzamento.acquire(); // Bloqueia o cruzamento para a thread
                    noCruzamento = true;
                    System.out.println(nome + " ENTROU no cruzamento.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (!zonaDeRisco && noCruzamento) {
                mutexCruzamento.release(); // O semáforo libera o cruzamento para a thread
                noCruzamento = false;
                System.out.println(nome + " SAIU do cruzamento.");
            }

            if (distanciaPercorrida >= distanciaTotal) {
                ativo = false;
                System.out.println(nome + " Concluiu o trajeto inteiro!");
                
                // Não sei se é o correto, mas aqui é uma grantia para caso a thread termine dentro da zona de risco
                if (noCruzamento) {
                    mutexCruzamento.release();
                }
                break;
            }
            
            try {
                int tempoPausa = gerador.nextInt(50) + 20; // Para a renderização dos carros andando fique esteticamente agradável
                Thread.sleep(tempoPausa);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}