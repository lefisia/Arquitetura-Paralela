package io.github.projetos_consoles;

public class Main {

    public static void main(String[] args) {
        
        System.out.println("--- 1,2,3 ---");
        
        // Criamos os 5 carros
        CarroThreadRandom carro1 = new CarroThreadRandom("Carro_01", 10, 200);
        CarroThreadRandom carro2 = new CarroThreadRandom("Carro_02", 10, 200);
        CarroThreadRandom carro3 = new CarroThreadRandom("Carro_03", 10, 200);
        CarroThreadRandom carro4 = new CarroThreadRandom("Carro_04", 10, 200);
        CarroThreadRandom carro5 = new CarroThreadRandom("Carro_05", 10, 200);
        
        System.out.println("--- COMEÇAR! ---");
        System.out.println("");
        
        // Iniciamos as threads
        carro1.start();
        carro2.start();
        carro3.start();
        carro4.start();
        carro5.start();
    }
}