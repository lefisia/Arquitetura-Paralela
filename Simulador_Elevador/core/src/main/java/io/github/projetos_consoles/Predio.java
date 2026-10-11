package io.github.projetos_consoles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Predio {
    public static final int NUM_ANDARES = 7;
    public static final int CAPACIDADE = 2; // Tentativa de 2 passageiros

    // Semáforo Mutex
    public static Semaphore mutex = new Semaphore(1);
    
    // Semáforos para sinalizar a condição
    public static Semaphore[] semEmbarque = new Semaphore[NUM_ANDARES];
    public static Semaphore[] semDesembarque = new Semaphore[NUM_ANDARES];
    public static Semaphore semAcaoConcluida = new Semaphore(0);

    public static List<Passageiro>[] filasAndares = new ArrayList[NUM_ANDARES];
    public static List<Passageiro> passageirosNoElevador = new ArrayList<>();

    public static void inicializar() {
        for (int i = 0; i < NUM_ANDARES; i++) {
            semEmbarque[i] = new Semaphore(0);
            semDesembarque[i] = new Semaphore(0);
            filasAndares[i] = new ArrayList<>();
        }
        passageirosNoElevador.clear();
    }
}