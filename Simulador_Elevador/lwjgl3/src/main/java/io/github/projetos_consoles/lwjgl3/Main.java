package io.github.projetos_consoles.lwjgl3;

// 4) Criação do Main com String [] args:
// Para eu conseguir rodar esse programa, terei que: pasta do arquivo>show in> terminal> digital .\gradlew lwjgl3:run
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
//Para não esquecer: sempre confirmar o import do arquivo do projeto!
import io.github.projetos_consoles.ElevadorGame;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Simulador de Elevador Mágico");
        
        // Background e tela da simulação
        config.setWindowedMode(900, 800);
        config.useVsync(true);
        config.setForegroundFPS(60);
        
        new Lwjgl3Application(new ElevadorGame(), config);
    }
}