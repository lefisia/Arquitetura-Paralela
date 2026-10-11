package io.github.projetos_consoles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


// 1) Criação do Bloco Prédio 
// - Número de andares;
// - Capacidade máxima.
public class Predio {
    public static final int NUM_ANDARES = 7;
    public static final int CAPACIDADE = 2; // Tentativa de 2 pôneis por elevador

    // Uso do semáforo Mutex
    public static Semaphore mutex = new Semaphore(1);
    
    // Semáforos para sinalizar a condição do elevador usando as variáveis do prédio
    public static Semaphore[] semEmbarque = new Semaphore[NUM_ANDARES];
    public static Semaphore[] semDesembarque = new Semaphore[NUM_ANDARES];
    public static Semaphore semAcaoConcluida = new Semaphore(0);

    
    // Para cada pôneis passageiro, foi criado uma lista
    public static List<Passageiro>[] filasAndares = new ArrayList[NUM_ANDARES];
    public static List<Passageiro> passageirosNoElevador = new ArrayList<>();

    // O elevador estará diretamente correlacionado a condição de embarque, desembargque e fila
    public static void inicializar() {
        for (int i = 0; i < NUM_ANDARES; i++) {
            semEmbarque[i] = new Semaphore(0);
            semDesembarque[i] = new Semaphore(0);
            filasAndares[i] = new ArrayList<>();
        }
        passageirosNoElevador.clear();
    }
}