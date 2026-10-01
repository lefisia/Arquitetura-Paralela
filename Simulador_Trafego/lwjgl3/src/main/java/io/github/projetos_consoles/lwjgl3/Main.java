package io.github.projetos_consoles.lwjgl3;

// Obs.: Para executar este projeto, tive que forçar o eclipse pelo terminal: Simlador_trafego> Show in local terminal > .\gradlew lwjgl3:run > enter
// Aqui, importarei o libGDX
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.github.projetos_consoles.CorridaGame;


// Já que aqui é o Main, é importante ter esse bloco
public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Simulador de Tráfego");
        
        // Resolução da tela
        config.setWindowedMode(800, 600);
        config.useVsync(true);
        config.setForegroundFPS(60);
        
        // Chama a classe do jogo que eu criei e está no core
        new Lwjgl3Application(new CorridaGame(), config);
    }
}