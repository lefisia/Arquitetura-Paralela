# Arquitetura Paralela & Jogos p/ Consoles 

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![LibGDX](https://img.shields.io/badge/LibGDX-E33632?style=for-the-badge&logo=libgdx&logoColor=white)
![Multithreading](https://img.shields.io/badge/Multithreading-Concurrent-blue?style=for-the-badge)

Este repositório documenta os meus estudos e projetos focados na disciplina Jogos para Consoles. Nele, darei ênfase a **programação paralela, sincronização de threads e compartilhamento seguro de memória**.

---

## 📂 Meus Projetos:

### 1. Simulador de Corrida - Multithreading (GPU / LibGDX)
**Diretório:** [`/Projeto_Multithread_em_GPU`](./Projeto_Multithread_em_GPU)

Uma simulação de corrida de carros onde cada veículo opera de forma independente usando Threads. 
* **Tecnologias:** Java, LibGDX.
* **Conceitos:** Variáveis voláteis, Race Conditions, renderização em GPU.
* **Competências Desenvolvidas:**
  * Gestão do ciclo de vida de múltiplas threads num ambiente gráfico.
  * Solução de problemas de concorrência em cenários onde a sincronização estrita comprometeria a fluidez visual do jogo, isto é: *Race Conditions*.

### 2. Simulador de Elevador - Sincronização e uso de Semáforos (CPU / Swing)
**Diretório:** [`/Processamento_Multithread_em_CPU`](./Processamento_Multithread_em_CPU)

Uma simulação de elevador de passageiros num prédio, usando *Threads* e *Semáforos*. 
* **Tecnologias:** Java Swing.
* **Conceitos:** Problema do *Produtor-Consumidor*, exclusão mútua utilizando `java.util.concurrent.Semaphore`, prevenção de *Starvation* e *Deadlocks*.
* **Competências Desenvolvidas:**
  * Substituição de blocos *synchronized* por *semáforos* para garantir controle de acesso à zona crítica.
  * Modelagem de arquiteturas orientadas a eventos autônomos (passageiros como entidades que geram requisições independentes e competem pelo recurso).
  * Criação de interfaces gráficas em Java Swing.

### 3. Oficina Game - Exemplo Produtor/Consumidor 
**Diretório:** [`/Produtor_Consumidor_Exemplo_Aula`](./Produtor_Consumidor_Exemplo_Aula).

Exemplo de simulação, dada em sala de aula, onde um Lenhador (Produtor) gera madeira e os Carpinteiros (Consumidores) a utilizam.
* **Tecnologias:** Java.
* **Conceitos:** Compartilhamento de Recursos Limitados, *Loop* em Terminal (console).
* **Competências Desenvolvidas:**
  * Orquestração segura de entidades que partilham e disputam um recurso central com capacidade máxima predefinida (Depósito).
  * Implementação de um *Game Loop* básico e atualização dos estados das *threads* de forma dinâmica.

### 4. Sistema de Transações Bancárias - Exemplo de uso de Mutex
**Diretório:** [`/Wallet_Exemplo_Aula`](./Wallet_Exemplo_Aula).

Exemplo de uma simulação de transição bancária, dada em sala de aula, com enfoque de proteger blocos de memória durante depósitos simultâneos de dinheiro.
* **Tecnologias:** Java.
* **Conceitos:** *Mutex*, Região Crítica, Serialização de Processos.
* **Competências Desenvolvidas:**
  * Identificação de quebras de concorrência onde lógicas básicas (`saldo += 1`) falham no multithread.
  * Resolução de *Deadlocks* arquitetando corretamente os blocos `try-catch-finally` para garantir a libertação de semáforos, independentemente de falhas de execução.

### 5. Simulador de Tráfego - Multitreading (GPU / LibGDX)
**Diretório:** [`/Simulador_Trafego`](./Simulador_Trafego).

<img width="640" height="478" alt="Atividade_3_Consoles" src="https://github.com/user-attachments/assets/a410922f-0b34-49fe-8d31-8c5f916547aa" />

Uma simulação de tráfego gráfica desenvolvida em Java e LibGDX, onde cada veículo opera de forma concorrente como uma Thread independente, gerindo o acesso e o congestionamento em um cruzamento centralizado.
* **Tecnologias:** Java, LibGDX.
* **Conceitos:** *Mutex*, Região Crítica, Sincronização de *Threads* com *Semáforos*.
* **Competências Desenvolvidas:**
  * Implementação de coordenadas voláteis e tratamento de concorrência para sincronizar atualizações de *Threads* a 60 FPS.
  * Mapeamento de zonas de risco e exclusão mútua em um layout de via cruzada usando *Semáforo*;

---

## Como Clonar e Executar?

Para explorar o código fonte ou rodar as simulações na sua máquina, apenas:

1. Clone este repositório:
   ```bash
   git clone [https://github.com/lefisia/Arquitetura-Paralela.git](https://github.com/lefisia/Arquitetura-Paralela.git)
