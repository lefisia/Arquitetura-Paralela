# 📑 Trabalho Bimestral 1: Simulador de Elevador
## 1. Sobre o Projeto
Este projeto é uma aplicação gráfica construída em **Java** com o motor gráfico **LibGDX**, focado nos estudos sobre programação concorrente/paralela.

O atual sistema simula o fluxo de um prédio de 7 andares. Cada passageiro e a própria cabine do elevador operam como *Threads* completamente independentes. O grande desafio tecnológico foi coordenar as *Threads* para embarcarem e desembarcarem com segurança, tudo a 60 FPS. Além disso, problemas como **condições de *Race Conditions* e*Deadlocks* foram resolvidos com êxito.

<img width="800" height="711" alt="Atividade_1_bimestral_V0 1" src="https://github.com/user-attachments/assets/c5bcd67e-3524-48e2-885f-4495d6c66e17" />


## 2. Tecnologias Utilizadas

*   **Linguagem:** Java 
*   **Motor Gráfico:** LibGDX 
*   **Build:** Gradle 
*   **IDE:** Eclipse IDE for Java Develops 

## 3. Conceitos Aplicados
*   **Multithreading:** Múltiplas classes herdando de `Thread`.
*   **Mutex:** Bloqueio do acesso às listas para que apenas uma Thread funcione por vez.
*   **Semáforos:** Utilização de `java.util.concurrent.Semaphore` para sinalizar ao passageiro da scondições do elevador e os momentos de embarque/desembarque.
*   **Delegação de Exceções (`throws`):** Tratamento do `InterruptedException` que ocorrem com os métodos `.sleep()` e `.acquire()`.
  
## 4. Como Executar o Projeto?
**Execução via Terminal:**
  1. Faça o clone do repositório;
  2. Navegue até ao diretório raiz do projeto: `cd Simulador_Elevador`
  3. No terminal, escreva: `.\gradlew lwjgl3:run` + `enter`
