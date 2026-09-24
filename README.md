# Arquitetura Paralela & Jogos p/ Consoles 🎮⚙️

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![LibGDX](https://img.shields.io/badge/LibGDX-E33632?style=for-the-badge&logo=libgdx&logoColor=white)
![Multithreading](https://img.shields.io/badge/Multithreading-Concurrent-blue?style=for-the-badge)

Este repositório documenta os meus estudos e projetos focados na disciplina Jogos para Consoles. Nele, darei ênfase a **programação paralela, sincronização de threads e compartilhamento seguro de memória**.

---

## 📂 Meus Projetos:

### 1. Simulador de Corrida Multithread (GPU / LibGDX)
**Diretório:** [`/Projeto_Multithread_em_GPU`](./Projeto_Multithread_em_GPU)

Uma simulação de corrida de carros onde cada veículo opera de forma totalmente assíncrona na sua própria *Thread*. 
* **Tecnologias:** Java, LibGDX (SpriteBatch, Textures).
* **Conceitos:** Variáveis voláteis, *Race Conditions*, rendering em GPU separado da lógica de física.
* **Competências Desenvolvidas:**
  * Desacoplamento da lógica matemática do motor de renderização visual, passo essencial para manter a estabilidade do *framerate*.
  * Gestão do ciclo de vida de múltiplas *threads* simultâneas num ambiente gráfico.
  * Mitigação de problemas de concorrência em cenários onde a sincronização estrita comprometeria a fluidez visual do jogo.

### 2. Simulador de Elevador - Sincronização Estrita (CPU / Swing)
**Diretório:** [`/Processamento_Multithread_em_CPU`](./Processamento_Multithread_em_CPU)

Sistema de controlo de um elevador e de passageiros num prédio, inspirado na estética do jogo clássico *Elevator Action*. 
* **Tecnologias:** Java Swing, Java 2D Graphics.
* **Conceitos:** Problema do Produtor-Consumidor modificado, exclusão mútua estrita utilizando `java.util.concurrent.Semaphore`, prevenção de *starvation* e *deadlocks*.
* **Competências Desenvolvidas:**
  * Substituição de blocos *synchronized* nativos por semáforos customizados para garantir um controlo de acesso granular à região crítica.
  * Modelagem de arquiteturas orientadas a eventos autônomos (passageiros como entidades que geram requisições independentes e competem pelo recurso).
  * Criação de interfaces gráficas retro em Swing que desenham o estado interno das *threads* em tempo real (filas, portas, cabine).

### 3. Oficina Game - Produtor/Consumidor
**Diretório:** [`/Produtor_Consumidor_Exemplo_Aula`](./Produtor_Consumidor_Exemplo_Aula)[cite: 7]

Uma versão visual e interativa do problema clássico da computação concorrente, onde um Lenhador (Produtor) gera madeira e os Carpinteiros (Consumidores) a utilizam[cite: 7].
* **Tecnologias:** Java Puro.
* **Conceitos:** Partilha de Recursos Limitados, *Render Loop* em Terminal.
* **Competências Desenvolvidas:**
  * Orquestração segura de entidades que partilham e disputam um recurso central com capacidade máxima predefinida (Depósito).
  * Implementação de um *Game Loop* simplificado via consola que atualiza e monitoriza os estados das *threads* dinamicamente.

### 4. Sistema de Transações Bancárias (Wallet)
**Diretório:** [`/Wallet_Exemplo_Aula`](./Wallet_Exemplo_Aula)[cite: 7]

Um laboratório de testes focado em proteger blocos de memória durante depósitos massivos e simultâneos[cite: 7].
* **Tecnologias:** Java Puro.
* **Conceitos:** *Mutex*, Região Crítica, Serialização de Processos.
* **Competências Desenvolvidas:**
  * Identificação cirúrgica de quebras de concorrência onde lógicas básicas (`saldo += 1`) falham sob pressão multithread.
  * Resolução definitiva de *Deadlocks* arquitetando corretamente os blocos `try-catch-finally` para garantir a libertação de semáforos, independentemente de falhas de execução.

---

## Como Clonar e Executar?

Para explorar o código fonte ou rodar as simulações na sua máquina, apenas:

1. Clone este repositório:
   ```bash
   git clone [https://github.com/lefisia/Arquitetura-Paralela.git](https://github.com/lefisia/Arquitetura-Paralela.git)
