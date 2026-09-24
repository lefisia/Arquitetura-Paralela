package io.github.projetos_consoles; 

import java.util.Random;

public class CarroThreadRandom extends Thread {
    public String nome;
    public int aceleracao;
    
    public volatile int distanciaPercorrida = 0; 
    public int distanciaTotal;

    // Gerador de números aleatórios radom:
    private static Random gerador = new Random();

    // Construtor do carro
    public CarroThreadRandom(String nome, int aceleracaoMaxima, int distanciaTotal) {
        this.nome = nome;
        this.aceleracao = aceleracaoMaxima;
        this.distanciaTotal = distanciaTotal;
    }

   @Override
   public void run() {
  
        while (distanciaPercorrida < distanciaTotal) {
            
            
            int avanco = gerador.nextInt(aceleracao) + 1;

            distanciaPercorrida += avanco;
            
            if (distanciaPercorrida >= distanciaTotal) {
                distanciaPercorrida = distanciaTotal; // Trava a posição na linha de chegada
                System.out.println(nome + " alcançou a linha de chegada.");
                break;
            }
            
            System.out.println("O " + nome + " andou " + avanco + "m        e já percorreu " + distanciaPercorrida + "m");
            
            try {
                int tempoPausa = gerador.nextInt(500) + 100;
                Thread.sleep(tempoPausa);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

