# Arquitetura Paralela & Jogos p/ Consoles

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![LibGDX](https://img.shields.io/badge/LibGDX-E33632?style=for-the-badge&logo=libgdx&logoColor=white)
![Multithreading](https://img.shields.io/badge/Multithreading-Concurrent-blue?style=for-the-badge)

Este é um repositório dedicado aos projetos práticos desenvolvidos para a disciplina de Jogos para Consoles. O foco principal destas implementações é o estudo aprofundado de **arquitetura paralela**, aliada ao desenvolvimento de interfaces gráficas usando a libGDX.

---

## 📂 Meus Projetos Desenvolvidos:

### 1. Simulador de Corrida Multithread (GPU / LibGDX)
**Diretório:** [`/Projeto_Multithread_em_GPU`](./Projeto_Multithread_em_GPU)

Uma simulação de corrida de carros onde cada veículo opera de forma totalmente assíncrona na sua própria *Thread*. 
* **Tecnologias:** Java, LibGDX (SpriteBatch, Textures).
* **Conceitos:** Variáveis voláteis, *Race Conditions* controladas, rendering em GPU separado da lógica de física.

### 2. Simulador de Elevator - Sincronização Estrita (CPU / Swing)
**Diretório:** [`/Processamento_Multithread_em_CPU`](./Processamento_Multithread_em_CPU)

Sistema de controlo de um elevador e de passageiros num prédio, inspirado no clássico jogo de Atari. 
* **Tecnologias:** Java Swing, Graphics (Estética Retro).
* **Conceitos:** Problema do Produtor-Consumidor, exclusão mútua estrita utilizando `java.util.concurrent.Semaphore` (sem uso de *synchronized*), prevenção de *starvation* e *deadlocks*.

### 3. Sistema de Transações Bancárias (Wallet)
**Diretório:** [`/Wallet_Exemplo_Aula`](./Wallet_Exemplo_Aula)


## 🛠️ Como Clonar e Executar

Para testar os projetos localmente na sua máquina:

1. Clone este repositório:
   ```bash
   git clone [https://github.com/lefisia/Arquitetura-Paralela.git](https://github.com/lefisia/Arquitetura-Paralela.git)
